package org.kyerp.service.warehouse.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.warehouse.Brand;
import org.kyerp.service.warehouse.IBrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BrandService extends DaoSupport<Brand> implements IBrandService {

	@Override
	public void save(Brand brand) throws Exception {
		if (getCount(brand)>0) {
			throw new Exception("保存时发生错误！已有名为："+brand.getName()+"的品牌");
		}else {
			super.save(brand);
		}
	}

	@Override
	public int getCount(Brand brand) throws Exception {
		StringBuffer wherejpql = new StringBuffer("");
		List<Object> queryParams = new ArrayList<Object>();
		wherejpql.append(" 1=?").append((queryParams.size() + 1));
		queryParams.add(1);
		
		// 名称
		if(null != brand.getName()) {
			wherejpql.append(" and o.name like ?").append(queryParams.size() + 1);
			queryParams.add(brand.getName().trim());

		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<Brand> queryResult = this.getScrollData(wherejpql.toString(), queryParams.toArray(), null);
		logger.debug("jpql:"+wherejpql.toString());
		logger.debug("params:"+queryParams.toString());
		logger.debug("total:"+queryResult.getTotalrecord());
		return (int)queryResult.getTotalrecord();
	}

	@Override
	public void update(Brand brand) throws Exception {
		if (getCount(brand)>1) {
			throw new Exception("更新时发生错误！已有名为: "+brand.getName()+" 的品牌");
		}else {
			super.update(brand);
		}
	}

}
