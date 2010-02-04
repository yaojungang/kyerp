<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统设置</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ymPrompt/ymPrompt.js"></script>
<link rel="stylesheet" id='skin' type="text/css"
	href="${pageContext.request.contextPath}/Library/js/ymPrompt/skin/qq/ymPrompt.css" />
<script src="${pageContext.request.contextPath}/Library/js/jquery.js"
	type="text/javascript"></script>
</head>
<body>
<div class="pim2_secondMenu">
<ul>
	<li><form action="updateEmpNo.action"> <input type="submit" value="更新员工编号"> </form></li>
	<li><a href="${pageContext.request.contextPath}/AF/updateOurBinding.action">更新本厂装订任务单</a></li>
</ul>
</div>

</body>
</html>
