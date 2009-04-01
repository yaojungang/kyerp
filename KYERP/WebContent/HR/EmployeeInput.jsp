<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#request['Employee']" name="employee" id="employee" />
<s:set value="#request['roleList']" name="roleList" id="roleList" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="#request['pageTitle']" />-编辑人员</title>
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
	$.formValidator.initConfig({validatorgroup:"1",onsuccess:function(){return true;},onerror:function(){alert("具体错误，请看网页上的提示")}});
    $.formValidator.initConfig({validatorgroup:"2",onsuccess:function(){return true;},onerror:function(){alert("具体错误，请看网页上的提示")}});
    $.formValidator.initConfig({validatorgroup:"3",onsuccess:function(){return true;},onerror:function(){alert("具体错误，请看网页上的提示")}});
    $.formValidator.initConfig({validatorgroup:"4",onsuccess:function(){return true;},onerror:function(){return true;}});
	
    $("#sfzh").formValidator({validatorgroup:"1",onshow:"请输入15或18位的身份证",onfocus:"输入15或18位的身份证",oncorrect:"输入正确"}).regexValidator({regexp:"idcard",datatype:"enum",onerror:"你输入的身份证格式不正确"});;
    $("#employee_birthday").focus(function(){WdatePicker({skin:'whyGreen',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}})}).formValidator({validatorgroup:"1",onshow:"请输入的出生日期",onfocus:"请输入的出生日期，不能全部是0哦",oncorrect:"你输入的日期合法"}).inputValidator({min:"1900-01-01",max:"2100-01-01",type:"date",onerror:"日期必须在\"1900-01-01\"和\"2100-01-01\"之间"});
    $("#employee_participateDate").focus(function(){WdatePicker({skin:'whyGreen',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}})}).formValidator({validatorgroup:"1",onshow:"请输入进单位工作时间",onfocus:"请输入日期",oncorrect:"你输入的日期合法"}).inputValidator({min:"1980-01-01",max:"2100-01-01",type:"date",onerror:"日期必须在\"1980-01-01\"和\"2100-01-01\"之间"});
    $("#employee_workTel").formValidator({validatorgroup:"2",empty:true,onshow:"请输入你的电话",onfocus:"格式例如：010-62781234",oncorrect:"谢谢你的合作",onempty:"你真的不想留电话了吗？"}).regexValidator({regexp:"^[[0-9]{3}-|\[0-9]{4}-]?([0-9]{8}|[0-9]{7})?$",onerror:"你输入的电话格式不正确"});
    $("#employee_mobile").formValidator({validatorgroup:"2",empty:true,onshow:"请输入你的手机号码",onfocus:"你要是输入了，必须输入正确",oncorrect:"谢谢你的合作",onempty:"你真的不想留手机号码啊？"}).inputValidator({min:11,max:11,onerror:"手机号码必须是11位的,请确认"}).regexValidator({regexp:"mobile",datatype:"enum",onerror:"你输入的手机号码格式不正确"});;
    $("#employeez_tel").formValidator({validatorgroup:"2",empty:true,onshow:"请输入你现住址的电话",onfocus:"格式例如：010-62781234",oncorrect:"谢谢你的合作",onempty:"你真的不想留电话了吗？"}).regexValidator({regexp:"^[[0-9]{3}-|\[0-9]{4}-]?([0-9]{8}|[0-9]{7})?$",onerror:"你输入的电话格式不正确"});
    $("#employee_rprtel").formValidator({validatorgroup:"2",empty:true,onshow:"请输入能联系你家庭成员的电话，以备紧急情况下使用",onfocus:"格式例如：010-62781234",oncorrect:"谢谢你的合作",onempty:"你真的不想留电话了吗？"}).regexValidator({regexp:"^[[0-9]{3}-|\[0-9]{4}-]?([0-9]{8}|[0-9]{7})?$",onerror:"你输入的电话格式不正确"});
    $("#employee_graduteDate").focus(function(){WdatePicker({skin:'whyGreen',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}})}).formValidator({validatorgroup:"2",empty:true,onshow:"请输入毕业时间",onfocus:"请输入日期",oncorrect:"你输入的日期合法"}).inputValidator({min:"1980-01-01",max:"2100-01-01",type:"date",onerror:"日期必须在\"1980-01-01\"和\"2100-01-01\"之间"});
    $("#employee_zhiChenDate").focus(function(){WdatePicker({skin:'whyGreen',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}})}).formValidator({validatorgroup:"2",empty:true,onshow:"请输入职称取得时间",onfocus:"请输入日期",oncorrect:"你输入的日期合法"}).inputValidator({min:"1980-01-01",max:"2100-01-01",type:"date",onerror:"日期必须在\"1980-01-01\"和\"2100-01-01\"之间"});
    $("#employee_contractSignDate").focus(function(){WdatePicker({skin:'whyGreen',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}})}).formValidator({validatorgroup:"2",empty:true,onshow:"请输入合同签订日期",onfocus:"请输入日期",oncorrect:"你输入的日期合法"}).inputValidator({min:"1980-01-01",max:"2100-01-01",type:"date",onerror:"日期必须在\"1980-01-01\"和\"2100-01-01\"之间"});
    $("#employee_contractMatureDate").focus(function(){WdatePicker({skin:'whyGreen',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}})}).formValidator({validatorgroup:"2",empty:true,onshow:"请输合同到期日期",onfocus:"请输入日期",oncorrect:"你输入的日期合法"}).inputValidator({min:"1980-01-01",max:"2100-01-01",type:"date",onerror:"日期必须在\"1980-01-01\"和\"2100-01-01\"之间"});

    $("#employee_email").formValidator({validatorgroup:"3",empty:true,onshow:"请输入邮箱",onfocus:"邮箱6-100个字符",oncorrect:"恭喜你,你输对了"}).inputValidator({min:6,max:100,onerror:"你输入的邮箱长度非法,请确认"}).regexValidator({regexp:"^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$",onerror:"你输入的邮箱格式不正确"});
    $("#employee_qq").formValidator({validatorgroup:"3",empty:true,onshow:"请输入QQ号",onfocus:"输入QQ号",oncorrect:"输入正确"}).regexValidator({regexp:"qq",datatype:"enum",onerror:"你输入的QQ号格式不正确"});;
    $("#employee_msn").formValidator({validatorgroup:"3",empty:true,onshow:"请输入MSN",onfocus:"MSN 6-100个字符",oncorrect:"恭喜你,你输对了"}).inputValidator({min:6,max:100,onerror:"你输入的MSN长度非法,请确认"}).regexValidator({regexp:"^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$",onerror:"你输入的MSN格式不正确"});
	$("#deptId").change(function() {
		var id=$("#deptId").val();
		$.get("noSkin_getRoleListByDeptId.action?id=" + id, null, function(data) {
			document.getElementById("divRoleSelect").innerHTML = data;
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
		  	  $("#employee_sex1").attr("checked","checked");
		  	  $("#employee_sex0").attr("checked","");
		  }
		  if(getSexByIdCard(idCardNo)==0){
		  	  $("#employee_sex0").val(getSexByIdCard(idCardNo));
		  	  $("#employee_sex0").attr("checked","checked");
		  	  $("#employee_sex1").attr("checked","");
		  }
		});

});

