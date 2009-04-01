<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看纸张参数-物料管理</title>
<style type="text/css">
<!--
.STYLE1 {
	font-size: large
}
-->
</style>
</head>
<body>
<span class="pageTitle">纸张参数</span>
<div id="div_print">
<div class="height30"></div>
<s:set value="#request.paper" name="paper" />
<table width="350" border="1" cellpadding="6" cellspacing="0"
	style="border-collapse: collapse">
	<tr>
		<td width="40" rowspan="5">
		<div align="center" class="STYLE1">纸<br>
		<br>
		样</div>
		</td>
		<td width="60" align="center">编号</td>
		<td><s:property value="#paper.pid" /></td>
	</tr>
	<tr>
		<td width="60" align="center">品名</td>
		<td><s:property value="#paper.type" /></td>
	</tr>
	<tr>
		<td width="60" align="center">品牌</td>
		<td><s:property value="#paper.bands" /></td>
	</tr>
	<tr>
		<td width="60" align="center">克重</td>
		<td><s:property value="#paper.weight" />gsm</td>
	</tr>
	<tr>
		<td width="60" align="center">规格</td>
		<td><s:property value="#paper.width" /> * <s:property
			value="#paper.height" /></td>
	</tr>
</table>
</div>
<div style="clear: both;"></div>
<div align="center" style="padding: 20px;"><input name="b_print"
	type="button" class="ipt" onClick="printdiv('div_print');" value=" 打印 ">
</div>
</body>
</html>