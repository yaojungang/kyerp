<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>查询任务单</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/AF/getAFByNo.action"
	theme="simple" method="GET" target="_blank">按完整任务单号查询 <br />
<input type="text" name="AFNo" style="width: 80px" value="SK2008" /> <input
	type="submit" value="查询" /></form>
<form action="${pageContext.request.contextPath}/AF/getAFByAFNo.action"
	theme="simple" method="post" target="_blank"><br />
按任务单号查询 <br />
<input type="text" name="intAFNo" style="width: 80px" /> <input
	type="submit" value="查询" /></form>
<form action="${pageContext.request.contextPath}/AF/getAFByName.action"
	theme="simple" method="post" target="_blank"><br />
按印品名称查询 <br />
<input type="text" name="pressworkName" style="width: 80px" /> <input
	type="submit" value="查询" /></form>
<form
	action="${pageContext.request.contextPath}/AF/getAFByClient.action"
	theme="simple" method="post" target="_blank"><br />
按客户名称查询 <br />
<input type="text" name="client" style="width: 80px" /> <input
	type="submit" value="查询" /></form>
</body>
</html>