function Submit1_onclick() {
    return jQuery.formValidator.pageIsValid("1");
}
function Submit2_onclick() {
    return jQuery.formValidator.pageIsValid("2");
}
function Submit3_onclick() {
    return jQuery.formValidator.pageIsValid("3");
}
function Submit4_onclick() {
    return jQuery.formValidator.pageIsValid("4");
}
function addOption(selectID, theText, theValue)
{
    var sel=document.getElementById(selectID);
    var opt=new Option(theValue, theText);
    sel.options.add(opt);
}
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
}
</script>
</head>
<body>
<span class="pageTitle"><s:property value="#request['pageTitle']" /></span>
<form action='editEmployee_save.action' method="post" id="form1">
<s:if test="#employee.id>0">
	<s:hidden name="employee.id" value="%{#employee.id}" />
</s:if>
<div id="tabs1">
<div class="menu1box">
<ul id="menu1">
	<li class="hover" onClick="setTab(1,0)"><a href="#">基本资料</a></li>
	<li onClick="setTab(1,1)"><a href="#">详细资料</a></li>
	<li onClick="setTab(1,2)"><a href="#">个人简历</a></li>
	<li onClick="setTab(1,3)"><a href="#">社会关系</a></li>
	<li onClick="setTab(1,4)"><a href="#">个性化资料</a></li>
	<li onClick="setTab(1,5)"><a href="#">系统选项</a></li>
