<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#request['roleList']" name="roleList" id="roleList" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码-人员管理</title>
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
<script src="${pageContext.request.contextPath}/Library/js/idCard.js"
	type="text/javascript" charset="UTF-8"></script>
<script>

</script>
</head>
<body>
<span class="pageTitle">修改密码</span>
<form action='addEmployee_save.action' method="post">
<div id="tabs1">
<div class="menu1box">
<ul id="menu1">
	<li class="hover" onClick="setTab(1,0)"><a href="#">修改密码</a></li>
</ul>
</div>
<div class="main1box">
<div class="main" id="main1">
<ul class="block">
	<li>
	<table width="90%" border="0" align="center" cellpadding="8"
		cellspacing="0" bgcolor="#ffffff">
		<tr>
			<td width="80" bgcolor="#FFFFFF" align="right">用户名</td>
			<td bgcolor="#FFFFFF" align="left"><input type="text"
				style="width: 100px;" name="user.username" /></td>
		</tr>
		<tr>
			<td width="80" bgcolor="#FFFFFF" align="right">密码</td>
			<td bgcolor="#FFFFFF" align="left"><input name="user.password"
				id="password1" type="password" style="width: 100px;" /></td>
		</tr>
		<tr>
			<td width="80" bgcolor="#FFFFFF" align="right">确认密码</td>
			<td bgcolor="#FFFFFF" align="left"><input id="password2"
				type="password" style="width: 100px;" onBlur="checkPassword()" /></td>
		</tr>
		<tr>
			<td width="80" align="right" bgcolor="#FFFFFF">部门</td>
			<td align="left" bgcolor="#FFFFFF"><select
				name="employee.companyDept.id">
				<s:iterator value="#request['DeptTree']" status="st">
					<option value="<s:property value="id"/>"><s:property
						value="name" /></option>
				</s:iterator>
			</select></td>
		</tr>
		<tr>
			<td width="80" align="right" bgcolor="#FFFFFF"></td>
			<td align="left" bgcolor="#FFFFFF"><input type="submit"
				value="保存" name="submit" /></td>
		</tr>
	</table>
	</li>
</ul>
</div>
</div>
</div>
<center style="padding: 8px"><input type="submit" value="保存"
	name="submit" onClick="return Submit1_onclick()" /></center>
</form>
</body>
</html>
