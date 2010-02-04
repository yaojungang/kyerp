package org.kyerp.dao;

import java.io.Serializable;
import java.util.LinkedHashMap;

import org.kyerp.domain.base.views.QueryResult;

public interface DAO<T> {
	/**
	 * 获取记录总数
	 * 
	 * @param entityClass
	 *            实体类
	 * @return count
	 */
	public long getCount();

	/**
	 * 清除一级缓存的数据
	 */
	public void clear();

	/**
	 * 保存实体
	 * 
	 * @param entity
	 *            实体id
	 */
	public void save(Object entity);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 *            实体id
	 */
	public void update(Object entity);

	/**
	 * 删除实体
	 * 
	 * @param entityClass
	 *            实体类
	 * @param entityids
	 *            实体id数组
	 */
	public void delete(Serializable... entityids);

	/**
	 * 获取实体
	 * 
	 * @param entityClass
	 *            实体类
	 * @param entityId
	 *            实体id
	 */
	public T find(Serializable entityId);

	/**
	 * 获取最后插入的实体
	 * 
	 * @param entityClass
	 *            实体类
	 * @param entityId
	 *            实体id
	 */
	public T findLast();

	/**
	 * 获取分页数据
	 * 
	 * @param entityClass
	 *            实体类
	 * @param firstindex
	 *            开始索引
	 * @param maxresult
	 *            需要获取的记录数
	 */
	public QueryResult<T> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby);

	public QueryResult<T> getScrollData(String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby);

	public QueryResult<T> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams);

	public QueryResult<T> getScrollData(int firstindex, int maxresult,
			LinkedHashMap<String, String> orderby);

	public QueryResult<T> getScrollData(int firstindex, int maxresult);

	public QueryResult<T> getScrollData(LinkedHashMap<String, String> orderby);

	public QueryResult<T> getScrollData();
}
