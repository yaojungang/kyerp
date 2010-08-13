package org.kyerp.service.warehouse;

import org.kyerp.dao.DAO;
import org.kyerp.domain.warehouse.InventoryDetail;
import org.kyerp.domain.warehouse.Stock;
import org.kyerp.domain.warehouse.StockDetail;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:25:26
 */
@Service
public interface IStockService extends DAO<Stock> {
	/**
	 * 根据materialId和ownerId查询Stock
	 * @param materailId
	 * @param ownerId
	 * @return
	 */
	Stock findStockByMaterialIdAndOwnerId(Long materailId,Long ownerId);
	/**
	 * 处理入库单，出库单
	 * @param inventoryDetail
	 * @return
	 * @throws Exception
	 */
	StockDetail dealWithInventoryDetail(InventoryDetail inventoryDetail) throws Exception;
	/**
	 * 入库
	 * 
	 * @param stockDetail
	 */
	//StockDetail inStock(InStockDetail inStockDetail) throws Exception;

	/**
	 * 出库
	 * 
	 * @param stockDetail
	 */
	//StockDetail outStock(OutStockDetail outStockDetail) throws Exception;
	/**
	 * 更新Stock的数量，金额和价格，删除数量为零的批次，并保存stock
	 * @param stockId
	 */
	void updateAmountPriceAndCost(Long stockId) throws Exception;
	/**
	 * 更新Stock的数量，金额和价格，删除数量为零的批次，不保存Stock
	 * 返回 总数量与0的比值
		0:库存量为零
		1:库存量为正数
		-1:库存量为负数数
	 * @param stock
	 */
	int updateAmountPriceAndCost(Stock stock) throws Exception;
	/**
	 * 根据入库/出库项目，创建一个新的库存记录，并保存stock
	 * @return
	 */
	Stock add(InventoryDetail inventoryDetail) throws Exception ;
	/**
	 * 删除余额为零的记录
	 * @throws Exception
	 */
	void removeZeroStock(Stock stock) throws Exception;
}
