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
import org.kyerp.domain.warehouse.StockDetail;
import org.kyerp.domain.warehouse.print.PaperOutStock;
import org.kyerp.domain.warehouse.print.PaperOutStockDetail;
import org.kyerp.service.warehouse.IInOutTypeService;
import org.kyerp.service.warehouse.IInStockDetailService;
import org.kyerp.service.warehouse.IInStockService;
import org.kyerp.service.warehouse.IPaperOutStockService;
import org.kyerp.service.warehouse.IStockDetailService;
import org.kyerp.service.warehouse.IStockService;
import org.kyerp.service.warehouse.ISupplierService;
import org.kyerp.service.warehouse.print.IPaperOutStockDetailService;
import org.kyerp.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PaperOutStockService extends DaoSupport<PaperOutStock> implements
		IPaperOutStockService {
	@Autowired
	IStockService stockService;
	@Autowired
	IStockDetailService stockDetailService;
	@Autowired
	IInOutTypeService inOutTypeService;
	@Autowired
	IInStockDetailService inStockDetailService;
	@Autowired
	IInStockService inStockService;
	@Autowired
	ISupplierService supplierService;
	@Autowired
	IPaperOutStockDetailService paperOutStockDetailService;

	@Override
	public void save(PaperOutStock paperOutStock) throws Exception {
		if ("+".equals(paperOutStock.getInOutType().getInOutMark())) {
			throw new Exception(paperOutStock.getInOutType().getName()+"是一个入库类型，请选择一个出库类型");
		}
		if (paperOutStock.getDetails().size() == 0) {
			throw new Exception("至少需要一条出库项目！");
		}
		for (int i = 0; i < paperOutStock.getDetails().size(); i++) {
			if (null == paperOutStock.getDetails().get(i).getOutStockCount()) {
				throw new Exception("第" + i + 1 + "条记录，数量为空");
			} else if (0 == BigDecimal.ZERO.compareTo(paperOutStock.getDetails()
					.get(i).getOutStockCount())) {
				throw new Exception("第" + i + 1 + "条记录，数量为零");
			}
		}
		// 设置单据状态
		paperOutStock.setStatus(BillStatus.WRITING);
		// 设置填单人
		if (null == paperOutStock.getWriteUser()) {
			paperOutStock.setWriteUser(WebUtil.getCurrentUser());
			paperOutStock.setWriteEmployee(WebUtil.getCurrentEmployee());
		}
		// 设置填单时间
		if (null == paperOutStock.getWriteDate()) {
			paperOutStock.setWriteDate(new Date());
		}
		//设置时间
		for (PaperOutStockDetail detail : paperOutStock.getDetails()) {
			if (null == detail.getOutDate()) {
				detail.setOutDate(new Date());
			}
		}
		if (null == paperOutStock.getSerialNumber() || paperOutStock.getSerialNumber().length() == 0) {
			// 如果没有填写单号则设置单号
			try {
				paperOutStock.setSerialNumber(nextSerialNumber());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		updatePaperOutStockCountAndCost(paperOutStock);
		super.save(paperOutStock);
	}

	/*
	 * 审核出库单 改变状态为已审核,设置审核人，审核时间
	 * 
	 * @see org.kyerp.service.warehouse.IPaperOutStockService#checkPaperOutStock(
	 * org.kyerp.domain.warehouse.PaperOutStock)
	 */
	@Override
	public void checkPaperOutStock(Long paperOutStockId) throws Exception {
		PaperOutStock paperOutStock = find(paperOutStockId);
		if (BillStatus.CHECKED == paperOutStock.getStatus()) {
			throw new Exception("出库单("+paperOutStockId+")已经审核过，不能再审核。");
		}
		// 循环取出入库单明细里的每个条目；
		for (PaperOutStockDetail paperOutStockDetail : paperOutStock.getDetails()) {
			if (paperOutStock.getDetails().size() == 0) {
				throw new Exception("至少需要一条出库项目！");
			}
			try {
				paperOutStockDetail.setHappenDate(paperOutStockDetail.getPaperOutStock().getWriteDate());
				//StockDetail stockDetail = stockService.paperOutStock(paperOutStockDetail);
				StockDetail stockDetail = stockService.dealWithInventoryDetail(paperOutStockDetail);
				//设置当前余额
				paperOutStockDetail.setCurrentStockCount(stockDetail.getAmount());
				//反查期初余额
				paperOutStockDetail.setBegingStockCount(stockDetail.getAmount().add(paperOutStockDetail.getOutStockCount()));
				//处理结余和补纸的问题
				if (0 != paperOutStockDetail.getOutStockCount().compareTo(paperOutStockDetail.getRealOutStockCount())) {
					paperOutStockDetailService.dealwithMoreOrLess(paperOutStockDetail);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("审核出库项目时发生错误:"+e.getLocalizedMessage());
			}
		}
		// 设置单据状态
		paperOutStock.setStatus(BillStatus.CHECKED);
		// 设置审核日期
		paperOutStock.setCheckDate(new Date());
		// 设置审核人
		paperOutStock.setCheckUser(WebUtil.getCurrentUser());
		paperOutStock.setCheckEmployee(WebUtil.getCurrentEmployee());
		updatePaperOutStockCountAndCost(paperOutStock);
		update(paperOutStock);
	}

	/*
	 * 冲销出库单生成冲销入库单
	 * 
	 * @see
	 * org.kyerp.service.warehouse.IPaperOutStockService#congXiao(java.lang.Long)
	 */
	@Override
	public void congXiao(Long paperOutStockId) throws Exception {
		PaperOutStock paperOutStock = find(paperOutStockId);
		if (null != paperOutStock) {
			if (BillStatus.CONGXIAO.equals(paperOutStock.getStatus())) {
				throw new Exception("出库单(Id=" + paperOutStockId + ")已经冲销，不能再次冲销！");
			} else {
				InStock inStock = new InStock();
				// 设置收发类型为 "冲销入库"
				inStock.setInOutType(inOutTypeService.find(11L));
				inStock.setWriteDate(new Date());
				inStock.setWriteEmployee(WebUtil.getCurrentEmployee());
				inStock.setKeeper(WebUtil.getCurrentEmployee());
				inStock.setRemark("" + paperOutStock.getSerialNumber() + "的冲销单");
				inStock.setStatus(BillStatus.WAITING_FOR_CHECK);
				// 设置供应商为 "不详"
				inStock.setSupplier(supplierService.find(26L));
				inStockService.save(inStock);
				List<InStockDetail> inStockDetails = new ArrayList<InStockDetail>();
				for (PaperOutStockDetail paperOutStockDetail : paperOutStock.getDetails()) {
					InStockDetail inStockDetail = new InStockDetail();
					inStockDetail.setMaterial(paperOutStockDetail.getMaterial());
					inStockDetail.setBatchNumber(paperOutStockDetail
							.getBatchNumber());
					inStockDetail.setInStockCount(paperOutStockDetail
							.getOutStockCount());
					inStockDetail.setWarehouse(paperOutStockDetail.getWarehouse());
					inStockDetail.setPrice(paperOutStockDetail.getPrice());
					inStockDetail.setUnit(paperOutStockDetail.getUnit());
					inStockDetail.setInStockCount(paperOutStockDetail
							.getOutStockCount());
					inStockDetail.setInStock(inStock);
					inStockDetailService.save(inStockDetail);
					inStockDetails.add(inStockDetail);
				}
				inStock.setDetails(inStockDetails);
				inStockService.update(inStock);

				paperOutStock.setStatus(BillStatus.CONGXIAO);
				paperOutStock.setRemark("已冲销!单号：" + inStock.getSerialNumber());
				update(paperOutStock);
			}
		} else {
			throw new Exception("出库单(Id=" + paperOutStockId + ")没有找到！");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kyerp.service.warehouse.IPaperOutStockService#nextSerialNumber()
	 */
	@Override
	public String nextSerialNumber() throws Exception {
		int serno = 1;
		String head = "CK" + new SimpleDateFormat("yyyy").format(new Date());
		StringBuffer wherejpql = new StringBuffer("");
		List<Object> queryParams = new ArrayList<Object>();
		wherejpql.append(" o.serialNumber like ?").append(
				(queryParams.size() + 1));
		queryParams.add(head + "%");
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("serialNumber", "desc");
		if (getScrollData(wherejpql.toString(), queryParams.toArray(), orderby)
				.getTotalrecord() > 0) {
			PaperOutStock paperOutStock = getScrollData(wherejpql.toString(),
					queryParams.toArray(), orderby).getResultlist().get(0);
			if (paperOutStock != null) {
				String code = paperOutStock.getSerialNumber();
				serno = Integer.parseInt(code.substring(head.length())) + 1;
			}
		}
		return head + new DecimalFormat("0000").format(serno);
	}
	
	@Override
	public void updatePaperOutStockCountAndCost(PaperOutStock paperOutStock) throws Exception {
		paperOutStock.setBillCount(BigDecimal.ZERO);
		paperOutStock.setBillCost(BigDecimal.ZERO);
		for (PaperOutStockDetail detail : paperOutStock.getDetails()) {
			paperOutStock.setBillCount(paperOutStock.getBillCount().add(
					detail.getOutStockCount()));
			paperOutStock.setBillCost(paperOutStock.getBillCost().add(detail.getCost()));
		}
		// 删除数量为零 入库记录
		List<PaperOutStockDetail> paperOutStockDetails = paperOutStock.getDetails();
		Iterator<PaperOutStockDetail> iterator = paperOutStockDetails.iterator();
		while (iterator.hasNext()) {
			PaperOutStockDetail paperOutStockDetail = iterator.next();
			if (0 == BigDecimal.ZERO.compareTo(paperOutStockDetail.getOutStockCount())) {
				iterator.remove();
				if (null != paperOutStockDetail.getId()) {
					logger.debug("删除数量为零的库存批次" + paperOutStockDetail.toString());
					inStockDetailService.delete(paperOutStockDetail.getId());
				}
			}
		}
		paperOutStock.setDetails(paperOutStockDetails);
	}

	@Override
	public void update(PaperOutStock paperOutStock) throws Exception {
		updatePaperOutStockCountAndCost(paperOutStock);
		super.update(paperOutStock);
	}
}
