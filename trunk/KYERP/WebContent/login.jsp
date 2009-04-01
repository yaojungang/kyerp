<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<SCRIPT language=javaScript><!-- 
if (top.location != location) top.location.href = location.href;
self.moveTo(0,0);
self.resizeTo(screen.availWidth,screen.availHeight);
</SCRIPT>
</head>
<body>

<center><form action="${pageContext.request.contextPath}/login.action" method="post">
	<s:token />
	<br />
	<table width="260" border="0" cellspacing="0" cellpadding="4">
		<tr>
			<td align="center">请登录</td>
		</tr>
		<tr>
			<td>用 户: <s:textfield label="用户名" name="user.username"
				required="true" cssStyle="width: 160px;"></s:textfield></td>
		</tr>
		<tr>
			<td>密 码: <s:password label="密 码" name="user.password"
				required="true" cssStyle="width: 160px;"></s:password></td>
		</tr>
		<tr>
			<td align="center"><s:submit value="登录"></s:submit> <s:reset
				value="重写"></s:reset></td>
		</tr>
	</table>
</form> <br />
<br />
<br />
</center>
</body>
</html>
