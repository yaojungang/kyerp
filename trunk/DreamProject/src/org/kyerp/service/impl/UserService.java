package org.kyerp.service.impl;

import java.util.List;

import org.kyerp.dao.IRoleDAO;
import org.kyerp.dao.IUserDAO;
import org.kyerp.domain.Role;
import org.kyerp.domain.User;
import org.kyerp.service.IUserService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserService implements IUserService {
	private IRoleDAO roleDAO;
	private IUserDAO userDAO;

	@Override
	public Object find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role findRoleById(Long id) {
		return this.roleDAO.find(id);
	}

	@Override
	public User findUserById(Long id) {
		return this.userDAO.find(id);
	}

	@Override
	public Object getByName(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role getRoleByName(String propertyName, Object value) {
		return this.roleDAO.getByName(propertyName, value);
	}

	public IRoleDAO getRoleDAO() {
		return roleDAO;
	}

	@Override
	public User getUserByName(String propertyName, Object value) {
		return this.userDAO.getByName(propertyName, value);
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	@Override
	public List query(String sql, Object[] paras, int begin, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> queryRole(String sql, Object[] paras, int begin, int count) {
		return this.roleDAO.query(sql, paras, begin, count);
	}

	@Override
	public List<User> queryUser(String sql, Object[] paras, int begin, int count) {
		return this.userDAO.query(sql, paras, begin, count);
	}

	@Override
	public void remove(Object t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeRole(Role role) {
		this.roleDAO.remove(role);
	}

	@Override
	public void removeUser(User user) {
		this.userDAO.remove(user);
	}

	@Override
	public void removeUserById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removRoleById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(Object t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveRole(Role role) {
		this.roleDAO.save(role);
	}

	@Override
	public void saveUser(User user) {
		this.userDAO.save(user);
	}

	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public void update(Object t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateRole(Role role) {
		this.roleDAO.update(role);
	}

	@Override
	public void updateUser(User user) {
		this.userDAO.update(user);
	}

}
