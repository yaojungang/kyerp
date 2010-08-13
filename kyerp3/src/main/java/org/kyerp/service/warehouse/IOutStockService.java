package org.kyerp.service.warehouse;

import org.kyerp.dao.DAO;
import org.kyerp.domain.warehouse.OutStock;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:25:26
 */
@Service
public interface IOutStockService extends DAO<OutStock>{

	/**
	 * 保存出库单,如果没有填写单号则设置单号
	 */
	void save(OutStock outStock) throws Exception;
	/**
	 * 更新出库单,更新出库单的数量和总金额
	 */
	void update(OutStock outStock) throws Exception;

	/**
	 * 审核出库单 改变状态为已审核,设置审核人，审核时间
	 * 
	 * @return String
	 * @throws Exception
	 */
	void checkOutStock(Long outStockId) throws Exception;

	/**
	 * 冲销出库单生成冲销入库单
	 * 
	 * @return String
	 * @throws Exception
	 */
	void congXiao(Long outStockId) throws Exception;

	/**
	 * 下一个单号
	 * */
	String nextSerialNumber() throws Exception;
	/**
	 * 更新出库单的数量和总金额
	 * @param outStock
	 * @throws Exception
	 */
	void updateOutStockCountAndCost(OutStock outStock) throws Exception;
}
