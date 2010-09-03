package org.kyerp.service.warehouse;

import org.kyerp.dao.DAO;
import org.kyerp.domain.warehouse.Brand;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:25:26
 */
@Service
public interface IBrandService extends DAO<Brand> {
	/**
	 * 保存
	 * @param supplier
	 */
	public void save(Brand brand) throws Exception;
	/**
	 * 修改
	 * @param supplier
	 */
	public void update(Brand brand) throws Exception;
	/**
	 * 检查重复
	 * @param paper
	 */
	public int getCount(Brand brand) throws Exception;
}
