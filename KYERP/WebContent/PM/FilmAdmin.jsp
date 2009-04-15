<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>制版车间-软片管理</title>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0">
<table width="100%" border="0" align="center" cellpadding="6"
	class="stripe_tb" cellspacing="0">
	<thead><tr>
		<th width="40px;">ID</th>
		<th width="60px;">名称</th>
		<th>说明</th>
        <th width="40px;">修改</th>
	</tr>
	</thead>
	<tbody>
	<s:iterator value="#request['EmployeeList']" id="employee" status="st">
		<tr>
			<td width="40px"><s:property value="#employee.id" /></td>
			<td width="60px"><s:property value='#employee.name' /></td>
			<td><s:property value="#employee.remark" /></td>
			<td width="40px"><a href="editEmployee.action?id=<s:property value='#employee.id' />">修改</a></td>
		</tr>
	</s:iterator>
	</tbody>
</table>
    
</body>
</html>