/**
 * 
 */
package org.kyerp.service.warehouse.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.MaterialCategory;
import org.kyerp.service.warehouse.IMaterialCategoryService;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
public class MaterialCategoryService extends DaoSupport<MaterialCategory> implements IMaterialCategoryService{
	public List<MaterialCategory> getParentMaterialCategories(Long id) {
		MaterialCategory mc = this.find(id);
		MaterialCategory mcParent = (MaterialCategory) ((MaterialCategory) mc).getParentCategory();
		List<MaterialCategory> mcs = new ArrayList<MaterialCategory>();
		mcs.add(mc);
		while (mcParent != null) {
			mcs.add(mcParent);
			mcParent = (MaterialCategory) mcParent.getParentCategory();
		}
		return mcs;
	}

	/*
	 * 
	 * 
	 * @seeorg.kyerp.service.warehouse.IMaterialCategoryService#
	 * getMaterialCategoriesByParentId(java.lang.Long)
	 */
	@Override
	public List<MaterialCategory> getMaterialCategoriesByParentId(Long parentId) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("serialNumber", "asc");
		orderby.put("createTime", "asc");
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		if(parentId != null && parentId > 0) {
			jpql.append(" and o.parentMaterialCategory.id=?" + (params.size() + 1));
			params.add(parentId);
		} else {
			jpql.append(" and o.parentMaterialCategory is null");
		}
		List<MaterialCategory> materialCategories = getScrollData(jpql.toString(), params.toArray(), orderby).getResultlist();
		return materialCategories;
	}
}
