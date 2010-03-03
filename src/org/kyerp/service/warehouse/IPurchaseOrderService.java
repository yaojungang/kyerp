package org.kyerp.service.warehouse;

import org.kyerp.dao.DAO;
import org.kyerp.domain.warehouse.PurchaseOrder;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:25:26
 */
@Service
public interface IPurchaseOrderService extends DAO<PurchaseOrder> {
	void savePurchaseOrder(PurchaseOrder purchaseOrder);

	void updatePurchaseOrder(PurchaseOrder purchaseOrder);
}
