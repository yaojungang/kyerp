package org.kyerp.service.security.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Service;

/**
 *org.kyerp.service.security.impl.MyRememberMeServices.java
 * 
 * @author y109
 *         2010-4-15上午12:02:07
 */
@Service
public class MyRememberMeServices implements RememberMeServices{

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.RememberMeServices#autoLogin(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Authentication autoLogin(HttpServletRequest arg0, HttpServletResponse arg1) {
		System.out.println("Auto Login");
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.RememberMeServices#loginFail(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void loginFail(HttpServletRequest arg0, HttpServletResponse arg1) {
		System.out.println("Auto Login Fail");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.RememberMeServices#loginSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void loginSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication arg2) {
		System.out.println("Auto Login Success");

	}

}
