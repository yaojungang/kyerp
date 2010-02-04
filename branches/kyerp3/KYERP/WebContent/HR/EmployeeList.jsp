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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Library/js/jsTree/source/tree_component.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/Library/js/jsTree/_lib/css.js">
<script type="text/javascript" src="${pageContext.request.contextPath}/Library/js/jsTree/source/tree_component.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Library/js/jsTree/_lib/jquery.cookie.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/Library/js/jquery.layout/jquery.layout.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/Library/js/jquery.layout/jquery.ui.all.js"></script> 
<style type="text/css"> 

	.ui-layout-pane { /* all 'panes' */ 
		padding: 10px; 
		background: #FFF; 
		border-top: 1px solid #BBB;
		border-bottom: 1px solid #BBB;
		}
		.ui-layout-pane-north ,
		.ui-layout-pane-south {
			border: 1px solid #BBB;
		} 
		.ui-layout-pane-west {
			border-left: 1px solid #BBB;
		} 
		.ui-layout-pane-east {
			border-right: 1px solid #BBB;
		} 
		.ui-layout-pane-center {
			border-left: 0;
			border-right: 0;
			} 
			.inner-center {
				border: 1px solid #BBB;
			} 

		.outer-west ,
		.outer-east {
			background-color: #EEE;
		}
		.middle-west ,
		.middle-east {
			background-color: #F8F8F8;
		}

	.ui-layout-resizer { /* all 'resizer-bars' */ 
		background: #DDD; 
		}
		.ui-layout-resizer:hover { /* all 'resizer-bars' */ 
			background: #FED; 
		}
		.ui-layout-resizer-west {
			border-left: 1px solid #BBB;
		}
		.ui-layout-resizer-east {
			border-right: 1px solid #BBB;
		}

	.ui-layout-toggler { /* all 'toggler-buttons' */ 
		background: #AAA; 
		} 
		.ui-layout-toggler:hover { /* all 'toggler-buttons' */ 
			background: #FC3; 
		} 

	.outer-center ,
	.middle-center {
		/* center pane that are 'containers' for a nested layout */ 
		padding: 0; 
		border: 0; 
	} 

	</style> 
<script type="text/javascript">
var middleLayout;
$(document).ready(function(){
    $(".stripe_tb tr").mouseover(function(){
    $(this).addClass("over");}).mouseout(function(){
    $(this).removeClass("over");}).click(function(){	
	$(this).toggleClass("click").removeClass("alt")})
 	$(".stripe_tb tr:even").addClass("alt");
	
	middleLayout = $('body').layout({ 
			center__paneSelector:	".peopleList" 
		,	west__paneSelector:		".deptTree" 
		,	west__size:				100 
		,	spacing_open:			8  // ALL panes
		,	spacing_closed:			12 // ALL panes
		}); 
	
});
</script>
</head>
<body>
<span class="pageTitle"><s:property value="#request['pageTitle']" /></span>
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
<div class="center">
<div class="deptTree">deptTree</div>
<div class="peopleList">
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
</div>
</body>
</html>
