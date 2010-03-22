package org.kyerp.service.warehouse.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.BillStatus;
import org.kyerp.domain.warehouse.InStock;
import org.kyerp.domain.warehouse.InStockDetail;
import org.kyerp.domain.warehouse.Stock;
import org.kyerp.domain.warehouse.StockDetail;
import org.kyerp.service.warehouse.IInStockService;
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
public class InStockService extends DaoSupport<InStock> implements
		IInStockService {
	@Autowired
	IStockService		stockService;
	@Autowired
	IStockDetailService	stockDetailService;

	@Override
	public void saveInStock(InStock e) {
		if (null == e.getSerialNumber() || e.getSerialNumber().length() == 0) {
			// 如果没有填写单号则设置单号
			String jpql = "select count(o) from InStock o where o.createTime>=?1";
			try {
				e.setSerialNumber(SerialNumberHelper
						.buildSerialNumber(em, jpql));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		save(e);
	}

	/*
	 * 审核入库单 改变状态为已审核,设置审核人，审核时间
	 * 
	 * @see org.kyerp.service.warehouse.IInStockService#checkInStock(
	 * org.kyerp.domain.warehouse.InStock)
	 */
	@Override
	public String checkInStock(InStock inStock) {
		if (BillStatus.CHECKED == inStock.getStatus()) {
			return "该单据已经审核过，不能再审核。";
		}
		// 循环取出入库单明细里的每个条目；
		for (InStockDetail d : inStock.getDetails()) {
			if (inStock.getDetails().size() == 0) {
				continue;
			}
			// 查询库存主表如果存在该Id的物料则选中这条库存记录
			String wherejpql = "o.material.id=" + d.getMaterial().getId();
			// System.out.println("jpql:" + wherejpql);
			Stock stock = new Stock();
			if (stockService.getScrollData(wherejpql, null, null)
					.getTotalrecord() > 0) {
				stock = stockService.getScrollData(wherejpql, null, null)
						.getResultlist().get(0);
			}
			if (null != stock.getId()) {
				// 查询库存明细表中是否存在 同批次号、同库房的库存记录，如果存在则选中
				String wherejpql2 = "o.batchNumber=" + d.getBatchNumber()
						+ " and o.warehouse.id=" + d.getWarehouse().getId();
				// System.out.println("jpql:" + wherejpql);
				StockDetail stockDetail = new StockDetail();
				if (stockDetailService.getScrollData(wherejpql2, null, null)
						.getTotalrecord() > 0) {
					stockDetail = stockDetailService.getScrollData(wherejpql2,
							null, null).getResultlist().get(0);
					// 更新这个批次并且存放在这个库房的物料的数量
					stockDetail.setAmount(stockDetail.getAmount().add(
							d.getBillCount()));
					// 如果库存总数为0则删除这条库存记录，否则更新库存金额和绝对平均价格
					BigDecimal zero4 = new BigDecimal("0.0000");
					if (zero4.equals(stockDetail.getAmount().setScale(4))) {
						stockDetailService.delete(stockDetail.getId());
					} else {
						stockDetail.setCost(stockDetail.getCost().add(
								d.getBillCost()));
						stockDetail.setPrice(stockDetail.getCost().divide(
								stockDetail.getAmount()));
					}

					stockDetailService.update(stockDetail);
				} else {
					stockDetail.setStock(stock);
					// 如果没有设置批次号，则设置批次号
					if (null == d.getBatchNumber()
							|| d.getBatchNumber().length() == 0) {
						// 如果没有填写单号则设置单号
						String jpql = "select count(o) from StockDetail o where o.stock.id="
								+ stock.getId() + " and o.createTime>=?1";
						try {
							stockDetail.setBatchNumber(SerialNumberHelper
									.buildSerialNumber(em, jpql));
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					} else {
						stockDetail.setBatchNumber(d.getBatchNumber());
					}

					stockDetail.setWarehouse(d.getWarehouse());
					stockDetail.setAmount(d.getBillCount());
					stockDetail.setUnit(d.getUnit());
					stockDetail.setPrice(d.getPrice());
					stockDetail.setCost(d.getBillCost());
					stockDetailService.save(stockDetail);
				}

				// 更新库存表的总数量
				stock.setTotalAmount(stock.getTotalAmount().add(
						d.getBillCount()));
				// 如果库存总数为0则删除这条库存记录，否则更新库存金额和绝对平均价格
				BigDecimal zero4 = new BigDecimal("0.0000");
				if (zero4.equals(stock.getTotalAmount().setScale(4))) {
					stockService.delete(stock.getId());
				} else {
					stock.setCost(stock.getCost().add(d.getBillCost()));
					stock.setPrice(stock.getCost().divide(
							stock.getTotalAmount()));
					stockService.update(stock);
				}
			} else {
				// stock = new Stock();
				stock.setMaterial(d.getMaterial());
				// 库存编号与物料编号一致
				stock.setSerialNumber(d.getMaterial().getSerialNumber());
				stock.setUnit(d.getUnit());
				stock.setTotalAmount(d.getBillCount());
				stock.setPrice(d.getPrice());
				stock.setCost(d.getBillCost());
				stockService.save(stock);

				StockDetail stockDetail = new StockDetail();
				stockDetail.setStock(stock);
				// 如果没有设置批次号，则设置批次号
				if (null == d.getBatchNumber()
						|| d.getBatchNumber().length() == 0) {
					// 如果没有填写单号则设置单号
					String jpql = "select count(o) from StockDetail o where o.stock.id="
							+ stock.getId() + " and o.createTime>=?1";
					try {
						stockDetail.setBatchNumber(SerialNumberHelper
								.buildSerialNumber(em, jpql));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				} else {
					stockDetail.setBatchNumber(d.getBatchNumber());
				}
				stockDetail.setWarehouse(d.getWarehouse());
				stockDetail.setAmount(d.getBillCount());
				stockDetail.setUnit(d.getUnit());
				stockDetail.setPrice(d.getPrice());
				stockDetail.setCost(d.getBillCost());
				stockDetailService.save(stockDetail);
			}

		}
		// 设置单据状态
		inStock.setStatus(BillStatus.CHECKED);
		// 设置审核日期
		inStock.setCheckDate(new Date());
		// 设置审核人
		inStock.setCheckUser(WebUtil.getCurrentUser());
		update(inStock);
		return "success";
	}
}
