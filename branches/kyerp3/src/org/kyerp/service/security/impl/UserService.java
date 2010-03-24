package org.kyerp.service.security.impl;

import java.util.ArrayList;
import java.util.List;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.security.User;
import org.kyerp.service.security.IUserService;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
public class UserService extends DaoSupport<User> implements IUserService,UserDetailsService{
	public User getUserByName(String userName) {
		return (User) this.loadUserByUsername(userName);
	}

	@Override
	// 根据用户名得到用户的权限信息等详细资料
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
		StringBuffer jpql = new StringBuffer("o.enabled=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		if(null != userName) {
			jpql.append(" and o.userName=?" + (params.size() + 1));
			params.add(userName);
		} else {
			jpql.append(" and 1=1");
		}
		List<User> users = super.getScrollData(jpql.toString(), params.toArray(), null).getResultlist();
		if(users.size() == 0) {
			throw new RuntimeException(userName + "用户没有找到!");
		}
		User user = users.get(0);
		return user;
	}
}
