<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QYYT-ZB-07-制版车间检验记录</title>
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
    <td><p align="center" class="formTitle">制版车间检验记录</p>
    <p class="formText">QYYT-ZB-07 </p></td>
  </tr>
</table>
<table width="1050" border="1" align="center" cellpadding="0" cellspacing="1" bordercolor="#000000" bgcolor="#000000" style="border-collapse: collapse; font-size: 16px;">
  <tr>
    <td width="57" height="60" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">日期</span></td>
    <td width="427" height="60" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">印品名称</td>
    <td width="16" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">页码</td>
    <td width="40" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">台纸<br />规格</td>
    <td width="38" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">正反<br />拼套</td>
    <td height="29" colspan="5" align="center" valign="middle" bgcolor="#FFFFFF">拼版规格</td>
    <td width="43" height="60" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">晒版<br />规格</td>
    <td width="16" height="60" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">文字</td>
    <td width="16" height="60" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">图像</td>
    <td width="16" height="60" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">网点</td>
    <td width="16" height="60" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">版面</td>
    <td width="36" height="60" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">结论</td>
    <td width="102" height="60" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">不合格数量<br />说明及处置</td>
    <td width="49" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">处置后<br />检验</td>
    <td width="38" height="60" rowspan="2" align="center" valign="middle" bgcolor="#FFFFFF">检查<br />人员</td>
    </tr>
  <tr>
    <td width="16" height="30" align="center" valign="middle" bgcolor="#FFFFFF">顺序</td>
    <td width="16" align="center" valign="middle" bgcolor="#FFFFFF">十字线</td>
    <td width="16" align="center" valign="middle" bgcolor="#FFFFFF">角线</td>
    <td width="16" align="center" valign="middle" bgcolor="#FFFFFF">代标</td>
    <td width="16" align="center" valign="middle" bgcolor="#FFFFFF">顺序号</td>
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
    <td width="16" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="16" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="16" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="16" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="36" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
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