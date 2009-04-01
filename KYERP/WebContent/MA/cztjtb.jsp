<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产值统计图表-输入范围</title>
</head>
<body>
<span class="pageTitle">产值统计图表-输入范围</span>
<h2>输入任务单号范围</h2>
<div style="padding: 20px; border-width: 1px; line-height: 180%;">
<s:form action="cztjtb" method="GET" target="_blank">
	受控业务(SK)：<input type="text" name="SKStartAFNo" value="20081301" /> - <input
		type="text" name="SKEndAFNo" value="20081550" />
	<br />
	零活业务(LH)：<input type="text" name="LHStartAFNo" value="20080801" /> - <input
		type="text" name="LHEndAFNo" value="20080910" />
	<br />
	<input type="submit" value="提交" />
</s:form></div>
<br>
<h2>各月任务单号参考范围</h2>
<table width="90%" border="1" cellpadding="6" cellspacing="0"
	bordercolor="#000000">
	<tr>
		<td width="120" align="center">&nbsp;</td>
		<td width="40%">受控任务单(SK)</td>
		<td width="40%">零活任务单(LH)</td>
	</tr>
	<tr>
		<td align="center">08年五月</td>
		<td>20080801 — 20081000</td>
		<td>20080546 — 20080670</td>
	</tr>
	<tr>
		<td align="center">08年六月</td>
		<td>20081001 — 20081300</td>
		<td>20080671 — 20080800</td>
	</tr>
	<tr>
		<td align="center">08年七月</td>
		<td>20081301 — 20081550</td>
		<td>20080801 — 20080910</td>
	</tr>
	<tr>
		<td align="center">08年八月</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="center">08年九月</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="center">08年十月</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="center">08年十一月</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="center">08年十二月</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
</table>
相关数据来自业务室
<br>
</html>
