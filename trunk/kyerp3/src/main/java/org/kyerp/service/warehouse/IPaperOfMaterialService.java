/**
 * 
 */
package org.kyerp.service.warehouse;

import org.kyerp.dao.DAO;
import org.kyerp.domain.warehouse.print.PaperOfMaterial;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-27下午11:55:19
 */
@Service
public interface IPaperOfMaterialService extends DAO<PaperOfMaterial>{
	/**
	 * 保存
	 * @param paper
	 */
	public void save(PaperOfMaterial paper) throws Exception;
	/**
	 * 修改
	 * @param paper
	 * @throws Exception
	 */
	public void update(PaperOfMaterial paper) throws Exception;
	/**
	 * 检查库中已有数量，防止重复
	 * @param paper
	 */
	public int getCount(PaperOfMaterial paper);
}
