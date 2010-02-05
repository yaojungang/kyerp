package org.kyerp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;

/**
 * @author y109 2010-2-4下午10:13:19
 */
public class SerialNumberHelper {
	/**
	 * 生成序列号,序列号的组成 两位年+两位月+两位日+两位小时+当天的入库单总数+1 如:09 05 14 11 100
	 */
	public static String buildSerialNumber(EntityManager em, String jpql) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH");
		StringBuilder out = new StringBuilder(dateFormat.format(new Date()));
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strdate = dateFormat.format(new Date());// 2009-05-14
		Date date = new Date();
		try {
			date = dateFormat.parse(strdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// long count = (Long) em.createQuery(
		// "select count(o) from Material o where o.createTime>=?1").setParameter(1,
		// date, TemporalType.TIMESTAMP).getSingleResult();
		long count = (Long) em.createQuery(jpql).setParameter(1, date,
				TemporalType.TIMESTAMP).getSingleResult();
		out.append(count + 1);
		return out.toString();
	}

}
