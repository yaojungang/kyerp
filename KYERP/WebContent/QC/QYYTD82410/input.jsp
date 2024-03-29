<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QYYT-D-8.2.4-10-装订产品巡检记录——输入</title>
<link href="${pageContext.request.contextPath}/Library/css/ISOForm.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/Library/js/DatePicker/WdatePicker.js"></script>
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/Library/js/Validator.js"></script>
<script src="${pageContext.request.contextPath}/Library/js/jquery.js"
	type="text/javascript"></script>
<script>
$(document).ready(function(){
$("#deptId").change(function() {
	var id=$("#deptId").val();
	$.get("noSkin_getPeople.action?id=" + id, null, function(data) {
		document.getElementById("divPeopleSelect").innerHTML = data;
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

<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><p align="center"><span class="formTitle">QYYT-D-8.2.4-10-装订产品巡检记录——输入</span></p></td>
  </tr>
</table>
<form action="save.action" method="post" id="form">
<s:token></s:token>
<input type="hidden" name="afId" value="${afBase.afId}" />
<input type="hidden" name="ir.id" value="${ir.id}"/>
<table width="800" border="1" align="center" cellpadding="0" cellspacing="1" bordercolor="#000000" bgcolor="#000000" style="border-collapse: collapse; font-size: 16px;">
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">名称</span></td>
    <td height="40" align="left" valign="middle" bgcolor="#FFFFFF">${afBase.presswork}<span class="formText">：</span></td>
    </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">卡号</span></td>
    <td height="40" align="left" valign="middle" bgcolor="#FFFFFF">${afBase.iso}${afBase.afNo}</td>
    </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">数量</span></td>
    <td height="40" align="left" valign="middle" bgcolor="#FFFFFF">${afBase.amount}</td>
    </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">检验项目</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">检验结果</span></td>
    </tr>
  <tr>
    <td align="center" valign="middle" bgcolor="#FFFFFF">配页</td>
    <td height="60" align="left" valign="middle" bgcolor="#FFFFFF"><input id="examItem01" name="ir.examItem01" type="text" size="5" value="${ir.examItem01}"/>
    &nbsp;&nbsp;<select id="examItem0100" name="examItem0100" onChange="document.getElementById('examItem01').value=this.options[this.selectedIndex].value">
        <option value="--">选择</option>
          <option value='合格'>合格</option>
          <option value='不合格'>不合格</option>
        </select>
</td>
    </tr>
  <tr>
    <td align="center" valign="middle" bgcolor="#FFFFFF">折页</td>
    <td height="60" align="left" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem02" type="text" id="examItem02" size="5" value="${ir.examItem02}" />
    &nbsp;&nbsp;<select id="examItem0200" name="examItem0200" onChange="document.getElementById('examItem02').value=this.options[this.selectedIndex].value">
        <option value="--">选择</option>
          <option value='合格'>合格</option>
          <option value='不合格'>不合格</option>
        </select>
    </td>
    </tr>
  <tr>
    <td align="center" valign="middle" bgcolor="#FFFFFF">骑马钉</td>
    <td height="60" align="left" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem03" type="text" id="examItem03" size="5" value="${ir.examItem03}"/>
    &nbsp;&nbsp;<select id="examItem0300" name="examItem0300" onChange="document.getElementById('examItem03').value=this.options[this.selectedIndex].value">
        <option value="--">选择</option>
          <option value='合格'>合格</option>
          <option value='不合格'>不合格</option>
        </select>
    </td>
    </tr>
  <tr>
    <td align="center" valign="middle" bgcolor="#FFFFFF">胶订</td>
    <td height="60" align="left" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem04" type="text" id="examItem04" size="5" value="${ir.examItem04}" />
    &nbsp;&nbsp;<select id="examItem0400" name="examItem0400" onChange="document.getElementById('examItem04').value=this.options[this.selectedIndex].value">
        <option value="--">选择</option>
          <option value='合格'>合格</option>
          <option value='不合格'>不合格</option>
        </select>
    </td>
    </tr>
  <tr>
    <td align="center" valign="middle" bgcolor="#FFFFFF">裁切成品</td>
    <td height="60" align="left" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem05" type="text" id="examItem05" size="5" value="${ir.examItem05}" />
    &nbsp;&nbsp;<select id="examItem0500" name="examItem0500" onChange="document.getElementById('examItem05').value=this.options[this.selectedIndex].value">
        <option value="--">选择</option>
          <option value='合格'>合格</option>
          <option value='不合格'>不合格</option>
        </select>
    </td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><p><span class="formText">结论</span></p></td>
    <td height="40" align="left" valign="middle" bgcolor="#FFFFFF">
      <textarea name="ir.examResult" id="ir.examResult" cols="35" rows="5">${ir.examResult}</textarea>
      
      </td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">不合格说明及处置</td>
    <td height="40" align="left" valign="middle" bgcolor="#FFFFFF"><textarea name="ir.remark" id="ir.remark" cols="35" rows="5">${ir.remark }</textarea></td>
    </tr>
</table>
<center><input type="submit" value="提交" /></center>
</form>
</body>
</html>