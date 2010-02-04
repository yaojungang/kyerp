<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量收款</title>
<style>
.itemNo {
	line-height: 180%;
	clear: both;
}

.itemMainLeft {
	width: 60%;
	float: left;
	padding: 10px;
}

.itemMainRight {
	width: 30%;
	float: left;
	padding: 10px;
	background-color: #FFFFCC;
}

.submitbutton {
	clear: both;
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
<span class="pageTitle">批量收款</span>
<div class="itemNo">
<h2>根据任务单号批量收款</h2>
</div>
<div style="width: 100%;"><s:form
	action="MoneyIn_BAT_AFNoList_input2" method="post" target="_blank">
	<div>
	<div class="itemMainLeft"><textarea rows="10" cols="50"
		name="AFNoList"></textarea></div>
	<div class="itemMainRight">请在左边的输入框中输入需要生成开票的任务单号列表,单号之间用逗号（,）隔开。
	例如：<br>
	SK20080001,SK20080234</div>
	<div class="submitbutton"><input type="submit" value="提交" /></div>
	</div>
</s:form></div>
<div class="itemNo">
<h2>根据印制单号批量收款</h2>
</div>
<div style="width: 100%;"><s:form
	action="MoneyIn_BAT_YZNoList_input2" method="post" target="_blank">
	<div>
	<div class="itemMainLeft"><textarea rows="10" cols="50"
		name="YZNoList"></textarea></div>
	<div class="itemMainRight">请在左边的输入框中输入需要生成开票的印制单号列表,单号之间用逗号（,）隔开。
	例如新东方：<br>
	TS08330,TS08328,TS08314</div>
	<div class="submitbutton"><input type="submit" value="提交" /></div>
	</div>
</s:form></div>
</html>
