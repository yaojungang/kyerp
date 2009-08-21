<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人事管理</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/showPages.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ymPrompt/ymPrompt.js"></script>
<link rel="stylesheet" id='skin' type="text/css"
	href="${pageContext.request.contextPath}/Library/js/ymPrompt/skin/qq/ymPrompt.css" />
<script src="${pageContext.request.contextPath}/Library/js/jquery.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<{$xoops_imageurl}>jsTree/source/tree_component.css" />
<script type="text/javascript" src="<{$xoops_imageurl}>jsTree/_lib/css.js">
<script type="text/javascript" src="<{$xoops_imageurl}>jsTree/source/tree_component.js"></script>
<script type="text/javascript" src="<{$xoops_imageurl}>jsTree/_lib/jquery.cookie.js"></script>
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
	<li><input type="button" value="增加人员"
		onClick='javascript:window.location.href="addEmployee.action"'></li>
	<li>
	<select onChange="window.location.href=this.options[this.selectedIndex].value;">
		<option value="#" selected="selected">按部门筛选</option>
		<s:iterator value="#request['DeptTree']" status="st">
			<option value='HRAdminGetEmployeeByDeptId.action?deptId=<s:property value="id"/>' <s:if test="id == #request.deptId" >selected="selected"</s:if>><s:property value="name" /></option>
		</s:iterator>
	</select></li>
	<li>
	<select onChange="window.location.href=this.options[this.selectedIndex].value;">
		    <option value="#" selected="selected">按人员状态筛选</option>
			<option value='HRAdmin.action?workStatus=0' <s:if test="0 == #request.workStatus" >selected="selected"</s:if>>在编人员</option>
			<option value='HRAdmin.action?workStatus=1' <s:if test="1 == #request.workStatus" >selected="selected"</s:if>>试用期人员</option>
			<option value='HRAdmin.action?workStatus=2' <s:if test="2 == #request.workStatus" >selected="selected"</s:if>>申请离职人员</option>
			<option value='HRAdmin.action?workStatus=100' <s:if test="100 == #request.workStatus" >selected="selected"</s:if>>退休人员</option>
			<option value='HRAdmin.action?workStatus=101' <s:if test="101 == #request.workStatus" >selected="selected"</s:if>>离职人员</option>
			<option value='HRAdmin.action?workStatus=102' <s:if test="102 == #request.workStatus" >selected="selected"</s:if>>病休人员</option>
	</select>
	</li>
</ul>
</div>
<span class="pageTitle"><s:property value="#request['pageTitle']" /></span>
<div class="Center">
<table width="100%" border="0" align="center" cellpadding="6"
	class="stripe_tb" cellspacing="0">
	<thead><tr>
		<th width="40px;">ID</th>
		<th width="60px;">姓名</th>
		<th width="60px;">员工编号</th>
		<th width="80px;">部门</th>
		<th width="80px;">进厂时间</th>
		<th width="80px;">办公电话</th>
		<th width="80px;">手机号</th>
		<th width="60px;">用户名</th>
		<th>备注</th>
		<th width="40px;">修改</th>
	</tr>
	</thead>
	<tbody>
	<s:iterator value="#request['EmployeeList']" id="employee" status="st">
		<tr>
			<td width="40px"><s:property value="#employee.id" /></td>
			<td width="60px"><a target="_blank"
				href="Employee.action?id=<s:property value='#employee.id' />"><s:property
				value="#employee.realname" /></a></td>
			<td width="60px"><s:property value="#employee.empNo" /></td>
			<td width="80px"><s:property value="#employee.companyDept.name" /></td>
			
          <td width="80px"><s:date format="yyyy-MM-dd" nice="false" name="#employee.participateDate" /></td>
          <td width="80px"><s:property value="#employee.workTel" /></td>
          <td width="80px"><s:property value="#employee.mobile" /></td>
			<td width="60px"><s:property value="#employee.user.username" /></td>
			<td><s:property value="#employee.remark" /></td>
			<td width="40px"><a href="editEmployee.action?id=<s:property value='#employee.id' />">修改</a></td>
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
</div>

</body>
</html>
