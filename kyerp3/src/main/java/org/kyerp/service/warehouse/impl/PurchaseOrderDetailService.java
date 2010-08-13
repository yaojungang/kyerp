package org.kyerp.service.warehouse.impl;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.PurchaseOrderDetail;
import org.kyerp.service.warehouse.IPurchaseOrderDetailService;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
public class PurchaseOrderDetailService extends DaoSupport<PurchaseOrderDetail>
		implements IPurchaseOrderDetailService {
}
