package org.kyerp.service.warehouse;

import org.kyerp.dao.DAO;
import org.kyerp.domain.warehouse.InStockDetail;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:25:26
 */
@Service
public interface IInStockDetailService extends DAO<InStockDetail>{
	public void saveInStockDetail(InStockDetail e);
}
