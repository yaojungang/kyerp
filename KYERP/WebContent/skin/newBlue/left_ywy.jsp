<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>业务员</title>
</head>
<body>
<table width="100%" border="0" cellpadding="3" cellspacing="0"
	bgcolor="#6088FF">
	<tr bgcolor="#FFFFFF">
		<td height="25" align="center">
		<form method="post" action="getAFByYWY.action" target="_blank"
			style="padding: 0px; margin: 0px;"><input type="text"
			name="YWName" value="" style="width: 60px" /> <s:submit value="查询" /></form>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td height="25" align="center"><a
			href="getAFByYWY.action?YWName=wxy" target="_blank">王秀云</a>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td height="25" align="center"><a
			href="getAFByYWY.action?YWName=cgz" target="_blank">陈桂芝</a></td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td height="25" align="center"><a
			href="getAFByYWY.action?YWName=sjw" target="_blank">孙纪文</a></td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td height="25" align="center"><a
			href="getAFByYWY.action?YWName=wbj" target="_blank">吴宝举</a></td>
	</tr>
</table>
</body>
</html>