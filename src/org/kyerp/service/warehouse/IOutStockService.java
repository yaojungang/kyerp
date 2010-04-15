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
	 * 保存出库单 如果没有填写单号则设置单号
	 */
	void saveOutStock(OutStock outStock);

	/**
	 * 审核出库单 改变状态为已审核,设置审核人，审核时间
	 * 
	 * @return String
	 * @throws Exception
	 */
	String checkOutStock(OutStock outStock) throws Exception;

}
