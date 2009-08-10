package com.tyopf.util;

import java.io.IOException;
import java.util.Date;

import cn.edu.ctgu.ghl.fetion.Fetion;
import cn.edu.ctgu.ghl.fetion.FetionEvent;
import cn.edu.ctgu.ghl.fetion.IFetionEventListener;

public class FetionTest {
	public static void main(String[] args) throws Exception {
		final Fetion fetiont = new Fetion("13810965292", "yaoyiyjg");
//		fetion.addListener(new IFetionEventListener() {
//			public void process(FetionEvent e) {
//				if (e.getFirstLine() != null && e.getFirstLine().startsWith("M") && e.getBody() != null) {
//					fetion.sendSms2SelfPhone(e.toString());
//					if (e.getBody().trim().startsWith("cmd")) {
//						System.out.println("excute[" + e.getBody().trim().substring(3) + "]");
//						try {
//							Runtime.getRuntime().exec(e.getBody().trim().substring(3));
//						}
//						catch (IOException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//					}
//				}
//			}
//		});
		fetiont.login();
		//for (Contact cc : fetion.getContacts()) {
		//	System.out.println("####\r\n" + cc + "\r\n");
		//	fetion.sendSms(cc.getUri(), cc.getNickName() + "你好哦...");
		//}
		fetiont.sendSms2SelfPhone(new Date()+"给自己发个试哈^_^...");
		//fetion.sendSms("13661059897","测试一下！");
		System.out.println(new Date()+"test");
		fetiont.logout();
	}
}
