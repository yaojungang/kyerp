package org.kyerp.service.warehouse.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.BillStatus;
import org.kyerp.domain.warehouse.InStock;
import org.kyerp.domain.warehouse.InStockDetail;
import org.kyerp.domain.warehouse.OutStock;
import org.kyerp.domain.warehouse.OutStockDetail;
import org.kyerp.domain.warehouse.Stock;
import org.kyerp.domain.warehouse.StockDetail;
import org.kyerp.service.warehouse.IInOutTypeService;
import org.kyerp.service.warehouse.IInStockDetailService;
import org.kyerp.service.warehouse.IInStockService;
import org.kyerp.service.warehouse.IOutStockService;
import org.kyerp.service.warehouse.IStockDetailService;
import org.kyerp.service.warehouse.IStockService;
import org.kyerp.service.warehouse.ISupplierService;
import org.kyerp.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
public class OutStockService extends DaoSupport<OutStock> implements IOutStockService{
	@Autowired
	IStockService			stockService;
	@Autowired
	IStockDetailService		stockDetailService;
	@Autowired
	IInOutTypeService		inOutTypeService;
	@Autowired
	IInStockDetailService	inStockDetailService;
	@Autowired
	IInStockService			inStockService;
	@Autowired
	ISupplierService		supplierService;

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void saveOutStock(OutStock e) {
		if(null == e.getSerialNumber() || e.getSerialNumber().length() == 0) {
			// 如果没有填写单号则设置单号
			try {
				e.setSerialNumber(nextSerialNumber());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		save(e);
	}

	/*
	 * 审核入库单 改变状态为已审核,设置审核人，审核时间
	 * 
	 * @see org.kyerp.service.warehouse.IOutStockService#checkOutStock(
	 * org.kyerp.domain.warehouse.OutStock)
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class, Throwable.class })
	public String checkOutStock(OutStock outStock) throws Exception {
		if(BillStatus.CHECKED == outStock.getStatus()) {
			return "该单据已经审核过，不能再审核。";
		}
		// 循环取出入库单明细里的每个条目；
		for (OutStockDetail outStockDetail : outStock.getDetails()) {
			if(outStock.getDetails().size() == 0) {
				continue;
			}
			// 查询库存主表如果存在该Id的物料则选中这条库存记录
			String wherejpql = "o.material.id=" + outStockDetail.getMaterial().getId();
			// System.out.println("jpql:" + wherejpql);
			Stock stock = new Stock();
			if(stockService.getScrollData(wherejpql, null, null).getTotalrecord() > 0) {
				stock = stockService.getScrollData(wherejpql, null, null).getResultlist().get(0);
				// 查询库存明细表中是否存在 同批次号、同库房的库存记录，如果存在则选中
				String wherejpql2 = "o.batchNumber='" + outStockDetail.getBatchNumber() + "' and o.warehouse.id=" + outStockDetail.getWarehouse().getId();
				System.out.println("jpql:" + wherejpql2);
				StockDetail stockDetail = new StockDetail();
				if(stockDetailService.getScrollData(wherejpql2, null, null).getTotalrecord() > 0) {
					stockDetail = stockDetailService.getScrollData(wherejpql2, null, null).getResultlist().get(0);

					// 如果出库数量大于库存数量时如何处理
// if(outStockDetail.getBillCount().compareTo(stockDetail.getAmount()) == 1) {
// String materialName = outStockDetail.getMaterial().getName();
// return "库存数量不足：" + materialName + "[" + outStockDetail.getBatchNumber() + "]只有" + stockDetail.getAmount() + "(" + stockDetail.getUnit().getName() + ")";
// }
					// 更新这个批次并且存放在这个库房的物料的数量
					stockDetail.setAmount(stockDetail.getAmount().subtract(outStockDetail.getOutStockCount()));

					// 如果库存总数为0则删除这条库存记录，否则更新库存金额和绝对平均价格
					if(BigDecimal.ZERO.compareTo(stockDetail.getAmount()) == 0) {
						stockDetailService.delete(stockDetail.getId());
						logger.debug("Delete stockDetail");
					} else {
						logger.debug("stockDetail.getCost():" + stockDetail.getCost());
						logger.debug("outStockDetail.getBillCost():" + outStockDetail.getBillCost());

						stockDetail.setCost(stockDetail.getCost().subtract(outStockDetail.getBillCost()));
						stockDetail.setPrice(stockDetail.getCost().divide(stockDetail.getAmount(), 4, BigDecimal.ROUND_HALF_UP));
						stockDetailService.update(stockDetail);
					}
					// 更新库存表的总数量
					stock.setTotalAmount(stock.getTotalAmount().subtract(outStockDetail.getOutStockCount()));
					// 如果库存总数为0则删除这条库存记录，否则更新库存金额和绝对平均价格
					if(BigDecimal.ZERO.compareTo(stock.getTotalAmount()) == 0) {
						stockService.delete(stock.getId());
					} else {
						stock.setCost(stock.getCost().subtract(outStockDetail.getBillCost()));
						stock.setPrice(stock.getCost().divide(stock.getTotalAmount(), 4, BigDecimal.ROUND_HALF_UP));
						stockService.update(stock);
					}
				} else {
					return "库存中没有找到这个批次的物料，请核对后重试！";
				}
			} else {
				return "库存中没有找到这个物料，请核对，后重试！";
			}
		}
		// 设置单据状态
		outStock.setStatus(BillStatus.CHECKED);
		// 设置审核日期
		outStock.setCheckDate(new Date());
		// 设置审核人
		outStock.setCheckUser(WebUtil.getCurrentUser());
		outStock.setCheckEmployee(WebUtil.getCurrentEmployee());
		update(outStock);
		return "success";
	}

	/*
	 * 冲销出库单生成冲销入库单
	 * 
	 * @see org.kyerp.service.warehouse.IOutStockService#congXiao(java.lang.Long)
	 */
	@Override
	public String congXiao(Long outStockId) throws Exception {
		OutStock outStock = find(outStockId);
		if(null != outStock) {
			if(BillStatus.CONGXIAO.equals(outStock.getStatus())) {
				return "已经冲销，不能再次冲销！";
			} else {
				InStock inStock = new InStock();
				// 设置收发类型为 "冲销入库"
				inStock.setInOutType(inOutTypeService.find(11L));
				inStock.setWriteDate(new Date());
				inStock.setWriteEmployee(WebUtil.getCurrentEmployee());
				inStock.setKeeper(WebUtil.getCurrentEmployee());
				inStock.setRemark("出库单：" + outStock.getSerialNumber() + "的冲销单");
				inStock.setStatus(BillStatus.WAITING_FOR_CHECK);
				// 设置供应商为 "不详"
				inStock.setSupplier(supplierService.find(26L));

				inStockService.saveInStock(inStock);
				List<InStockDetail> inStockDetails = new ArrayList<InStockDetail>();
				for (OutStockDetail outStockDetail : outStock.getDetails()) {
					InStockDetail inStockDetail = new InStockDetail();
					inStockDetail.setMaterial(outStockDetail.getMaterial());
					inStockDetail.setBatchNumber(outStockDetail.getBatchNumber());
					inStockDetail.setInStockCount(outStockDetail.getOutStockCount());
					inStockDetail.setWarehouse(outStockDetail.getWarehouse());
					inStockDetail.setPrice(outStockDetail.getPrice());
					inStockDetail.setUnit(outStockDetail.getUnit());
					inStockDetail.setInStockCount(outStockDetail.getOutStockCount());
					inStockDetail.setInStock(inStock);
					inStockDetailService.save(inStockDetail);
					inStockDetails.add(inStockDetail);
				}
				inStock.setDetails(inStockDetails);
				inStockService.update(inStock);

				outStock.setStatus(BillStatus.CONGXIAO);
				outStock.setRemark("已经冲销!入库单号：" + inStock.getSerialNumber());
				update(outStock);
				return "success";
			}
		} else {
			return "没有找到相应的出库单！";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kyerp.service.warehouse.IOutStockService#nextSerialNumber()
	 */
	@Override
	public String nextSerialNumber() throws Exception {
		int serno = 1;
		String head = "CK" + new SimpleDateFormat("yyyy").format(new Date());
		StringBuffer wherejpql = new StringBuffer("");
		List<Object> queryParams = new ArrayList<Object>();
		wherejpql.append(" o.serialNumber like ?").append((queryParams.size() + 1));
		queryParams.add(head + "%");
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("serialNumber", "desc");
		if(getScrollData(wherejpql.toString(), queryParams.toArray(), orderby).getTotalrecord() > 0) {
			OutStock outStock = getScrollData(wherejpql.toString(), queryParams.toArray(), orderby).getResultlist().get(0);
			if(outStock != null) {
				String code = outStock.getSerialNumber();
				serno = Integer.parseInt(code.substring(head.length())) + 1;
			}
		}
		return head + new DecimalFormat("0000").format(serno);
	}
}
