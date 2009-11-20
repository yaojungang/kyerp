<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QYYT-D-8.2.4-09-（单色）印刷品巡检记录</title>
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
    <td><p align="center" class="formTitle">（单色）印刷品巡检记录</p>
    <p class="formText">QYYT-D-8.2.4-09 </p></td>
  </tr>
</table>
<table width="1050" border="1" align="center" cellpadding="0" cellspacing="1" bordercolor="#000000" bgcolor="#000000" style="border-collapse: collapse; font-size: 16px;">
  <tr>
    <td width="60" height="60" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">日期</span></td>
    <td width="70" height="30" align="center" valign="middle" bgcolor="#FFFFFF">操作员</td>
    <td width="335" height="30" align="center" valign="middle" bgcolor="#FFFFFF">书名</td>
    <td width="40" height="30" align="center" valign="middle" bgcolor="#FFFFFF">代数</td>
    <td width="40" height="30" align="center" valign="middle" bgcolor="#FFFFFF">数量</td>
    <td width="40" height="30" align="center" valign="middle" bgcolor="#FFFFFF">巡检<br />
      时间</td>
    <td width="40" height="30" align="center" valign="middle" bgcolor="#FFFFFF">正反<br />
套印</td>
    <td width="40" height="30" align="center" valign="middle" bgcolor="#FFFFFF">墨色</td>
    <td width="40" height="30" align="center" valign="middle" bgcolor="#FFFFFF">外观</td>
    <td width="40" height="30" align="center" valign="middle" bgcolor="#FFFFFF">文字</td>
    <td width="40" align="center" valign="middle" bgcolor="#FFFFFF">网点</td>
    <td width="40" height="30" align="center" valign="middle" bgcolor="#FFFFFF">结论</td>
    <td width="97" height="30" align="center" valign="middle" bgcolor="#FFFFFF">不合格说<br />明及处置</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="40" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
</table>
<table width="1050" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="40" align="left"><p class="formText">注：合格打“√”，不合格打“×”，有数据要求的填写实测数值。</p></td>
  </tr>
</table>
</div>
</body>
</html>