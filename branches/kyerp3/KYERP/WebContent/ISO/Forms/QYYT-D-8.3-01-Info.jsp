<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QYYT-D-8.3-01-不合格品报告单</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/function.js"></script>
<link href="../../Library/css/ISOForm.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="pim2_secondMenu tnoprint">
<ul>
	<li><input name="b_print"
	type="button" class="ipt" onClick="printdiv('tableInfo');"
	value=" 打印 "></li></ul></div>
<div id="tableInfo">
<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><p align="center" class="formTitle">不合格品报告单 </p>
    <p class="formText">QYYT-D-8.3-01 </p></td>
  </tr>
</table>
<table width="750" border="1" align="center" cellpadding="0" cellspacing="1" bordercolor="#000000" bgcolor="#000000" style="border-collapse: collapse; font-size: 16px;">
  <tr>
    <td width="63" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">名称</span></td>
    <td height="40" colspan="3" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="101" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">报告日期</span></td>
    <td width="93" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">卡号</span></td>
    <td width="212" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="78" height="40" colspan="-1" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">车间</span></td>
    <td width="188" height="40" colspan="-1" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">不合格数量</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="200" colspan="6" align="left" valign="top" bgcolor="#FFFFFF"><p><span class="formText">报告人陈述：</span></p>
      <p>&nbsp;</p>
<p>&nbsp;</p>
      <p>&nbsp;</p>
<p>&nbsp;</p>
      <table width="750" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="483" height="40">&nbsp;</td>
          <td width="56" height="40"><span class="formText">签字：</span></td>
          <td width="67" height="40">&nbsp;</td>
          <td width="57" height="40"><span class="formText">日期：</span></td>
          <td width="87" height="40">&nbsp;</td>
        </tr>
      </table>
  </td>
  </tr>
  <tr>
    <td height="200" colspan="6" align="left" valign="top" bgcolor="#FFFFFF"><p><span class="formText">车间意见：</span></p>
      <p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
      <p>&nbsp;</p>
      <table width="750" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="483" height="40">&nbsp;</td>
          <td width="56" height="40"><span class="formText">签字：</span></td>
          <td width="67" height="40">&nbsp;</td>
          <td width="60" height="40"><span class="formText">日期：</span></td>
          <td width="84" height="40">&nbsp;</td>
        </tr>
      </table></td>
    </tr>
  <tr>
    <td height="200" colspan="6" align="left" valign="top" bgcolor="#FFFFFF"><p><span class="formText">技术质量科意见：</span></p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
<p>&nbsp;</p>
      <p>&nbsp;</p>
      <table width="750" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="483" height="40">&nbsp;</td>
          <td width="56" height="40"><span class="formText">签字：</span></td>
          <td width="67" height="40">&nbsp;</td>
          <td width="62" height="40"><span class="formText">日期：</span></td>
          <td width="82" height="40">&nbsp;</td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td height="200" colspan="6" align="left" valign="top" bgcolor="#FFFFFF"><p><span class="formText">备注：</span></p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p></td>
  </tr>
</table>
</div>
</body>
</html>