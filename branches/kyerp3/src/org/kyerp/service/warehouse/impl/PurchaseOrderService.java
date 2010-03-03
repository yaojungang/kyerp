package org.kyerp.service.warehouse.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.BillStatus;
import org.kyerp.domain.warehouse.PurchaseOrder;
import org.kyerp.domain.warehouse.PurchaseOrderDetail;
import org.kyerp.service.warehouse.IPurchaseOrderService;
import org.kyerp.utils.SerialNumberHelper;
import org.kyerp.web.controller.BaseController;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
public class PurchaseOrderService extends DaoSupport<PurchaseOrder> implements
		IPurchaseOrderService {

	@Override
	public void savePurchaseOrder(PurchaseOrder e) {
		if (null == e.getSerialNumber() || e.getSerialNumber().length() == 0) {
			// 如果没有填写单号则设置单号
			if (null == e.getSerialNumber()
					|| e.getSerialNumber().length() == 0) {
				String jpql = "select count(o) from PurchaseOrder o where o.createTime>=?1";
				try {
					e.setSerialNumber(SerialNumberHelper.buildSerialNumber(em,
							jpql));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		}
		// 设置填单时间
		e.setWriteDate(new Date());
		// 设置单据状态
		e.setStatus(BillStatus.WRITING);
		// 保存填单人
		e.setWriteUser(BaseController.getCurrentUser());
		// billCount
		if (null == e.getBillCount()) {
			e.setBillCount(0);
		}
		// billCost
		if (null == e.getBillCost()) {
			e.setBillCost(new BigDecimal("0"));
		}
		save(e);
		updatePurchaseOrder(e);
	}

	@Override
	public void updatePurchaseOrder(PurchaseOrder purchaseOrder) {

		purchaseOrder.setBillCount(0);
		purchaseOrder.setBillCost(new BigDecimal("0"));
		for (PurchaseOrderDetail detail : purchaseOrder.getDetails()) {
			purchaseOrder.setBillCount(purchaseOrder.getBillCount()
					+ detail.getBillCount());
			purchaseOrder.setBillCost(purchaseOrder.getBillCost().add(
					detail.getBillCost()));
		}
		System.out.println("update bill! count:" + purchaseOrder.getBillCount()
				+ "cost:" + purchaseOrder.getBillCost());
		update(purchaseOrder);
	}

}
