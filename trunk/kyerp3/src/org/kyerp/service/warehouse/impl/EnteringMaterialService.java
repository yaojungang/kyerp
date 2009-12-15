package org.kyerp.service.warehouse.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.TemporalType;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.EnteringMaterial;
import org.kyerp.service.warehouse.IEnteringMaterialService;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
public class EnteringMaterialService extends DaoSupport<EnteringMaterial>
		implements IEnteringMaterialService {

	/**
	 * 生成入库单号,入库单号的组成 两位年+两位月+两位日+两位小时+当天的入库单总数+1 如:09 05 14 11 100
	 */
	private String buildSerialNumber(Date createDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHH");
		StringBuilder out = new StringBuilder(dateFormat.format(createDate));
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strdate = dateFormat.format(createDate);// 2009-05-14
		Date date = createDate;
		try {
			date = dateFormat.parse(strdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long count = (Long) em
				.createQuery(
						"select count(o) from EnteringMaterial o where o.createTime>=?1")
				.setParameter(1, date, TemporalType.TIMESTAMP)
				.getSingleResult();
		out.append(count + 1);
		return out.toString();
	}

}
