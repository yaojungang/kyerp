package org.kyerp.service.warehouse.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import javax.persistence.Transient;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.BillStatus;
import org.kyerp.domain.warehouse.OutStock;
import org.kyerp.domain.warehouse.OutStockDetail;
import org.kyerp.domain.warehouse.Stock;
import org.kyerp.domain.warehouse.StockDetail;
import org.kyerp.service.warehouse.IOutStockService;
import org.kyerp.service.warehouse.IStockDetailService;
import org.kyerp.service.warehouse.IStockService;
import org.kyerp.utils.SerialNumberHelper;
import org.kyerp.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
public class OutStockService extends DaoSupport<OutStock> implements IOutStockService{
	@Autowired
	IStockService		stockService;
	@Autowired
	IStockDetailService	stockDetailService;

	@Override
	public void saveOutStock(OutStock e) {
		if(null == e.getSerialNumber() || e.getSerialNumber().length() == 0) {
			// 如果没有填写单号则设置单号
			String jpql = "select count(o) from OutStock o where o.createTime>=?1";
			try {
				e.setSerialNumber(SerialNumberHelper.buildSerialNumber(em, jpql));
			} catch (ParseException e1) {
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
	@Transient
	public String checkOutStock(OutStock outStock) {
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
			System.out.println("jpql:" + wherejpql);
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
					if(outStockDetail.getBillCount().compareTo(stockDetail.getAmount()) == 1) {
						String materialName = outStockDetail.getMaterial().getName();
						return "库存数量不足：" + materialName + "[" + outStockDetail.getBatchNumber() + "]只有" + stockDetail.getAmount() + "(" + stockDetail.getUnit().getName() + ")";
					}
					// 更新这个批次并且存放在这个库房的物料的数量
					stockDetail.setAmount(stockDetail.getAmount().subtract(outStockDetail.getBillCount()));

					// 如果库存总数为0则删除这条库存记录，否则更新库存金额和绝对平均价格
					if(BigDecimal.ZERO.compareTo(stockDetail.getAmount()) == 0) {
						stockDetailService.delete(stockDetail.getId());
						System.out.println("Delete stockDetail");
					} else {
						System.out.println("stockDetail.getCost():" + stockDetail.getCost());
						System.out.println("outStockDetail.getBillCost():" + outStockDetail.getBillCost());

						stockDetail.setCost(stockDetail.getCost().subtract(outStockDetail.getBillCost()));
						stockDetail.setPrice(stockDetail.getCost().divide(stockDetail.getAmount(), 3, BigDecimal.ROUND_HALF_UP));
						stockDetailService.update(stockDetail);
					}
					// 更新库存表的总数量
					stock.setTotalAmount(stock.getTotalAmount().subtract(outStockDetail.getBillCount()));
					// 如果库存总数为0则删除这条库存记录，否则更新库存金额和绝对平均价格
					if(BigDecimal.ZERO.compareTo(stock.getTotalAmount()) == 0) {
						stockService.delete(stock.getId());
					} else {
						stock.setCost(stock.getCost().subtract(outStockDetail.getBillCost()));
						stock.setPrice(stock.getCost().divide(stock.getTotalAmount(), 3, BigDecimal.ROUND_HALF_UP));
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
		update(outStock);
		return "success";
	}
}
