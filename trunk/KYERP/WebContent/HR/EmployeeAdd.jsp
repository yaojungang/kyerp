<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#request['roleList']" name="roleList" id="roleList" />
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

    $("#username").formValidator({onshow:"请输入用户名",onfocus:"用户名至少3个字符,最多20个字符",oncorrect:"该用户名可以注册"}).inputValidator({min:3,max:20,onerror:"你输入的用户名非法,请确认"}).regexValidator({regexp:"username",datatype:"enum",onerror:"用户名格式不正确"})
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
$("#password1").formValidator({onshow:"请输入密码",onfocus:"密码不能为空,且最少输入6位",oncorrect:"密码合法"}).inputValidator({min:6,empty:{leftempty:false,rightempty:false,emptyerror:"密码两边不能有空符号"},onerror:"密码不能为空,请确认"});
$("#password2").formValidator({onshow:"请输入重复密码",onfocus:"两次密码必须一致哦",oncorrect:"密码一致"}).inputValidator({min:6,empty:{leftempty:false,rightempty:false,emptyerror:"重复密码两边不能有空符号"},onerror:"重复密码不能为空,请确认"}).compareValidator({desid:"password1",operateor:"=",onerror:"2次密码不一致,请确认"});
$("#sfzh").formValidator({onshow:"请输入15或18位的身份证",onfocus:"输入15或18位的身份证",oncorrect:"输入正确"}).regexValidator({regexp:"idcard",datatype:"enum",onerror:"你输入的身份证格式不正确"});;
$("#deptId").change(function() {	
	var id=$("#deptId").val();
	$.get("noSkin_getRoleListByDeptId.action?id=" + id, null, function(data) {
		document.getElementById("divRoleSelect").innerHTML = data;
		//var jobList = data.split("|");
		//clearOption("roleList");
		//for(i=0; i<jobList.length;i++)
		//{
		//	job = trim(jobList[i]);
			//alert(jobList[i]);
		//	addOption("roleList",role, role);
		//} 
	});
	});

$("#sfzh").change( function() {
  // 这里可以写些验证代码
  var idCardNo = $("#sfzh").val();
	//alert(getInfo(idCardNo));
  $("#employee_nativeplace").val(getNativeplaceByIdCard(idCardNo));
  $("#employee_rpraddress").val(getNativeplaceByIdCard(idCardNo));
  $("#employee_birthday").val(getBirthdayByIdCard(idCardNo));
  if(getSexByIdCard(idCardNo)==1){
  	  $("#employee_sex1").val(getSexByIdCard(idCardNo));
  }
  if(getSexByIdCard(idCardNo)==0){
  	  $("#employee_sex0").val(getSexByIdCard(idCardNo));
  }
});

});

function Submit1_onclick() {
    return jQuery.formValidator.pageIsValid("1");
};
function addOption(selectID, theText, theValue)
{
    var sel=document.getElementById(selectID);
    var opt=new Option(theValue, theText);
    sel.options.add(opt);
};
function clearOption(selectID)
{
    var obj;
    obj=document.getElementById(selectID);
    var i;
    for(i=1;i<obj.length;i++)
    {
      obj.remove(i);
      i--;
    }
};
</script>
</head>
<body>
<span class="pageTitle">添加人员</span>
<form action='addEmployee_save.action' method="post">
<div id="tabs1">
<div class="menu1box">
<ul id="menu1">
	<li class="hover" onClick="setTab(1,0)"><a href="#">基本信息</a></li>
</ul>
</div>
<div class="main1box">
<div class="main" id="main1">
<ul class="block">
	<li>
	<table width="90%" border="0" align="center" cellpadding="8"
		cellspacing="0" bgcolor="#ffffff">
		<tr>
			<td width="80" align="right" bgcolor="#FFFFFF">姓名</td>
			<td align="left" bgcolor="#FFFFFF"><input type="text"
				style="width: 100px;" name="employee.realname" /></td>
		</tr>
		<tr>
			<td width="80" align="right">身份证号</td>
			<td align="left"><input id="sfzh" type="text"
				name="employee.idcard" style="width: 200px;" />
			<div id="sfzhTip"></div>
			</td>
		</tr>
		<tr>
			<td width="80" align="right">性别</td>
			<td align="left"><input type="radio" id="employee_sex1"
				name="employee.sex" value="1" />男 <input type="radio"
				id="employee_sex0" name="employee.sex" value="0" />女</td>
		</tr>
		<tr>
			<td width="80" align="right">出生日期</td>
			<td align="left"><input type="text" id="employee_birthday"
				name="employee.birthday" style="width: 100px;" /> 格式：yyyy-mm-dd</td>
		</tr>
		<tr>
			<td width="80" align="right">籍贯</td>
			<td align="left"><input id="employee_nativeplace" type="text"
				name="employee.nativeplace" style="width: 100px;" /></td>
		</tr>
		<tr>
		<tr>
			<td width="80" align="right">户口所在地</td>
			<td align="left"><input type="text" id="employee_rpraddress"
				name="employee.rpraddress"
				value="<s:property value="#employee.rpraddress" />" /></td>
		</tr>
		<tr>
			<td width="80" bgcolor="#FFFFFF" align="right">系统用户名</td>
			<td bgcolor="#FFFFFF" align="left"><input type="text"
				style="width: 180px;" name="user.username" id="username" />
			<div id="usernameTip"></div>
			</td>
		</tr>
		<tr>
			<td width="80" bgcolor="#FFFFFF" align="right">登陆密码</td>
			<td bgcolor="#FFFFFF" align="left"><input name="user.password"
				style="width: 180px;" id="password1" type="password" />
			<div id="password1Tip"></div>
			</td>
		</tr>
		<tr>
			<td width="80" bgcolor="#FFFFFF" align="right">确认密码</td>
			<td bgcolor="#FFFFFF" align="left"><input id="password2"
				type="password" style="width: 180px;" />
			<div id="password2Tip"></div>
			</td>
		</tr>
		<tr>
			<td width="80" align="right" bgcolor="#FFFFFF">部门</td>
			<td align="left" bgcolor="#FFFFFF"><select id="deptId"
				name="employee.companyDept.id">
				<option value="--">选择</option>
				<s:iterator value="#request['DeptTree']" status="st">
					<option value="<s:property value="id"/>"><s:property
						value="name" /></option>
				</s:iterator>
			</select>
		</tr>
		<tr>
			<td width="80" align="right" bgcolor="#FFFFFF">岗位</td>
			<td align="left" bgcolor="#FFFFFF">
			<div id="divRoleSelect"></div>
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
