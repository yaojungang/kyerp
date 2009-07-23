<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>用户信息</title>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="6">
	<tr>
		<td align="center"><s:if
			test="#session.user.employee.photo != null">
			<a href="${pageContext.request.contextPath}/User/index.action" ><img border="0" style="width: 96px;" src="/uploadData/<s:property value="#session.user.employee.photo" />" /></a>
		</s:if> <s:else>
			<a href="${pageContext.request.contextPath}/User/index.action" ><img border="0" src="${pageContext.request.contextPath}/Library/images/noavatar.gif"></a>
		</s:else></td>
	</tr>
	<tr>
		<td align="center">欢迎您 <s:property value="#session.user.employee.realname" /> </td>
	</tr>
	<tr>
		<td align="center"><a href="${pageContext.request.contextPath}<s:property value = "#session.user.url" />">我的首页</a></td>
	</tr>
	<tr>
		<td align="center"><a href="https://cas.tyopf.com:8443/cas/logout?url=http://www.tyopf.com">CAS注销</a></td>
	</tr>
</table>
</body>
</html>