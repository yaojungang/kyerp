<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="#request['pageTitle']" /></title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/showPages.js"></script>
<body>
<s:set value="#request['AFPager']" name="pager" />
<div id="divAFList">
<table width="98%" border="0" cellpadding="6" cellspacing="1"
	bgcolor="#CCCCCC">
	<tr align="center">
		<td width="85" bgcolor="#EEEEEE"><span class="From_item">任务单号</span></td>
		<td bgcolor="#EEEEEE">印品名称</td>
		<td width="50" bgcolor="#EEEEEE">机器</td>
		<td width="50" bgcolor="#EEEEEE">类型</td>
		<td width="90" bgcolor="#EEEEEE">制版</td>
		<td width="90" bgcolor="#EEEEEE">印刷</td>
		<td width="90" bgcolor="#EEEEEE">装订</td>
		<td width="90" bgcolor="#EEEEEE">发行</td>
	</tr>
	<s:iterator value="#request['ListAFE']" id="afe" status="st">
		<tr <s:if test="#st.even">style="background-color:#EAEAEA"</s:if>
			<s:if test="#st.odd">style="background-color:#FFffff"</s:if>>
			<td align="center"><s:property value="#afe.afBase.iso" /><s:property
				value="#afe.afBase.afNo" /></td>
			<td><a
				href="${pageContext.request.contextPath}/AF/AFInfo.action?afId=<s:property value="#afe.afBase.afId" />"
				target="_blank"><s:property value="#afe.afBase.presswork" /></a></td>
			<td><s:property value="#afe.EMachine" /></td>
			<td><s:if test="#afe.EType.equals('BB')">正文</s:if> <s:if
				test="#afe.EType.equals('Cover')">封面</s:if> <s:if
				test="#afe.EType.equals('CI')">插页</s:if></td>
			<td align="center"><s:if test="#afe.EComPm">
				<s:date name="#afe.EComPm" nice="false" format="MM-dd HH:MM" />
			</s:if> <s:else>
				<img
					src="${pageContext.request.contextPath}/Library/images/notFinish.gif" />
			</s:else></td>
			<td align="center"><s:if test="#afe.EComPress">
				<s:date name="#afe.EComPress" nice="false" format="MM-dd HH:MM" />
			</s:if> <s:else>
				<img
					src="${pageContext.request.contextPath}/Library/images/notFinish.gif" />
			</s:else></td>
			<td align="center"><s:if test="#afe.afBase.ourbinding == 1">
				<s:if test="#afe.afBase.comBind">
					<s:date name="#afe.afBase.comBind" nice="false"
						format="MM-dd HH:MM" />
				</s:if>
				<s:else>
					<img
						src="${pageContext.request.contextPath}/Library/images/notFinish.gif" />
				</s:else>
			</s:if> <s:else>X</s:else></td>
			<td align="center"><s:if test="#afe.afBase.comDeliver">
				<s:date name="#afe.afBase.comDeliver" nice="false"
					format="MM-dd HH:MM" />
			</s:if> <s:else>
				<img
					src="${pageContext.request.contextPath}/Library/images/notFinish.gif" />
			</s:else></td>
		</tr>
	</s:iterator>
</table>
<script language="JavaScript">
<!--
var pg = new showPages('pg');
pg.pageCount =<s:property value="#pager.totalPage" />;  // 定义总页数(必要)
pg.argName = 'currentPage';  // 定义参数名(可选,默认为page)
pg.printHtml(1);
//-->
</script></div>
</body>
</html>
