package org.kyerp.service.impl;

import java.util.List;

import org.kyerp.dao.IUserDAO;
import org.kyerp.domain.User;
import org.kyerp.service.IUserService;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public class UserService implements IUserService {
	private IUserDAO userDao;

	public IUserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public User find(Long id) {
		return userDao.find(id);
	}

	@Override
	public User getByName(String propertyName, Object value) {
		return userDao.getByName(propertyName, value);
	}

	@Override
	public List<User> query(String sql, Object[] paras, int begin, int count) {
		return userDao.query(sql, paras, begin, count);
	}

	@Override
	public void remove(User u) {
		userDao.remove(u);
	}

	@Override
	public void save(User u) {
		userDao.save(u);
	}

	@Override
	public void update(User u) {
		userDao.update(u);
	}
	
}
