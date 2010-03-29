<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QYYT-ZB-05-制版车间工作台帐</title>
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
    <td><p align="center" class="formTitle">制版车间工作台帐</p>
    <p class="formText">QYYT-ZB-05 </p></td>
  </tr>
</table>
<table width="1050" border="1" align="center" cellpadding="0" cellspacing="1" bordercolor="#000000" bgcolor="#000000" style="border-collapse: collapse; font-size: 16px;">
  <tr>
    <td width="53" height="60" rowspan="3" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">卡号</span></td>
    <td width="285" height="60" rowspan="3" align="center" valign="middle" bgcolor="#FFFFFF">印品名称</td>
    <td width="16" rowspan="3" align="center" valign="middle" bgcolor="#FFFFFF">色数</td>
    <td width="70" rowspan="3" align="center" valign="middle" bgcolor="#FFFFFF">接活时间</td>
    <td colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">拼版</td>
    <td height="29" colspan="6" align="center" valign="middle" bgcolor="#FFFFFF">晒版</td>
    <td width="78" height="60" rowspan="3" align="center" valign="middle" bgcolor="#FFFFFF">机组领单时间</td>
    <td width="83" height="60" rowspan="3" align="center" valign="middle" bgcolor="#FFFFFF">机型/签字</td>
    <td width="50" height="60" rowspan="3" align="center" valign="middle" bgcolor="#FFFFFF">份数</td>
    <td width="123" height="60" rowspan="3" align="center" valign="middle" bgcolor="#FFFFFF">备注</td>
    </tr>
  <tr>
    <td width="16" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">人员</td>
    <td width="16" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">数量</td>
    <td width="16" height="30" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">人员</td>
    <td width="17" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">方法</td>
    <td colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">新版数量</td>
    <td colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">再生版数量</td>
    </tr>
  <tr>
    <td width="43" align="center" valign="middle" bgcolor="#FFFFFF">4开</td>
    <td width="39" align="center" valign="middle" bgcolor="#FFFFFF">对开</td>
    <td width="46" align="center" valign="middle" bgcolor="#FFFFFF">4开</td>
    <td width="48" align="center" valign="middle" bgcolor="#FFFFFF">对开</td>
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
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="83" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="50" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
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