</ul>
</div>
<div class="main1box">
<div class="main" id="main1">
<ul class="block">
	<li><s:if test="#employee.user.id>0">
		<s:hidden name="user.id" value="%{#employee.user.id}" />
	</s:if>
	<table width="90%" border="0" align="center" cellpadding="8"
		cellspacing="0">
		<tr>
			<td width="80" align="right">姓名</td>
			<td align="left"><input type="text" name="employee.realname"
				value="<s:property value="#employee.realname" />" /></td>
		</tr>
		<tr>
			<td width="80" align="right">人员状态</td>
			<td align="left"><select name="employee.workStatus">
				<option value="0"
					<s:if test="#employee.workStatus == 0">selected="selected"</s:if>>在岗</option>
				<option value="1"
					<s:if test="#employee.workStatus == 1">selected="selected"</s:if>>试用期</option>
				<option value="2"
					<s:if test="#employee.workStatus == 2">selected="selected"</s:if>>申请离职</option>
				<option value="100"
					<s:if test="#employee.workStatus == 100">selected="selected"</s:if>>退休</option>
				<option value="101" <s:if test="#employee.workStatus == 101">selected="selected"</s:if>>离职</option>
				<option value="102" <s:if test="#employee.workStatus == 102">selected="selected"</s:if>>病休</option>
			</select></td>
		</tr>
		<tr>
			<td width="80" align="right">个人资料状态</td>
			<td align="left"><select name="employee.infoStatus">
				<option value="0"
					<s:if test="#employee.infoStatus == 0">selected="selected"</s:if>>锁定</option>
				<option value="1"
					<s:if test="#employee.infoStatus == 1">selected="selected"</s:if>>本人可修改</option>
			</select></td>
		</tr>
		<tr>
			<td width="80" align="right">员工编号</td>
			<td align="left"><s:property value="#employee.empNo" /></td>
		</tr>
		<tr>
			<td width="80" align="right">身份证号</td>
			<td align="left"><input id="sfzh" type="text"
				name="employee.idcard"
				value="<s:property value="#employee.idcard" />" />
			<div id="sfzhTip"></div>
			</td>
		</tr>
		<tr>
			<td width="80" align="right">性别</td>
			<td align="left"><input type="radio" id="employee_sex1" name="employee.sex" value="1" <s:if test="#employee.sex==1" >checked="checked"</s:if> />
            男 <input type="radio" id="employee_sex0" name="employee.sex" value="0" <s:if test="#employee.sex==0" >checked="checked"</s:if> />女</td>
		</tr>
		<tr>
			<td width="80" align="right">出生日期</td>
			<td align="left"><input type="text" id="employee_birthday" name="employee.birthday" value='<s:date format="yyyy-MM-dd" nice="false" name="#employee.birthday" />' />
			格式：yyyy-mm-dd<div id="employee_birthdayTip"></div></td>
		</tr>
		<tr>
			<td width="80" align="right">籍贯</td>
			<td align="left"><input type="text" id="employee_nativeplace"
				name="employee.nativeplace"
				value="<s:property value="#employee.nativeplace" />" /></td>
		</tr>
		<tr>
			<td width="80" align="right">户口所在地</td>
			<td align="left"><input type="text" id="employee_rpraddress"
				name="employee.rpraddress"
				value="<s:property value="#employee.rpraddress" />" /></td>
		</tr>
		<tr>
			<td width="80" align="right">户口类型</td>
			<td align="left">
				<select name="employee.rprtype" id="employee_rprtype">
				<option value="1"
					<s:if test="#employee.rprtype == 1">selected="selected"</s:if>>城镇户口</option>
				<option value="0"
					<s:if test="#employee.rprtype == 0">selected="selected"</s:if>>农业户口</option>
			</select>
				</td>
		</tr>
		<tr>
			<td width="80" align="right">进厂时间</td>
			<td align="left"><input type="text" id="employee_participateDate" name="employee.participateDate" value='<s:date format="yyyy-MM-dd" nice="false" name="#employee.participateDate" />' />
			格式：yyyy-mm-dd<div id="employee_participateDateTip"></div></td>
		</tr>
		<tr>
			<td width="80" align="right">所在部门</td>
			<td align="left"><select id="deptId"
				name="employee.companyDept.id">
				<s:iterator value="#request['DeptTree']" status="st">
					<option value="<s:property value="id"/>"
						<s:if test="#employee.companyDept.id == id" >selected="selected"</s:if>><s:property
						value="name" /></option>
				</s:iterator>
			</select></td>
		</tr>
		<tr>
			<td width="80" align="right" bgcolor="#FFFFFF">岗位</td>
			<td align="left" bgcolor="#FFFFFF">
			<div id="divRoleSelect">
			<select name="roles[0].id">
				<s:iterator value="#request['roleList']" status="st">
					<option value="<s:property value="id"/>"
						<s:if test="#request.role0Id == id" >selected="selected"</s:if>><s:property
						value="rname" /></option>
				</s:iterator>
			</select></div>
			</td>
		</tr>
	</table>
	<input type="submit" value="保存" name="submit" id="Submit1"
		onClick="return Submit1_onclick()" /></li>
