<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#request['AFInfo']" name="af" id="af" />
<s:set value="#session['privilegesList']" name="privilegesList"
	id="privilegesList" />
<s:set value="#session['user']" name="user" id="user" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>名片任务单-<s:property value="#af.iso" /><s:property
	value="#af.afNo" /></title>
<link href="<%=request.getContextPath()%>/Library/css/AFinfo.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
<table width="750" border="0" align="center" cellpadding="3"
	cellspacing="0">
	<tr>
		<td height="50" align="right" valign="middle"><img
			src="<%=request.getContextPath()%>/Library/images/title_mprwd.gif"></td>
		<td width="185" rowspan="2" align="center" valign="bottom"><img
			src='<%=request.getContextPath()%>/barcode?height=10&msg=<s:property value="#af.iso" /><s:property value="#af.afNo" />'
			width=160px /></td>
	</tr>
	<tr>
		<td align="left">
		<div style="margin-right: 50px; float: right;"><span
			class="text"><s:date name="#af.ad"
			format="yyyy-MM-dd HH:MM:SS" nice="false" /></span></div>
		</td>
	</tr>
</table>
<table width="750" border="1" align="center" cellpadding="8"
	cellspacing="0" bordercolor="#000000">
	<tr>
		<td width="12%" align="center" class="item">委托单位</td>
		<td align="center" class="text"><s:property value="#af.client" /></td>
		<td width="12%" align="center" class="item">经手人</td>
		<td width="16%" align="center" class="text"><s:property
			value="#af.linkman" /></td>
		<td width="12%" align="center" class="item">电话</td>
		<td width="16%" align="center" class="text"><s:property
			value="#af.tel" /></td>
	</tr>
	<tr>
		<td align="center" class="item">纸张</td>
		<td align="center" class="text"><s:property value="#af.paper" /></td>
		<td align="center" class="item">印数</td>
		<td align="center" class="text"><s:property value="#af.amount" />(<s:property
			value="#af.number" />盒)</td>
		<td align="center" class="item">色数</td>
		<td align="center" class="text"><s:property
			value="#af.colorFrontN" />+<s:property value="#af.colorBackN" /></td>
	</tr>
	<tr>
		<td align="center" class="item">正面颜色</td>
		<td align="center" class="text"><s:property
			value="#af.colorFront" /></td>
		<td align="center" class="item">背面颜色</td>
		<td colspan="3" align="center" class="text"><s:property
			value="#af.colorBack" /></td>
	</tr>
	<tr>
		<td rowspan="2" align="center" class="item">备 注</td>
		<td colspan="3" rowspan="2" align="left" valign="top" class="text"><s:property
			value="#af.remark" /></td>
		<td colspan="2" align="center" class="item">计价项目</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<table width="100%" border="0" cellspacing="0" cellpadding="4">
			<!-- <s:iterator value="#af.AfValuation" id="afv" status="st"> -->
			<tr>
				<td align="center" class="text"><s:property
					value="#afv.itemName" /></td>
				<td align="center" class="text"><s:property
					value="#afv.totalAmount" /></td>
			</tr>
			<!-- </s:iterator> -->
			<tr>
				<td align="center" class="item">合计:</td>
				<td align="center" class="text"><s:property
					value="#af.moneyShould" />元</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td align="center" class="item">发行日期</td>
		<td align="center" class="text"><s:date name="#af.planDeliver"
			format="yyyy年MM月dd日" nice="false" /></td>
		<td align="center" class="item">送货地点</td>
		<td colspan="3" align="center" class="text"><s:property
			value="#af.deliverAddress" /></td>
	</tr>
</table>
</body>
</html>
