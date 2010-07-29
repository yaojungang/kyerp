package org.kyerp.service.warehouse.impl;

import java.util.Iterator;
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
@Transactional(rollbackFor = Exception.class)
public class StockDetailService extends DaoSupport<StockDetail> implements
		IStockDetailService {
	@Autowired
	IStockService stockService;

	@Override
	/**
	 * TODO 加上对 所有者的判断
	 */
	public StockDetail find(Long materailId, String materialBatchNumber, Long warehouseId) {
		String wherejpql = "o.stock.material.id = " + materailId
				+ " and o.batchNumber like \'" + materialBatchNumber
				+ "\' and o.warehouse.id = " + warehouseId;
		logger.debug(wherejpql);
		List<StockDetail> stockDetails = getScrollData(wherejpql, null, null)
				.getResultlist();
		if (stockDetails.size() > 0) {
			logger.debug("找到了o.stock.material.id = " + materailId
				+ " and o.batchNumber like \'" + materialBatchNumber
				+ "\' and o.warehouse.id = " + warehouseId+"的物料批次");
			return stockDetails.get(0);
		} else {
			logger.debug("没有找到了o.stock.material.id = " + materailId
					+ " and o.batchNumber like \'" + materialBatchNumber
					+ "\' and o.warehouse.id = " + warehouseId+"的物料批次");
			return null;
		}
	}

	@Override
	public StockDetail in(StockDetail stockDetail ,InStockDetail inStockDetail) throws Exception {
		logger.debug("入库：" + stockDetail.getBatchNumber() +"数量"+stockDetail.getAmount()+" + "+inStockDetail.getInStockCount());
		stockDetail.setAmount(stockDetail.getAmount().add(inStockDetail.getInStockCount()));
		stockDetail.setPrice(stockDetail.getPrice());
		stockDetail.setCost(stockDetail.getAmount().multiply(stockDetail.getPrice()));
		update(stockDetail);
		return stockDetail;
	}

	@Override
	public StockDetail out(StockDetail stockDetail ,OutStockDetail outStockDetail) throws Exception {
		logger.debug("出库：批次号：" + stockDetail.getBatchNumber() +"  数量 = "+stockDetail.getAmount()+" - "+ outStockDetail.getOutStockCount());
		stockDetail.setAmount(stockDetail.getAmount().subtract(
				outStockDetail.getOutStockCount()));
		stockDetail.setCost(stockDetail.getAmount().multiply(stockDetail.getPrice()));
		update(stockDetail);
		return stockDetail;
	}

	@Override
	public StockDetail addStockDetailfromInstockDetail(Stock stock,InStockDetail inStockDetail) throws Exception {
		StockDetail stockDetail = createStockDetailfromInstockDetail(inStockDetail);
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
	public StockDetail createStockDetailfromInstockDetail(InStockDetail inStockDetail)
			throws Exception {
		StockDetail stockDetail = new StockDetail();
		stockDetail.setUnit(inStockDetail.getMaterial().getUnit());
		stockDetail.setBatchNumber(inStockDetail.getBatchNumber());
		stockDetail.setWarehouse(inStockDetail.getWarehouse());
		stockDetail.setAmount(inStockDetail.getInStockCount());
		stockDetail.setPrice(inStockDetail.getPrice());
		stockDetail.setCost(inStockDetail.getCost());
		//save(stockDetail);
		//直接由stock保存了，所以这里不用保存stockdetail
		return stockDetail;
	}
}
