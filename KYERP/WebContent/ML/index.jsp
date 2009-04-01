<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车间首页</title>
<link rel="STYLESHEET" type="text/css"
	href="${pageContext.request.contextPath}/Library/js/ajaxtabscontent/ajaxtabs/ajaxtabs.css">
<script
	src="${pageContext.request.contextPath}/Library/js/ajaxtabscontent/ajaxtabs/ajaxtabs.js"></script>
</head>
<body>
车间首页
<div id="pettabs" class="shadetabs">
<ul>
	<li><a href="AFE_getAllAFEs.action" rel="#iframe" class="selected">详细列表</a></li>
	<li><a href="AF_getTodayAFs.action" rel="#iframe">今日新任务单</a></li>
	<li><a href="AF_getSKAFs.action" rel="#iframe">受控任务单</a></li>
	<li><a href="AF_getLHAFs.action" rel="#iframe">零活任务单</a></li>
	<li><a href="AF_getAllAFs.action" rel="#iframe">全部任务单</a></li>
</ul>
</div>
<div id="petsdivcontainer"
	style="border-top: 1px solid gray; height: 550px; padding: 5px; margin-bottom: 1em">
</div>
<script type="text/javascript">
var mypets=new ddajaxtabs("pettabs", "petsdivcontainer")
mypets.setpersist(false)
mypets.setselectedClassTarget("link")
mypets.init()
</script>
</body>
</html>