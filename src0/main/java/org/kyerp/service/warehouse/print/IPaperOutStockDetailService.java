package org.kyerp.service.warehouse.print;

import org.kyerp.dao.DAO;
import org.kyerp.domain.warehouse.print.PaperOutStockDetail;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:25:26
 */
@Service
public interface IPaperOutStockDetailService extends DAO<PaperOutStockDetail> {
	/**
	 * 处理结余和补纸的问题
	 * @param paperOutStockDetail
	 * @throws Exception
	 */
	public void dealwithMoreOrLess(PaperOutStockDetail paperOutStockDetail) throws Exception;
}
