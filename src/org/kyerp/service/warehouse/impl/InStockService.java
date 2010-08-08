package org.kyerp.service.warehouse.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.BillStatus;
import org.kyerp.domain.warehouse.InStock;
import org.kyerp.domain.warehouse.InStockDetail;
import org.kyerp.domain.warehouse.OutStock;
import org.kyerp.domain.warehouse.OutStockDetail;
import org.kyerp.domain.warehouse.StockDetail;
import org.kyerp.service.warehouse.IInOutTypeService;
import org.kyerp.service.warehouse.IInStockDetailService;
import org.kyerp.service.warehouse.IInStockService;
import org.kyerp.service.warehouse.IOutStockDetailService;
import org.kyerp.service.warehouse.IOutStockService;
import org.kyerp.service.warehouse.IStockDetailService;
import org.kyerp.service.warehouse.IStockService;
import org.kyerp.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InStockService extends DaoSupport<InStock> implements
		IInStockService {
	@Autowired
	IStockService stockService;
	@Autowired
	IStockDetailService stockDetailService;
	@Autowired
	IInStockDetailService inStockDetailService;
	@Autowired
	IOutStockDetailService outStockDetailService;
	@Autowired
	IInOutTypeService inOutTypeService;
	@Autowired
	IOutStockService outStockService;

	@Override
	public void save(InStock inStock) throws Exception {
		if ("-".equals(inStock.getInOutType().getInOutMark())) {
			throw new Exception(inStock.getInOutType().getName()+"是一个出库类型，请选择一个入库类型");
		}
		if (inStock.getDetails().size() == 0) {
			throw new Exception("至少需要一条入库项目！");
		}
		for (int i = 0; i < inStock.getDetails().size(); i++) {
			if (null == inStock.getDetails().get(i).getInStockCount()) {
				throw new Exception("第" + i + 1 + "条入库记录，入库数量为空");
			} else if (0 == BigDecimal.ZERO.compareTo(inStock.getDetails()
					.get(i).getInStockCount())) {
				throw new Exception("第" + i + 1 + "条入库记录，入库数量为零");
			}
		}
		// 设置单据状态
		inStock.setStatus(BillStatus.WRITING);
		// 设置填单人
		if (null == inStock.getWriteUser()) {
			inStock.setWriteUser(WebUtil.getCurrentUser());
			inStock.setWriteEmployee(WebUtil.getCurrentEmployee());
		}
		// 设置填单时间
		if (null == inStock.getWriteDate()) {
			inStock.setWriteDate(new Date());
		}
		if (null == inStock.getSerialNumber()
				|| inStock.getSerialNumber().length() == 0) {
			// 如果没有填写单号则设置单号
			inStock.setSerialNumber(nextSerialNumber());
		}
		updateInStockCountAndCost(inStock);
		super.save(inStock);
	}

	/*
	 * 审核入库单 改变状态为已审核,设置审核人，审核时间
	 * 
	 * @see org.kyerp.service.warehouse.IInStockService#checkInStock(
	 * org.kyerp.domain.warehouse.InStock)
	 */
	@Override
	public void checkInStock(Long inStockId) throws Exception {
		InStock inStock = find(inStockId);
		if (BillStatus.CHECKED == inStock.getStatus()) {
			throw new Exception("入库单(Id=" + inStockId + ")已经审核过，不能再审核。");
		}

		// 设置单据状态
		inStock.setStatus(BillStatus.CHECKED);
		// 设置审核日期
		inStock.setCheckDate(new Date());
		// 设置审核人
		inStock.setCheckUser(WebUtil.getCurrentUser());
		inStock.setCheckEmployee(WebUtil.getCurrentEmployee());
		// 循环取出入库单明细里的每个条目
		for (InStockDetail inStockDetail : inStock.getDetails()) {
			if (inStock.getDetails().size() == 0) {
				throw new Exception("至少需要一条入库项目！");
			}
			try {
				inStockDetail.setHappenDate(inStockDetail.getInStock().getArriveDate());
				//StockDetail stockDetail = stockService.inStock(inStockDetail);
				StockDetail stockDetail = stockService.dealWithInventoryDetail(inStockDetail);
				// 设置当前余额
				inStockDetail.setCurrentStockCount(stockDetail.getAmount());
				// 反查期初余额
				inStockDetail.setBegingStockCount(stockDetail.getAmount()
						.subtract(inStockDetail.getInStockCount()));
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("审核入库项目时发生错误:"+e.getLocalizedMessage());
			}
		}
		updateInStockCountAndCost(inStock);
		update(inStock);
	}

	@Override
	public String nextSerialNumber() throws Exception {
		int serno = 1;
		String head = "RK" + new SimpleDateFormat("yyyy").format(new Date());
		StringBuffer wherejpql = new StringBuffer("");
		List<Object> queryParams = new ArrayList<Object>();
		wherejpql.append(" o.serialNumber like ?").append(
				(queryParams.size() + 1));
		queryParams.add(head + "%");
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("serialNumber", "desc");
		if (getScrollData(wherejpql.toString(), queryParams.toArray(), orderby)
				.getTotalrecord() > 0) {
			InStock inStock = getScrollData(wherejpql.toString(),
					queryParams.toArray(), orderby).getResultlist().get(0);
			if (inStock != null) {
				String code = inStock.getSerialNumber();
				serno = Integer.parseInt(code.substring(head.length())) + 1;
			}
		}
		return head + new DecimalFormat("0000").format(serno);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kyerp.service.warehouse.IInStockService#congXiao(java.lang.Long)
	 */
	@Override
	public void congXiao(Long inStockId) throws Exception {
		InStock inStock = find(inStockId);
		if (null != inStock) {
			if (BillStatus.CONGXIAO.equals(inStock.getStatus())) {
				throw new Exception("入库单(Id=" + inStockId + ")已经冲销，不能再次冲销！");
			} else {
				OutStock outStock = new OutStock();
				// 设置收发类型为 "冲销入库"
				outStock.setInOutType(inOutTypeService.find(11L));
				outStock.setWriteDate(new Date());
				outStock.setWriteEmployee(WebUtil.getCurrentEmployee());
				outStock.setKeeper(WebUtil.getCurrentEmployee());
				outStock.setRemark(outStock.getSerialNumber() + "的冲销单");
				outStock.setStatus(BillStatus.WAITING_FOR_CHECK);
				// 设置出库部门，与出库人
				outStock.setReceiveDepartment(WebUtil.getCurrentEmployee()
						.getDepartment());
				outStock.setReceiveEmployee(WebUtil.getCurrentEmployee());
				outStock.setKeeper(WebUtil.getCurrentEmployee());

				outStockService.save(outStock);
				List<OutStockDetail> outStockDetails = new ArrayList<OutStockDetail>();
				for (InStockDetail inStockDetail : inStock.getDetails()) {
					OutStockDetail outStockDetail = new OutStockDetail();
					outStockDetail.setMaterial(inStockDetail.getMaterial());
					outStockDetail.setBatchNumber(inStockDetail
							.getBatchNumber());
					outStockDetail.setWarehouse(inStockDetail.getWarehouse());
					outStockDetail.setPrice(inStockDetail.getPrice());
					outStockDetail.setUnit(inStockDetail.getUnit());
					outStockDetail.setOutStockCount(inStockDetail
							.getInStockCount());
					outStockDetail.setOutStock(outStock);
					outStockDetailService.save(outStockDetail);
					outStockDetails.add(outStockDetail);
				}
				outStock.setDetails(outStockDetails);
				outStockService.update(outStock);

				inStock.setStatus(BillStatus.CONGXIAO);
				inStock.setRemark("已冲销!单号：" + inStock.getSerialNumber());
				update(outStock);
			}
		} else {
			throw new Exception("没有找到入库单(Id=" + inStockId + ")");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.kyerp.service.warehouse.IInStockService#calculateInStockCount(org
	 * .kyerp.domain.warehouse.InStock)
	 */
	@Override
	public void updateInStockCountAndCost(InStock inStock) throws Exception {
		inStock.setBillCount(BigDecimal.ZERO);
		inStock.setBillCost(BigDecimal.ZERO);
		for (InStockDetail detail : inStock.getDetails()) {
			inStock.setBillCount(inStock.getBillCount().add(
					detail.getInStockCount()));
			inStock.setBillCost(inStock.getBillCost().add(detail.getBillCost()));
		}

		// 删除数量为零 入库记录
		List<InStockDetail> inStockDetails = inStock.getDetails();
		Iterator<InStockDetail> iterator = inStockDetails.iterator();
		while (iterator.hasNext()) {
			InStockDetail inStockDetail = iterator.next();
			if (0 == BigDecimal.ZERO.compareTo(inStockDetail.getInStockCount())) {
				iterator.remove();
				if (null != inStockDetail.getId()) {
					logger.debug("删除数量为零的库存批次" + inStockDetail.toString());
					inStockDetailService.delete(inStockDetail.getId());
				}
			}
		}
		inStock.setDetails(inStockDetails);
	}

	@Override
	public void update(InStock inStock) throws Exception {
		updateInStockCountAndCost(inStock);
		super.update(inStock);
	}
}
