<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生产统计</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/function.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/table2excel.js"></script>
</head>
<body>
<div id="div_print">
<div class="height30"></div>
<span class="pageTitle">生产统计</span>
<table width="485" border="1" align="center" cellpadding="6">
  <tr>
    <td width="108" align="right">总印数</td>
    <td width="341"><s:property value="#request.printAmount" /></td>
  </tr>
  <tr>
    <td align="right">总印张</td>
    <td><s:property value="#request.epsAmount" /></td>
  </tr>
  <tr>
    <td align="right">总纸数</td>
    <td><s:property value="#request.paperAmount" /></td>
  </tr>
</table>
<hr />
<table width="96%" border="0" align="center" cellpadding="4"
	cellspacing="0" bordercolor="#000000" id="tableExcel0">
<tr>
	  <td align="center">序号</td>
		<td align="center">开单日期</td>
		<td align="center">任务单号</td>
		<td align="center">印品名称</td>
		<td align="center">客户</td>
		<td align="center">印数</td>
		<td align="center">印张</td>
		<td align="center">装订</td>
	</tr>
	<s:iterator value="#request['ListAF']" id="af" status="st">
		<tr>
		  <td align="center"><s:property value="#st.index + 1" /></td>
			<td align="center">&nbsp;<s:date name="#af.ad" format="yyyy-MM-dd"
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
			</s:iterator> 
			<s:property value="#teps" /></td>
			<td><s:if test="#af.ourbinding==1">Y</s:if></td>
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
</div>
</body>
</html>
