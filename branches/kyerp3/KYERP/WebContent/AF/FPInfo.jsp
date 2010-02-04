<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#request['AFInfo']" name="af" id="af" />
<s:set value="#session['userSystemFunctionList']" name="userSystemFunctionList"
	id="userSystemFunctionList" />
<s:set value="#session['user']" name="user" id="user" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>清华园胶印厂生产任务单-<s:property value="#af.iso" /><s:property
	value="#af.afNo" /></title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/function.js"></script>
<link href="<%=request.getContextPath()%>/Library/css/AFinfo.css"
	rel="stylesheet" type="text/css" />
<style>
#AFmenu ul {
	PADDING: 0px;
	MARGIN: 2px;
	FONT: 12px Arial, Verdana, sans-serif;
	LIST-STYLE-TYPE: none;
}

#AFmenu li {
	PADDING-RIGHT: 0px;
	DISPLAY: inline;
	PADDING-LEFT: 0px;
	PADDING-BOTTOM: 0px;
	MARGIN: 0px 2px 20px 0px;
	TEXT-TRANSFORM: uppercase;
	PADDING-TOP: 0px
}

#AFmenu LI A {
	PADDING-RIGHT: 9px;
	PADDING-LEFT: 5px;
	FONT-SIZE: 13px;
	BACKGROUND: url(media/halfmoontab.gif) #e5febc no-repeat right top;
	PADDING-BOTTOM: 2px;
	MARGIN: 0px 1px 0px 0px;
	BORDER: gray 1px solid;
	COLOR: black;
	PADDING-TOP: 3px;
	TEXT-DECORATION: none
}

#AFmenu LI A:visited {
	COLOR: black;
}

#AFmenu LI A:hover {
	COLOR: #2d2b2b;
	TEXT-DECORATION: underline
}

#AFInfo {
	clear: both;
	border: 1px;
}
</style>
</head>
<body>
<div id="AFmenu" class="noprint">
<ul>
	<li><a target="_blank"
		href='<%=request.getContextPath()%>/AF/AFInfo_print.action?afId=<s:property value="#af.afId" />'>打印任务单</a></li>
	<s:if
		test="#user.userType.equals('Admin') or 'OM-AF-ZK' in #privilegesList">
		<li><a target="_blank"
			href='<%=request.getContextPath()%>/OPE/ZK.action?AFNo=<s:property value="#af.iso" /><s:property value="#af.afNo" />'>转开任务单</a></li>
	</s:if>
	<s:if test="#af.moneyStatus == 0">
		<s:if
			test="#user.userType.equals('Admin') or 'FM-AF-ViewMoneyFact' in #userSystemFunctionList">
			<li><a
				href='<%=request.getContextPath()%>/OPE/CalAF.action?afId=<s:property value="#af.afId" />'
				target="_blank">实收金额:<s:property value="#af.moneyFact" />元,已结清!</a></li>
		</s:if>
	</s:if>
	<s:else>
		
			<s:if test="#af.moneyShould > 0">
				<s:if test="#user.userType.equals('Admin') or 'FM-AF-ViewMoneyShould' in #userSystemFunctionList">
					<li><a
						href='<%=request.getContextPath()%>/OPE/CalAF.action?afId=<s:property value="#af.afId" />'
						target="_blank">应收金额:<s:property value="#af.moneyShould" />元</a></li>				
				</s:if>
			</s:if>
			<s:else>
				<s:if test="#user.userType.equals('Admin') or 'OM-AF-CalAF' in #userSystemFunctionList">
					<li><a href='<%=request.getContextPath()%>/OPE/CalAF_input.action?afId=<s:property value="#af.afId" />' >计价</a></li>
				</s:if>
			</s:else>
		<s:if test="#af.moneyFact > 0">
					<s:if
						test="#user.userType.equals('Admin') or 'OM-AF-MoneyInput' in #userSystemFunctionList">
						<li><a
							href='<%=request.getContextPath()%>/OPE/CalAF_MoneyInput.action?afId=<s:property value="#af.afId" />'
							target="_blank">实收金额:<s:property value="#af.moneyFact" />元,未结清!</a></li>
					</s:if>
				</s:if>
				<s:else>
					<s:if test="#user.userType.equals('Admin') or 'OM-AF-MoneyInput' in #userSystemFunctionList">
						<li><a
							href='<%=request.getContextPath()%>/OPE/CalAF_MoneyInput.action?afId=<s:property value="#af.afId" />' >收款</a></li>
					</s:if>
				</s:else>
		<s:if test="#user.userType.equals('Admin') or #af.afStatus != 0">
			<s:if
				test="#user.userType.equals('Admin') or 'OM-AF-Edit' in #userSystemFunctionList">
				<li><a
					href='<%=request.getContextPath()%>/OPE/editAF.action?afId=<s:property value="#af.afId" />' >修改任务单</a></li>
				<li><a
					href='<%=request.getContextPath()%>/OPE/FPAddDispose.action?afId=<s:property value="#af.afId" />' >任务单-添加工序</a></li>
			</s:if>
		</s:if>
		<s:if test="#user.userType.equals('Admin') or 'OM-AF-Del' in #userSystemFunctionList">
			<li><a
				href='<%=request.getContextPath()%>/OPE/delAF.action?afId=<s:property value="#af.afId" />'
				target="_blank" onClick="return checkit('删除后不能恢复,您确认要删除这个任务单吗?')">删除任务单</a></li>
		</s:if>
	</s:else>
	
