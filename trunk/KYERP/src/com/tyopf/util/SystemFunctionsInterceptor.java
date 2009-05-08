package com.tyopf.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tyopf.dao.impl.UserDAO;
import com.tyopf.vo.Employee;
import com.tyopf.vo.Role;
import com.tyopf.vo.SystemFunctions;
import com.tyopf.vo.User;

public class SystemFunctionsInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 2851203712084901130L;
	private String checkFunctions;
	private String message;
	private UserDAO userDAO;
	public String getCheckFunctions() {
		return checkFunctions;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setCheckFunctions(String checkFunctions) {
		this.checkFunctions = checkFunctions;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Boolean getAuth = false;
		Map session = invocation.getInvocationContext().getSession();
		Map request0 = (Map) invocation.getInvocationContext().get("request");
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		User user = (User) session.get("user");
		if (null == user) {
			//System.out.println("cookie login start");
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; cookies != null && i < cookies.length; i++) {
					if ("userId".equals(cookies[i].getName())) {
						int userId = new Integer(cookies[i].getValue());
						user =(User) userDAO.getUserById(userId);
						//System.out.println("userId="+userId);
						if (null != user) {
							System.out.println(new Date()+" "+user.getUsername()+" login from cookie!");
							user.setLastLoginIp(org.apache.struts2.ServletActionContext
									.getRequest().getRemoteAddr());
								Set<Role> userRoles = user.getRoles();
								List<String> userSystemFunctionList = new ArrayList<String>();
								for(Role role:userRoles){
									for(SystemFunctions systemFunction : role.getSystemFunctions()){
										userSystemFunctionList.add(systemFunction.getName());
									}
								}
							Employee e = userDAO.getEmployeeById(user.getEmployee().getId());
							session.put("user", user);
							session.put("employee", e);
							session.put("userSystemFunctionList", userSystemFunctionList);
						}
					}
				}
			}
		}
		if (null != user) {
			List<String> userSystemFunctionList = (List<String>) session.get("userSystemFunctionList");
			for (String systemfunction : userSystemFunctionList) {
				if (checkFunctions.equals(systemfunction)) {
					getAuth = true;
				}
			}
			if ("Admin".equals(user.getUserType())) {
				getAuth = true;
			}
			if ("Locked".equals(user.getUserType())) {
				getAuth = false;
				message = "您的账号已被锁定，请联系管理员！";
				request0.put("message", message);
				return Action.LOGIN;
			}
			if (getAuth) {
				String result = invocation.invoke();
				return result;
			}
			message = "您没有【" + checkFunctions + "】权限！请联系管理员，或者用其他用户名重新登陆！";
			request0.put("message", message);
			return Action.LOGIN;
		}
		message = "请输入用户名和密码，登陆系统！";
		request0.put("message", message);
		return Action.LOGIN;
	}
}
