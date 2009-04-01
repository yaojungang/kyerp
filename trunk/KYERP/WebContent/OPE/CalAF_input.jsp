<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#request['AFInfo']" name="af" id="af" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>北京市清华园胶印厂计价单-No.<s:property value="#af.iso" /><s:property
	value="#af.afNo" /></title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/skin/default/AFinfoForCal.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/RMBtoBig.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/function.js"></script>
</head>
<body>
<div>
<div align="center"><img src="../Library/images/title_jjd.gif"
	width="450" height="30"></div>
</div>
<div style="float: right;"><span class="sImpactred">No.<s:property
	value="#af.iso" /><s:property value="#af.afNo" />&nbsp;</span></div>
<div><br />
<br />
<s:iterator value="#af.AfElement"></s:iterator> <br>
<form action="CalAF_save.action" method="post"><input
	type="hidden" name="afId" value="<s:property value="#af.afId" />" />
<table width="100%" border="1" align="center" cellpadding="3"
	cellspacing="0" bordercolor="#000000" bgcolor="#000000"
	style="border-collapse: collapse">
	<tr>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">委印单位</span></td>
		<td colspan="7" bgcolor="#FFFFFF"><span class="text"> <s:property
			value="#af.client" /> </span></td>
		<td width="10%" align="center" bgcolor="#FFFFFF"><span
			class="item">印单号</span></td>
		<td bgcolor="#FFFFFF" width="15%"><span class="text"> <s:property
			value="#af.pcAf" /> </span></td>
	</tr>
	<tr>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">印品名称</span></td>
		<td colspan="7" bgcolor="#FFFFFF"><span class="text"> <s:property
			value="#af.presswork" /> </span></td>
		<td width="10%" align="center" bgcolor="#FFFFFF"><span
			class="item">经手人</span></td>
		<td bgcolor="#FFFFFF"><span class="text"> <s:property
			value="#af.linkman" /> </span></td>
	</tr>
	<tr>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">成品尺寸</span></td>
		<td width="10%" bgcolor="#FFFFFF"><span class="text"> <s:property
			value="#af.fps" /> </span></td>
		<td width="8%" align="center" bgcolor="#FFFFFF">开本</td>
		<td width="8%" align="center" bgcolor="#FFFFFF"><span
			class="text"> <s:property value="#af.format" /> </span></td>
		<td width="8%" align="center" bgcolor="#FFFFFF"><span
			class="item">版次</span></td>
		<td width="8%" align="center" bgcolor="#FFFFFF"><span
			class="text"> <s:property value="#af.edition" /> </span></td>
		<td width="8%" align="center" bgcolor="#FFFFFF"><span
			class="item">订法</span></td>
		<td width="8%" align="center" bgcolor="#FFFFFF"><span
			class="text"> <s:property value="#af.bm" /> </span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="item">电话</span></td>
		<td bgcolor="#FFFFFF" class="text"><s:property value="#af.tel" /></td>
	</tr>
	<tr>
		<td colspan="10" align="center" bgcolor="#f0f0f0"><span
			class="bigitem">主要元件</span></td>
	</tr>
	<tr>
		<td colspan="10" align="center" bgcolor="#FFFFFF">
		<table width="100%" border="1" cellpadding="3" cellspacing="0"
			bordercolor="#000000" style="border-collapse: collapse">
			<tr>
				<td align="center" bgcolor="#FFFFFF"><span class="item">类型</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">机型</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">印张</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">印数</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">开本</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">墨色</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">印版</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">用纸</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">纸数</span></td>
			</tr>
			<s:iterator value="#af.AfElement">
				<tr>
					<td align="center" bgcolor="#FFFFFF" class="text"><s:if
						test="EType.equals('BB')">正文</s:if> <s:if
						test="EType.equals('Cover')">封面</s:if> <s:if
						test="EType.equals('CI')">插页</s:if></td>
					<td align="center" bgcolor="#FFFFFF" class="text"><s:property
						value="EMachine" /></td>
					<td align="center" bgcolor="#FFFFFF" class="text"><s:property
						value="EPs" /></td>
					<td align="center" bgcolor="#FFFFFF" class="text"><s:property
						value="EAmount" /></td>
					<td align="center" bgcolor="#FFFFFF" class="text"><s:property
						value="EFormat" /></td>
					<td align="center" bgcolor="#FFFFFF" class="text"><s:property
						value="EColorFrontN" />+<s:property value="EColorBackN" /></td>
					<td align="center" bgcolor="#FFFFFF" class="text"><s:property
						value="EPlateType" />/<s:property value="EPlateAmount" /></td>
					<td align="center" bgcolor="#FFFFFF" class="text">(<s:property
						value="EPaperFrom" />)<s:property value="EPaper" /></td>
					<td align="center" bgcolor="#FFFFFF" class="text"><s:property
						value="EReam" />+<s:property value="EOvers" />=<SCRIPT
						type="text/javascript">document.write(fixfloat(<s:property value="EReam+EOvers" />,3));</SCRIPT></td>
				</tr>
			</s:iterator>
		</table>
		</td>
	</tr>
	<tr>
		<td colspan="10" align="center" bgcolor="#f0f0f0"><span
			class="bigitem">其它项目</span></td>
	</tr>
	<tr>
		<td colspan="2" align="center" bgcolor="#FFFFFF"><span
			class="item">工艺类型</span></td>
		<td colspan="3" align="center" bgcolor="#FFFFFF"><span
			class="item">工艺名称</span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="item">数量</span></td>
		<td colspan="3" align="center" bgcolor="#FFFFFF"><span
			class="item">工厂</span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="item">备注</span></td>
	</tr>
	<s:iterator value="#af.AfDispose">
		<tr>
			<td colspan="2" align="center" bgcolor="#FFFFFF"><span
				class="text"> <s:property value="afEType" /> </span></td>
			<td colspan="3" align="center" bgcolor="#FFFFFF"><span
				class="text"> <s:property value="afDItem" /> </span></td>
			<td align="center" bgcolor="#FFFFFF"><span class="text">
			<s:property value="afDAmount" /> </span></td>
			<td colspan="3" align="center" bgcolor="#FFFFFF"><span
				class="text"> <s:property value="afDFactory" /> </span></td>
			<td align="center" bgcolor="#FFFFFF"><span class="text">
			<s:property value="afDRemark" /> </span></td>
		</tr>
	</s:iterator>
	<tr>
		<td colspan="10" align="center" bgcolor="#f0f0f0"><span
			class="bigitem">计价明细</span></td>
	</tr>
	<tr>
		<td colspan="10" align="center" bgcolor="#FFFFFF">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top">
				<table width="100%" border="1" align="center" cellpadding="4"
					bordercolor="#000000" style="border-collapse: collapse">
					<tr>
						<td width="18%" align="center"><span class="item">项目名称</span></td>
						<td width="10%" align="center"><span class="item">车间</span></td>
						<td width="10%" align="center"><span class="text">单价</span></td>
						<td align="left"><span class="text">算法</span></td>
						<td width="12%" align="center"><span class="text">金额</span></td>
					</tr>
					<s:iterator value="#af.AfValuation" id="bj" status="st">
						<tr>
							<td align="center"><INPUT type="hidden"
								name="bj[<s:property value="#st.index" />].afVId"
								value="<s:property value="#bj.afVId" />" /> <INPUT
								name="bj[<s:property value="#st.index" />].itemName"
								value="<s:property value="#bj.itemName" />" /></td>
							<td align="center"><select
								name="bj[<s:property value="#st.index" />].chejian">
								<option value="Del">删除</option>
								<option value="照排"
									<s:if test="#bj.chejian.equals('照排')">selected="selected"</s:if>>照排</option>
								<option value="制版车间"
									<s:if test="#bj.chejian.equals('制版车间')">selected="selected"</s:if>>制版车间</option>
								<option value="四色"
									<s:if test="#bj.chejian.equals('四色')">selected="selected"</s:if>>四色</option>
								<option value="双面"
									<s:if test="#bj.chejian.equals('双面')">selected="selected"</s:if>>双面</option>
								<option value="05"
									<s:if test="#bj.chejian.equals('05')">selected="selected"</s:if>>05</option>
								<option value="02"
									<s:if test="#bj.chejian.equals('02')">selected="selected"</s:if>>02</option>
								<option value="小制版"
									<s:if test="#bj.chejian.equals('小制版')">selected="selected"</s:if>>小制版</option>
								<option value="装订"
									<s:if test="#bj.chejian.equals('装订')">selected="selected"</s:if>>装订</option>
								<option value="库房"
									<s:if test="#bj.chejian.equals('库房')">selected="selected"</s:if>>库房</option>
								<option value="外加工"
									<s:if test="#bj.chejian.equals('外加工')">selected="selected"</s:if>>外加工</option>
								<option value="其它"
									<s:if test="#bj.chejian.equals('其它')">selected="selected"</s:if>>其它</option>
							</select></td>
							<td align="center"><INPUT
								name="bj[<s:property value="#st.index" />].danjia"
								value="<s:property value="#bj.danjia" />" style="width: 80px;" /></td>
							<td align="left"><INPUT
								name="bj[<s:property value="#st.index" />].calProcess"
								value="<s:property value="#bj.calProcess" />"
								style="width: 280px;" /></td>
							<td align="center"><INPUT
								name="bj[<s:property value="#st.index" />].totalAmount"
								value='<s:property value="#bj.totalAmount" />'
								style="width: 80px;" /></td>
						</tr>
					</s:iterator>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td colspan="10" align="center" bgcolor="#f0f0f0"><span
			class="bigitem">统 计</span></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item">接洽人</span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="text"> <s:property
			value="#af.cp" /> </span></td>
		<td colspan="2" align="center" bgcolor="#FFFFFF"><span
			class="item">应收金额(大写)</span></td>
		<td colspan="4" align="left" bgcolor="#FFFFFF"><s:if
			test="#af.moneyShould > 0">
			<span class="text"><script type="text/javascript">
