package org.kyerp.service.warehouse;

import org.kyerp.dao.DAO;
import org.kyerp.domain.warehouse.Material;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:25:26
 */
@Service
public interface IMaterialService extends DAO<Material> {

	/**
	 * 保存
	 * @param material
	 */
	void save(Material material) throws Exception;
	/**
	 * 修改
	 * @param material
	 * @throws Exception
	 */
	void update(Material material) throws Exception;
	/**
	 * 检查唯一性，防止重复
	 * @param paper
	 */
	public int getCount(Material material);
}
