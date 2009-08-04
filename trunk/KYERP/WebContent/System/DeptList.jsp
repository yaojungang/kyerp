<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门管理</title>
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
	<li><input type="button" value="增加部门"
		onClick="ymPrompt.win(document.getElementById('popWindowAddDept').innerHTML, 450,350, '增加部门');"></li>
</ul>
</div>
<span class="pageTitle"><s:property value="#request['pageTitle']" /></span>
<table width="100%" border="0" align="center" cellpadding="6"
	class="stripe_tb" cellspacing="0">
	<thead>
		<tr>
			<th width="40px">编号</th>
			<th width="120px">部门名称</th>
			<th>角色列表</th>
			<th width="80px">员工人数</th>
			<th width="80px">备注</th>
		</tr>
	</thead>
	<s:iterator value="#request['DeptTree']" id="dept" status="st">
		<tr>
			<td><s:property value="#dept.id" /></td>
			<td><a
				href="DeptAdmin.action?upDeptId=<s:property value="#dept.id" />"><s:property
				value="#dept.name" /></a></td>
			<td><s:iterator value="#dept.roles" status="st">
				<a href="EditRole.action?id=<s:property value="id" />"><s:property
					value="rname" /></a>
			</s:iterator></td>
			<td><s:property value="#dept.employeeAmount" /></td>
			<td><input type="button" value="修改部门"
				onClick="ymPrompt.win(document.getElementById('popWindowDept<s:property value="#dept.id" />').innerHTML,450,500,'修改部门')" />
			<div id="popWindowDept<s:property value="#dept.id" />"
				style="display: none;">
			<div class="popWindowBody">
			<form action="EditDept.action" method="POST"><input
				type="hidden" name="dept.id" value="<s:property value="#dept.id" />" />
			<table width="100%" border="0" align="center" cellpadding="8"
				cellspacing="0">
				<tr>
					<td width="120" align="right">部门名称</td>
					<td align="left"><input type="text" style="width: 100px;"
						name="dept.name" value="<s:property value="#dept.name" />" /></td>
				</tr>
				<tr>
					<td width="80" align="right">上级部门</td>
					<td align="left"><select name="dept.upDeptId">
						<option value="0">酷印通ERP</option>
						<s:iterator value="#request['DeptTree']" status="st">
							<option value="<s:property value="id"/>"
								<s:if test="#dept.upDeptId == id">selected="selected"</s:if>><s:property
								value="name" /></option>
						</s:iterator>
					</select></td>
				</tr>
				<tr>
					<td width="80" align="right">排序</td>
					<td align="left"><input type="text" name="dept.deptOrder"
						value="<s:property value="deptOrder"/>" /></td>
				</tr>
				<tr>
					<td width="80" align="right">状态</td>
					<td align="left"><select name="dept.deptStatus">
						<option value="1"
							<s:if test="deptStatus == 1">selected="selected"</s:if>>正常</option>
						<option value="0"
							<s:if test="deptStatus == 0">selected="selected"</s:if>>锁定</option>
					</select></td>
				</tr>
				<tr>
					<td width="80" align="right">备注</td>
					<td align="left"><textarea name="dept.remark"
						style="width: 200px; height: 80px;"><s:property
						value="remark" /></textarea></td>
				</tr>
				<tr>
					<td width="80" align="right"></td>
					<td align="left"><input type="submit" value="保存" /></td>
				</tr>
			</table>
			</form>
			</div>
			</div>
			</td>
		</tr>
	</s:iterator>
</table>
<div id="popWindowAddDept" style="display: none;">
<div class="popWindowBody">
<form action="AddDept.action" method="POST">
<table width="100%" border="0" align="center" cellpadding="8"
	cellspacing="0">
	<tr>
		<td width="80" align="right">部门名称</td>
		<td align="left"><input type="text" style="width: 100px;"
			name="dept.name" /></td>
	</tr>
	<tr>
		<td width="80" align="right">上级部门</td>
		<td align="left"><select name="dept.upDeptId">
			<option value="0">酷印通ERP</option>
			<s:iterator value="#request['DeptTree']" status="st">
				<option value="<s:property value="id"/>"><s:property
					value="name" /></option>
			</s:iterator>
		</select></td>
	</tr>
	<tr>
		<td width="80" align="right">备注</td>
		<td align="left"><textarea name="dept.remark"
			style="width: 200px; height: 80px;"></textarea></td>
	</tr>
	<tr>
		<td width="80" align="right"></td>
		<td align="left"><input type="submit" value="保存" /></td>
	</tr>
</table>
</form>
</div>
</div>
</body>
</html>
