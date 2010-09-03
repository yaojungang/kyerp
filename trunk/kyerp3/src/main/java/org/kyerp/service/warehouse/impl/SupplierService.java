/**
 * 
 */
package org.kyerp.service.warehouse.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.warehouse.Supplier;
import org.kyerp.service.warehouse.ISupplierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SupplierService extends DaoSupport<Supplier> implements
		ISupplierService {

	@Override
	public void save(Supplier supplier) throws Exception {
		if (getCount(supplier)>0) {
			throw new Exception("已有名为："+supplier.getName()+"的供应商");
		};
		super.save(supplier);
		
	}

	@Override
	public int getCount(Supplier supplier) {
		StringBuffer wherejpql = new StringBuffer("");
		List<Object> queryParams = new ArrayList<Object>();
		wherejpql.append(" 1=?").append((queryParams.size() + 1));
		queryParams.add(1);
		
		// 名称
		if(null != supplier.getName()) {
			wherejpql.append(" and o.name like ?").append(queryParams.size() + 1);
			queryParams.add(supplier.getName().trim());

		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<Supplier> queryResult = this.getScrollData(wherejpql.toString(), queryParams.toArray(), null);
		logger.debug("jpql:"+wherejpql.toString());
		logger.debug("params:"+queryParams.toString());
		logger.debug("total:"+queryResult.getTotalrecord());
		return (int) queryResult.getTotalrecord();
	}

	@Override
	public void update(Supplier supplier) throws Exception {
		if (getCount(supplier)>1) {
			throw new Exception("已有名为："+supplier.getName()+"的供应商");
		};
		super.update(supplier);
		
	}

}
