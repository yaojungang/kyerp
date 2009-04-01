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
	src="${pageContext.request.contextPath}/Library/js/function.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/RMBtoBig.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/table2excel.js"></script>
</head>
<body>
<div id="div_print">
<div class="height30"></div>
<span class="pageTitle"><s:property value="#request['pageTitle']" /></span>
<table width="100%" border="1" cellpadding="6" cellspacing="0"
	bgcolor="#ffffff" id="tableExcel0">
	<tr align="center">
		<td width="40" bgcolor="#EEEEEE"><span class="From_item">序号</span></td>
		<td width="80" bgcolor="#EEEEEE"><span class="From_item">任务单号</span></td>
		<td bgcolor="#EEEEEE">印品名称</td>
		<td width="50" bgcolor="#EEEEEE">业务员</td>
		<td width="50" bgcolor="#EEEEEE">印数</td>
		<td width="80" bgcolor="#EEEEEE">应收款</td>
		<td width="80" bgcolor="#EEEEEE">实收款</td>
	</tr>
	<s:iterator value="#request['ListAF']" id="af" status="st">
		<tr <s:if test="#st.even">style="background-color:#EAEAEA"</s:if>
			<s:if test="#st.odd">style="background-color:#FFffff"</s:if>>
			<td align="center"><s:property value="#st.index +1" /></td>
			<td height="19" align="center"><s:property value="#af.iso" /><s:property
				value="#af.afNo" /></td>
			<td><a
				href="${pageContext.request.contextPath}/AF/AFInfo.action?afId=<s:property value="#af.afId" />"
				target="_blank"><s:property value="#af.presswork" /></a></td>
			<td><s:property value="#af.cp" /></td>
			<td><s:property value="#af.amount" /></td>
			<td align="left"><s:if test="#af.moneyShould > 0">
				<a href="CalAF.action?afId=<s:property value="#af.afId" />"
					target="_blank">￥<s:property value="#af.moneyShould" /></a>
			</s:if><s:elseif test="#af.moneyShould > 0">&nbsp;</s:elseif></td>
			<td align="left"><s:if test="#af.moneyFact > 0">￥<s:property
					value="#af.moneyFact" />
			</s:if><s:elseif test="#af.moneyFact > 0">&nbsp;</s:elseif></td>
		</tr>
	</s:iterator>
</table>
<br />
<table width="98%" border="1" cellpadding="8" cellspacing="0"
	bordercolor="#000000">
	<tr>
		<td>应收金额（大写）：<span class="moneyBig"><script
			type="text/javascript">
var ssBig = cmycurd(fixfloat(<s:property value="#request['moneyAF']" />,2));
document.write(ssBig);</script></span></td>
		<td width="180">应收金额：<span class="moneyXiao">￥ <SCRIPT
			type="text/javascript">document.write(fixfloat(<s:property value="#request['moneyAF']" />,2));</SCRIPT></span></td>
	</tr>
</table>
<br />
<table width="98%" border="1" cellpadding="8" cellspacing="0"
	bordercolor="#000000">
	<tr>
		<td>实收金额（大写）：<span class="moneyBig"><script
			type="text/javascript">
var ssBig = cmycurd(fixfloat(<s:property value="#request['moneyGET']" />,2));
document.write(ssBig);</script></span></td>
		<td width="180">实收金额：<span class="moneyXiao">￥ <SCRIPT
			type="text/javascript">document.write(fixfloat(<s:property value="#request['moneyGET']" />,2));</SCRIPT></span></td>
	</tr>
</table>
</div>
<div align="center" style="padding: 20px;"><input name="b_print"
	type="button" class="ipt" onClick="printdiv('div_print');"
	value=" 打印报表 "> <input type="button"
	onClick="javascript:talbe2excel_method1('div_print');" value="导出到EXCEL">
<input type="button"
	onClick="javascript:getXlsFromTbl('tableExcel0',null);" value="导出CVS文件">
</div>
</body>
</html>