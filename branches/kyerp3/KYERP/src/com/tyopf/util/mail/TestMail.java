package com.tyopf.util.mail;

import java.util.Date;

public class TestMail {
	public static void main(String[] args){
        //这个类主要是设置邮件
	  MailSenderInfo mailInfo = new MailSenderInfo(); 
	  mailInfo.setMailServerHost("mail.tyopf.com"); 
	  mailInfo.setMailServerPort("25"); 
	  mailInfo.setValidate(true); 
	  mailInfo.setUserName("postmaster@tyopf.com"); 
	  mailInfo.setPassword("tyopfkeek");//您的邮箱密码 
	  mailInfo.setFromAddress("postmaster@tyopf.com"); 
	  mailInfo.setToAddress("y109@tyopf.com"); 
	  mailInfo.setSubject("设置邮箱标题"+new Date()); 
	  mailInfo.setContent("设置邮箱内容"); 
        //这个类主要来发送邮件
	  SimpleMailSender sms = new SimpleMailSender();
         sms.sendTextMail(mailInfo);//发送文体格式 
         //sms.sendHtmlMail(mailInfo);//发送html格式
         //System.out.println("send mail");
	}
	
}
