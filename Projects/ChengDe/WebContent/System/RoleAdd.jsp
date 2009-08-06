<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#request['roleList']" name="roleList" id="roleList" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加角色</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ymPrompt/ymPrompt.js"></script>
<link rel="stylesheet" id='skin' type="text/css"
	href="${pageContext.request.contextPath}/Library/js/ymPrompt/skin/qq/ymPrompt.css" />
<script src="${pageContext.request.contextPath}/Library/js/jquery.js"
	type="text/javascript"></script>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/Library/js/formValidator/style/validator.css"></link>
<script
	src="${pageContext.request.contextPath}/Library/js/formValidator/formValidator.js"
	type="text/javascript" charset="UTF-8"></script>
<script
	src="${pageContext.request.contextPath}/Library/js/formValidator/formValidatorRegex.js"
	type="text/javascript" charset="UTF-8"></script>
<script type="text/javascript">
$(document).ready(function(){
    $(".stripe_tb tr").mouseover(function(){
    $(this).addClass("over");}).mouseout(function(){
    $(this).removeClass("over");}).click(function(){	
	$(this).toggleClass("click").removeClass("alt")})
 	$(".stripe_tb tr:even").addClass("alt");
});
</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Library/js/tooltip/style.css" />
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/Library/js/tooltip/script.js"></script>
</head>
<body>
<span class="pageTitle"><s:property value="#request['pageTitle']" /></span>
<form action='AddRole_save.action' method="post"><input
	type="hidden" name="role.companyDept.id"
	value='<s:property value="#request['deptId']" />' />
<table width="90%" border="0" align="center" cellpadding="8"
	cellspacing="0" bgcolor="#ffffff">
	<tr>
		<td width="80" align="right" bgcolor="#FFFFFF">角色名称</td>
		<td align="left" bgcolor="#FFFFFF"><input type="text"
			style="width: 100px;" name="role.rname" /></td>
	</tr>
	<tr>
		<td width="80" align="right">角色描述</td>
		<td align="left"><input type="text" name="role.rdescribe"
			style="width: 200px;" /></td>
	</tr>
	<tr>
		<td width="80" align="right">备注</td>
		<td align="left">
		<textarea name="role.rremark" style="width: 250px; height: 100px;"></textarea>
		</td>
	</tr>
</table>

	<table width="100%" border="0" align="center" cellpadding="6"
	class="stripe_tb" cellspacing="0">
		<thead>
			<tr>
				<th width="80" align="center">模块</th>
				<th align="left">权限列表</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="#request['SystemMoudlesList']" id="sm" status="st">
				<tr>
					<td align="center"><s:property value="#sm.chineseName" /><br />[<s:property value="#sm.name" />]</td>
					<td align="left">
				<s:iterator value="#sm.systemFunctions" id="sf" status="st">
					<input type="checkbox" name="sfs" alt="<s:property value="#sf.remark"/>" value="<s:property value="#sf.id"/>" /> <span class="hotspot" onmouseover="tooltip.show('<s:property value="#sf.remark"/>');" onmouseout="tooltip.hide();"><s:property value="#sf.name" /></span>
				</s:iterator>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>

<center><input type="submit" value="保存" name="submit" /></center>
</form>
</body>
</html>
