<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>装订车间首页</title>
<link rel="STYLESHEET" type="text/css"
	href="${pageContext.request.contextPath}/Library/js/ajaxtabscontent/ajaxtabs/ajaxtabs.css">
<script
	src="${pageContext.request.contextPath}/Library/js/ajaxtabscontent/ajaxtabs/ajaxtabs.js"></script>
</head>
<body>
<span class="pageTitle">装订车间首页</span>
<div id="pettabs" class="shadetabs">
<ul>
	<li><a href="AF_getAFByBindingFactory.action" rel="#iframe" class="selected">本厂装订</a></li>
	<li><a href="AF_getTodayAFs.action" rel="#iframe">今日任务单</a></li>
	<li><a href="AF_getNotFinishedAFD.action" rel="#iframe">待完成任务</a></li>
	<li><a href="AF_getFinishedAFD.action" rel="#iframe">已完成任务</a></li>
	<li><a href="AF_getSKAFs.action" rel="#iframe">受控任务单</a></li>
	<li><a href="AF_getLHAFs.action" rel="#iframe">零活任务单</a></li>
	<li><a href="AF_getAllAFs.action" rel="#iframe">全部任务单</a></li>
</ul>
</div>
<div id="petsdivcontainer"
	style="border-top: 1px solid gray; height: 850px; padding: 5px; margin-bottom: 1em">
</div>
<script type="text/javascript">
var mypets=new ddajaxtabs("pettabs", "petsdivcontainer")
mypets.setpersist(false)
mypets.setselectedClassTarget("link")
mypets.init()
</script>
</body>
</html>