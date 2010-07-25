package org.kyerp.service.warehouse;

import org.kyerp.dao.DAO;
import org.kyerp.domain.warehouse.InStockDetail;
import org.kyerp.domain.warehouse.OutStockDetail;
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
	StockDetail findStockDetailByMaterialIdAndMaterialBatchNumberAndWarehouseId(
			Long materailId, String materialBatchNumber, Long warehouseId);
	/**
	 * 入库
	 * @param stockDetail
	 * @throws Exception
	 */
	void in(InStockDetail inStockDetail) throws Exception;
	/**
	 * 出库
	 * @param stockDetail
	 * @throws Exception
	 */
	void out(OutStockDetail outStockDetail) throws Exception;
	/**
	 * 把入库单中的入库项目，转换为库存项目
	 * @param inStockDetail
	 */
	void addStockDetailfromInstockDetail(InStockDetail inStockDetail) throws Exception;
}