</ul>
<ul>
	<li>
	<table width="90%" border="0" align="center" cellpadding="8"
		cellspacing="0">
		<tr>
			<td width="80" align="right">民族</td>
			<td align="left"><input type="text" id="employee_nation"
				name="employee.nation"
				value="<s:property value="#employee.nation" />" /> <select
				id="employee_nation00"
				onChange="(document.getElementById('employee_nation').value=this.options[this.selectedIndex].value)">
				<option value="">选择</option>
				<option value="汉族">汉族</option>
				<option value="回族">回族</option>
			</select></td>
		</tr>
		<tr>
			<td width="80" align="right">婚姻状况</td>
			<td align="left"><select name="employee.weeding">
				<option value="未填">未填</option>
				<option value="未婚"
					<s:if test="#employee.weeding.equals('未婚')">selected="selected"</s:if>>未婚</option>
				<option value="已婚"
					<s:if test="#employee.weeding.equals('已婚')">selected="selected"</s:if>>已婚</option>
				<option value="离异"
					<s:if test="#employee.weeding.equals('离异')">selected="selected"</s:if>>离异</option>
				<option value="丧偶"
					<s:if test="#employee.weeding.equals('丧偶')">selected="selected"</s:if>>丧偶</option>
			</select></td>
		</tr>
		<tr>
			<td width="80" align="right">健康状况</td>
			<td align="left"><input type="text" name="employee.health" id="employee_health" value="<s:property value="#employee.health" />" />
