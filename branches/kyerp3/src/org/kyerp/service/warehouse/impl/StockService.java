package org.kyerp.service.warehouse.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.InStockDetail;
import org.kyerp.domain.warehouse.InventoryDetail;
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
public class StockService extends DaoSupport<Stock> implements IStockService {
	@Autowired
	IStockDetailService stockDetailService;

	public Stock findStockByMaterialId(Long materailId) {
		String wherejpql = "o.material.id = " + materailId;
		logger.debug(wherejpql);
		List<Stock> stocks = getScrollData(wherejpql, null, null)
				.getResultlist();
		if (stocks.size() > 0) {
			return stocks.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 判断是否存在同类物料（同ID，批次，库房的物料）
	 * 
	 * @param inStockDetail
	 * @param queryParams
	 * @return
	 */
	@Override
	public boolean isContainedSameMaterial(InventoryDetail inventoryDetail) {
		return null != stockDetailService
				.findStockDetailByMaterialIdAndMaterialBatchNumberAndWarehouseId(
						inventoryDetail.getMaterial().getId(), inventoryDetail
								.getBatchNumber(), inventoryDetail
								.getWarehouse().getId());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void inStock(InStockDetail inStockDetail) throws Exception {
		Stock stock = null;
		if (null != findStockByMaterialId(inStockDetail.getMaterial().getId()) && findStockByMaterialId(inStockDetail
				.getMaterial().getId()).getId() >0) {
			stock = findStockByMaterialId(inStockDetail.getMaterial().getId());
		} else {
			stock = new Stock();
		}
		stock.setTotalAmount(stock.getTotalAmount().add(
				inStockDetail.getInStockCount()));
		if (isContainedSameMaterial(inStockDetail)) {
			stockDetailService.in(inStockDetail);
		} else {
			stockDetailService.addStockDetailfromInstockDetail(inStockDetail);
		}
		if (0 == BigDecimal.ZERO.compareTo(stock.getTotalAmount())) {
			logger.debug("delete:" + stock.toString());
			delete(stock.getId());
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void outStock(OutStockDetail outStockDetail) throws Exception {
		if (isContainedSameMaterial(outStockDetail)) {
			Stock stock = findStockByMaterialId(outStockDetail.getMaterial()
					.getId());
			stock.setTotalAmount(stock.getTotalAmount().subtract(
					outStockDetail.getOutStockCount()));

			if (0 == BigDecimal.ZERO.compareTo(stock.getTotalAmount())) {
				logger.debug("delete:" + stock.toString());
				delete(stock.getId());
			} else {
				StockDetail stockDetail = stockDetailService
						.findStockDetailByMaterialIdAndMaterialBatchNumberAndWarehouseId(
								outStockDetail.getMaterial().getId(),
								outStockDetail.getBatchNumber(), outStockDetail
										.getWarehouse().getId());
				if (0 == stockDetail.getAmount().compareTo(
						outStockDetail.getOutStockCount())) {
					logger.debug("库存量 = 出库量: 正好出完，删除这条记录");
					deleteStockDetail(stockDetail);
					logger.debug("保存 Stock" + stock.toString());
				} else {
					logger.debug("正常出库");
					stockDetailService.out(outStockDetail);
				}
				updateAmountPriceAndCost(stock);
				save(stock);
			}
		} else {
			throw new Exception("没有找到相应的库存记录,请核对批次号");
		}
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
		stockDetailService.delete(stockDetail.getId());
		stock.setStockDetails(stockDetails);
		updateAmountPriceAndCost(stock);
		update(stock);
	}

	public void updateAmountPriceAndCost(Long stockId) throws Exception {
		Stock stock = find(stockId);
		if (null != stock && stock.getId()>0) {
			updateAmountPriceAndCost(stock);
			update(stock);
		}else {
			throw new Exception("没有找到Id为："+stockId+" 的库存记录");
		}
		
	}

	public void updateAmountPriceAndCost(Stock stock) throws Exception {
		if (null != stock.getStockDetails()
				&& stock.getStockDetails().size() > 0) {
			stock.setTotalAmount(new BigDecimal("0.0000").setScale(4,
					BigDecimal.ROUND_HALF_UP));
			stock.setCost(new BigDecimal("0.0000").setScale(4,
					BigDecimal.ROUND_HALF_UP));
			//更新数量和总金额
			for (StockDetail detail : stock.getStockDetails()) {
				stock.setTotalAmount(stock.getTotalAmount().add(
						detail.getAmount()));
				stock.setCost(stock.getCost().add(detail.getCost()));
			}
			//更新价格（平均价格）
			if (1 == stock.getCost().compareTo(BigDecimal.ZERO) && 1 == stock.getTotalAmount().compareTo(BigDecimal.ZERO)) {
				stock.setPrice(stock.getCost().divide(stock.getTotalAmount()));
			}else {
				stock.setPrice(BigDecimal.ZERO);
			}
			//删除数量为零 的批次的库存记录
			List<StockDetail> stockDetails = stock.getStockDetails();
			Iterator<StockDetail> iterator = stockDetails.iterator();
			while (iterator.hasNext()) {
				StockDetail stockDetail = iterator.next();
				if (0 == BigDecimal.ZERO.compareTo(stockDetail.getAmount())) {
					iterator.remove();
					stockDetailService.delete(stockDetail.getId());
				}
			}
			stock.setStockDetails(stockDetails);
		}else {
			throw new Exception("库存记录没有具体的批次记录，不能更新");
		}

	}
}