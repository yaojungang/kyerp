package org.kyerp.service.warehouse;

import org.kyerp.dao.DAO;
import org.kyerp.domain.warehouse.print.PaperOutStock;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:25:26
 */
@Service
public interface IPaperOutStockService extends DAO<PaperOutStock>{

	/**
	 * 保存纸张出库单,如果没有填写单号则设置单号
	 */
	void save(PaperOutStock paperOutStock) throws Exception;
	/**
	 * 更新纸张出库单,更新纸张出库单的数量和总金额
	 */
	void update(PaperOutStock paperOutStock) throws Exception;

	/**
	 * 审核纸张出库单 改变状态为已审核,设置审核人，审核时间
	 * 
	 * @return String
	 * @throws Exception
	 */
	void checkPaperOutStock(Long paperOutStockId) throws Exception;

	/**
	 * 冲销纸张出库单生成冲销入库单
	 * 
	 * @return String
	 * @throws Exception
	 */
	void congXiao(Long paperOutStockId) throws Exception;

	/**
	 * 下一个单号
	 * */
	String nextSerialNumber() throws Exception;
	/**
	 * 更新纸张出库单的数量和总金额
	 * @param paperOutStock
	 * @throws Exception
	 */
	void updatePaperOutStockCountAndCost(PaperOutStock paperOutStock) throws Exception;
}