<select id="employee_health00"  onChange="document.getElementById('employee_health').value=document.getElementById('employee_health').value+this.options[this.selectedIndex].value">
				<option value="">选择</option>
				<option value="良好">良好</option>
				<option value="视力残疾">视力残疾</option>
				<option value="听力残疾">听力残疾</option>
				<option value="言语残疾">言语残疾</option>
				<option value="肢体残疾">肢体残疾</option>
				<option value="智力残疾">智力残疾</option>
				<option value="精神残疾">精神残疾</option>
			</select>
			<br />非残疾人，请填写良好；需要填写多项内容时， 每项之间用,隔开
			</td>
		</tr>
		<tr>
			<td width="80" align="right">政治面貌</td>
			<td align="left"><input type="text" id="employee_polity"
				name="employee.polity"
				value="<s:property value="#employee.polity" />" /> <select
				id="employee_polity00"
				onChange="(document.getElementById('employee_polity').value=this.options[this.selectedIndex].value)">
				<option value="">选择</option>
				<option value="中共党员">中共党员</option>
				<option value="预备党员">预备党员</option>
				<option value="共青团员">共青团员</option>
				<option value="民主党派">民主党派</option>
				<option value="群众">群众</option>
			</select></td>
		</tr>
        <tr>
			<td width="80" align="right">毕业院校</td>
			<td align="left"><input type="text" id="employee_school"
				name="employee.school"
				value="<s:property value="#employee.school" />" /> <select
				id="employee_school00"
				onChange="(document.getElementById('employee_school').value=this.options[this.selectedIndex].value)">
				<option value="">选择</option>
				<option value="北京印刷学院">北京印刷学院</option>
				<option value="清华大学">清华大学</option>
			</select></td>
		</tr>
        <tr>
			<td width="80" align="right">毕业时间</td>
			<td align="left"><input type="text"
				id="employee_graduteDate" name="employee.graduteDate" value='<s:date format="yyyy-MM-dd" nice="false" name="#employee.graduteDate" />' />
			格式：yyyy-mm-dd<div id="employee_graduteDateTip"></div></td>
		</tr>
		<tr>
			<td width="80" align="right">文化程度</td>
			<td align="left"><input type="text" id="employee_degree"
				name="employee.degree"
				value="<s:property value="#employee.degree" />" /> <select
				id="employee_degree00"
				onChange="(document.getElementById('employee_degree').value=this.options[this.selectedIndex].value)">
				<option value="">选择</option>
				<option value="大本">大本</option>
				<option value="大专">大专</option>
				<option value="中专">中专</option>
				<option value="职高">职高</option>
				<option value="高中">高中</option>
				<option value="初中">初中</option>
				<option value="小学">小学</option>
				<option value="硕士">硕士</option>
				<option value="博士">博士</option>
				<option value="博士后">博士后</option>
			</select></td>
		</tr>
		<tr>
			<td width="80" align="right">专业</td>
			<td align="left"><input type="text" id="employee_speciality"
				name="employee.speciality"
				value="<s:property value="#employee.speciality" />" /> <select
				id="employee_speciality00"
				onChange="(document.getElementById('employee_speciality').value=this.options[this.selectedIndex].value)">
				<option value="">选择</option>
				<option value="印刷技术">印刷技术</option>
				<option value="印刷工程">印刷工程</option>
				<option value="排版">排版</option>
				<option value="制版">制版</option>
				<option value="印刷">印刷</option>
				<option value="装订">装订</option>
				<option value="财务">财务</option>
			</select></td>
		</tr>
        <tr>
			<td width="80" align="right">特长爱好</td>
			<td align="left"><input type="text" id="employee_interest"
				name="employee.interest"
				value="<s:property value="#employee.interest" />" /> <select
				id="employee_interest00"
				onChange="(document.getElementById('employee_interest').value=this.options[this.selectedIndex].value)">
				<option value="">选择</option>
				<option value="计算机">计算机</option>
				<option value="文艺">文艺</option>
				<option value="书法">书法</option>
				<option value="乒乓球">乒乓球</option>
				<option value="篮球">篮球</option>
				<option value="足球">足球</option>
			</select></td>
		</tr>
		<tr>
			<td width="80" align="right">办公电话</td>
			<td align="left"><input type="text" id="employee_workTel" name="employee.workTel" value='<s:property value="#employee.workTel" />' /><div id="employee_workTelTip"></div></td>
		</tr>
		<tr>
			<td width="80" align="right">手机</td>
			<td align="left"><input type="text" id="employee_mobile" name="employee.mobile"
				value="<s:property value="#employee.mobile" />" /><div id="employee_mobileTip"></div></td>
		</tr>
		<tr>
			<td width="80" align="right">现住址</td>
			<td align="left"><input type="text" id="employee_address" style="width:250px;" name="employee.address"
				value="<s:property value="#employee.address" />" /><div id="employee_addressTip"></div></td>
		</tr>
		<tr>
			<td width="80" align="right">住宅电话</td>
			<td align="left"><input type="text" id="employeez_tel" name="employee.tel"
				value="<s:property value="#employee.tel" />" /><div id="employeez_telTip"></div></td>
		</tr>
		<tr>
			<td width="80" align="right">家庭电话</td>
			<td align="left"><input type="text" id="employee_rprtel" name="employee.rprtel"
				value="<s:property value="#employee.rprtel" />" /><div id="employee_rprtelTip"></div></td>
		</tr>
		
		<tr>
			<td width="80" align="right">职称</td>
			<td align="left">
			<select name="employee.zhiChen">
				<option value="-">--</option>
				<option value="初级工" <s:if test="#employee.zhiChen.equals('初级工')">selected="selected"</s:if>>初级工</option>
				<option value="中级工" <s:if test="#employee.zhiChen.equals('中级工')">selected="selected"</s:if>>中级工 </option>
				<option value="搞级工" <s:if test="#employee.zhiChen.equals('高级工')">selected="selected"</s:if>>高级工 </option>
				<option value="技师" <s:if test="#employee.zhiChen.equals('技师')">selected="selected"</s:if>>技师 </option>
				<option value="会计员" <s:if test="#employee.zhiChen.equals('会计员')">selected="selected"</s:if>>会计员 </option>
				<option value="助理会计师" <s:if test="#employee.zhiChen.equals('助理会计师')">selected="selected"</s:if>>助理会计师 </option>
				<option value="会计师" <s:if test="#employee.zhiChen.equals('会计师')">selected="selected"</s:if>>会计师 </option>
				<option value="助理工程师" <s:if test="#employee.zhiChen.equals('助理工程师')">selected="selected"</s:if>>助理工程师</option>
				<option value="初级工程师" <s:if test="#employee.zhiChen.equals('初级工程师')">selected="selected"</s:if>>初级工程师</option>
				<option value="工程师" <s:if test="#employee.zhiChen.equals('工程师')">selected="selected"</s:if>>工程师</option>
				<option value="高级工程师" <s:if test="#employee.zhiChen.equals('高级工程师')">selected="selected"</s:if>>高级工程师</option>
			</select>
			</td>
		</tr>
		<tr>
			<td width="80" align="right">职称取得时间</td>
			<td align="left"><input type="text" id="employee_zhiChenDate" name="employee.zhiChenDate" value='<s:date format="yyyy-MM-dd" nice="false" name="#employee.zhiChenDate" />' />
			格式：yyyy-mm-dd<div id="employee_zhiChenDateTip"></div>
			</td>
		</tr>
		<tr>
			<td width="80" align="right">编制</td>
			<td align="left">
			<select name="employee.bianZhi">
				<option value="合同" <s:if test="#employee.bianZhi.equals('合同')">selected="selected"</s:if>>合同</option>
				<option value="全民" <s:if test="#employee.bianZhi.equals('全民')">selected="selected"</s:if>>全民</option>
				<option value="集体" <s:if test="#employee.bianZhi.equals('集体')">selected="selected"</s:if>>集体</option>
				<option value="校企编" <s:if test="#employee.bianZhi.equals('校企编')">selected="selected"</s:if>>校企编</option>
				<option value="海淀企编" <s:if test="#employee.bianZhi.equals('海淀企编')">selected="selected"</s:if>>海淀企编</option>
				<option value="农转" <s:if test="#employee.bianZhi.equals('农转')">selected="selected"</s:if>>农转</option>
				<option value="校管合同制" <s:if test="#employee.bianZhi.equals('校管合同制')">selected="selected"</s:if>>校管合同制</option>
			</select>
			</td>
		</tr>
		<tr>
			<td width="80" align="right">残疾证编号</td>
			<td align="left"><input type="text"
				name="employee.disabilityIdcard"
				value="<s:property value="#employee.disabilityIdcard" />" />
			仅残疾员工填写</td>
		</tr>
		<tr>
			<td width="80" align="right">合同签订日期</td>
			<td align="left"><input type="text" id="employee_contractSignDate" name="employee.contractSignDate" value='<s:date format="yyyy-MM-dd" nice="false" name="#employee.contractSignDate" />' />
			格式：yyyy-mm-dd<div id="employee_contractSignDateTip"></div></td>
		</tr>
		<tr>
			<td width="80" align="right">合同到期日期</td>
			<td align="left"><input type="text" id="employee_contractMatureDate" name="employee.contractMatureDate" value='<s:date format="yyyy-MM-dd" nice="false" name="#employee.contractMatureDate" />' />
			格式：yyyy-mm-dd<div id="employee_contractMatureDateTip"></div></td>
		</tr>
		<tr>
			<td width="80" align="right">劳动合同签订备注</td>
			<td align="left"><textarea name="employee.contractRemark"
				style="width: 300px; height: 80px;"><s:property
				value="#employee.contractRemark" /></textarea></td>
		</tr>
        <tr>
			<td width="80" align="right">社保缴纳情况</td>
			<td align="left">
			<INPUT id="cy_yl" type=button value="养老" onclick='insertAtCaret(this.form.employee_insurance,this.value+"；");' />
			<INPUT id="cy_sy" type=button value="失业" onclick='insertAtCaret(this.form.employee_insurance,this.value+"；");' />
			<INPUT id="cy_gs" type=button value="工伤" onclick='insertAtCaret(this.form.employee_insurance,this.value+"；");' />
			<INPUT id="cy_yiliao" type=button value="医疗" onclick='insertAtCaret(this.form.employee_insurance,this.value+"；");' />
			<INPUT id="cy_sy" type=button value="生育" onclick='insertAtCaret(this.form.employee_insurance,this.value+"；");' />
			<br /><textarea id="employee_insurance" name="employee.insurance"
				style="width: 300px; height: 40px;" onClick="setCaret(this);"
					onkeyup="setCaret(this);" ><s:property
				value="#employee.insurance"/></textarea></td>
		</tr>
		<tr>
			<td width="80" align="right">奖惩记录</td>
			<td align="left"><textarea name="employee.rewardsAndPunishiment"
				style="width: 300px; height: 120px;"><s:property
				value="#employee.rewardsAndPunishiment" /></textarea></td>
		</tr>
		<tr>
			<td width="80" align="right">工作或岗位变动情况</td>
			<td align="left"><textarea name="employee.jobChanges"
				style="width: 300px; height: 80px;"><s:property
				value="#employee.jobChanges" /></textarea></td>
		</tr>
		
		<tr>
			<td width="80" align="right">培训记录</td>
			<td align="left"><textarea name="employee.workTrain"
				style="width: 300px; height: 80px;"><s:property
				value="#employee.workTrain" /></textarea></td>
		</tr>
		<tr>
			<td width="80" align="right">担保记录</td>
			<td align="left"><textarea name="employee.recognizor"
				style="width: 300px; height: 80px;"><s:property
				value="#employee.recognizor" /></textarea></td>
		</tr>
		<tr>
			<td width="80" align="right">体检记录</td>
			<td align="left"><textarea name="employee.physical"
				style="width: 300px; height: 80px;"><s:property
				value="#employee.physical" /></textarea></td>
		</tr>
		<tr>
			<td width="80" align="right">备注</td>
			<td align="left"><textarea name="employee.remark"
				style="width: 300px; height: 150px;"><s:property
				value="#employee.remark" /></textarea></td>
		</tr>
	</table>
	<br>
	<input type="submit" value="保存" name="submit" id="Submit2"
		onClick="return Submit2_onclick()" /></li>
