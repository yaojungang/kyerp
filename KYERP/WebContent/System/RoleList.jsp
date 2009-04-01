<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
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
<div class="pim2_secondMenu">
<ul>
	<li></li>
</ul>
</div>
<span class="pageTitle"><s:property value="#request['pageTitle']" /></span>
<table width="100%" border="0" align="center" cellpadding="6"
	class="stripe_tb" cellspacing="0">
	<thead>
		<tr>
			<th width="40px">编号</th>
			<th width="80px">部门名称</th>
			<th>角色列表</th>
			<th width="80px">增加</th>
		</tr>
	</thead>
	<s:iterator value="#request['DeptTree']" id="dept" status="st">
		<tr>
			<td width="40px"><s:property value="#dept.id" /></td>
			<td width="80px"><a
				href="RoleAdmin.action?upDeptId=<s:property value="#dept.id" />"><s:property
				value="#dept.name" /></a></td>
			<td><s:iterator value="#dept.roles" status="st">
				<a href="EditRole.action?id=<s:property value="id" />"><s:property
					value="rname" /></a>
			</s:iterator></td>
			<td width="80px"><input type="button" value="增加角色"
				onClick='javascript:window.location.href="AddRole.action?deptId=<s:property value="#dept.id" />"'></td>
		</tr>
	</s:iterator>
</table>
</body>
</html>
