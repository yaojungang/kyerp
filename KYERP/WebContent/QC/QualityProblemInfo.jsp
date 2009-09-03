<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#request['AFInfo']" name="af" id="af" />
<s:set value="#request['AFQualityProblem']" name="afqp" id="afqp" />
<s:set value="#session['user']" name="user" id="user" />
<!DOCTYPE HTML PUBLIC "-//W8C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>质量问题记录-<s:property value="#request['pageTitle']" /></title>
<link href="${pageContext.request.contextPath}/Library/css/Info.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="tnoprint">
<div class="SecMenu">
<ul>
	<li><a href='QualityProblemInfo_print.action?afId=<s:property value="#af.afId" />&id=<s:property value="#afqp.id" />' target="_blank">打印</a></li>
	<li><a href='QualityProblemInput.action?afId=<s:property value="#af.afId" />&id=<s:property value="#afqp.id" />' target="_blank">修改</a></li>
	<li><a href='${pageContext.request.contextPath}/QC/delQualityProblem.action?afId=<s:property value="#af.afId" />&id=<s:property value="id" />'>删除</a></li>
</ul></div></div>
<div style="margin:auto 0;">
<div class="BigTitle">质量问题记录</div>
<table class="TableBlock" width="750" border="1" align="center" cellpadding="3"
	cellspacing="0" bordercolor="#000000" bgcolor="#000000"
	style="border-collapse: collapse">
  <tr>
    <td width="80" align="center" bgcolor="#FFFFFF"><div align="center" class="item">印品名称</div></td>
    <td colspan="5" bgcolor="#FFFFFF" ><span class="text"><s:property value="#af.presswork" /></span></td>
    <td width="80" align="center" bgcolor="#FFFFFF"><div align="center" class="item">日期</div></td>
    <td bgcolor="#FFFFFF"><span class="text"><s:date name="#af.ad" nice="false" /></span></td>
    </tr>
  <tr>
    <td width="80" align="center" bgcolor="#FFFFFF"><div align="center" class="item">卡号</div></td>
    <td bgcolor="#FFFFFF"><span class="text"><s:property value="#af.iso" /><s:property value="#af.afNo" /></span></td>
    <td width="80" align="center" bgcolor="#FFFFFF"><div align="center" class="item">数量</div></td>
    <td bgcolor="#FFFFFF"><span class="text"><s:property value="#af.amount" /></span></td>
    <td width="80" align="center" bgcolor="#FFFFFF"><div align="center" class="item">车间</div></td>
    <td bgcolor="#FFFFFF"><span class="text"><s:iterator value="#request['DeptTree']" status="st"> <s:if test="#afqp.workshop == id" ><s:property value="name" /></s:if> </s:iterator></span></td>
    <td width="80" align="center" bgcolor="#FFFFFF"><div align="center" class="item">责任人</div></td>
    <td bgcolor="#FFFFFF"><span class="text"><s:property value="#afqp.personLiable" /></span></td>
    </tr>
  <tr>
    <td width="80" align="center" bgcolor="#FFFFFF"><div align="center" class="item">问题描述</div></td>
    <td colspan="7" bgcolor="#FFFFFF"><span class="text"><s:property value="#afqp.description" /></span></td>
  </tr>
  <tr>
    <td width="80" align="center" bgcolor="#FFFFFF"><div align="center" class="item">原因分析</div></td>
    <td colspan="7" bgcolor="#FFFFFF"><span class="text"><s:property value="#afqp.reason" /></span></td>
  </tr>
  <tr>
    <td width="80" align="center" bgcolor="#FFFFFF"><div align="center" class="item">解决办法</div></td>
    <td colspan="7" bgcolor="#FFFFFF"><span class="text"><s:property value="#afqp.solution" /></span></td>
  </tr>
  <tr>
    <td width="80" align="center" bgcolor="#FFFFFF"><div align="center" class="item">损失</div></td>
    <td colspan="7" bgcolor="#FFFFFF"><span class="text"><s:property value="#afqp.loss" /></span></td>
  </tr>
  <tr>
    <td width="80" align="center" bgcolor="#FFFFFF"><div align="center" class="item">处置</div></td>
    <td colspan="7" bgcolor="#FFFFFF"><span class="text"><s:property value="#afqp.disposal" /></span></td>
  </tr>
  <tr>
    <td width="80" align="center" bgcolor="#FFFFFF"><div align="center" class="item">当事人意见</div></td>
    <td colspan="7" bgcolor="#FFFFFF"><span class="text"><s:property value="#afqp.personOpinion" /></span></td>
  </tr>
