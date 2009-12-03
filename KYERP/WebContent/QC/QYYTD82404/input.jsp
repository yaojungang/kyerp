<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QYYT-D-8.2.4-04-装订成品检验记录</title>
<link href="${pageContext.request.contextPath}/Library/css/ISOForm.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/Library/js/DatePicker/WdatePicker.js"></script>
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/Library/js/Validator.js"></script>
</head>

<body>

<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><p align="center" class="表格抬头"><span class="formTitle">装订成品检验记录——输入</span></p>
    <p class="表格文字"><span class="formText">QYYT-D-8.2.4-04 </span></p></td>
  </tr>
</table>
<form action="${pageContext.request.contextPath}/QC/InspectionRecordsOfBindingfinshed/save.action" method="post" id="form">
<s:token></s:token>
<input type="hidden" name="afId" value="${afBase.afId}" />
<input type="hidden" name="ir.id" value="${ir.id}"/>
<table width="800" border="1" align="center" cellpadding="0" cellspacing="1" bordercolor="#000000" bgcolor="#000000" style="border-collapse: collapse; font-size: 16px;">
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">名称</span></td>
    <td height="40" colspan="5" align="center" valign="middle" bgcolor="#FFFFFF">${afBase.presswork}</td>
    <td width="64" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">卡号</span></td>
    <td width="184" height="40" align="center" valign="middle" bgcolor="#FFFFFF">${afBase.iso}${afBase.afNo}</td>
  </tr>
  <tr>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">类别</span></td>
    <td width="60" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText"><span class="formInput">全检</span></span></td>
    <td width="73" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">总数量</span></td>
    <td width="132" height="40" align="center" valign="middle" bgcolor="#FFFFFF">${afBase.amount}</td>
    <td width="62" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">正品数</span></td>
    <td width="108" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.qualifiedAmount" type="text" id="ir.qualifiedAmount" size="5" value="${ir.qualifiedAmount}" /></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">不合格数</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.unqualifiedAmount" type="text" id="ir.unqualifiedAmount" size="5" value="${ir.unqualifiedAmount}" /></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">序号</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">项目</span></td>
    <td height="40" colspan="4" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">质量要求</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">检验结果</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">日期</span></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">1</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">毛样书</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">全书页码顺序正确</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem01" type="text" size="5" value="${ir.examItem01}"/></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem01Date" type="text" id="examItem01Date" value="<fmt:formatDate value="${ir.examItem01Date}" pattern="yyyy-MM-dd HH:mm:ss" />" size="20" /> <img onclick="WdatePicker({el:'examItem01Date',dateFmt:'yyyy-MM-dd H:mm:ss'})" src="${pageContext.request.contextPath}/Library/js/DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">2</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">折页</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">书帖页码和版面顺序正确，书贴平服整齐,无明显八字皱折、死折、折角、残页和套帖，相连页之间页码位置允差≤4.0 mm。</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem02" type="text" id="examItem02" size="5" value="${ir.examItem02}" /></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem02Date" type="text" id="examItem02Date" value="<fmt:formatDate value="${ir.examItem02Date}" pattern="yyyy-MM-dd HH:mm:ss" />" size="20" /> <img onclick="WdatePicker({el:'examItem02Date',dateFmt:'yyyy-MM-dd H:mm:ss'})" src="${pageContext.request.contextPath}/Library/js/DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">3</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">配页</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">书贴页码和版面顺序正确,无错贴、漏贴、脏迹</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem03" type="text" id="examItem03" size="5" value="${ir.examItem03}"/></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem03Date" type="text" id="examItem03Date" value="<fmt:formatDate value="${ir.examItem03Date}" pattern="yyyy-MM-dd HH:mm:ss" />" size="20" /> <img onclick="WdatePicker({el:'examItem03Date',dateFmt:'yyyy-MM-dd H:mm:ss'})" src="${pageContext.request.contextPath}/Library/js/DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">4</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">胶订</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">粘合剂粘度适当，书背和封面粘合牢固，无粘合剂益出，侧胶宽为3.0 mm-7.0 mm。无粘合剂溢出。</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem04" type="text" id="examItem04" size="5" value="${ir.examItem04}" /></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem04Date" type="text" id="examItem04Date" value="<fmt:formatDate value="${ir.examItem04Date}" pattern="yyyy-MM-dd HH:mm:ss" />" size="20" /> <img onclick="WdatePicker({el:'examItem04Date',dateFmt:'yyyy-MM-dd H:mm:ss'})" src="${pageContext.request.contextPath}/Library/js/DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">5</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">铁丝平订</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">无坏锯，漏钉、钉脚平服牢固，盖住钉痕，粘口4.0 mm-7.0mm。</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem05" type="text" id="examItem05" size="5" value="${ir.examItem05}" /></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem05Date" type="text" id="examItem05Date" value="<fmt:formatDate value="${ir.examItem05Date}" pattern="yyyy-MM-dd HH:mm:ss" />" size="20" /> <img onclick="WdatePicker({el:'examItem05Date',dateFmt:'yyyy-MM-dd H:mm:ss'})" src="${pageContext.request.contextPath}/Library/js/DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">6</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">骑马钉</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">无坏钉、漏钉、重钉，书册平服整齐、干净，钉脚平整牢固，钉锯均钉在折缝线上，书贴歪斜≤2 .0mm。钉锯外钉眼距书芯长上下各1/4处，允差±3.0 mm</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem06" type="text" id="examItem06" size="5" value="${ir.examItem06}" /></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem06Date" type="text" id="examItem06Date" value="<fmt:formatDate value="${ir.examItem06Date}" pattern="yyyy-MM-dd HH:mm:ss" />" size="20" /> <img onclick="WdatePicker({el:'examItem06Date',dateFmt:'yyyy-MM-dd H:mm:ss'})" src="${pageContext.request.contextPath}/Library/js/DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">7</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">锁线订</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">锁线后书芯各贴应排列正确、整齐，无破损、掉页和脏迹，锁线松紧适当，书芯厚度基本一致。</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem07" type="text" id="examItem07" size="5" value="${ir.examItem07}" /></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem07Date" type="text" id="examItem07Date" value="<fmt:formatDate value="${ir.examItem07Date}" pattern="yyyy-MM-dd HH:mm:ss" />" size="20" /> <img onclick="WdatePicker({el:'examItem07Date',dateFmt:'yyyy-MM-dd H:mm:ss'})" src="${pageContext.request.contextPath}/Library/js/DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">8</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">粘页机</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">粘页顺序正确，排序整齐，无破损、脏迹，粘接牢固无掉页，刷胶宽度3-4mm，收贴依次错位重叠排列。</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem08" type="text" id="examItem08" size="5" value="${ir.examItem08}" /></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem08Date" type="text" id="examItem08Date" value="<fmt:formatDate value="${ir.examItem08Date}" pattern="yyyy-MM-dd HH:mm:ss" />" size="20" /> <img onclick="WdatePicker({el:'examItem08Date',dateFmt:'yyyy-MM-dd H:mm:ss'})" src="${pageContext.request.contextPath}/Library/js/DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">9</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><p>三面切书<br />切纸机</p></td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">成品裁切尺寸允许误差不超过1.5mm，歪斜允差≤1.5mm产品无严重刀花，无连刀，无严重破头，成品每刀上下本裁切尺寸误差≤1.0mm。</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem09" type="text" id="examItem09" size="5" value="${ir.examItem09}" /></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem09Date" type="text" id="examItem09Date" value="<fmt:formatDate value="${ir.examItem09Date}" pattern="yyyy-MM-dd HH:mm:ss" />" size="20" /> <img onclick="WdatePicker({el:'examItem09Date',dateFmt:'yyyy-MM-dd H:mm:ss'})" src="${pageContext.request.contextPath}/Library/js/DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">10</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">成品</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">全书墨色均匀、字迹清楚，无掉字、上脏，页码位置允差≤7.0 mm。书背字居中，书脊平直，无空泡，无皱折。成品裁切允差≤1.5 mm，无严重刀花，无连刀页，无严重破头。</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem10" type="text" id="examItem10" size="5" value="${ir.examItem10}" /></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem10Date" type="text" id="examItem10Date" value="<fmt:formatDate value="${ir.examItem10Date}" pattern="yyyy-MM-dd HH:mm:ss" />" size="20" /> <img onclick="WdatePicker({el:'examItem10Date',dateFmt:'yyyy-MM-dd H:mm:ss'})" src="${pageContext.request.contextPath}/Library/js/DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><p><span class="formText">综合</span><br /><span class="formText">结论</span></p></td>
    <td height="40" colspan="3" align="left" valign="middle" bgcolor="#FFFFFF">
      <textarea name="ir.examResult" id="ir.examResult" cols="35" rows="5">${ir.examResult}</textarea>

 </td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><p><span class="formText">不合格品的</span><br /><span class="formText">说明及处理</span></p></td>
    <td height="40" colspan="3" align="left" valign="middle" bgcolor="#FFFFFF"><textarea name="ir.remark" id="ir.remark" cols="35" rows="5">${ir.remark }</textarea></td>
  </tr>
</table>
<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="left"><p class="formText">说明：<br />
      1．质量要求、文字描述的检验结果，符合的“√”，不符合“×”； <br />
      2．有数据要求的一律填实测数据； <br />
      3．检验结论一栏，填是否合格。 </p></td>
  </tr>
</table>
<center><input type="submit" value="提交" /></center>
</form>
</body>
</html>