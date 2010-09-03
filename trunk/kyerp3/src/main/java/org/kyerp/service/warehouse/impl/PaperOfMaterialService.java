/**
 * 
 */
package org.kyerp.service.warehouse.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.TemporalType;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.warehouse.print.PaperOfMaterial;
import org.kyerp.service.warehouse.IPaperOfMaterialService;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午01:46:55
 */
@Service
public class PaperOfMaterialService extends DaoSupport<PaperOfMaterial> implements IPaperOfMaterialService{
	@Override
	public void save(PaperOfMaterial paper) throws Exception {
		paper.setMaterialName();
		paper.setSpecification();
		paper.setFullName();
		if (getCount(paper)>0) {
			throw new Exception("库中已有名为："+paper.getFullName()+"的纸张");
		};
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

	@Override
	public int getCount(PaperOfMaterial paper) {
		StringBuffer wherejpql = new StringBuffer("");
		List<Object> queryParams = new ArrayList<Object>();
		wherejpql.append(" 1=?").append((queryParams.size() + 1));
		queryParams.add(1);
		// 克重
		if(paper.getPaperWeight()> 0) {
			if(queryParams.size() > 0) {
				wherejpql.append(" and ");
			}
			wherejpql.append(" o.paperWeight=?").append((queryParams.size() + 1));
			queryParams.add(paper.getPaperWeight());
		}
		// 纸长
		if(paper.getPaperHeight()> 0) {
			if(queryParams.size() > 0) {
				wherejpql.append(" and ");
			}
			wherejpql.append(" o.paperHeight=?").append((queryParams.size() + 1));
			queryParams.add(paper.getPaperHeight());
		}
		// 纸宽
		if(paper.getPaperWidth()> 0) {
			if(queryParams.size() > 0) {
				wherejpql.append(" and ");
			}
			wherejpql.append(" o.paperWidth=?").append((queryParams.size() + 1));
			queryParams.add(paper.getPaperWidth());
		}
		// 名称
		if(null != paper.getPaperName()) {
			wherejpql.append(" and o.paperName like ?").append(queryParams.size() + 1);
			queryParams.add("%" + paper.getPaperName().trim() + "%");

		}
		// 供应商
		if(null != paper.getSupplier()) {
			wherejpql.append(" and o.supplier.name like ?").append(queryParams.size() + 1);
			queryParams.add("%" + paper.getSupplier().getName().trim() + "%");
			
		}
		// 品牌
		if(null != paper.getBrand()) {
			wherejpql.append(" and o.brand.name like ?").append(queryParams.size() + 1);
			queryParams.add("%" + paper.getBrand().getName().trim() + "%");
			
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<PaperOfMaterial> queryResult = this.getScrollData(wherejpql.toString(), queryParams.toArray(), null);
		logger.debug("jpql:"+wherejpql.toString());
		logger.debug("params:"+queryParams.toString());

		return (int) queryResult.getTotalrecord();
	}

	@Override
	public void update(PaperOfMaterial paper) throws Exception {
		paper.setMaterialName();
		paper.setSpecification();
		paper.setFullName();
		if (getCount(paper)>1) {
			throw new Exception("库中已有名为："+paper.getFullName()+"的纸张");
		};
		super.update(paper);
		
	}
}
