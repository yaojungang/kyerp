<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>企业经营业绩统计</title>
</head>
<body>
<div align="center"><span class="pageTitle">企业经营业绩统计</span> <s:form
	action="getQYJYYJ.action" method="get" target="_blank">
	<h2 align="left">按任务单号范围统计产值</h2>
	<table width="90%" border="0" cellspacing="4" cellpadding="4">
		<tr>
			<td width="180" align="right">业务类型：</td>
			<td align="left"><select name="AFType" id="AFType">
				<option value="SK">受控</option>
				<option value="LH">零活</option>
			</select></td>
		</tr>
		<tr>
			<td align="right">任务单号范围：</td>
			<td align="left"><input type="text" name="StartAFNo"
				id="StartAFNo" value="20081301"> — <input type="text"
				name="EndAFNo" id="EndAFNo" value="20081550"></td>
		</tr>
		<tr>
			<td align="right">&nbsp;</td>
			<td align="left"><input type="submit" value="统计"></td>
		</tr>
	</table>
</s:form>
</div>
</body>
</html>
