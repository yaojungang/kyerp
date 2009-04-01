<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>业务室台帐-业务管理</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/function.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/table2excel.js"></script>
</head>
<body>
<div id="div_print">
<div class="height30"></div>
<div align="center">业务室台帐</div>
<div align="left">QYYT-D-7.2.2-05</div>
<table width="100%" border="1" align="center" cellpadding="0"
	cellspacing="0" bordercolor="#000000" id="tableExcel0">
	<tr>
		<td rowspan="2" align="center">日期</td>
		<td rowspan="2" align="center">卡号</td>
		<td rowspan="2" align="center">单位</td>
		<td rowspan="2" align="center">联系人</td>
		<td rowspan="2" align="center">印品名称</td>
		<td rowspan="2" align="center">册数</td>
		<td rowspan="2" align="center">印张</td>
		<td colspan="4" align="center">机型</td>
		<td rowspan="2" align="center">开数</td>
		<td rowspan="2" align="center">订法</td>
		<td rowspan="2" align="center">完成日期</td>
		<td rowspan="2" align="center">令数</td>
	</tr>
	<tr>
		<td align="center">04</td>
		<td align="center">05</td>
		<td align="center">双</td>
		<td align="center">四</td>
	</tr>
	<s:iterator value="#request['ListAF']" id="af" status="st">
		<tr>
			<td align="center">&nbsp;<s:date name="#af.ad" format="MM月dd日"
				nice="false" /></td>
			<td align="center"><s:property value="#af.iso" /><s:property
				value="#af.afNo" /></td>
			<td align="left"><s:property value="#af.client" /></td>
			<td align="center"><s:property value="#af.linkman" /></td>
			<td align="left"><s:property value="#af.presswork" /></td>
			<td align="center"><s:property value="#af.amount" /></td>
			<td align="center"><s:set name="teps" value="0" /> <s:iterator
				value="#af.AfElement">
				<s:if test="EPs">
					<s:set name="teps" value="#teps=#teps+EPs" />
				</s:if>
			</s:iterator> <s:property value="#teps" /></td>
			<td align="center">&nbsp; <s:set name="tj04" value="0" /> <s:iterator
				value="#af.AfElement">
				<s:if test="EMachine.equals('04')">
					<s:set name="tj04" value="#tj04 + EPlateAmount" />
				</s:if>
			</s:iterator> <s:property value="#tj04" /></td>
			<td align="center">&nbsp;<s:set name="tj05" value="0" /> <s:iterator
				value="#af.AfElement">
				<s:if test="EMachine.equals('05')">
					<s:set name="tj05" value="#tj05 + EPlateAmount" />
				</s:if>
			</s:iterator> <s:property value="#tj05" /></td>
			<td align="center">&nbsp;<s:set name="tjsm" value="0" /> <s:iterator
				value="#af.AfElement">
				<s:if test="EMachine.equals('双面')">
					<s:set name="tjsm" value="#tjsm + EPlateAmount" />
				</s:if>
			</s:iterator> <s:property value="#tjsm" /></td>
			<td align="center">&nbsp; <s:set name="tjss" value="0" /> <s:iterator
				value="#af.AfElement">
				<s:if test="EMachine.equals('四色')">
					<s:set name="tjss" value="#tjss + EPlateAmount" />
				</s:if>
			</s:iterator> <s:property value="#tjss" /></td>
			<td align="center"><s:property value="#af.format" /></td>
			<td align="center"><s:property value="#af.bm" /></td>
			<td align="center"><s:date name="#af.planDeliver"
				format="MM月dd日" nice="false" /></td>
			<td align="center"><s:set name="tEReam" value="0" /> <s:iterator
				value="#af.AfElement">
				<s:if test="EReam">
					<s:set name="tEReam" value="#tEReam=#tEReam+EReam+EOvers" />
				</s:if>
			</s:iterator> <SCRIPT type="text/javascript">document.write(fixfloat(<s:property value="#tEReam"/>,2));</SCRIPT>
			</td>
		</tr>
	</s:iterator>
</table>
<div align="center" style="padding: 20px;"><input name="b_print"
	type="button" class="ipt" onClick="printdiv('div_print');"
	value=" 打印报表 "> <input type="button"
	onClick="javascript:talbe2excel_method1('div_print');" value="导出到EXCEL">
<input type="button"
	onClick="javascript:getXlsFromTbl('tableExcel0',null);" value="导出CVS文件">
</div>
</body>
</html>
