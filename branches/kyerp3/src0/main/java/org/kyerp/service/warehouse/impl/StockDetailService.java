package org.kyerp.service.warehouse.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.InventoryDetail;
import org.kyerp.domain.warehouse.Stock;
import org.kyerp.domain.warehouse.StockDetail;
import org.kyerp.service.warehouse.IStockDetailService;
import org.kyerp.service.warehouse.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StockDetailService extends DaoSupport<StockDetail> implements
		IStockDetailService {
	@Autowired
	IStockService stockService;

	@Override
	public StockDetail find(Long ownerId, Long materailId,
			String materialBatchNumber, Long warehouseId, BigDecimal price) {
		String wherejpql = "o.stock.owner.id = " + ownerId
				+ " and o.stock.material.id = " + materailId
				+ " and o.batchNumber like \'" + materialBatchNumber
				+ "\' and o.warehouse.id = " + warehouseId;
		logger.debug("查找物料,jpql="+wherejpql);
		List<StockDetail> stockDetails = getScrollData(wherejpql, null, null)
				.getResultlist();
		if (stockDetails.size() > 0) {
			if (null != price && 0!= BigDecimal.ZERO.compareTo(price)) {
				logger.debug("单价不为空，不为零，比较单价"+price.toString());
				for (int i = 0; i < stockDetails.size(); i++) {
					StockDetail stockDetail = stockDetails.get(i);
					if (0 == stockDetail.getPrice().compareTo(price)) {
						logger.debug("找到了单价相同的物料jpql="+wherejpql);
						return stockDetails.get(i);
					}
				}
				logger.debug("没有找到同样单价的物料jpql="+wherejpql);
				return null;
			} else {
				logger.debug("单价为空，或为零，不比较单价");
				logger.debug("找到了物料jpql="+wherejpql);
				return stockDetails.get(0);
			}

		} else {
			logger.debug("没有找到物料jpql="+wherejpql);
			return null;
		}
	}
	@Override
	public StockDetail dealWithInventoryDetail(StockDetail stockDetail,InventoryDetail inventoryDetail)
			throws Exception {
		inventoryDetail.caculateStockDetail(stockDetail, inventoryDetail);		
		stockDetail.setPrice(stockDetail.getPrice());
		stockDetail.setCost(stockDetail.getAmount().multiply(
				stockDetail.getPrice()));
		update(stockDetail);
		return stockDetail;
	}
/*
	@Override
	public StockDetail in(StockDetail stockDetail, InStockDetail inStockDetail)
			throws Exception {
		logger.debug("入库：" + stockDetail.getBatchNumber() + "数量"
				+ stockDetail.getAmount() + " + "
				+ inStockDetail.getInStockCount());
		stockDetail.setAmount(stockDetail.getAmount().add(
				inStockDetail.getInStockCount()));
		stockDetail.setPrice(stockDetail.getPrice());
		stockDetail.setCost(stockDetail.getAmount().multiply(
				stockDetail.getPrice()));
		update(stockDetail);
		return stockDetail;
	}

	@Override
	public StockDetail out(StockDetail stockDetail,
			OutStockDetail outStockDetail) throws Exception {
		logger.debug("出库：批次号：" + stockDetail.getBatchNumber() + "  数量 = "
				+ stockDetail.getAmount() + " - "
				+ outStockDetail.getOutStockCount());
		stockDetail.setAmount(stockDetail.getAmount().subtract(
				outStockDetail.getOutStockCount()));
		stockDetail.setCost(stockDetail.getAmount().multiply(
				stockDetail.getPrice()));
		update(stockDetail);
		return stockDetail;
	}
*/
	@Override
	public StockDetail addStockDetailfromInventoryDetail(Stock stock,
			InventoryDetail inventoryDetail) throws Exception {
		StockDetail stockDetail = createStockDetailfromInventoryDetail(inventoryDetail);
		stockDetail.setStock(stock);
		List<StockDetail> stockDetails = stock.getStockDetails();
		stockDetails.add(stockDetail);
		stock.setStockDetails(stockDetails);
		return stockDetail;
	}

	@Override
	public void deleteStockDetail(StockDetail stockDetail) throws Exception {
		Stock stock = stockDetail.getStock();
		List<StockDetail> stockDetails = stock.getStockDetails();
		Iterator<StockDetail> iterator = stockDetails.iterator();
		while (iterator.hasNext()) {
			if (0 == iterator.next().getId().compareTo(stockDetail.getId())) {
				iterator.remove();
			}
		}
		delete(stockDetail.getId());
		stock.setStockDetails(stockDetails);
		stockService.updateAmountPriceAndCost(stock);
		stockService.update(stock);
	}

	@Override
	public StockDetail createStockDetailfromInventoryDetail(
			InventoryDetail inventoryDetail) throws Exception {
		StockDetail stockDetail = new StockDetail();
		stockDetail.setUnit(inventoryDetail.getMaterial().getUnit());
		stockDetail.setBatchNumber(inventoryDetail.getBatchNumber());
		stockDetail.setWarehouse(inventoryDetail.getWarehouse());
		stockDetail.setAmount(inventoryDetail.getInStockCount().subtract(
				inventoryDetail.getOutStockCount()));
		stockDetail.setPrice(inventoryDetail.getPrice());
		stockDetail.setCost(inventoryDetail.getCost());
		// save(stockDetail);
		// 直接由stock保存了，所以这里不用保存stockdetail
		return stockDetail;
	}
}
