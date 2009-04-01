<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>装订车间任务单列表</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/showPages.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/jquery.js"></script>
<script type="text/javascript">
function AFComplete(url,id) {
	var divNode = document.getElementById("div" + id);
	$.get(url + id, null, function(data) {
		divNode.innerHTML = data;
	});

}
</script>
</head>
<body>
<span class="pageTitle"><s:property value="#request['pageTitle']" /></span>
<table width="98%" border="0" cellpadding="6" cellspacing="1"
	bgcolor="#CCCCCC">
	<tr align="center">
		<td width="85" bgcolor="#EEEEEE"><span class="From_item">任务单号</span></td>
		<td width="85" bgcolor="#EEEEEE"><span class="From_item">加工类型</span></td>
		<td bgcolor="#EEEEEE">印品名称</td>
		<td width="50" bgcolor="#EEEEEE">印数</td>
		<td width="85" bgcolor="#EEEEEE">成品尺寸</td>
		<td width="50" bgcolor="#EEEEEE">管理</td>
	</tr>
	<s:iterator value="#request['ListAFD']" id="afd" status="st">
		<tr <s:if test="#st.even">style="background-color:#EAEAEA"</s:if>
			<s:if test="#st.odd">style="background-color:#FFffff"</s:if>>
			<td align="center"><s:property value="#afd.afBase.iso" /><s:property
				value="#afd.afBase.afNo" /></td>
			<td align="center"><s:property value="#afd.afEType" />-<s:property
				value="#afd.afDItem" /></td>
			<td><a
				href="${pageContext.request.contextPath}/AF/AFInfo.action?afId=<s:property value="#afd.afBase.afId" />"
				target="_blank"><s:property value="#afd.afBase.presswork" /></a></td>
			<td align="center"><s:property value="#afd.afBase.amount" /></td>
			<td align="center"><s:property value="#afd.afBase.fps" /></td>
			<td align="center"><s:if test="#afd.afDDate">
				<s:date name="#afd.afDDate" nice="false" format="MM-dd HH:MM:SS" />
			</s:if> <s:else>
				<div id='div<s:property value="#afd.afDId" />'><a
					id='<s:property value="#afd.afDId" />' href="#"
					onclick="AFComplete('BL_Complete_noSkin.action?afDId=','<s:property value="#afd.afDId" />')">完成</a>
				</div>
			</s:else></td>
		</tr>
	</s:iterator>
</table>
<br />
<br />
<s:set value="#request.AFPager" name="pager" />
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