</ul>
</div>
<div id="AFInfo">
<div class="height30"></div>
<div style="position: relative; left: 0px; top: 0px"><s:if
	test="#af.timeRank==1">
	<div style="position: absolute; left: 30px; top: 10px"><img
		src="<%=request.getContextPath()%>/Library/images/ji.gif"></div>
</s:if> <s:if test="#af.ourbinding==1">
	<div style="position: absolute; left: 0px; top: 40px"><img
		src="<%=request.getContextPath()%>/Library/images/zhuang.gif"></div>
</s:if>
<table width="750" border="0" align="center" cellpadding="3"
	cellspacing="0">
	<tr>
		<td height="50" align="right" valign="middle"><img
			src="<%=request.getContextPath() %>/Library/images/title_fprwd.gif"></td>
		<td width="185" rowspan="2" align="center" valign="bottom"><img
			src='<%=request.getContextPath() %>/barcode?height=10&msg=<s:property value="#af.iso" /><s:property value="#af.afNo" />'
			width=160px /></td>
	</tr>
	<tr>
		<td align="left">
		<div style="margin-left: 20px; float: left;"><span class="item"><s:if
			test="#af.iso.equals('SK')"> QYYT-D-7.5.1-01</s:if></span></div>
		<div style="margin-right: 50px; float: right;"><span
			class="text"><s:date name="#af.ad"
			format="yyyy-MM-dd HH:MM:SS" nice="false" /></span></div>
		</td>
	</tr>
</table>
<table width="750" border="1" align="center" cellpadding="3"
	cellspacing="0" bordercolor="#000000" bgcolor="#000000"
	style="border-collapse: collapse">
	<tr>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">委印单位</span></td>
		<td colspan="5" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#af.client" /></span></td>
		<td width="10%" align="center" bgcolor="#FFFFFF"><span
			class="item">经手人</span></td>
		<td bgcolor="#FFFFFF" width="18%"><span class="text"><s:property
			value="#af.linkman" /></span></td>
	</tr>
	<tr>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">印品名称</span></td>
		<td colspan="5" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#af.presswork" /></span></td>
		<td width="10%" align="center" bgcolor="#FFFFFF"><span
			class="item">电话</span></td>
		<td bgcolor="#FFFFFF"><span class="text"><s:property
			value="#af.tel" /></span></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item">成品尺寸</span></td>
		<td bgcolor="#FFFFFF"><span class="text"> <s:property
			value="#af.fps" /> </span></td>
		<td width="8%" align="center" bgcolor="#FFFFFF"><span
			class="item">开本</span></td>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="text"> <s:property value="#af.format" /> </span></td>
		<td width="8%" align="center" bgcolor="#FFFFFF"><span
			class="item">订法</span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="text"> <s:property
			value="#af.bm" /> </span></td>
		<td width="10%" align="center" bgcolor="#FFFFFF"><span
			class="item">印数</span></td>
		<td bgcolor="#FFFFFF" class="text"><s:property value="#af.amount" /></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item">工
		序</span></td>
		<td colspan="7" align="center" bgcolor="#FFFFFF">
		<table width="100%" border="1" align="center" cellpadding="3"
			cellspacing="0" bordercolor="#000000" bgcolor="#000000"
			style="border-collapse: collapse">
			<tr>
				<td width="100" align="center" bgcolor="#FFFFFF"><span
					class="item">工艺名称</span></td>
				<td width="100" align="center" bgcolor="#FFFFFF"><span
					class="item">车间</span></td>
				<td width="100" align="center" bgcolor="#FFFFFF"><span
					class="item">数量</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">备注</span></td>
			</tr>
			<s:iterator value="#af.AfDispose">
				<tr>
					<td bgcolor="#FFFFFF"><span class="text"> <s:property
						value="afDItem" /> </span></td>
					<td bgcolor="#FFFFFF"><span class="text"> <s:property
						value="afDFactory" /> </span></td>
					<td bgcolor="#FFFFFF"><span class="text"> <s:property
						value="afDAmount" /> </span></td>
					<td bgcolor="#FFFFFF"><span class="text"> <s:property
						value="afDRemark" /> </span></td>
				</tr>
			</s:iterator>
		</table>
		</td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item"><br />
		备 注<br />
		<br />
		</span></td>
		<td colspan="7" align="left" valign="top" bgcolor="#FFFFFF"><span
			class="text"> <s:property value="#af.remark" /> </span></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item">送货日期</span></td>
		<td align="left" valign="top" bgcolor="#FFFFFF"><span
			class="text"> <s:date name="#af.planDeliver"
			format="yyyy年MM月dd日" nice="false" /> </span></td>
		<td align="left" valign="top" bgcolor="#FFFFFF"><span
			class="item">地点</span></td>
		<td colspan="3" align="left" valign="top" bgcolor="#FFFFFF"><span
			class="text"> <s:property value="#af.deliverAddress" /> </span></td>
		<td align="left" valign="top" bgcolor="#FFFFFF">应收金额</td>
		<td align="left" valign="top" bgcolor="#FFFFFF"><span
			class="text"> <s:property value="#af.moneyShould" /> 元</span></td>
	</tr>
</table>
<table width="750" border="0" align="center" cellpadding="8"
	cellspacing="0">
	<tr>
		<td><span class="bottomtext">接洽人: <s:property
			value="#af.cp" /></span></td>
		<td><span class="bottomtext">开单人: <s:property
			value="#af.fmp" /></span></td>
		<td><span class="bottomtext">审核人: <s:property
			value="#af.auditer" /></span></td>
		<td><span class="bottomtext">审核时间: <s:date
			name="#af.auditTime" format="yyyy-MM-dd HH:MM:SS" nice="false" /></span></td>
	</tr>
</table>
</div>
</div>
</body>
</html>