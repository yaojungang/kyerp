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
    <td><p align="center" class="formTitle">顾客满意度调查表 </p>
    <p class="formText">QYYT-D-8.2.1-02 </p></td>
  </tr>
</table>
<table width="750" border="1" align="center" cellpadding="0" cellspacing="1" bordercolor="#000000" bgcolor="#000000" style="border-collapse: collapse; font-size: 16px;">
  <tr>
    <td width="132" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">单位名称</span></td>
    <td width="283" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><select name="select" id="select">
    </select></td>
    <td width="145" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">地址</span></td>
    <td width="181" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield" type="text" id="textfield" value="自动" size="6" /></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">电话</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield3" type="text" id="textfield3" value="自动" size="6" /></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">联系人</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield2" type="text" id="textfield2" value="自动" size="6" /></td>
  </tr>
  <tr>
    <td height="200" colspan="4" align="left" valign="top" bgcolor="#FFFFFF"><p><span class="formText">对本公司产品的满意程度：</span></p>
      <table width="750" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="41" height="40">&nbsp;</td>
          <td width="128" height="40">设计制作：</td>
          <td width="200" height="40"><span class="formText">
            <input type="checkbox" name="checkbox" id="checkbox" />
            满意</span></td>
          <td width="172" height="40"><span class="formText">
            <input type="checkbox" name="checkbox2" id="checkbox2" />
          </span>一般</td>
          <td width="209" height="40"><span class="formText">
            <input type="checkbox" name="checkbox3" id="checkbox3" />
            不满意</span></td>
        </tr>
        <tr>
          <td width="41" height="40">&nbsp;</td>
          <td width="128" height="40">印刷质量：</td>
          <td width="200" height="40"><span class="formText">
            <input type="checkbox" name="checkbox" id="checkbox" />
            满意</span></td>
          <td width="172" height="40"><span class="formText">
            <input type="checkbox" name="checkbox2" id="checkbox2" />
          </span>一般</td>
          <td width="209" height="40"><span class="formText">
            <input type="checkbox" name="checkbox3" id="checkbox3" />
            不满意</span></td>
        </tr>
        <tr>
          <td width="41" height="40">&nbsp;</td>
          <td width="128" height="40">装订质量：</td>
          <td width="200" height="40"><span class="formText">
            <input type="checkbox" name="checkbox" id="checkbox" />
            满意</span></td>
          <td width="172" height="40"><span class="formText">
            <input type="checkbox" name="checkbox2" id="checkbox2" />
          </span>一般</td>
          <td width="209" height="40"><span class="formText">
            <input type="checkbox" name="checkbox3" id="checkbox3" />
            不满意</span></td>
        </tr>
        <tr>
          <td width="41" height="40">&nbsp;</td>
          <td width="128" height="40">产品包装：</td>
          <td width="200" height="40"><span class="formText">
            <input type="checkbox" name="checkbox" id="checkbox" />
            满意</span></td>
          <td width="172" height="40"><span class="formText">
            <input type="checkbox" name="checkbox2" id="checkbox2" />
          </span>一般</td>
          <td width="209" height="40"><span class="formText">
            <input type="checkbox" name="checkbox3" id="checkbox3" />
            不满意</span></td>
        </tr>
        <tr>
          <td width="41" height="40">&nbsp;</td>
          <td width="128" height="40">交货周期：</td>
          <td width="200" height="40"><span class="formText">
            <input type="checkbox" name="checkbox" id="checkbox" />
            满意</span></td>
          <td width="172" height="40"><span class="formText">
            <input type="checkbox" name="checkbox2" id="checkbox2" />
          </span>一般</td>
          <td width="209" height="40"><span class="formText">
            <input type="checkbox" name="checkbox3" id="checkbox3" />
            不满意</span></td>
        </tr>
        <tr>
          <td width="41" height="40">&nbsp;</td>
          <td width="128" height="40">服务质量：</td>
          <td width="200" height="40"><span class="formText">
            <input type="checkbox" name="checkbox" id="checkbox" />
            满意</span></td>
          <td width="172" height="40"><span class="formText">
            <input type="checkbox" name="checkbox2" id="checkbox2" />
          </span>一般</td>
          <td width="209" height="40"><span class="formText">
            <input type="checkbox" name="checkbox3" id="checkbox3" />
            不满意</span></td>
        </tr>
      </table>
    <p>&nbsp;</p>
  </td>
  </tr>
  <tr>
    <td height="200" colspan="4" align="left" valign="top" bgcolor="#FFFFFF"><p><span class="formText">其他的意见、要求或建议：</span></p>
      <p>
        <textarea name="textarea" id="textarea" cols="45" rows="5"></textarea>
      </p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
<p>&nbsp;</p>
      <p>&nbsp;</p>
      <table width="750" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="397" height="40"><p>相关印品：
            <select name="select2" id="select2">
            </select>
          （或 调查周期：
              <select name="select3" id="select3">
              </select>
            ）</p></td>
          <td width="142" height="40"><span class="formText">顾客签字：</span></td>
          <td width="67" height="40"><input name="textfield14" type="text" id="textfield14" size="5" /></td>
          <td width="62" height="40"><span class="formText">日期：</span></td>
          <td width="82" height="40"><input name="textfield4" type="text" id="textfield4" size="5" /></td>
        </tr>
      </table></td>
  </tr>
</table>
</div>
</body>
</html>