package com.tyopf.util;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tyopf.vo.SystemFunctions;
import com.tyopf.vo.User;

public class SystemFunctionsInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 2851203712084901130L;
	private String checkFunctions;
	private String message;
	
	public String getCheckFunctions() {
		return checkFunctions;
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
		Map request = (Map) invocation.getInvocationContext().get("request");
		
		User user = (User)session.get("user");
		if (null != user)
		{
			List<String> userSystemFunctionList = (List<String>) session.get("userSystemFunctionList");
			for(String systemfunction:userSystemFunctionList){
				if(checkFunctions.equals(systemfunction)){
					getAuth = true;
				}
			}
		if ("Admin".equals(user.getUserType())) {
			getAuth = true;
		}
		if("Locked".equals(user.getUserType())){
			getAuth = false;
			message = "您的账号已被锁定，请联系管理员！";
			request.put("message", message);
			return Action.LOGIN;
		}
		if (getAuth) {
			String result= invocation.invoke();
			
			return result;
		}
		message = "您没有【" + checkFunctions + "】权限！请联系管理员，或者用其他用户名重新登陆！";
		request.put("message", message);
		return Action.LOGIN;
		}
		message = "请输入用户名和密码，登陆系统！";
		request.put("message" , message);
		return Action.LOGIN;
	}

}
