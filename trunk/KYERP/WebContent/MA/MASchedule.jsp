<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生产进度</title>
<link rel="STYLESHEET" type="text/css"
	href="<%= request.getContextPath() %>/Library/js/ajaxtabscontent/ajaxtabs/ajaxtabs.css">
<script
	src="<%= request.getContextPath() %>/Library/js/ajaxtabscontent/ajaxtabs/ajaxtabs.js"></script>
</head>
<body>
<span class="pageTitle">生产进度</span>
<div id="pettabs" class="shadetabs">
<ul>
	<li><a href="AFE_schedule.action" rel="#iframe" class="selected">生产进度</a></li>
	<li><a href="AFE_schedule_getAFEsByMachine.action?MachineName=ss"
		rel="#iframe">[四色]任务</a></li>
	<li><a href="AFE_schedule_getAFEsByMachine.action?MachineName=sm"
		rel="#iframe">[双面]任务</a></li>
	<li><a href="AFE_schedule_getAFEsByMachine.action?MachineName=05"
		rel="#iframe">[05]任务</a></li>
</ul>
</div>
<div id="petsdivcontainer"
	style="border-top: 1px solid gray; height: 550px; padding: 5px; margin-bottom: 1em">
</div>
<script type="text/javascript">
var mypets=new ddajaxtabs("pettabs", "petsdivcontainer");
mypets.setpersist(false);
mypets.setselectedClassTarget("link");
mypets.init();
</script>
</html>
