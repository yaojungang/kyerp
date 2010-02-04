package org.kyerp.service.security.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.security.Role;
import org.kyerp.domain.security.SystemResource;
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
public class UserService extends DaoSupport<User> implements IUserService,
		UserDetailsService {

	@Override
	// 根据用户名得到用户的权限信息等详细资料
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createTime", "asc");
		StringBuffer jpql = new StringBuffer("o.enabled=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		if (null != userName) {
			jpql.append(" and o.userName=?" + (params.size() + 1));
			params.add(userName);
		} else {
			jpql.append(" and 1=1");
		}
		// initUser();
		List<User> users = null;
		users = super.getScrollData(jpql.toString(), params.toArray(), orderby)
				.getResultlist();
// System.out.println("jpql:" + jpql.toString() + params.toString()
// + ";users size:" + users.size());
		if (users.size() == 0) {
			// System.out.println(userName + "用户没有找到!");
			// throw new UsernameNotFoundException(userName + "用户没有找到!");
			throw new RuntimeException(userName + "用户没有找到!");
		}
		for (Role r : users.get(0).getRoles()) {
			System.out.println(userName + "的角色：" + r.getName());
			for (SystemResource sr : r.getSystemResources()) {
				System.out.println("资源:" + sr.getName() + "[" + sr.getContent()
						+ "]");
			}
		}
		return users.get(0);
	}
}
