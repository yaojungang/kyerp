package org.kyerp.service.warehouse;

import org.kyerp.dao.DAO;
import org.kyerp.domain.warehouse.InStockDetail;
import org.kyerp.domain.warehouse.InventoryDetail;
import org.kyerp.domain.warehouse.OutStockDetail;
import org.kyerp.domain.warehouse.Stock;
import org.kyerp.domain.warehouse.StockDetail;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:25:26
 */
@Service
public interface IStockService extends DAO<Stock> {
	/**
	 * 根据materialId查询Stock
	 * @param materailId
	 * @return
	 */
	Stock findStockByMaterialId(Long materailId);
	/**
	 * 判断是否存在同类物料（同ID，批次，库房的物料）
	 * @param inStockDetail
	 * @return
	 */
	boolean isContainedSameMaterial(InventoryDetail inventoryDetail);
	/**
	 * 入库
	 * 
	 * @param stockDetail
	 */
	void inStock(InStockDetail inStockDetail) throws Exception;

	/**
	 * 出库
	 * 
	 * @param stockDetail
	 */
	void outStock(OutStockDetail outStockDetail) throws Exception;
	/**
	 * 删除库存项目
	 * @param stockDetail
	 */
	void deleteStockDetail(StockDetail stockDetail) throws Exception;
	/**
	 * 更新Stock的数量，金额和价格
	 * @param stockId
	 */
	void updateAmountPriceAndCost(Long stockId) throws Exception;
	/**
	 * 更新Stock的数量，金额和价格
	 * @param stock
	 */
	void updateAmountPriceAndCost(Stock stock) throws Exception;
}
