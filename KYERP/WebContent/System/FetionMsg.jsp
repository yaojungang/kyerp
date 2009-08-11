<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>飞信短信</title>
</head>
<body>
<span class="pageTitle">飞信短信</span>
<form action="FetionMsgSend.action" method="POST" target="_blank">
手机：<input name="myPhone" value=""/ ><br /><br />
密码：<input name="myPassword" type="password" value=""/ ><br /><br />
发往：<input name="toPhone" value=""/ >（手机号或飞信号）<br /><br />
内容：<textarea name="msg" rows="8" cols="60">飞信测试内容</textarea><br /><br />
<input type="submit" value="发  送" />
</form>
</body>
</html>