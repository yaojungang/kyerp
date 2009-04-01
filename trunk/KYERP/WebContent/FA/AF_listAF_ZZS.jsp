<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#session['privilegesList']" name="privilegesList"
	id="privilegesList" />
<s:set value="#session['user']" name="user" id="user" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="#request['pageTitle']" /></title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/showPages.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/function.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/RMBtoBig.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/table2excel.js"></script>
</head>
<body>
<div id="tableExcel">
<div align="center" style="font-size: 24px; line-height: 180%"><s:property
	value="#request['pageTitle']" /></div>
<div align="right">京国税</div>
<table width="100%" border="1" align="center" cellpadding="6"
	cellspacing="0" bordercolor="#000000" id="tableExcel0">
	<tr align="center">
		<td width="50%" align="left" bgcolor="#FFFFFF">销货单位：北京市清华园胶印厂</td>
		<td colspan="4" align="left" bgcolor="#FFFFFF">发票号：</td>
	</tr>
	<tr align="center">
		<td align="left" bgcolor="#FFFFFF">购货单位：<s:property
			value="#request['client']" /></td>
		<td colspan="4" align="left" bgcolor="#FFFFFF">支票号：</td>
	</tr>
	<tr align="center">
		<td width="50%" bgcolor="#FFFFFF"><span class="From_item">商品或劳务名称</span></td>
		<td width="80" bgcolor="#FFFFFF"><span class="From_item">计量单位</span></td>
		<td width="80" bgcolor="#FFFFFF">数量</td>
		<td width="80" bgcolor="#FFFFFF">单价</td>
		<td width="80" bgcolor="#FFFFFF">金额</td>
	</tr>
	<s:iterator value="#request['ListAF']" id="af" status="st">
		<tr>
			<td bgcolor="#FFFFFF"><s:if test="#af.pcAf != null">
				<s:property value="#af.pcAf" />
			</s:if><s:property value="#af.iso" /><s:property value="#af.afNo" /> <s:property
				value="#af.presswork" /></td>
			<td align="center" bgcolor="#FFFFFF">册</td>
			<td align="center" bgcolor="#FFFFFF"><s:property
				value="#af.amount" /></td>
			<td align="center" bgcolor="#FFFFFF"><s:if
				test="#af.moneyShould > 0">
				<SCRIPT type="text/javascript">document.write(fixfloat(<s:property value="#af.moneyShould / #af.amount" />,2));</SCRIPT>
			</s:if></td>
			<td align="center" bgcolor="#FFFFFF"><s:if
				test="#af.moneyShould > 0">
				<SCRIPT type="text/javascript">document.write(fixfloat(<s:property value="#af.moneyShould" />,2));</SCRIPT>
			</s:if></td>
		</tr>
	</s:iterator>
	<tr>
		<td align="center" bgcolor="#FFFFFF">金额合计 <s:set name="tmoney"
			value="0" /> <s:iterator value="#request['ListAF']">
			<s:if test="moneyShould">
				<s:set name="tmoney" value="#tmoney=#tmoney + moneyShould" />
			</s:if>
		</s:iterator></td>
		<td colspan="3" align="center" bgcolor="#FFFFFF"><s:if
			test="#tmoney > 0">
			<script type="text/javascript">
var ysBig = cmycurd(<s:property value="#tmoney" />);
document.write(ysBig);</script>
		</s:if></td>
		<td align="center" bgcolor="#FFFFFF"><s:if test="#tmoney > 0">
			<script type="text/javascript">document.write(fixfloat(<s:property value="#tmoney / 1.17"/>,2));</script>
		</s:if></td>
	</tr>
</table>
</td>
</tr>
</table>
</div>
<br />
<br />
<br>
<div align="center" style="padding: 10px;"><input type="button"
	onClick="javascript:talbe2excel_method1('tableExcel');"
	value="导出到EXCEL"> <input type="button"
	onClick="javascript:getXlsFromTbl('tableExcel0',null);" value="导出CVS文件">
</div>
</body>
</html>