<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>详细任务列表</title>
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
<span class="pageTitle">印刷车间台帐</span>
<table width="98%" border="0" cellpadding="6" cellspacing="1"
	bgcolor="#CCCCCC">
	<tr align="center">
	  <td bgcolor="#EEEEEE">日期</td>
		<td bgcolor="#EEEEEE"><span class="From_item">任务单号</span></td>
		<td bgcolor="#EEEEEE">单位</td>
		<td bgcolor="#EEEEEE">印品名称</td>
		<td bgcolor="#EEEEEE">类型</td>
		<td bgcolor="#EEEEEE">印数</td>
		<td bgcolor="#EEEEEE">代数</td>
		<td bgcolor="#EEEEEE">纸张规格</td>
		<td bgcolor="#EEEEEE">令数</td>
		<td bgcolor="#EEEEEE">机台</td>
		<td bgcolor="#EEEEEE">要求日期</td>
		<td bgcolor="#EEEEEE">实际日期</td>
		<td bgcolor="#EEEEEE">样书</td>
		<td bgcolor="#EEEEEE">交回签字</td>
		<td bgcolor="#EEEEEE">备注</td>
	</tr>
	<s:iterator value="#request['ListAFE']" id="afe" status="st">
		<tr  bgcolor="#FFFFFF">
		  <td align="center"><s:date name="#afe.afBase.ad" nice="false" format="yy-MM-dd" /></td>
			<td align="center"><s:property value="#afe.afBase.iso" /><s:property
				value="#afe.afBase.afNo" /></td>
			<td><s:property value="#afe.afBase.client" /></td>
			<td><a
				href='${pageContext.request.contextPath}/AF/AFInfo.action?afId=<s:property value="#afe.afBase.afId" />'
				target="_blank"><s:property value="#afe.afBase.presswork" /></a></td>
			<td><s:if test="#afe.EType.equals('BB')">正文</s:if> <s:if
				test="#afe.EType.equals('Cover')">封面</s:if> <s:if
				test="#afe.EType.equals('CI')">插页</s:if></td>
			<td align="center"><s:property value="#afe.afBase.amount" /></td>
			<td align="center">&nbsp;</td>
			<td align="center"><s:property value="#afe.EPaper" /></td>
			<td align="center"><s:property value="#afe.EReam" /> + <s:property value="#afe.EOvers" /></td>
			<td align="center"></td>
			<td align="center"><s:date name="#afe.EPlanPress" nice="false" format="MM-dd" /></td>
			<td align="center"><s:if test="#afe.EComPress">
				<s:date name="#afe.EComPress" nice="false" format="MM-dd" />
			</s:if> <s:else>
				<div id='div<s:property value="#afe.afEId" />'><a
					id='<s:property value="#afe.afEId" />' href="#"
					onclick="AFComplete('PL_Complete.action?afEId=','<s:property value="#afe.afEId" />')">完成</a>
				</div>
			</s:else></td>
			<td align="center">&nbsp;</td>
			<td align="center">&nbsp;</td>
			<td align="center">&nbsp;</td>
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