</ul>
<ul>
	<li><s:iterator value="#employee.resume" status="stat">
		<input type="hidden"
			name="resumes[<s:property value="#stat.index" />].id"
			value="<s:property value="id"/>" />
		<input type="hidden"
			name="resumes[<s:property value="#stat.index" />].employee.id"
			value="<s:property value="#employee.id"/>" />
		<input type="hidden"
			name="resumes[<s:property value="#stat.index" />].resumeOrder"
			value="<s:property value="resumeOrder"/>" />
		<table width="90%" border="0" align="center" cellpadding="8"
			cellspacing="0">
			<tr>
				<td align="right" bgcolor="#B5DAFF">个人简历</td>
				<td align="left" bgcolor="#B5DAFF"></td>
			</tr>
			<tr>
				<td width="80" align="right">单位名称</td>
				<td align="left"><input type="text" style="width: 300px;"
					name="resumes[<s:property value="#stat.index" />].company"
					value="<s:property value="company" />" /> 清空单位名称将删除本条记录</td>
			</tr>
			<tr>
				<td width="80" align="right">职务</td>
				<td align="left"><input
					name="resumes[<s:property value="#stat.index" />].job" type="text"
					style="width: 150px;" value="<s:property value="job" />" /></td>
			</tr>
			<tr>
				<td width="80" align="right">开始时间</td>
				<td align="left"><input name='resumes[<s:property value="#stat.index" />].startTime' type="text" style="width: 100px;" value='<s:date format="yyyy-MM-dd" nice="false" name="startTime" />' />
				日期格式：yyyy-mm-dd</td>
			</tr>
			<tr>
				<td width="80" align="right">结束时间</td>
				<td align="left"><input
					name="resumes[<s:property value="#stat.index" />].endTime"
					type="text" style="width: 100px;"
					value="<s:date format="yyyy-MM-dd" nice="false" name="endTime" />" />
				日期格式：yyyy-mm-dd</td>
			</tr>
		</table>
		<br />
	</s:iterator> <input type="submit" value="保存" name="submit" /></li>
