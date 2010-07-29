package org.kyerp.service.warehouse.impl;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.OutStockDetail;
import org.kyerp.service.warehouse.IOutStockDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OutStockDetailService extends DaoSupport<OutStockDetail> implements
		IOutStockDetailService {

}
