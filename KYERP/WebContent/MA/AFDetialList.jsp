<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#session['privilegesList']" name="privilegesList"
	id="privilegesList" />
<s:set value="#session['user']" name="user" id="user" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="#request['pageTitle']" />-生产管理</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/showPages.js"></script>
</head>
<body>
<span class="pageTitle"><s:property value="#request['pageTitle']" /></span>
<table width="98%" border="1" cellpadding="6" cellspacing="1">
	<tr align="center">
		<td width="85">任务单号</td>
		<td>印品名称</td>
		<td width="30">印前</td>
		<td width="30">制版</td>
		<td width="30">印刷</td>
		<td width="30">装订</td>
		<td width="30">发行</td>
		<td width="30">结清</td>
	</tr>
	<s:iterator value="#request['ListAF']" id="af" status="st">
		<tr>
			<td align="center"><s:property value="#af.iso" /><s:property
				value="#af.afNo" /></td>
			<td><a
				href="${pageContext.request.contextPath}/AF/AFInfo.action?afId=<s:property value="#af.afId" />"
				target="_blank"><s:property value="#af.presswork" /></a></td>
			<td align="center"><s:if test="#af.planTypeset > 0">
				<s:if test="#af.comTypeset > 0">
					<img
						src="${pageContext.request.contextPath}/Library/images/minico/yes.gif">
				</s:if>
				<s:else>
					<img
						src="${pageContext.request.contextPath}/Library/images/notFinish.gif" />
				</s:else>
			</s:if> <s:else>
					-
				</s:else></td>
			<td align="center">-</td>
			<td align="center">-</td>
			<td align="center"><s:if test="#af.planBind > 0">
				<s:if test="#af.comBind > 0">
					<img
						src="${pageContext.request.contextPath}/Library/images/minico/yes.gif">
				</s:if>
				<s:else>
					<img
						src="${pageContext.request.contextPath}/Library/images/notFinish.gif" />
				</s:else>
			</s:if> <s:else>
					-
				</s:else></td>
			<td align="center"><s:if test="#af.planDeliver > 0">
				<s:if test="#af.planDeliver > 0">
					<img
						src="${pageContext.request.contextPath}/Library/images/minico/yes.gif">
				</s:if>
				<s:else>
					<img
						src="${pageContext.request.contextPath}/Library/images/notFinish.gif" />
				</s:else>
			</s:if> <s:else>
					-
				</s:else></td>
			<td align="center"><s:if test="#af.moneyStatus == 0">
				<img
					src="${pageContext.request.contextPath}/Library/images/minico/yes.gif">
			</s:if> <s:else>
					x
				</s:else></td>
		</tr>
	</s:iterator>
</table>
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
</body>
</html>