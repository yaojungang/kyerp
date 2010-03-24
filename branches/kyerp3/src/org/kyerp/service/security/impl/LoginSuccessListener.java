package org.kyerp.service.security.impl;

import java.util.Date;

import org.kyerp.domain.security.User;
import org.kyerp.service.security.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;

/**
 *org.kyerp.service.security.impl.LoginSuccessListener.java
 * 
 * @author y109
 *         2010-3-23上午12:09:14
 */
@SuppressWarnings("unchecked")
public class LoginSuccessListener implements ApplicationListener{
	@Autowired
	IUserService	userService;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof AuthenticationSuccessEvent) {
			AuthenticationSuccessEvent authEvent = (AuthenticationSuccessEvent) event;
			Authentication auth = authEvent.getAuthentication();
			String userName = auth.getName();
			User user = userService.getUserByName(userName);
			// 更新用户登录次数
			if(null != user.getLoginTimes()) {
				user.setLoginTimes(user.getLoginTimes() + 1);
			} else {
				user.setLoginTimes(1);
			}
			// 更新用户登录时间
			user.setLastLoginTime(new Date());
			userService.update(user);

			System.out.println("[" + userName + "]成功登录...");
		}
	}
}
