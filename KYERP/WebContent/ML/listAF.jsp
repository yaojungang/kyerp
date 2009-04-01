<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>业务管理首页</title>
</head>
<body>
<span class="pageTitle"><s:property value="#request['pageTitle']" /></span>
<h1>任务单列表</h1>
<table width="80%" border="0" cellpadding="6" cellspacing="1"
	bgcolor="#CCCCCC">
	<tr align="center">
		<td width="85" bgcolor="#EEEEEE"><span class="From_item">任务单号</span></td>
		<td width="85" bgcolor="#EEEEEE"><span class="From_item">印制单号</span></td>
		<td bgcolor="#EEEEEE">印品名称</td>
		<td width="50" bgcolor="#EEEEEE">印次</td>
		<td width="50" bgcolor="#EEEEEE">印数</td>
		<td width="50" bgcolor="#EEEEEE">成品尺寸</td>
		<td width="50" bgcolor="#EEEEEE">开本</td>
		<td width="50" bgcolor="#EEEEEE">查看</td>
	</tr>
	<s:iterator value="#request['ListAF']" id="af">
		<tr>
			<td align="center" bgcolor="#FFFFFF"><s:property value="#af.iso" /><s:property
				value="#af.afNo" /></td>
			<td align="center" bgcolor="#FFFFFF"><s:property
				value="#af.pcAf" /></td>
			<td height="19" bgcolor="#FFFFFF"><a
				href="${pageContext.request.contextPath}/AF/AFInfo.action?afId=<s:property value="#af.afId" />"
				target="_blank"><s:property value="#af.presswork" /></a></td>
			<td bgcolor="#FFFFFF"><s:property value="#af.edition" /></td>
			<td bgcolor="#FFFFFF"><s:property value="#af.amount" /></td>
			<td bgcolor="#FFFFFF"><s:property value="#af.fps" /></td>
			<td bgcolor="#FFFFFF"><s:property value="#af.format" /></td>
			<td align="center" bgcolor="#FFFFFF"><a
				href="${pageContext.request.contextPath}/AF/AFInfo.action?afId=<s:property value="#af.afId" />"
				target="_blank">查看</a></td>
		</tr>
	</s:iterator>
</table>
<br />
<br />
<s:set value="#request['AFPager']" name="pager" />
<s:if test="#pager.hasFirst">
	<a
		href="${pageContext.request.contextPath}/ML/ListAF.action?currentPage=1">首页</a>
</s:if>
<s:if test="#pager.hasPrevious">
	<a
		href="${pageContext.request.contextPath}/ML/ListAF.action?currentPage=<s:property value="#pager.currentPage-1"/>">上一页</a>
</s:if>
<s:if test="#pager.hasNext">
	<a
		href="${pageContext.request.contextPath}/ML/ListAF.action?currentPage=<s:property value="#pager.currentPage+1"/>">下一页</a>
</s:if>
<s:if test="#pager.hasLast">
	<a
		href="${pageContext.request.contextPath}/ML/ListAF.action?currentPage=<s:property value="#pager.totalPage"/>">尾页</a>
</s:if>
<br />
当前第
<s:property value="#pager.currentPage" />
页,总共
<s:property value="#pager.totalPage" />
页
</body>
</html>