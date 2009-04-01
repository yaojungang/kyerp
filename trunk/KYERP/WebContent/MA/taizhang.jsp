<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生产科台帐</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/function.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/table2excel.js"></script>
</head>
<body>
<div id="div_print">
<div class="height30"></div>
<div align="center">生产科台帐</div>
<div align="left">QYYT-D-7.2.2-05</div>
<table width="100%" border="1" align="center" cellpadding="0"
	cellspacing="0" bordercolor="#000000" id="tableExcel0">
	<tr>
		<td rowspan="2" align="center">开单日期</td>
		<td rowspan="2" align="center">任务单号</td>
		<td rowspan="2" align="center">印品名称</td>
		<td rowspan="2" align="center">单位</td>
		<td rowspan="2" align="center">印数</td>
		<td rowspan="2" align="center">印张</td>
		<td colspan="2" align="center">印刷(封面)</td>
		<td colspan="4" align="center">印刷(正文)</td>
		<td colspan="3" align="center">装订</td>
		<td colspan="2" align="center">发行</td>
		<td rowspan="2" align="center">备注</td>
	</tr>
	<tr>
		<td align="center">制版日期</td>
		<td align="center">完成情况</td>
		<td align="center">纸数</td>
		<td align="center">机器</td>
		<td align="center">开印日期</td>
		<td align="center">完成情况</td>
		<td align="center">订法</td>
		<td align="center">装订日期</td>
		<td align="center">完成情况</td>
		<td align="center">发行日期</td>
		<td align="center">完成情况</td>
	</tr>
	<s:iterator value="#request['ListAF']" id="af" status="st">
		<tr>
			<td align="center">&nbsp;<s:date name="#af.ad" format="MM月dd日"
				nice="false" /></td>
			<td align="center"><s:property value="#af.iso" /><s:property
				value="#af.afNo" /></td>
			<td align="left"><s:property value="#af.presswork" /></td>
			<td align="left"><s:property value="#af.client" /></td>
			<td align="center"><s:property value="#af.amount" /></td>
			<td align="center"><s:set name="teps" value="0" /> <s:iterator
				value="#af.AfElement">
				<s:if test="EPs">
					<s:set name="teps" value="#teps=#teps+EPs" />
				</s:if>
			</s:iterator> <s:property value="#teps" /></td>
			<td align="center">&nbsp;<s:iterator value="#af.AfElement">
				<s:if test="EType.equals('Cover')">
					<s:date name="EPlanPm" format="MM月dd日" nice="false" />
				</s:if>
			</s:iterator></td>
			<td align="center">&nbsp;<s:iterator value="#af.AfElement">
				<s:if test="EType.equals('Cover')">√</s:if>
			</s:iterator></td>
			<td align="center">&nbsp;<s:iterator value="#af.AfElement">
				<s:if test="EType.equals('BB')">
					<SCRIPT type="text/javascript">document.write(fixfloat(<s:property value="EReam+EOvers" />,3));</SCRIPT>
				</s:if>
			</s:iterator></td>
			<td align="center">&nbsp;<s:iterator value="#af.AfElement">
				<s:if test="EType.equals('BB')">
					<s:property value="EMachine" />
				</s:if>
			</s:iterator></td>
			<td align="center">&nbsp;<s:iterator value="#af.AfElement">
				<s:if test="EType.equals('BB')">
					<s:date name="EPlanPm" format="MM月dd日" nice="false" />
				</s:if>
			</s:iterator></td>
			<td align="center">&nbsp;<s:iterator value="#af.AfElement">
				<s:if test="EType.equals('BB')">√</s:if>
			</s:iterator></td>
			<td align="center">&nbsp;<s:property value="#af.bm" /></td>
			<td align="center">&nbsp;<s:if test="#af.ourbinding==1">
				<s:iterator value="#af.AfDispose">
					<s:date name="afDDate" format="MM月dd日" nice="false" />
				</s:iterator>
			</s:if></td>
			<td align="center">&nbsp;<s:if test="#af.ourbinding==1">√</s:if></td>
			<td align="center">&nbsp;<s:date name="#af.planDeliver"
				format="MM月dd日" nice="false" /></td>
			<td align="center">&nbsp;<s:if test="#af.planDeliver">√</s:if></td>
			<td align="center">&nbsp;</td>
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
