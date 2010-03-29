<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QYYT-ZB-01-制版车间软片检验记录</title>
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
    <td><p align="center" class="formTitle">制版车间软片检验记录</p>
    <p class="formText">QYYT-ZB-01 </p></td>
  </tr>
</table>
<table width="1050" border="1" align="center" cellpadding="0" cellspacing="1" bordercolor="#000000" bgcolor="#000000" style="border-collapse: collapse; font-size: 16px;">
  <tr>
    <td width="60" height="60" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">日期</span></td>
    <td width="126" height="60" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">名称</td>
    <td height="29" colspan="6" align="center" valign="middle" bgcolor="#FFFFFF">检验项目</td>
    <td width="76" height="60" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">结论</td>
    <td width="54" height="60" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">检验人</td>
    <td width="99" height="60" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">不合格数量<br />及处置</td>
    <td width="64" height="60" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">处置后<br />检验</td>
    <td width="57" height="60" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">取片人</td>
    <td width="66" height="60" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">取片日期</td>
    <td width="65" height="60" colspan="2" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">备注</td>
    </tr>
  <tr>
    <td width="45" height="30" align="center" valign="middle" bgcolor="#FFFFFF">版芯</td>
    <td width="54" align="center" valign="middle" bgcolor="#FFFFFF">页码</td>
    <td width="55" align="center" valign="middle" bgcolor="#FFFFFF">版面</td>
    <td width="66" align="center" valign="middle" bgcolor="#FFFFFF">文字</td>
    <td width="64" align="center" valign="middle" bgcolor="#FFFFFF">图像</td>
    <td width="51" align="center" valign="middle" bgcolor="#FFFFFF">密度</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="54" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="99" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="64" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="57" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="66" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="54" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="99" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="64" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="57" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="66" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
    <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="54" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="99" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="64" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="57" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="66" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
    <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="54" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="99" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="64" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="57" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="66" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
    <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="54" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="99" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="64" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="57" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="66" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
    <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="54" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="99" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="64" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="57" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="66" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
    <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="54" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="99" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="64" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="57" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="66" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
    <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="54" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="99" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="64" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="57" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="66" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
    <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="54" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="99" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="64" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="57" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="66" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
    <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="54" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="99" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="64" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="57" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="66" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
    <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="54" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="99" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="64" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="57" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="66" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
    <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="54" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="99" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="64" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="57" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="66" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
    <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="54" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="99" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="64" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="57" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="66" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
    

</table>
<table width="1050" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="40" align="left"><p class="formText">注：合格打“√”，不合格打“×”。</p></td>
  </tr>
</table>
</div>
</body>
</html>