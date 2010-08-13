/**
 * 
 */
package org.kyerp.service.warehouse.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.BaseCategory;
import org.kyerp.service.warehouse.IBaseCategoryService;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
public class BaseCategoryService extends DaoSupport<BaseCategory> implements IBaseCategoryService{
	public List<BaseCategory> getParentCategories(Long id) {
		BaseCategory mc = this.find(id);
		BaseCategory mcParent = (BaseCategory) ((BaseCategory) mc).getParentCategory();
		List<BaseCategory> mcs = new ArrayList<BaseCategory>();
		mcs.add(mc);
		while (mcParent != null) {
			mcs.add(mcParent);
			mcParent = (BaseCategory) mcParent.getParentCategory();
		}
		return mcs;
	}

	/*
	 * 
	 * 
	 * @seeorg.kyerp.service.warehouse.IBaseCategoryService#
	 * getMaterialCategoriesByParentId(java.lang.Long)
	 */
	@Override
	public List<BaseCategory> getCategoriesByParentId(Long parentId) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("serialNumber", "asc");
		orderby.put("createTime", "asc");
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		if(parentId != null && parentId > 0) {
			jpql.append(" and o.parentCategory.id=?" + (params.size() + 1));
			params.add(parentId);
		} else {
			jpql.append(" and o.parentCategory is null");
		}
		List<BaseCategory> materialCategories = getScrollData(jpql.toString(), params.toArray(), orderby).getResultlist();
		return materialCategories;
	}
}
