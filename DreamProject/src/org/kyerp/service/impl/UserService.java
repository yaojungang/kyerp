package org.kyerp.service.impl;

import java.util.List;

import org.kyerp.dao.IUserDAO;
import org.kyerp.service.IUserService;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Transactional
public class UserService<T> implements IUserService {
	private IUserDAO userDao;

	@Override
	public Object find(Long id) {
		return this.userDao.find(id);
	}

	@Override
	public Object getByName(String propertyName, Object value) {
		return this.userDao.getByName(propertyName, value);
	}

	public IUserDAO getUserDao() {
		return userDao;
	}

	@Override
	public List query(String sql, Object[] paras, int begin, int count) {
		return this.userDao.query(sql, paras, begin, count);
	}

	public void remove(T t) {
		this.userDao.remove(t);
	}

	@Override
	public void save(Object t) {
		this.userDao.save(t);
	}

	public void setUserDao(IUserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public void update(Object t) {
		this.userDao.update(t);
	}

}
