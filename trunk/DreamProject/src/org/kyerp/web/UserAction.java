package org.kyerp.web;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kyerp.domain.Role;
import org.kyerp.domain.User;
import org.kyerp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserAction {
	@Autowired
	private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/user/getUser.html")
	public String index(ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {
		User user = new User();
		user.setUserName("user" + System.currentTimeMillis());
		user.setLastUseTime(new Date());

		List<User> userList = userService.queryUser("1=1 order by id desc",
				null, 0, 100);

		Role role = new Role();
		role.setRoleName("rolename");
		Set roleSet = new HashSet<Role>();
		roleSet.add(role);

		userService.saveRole(role);
		userService.saveUser(user);

		map.put("userList", userList);
		return "user";
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
