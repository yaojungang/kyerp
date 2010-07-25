package org.kyerp.service.warehouse.impl;

import java.util.List;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.InStockDetail;
import org.kyerp.domain.warehouse.OutStockDetail;
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
public class StockDetailService extends DaoSupport<StockDetail> implements
		IStockDetailService {
	@Autowired
	IStockService stockService;

	@Override
	public StockDetail findStockDetailByMaterialIdAndMaterialBatchNumberAndWarehouseId(
			Long materailId, String materialBatchNumber, Long warehouseId) {
		String wherejpql = "o.stock.material.id = " + materailId
				+ " and o.batchNumber like \'" + materialBatchNumber
				+ "\' and o.warehouse.id = " + warehouseId;
		logger.debug(wherejpql);
		List<StockDetail> stockDetails = getScrollData(wherejpql, null, null)
				.getResultlist();
		if (stockDetails.size() > 0) {
			return stockDetails.get(0);
		} else {
			return null;
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void in(InStockDetail inStockDetail) throws Exception {
		StockDetail stockDetail = findStockDetailByMaterialIdAndMaterialBatchNumberAndWarehouseId(
				inStockDetail.getMaterial().getId(),
				inStockDetail.getBatchNumber(), inStockDetail.getWarehouse()
						.getId());
		logger.debug("before update stockdetail:" + stockDetail.toString());
		stockDetail.setAmount(stockDetail.getAmount().add(
				inStockDetail.getInStockCount()));
		stockDetail.setPrice(stockDetail.getPrice());
		stockDetail.setCost(stockDetail.getAmount().multiply(stockDetail.getPrice()));
		logger.debug("after update stockdetail:" + stockDetail.toString());
		save(stockDetail);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void out(OutStockDetail outStockDetail) throws Exception {
		StockDetail stockDetail = findStockDetailByMaterialIdAndMaterialBatchNumberAndWarehouseId(
				outStockDetail.getMaterial().getId(),
				outStockDetail.getBatchNumber(), outStockDetail.getWarehouse()
						.getId());
		logger.debug("before update stockdetail:" + stockDetail.toString());
		stockDetail.setAmount(stockDetail.getAmount().subtract(
				outStockDetail.getOutStockCount()));
		logger.debug("after update stockdetail:" + stockDetail.toString());
		save(stockDetail);
	}

	@Override
	public void addStockDetailfromInstockDetail(InStockDetail inStockDetail) throws Exception {
		Stock stock = null;
		if (null != stockService.findStockByMaterialId(inStockDetail
				.getMaterial().getId()) && stockService.findStockByMaterialId(inStockDetail
						.getMaterial().getId()).getId() >0) {
			stock = stockService.findStockByMaterialId(inStockDetail
					.getMaterial().getId());
		} else {
			stock = new Stock();
		}
		StockDetail stockDetail = new StockDetail();
		stockDetail.setUnit(inStockDetail.getMaterial().getUnit());
		stockDetail.setBatchNumber(inStockDetail.getBatchNumber());
		stockDetail.setWarehouse(inStockDetail.getWarehouse());
		stockDetail.setAmount(inStockDetail.getInStockCount());
		stockDetail.setPrice(inStockDetail.getPrice());
		stockDetail.setCost(inStockDetail.getCost());
		stockDetail.setStock(stock);
		List<StockDetail> stockDetails = stock.getStockDetails();
		stockDetails.add(stockDetail);
		stock.setStockDetails(stockDetails);
		if (null != stock.getId() && stock.getId() > 0) {
			stockService.updateAmountPriceAndCost(stock);
			stockService.update(stock);
		} else {
			stock.setMaterial(inStockDetail.getMaterial());
			stock.setUnit(inStockDetail.getMaterial().getUnit());
			//stock.setTotalAmount(inStockDetail.getInStockCount());
			stockService.updateAmountPriceAndCost(stock);
			stockService.save(stock);
		}
	}

}