</table>
<table width="750" border="0" align="center" cellpadding="8"
	cellspacing="0">
	<tr>
		<td><span class="bottomtext">制表人：<s:property
			value="#afqp.inputMan" /></span></td>
		<td><span class="bottomtext">制表时间： <s:date name="#afqp.inputTime" format="yyyy-MM-dd HH:MM:SS" nice="false" /></span></td>
		<td><span class="bottomtext">审核人: <s:property
			value="#afqp.verifyMan" /></span></td>
		<td><span class="bottomtext">审核时间: <s:date name="#afqp.verifyTime" format="yyyy-MM-dd HH:MM:SS" nice="false" /></span></td>
	</tr>
</table>
</div>

<s:iterator value="#afqp.attachments" status="st">
<div style="PAGE-BREAK-AFTER: always"></div>
<div class="noprintOnScreen">
<div class="BigTitle">质量问题记录</div>
<table class="TableBlock" width="750" border="1" align="center" cellpadding="3"
	cellspacing="0" bordercolor="#000000" bgcolor="#000000"
	style="border-collapse: collapse">
  <tr>
    <td width="80" align="center" bgcolor="#FFFFFF"><div align="center" class="item">印品名称</div></td>
    <td colspan="5" bgcolor="#FFFFFF" ><span class="text"><s:property value="#af.presswork" /></span></td>
    <td width="80" align="center" bgcolor="#FFFFFF"><div align="center" class="item">日期</div></td>
    <td bgcolor="#FFFFFF"><span class="text"><s:date name="#af.ad" nice="false" /></span></td>
    </tr>
  <tr>
    <td width="80" align="center" bgcolor="#FFFFFF"><div align="center" class="item">卡号</div></td>
    <td bgcolor="#FFFFFF"><span class="text"><s:property value="#af.iso" /><s:property value="#af.afNo" /></span></td>
    <td width="80" align="center" bgcolor="#FFFFFF"><div align="center" class="item">数量</div></td>
    <td bgcolor="#FFFFFF"><span class="text"><s:property value="#af.amount" /></span></td>
    <td width="80" align="center" bgcolor="#FFFFFF"><div align="center" class="item">车间</div></td>
    <td bgcolor="#FFFFFF"><span class="text"><s:iterator value="#request['DeptTree']" status="st"> <s:if test="#afqp.workshop == id" ><s:property value="name" /></s:if> </s:iterator></span></td>
    <td width="80" align="center" bgcolor="#FFFFFF"><div align="center" class="item">责任人</div></td>
    <td bgcolor="#FFFFFF"><span class="text"><s:property value="#afqp.personLiable" /></span></td>
    </tr>
</table>
</div>
<table width="100%" style="margin: 8px;">
		<tr>
			<td align="center"><a href='/uploadData/QualityProblemAttactent/<s:property value="fileName" />' target="_blank"><img src='/uploadData/QualityProblemAttactent/<s:property value="fileName" />' border="0" style="width: 600px;"/></a></td>
	  </tr>
		<tr>
		  <td align="center"><s:property value="name" /></td>
	  </tr>
</table>
<div class="noprintOnScreen">
<table width="750" border="0" align="center" cellpadding="8"
	cellspacing="0">
	<tr>
		<td><span class="bottomtext">制表人：<s:property
			value="#afqp.inputMan" /></span></td>
		<td><span class="bottomtext">制表时间： <s:date name="#afqp.inputTime" format="yyyy-MM-dd HH:MM:SS" nice="false" /></span></td>
		<td><span class="bottomtext">审核人: <s:property
			value="#afqp.verifyMan" /></span></td>
		<td><span class="bottomtext">审核时间: <s:date name="#afqp.verifyTime" format="yyyy-MM-dd HH:MM:SS" nice="false" /></span></td>
	</tr>
</table>
</div>
</s:iterator>
</body>
</html>
