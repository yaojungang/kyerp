<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查找任务单</title>
</head>
<body>
<span class="pageTitle">查找任务单</span>
<h2>根据任务单号生成任务单列表</h2>
<div align="left" style="width: 100%;"><s:form
	action="getAFbyAFNoList.action" method="post" target="_blank">
	<table border="0">
		<tr>
			<td valign="top"><textarea rows="10" cols="70" name="AFNoList"></textarea></td>
			<td width="400" align="left" valign="top">请在左边的输入框中输入需要生成的任务单号列表,单号之间用逗号（,）隔开。
			例如：<br>
			SK20080001,SK20080234,SK20080235,SK20080236,LH20080234,LH200801122,SK20080145</td>
		</tr>
		<tr>
			<td align="center"><input type="submit" value="提交" /></td>
			<td>&nbsp;</td>
		</tr>
	</table>
</s:form></div>
</html>
