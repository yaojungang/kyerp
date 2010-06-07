package org.kyerp.service.warehouse.impl;

import java.math.BigDecimal;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.Stock;
import org.kyerp.domain.warehouse.StockDetail;
import org.kyerp.service.warehouse.IStockService;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
public class StockService extends DaoSupport<Stock> implements IStockService{
	public void updateStock(Stock stock) {
		updateAmountPriceAndCost(stock);
		update(stock);
	}

	public void updateAmountPriceAndCost(Stock stock) {
		if(null != stock.getStockDetails() && stock.getStockDetails().size() > 0) {
			stock.setTotalAmount(new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP));
			stock.setCost(new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP));
			for (StockDetail detail : stock.getStockDetails()) {
				logger.debug("库存数量增加:" + detail.getAmount());
				stock.setTotalAmount(stock.getTotalAmount().add(detail.getAmount()));
				stock.setCost(stock.getCost().add(detail.getCost()));
			}
			logger.debug("更新库存数量为:" + stock.getTotalAmount());
		}

	};

	public void updateAmountPriceAndCost(Long id) {
		Stock stock = find(id);
		if(null != stock.getStockDetails() && stock.getStockDetails().size() > 0) {
			stock.setTotalAmount(new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP));
			stock.setCost(new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP));
			for (StockDetail detail : stock.getStockDetails()) {
				logger.debug("库存数量增加:" + detail.getAmount());
				stock.setTotalAmount(stock.getTotalAmount().add(detail.getAmount()));
				stock.setCost(stock.getCost().add(detail.getCost()));
			}
			logger.debug("更新库存数量为:" + stock.getTotalAmount());
			update(stock);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kyerp.service.warehouse.IStockService#resetStockAmount(java.lang.Long)
	 */
	@Override
	public void resetStockAmount(Long id) throws Exception {
		Stock stock = find(id);
		BigDecimal tCount = new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
		if(null != stock) {
			for (StockDetail detail : stock.getStockDetails()) {
				logger.debug("add:" + detail.getAmount());
				tCount = tCount.add(detail.getAmount());
				logger.debug("tCount:" + tCount.toString());
			}
			stock.setTotalAmount(tCount);
			logger.debug("stock.setTotalAmount:" + tCount.toString());
			update(stock);
		} else {
			throw new Exception("没有找到相应的库存记录！");
		}

	};
}