</ul>
<ul>
	<li><s:iterator value="#employee.family" status="stat">
		<input type="hidden"
			name="familys[<s:property value="#stat.index" />].id"
			value="<s:property value="id"/>" />
		<input type="hidden"
			name="familys[<s:property value="#stat.index" />].employee.id"
			value="<s:property value="#employee.id"/>" />
		<input type="hidden"
			name="familys[<s:property value="#stat.index" />].familyOrder"
			value="familyOrder" />
		<table width="90%" border="0" align="center" cellpadding="8"
			cellspacing="0">
			<tr>
				<td align="right" bgcolor="#B5DAFF">家庭成员</td>
				<td align="left" bgcolor="#B5DAFF"></td>
			</tr>
			<tr>
				<td width="80" align="right">姓名</td>
				<td align="left"><input type="text" style="width: 100px;"
					name="familys[<s:property value="#stat.index" />].name"
					value="<s:property value="name" />" /> 清空将删除本条记录</td>
			</tr>
			<tr>
				<td width="80" align="right">关系</td>
				<td align="left"><input type="text" style="width: 100px;"
					id="familys[<s:property value="#stat.index" />]_relation"
					name="familys[<s:property value="#stat.index" />].relation"
					value="<s:property value="relation" />" /> <select
					id="familys[<s:property value="#stat.index" />]_relation00"
					onChange="(document.getElementById('familys[<s:property value="#stat.index" />]_relation').value=this.options[this.selectedIndex].value)">
					<option value="">选择</option>
					<option value="配偶">配偶</option>
					<option value="父子">父子</option>
					<option value="父女">父女</option>
					<option value="母子">母子</option>
					<option value="母女">母女</option>
					<option value="哥哥">哥哥</option>
					<option value="弟弟">弟弟</option>
					<option value="姐姐">姐姐</option>
					<option value="妹妹">妹妹</option>
					<option value="儿子">儿子</option>
					<option value="女儿">女儿</option>
				</select></td>
			</tr>
			<tr>
				<td width="80" align="right">政治面貌</td>
				<td align="left"><input type="text" style="width: 100px;"
					id="familys[<s:property value="#stat.index" />]_polity"
					name="familys[<s:property value="#stat.index" />].polity"
					value="<s:property value="polity" />" /> <select
					id="familys[<s:property value="#stat.index" />]_polity00"
					onChange="(document.getElementById('familys[<s:property value="#stat.index" />]_polity').value=this.options[this.selectedIndex].value)">
					<option value="">选择</option>
					<option value="中共党员">中共党员</option>
					<option value="共青团员">共青团员</option>
					<option value="民主党派">民主党派</option>
					<option value="群众">群众</option>
				</select></td>
			</tr>
			<tr>
				<td width="80" align="right">工作单位</td>
				<td align="left"><input type="text" style="width: 300px;"
					name="familys[<s:property value="#stat.index" />].company"
					value="<s:property value="company" />" /></td>
			</tr>
			<tr>
				<td width="80" align="right">职务</td>
				<td align="left"><input type="text" style="width: 150px;"
					name="familys[<s:property value="#stat.index" />].job"
					value="<s:property value="job" />" /></td>
			</tr>
		</table>
		<br />
	</s:iterator> <input type="submit" value="保存" name="submit" /></li>
