<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>质量问题记录</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/showPages.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ymPrompt/ymPrompt.js"></script>
<link rel="stylesheet" id='skin' type="text/css"
	href="${pageContext.request.contextPath}/Library/js/ymPrompt/skin/qq/ymPrompt.css" />
<script src="${pageContext.request.contextPath}/Library/js/jquery.js"
	type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
    $(".stripe_tb tr").mouseover(function(){
    $(this).addClass("over");}).mouseout(function(){
    $(this).removeClass("over");}).click(function(){	
	$(this).toggleClass("click").removeClass("alt")})
 	$(".stripe_tb tr:even").addClass("alt");
});
</script>
</head>
<body>

<span class="pageTitle"><s:property value="#request['pageTitle']" /></span>
<table width="100%" border="0" align="center" cellpadding="6"
	class="stripe_tb" cellspacing="0">
	<thead><tr>
		<th width="40px;">ID</th>
		<th width="60px;">任务单号</th>
		<th width="80px;">印品名称</th>
		<th width="80px;">车间</th>
		<th width="80px;">责任人</th>
		<th width="80px;">问题描述</th>
		<th>处置</th>
        <th width="60px;">审核</th>
	</tr>
	</thead>
	<tbody>
	<s:iterator value="#request['QualityProblemList']" id="afqp" status="st">
		<tr>
			<td width="40px"><s:property value="#afqp.id" /></td>
			<td width="60px"><s:property value="#afqp.afBase.iso" /><s:property
				value="#afqp.afBase.afNo" /></td>
			<td width="80px"><s:property value="#afqp.afBase.presswork" /></td>
			
          <td width="80px"><s:property value="#afqp.workshop" /></td>
          <td width="80px"><s:property value="#afqp.personLiable" /></td>
          <td width="80px"><s:property value="#afqp.description" /></td>
		  <td><s:property value="#afqp.solution" /></td>
          <td width="60px"><s:property value="#afqp.verifyMan" /></td>
		</tr>
	</s:iterator>
	</tbody>
</table>
<s:set value="#request['Pager']" name="pager" />
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
