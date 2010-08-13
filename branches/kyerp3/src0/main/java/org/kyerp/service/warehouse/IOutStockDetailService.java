package org.kyerp.service.warehouse;

import org.kyerp.dao.DAO;
import org.kyerp.domain.warehouse.OutStockDetail;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:25:26
 */
@Service
public interface IOutStockDetailService extends DAO<OutStockDetail> {
	/**
	 * 处理结余和补纸的问题
	 * @param outStockDetail
	 * @throws Exception
	 */
	public void dealwithMoreOrLess(OutStockDetail outStockDetail) throws Exception;
}
