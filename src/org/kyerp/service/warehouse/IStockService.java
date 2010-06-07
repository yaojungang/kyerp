package org.kyerp.service.warehouse;

import org.kyerp.dao.DAO;
import org.kyerp.domain.warehouse.Stock;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:25:26
 */
@Service
public interface IStockService extends DAO<Stock>{
	void updateStock(Stock stock);

	void updateAmountPriceAndCost(Long id);

	/**
	 * 重新计算库存数量
	 * 
	 * @throws Exception
	 */
	void resetStockAmount(Long id) throws Exception;
}
