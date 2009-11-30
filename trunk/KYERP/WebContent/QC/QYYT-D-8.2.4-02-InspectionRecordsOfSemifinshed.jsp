<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QYYT-D-8.2.4-02-毛样书记录</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/function.js"></script>
</head>
<body>
<div class="pim2_secondMenu tnoprint">
<ul>
	<li><input name="b_print" type="button" class="ipt" onclick="printdiv('tableInfo');" value=" 打印 "></li>
 </ul>
</div>
<div id="tableInfo">
<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><p align="center" class="formTitle">毛样书记录 </p>
    <p class="formText">QYYT-D-8.2.4-02 </p></td>
  </tr>
</table>
<table width="750" border="1" align="center" cellpadding="0" cellspacing="1" bordercolor="#000000" bgcolor="#000000" style="border-collapse: collapse; font-size: 16px;">
  <tr>
    <td width="150" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">日期</span></td>
    <td width="90" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">卡号</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">名称</span></td>
    <td width="90" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">项目</span></td>
    <td width="58" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">负责人</span></td>
    <td width="100" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">备注</span></td>
  </tr>
  <s:iterator value="#request['qcList']" id="qc" status="st">
    <tr>
      <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><s:date name="#qc.examdate" format="yyyy-MM-dd" nice="false" /></td>
      <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><s:property value="#qc.afBase.iso" /><s:property value="#qc.afBase.afNo" /></td>
      <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><s:property value="#qc.afBase.presswork" /></td>
      <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><s:property value="#qc.item" /></td>
      <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><s:property value="#qc.operator" /></td>
      <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><s:property value="#qc.remark" /></td>
      </tr>
  </s:iterator>
</table>
</div>
</body>
</html>