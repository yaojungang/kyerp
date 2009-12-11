/**
 * 
 */
package org.kyerp.service.warehouse.impl;

import java.util.ArrayList;
import java.util.List;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.MaterialCategory;
import org.kyerp.service.warehouse.IMaterialCategoryService;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
public class MaterialCategoryService extends DaoSupport<MaterialCategory>
		implements IMaterialCategoryService {
	public List<MaterialCategory> getParentMaterialCategories(Long id) {
		MaterialCategory mc = this.find(id);
		MaterialCategory mcParent = mc.getParentMaterialCategory();
		List<MaterialCategory> mcs = new ArrayList<MaterialCategory>();
		mcs.add(mc);
		while (mcParent != null) {
			mcs.add(mcParent);
			mcParent = mcParent.getParentMaterialCategory();
		}
		System.out.println("getParentMaterialCategories:" + mcs.size());
		return mcs;
	}
}
