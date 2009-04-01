<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="#request['pageTitle']" /></title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/function.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/RMBtoBig.js"></script>
</head>
<body>
<div id="div_print">
<div class="height30"></div>
<span class="pageTitle"><s:property value="#request['pageTitle']" /></span>
<table width="98%" border="0" cellpadding="6" cellspacing="1"
	bgcolor="#CCCCCC">
	<tr align="center">
		<td width="40" bgcolor="#EEEEEE"><span class="From_item">序号</span></td>
		<td width="80" bgcolor="#EEEEEE"><span class="From_item">任务单号</span></td>
		<td bgcolor="#EEEEEE">印品名称</td>
		<td width="50" bgcolor="#EEEEEE">业务员</td>
		<td width="50" bgcolor="#EEEEEE">车间</td>
		<td width="80" bgcolor="#EEEEEE">项目</td>
		<td width="80" bgcolor="#EEEEEE">金额</td>
	</tr>
	<s:iterator value="#request['ListAFV']" id="afv" status="st">
		<tr <s:if test="#st.even">style="background-color:#EAEAEA"</s:if>
			<s:if test="#st.odd">style="background-color:#FFffff"</s:if>>
			<td align="center"><s:property value="#st.index +1" /></td>
			<td height="19" align="center"><s:property
				value="#afv.AfBase.iso" /><s:property value="#afv.AfBase.afNo" /></td>
			<td><a
				href="${pageContext.request.contextPath}/AF/AFInfo.action?afId=<s:property value="#afv.AfBase.afId" />"
				target="_blank"><s:property value="#afv.AfBase.presswork" /></a></td>
			<td><s:property value="#afv.AfBase.cp" /></td>
			<td><s:property value="#afv.chejian" /></td>
			<td align="left"><s:property value="#afv.itemName" /></td>
			<td align="left"><s:if test="#afv.totalAmount > 0">
				<a href="CalAF.action?afId=<s:property value="#afv.AfBase.afId" />"
					target="_blank">￥<s:property value="#afv.totalAmount" /></a>
			</s:if></td>
		</tr>
	</s:iterator>
</table>
<br />
<table width="98%" border="1" cellpadding="8" cellspacing="0"
	bordercolor="#000000">
	<tr>
		<td>合计金额（大写）：<span class="moneyBig"><script
			type="text/javascript">
var ssBig = cmycurd(fixfloat(<s:property value="#request['moneyAFV']" />,2));
document.write(ssBig);</script></span></td>
		<td width="180">合计金额：<span class="moneyXiao">￥ <SCRIPT
			type="text/javascript">document.write(fixfloat(<s:property value="#request['moneyAFV']" />,2));</SCRIPT></span></td>
	</tr>
</table>
<div class="justPrint">
<hr noshade="noshade" size="1" />
<span class="bottomtext">打印者: <s:property
	value="#session.user.employee.realname" /></span> <span class="bottomtext">打印时间:
<%
 			String t = (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
 			.format(new java.util.Date());//日期：存储日期);
 	out.print(t);
 %> </span></div>
</div>
<div align="center" style="padding: 20px;"><input name="b_print"
	type="button" class="ipt" onClick="printdiv('div_print');"
	value=" 打印报表 "></div>
</body>
</html>