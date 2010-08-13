package org.kyerp.service.warehouse;

import java.math.BigDecimal;

import org.kyerp.dao.DAO;
import org.kyerp.domain.warehouse.InventoryDetail;
import org.kyerp.domain.warehouse.Stock;
import org.kyerp.domain.warehouse.StockDetail;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:25:26
 */
@Service
public interface IStockDetailService extends DAO<StockDetail> {
	/**
	 * 查找StockDetail
	 * @param materailId
	 * @param materialBatchNumber
	 * @param warehouseId
	 * @return
	 */
	StockDetail find(Long ownerId,Long materailId, String materialBatchNumber, Long warehouseId,BigDecimal price);
	/**
	 * 处理入库，出库单
	 * @param stockDetail
	 * @param inventoryDetail
	 * @return
	 * @throws Exception
	 */
	StockDetail dealWithInventoryDetail(StockDetail stockDetail,InventoryDetail inventoryDetail)throws Exception;
	/**
	 * 入库
	 * @param stockDetail
	 * @throws Exception
	 */
	//StockDetail in(StockDetail stockDetail ,InStockDetail inStockDetail) throws Exception;
	/**
	 * 出库
	 * @param stockDetail
	 * @throws Exception
	 */
	//StockDetail out(StockDetail stockDetail ,OutStockDetail outStockDetail) throws Exception;
	/**
	 * 把入库单/出库单中的项目，转换为库存项目
	 * @param inStockDetail
	 */
	StockDetail createStockDetailfromInventoryDetail(InventoryDetail inventoryDetail) throws Exception;
	/**
	 * 删除库存项目
	 * @param stockDetail
	 */
	void deleteStockDetail(StockDetail stockDetail) throws Exception;
	/**
	 * 新入库，stock中没有这个ID，批次,库房的记录，新入库一个
	 * 新出库，stock中没有这个ID，批次,库房的记录，新出库一个
	 * @param stock
	 * @param inStockDetail
	 * @throws Exception
	 */
	StockDetail addStockDetailfromInventoryDetail(Stock stock,InventoryDetail inventoryDetail) throws Exception;
}
