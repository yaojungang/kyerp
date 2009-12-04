package org.kyerp.service;

import java.util.List;

public interface IGenericService<T> {

	/** 修改对象 */
	public void save(T t) throws BusinessException;

	/** 删除对象 */
	public void remove(T t) throws BusinessException;

	/** 查找对象：按照对象ID */
	public T find(Class<T> clazz, Long id);

	/** 查找对象：按照JPQL */
	public List<T> find(String jpql);

	/** 查找对象：带参数查找 */
	public List<T> find(String jpql, Object param);

	/** 查找对象：带多个参数查找 */
	public List<T> find(String jpql, Object... param);

	/** 查找数据总数：参数如SELECT count(*)的JPQL */
	public int getTotalCount(String jpql);
}
