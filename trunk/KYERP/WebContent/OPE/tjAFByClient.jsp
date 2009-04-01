<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="#request['client']" />的任务单统计</title>
</head>
<body>
<s:form action="getMoneyByClientandType" namespace="/OPE" method="post"
	target="_blank">
	<h2 align="left"><s:property value="#request['client']" />的任务单统计</h2>
	<input type="hidden" name="client"
		value="<s:property value="#request['client']" />" />
	<table width="90%" border="0" cellspacing="4" cellpadding="4">
		<tr>
			<td width="180" align="right">业务类型：</td>
			<td align="left"><select name="AFType" id="AFType">
				<option value="SK">受控</option>
				<option value="LH" selected="selected">零活</option>
			</select></td>
		</tr>
		<tr>
			<td align="right">任务单号范围：</td>
			<td align="left"><input name="StartAFNo" type="text"
				id="StartAFNo" value="20080546"> — <input name="EndAFNo"
				type="text" id="EndAFNo" value="20080670"></td>
		</tr>
		<tr>
			<td align="right">&nbsp;</td>
			<td align="left"><input name="提交" type="submit" value="统计"></td>
		</tr>
	</table>
</s:form>
<br>
</html>
