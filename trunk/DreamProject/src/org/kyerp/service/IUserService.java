package org.kyerp.service;

import java.util.List;

import org.kyerp.domain.Role;
import org.kyerp.domain.User;

public interface IUserService extends GenericService {
	Role findRoleById(Long id);

	User findUserById(Long id);

	Role getRoleByName(String propertyName, Object value);

	User getUserByName(String propertyName, Object value);

	List<Role> queryRole(String sql, Object[] paras, int begin, int count);

	List<User> queryUser(String sql, Object[] paras, int begin, int count);

	void removeRole(Role role);

	void removeUser(User user);

	void removeUserById(Long id);

	void removRoleById(Long id);

	void saveRole(Role role);

	void saveUser(User user);

	void updateRole(Role role);

	void updateUser(User user);
}
