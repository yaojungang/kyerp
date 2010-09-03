/**
 * 
 */
package org.kyerp.service.warehouse.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.warehouse.Material;
import org.kyerp.service.warehouse.IMaterialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MaterialService extends DaoSupport<Material> implements
		IMaterialService {

	public void save(Material material) throws Exception {
		if (getCount(material)>0) {
			throw new Exception("库中已有名为："+material.getFullName()+"的物料");
		};
		material.setSerialNumber(buildSerialNumber(new Date()));
		super.save(material);
	}
	public void update(Material material) throws Exception {
		if (getCount(material)>1) {
			throw new Exception("库中已有名为："+material.getFullName()+"的物料");
		};
		super.update(material);
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
		long count = (Long) em.createQuery(
				"select count(o) from Material o where o.createTime>=?1")
				.setParameter(1, date, TemporalType.TIMESTAMP)
				.getSingleResult();
		out.append(count + 1);
		return out.toString();
	}

	@Override
	public int getCount(Material material) {
		StringBuffer wherejpql = new StringBuffer("");
		List<Object> queryParams = new ArrayList<Object>();
		wherejpql.append(" 1=?").append((queryParams.size() + 1));
		queryParams.add(1);
		
		// 名称
		if(null != material.getName()) {
			wherejpql.append(" and o.name like ?").append(queryParams.size() + 1);
			queryParams.add(material.getName().trim());

		}
		// 供应商
		if(null != material.getSpecification()) {
			wherejpql.append(" and o.specification like ?").append(queryParams.size() + 1);
			queryParams.add(material.getSpecification().trim());
			
		}
		// 品牌
		if(null != material.getBrand()) {
			wherejpql.append(" and o.brand.name like ?").append(queryParams.size() + 1);
			queryParams.add(material.getBrand().getName().trim());
			
		}
		QueryResult<Material> queryResult = this.getScrollData(wherejpql.toString(), queryParams.toArray(), null);
		logger.debug("jpql:"+wherejpql.toString());
		logger.debug("params:"+queryParams.toString());
		logger.debug("total:"+queryResult.getTotalrecord());
		return (int)queryResult.getTotalrecord();
	}

}
