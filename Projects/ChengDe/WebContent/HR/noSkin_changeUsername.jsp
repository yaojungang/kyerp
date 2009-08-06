<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加人员-人员管理</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ymPrompt/ymPrompt.js"></script>
<link rel="stylesheet" id='skin' type="text/css"
	href="${pageContext.request.contextPath}/Library/js/ymPrompt/skin/qq/ymPrompt.css" />
<script src="${pageContext.request.contextPath}/Library/js/jquery.js"
	type="text/javascript"></script>
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

    $("#username").formValidator({onshow:"请输入用户名",onfocus:"用户名至少3个字符,最多20个字符",oncorrect:"该用户名可以使用"}).inputValidator({min:3,max:20,onerror:"你输入的用户名非法,请确认"}).regexValidator({regexp:"username",datatype:"enum",onerror:"用户名格式不正确"})
    .ajaxValidator({
    type : "get",
	url : "jsonCheckUsernameForReg.action",
	datatype : "json",
	success : function(data){	
        if( data == "1" )
		{
            return true;
		}
        else
		{
            return false;
		}
	},
	buttons: $("#button"),
	error: function(){alert("服务器没有返回数据，可能服务器忙，请重试");},
	onerror : "该用户名不可用，请更换用户名",
	onwait : "正在对用户名进行合法性校验，请稍候..."
});

});

function Submit1_onclick() {
    return jQuery.formValidator.pageIsValid("1");
};

</script>
</head>
<body>
<form action='noSkin_changeUsername_save.action' method="post">
<input type="hidden" name="user.id" value="<s:property value="#request.user.id"/>" />
<table width="90%" border="0" align="center" cellpadding="8"
		cellspacing="0" bgcolor="#ffffff">
		<tr>
			<td width="80" bgcolor="#FFFFFF" align="right">原用户名</td>
			<td bgcolor="#FFFFFF" align="left"><s:property value="#request.user.username"/>
			</td>
		</tr>
		<tr>
			<td width="80" bgcolor="#FFFFFF" align="right">新用户名</td>
			<td bgcolor="#FFFFFF" align="left"><input type="text"
				style="width: 180px;" name="user.username" id="username" />
			<div id="usernameTip"></div>
			</td>
		</tr>
		
	</table>
	<input type="submit" value="修改" name="submit"
		onClick="return Submit1_onclick()" />
</form>
</body></html>