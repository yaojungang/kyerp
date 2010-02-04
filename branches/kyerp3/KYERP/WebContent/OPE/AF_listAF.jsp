<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#session['userSystemFunctionList']" name="userSystemFunctionList"
	id="userSystemFunctionList" />
<s:set value="#session['user']" name="user" id="user" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="#request['pageTitle']" /></title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/showPages.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/table2excel.js"></script>
</head>
<body>
<div id="tableExcel"><span class="pageTitle"><s:property
	value="#request['pageTitle']" /></span>
<table width="100%" border="0" cellpadding="6" cellspacing="1"
	bgcolor="#CCCCCC" id="tableExcel0">
	<tr align="center">
		<td width="80" bgcolor="#EEEEEE"><span class="From_item">任务单号</span></td>
		<td width="90" bgcolor="#EEEEEE"><span class="From_item">委印单位</span></td>
		<td bgcolor="#EEEEEE">印品名称</td>
		<td width="30" bgcolor="#EEEEEE">装订</td>
		<td width="50" bgcolor="#EEEEEE">印数</td>
		<td width="50" bgcolor="#EEEEEE">业务员</td>
		<td width="50" bgcolor="#EEEEEE">应收款</td>
		<td width="50" bgcolor="#EEEEEE">实收款</td>
	</tr>
	<s:iterator value="#request['ListAF']" id="af" status="st">
		<tr <s:if test="#st.even">style="background-color:#EAEAEA"</s:if>
			<s:if test="#st.odd">style="background-color:#FFffff"</s:if>>
			<td height="19" align="center"><s:property value="#af.iso" /><s:property
				value="#af.afNo" /></td>
			<td width="90" align="center"><s:property value="#af.client" /></td>
			<td><a
				href="${pageContext.request.contextPath}/AF/AFInfo.action?afId=<s:property value="#af.afId" />"
				target="_blank"><s:property value="#af.presswork" /></a></td>
			<td align="center"><s:if test="#af.ourbinding==1">
				<img
					src="${pageContext.request.contextPath}/Library/images/minico/yes.gif">
			</s:if></td>
			<td><s:property value="#af.amount" /></td>
			<td align="center"><s:property value="#af.cp" /></td>
			<td align="center">
			<s:if test="#user.userType.equals('Admin') or 'FM-AF-ViewMoneyShould' in #userSystemFunctionList">
				<s:if test="#af.moneyShould > 0">
					<a href="CalAF.action?afId=<s:property value="#af.afId" />"
						target="_blank"><s:property value="#af.moneyShould" /></a>
				</s:if>
			</s:if> <s:if
				test="#user.userType.equals('Admin') or 'OM-AF-CalAF' in #userSystemFunctionList">
				<s:if test="#af.moneyShould > 0"></s:if>
				<s:else>
					<s:form action="CalAF_input" namespace="/OPE" method="get"
						target="_blank">
						<input type="hidden" name="afId"
							value="<s:property value="#af.afId" />" />
						<input type="submit" value="计价" />
					</s:form>
				</s:else>
			</s:if></td>
			<td align="left"><s:if
				test="#user.userType.equals('Admin') or 'FM-AF-ViewMoneyFact' in #userSystemFunctionList">
				<s:if test="#af.moneyFact > 0">
					<a href="CalAF.action?afId=<s:property value="#af.afId" />"
						target="_blank"><s:property value="#af.moneyFact" /></a>
				</s:if>
			</s:if> <s:if
				test="#user.userType.equals('Admin') or 'OM-AF-MoneyInput' in #userSystemFunctionList">
				<s:if test="#af.moneyFact > 0"></s:if>
				<s:else>
					<s:form action="CalAF_MoneyInput" namespace="/OPE" method="get"
						target="_blank">
						<input type="hidden" name="afId"
							value="<s:property value="#af.afId" />" />
						<input type="submit" value="收款" />
					</s:form>
				</s:else>
			</s:if></td>
		</tr>
	</s:iterator>
</table>
</div>
<br />
<br />
<s:set value="#request['AFPager']" name="pager" />
<script language="JavaScript">
<!--
var pg = new showPages('pg');
pg.pageCount =<s:property value="#pager.totalPage" />;  // 定义总页数(必要)
pg.argName = 'currentPage';  // 定义参数名(可选,默认为page)
pg.printHtml(1);
//-->
</script>
<div align="center" style="padding: 10px;"><input type="button"
	onClick="javascript:talbe2excel_method1('tableExcel');"
	value="导出到EXCEL"> <input type="button"
	onClick="javascript:getXlsFromTbl('tableExcel0',null);" value="导出CVS文件">
</div>
</body>
</html>