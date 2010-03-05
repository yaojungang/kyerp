package org.kyerp.service.warehouse.impl;

import java.text.ParseException;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.PurchaseOrder;
import org.kyerp.service.warehouse.IPurchaseOrderService;
import org.kyerp.utils.SerialNumberHelper;
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
		save(e);
	}

}
