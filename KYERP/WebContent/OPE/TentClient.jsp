<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户生产费用明细</title>
<link rel="STYLESHEET" type="text/css"
	href="${pageContext.request.contextPath}/Library/js/ajaxtabscontent/ajaxtabs/ajaxtabs.css">
<script
	src="${pageContext.request.contextPath}/Library/js/ajaxtabscontent/ajaxtabs/ajaxtabs.js"></script>
</head>
<body>
<div align="center">客户生产费用明细 <br />
客户名称: <input type="text" name="textfield" id="textfield"> <input
	name="提交" type="submit" value="查询"> <br>
<br>
</div>
<table width="680" border="1" align="center" cellpadding="4"
	cellspacing="0">
	<tr>
		<td align="center">印制日期</td>
		<td align="center">任务单号</td>
		<td align="center">印制单号</td>
		<td align="center">印品名称</td>
		<td align="center">金额</td>
	</tr>
	<tr>
		<td align="center">&nbsp;</td>
		<td align="center">&nbsp;</td>
		<td align="center">&nbsp;</td>
		<td align="center">&nbsp;</td>
		<td align="center">&nbsp;</td>
	</tr>
</table>
</html>
