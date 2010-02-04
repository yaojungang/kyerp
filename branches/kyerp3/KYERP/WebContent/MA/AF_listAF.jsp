<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<table width="98%" border="0" cellpadding="6" cellspacing="1"
	bgcolor="#CCCCCC">
	<tr align="center">
		<td width="85" bgcolor="#EEEEEE"><span class="From_item">任务单号</span></td>
		<td bgcolor="#EEEEEE">印品名称</td>
		<td width="30" bgcolor="#EEEEEE">装订</td>
		<td width="50" bgcolor="#EEEEEE">印数</td>
		<td width="50" bgcolor="#EEEEEE">打印</td>
	</tr>
	<s:iterator value="#request['ListAF']" id="af" status="st">
		<tr <s:if test="#st.even">style="background-color:#EAEAEA"</s:if>
			<s:if test="#st.odd">style="background-color:#FFffff"</s:if>>
			<td align="center"><s:property value="#af.iso" /><s:property
				value="#af.afNo" /></td>
			<td><a
				href="${pageContext.request.contextPath}/AF/AFInfo.action?afId=<s:property value="#af.afId" />"
				target="_blank"><s:property value="#af.presswork" /></a></td>
			<td align="center"><s:if test="#af.ourbinding==1">
				<img
					src="${pageContext.request.contextPath}/Library/images/minico/yes.gif">
			</s:if></td>
			<td align="center"><s:property value="#af.amount" /></td>
			<td align="center"><a
				href="${pageContext.request.contextPath}/AF/AFInfo_print.action?afId=<s:property value="#af.afId" />"
				target="_blank">打印</a></td>
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