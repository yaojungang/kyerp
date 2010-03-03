package org.kyerp.service.warehouse.impl;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.PurchaseOrderDetail;
import org.kyerp.service.warehouse.IPurchaseOrderDetailService;
import org.kyerp.service.warehouse.IPurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
public class PurchaseOrderDetailService extends DaoSupport<PurchaseOrderDetail>
		implements IPurchaseOrderDetailService {
	@Autowired
	IPurchaseOrderService	purchaseOrderService;

	/*
	 * 保存并更新申请单的数量和金额
	 * 
	 * @seeorg.kyerp.service.warehouse.IPurchaseOrderDetailService#
	 * savePurchaseOrderDetail(org.kyerp.domain.warehouse.PurchaseOrderDetail)
	 */
	@Override
	public void savePurchaseOrderDetail(PurchaseOrderDetail detail) {
		super.save(detail);
		purchaseOrderService.updatePurchaseOrder(purchaseOrderService
				.find(detail.getPurchaseOrder().getId()));
	}

	@Override
	public void updatePurchaseOrderDetail(PurchaseOrderDetail detail) {
		super.update(detail);
		purchaseOrderService.updatePurchaseOrder(detail.getPurchaseOrder());
	}

}
