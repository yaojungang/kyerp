<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>业务室台帐-业务管理</title>
</head>
<body>
<span class="pageTitle">业务室台帐</span>
<br />
请输入任务单号范围:
<br />
<br />
<form name="ywstz" method="post" action="taizhang.action"
	target="_blank">任务单类型: <select name="AFType" id="AFType">
	<option value="SK">受控(SK)</option>
	<option value="LH">零活(LH)</option>
</select> 从 <input name="StartAFNo" type="text" id="StartAFNo" value="20081700">
到 <input name="EndAFNo" type="text" id="EndAFNo" value="20081766">
<input type="submit" name="button" id="button" value="提交"></form>
<br>
<br>
<br>
请输入任务单号范围:
<br>
<s:form action="taizhang_getAFbyAFNoList.action" method="post"
	target="_blank">
	<textarea rows="10" cols="70" name="AFNoList"></textarea>
	<br>

请在输入框中输入需要生成的任务单号列表,单号之间用逗号（,）隔开。 例如：          SK20080001,SK20080234,SK20080235,SK20080236,LH20080234,LH200801122,SK20080145
<br>
	<input type="submit" name="button" id="button" value="提交">
</s:form>
<hr>
<span class="pageTitle">顾客财产接收记录</span>
<br />
请输入任务单号范围:
<br />
<br />
<form name="gkcc" method="post" action="taizhang_gkcc.action"
	target="_blank">任务单类型: <select name="AFType" id="AFType">
	<option value="SK">受控(SK)</option>
	<option value="LH">零活(LH)</option>
</select> 从 <input name="StartAFNo" type="text" id="StartAFNo" value="20081700">
到 <input name="EndAFNo" type="text" id="EndAFNo" value="20081766">
<input type="submit" name="button" id="button" value="提交"></form>
<br>
<br>
<br>
请输入任务单号范围:
<br>
<s:form action="taizhang_gkcc_getAFbyAFNoList.action" method="post"
	target="_blank">
	<textarea rows="10" cols="70" name="AFNoList"></textarea>
	<br>

请在输入框中输入需要生成的任务单号列表,单号之间用逗号（,）隔开。 例如：          SK20080001,SK20080234,SK20080235,SK20080236,LH20080234,LH200801122,SK20080145
<br>
	<input type="submit" name="button" id="button" value="提交">
</s:form>
</body>
</html>
