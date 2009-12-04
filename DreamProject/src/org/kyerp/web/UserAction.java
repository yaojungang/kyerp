package org.kyerp.web;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.org.User;
import org.kyerp.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("userList")
public class UserAction {
	@Resource(name = "userService")
	IUserService	userService;

	@RequestMapping("/user/user.html")
	public void index(ModelMap model) {
		User user = new User();
		user.setUserName("user" + System.currentTimeMillis());
		user.setLastUseTime(new Date());
		QueryResult<User> userList0 = userService.getScrollData();
		List<User> userList = userList0.getResultlist();
		model.addAttribute("userList", userList);
	}
}
