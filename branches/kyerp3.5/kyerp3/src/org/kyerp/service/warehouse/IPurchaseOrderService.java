package org.kyerp.service.warehouse;

import org.kyerp.dao.DAO;
import org.kyerp.domain.warehouse.PurchaseOrder;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:25:26
 */
@Service
public interface IPurchaseOrderService extends DAO<PurchaseOrder>{
	/**
	 * 保存采购申请单 如果没有填写单号则设置单号
	 */
	void savePurchaseOrder(PurchaseOrder purchaseOrder);

	/**
	 * 审核采购申请单 改变状态为已审核,设置审核人，审核时间
	 * 
	 * @return String
	 * @throws Exception
	 */
	String checkPurchaseOrder(PurchaseOrder purchaseOrder) throws Exception;
}
