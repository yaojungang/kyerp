/**
 * 
 */
package org.kyerp.service.warehouse;

import org.kyerp.dao.DAO;
import org.kyerp.domain.warehouse.PaperOfMaterial;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-27下午11:55:19
 */
@Service
public interface IPaperOfMaterialService extends DAO<PaperOfMaterial>{
	/**
	 * @param paper
	 */
	public void savePaper(PaperOfMaterial paper);
}
