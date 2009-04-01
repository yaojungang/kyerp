<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收款</title>
</head>
<body>
<table width="680" border="1" align="center" cellpadding="4"
	cellspacing="0">
	<tr>
		<td align="center">客户</td>
		<td align="center">时间</td>
		<td align="center">摘要</td>
		<td align="center">金额</td>
	</tr>
	<tr>
		<td align="center"><SELECT name="Client00">
			<OPTION selected value=>==请选客户==</OPTION>
			<s:iterator value="#request['clientList']" id="client">
				<option value="<s:property value="#client.CCCom"/>"><s:property
					value="#client.CCCom" /></option>
			</s:iterator>
		</select></td>
		<td align="center"><s:datetimepicker name="kk" value="today"
			toggleType="explode" displayFormat="yyyy-MM-dd" toggleDuration="300" /></td>
		<td align="center"><input type="text" name="textfield"
			id="textfield"></td>
		<td align="center"><input type="text" name="textfield2"
			id="textfield2"></td>
	</tr>
</table>
<br>
<center><input type="submit" name="button" id="button"
	value="提交"></center>
</body>
</html>
