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
	 * @param material
	 */
	void saveMaterial(Material material);

}
