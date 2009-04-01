<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#request['roleList']" name="roleList" id="roleList" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>

<script src="${pageContext.request.contextPath}/Library/js/jquery.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/Library/js/formValidator/style/validator.css"></link>
<script src="${pageContext.request.contextPath}/Library/js/formValidator/formValidator.js" type="text/javascript" charset="UTF-8"></script>
<script src="${pageContext.request.contextPath}/Library/js/formValidator/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
 
<script>
$(document).ready(function(){
	$.formValidator.initConfig({onsuccess:function(){return true;},onerror:function(){alert("具体错误，请看网页上的提示")}});

$("#password0").formValidator({onshow:"请输入原密码",onfocus:"密码不能为空",oncorrect:"密码合法"}).inputValidator({min:1,empty:{leftempty:false,rightempty:false,emptyerror:"密码两边不能有空符号"},onerror:"密码不能为空,请确认"});
$("#password1").formValidator({onshow:"请输入密码",onfocus:"密码不能为空,且最少输入6位",oncorrect:"密码合法"}).inputValidator({min:6,empty:{leftempty:false,rightempty:false,emptyerror:"密码两边不能有空符号"},onerror:"密码不能为空,请确认"});
$("#password2").formValidator({onshow:"请输入重复密码",onfocus:"两次密码必须一致哦",oncorrect:"密码一致"}).inputValidator({min:6,empty:{leftempty:false,rightempty:false,emptyerror:"重复密码两边不能有空符号"},onerror:"重复密码不能为空,请确认"}).compareValidator({desid:"password1",operateor:"=",onerror:"2次密码不一致,请确认"});



});

function Submit1_onclick() {
    return jQuery.formValidator.pageIsValid("1");
};
</script>
</head>
<body>
<span class="pageTitle">修改密码</span>

<form action='ChangeMyPassword_save.action' method="post">
<div id="tabs1">
<div class="menu1box">
<ul id="menu1">
	<li class="hover" onClick="setTab(1,0)"><a href="#">修改密码</a></li>
</ul>
</div>
<div class="main1box">
<div class="main" id="main1">
<ul class="block">
	<li>
	<table width="90%" border="0" align="center" cellpadding="8"
		cellspacing="0" bgcolor="#ffffff">
		<tr>
			<td width="80" bgcolor="#FFFFFF" align="right">原密码</td>
			<td bgcolor="#FFFFFF" align="left"><input name="user0.password" id="password0" type="password" style="width: 100px;" /><div id="password0Tip"></div></td>
		</tr>
		<tr>
			<td width="80" bgcolor="#FFFFFF" align="right">新密码</td>
			<td bgcolor="#FFFFFF" align="left"><input name="userPassword" id="password1" type="password" style="width: 100px;" /><div id="password1Tip"></div></td>
		</tr>
		<tr>
			<td width="80" bgcolor="#FFFFFF" align="right">确认密码</td>
			<td bgcolor="#FFFFFF" align="left"><input id="password2" type="password" style="width: 100px;" /><div id="password2Tip"></div></td>
		</tr>
		<tr>
			<td width="80" align="right" bgcolor="#FFFFFF"></td>
			<td align="left" bgcolor="#FFFFFF"></td>
		</tr>
	</table>
	</li>
</ul>
</div>
</div>
</div>
<center style="padding: 8px"><input type="submit" value="提交" name="submit" onClick="return Submit1_onclick()" /></center>
</form>
</body>
</html>
