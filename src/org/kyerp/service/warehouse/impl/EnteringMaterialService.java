package org.kyerp.service.warehouse.impl;

import java.text.ParseException;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.EnteringMaterial;
import org.kyerp.service.warehouse.IEnteringMaterialService;
import org.kyerp.utils.SerialNumberHelper;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
public class EnteringMaterialService extends DaoSupport<EnteringMaterial>
		implements IEnteringMaterialService {

	public void saveEnteringMaterial(EnteringMaterial e) {
		// 如果没有填写单号则设置单号
		if (null == e.getSerialNumber() || e.getSerialNumber().length() == 0) {
			String jpql = "select count(o) from EnteringMaterial o where o.createTime>=?1";
			try {
				e.setSerialNumber(SerialNumberHelper
						.buildSerialNumber(em, jpql));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		super.save(e);
	}

}
