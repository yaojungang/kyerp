package com.tyopf.util;

import cn.edu.ctgu.ghl.fetion.Fetion;

public class SendMobileMsg {
	public static String SendMsg(String myPhone, String myPassword, String toPhone, String msg) {
		Fetion fetiont = new Fetion(myPhone, myPassword);
		fetiont.login();
//		for (Contact cc : fetiont.getContacts()) {
//			System.out.println("####\r\n" + cc + "\r\n");
//		}
		if(toPhone.length()<3 || toPhone.equals(fetiont.getFetionNum())){
			fetiont.sendSms2SelfPhone(msg);
			System.out.println(myPhone+" To Self");
		}
		else{
			fetiont.sendSms(toPhone,msg);
			System.out.println(myPhone + " Send a Message To "+toPhone);
		}
		fetiont.logout();
		return "success";
	}
}
