<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="#request['pageTitle']" />-系统管理</title>
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
	<li><input type="button" value="增加系统功能"
		onClick="ymPrompt.win(document.getElementById('popWindowAddSystemFunction').innerHTML, 450,350, '增加系统功能');"></li>
	<li>筛选：<select
		onchange="window.location.href=this.options[this.selectedIndex].value;">
		<option>选择模块</option>
		<s:iterator value="#request['SystemMoudlesList']" status="st">
			<option value='SystemFunctionsAdmin.action?moudleId=<s:property value="id"/>' <s:if test="id == #request.moudleId" >selected="selected"</s:if>><s:property value="id" /> - <s:property value="chineseName" />[<s:property value="name" />]</option>
		</s:iterator>
	</select></li>
</ul>
</div>
<span class="pageTitle"><s:property value="#request['pageTitle']" /></span>
<table width="100%" border="0" align="center" cellpadding="6"
	class="stripe_tb" cellspacing="0">
	<thead>
		<tr>
			<th width="50px">功能ID</th>
			<th width="180px">功能名称</th>
			<th>功能说明</th>
			<th width="80px">修改</th>
		</tr>
	</thead>
	<s:iterator value="#request['systemFunctionList']" id="sf" status="st">
		<tr>
			<td><s:property value="#sf.id" /></td>
			<td><s:property value="#sf.name" /></td>
			<td><s:property value="#sf.remark" /></td>
			<td>
	<input type="button" value="修改" onClick="ymPrompt.win(document.getElementById('popWindowEditSystemFunction<s:property value="#sf.id" />').innerHTML, 450,350, '修改系统功能');">
			
			<div id="popWindowEditSystemFunction<s:property value="#sf.id" />" style="display: none;">
<div class="popWindowBody">
<form action="EditSystemFunction_save.action" method="POST">
<input type="hidden" name="systemFunction.id" value="<s:property value="#sf.id"/>" />
<table width="100%" border="0" align="center" cellpadding="8" cellspacing="0">
	<tr>
		<td width="80" align="right">所属模块</td>
		<td align="left"><select name="systemFunction.systemModule.id">
			<s:iterator value="#request['SystemMoudlesList']" status="st">
			<option value='<s:property value="id"/>' <s:if test="id == #sf.systemModule.id" >selected="selected"</s:if>><s:property value="id" /> - <s:property value="chineseName" />[<s:property value="name" />]</option>
		</s:iterator>
		</select></td>
	</tr>
	<tr>
		<td width="80" align="right">功能名称</td>
		<td align="left"><input type="text" name="systemFunction.name" value="<s:property value="#sf.name" />" style="width: 200px;" /></td>
	</tr>
	<tr>
		<td width="80" align="right">描述</td>
		<td align="left"><textarea name="systemFunction.remark" style="width: 200px; height: 80px;"><s:property value="#sf.remark" /></textarea></td>
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

<div id="popWindowAddSystemFunction" style="display: none;">
<div class="popWindowBody">
<form action="AddSystemFunction_save.action" method="POST">
<table width="100%" border="0" align="center" cellpadding="8"
	cellspacing="0">
	<tr>
		<td width="80" align="right">所属模块</td>
		<td align="left"><select name="systemFunction.systemModule.id">
			<s:iterator value="#request['SystemMoudlesList']" status="st">
			<option value='<s:property value="id"/>' <s:if test="id == #request.moudleId" >selected="selected"</s:if>><s:property value="id" /> - <s:property value="chineseName" />[<s:property value="name" />]</option>
		</s:iterator>
		</select></td>
	</tr>
	<tr>
		<td width="80" align="right">功能名称</td>
		<td align="left"><input type="text" name="systemFunction.name"
			style="width: 200px;" /></td>
	</tr>
	<tr>
		<td width="80" align="right">描述</td>
		<td align="left"><textarea name="systemFunction.remark"
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
