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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kyerp.domain.security.User;
import org.kyerp.utils.ConfigReader;
import org.kyerp.utils.WebUtil;

/**
 * @author y109 2010-2-1下午03:29:35
 */
@SuppressWarnings("serial")
public class MySpecialAuthenticationFilter extends HttpServlet implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
		// 获取request 和 response
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		// 判断用户是否登录
		User currentUser;
		try {
			currentUser = (User) WebUtil.getCurrentUser();

			if(null != currentUser) {
				((HttpServletResponse) response).setHeader("LOGINED", "YES");
				((HttpServletResponse) response).setHeader("currentUser", currentUser.getUsername());
				// ((HttpServletResponse) response).setHeader("currentEmployee", currentUser.getEmployee().getId().toString());
			} else {
				response.sendRedirect(ConfigReader.read("cas.baseUrl"));
			}
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
