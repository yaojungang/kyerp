package org.kyerp.service.warehouse.impl;

import java.text.ParseException;
import java.util.Date;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.BillStatus;
import org.kyerp.domain.warehouse.InStock;
import org.kyerp.service.warehouse.IInStockService;
import org.kyerp.utils.SerialNumberHelper;
import org.kyerp.utils.WebUtil;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
public class InStockService extends DaoSupport<InStock> implements
		IInStockService {
	@Override
	public void saveInStock(InStock e) {
		if (null == e.getSerialNumber() || e.getSerialNumber().length() == 0) {
			// 如果没有填写单号则设置单号
			if (null == e.getSerialNumber()
					|| e.getSerialNumber().length() == 0) {
				String jpql = "select count(o) from InStock o where o.createTime>=?1";
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

	/*
	 * 审核入库单 改变状态为已审核,设置审核人，审核时间
	 * 
	 * @see org.kyerp.service.warehouse.IInStockService#checkInStock(
	 * org.kyerp.domain.warehouse.InStock)
	 */
	@Override
	public String checkInStock(InStock inStock) {
		if (BillStatus.CHECKED == inStock.getStatus()) {
			return "该单据已经审核过，不能再审核。";
		}
		// 设置单据状态
		inStock.setStatus(BillStatus.CHECKED);
		// 设置审核日期
		inStock.setCheckDate(new Date());
		// 设置审核人
		inStock.setCheckUser(WebUtil.getCurrentUser());
		update(inStock);
		return "success";
	}
}
