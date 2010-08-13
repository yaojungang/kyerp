package org.kyerp.service.warehouse.impl;

import java.text.ParseException;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.InStockDetail;
import org.kyerp.service.warehouse.IInStockDetailService;
import org.kyerp.utils.SerialNumberHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InStockDetailService extends DaoSupport<InStockDetail> implements IInStockDetailService{
	@Override
	public void save(InStockDetail e) {
		if(null == e.getBatchNumber() || e.getBatchNumber().length() == 0) {
			// 如果没有填写批次号则设置单号
			String jpql = "select count(o) from InStockDetail o where o.createTime>=?1";
			try {
				e.setBatchNumber(SerialNumberHelper.buildSerialNumber(em, jpql));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		super.save(e);
	}
}
