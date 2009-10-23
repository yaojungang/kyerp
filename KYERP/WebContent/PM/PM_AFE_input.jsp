<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#request['AFInfo']" name="af" id="af" />
<s:set value="#request['AFEInfo']" name="afe" id="afe" />
<s:set value="#session['user']" name="user" id="user" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>制版车间输入数据</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/function.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/DatePicker/WdatePicker.js"></script>
<script src="${pageContext.request.contextPath}/Library/js/jquery.js"
	type="text/javascript"></script>
<script>
$(document).ready(function(){
$("#bindMan-deptId").change(function() {
	var id=$("#bindMan-deptId").val();
	$.get("noSkin_getPeopleForBindManInput.action?id=" + id, null, function(data) {
		document.getElementById("divbindManSelect").innerHTML = data;
	});
	});

$("#saiMan-deptId").change(function() {
	var id2=$("#saiMan-deptId").val();
	$.get("noSkin_getPeopleForSaiManInput.action?id=" + id2, null, function(data) {
		document.getElementById("divSaiManSelect").innerHTML = data;
	});
	});
$("#pushSampleMan-deptId").change(function() {
	var id2=$("#pushSampleMan-deptId").val();
	$.get("noSkin_getPeopleForPushSampleManInput.action?id=" + id2, null, function(data) {
		document.getElementById("divPushSampleManSelect").innerHTML = data;
	});
	});
});


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
<div id="MainForm">
<form action="PM_AFE_save.action" class="cssform" method="post"><input type="hidden" name="afId"
	value="<s:property value="#af.afId" />" />

<p><label>接活时间</label> <input type="text" id="workTime" name="workTime" value='<s:date name="#afe.workTime" format="yyyy-MM-dd HH:mm:ss" nice="false"/>' />
<img onClick="WdatePicker({el:'workTime',dateFmt:'yyyy-MM-dd HH:mm:ss'})" src="<%=request.getContextPath()%>/Library/js/DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle" style="cursor:pointer"/>
</p>
<p><label>软片检验</label> <input name="filmCheck" type="radio" value='<s:property value="#afe.filmCheck" />' checked="checked">
  已检验 &nbsp;&nbsp; 
  <input name="filmCheck" type="radio" value='<s:property value="#afe.filmCheck" />'>
  未检验 </p>
<p><label>软片位置编号</label> <input type="text" name="filmPlace" value='<s:property value="#afe.filmPlace" />' />
只能填写数字</p>
<p><label>软片备注</label> <input type="text" id="filmStatus" name="filmStatus" value='<s:property value="#afe.filmStatus" />' />&nbsp;&nbsp; <select
			name="filmStatus00" id="filmStatus00"
			onChange="(document.getElementById('filmStatus').value=this.options[this.selectedIndex].value)">
			<option value="">选择</option>
			<option value="取走">取走</option>
			<option value="借用">借用</option>
			<option value="销毁">销毁</option>
		</select></p>

<p><label>拼版人员</label> <input type="text" id="bindMan" name="bindMan" value='<s:property value="#afe.bindMan" />' /> 
<select id="bindMan-deptId">
				<option value="--">选择</option>
				<s:iterator value="#request['DeptTree']" status="st">
					<option value="<s:property value="id"/>"><s:property
						value="name" /></option>
				</s:iterator>
			</select>
<span id="divbindManSelect"></span> 多个人员中间用半角逗号(,)分开 </p>
<p><label>拼版版数</label> <input type="text" name="EPlateAmount" value='<s:property value="#afe.EPlateAmount" />' /> 
由任务单给出</p>

<p><label>晒版人员</label> <input type="text" id="saiMan" name="saiMan" value='<s:property value="#afe.bindMan" />' /> 
<select id="saiMan-deptId">
				<option value="--">选择</option>
				<s:iterator value="#request['DeptTree']" status="st">
					<option value="<s:property value="id"/>"><s:property
						value="name" /></option>
				</s:iterator>
			</select>
<span id="divSaiManSelect"></span>
多个人员中间用半角逗号(,)分开</p>
<p><label>晒版版数</label> 新PS版：<input type="text" name="newPS" value='<s:property value="#afe.newPS" />' style="width:60px;" />&nbsp;&nbsp;再生PS版：<input type="text" name="oldPS" value='<s:property value="#afe.oldPS" />' style="width:60px;" /></p>
<p><label>晒版方法</label> <select name="saiNum">
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
</select>
填写几晒，只能填写数字</p>
<p><label>曝光时间</label> <input type="text" name="saiLong" value='<s:property value="#afe.saiLong" />' /> 
填写晒版时间（单位：秒），只能填写数字</p>
<p><label>真空度</label> <input type="text" name="saiMpa" value='<s:property value="#afe.saiMpa" />' /> 
填写真空度（单位：MPA），只能填写数字</p>

<p><label>机组领单时间</label> <input type="text" id="pushSampleTime" name="pushSampleTime" value='<s:date name="#afe.pushSampleTime" format="yyyy-MM-dd HH:mm:ss" nice="false"/>' /> 
<img onClick="WdatePicker({el:'pushSampleTime',dateFmt:'yyyy-MM-dd HH:mm:ss'})" src="<%=request.getContextPath()%>/Library/js/DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle" style="cursor:pointer"/>
</p>
<p><label>领单人</label> <input type="text" id="pushSampleMan" name="pushSampleMan" value='<s:property value="#afe.pushSampleMan" />' />
<select id="pushSampleMan-deptId">
				<option value="--">选择</option>
				<s:iterator value="#request['DeptTree']" status="st">
					<option value="<s:property value="id"/>"><s:property
						value="name" /></option>
				</s:iterator>
			</select>
<span id="divPushSampleManSelect"></span>
</p>

<div style="margin-left: 150px;"><input type="submit" value="提交" />
<input type="reset" value="重置" /></div>
</form>
</div>
<br>
<br>

</body>
</html>