var ysBig = cmycurd(<s:property value="#af.moneyShould" />);
document.write(ysBig);</script></span>
		</s:if></td>
		<td align="center" bgcolor="#FFFFFF"><span class="item">应收金额</span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#af.moneyShould" /></span></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item">交款人</span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="text"> <s:property
			value="#af.moneyGiveMan" /> </span></td>
		<td colspan="2" align="center" bgcolor="#FFFFFF"><span
			class="item">实收金额(大写)</span></td>
		<td colspan="4" align="left" bgcolor="#FFFFFF"><s:if
			test="#af.moneyFact > 0">
			<span class="text"><script type="text/javascript">
var ssBig = cmycurd(<s:property value="#af.moneyFact" />);
document.write(ssBig);</script></span>
		</s:if></td>
		<td align="center" bgcolor="#FFFFFF"><span class="item">实收金额</span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="text"> <s:property
			value="#af.moneyFact" /> </span></td>
	</tr>
	<tr>
		<td colspan="10" align="center" bgcolor="#F0F0F0"><span
			class="bigitem">计价备注</span></td>
	</tr>
	<tr>
		<td colspan="10" align="center" bgcolor="#FFFFFF"><textarea
			name="moneyInputRemark" cols="60" rows="5" id="moneyInputRemark"><s:property
			value="#af.moneyInputRemark" /></textarea></td>
	</tr>
</table>
<center><s:submit value="提交"></s:submit></center>
</form>
</div>
</body>
</html>
