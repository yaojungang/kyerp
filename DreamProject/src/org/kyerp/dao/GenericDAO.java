package org.kyerp.dao;

import java.util.List;

public interface GenericDAO<T> {
	void save(T t);
	void update(T t);
	void remove(T t);
	T find(Long id);
	T getByName(String propertyName,Object value);
	List<T> query(String sql, Object[] paras, int begin, int count);
}