</ul>
<ul>
	<li>
	<table width="90%" border="0" align="center" cellpadding="8"
		cellspacing="0">
		<tr>
			<td width="80" align="right">照片</td>
			<td align="left"><s:if test="#employee.photo != null">
				<img style="width: 96px;"
					src="/uploadData/<s:property value="#employee.photo" />?<s:property value="#request['t']" />" />
				<input type="hidden" name="employee.photo"
					value="<s:property value="#employee.photo" />" />
				<input type="button" value="修改"
					onClick="ymPrompt.win(document.getElementById('popWindowPhoto').innerHTML,450,180,'上传照片')" />
			</s:if> <s:else>
				<input type="button" value="上传照片"
					onClick="ymPrompt.win(document.getElementById('popWindowPhoto').innerHTML,450,180,'上传照片')" />
			</s:else></td>
		</tr>
		<tr>
			<td width="80" align="right">Email</td>
			<td align="left"><input type="text" id="employee_email" style="width:250px;" name="employee.email"
				value="<s:property value="#employee.email" />" /><div id="employee_emailTip"></div></td>
		</tr>
		<tr>
			<td width="80" align="right">QQ</td>
			<td align="left"><input type="text" id="employee_qq" name="employee.qq"
				value="<s:property value="#employee.qq" />" /><div id="employee_qqTip"></div></td>
		</tr>
		<tr>
			<td width="80" align="right">MSN</td>
			<td align="left"><input type="text" id="employee_msn" style="width:250px;" name="employee.msn"
				value="<s:property value="#employee.msn" />" /><div id="employee_msnTip"></div></td>
		</tr>
		<tr>
			<td width="80" align="right">自我介绍</td>
			<td align="left"><textarea name="employee.selfDesc"
				style="width: 350px; height: 200px;"><s:property
				value="#employee.selfDesc" /></textarea></td>
		</tr>
	</table>
	<br />
	<input type="submit" value="保存" name="submit" id="Submit3"
		onClick="return Submit3_onclick()" /></li>
</ul>
<ul>
	<li>
	<table width="90%" border="0" align="center" cellpadding="8"
		cellspacing="0">
		<tr>
			<td width="80" align="right">用户名</td>
			<td align="left"><s:property value="#employee.user.username" />
			</td>
		</tr>
		<tr>
			<td width="80" align="right">密码</td>
			<td align="left"><input type="button" value="修改"
				onClick="ymPrompt.win(document.getElementById('popWindowChangeUserPassword').innerHTML,450,280,'修改密码')" /></td>
		</tr>
		<tr>
			<td width="80" align="right">用户类型</td>
			<td align="left"><select name="user.userType">
				<option value="user"
					<s:if test="#employee.user.userType.equals('user')">selected="selected"</s:if>>普通用户</option>
				<option value="Admin"
					<s:if test="#employee.user.userType.equals('Admin')">selected="selected"</s:if>>管理员</option>
				<option value="Locked"
					<s:if test="#employee.user.userType.equals('Locked')">selected="selected"</s:if>>锁定</option>
			</select></td>
		</tr>
		<tr>
			<td width="80" align="right">用户首页</td>
			<td align="left"><input type="text" style="width: 250px;"
				name="user.url" value="<s:property value="#employee.user.url" />" /></td>
		</tr>
	</table>
	<input type="submit" value="保存" name="submit" id="Submit4"
		onClick="return Submit4_onclick()" /></li>
</ul>
</div>
</div>
</div>
</form>
<div id="popWindowPhoto" style="display: none;">
<div class="popWindowBody">
<form action="uploadPhoto.action" method="POST"
	enctype="multipart/form-data"><input type="hidden" name="id"
	value='<s:property value="#employee.id"/>' /> 上传的照片 <input type="file"
	name="uploadPhoto" /> <input type="submit" value="上   传" id="Submit6"
	onClick="return Submit6_onclick()" /></form>
说明：请上传JPEG格式的图片，最佳大小为96*96</div>
</div>
<div id="popWindowChangeUserPassword" style="display: none;">
<div class="popWindowBody">
<form action="changeUserPassword_save.action" method="POST"><input
	type="hidden" name="id" value='<s:property value="#employee.user.id"/>' />
<table width="90%" border="0" align="center" cellpadding="8"
	cellspacing="0">
	<tr>
		<td width="80" align="right">用户名</td>
		<td align="left"><s:property value="#employee.user.username" /></td>
	</tr>
	<tr>
		<td width="80" align="right">密码</td>
		<td align="left"><input id="password1" type="password"
			name="userPassword" /></td>
	</tr>
	<tr>
		<td width="80" align="right"></td>
		<td align="left"><input type="submit" value="提交" id="Submit5"
			onClick="return checkPassword()" /></td>
	</tr>
</table>
</form>
</div>
</div>
</body>
</html>
