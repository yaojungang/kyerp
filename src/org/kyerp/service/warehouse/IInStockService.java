package org.kyerp.service.warehouse;

import org.kyerp.dao.DAO;
import org.kyerp.domain.warehouse.InStock;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:25:26
 */
@Service
public interface IInStockService extends DAO<InStock>{
	/**
	 * 保存入库单 如果没有填写单号则设置单号
	 * 
	 * @throws Exception
	 */
	void saveInStock(InStock inStock) throws Exception;

	/**
	 * 审核入库单 改变状态为已审核,设置审核人，审核时间
	 * 
	 * @return String
	 * @throws Exception
	 */
	String checkInStock(Long inStockId) throws Exception;

	/**
	 * 冲销入库单生成冲销出库单
	 * 
	 * @return String
	 * @throws Exception
	 */
	String congXiao(Long inStockId) throws Exception;

	/**
	 * 下一个单号
	 * */
	String nextSerialNumber() throws Exception;


	/**
	 * 计算入库单数量、金额、单价
	 * */
	void updateInStockCountAndCost(InStock inStock) throws Exception;

}
