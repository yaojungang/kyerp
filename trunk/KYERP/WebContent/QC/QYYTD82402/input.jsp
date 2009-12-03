<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QYYT-D-8.2.4-02-毛样书记录</title>
<link href="${pageContext.request.contextPath}/Library/css/ISOForm.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/Library/js/DatePicker/WdatePicker.js"></script>
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/Library/js/Validator.js"></script>
</head>

<body>

<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><p align="center" class="表格抬头"><span class="formTitle">毛样书记录——输入</span></p>
    <p class="表格文字"><span class="formText">QYYT-D-8.2.4-02</span></p></td>
  </tr>
</table>
<form action="save.action" method="post" id="form">
<s:token></s:token>
<input type="hidden" name="afId" value="${afBase.afId}" />
<input type="hidden" name="ir.id" value="${ir.id}"/>
<span class="formText">名称:</span> ${afBase.presswork}<br />
<span class="formText">卡号:</span>${afBase.iso}${afBase.afNo}<br />
<p>选择项目： <input name="ir.item" id="item" Style="width: 180px; text-align: left;" value="${ir.item}" /> &nbsp;&nbsp; <select name="item00" class="wenbenkuang" id="E_Paper00"
			onChange="(document.getElementById('item').value=this.options[this.selectedIndex].value)">
            <option value="">选择</option>
            <option value="收集">收集</option>
            <option value="合格">合格</option>
            <option value="不合格">不合格</option>
            </select></p>
  <p>备注：
      <textarea name="ir.remark" id="ir.remark" cols="35" rows="5">${ir.remark }</textarea>
  </p></strong>
<center><input type="submit" value="提交" /></center>
</form>
</body>
</html>