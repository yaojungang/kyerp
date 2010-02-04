/**
 * 
 */
package org.kyerp.service.security.impl;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author y109 2010-2-1下午03:29:35
 */
@SuppressWarnings("serial")
public class MySpecialAuthenticationFilter extends HttpServlet implements
		Filter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		// 判断用户是否登录
// SecurityContextHolder.getContext()
// .getAuthentication();
// System.out.println("current User：" + currentUser.getUsername());
		if (null != SecurityContextHolder.getContext().getAuthentication()) {
			((HttpServletResponse) response).setHeader("sessionStatus", "YES");
// ((HttpServletResponse) response).setHeader("serverMessage", "没有权限");
// if(){
//				
// }
		}
		filterChain.doFilter(request, response);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
