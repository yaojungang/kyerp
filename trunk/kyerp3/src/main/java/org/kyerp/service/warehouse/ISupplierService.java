/**
 * 
 */
package org.kyerp.service.warehouse;

import org.kyerp.dao.DAO;
import org.kyerp.domain.warehouse.Supplier;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:25:26
 */
@Service
public interface ISupplierService extends DAO<Supplier> {
	/**
	 * 保存
	 * @param supplier
	 */
	public void save(Supplier supplier) throws Exception;
	/**
	 * 修改时检查重复
	 * @param supplier
	 * @throws Exception
	 */
	public void update(Supplier supplier) throws Exception;
	/**
	 * 检查重复
	 * @param paper
	 */
	public int getCount(Supplier supplier);
}
