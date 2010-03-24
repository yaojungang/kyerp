package org.kyerp.service.warehouse.impl;

import java.text.ParseException;
import java.util.Date;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.BillStatus;
import org.kyerp.domain.warehouse.PurchaseOrder;
import org.kyerp.service.warehouse.IPurchaseOrderService;
import org.kyerp.utils.SerialNumberHelper;
import org.kyerp.utils.WebUtil;
import org.springframework.stereotype.Service;

/**
 * 
 */
@Service
public class PurchaseOrderService extends DaoSupport<PurchaseOrder> implements IPurchaseOrderService{

	@Override
	public void savePurchaseOrder(PurchaseOrder e) {
		if(null == e.getSerialNumber() || e.getSerialNumber().length() == 0) {
			// 如果没有填写单号则设置单号
			if(null == e.getSerialNumber() || e.getSerialNumber().length() == 0) {
				String jpql = "select count(o) from PurchaseOrder o where o.createTime>=?1";
				try {
					e.setSerialNumber(SerialNumberHelper.buildSerialNumber(em, jpql));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		}
		save(e);
	}

	/*
	 * 审核采购申请单 改变状态为已审核,设置审核人，审核时间
	 * 
	 * @see
	 * org.kyerp.service.warehouse.IPurchaseOrderService#checkPurchaseOrder(
	 * org.kyerp.domain.warehouse.PurchaseOrder)
	 */
	@Override
	public String checkPurchaseOrder(PurchaseOrder purchaseOrder) {
		if(BillStatus.CHECKED == purchaseOrder.getStatus()) {
			return "该单据已经审核过，不能再审核。";
		}
		// 设置单据状态
		purchaseOrder.setStatus(BillStatus.CHECKED);
		// 设置审核日期
		purchaseOrder.setCheckDate(new Date());
		// 设置审核人
		purchaseOrder.setCheckUser(WebUtil.getCurrentUser());
		purchaseOrder.setCheckEmployee(WebUtil.getCurrentEmployee());
		update(purchaseOrder);
		return "success";
	}
}
