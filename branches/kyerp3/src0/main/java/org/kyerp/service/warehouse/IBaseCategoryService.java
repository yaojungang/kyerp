/**
 * 
 */
package org.kyerp.service.warehouse;

import java.util.List;

import org.kyerp.dao.DAO;
import org.kyerp.domain.warehouse.BaseCategory;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:25:26
 */
@Service
public interface IBaseCategoryService extends DAO<BaseCategory>{
	/** 返回指定类的所有父类 */
	public List<BaseCategory> getParentCategories(Long id);

	/** 返回指定Id父类下的所有子类 */
	public List<BaseCategory> getCategoriesByParentId(Long parentId);
}
