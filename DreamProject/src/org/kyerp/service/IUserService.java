package org.kyerp.service;

import java.util.List;

import org.kyerp.vo.User;

public interface IUserService{
	void save(User u);
	void update(User u);
	void remove(User u);
	User find(Long id);
	User getByName(String propertyName,Object value);
	List<User> query(String sql, Object[] paras, int begin, int count);
}
