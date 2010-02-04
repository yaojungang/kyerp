package com.tyopf.util.mail;


public class MailSender {
	public static Boolean SendMail(String mailServerHost,String mailServerPort,String userName,String password,String fromAddress,String toAddress,String subject,String content){
        //这个类主要是设置邮件
	  MailSenderInfo mailInfo = new MailSenderInfo(); 
	  mailInfo.setMailServerHost(mailServerHost); 
	  mailInfo.setMailServerPort(mailServerPort); 
	  mailInfo.setValidate(true); 
	  mailInfo.setUserName(userName); 
	  mailInfo.setPassword(password);//您的邮箱密码 
	  mailInfo.setFromAddress(fromAddress); 
	  mailInfo.setToAddress(toAddress); 
	  mailInfo.setSubject(subject); 
	  mailInfo.setContent(content); 
        //这个类主要来发送邮件
	  SimpleMailSender sms = new SimpleMailSender();
         return sms.sendTextMail(mailInfo);//发送文体格式 
         //sms.sendHtmlMail(mailInfo);//发送html格式
         //System.out.println("send mail");
	}
	
}
