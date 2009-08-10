package com.tyopf.util;

import java.util.Date;

import cn.edu.ctgu.ghl.fetion.Contact;
import cn.edu.ctgu.ghl.fetion.Fetion;

public class SendMobileMsg {
	public static String SendMsg(String myPhone, String myPassword, String toPhone, String msg) {
		Fetion fetiont = new Fetion(myPhone, myPassword);
		System.out.println("toPhone:"+toPhone);
		//Fetion fetion = new Fetion(MyPhone, MyPassword);
		fetiont.login();
//		for (Contact cc : fetiont.getContacts()) {
//			System.out.println("####\r\n" + cc + "\r\n");
//		}
		//fetiont.sendSms2SelfPhone(new Date() + "给自己发个试哈^_^...");
		//System.out.println("MyPhone:"+fetiont.getPhoneNum());
		if(null == toPhone || toPhone==fetiont.getPhoneNum()){
			fetiont.sendSms2SelfPhone(msg);
		}
		else{
		fetiont.sendSms(toPhone,msg);
		}
		fetiont.logout();
		return "success";
	}
}
