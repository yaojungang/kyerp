<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>飞信短信</title>
</head>
<body>
<span class="pageTitle">飞信短信</span>
<form action="FetionMsgSend.action" method="POST">
手机：<input name="myPhone" value='<s:property value="#request['myPhone']" />' /><br /><br />
密码：<input name="myPassword" type="password" value='<s:property value="#request['myPassword']" />' /><br /><br />
发往：<input name="toPhone" value='<s:property value="#request['toPhone']" />' />（手机号或飞信号）<br /><br />
内容：<textarea name="msg" rows="8" cols="60"><s:property value="#request['msg']" /></textarea><br /><br />
<input type="submit" value="发  送" />
</form>
</body>
</html>