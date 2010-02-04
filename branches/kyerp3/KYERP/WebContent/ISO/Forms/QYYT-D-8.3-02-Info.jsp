<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QYYT-D-8.3-02-不合格品记录</title>
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
<table width="1050" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><p align="center" class="formTitle">不合格品记录</p>
    <p class="formText">QYYT-D-8.3-02 </p></td>
  </tr>
</table>
<table width="1050" border="1" align="center" cellpadding="0" cellspacing="1" bordercolor="#000000" bgcolor="#000000" style="border-collapse: collapse; font-size: 16px;">
  <tr>
    <td width="71" height="60" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">日期</span></td>
    <td width="278" height="60" align="center" valign="middle" bgcolor="#FFFFFF">产品名称</td>
    <td width="55" height="60" align="center" valign="middle" bgcolor="#FFFFFF">产品<br />数量</td>
    <td width="55" height="60" align="center" valign="middle" bgcolor="#FFFFFF">生产<br />
      数量</td>
    <td width="55" height="60" align="center" valign="middle" bgcolor="#FFFFFF">工序</td>
    <td width="55" height="60" align="center" valign="middle" bgcolor="#FFFFFF">操作者</td>
    <td width="60" height="60" align="center" valign="middle" bgcolor="#FFFFFF">不合格<br />数量</td>
    <td width="55" height="60" align="center" valign="middle" bgcolor="#FFFFFF">类型</td>
    <td width="85" height="60" align="center" valign="middle" bgcolor="#FFFFFF">不合格记录<br />及原因分析</td>
    <td width="85" height="60" align="center" valign="middle" bgcolor="#FFFFFF">不合格<br />品处置</td>
    <td width="50" height="60" align="center" valign="middle" bgcolor="#FFFFFF">评审</td>
    <td width="107" align="center" valign="middle" bgcolor="#FFFFFF">备注</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="60" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="85" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="60" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="85" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="60" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="85" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="60" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="85" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="60" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="85" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="60" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="85" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="60" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="85" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="60" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="85" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="60" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="85" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="60" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="85" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="60" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="85" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="60" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="85" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="60" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="55" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="85" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
</table>
<table width="1050" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="40" align="left"><p class="formText">&nbsp;</p></td>
  </tr>
</table>
</div>
</body>
</html>