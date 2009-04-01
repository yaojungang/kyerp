<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>往来账管理</title>
</head>
<body>
报账区间:
<s:datetimepicker name="kk" value="today" toggleType="explode"
	displayFormat="yyyy-MM-dd" toggleDuration="300" />
--
<s:datetimepicker name="kk" value="today" toggleType="explode"
	displayFormat="yyyy-MM-dd" toggleDuration="300" />
客户:
<input name="" type="text">
<input type="submit" name="button" id="button" value="查询">
<br>
<br>
<table width="680" border="1" align="center" cellpadding="4"
	cellspacing="0">
	<tr>
		<td align="center">凭证日期</td>
		<td align="center">摘要</td>
		<td align="center">借方金额</td>
		<td align="center">贷方金额</td>
		<td align="center">方向</td>
		<td align="center">金额</td>
	</tr>
	<tr>
		<td align="center">&nbsp;</td>
		<td align="center">&nbsp;</td>
		<td align="center">&nbsp;</td>
		<td align="center">&nbsp;</td>
		<td align="center">&nbsp;</td>
		<td align="center">&nbsp;</td>
	</tr>
</table>
<br>
<center></center>
</body>
</html>
