package org.kyerp.service.warehouse.impl;

import java.util.Date;

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
		e.setEnteringTime(new Date());
		String jpql = "select count(o) from EnteringMaterial o where o.createTime>=?1";
		e.setSerialNumber(SerialNumberHelper.buildSerialNumber(em, jpql));
		super.save(e);
	}

}
