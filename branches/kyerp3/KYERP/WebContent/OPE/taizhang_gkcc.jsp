<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>顾客财产接收记录</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/function.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/table2excel.js"></script>
</head>
<body>
<div id="div_print">
<div class="height30"></div>
<div align="center">顾客财产接收记录</div>
<div align="left">QYYT-D-7.5.4-04</div>
<table width="100%" border="1" align="center" cellpadding="0"
	cellspacing="0" bordercolor="#000000" id="tableExcel0">
	<tr>
		<td rowspan="2" align="center">日期</td>
		<td rowspan="2" align="center">卡号</td>
		<td rowspan="2" align="center">顾客名称</td>
		<td rowspan="2" align="center">联系人</td>
		<td rowspan="2" align="center">印品名称</td>
		<td colspan="2" align="center">软片数量</td>
		<td rowspan="2" align="center">接收人</td>
		<td rowspan="2" align="center">接收日期</td>
		<td rowspan="2" align="center">制版接收人</td>
	</tr>
	<tr>
		<td align="center">正文</td>
		<td align="center">封面</td>
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
			<td align="center">&nbsp;<s:iterator value="#af.AfElement">
				<s:if test="EType.equals('BB')">
					<s:property value="EP" />
				</s:if>
			</s:iterator></td>
			<td align="center">&nbsp;<s:iterator value="#af.AfElement">
				<s:if test="EType.equals('Cover')">
					<s:property value="EP" />
				</s:if>
			</s:iterator></td>
			<td align="center">&nbsp;<s:property value="#af.cp" /></td>
			<td align="center">&nbsp;<s:date name="#af.ad" format="MM月dd日"
				nice="false" /></td>
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
