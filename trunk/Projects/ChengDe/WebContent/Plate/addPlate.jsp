<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增印版</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ymPrompt/ymPrompt.js"></script>
<link rel="stylesheet" id='skin' type="text/css"
	href="${pageContext.request.contextPath}/Library/js/ymPrompt/skin/qq/ymPrompt.css" />
<script src="${pageContext.request.contextPath}/Library/js/jquery.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/CursorInsert.js"></script>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/Library/js/formValidator/style/validator.css"></link>
<script
	src="${pageContext.request.contextPath}/Library/js/formValidator/formValidator.js"
	type="text/javascript" charset="UTF-8"></script>
<script
	src="${pageContext.request.contextPath}/Library/js/formValidator/formValidatorRegex.js"
	type="text/javascript" charset="UTF-8"></script>
<script src="${pageContext.request.contextPath}/Library/js/idCard.js"
	type="text/javascript" charset="UTF-8"></script>
<script>
$(document).ready(function(){
	$.formValidator.initConfig({onsuccess:function(){return true;},onerror:function(){alert("具体错误，请看网页上的提示")}});
   $("#plate_inputDate").focus(function(){WdatePicker({skin:'whyGreen',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}})}).formValidator({onshow:"请输入日期",onfocus:"请输入日期，不能全部是0哦",oncorrect:"你输入的日期合法"}).inputValidator({min:"1900-01-01",max:"2100-01-01",type:"date",onerror:"日期必须在\"1900-01-01\"和\"2100-01-01\"之间"});//.defaultPassed();
   $("#plate_lastUseDate").focus(function(){WdatePicker({skin:'whyGreen',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}})}).formValidator({onshow:"请输入日期",onfocus:"请输入日期，不能全部是0哦",oncorrect:"你输入的日期合法"}).inputValidator({min:"1900-01-01",max:"2100-01-01",type:"date",onerror:"日期必须在\"1900-01-01\"和\"2100-01-01\"之间"});//.defaultPassed();
   $("#plate_expDate").focus(function(){WdatePicker({skin:'whyGreen',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}})}).formValidator({onshow:"请输入日期",onfocus:"请输入日期，不能全部是0哦",oncorrect:"你输入的日期合法"}).inputValidator({min:"1900-01-01",max:"2100-01-01",type:"date",onerror:"日期必须在\"1900-01-01\"和\"2100-01-01\"之间"});//.defaultPassed();
 
});
 
function Submit1_onclick() {
    return jQuery.formValidator.pageIsValid("1");
};
</script>
</head>
<body>
<form action="addPlate_save.action" method="post">
<div id="tabs1">
<div class="menu1box">
<ul id="menu1">
	<li class="hover" onClick="setTab(1,0)"><a href="#">增加印版</a></li>
</ul>
</div>
<div class="main1box">
<div class="main" id="main1">
<ul class="block">
	<li>
	<table width="90%" border="0" align="center" cellpadding="8"
		cellspacing="0" bgcolor="#ffffff">
		<tr>
			<td width="80" align="right" bgcolor="#FFFFFF">档案号</td>
			<td align="left" bgcolor="#FFFFFF"><input type="text"
				style="width: 100px;" name="plate.danAnNo" /></td>
		</tr>
		<tr>
			<td width="80" align="right">印版名称</td>
			<td align="left"><input type="text"
				name="plate.pname" style="width: 200px;" />
			</td>
		</tr>
		<tr>
			<td width="80" align="right">入库时间</td>
			<td align="left"><input type="text" id="plate_inputDate"
				name="plate.inputDate" style="width: 150px;" value="<s:date
			name="#request.Today" format="yyyy-MM-dd" nice="false" />" /> <div id="plate_inputDateTip"></div></td>
		</tr>
        <tr>
			<td width="80" align="right">最后使用时间</td>
			<td align="left"><input type="text" id="plate_lastUseDate"
				name="plate.lastUseDate" style="width: 150px;" value="<s:date
			name="#request.Today" format="yyyy-MM-dd" nice="false" />" /><div id="plate_lastUseDateTip"></div></td>
		</tr>
        <tr>
			<td width="80" align="right">过期时间</td>
			<td align="left"><input type="text" id="plate_expDate"
				name="plate.expDate" style="width: 150px;" value="<s:date
			name="#request.NextYearD" format="yyyy-MM-dd" nice="false" />" /><div id="plate_expDateTip"></div></td>
		</tr>
		<tr>
			<td width="80" align="right">版号</td>
			<td align="left"><input type="text"
				name="plate.plateNo" style="width: 100px;" /></td>
		</tr>

		<tr>
			<td width="80" align="right">版长</td>
			<td align="left"><input type="text" id="plate_plateLength"
				name="plate.plateLength" /></td>
		</tr>
		<tr>
			<td width="80" bgcolor="#FFFFFF" align="right">版周</td>
			<td bgcolor="#FFFFFF" align="left"><input type="text" name="plate.plateWidth" />
			</td>
		</tr>
        <tr>
			<td width="80" bgcolor="#FFFFFF" align="right">支数</td>
			<td bgcolor="#FFFFFF" align="left"><input type="text" name="plate.plateAmount" />
			</td>
		</tr>
        <tr>
			<td width="80" bgcolor="#FFFFFF" align="right">共用版</td>
			<td bgcolor="#FFFFFF" align="left"><input type="text" name="plate.plateShare" />
			</td>
		</tr>
        <tr>
			<td width="80" bgcolor="#FFFFFF" align="right">位置编号</td>
			<td bgcolor="#FFFFFF" align="left"><input type="text" name="plate.plateAddress" />
			</td>
		</tr>
		
	</table>
	<input type="submit" value="保存" name="submit"
		onClick="return Submit1_onclick()" /></li>
</ul>
</div>
</div>
</div>
</form>
</body>
</html>
