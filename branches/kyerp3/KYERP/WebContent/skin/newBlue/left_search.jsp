<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>查询任务单</title>
</head>
<body>
<div align="center">
<form class="noPaddingMargin"
	action="${pageContext.request.contextPath}/AF/getAFByNo.action"
	id="forms_AFNo" method="GET" target="_blank"
	onSubmit="return Validator.Validate(document.forms_AFNo,2)"><br />
按完整单号查询 <br />
<input type="text" name="AFNo" style="width: 80px" value="SK2009" /> <input
	type="submit" value="查询" /></form>
<form class="noPaddingMargin" action="getAFByAFNo.action" method="GET"
	target="_blank" id="forms_AFId"
	onSubmit="return Validator.Validate(document.forms_AFId,2)"><br />
按任务单号查询 <br />
<input type="text" name="intAFNo" style="width: 80px" dataType="Integer"
	msg="任务单号格式不正确，只能输入整数" /> <input type="submit" value="查询" /></form>
<form class="noPaddingMargin" action="getAFByYZNo.action" method="GET"
	target="_blank" id="forms_AFId"
	onSubmit="return Validator.Validate(document.forms_AFId,2)"><br />
按印制单号查询 <br />
<input type="text" name="intYZNo" style="width: 80px" dataType="Integer"
	msg="印制号格式不正确，只能输入整数" /> <input type="submit" value="查询" /></form>
<form class="noPaddingMargin" action="getAFByName.action" method="post"
	target="_blank" id="forms_AFname"
	onSubmit="return Validator.Validate(document.forms_AFname,2)"><br />
按印品名称查询 <br />
<input type="text" name="pressworkName" style="width: 80px"
	dataType="Require" msg="[印品名称]必须填写" /> <input type="submit" value="查询" />
</form>
<form class="noPaddingMargin" action="getAFByClient.action"
	method="post" target="_blank" id="forms_client"
	onSubmit="return Validator.Validate(document.forms_client,2)"><br />
按客户名称查询 <br />
<input type="text" name="client" style="width: 80px" dataType="Require"
	msg="[客户名称]必须填写" /> <input type="submit" value="查询" /></form>
<form class="noPaddingMargin" action="getAFByYWY.action" method="post"
	target="_blank" id="forms_client"
	onSubmit="return Validator.Validate(document.forms_client,2)"><br />
按业务员查询 <br />
<input type="text" name="YWName" style="width: 80px" dataType="Require"
	msg="[业务员名称]必须填写" /> <input type="submit" value="查询" /></form>
<br />
</div>
</body>
</html>