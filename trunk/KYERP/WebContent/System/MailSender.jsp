<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>发送邮件</title>
</head>
<body>
<span class="pageTitle">发送邮件</span>
<form action="MailSender.action" method="POST">
		
邮件服务器：<input name="mailServerHost" value='<s:property value="#request['mailServerHost']" />' /> &nbsp; &nbsp;  端口：<input name="mailServerPort" value='<s:property value="#request['mailServerPort']" />' /><br /><br />
用户名：<input name="userName" value='<s:property value="#request['userName']" />' />&nbsp; &nbsp; 密码：<input name="myPassword" type="password" value='<s:property value="#request['myPassword']" />' /><br /><br />
邮件地址：<input name="fromAddress" value='<s:property value="#request['fromAddress']" />' /><br /><br />
<hr />
发送到：<input name="toAddress" value='<s:property value="#request['toAddress']" />' /><br /><br />
主题：<input name="subject" style="width:450px;" value='<s:property value="#request['subject']" />' /><br /><br />
内容：<br /><textarea name="content" rows="12" cols="80"><s:property value="#request['content']" /></textarea><br /><br />
<input type="submit" value="发  送" />
</form>
</body>
</html>