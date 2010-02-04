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
	<li><a href="#" onClick="printdiv('AFInfo');">打印任务单</a></li>
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
			src="<%=request.getContextPath() %>/Library/images/title_rwd.jpg"></td>
		<td width="185" rowspan="2" align="center" valign="bottom">&nbsp;</td>
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
			class="item">印单号</span></td>
		<td colspan="2" bgcolor="#FFFFFF" width="18%"><span class="text"><s:property
			value="#af.pcAf" /></span></td>
	</tr>
	<tr>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">印品名称</span></td>
		<td colspan="5" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#af.presswork" /></span></td>
		<td width="10%" align="center" bgcolor="#FFFFFF"><span
			class="item">经手人</span></td>
		<td colspan="2" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#af.linkman" /></span></td>
	</tr>
	<tr>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">丛书名称</span></td>
		<td colspan="5" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#af.seriesName" /></span></td>
		<td width="10%" align="center" bgcolor="#FFFFFF"><span
			class="item">电话</span></td>
		<td colspan="2" bgcolor="#FFFFFF" class="text"><s:property
			value="#af.tel" /></td>
	</tr>
	<tr>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">书号</span></td>
		<td colspan="3" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#af.isbn" /></span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="item">版次</span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#af.edition" /></span></td>
		<td width="10%" align="center" bgcolor="#FFFFFF"><span
			class="item">印数</span></td>
		<td colspan="2" bgcolor="#FFFFFF" class="text"><s:property
			value="#af.amount" /></td>
	</tr>
	<tr>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">成品尺寸</span></td>
		<td bgcolor="#FFFFFF"><span class="text"><s:property
			value="#af.fps" /></span></td>
		<td width="8%" align="center" bgcolor="#FFFFFF"><span
			class="item">开本</span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#af.format" /></span></td>
		<td width="8%" align="center" bgcolor="#FFFFFF"><span
			class="item">订法</span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#af.bm" /></span></td>
		<td width="10%" align="center" bgcolor="#FFFFFF"><span
			class="item">总印张</span></td>
		<td colspan="2" bgcolor="#FFFFFF">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="9" align="center" bgcolor="#FFFFFF">
		<table width="100%" border="1" align="center" cellpadding="3"
			cellspacing="0" bordercolor="#000000" bgcolor="#000000"
			style="border-collapse: collapse">
			<tr>
				<td width="3%" rowspan="4" align="center" bgcolor="#FFFFFF"><span
					class="etype"> 正<br />
				<br />
				文</span></td>
				<td width="10%" align="center" bgcolor="#FFFFFF"><span
					class="item">机型</span></td>
				<td width="12%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EMachine" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">印张</span></td>
				<td width="10%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EPs" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">墨色</span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EColorFrontN" />+<s:property
					value="EColorBackN" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">用纸</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="text">
				<s:property value="EPaper" /> </span></td>
			</tr>
			<tr>
				<td align="center" bgcolor="#FFFFFF"><span class="item">用纸规格</span></td>
				<td width="12%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="ESs" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">开本</span></td>
				<td width="10%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EFormat" /> </span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">P数</span></td>
				<td width="6%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EP" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">来源</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="text">
				<s:property value="EPaperFrom" /> </span></td>
			</tr>
			<tr>
				<td align="center" bgcolor="#FFFFFF"><span class="item">制版日期</span></td>
				<td width="12%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:date name="EPlanPm" format="MM月dd日"
					nice="false" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">正色</span></td>
				<td width="10%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EColorFront" /> </span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">印版</span></td>
				<td width="6%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EPlateType" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">令数</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="text">
				<s:property value="EReam" /> + <s:property value="EOvers" /> </span></td>
			</tr>
			<tr>
				<td align="center" bgcolor="#FFFFFF"><span class="item">印刷日期</span></td>
				<td width="12%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:date name="EPlanPress" format="MM月dd日"
					nice="false" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">背色</span></td>
				<td width="10%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EColorBack" /> </span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">版数</span></td>
				<td width="6%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EPlateAmount" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">总计</span></td>
				<td align="center" bgcolor="#FFFFFF"></td>
			</tr>
		</table>
		<table width="100%" border="1" align="center" cellpadding="3"
			cellspacing="0" bordercolor="#000000" bgcolor="#000000"
			style="border-collapse: collapse">
			<tr>
				<td width="3%" rowspan="4" align="center" bgcolor="#FFFFFF"><span
					class="etype"> 封<br />
				<br />
				面</span></td>
				<td width="10%" align="center" bgcolor="#FFFFFF"><span
					class="item">机型</span></td>
				<td width="12%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EMachine" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">印张</span></td>
				<td width="10%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EPs" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">墨色</span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EColorFrontN" />+<s:property
					value="EColorBackN" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">用纸</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="text">
				<s:property value="EPaper" /> </span></td>
			</tr>
			<tr>
				<td align="center" bgcolor="#FFFFFF"><span class="item">用纸规格</span></td>
				<td width="12%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="ESs" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">开本</span></td>
				<td width="10%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EFormat" /> </span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">P数</span></td>
				<td width="6%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EP" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">来源</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="text">
				<s:property value="EPaperFrom" /> </span></td>
			</tr>
			<tr>
				<td align="center" bgcolor="#FFFFFF"><span class="item">制版日期</span></td>
				<td width="12%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:date name="EPlanPm" format="MM月dd日"
					nice="false" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">正色</span></td>
				<td width="10%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EColorFront" /> </span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">印版</span></td>
				<td width="6%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EPlateType" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">令数</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="text">
				<s:property value="EReam" /> + <s:property value="EOvers" /> </span></td>
			</tr>
			<tr>
				<td align="center" bgcolor="#FFFFFF"><span class="item">印刷日期</span></td>
				<td width="12%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:date name="EPlanPress" format="MM月dd日"
					nice="false" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">背色</span></td>
				<td width="10%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EColorBack" /> </span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">版数</span></td>
				<td width="6%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EPlateAmount" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">总计</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="text"><SCRIPT
					type="text/javascript">document.write(fixfloat(<s:property value="EReam+EOvers" />,3));</SCRIPT></span></td>
			</tr>
		</table>
		<table width="100%" border="1" align="center" cellpadding="3"
			cellspacing="0" bordercolor="#000000" bgcolor="#000000"
			style="border-collapse: collapse">
			<tr>
				<td width="3%" rowspan="4" align="center" bgcolor="#FFFFFF"><span
					class="etype">插<br />
				<br />
				页</span></td>
				<td width="10%" align="center" bgcolor="#FFFFFF"><span
					class="item">机型</span></td>
				<td width="12%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EMachine" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">印张</span></td>
				<td width="10%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EPs" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">墨色</span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EColorFrontN" />+<s:property
					value="EColorBackN" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">用纸</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="text">
				<s:property value="EPaper" /> </span></td>
			</tr>
			<tr>
				<td align="center" bgcolor="#FFFFFF"><span class="item">用纸规格</span></td>
				<td width="12%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="ESs" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">开本</span></td>
				<td width="10%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EFormat" /> </span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">P数</span></td>
				<td width="6%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EP" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">来源</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="text">
				<s:property value="EPaperFrom" /> </span></td>
			</tr>
			<tr>
				<td align="center" bgcolor="#FFFFFF"><span class="item">制版日期</span></td>
				<td width="12%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:date name="EPlanPm" format="MM月dd日"
					nice="false" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">正色</span></td>
				<td width="10%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EColorFront" /> </span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">印版</span></td>
				<td width="6%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EPlateType" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">令数</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="text">
				<s:property value="EReam" /> + <s:property value="EOvers" /> </span></td>
			</tr>
			<tr>
				<td align="center" bgcolor="#FFFFFF"><span class="item">印刷日期</span></td>
				<td width="12%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:date name="EPlanPress" format="MM月dd日"
					nice="false" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">背色</span></td>
				<td width="10%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EColorBack" /> </span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">版数</span></td>
				<td width="6%" align="center" bgcolor="#FFFFFF"><span
					class="text"> <s:property value="EPlateAmount" /> </span></td>
				<td width="8%" align="center" bgcolor="#FFFFFF"><span
					class="item">总计</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="text"><SCRIPT
					type="text/javascript">document.write(fixfloat(<s:property value="EReam+EOvers" />,3));</SCRIPT></span></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item">其它项目</span></td>
		<td colspan="8" align="center" bgcolor="#FFFFFF">
		<table width="100%" border="1" align="center" cellpadding="3"
			cellspacing="0" bordercolor="#000000" bgcolor="#000000"
			style="border-collapse: collapse">
			<tr>
				<td align="center" bgcolor="#FFFFFF"><span class="item">项目名称</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">工艺名称</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">数量</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">工厂</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">备注</span></td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF"><span class="text">&nbsp; <s:property
					value="afEType" /> </span></td>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afDItem" /> </span></td>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afDAmount" /> </span></td>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afDFactory" /> </span></td>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afDRemark" /> </span></td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afEType" /> &nbsp;</span></td>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afDItem" /> </span></td>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afDAmount" /> </span></td>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afDFactory" /> </span></td>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afDRemark" /> </span></td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afEType" /> &nbsp;</span></td>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afDItem" /> </span></td>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afDAmount" /> </span></td>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afDFactory" /> </span></td>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afDRemark" /> </span></td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afEType" /> &nbsp;</span></td>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afDItem" /> </span></td>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afDAmount" /> </span></td>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afDFactory" /> </span></td>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afDRemark" /> </span></td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afEType" /> &nbsp;</span></td>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afDItem" /> </span></td>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afDAmount" /> </span></td>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afDFactory" /> </span></td>
				<td bgcolor="#FFFFFF"><span class="text"> <s:property
					value="afDRemark" /> </span></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item">送货日期</span></td>
		<td colspan="2" align="center" bgcolor="#FFFFFF"><span
			class="text"> <s:date name="#af.planDeliver"
			format="yyyy年MM月dd日" nice="false" /> </span></td>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">送货地点</span></td>
		<td colspan="5" align="left" bgcolor="#FFFFFF"><span class="text">
		<s:property value="#af.deliverAddress" /> </span></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item">印装次序<br />
		及<br />
		质量要求</span></td>
		<td colspan="8" align="left" valign="top" bgcolor="#FFFFFF"><span
			class="text"> <s:property value="#af.oq" /> </span></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item"><br>
		<br />
		备 注<br />
		<br>
		<br>
		<br />
		</span></td>
		<td colspan="8" align="left" valign="top" bgcolor="#FFFFFF"><span
			class="text"> <s:property value="#af.remark" /> </span></td>
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
<table width="750" border="0" align="center" cellpadding="8"
	cellspacing="0">
	<tr>
		<td width="50"><span class="bottomtext">注意: </span></td>
		<td>
		<ol>
			<li>本任务单为系统发生错误或其它特殊情况时,所使用的临时任务单;</li>
			<li>本任务单必须生产科主管领导签字方可生效使用;</li>
			<li>系统恢复正常后要及时录入系统;</li>
			<li>产值等数据以系统中的数据为主</li>
		</ol>
		</td>
	</tr>
</table>
</div>
</div>
</body>
</html>