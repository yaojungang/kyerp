/**
 * 
 */
package org.kyerp.service.warehouse;

import java.util.List;

import org.kyerp.dao.DAO;
import org.kyerp.domain.warehouse.MaterialCategory;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:25:26
 */
@Service
public interface IMaterialCategoryService extends DAO<MaterialCategory> {
	/** 返回指定类的所有父类 */
	public List<MaterialCategory> getParentMaterialCategories(Long id);

}
