package com.tyopf.action.test;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IHRService;
import com.tyopf.service.ISystemService;
import com.tyopf.vo.Employee;

@SuppressWarnings("serial")
public class TestAction extends ActionSupport {
	
	String myPhone;
	String myPassword;
	String toPhone;
	String msg;
	
	
	public String getMyPhone() {
		return myPhone;
	}


	public void setMyPhone(String myPhone) {
		this.myPhone = myPhone;
	}


	public String getMyPassword() {
		return myPassword;
	}


	public void setMyPassword(String myPassword) {
		this.myPassword = myPassword;
	}


	public String getToPhone() {
		return toPhone;
	}


	public void setToPhone(String toPhone) {
		this.toPhone = toPhone;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String testFetion() throws Exception{
		//System.out.println("testFetion");
		com.tyopf.util.SendMobileMsg.SendMsg(myPhone,myPassword,toPhone,msg);
		
		return SUCCESS;
	}
}