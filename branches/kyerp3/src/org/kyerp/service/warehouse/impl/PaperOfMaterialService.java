/**
 * 
 */
package org.kyerp.service.warehouse.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.TemporalType;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.print.PaperOfMaterial;
import org.kyerp.service.warehouse.IPaperOfMaterialService;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午01:46:55
 */
@Service
public class PaperOfMaterialService extends DaoSupport<PaperOfMaterial> implements IPaperOfMaterialService{

	/*
	 * @see org.kyerp.service.warehouse.IPaperOfMaterialService#savePaper(org.kyerp.domain.warehouse.PaperOfMaterial)
	 */
	@Override
	public void savePaper(PaperOfMaterial paper) {
		paper.setSerialNumber(buildSerialNumber(new Date()));
		super.save(paper);

	}

	/**
	 * 生成序列号,序列号的组成 两位年+两位月+两位日+两位小时+当天的入库单总数+1 如:09 05 14 11 100
	 */
	private String buildSerialNumber(Date createDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH");
		StringBuilder out = new StringBuilder(dateFormat.format(createDate));
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strdate = dateFormat.format(createDate);// 2009-05-14
		Date date = createDate;
		try {
			date = dateFormat.parse(strdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long count = (Long) em.createQuery("select count(o) from Material o where o.createTime>=?1").setParameter(1, date, TemporalType.TIMESTAMP).getSingleResult();
		out.append(count + 1);
		return out.toString();
	}
}
