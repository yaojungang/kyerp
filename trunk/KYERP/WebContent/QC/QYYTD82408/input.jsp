<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QYYT-D-8.2.4-08-（彩色）印刷品巡检记录——输入</title>
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
    <td><p align="center" class="表格抬头"><span class="formTitle">（彩色）印刷品巡检记录——输入</span></p>
    <p class="表格文字"><span class="formText">QYYT-D-8.2.4-08</span></p></td>
  </tr>
</table>
<form action="save.action" method="post" id="form">
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
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">总数量</span></td>
    <td height="40" align="left" valign="middle" bgcolor="#FFFFFF">${afBase.amount}</td>
    </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">完成日期</td>
    <td height="40" align="left" valign="middle" bgcolor="#FFFFFF"><input name="ir.finishDate" type="text" id="finishDate" value='<fmt:formatDate value="${ir.finishDate}" pattern="yyyy-MM-dd HH:mm:ss" />' size="20" /> <img onclick="WdatePicker({el:'finishDate',dateFmt:'yyyy-MM-dd H:mm:ss'})" src="${pageContext.request.contextPath}/Library/js/DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">操作员</td>
    <td height="40" align="left" valign="middle" bgcolor="#FFFFFF"><input name="ir.operators" id="operators" Style="width: 180px; text-align: left;" value="${ir.operators}" /> 
      &nbsp;&nbsp;<select id="deptId" name="companyDept_id">
        <option value="--">选择</option>
        <s:iterator value="#session['DeptTree']" status="st">
          <option value='<s:property value="id"/>'><s:property value="name" /></option>
          </s:iterator>
        </select><span id="divPeopleSelect"></span></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">巡检时间</td>
    <td height="40" align="left" valign="middle" bgcolor="#FFFFFF"><input name="ir.examDate" type="text" id="examDate" value='<fmt:formatDate value="${ir.examDate}" pattern="yyyy-MM-dd HH:mm:ss" />' size="20" /> <img onclick="WdatePicker({el:'examDate',dateFmt:'yyyy-MM-dd H:mm:ss'})" src="${pageContext.request.contextPath}/Library/js/DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">检验项目</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">检验结果</span></td>
    </tr>
  <tr>
    <td align="center" valign="middle" bgcolor="#FFFFFF">亮调</td>
    <td height="60" align="left" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem01" type="text" size="5" value="${ir.examItem01}"/></td>
    </tr>
  <tr>
    <td align="center" valign="middle" bgcolor="#FFFFFF">层次</td>
    <td height="60" align="left" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem02" type="text" id="examItem02" size="5" value="${ir.examItem02}" /></td>
    </tr>
  <tr>
    <td align="center" valign="middle" bgcolor="#FFFFFF">套印</td>
    <td height="60" align="left" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem03" type="text" id="examItem03" size="5" value="${ir.examItem03}"/></td>
    </tr>
  <tr>
    <td align="center" valign="middle" bgcolor="#FFFFFF">网点</td>
    <td height="60" align="left" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem04" type="text" id="examItem04" size="5" value="${ir.examItem04}" /></td>
    </tr>
  <tr>
    <td align="center" valign="middle" bgcolor="#FFFFFF">颜色</td>
    <td height="60" align="left" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem05" type="text" id="examItem05" size="5" value="${ir.examItem05}" /></td>
    </tr>
  <tr>
    <td align="center" valign="middle" bgcolor="#FFFFFF">版面</td>
    <td height="60" align="left" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem06" type="text" id="examItem06" size="5" value="${ir.examItem06}" /></td>
    </tr>
  <tr>
    <td align="center" valign="middle" bgcolor="#FFFFFF">接版</td>
    <td height="60" align="left" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem07" type="text" id="examItem07" size="5" value="${ir.examItem07}" /></td>
    </tr>
  <tr>
    <td align="center" valign="middle" bgcolor="#FFFFFF">图文</td>
    <td height="60" align="left" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem08" type="text" id="examItem08" size="5" value="${ir.examItem08}" /></td>
    </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><p><span class="formText">论验</span><br />
      <span class="formText">结论</span></p></td>
    <td height="40" align="left" valign="middle" bgcolor="#FFFFFF">
      <textarea name="ir.examResult" id="ir.examResult" cols="35" rows="5">${ir.examResult}</textarea>
      
    </td>
    </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">不合格说明及处置</td>
    <td height="40" align="left" valign="middle" bgcolor="#FFFFFF"><textarea name="ir.remark" id="ir.remark" cols="35" rows="5">${ir.remark }</textarea></td>
    </tr>
</table>
<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="left"><p class="formText">注：<br />
      ①抽检数量500册（含）以下抽13册；501-1200册抽20册；1201-10000册抽32册。 <br />
②检验结果一栏合格打“√”，不合格打“×”。有数据要求的填实测数。 <br />
③检验结论一栏，判定合格或不合格。 </p></td>
  </tr>
</table>
<center><input type="submit" value="提交" /></center>
</form>
</body>
</html>