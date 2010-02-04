<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#request['AFInfo']" name="af" id="af" />
<s:set value="#session['user']" name="user" id="user" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>北京市清华园胶印厂计价单-<s:property value="#af.iso" /><s:property
	value="#af.afNo" /></title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/Library/css/AFinfo_Cal.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/RMBtoBig.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/function.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/DatePicker/WdatePicker.js"></script>
</head>
<body>
<table width="100%" border="0" align="center" cellpadding="3"
	cellspacing="0">
	<tr>
		<td align="left"><s:form action="CalAF_print" namespace="/OPE"
			method="get" id="forms" target="_blank">
			<input type="hidden" name="afId"
				value="<s:property value="#af.afId" />" />
			<input type="submit" value="打印计价单" />
		</s:form> <s:if
			test="#user.userType.equals('Admin') or 'CalAF' in #privilegesList">
			<s:form action="CalAF_edit" namespace="/OPE" method="get">
				<input type="hidden" name="afId"
					value="<s:property value="#af.afId" />" />
				<input type="submit" value="修改计价单" />
			</s:form>
		</s:if> <s:form action="AFInfo" namespace="/AF" method="get">
			<input type="hidden" name="afId"
				value="<s:property value="#af.afId" />" />
			<input type="submit" value="查看任务单" />
		</s:form></td>
	</tr>
</table>
<div id="MainForm">
<form action="CalAF_MoneyInput_save.action" class="cssform"
	method="post"><input type="hidden" name="afId"
	value="<s:property value="#af.afId" />" />
<p><label for="moneyGiveMan">交款人</label> <input type="text"
	name="moneyGiveMan"
	value='<s:if test="%{#af.moneyGiveMan != null}"><s:property value="#af.moneyGiveMan" /></s:if><s:else><s:property value="#af.linkman" /></s:else>' />
</p>
<p><label for="moneyShould">应收金额</label> <s:property
	value="#af.moneyShould" /></p>
<p><label for="moneyFact">实收金额</label> <input type="text"
	style="color: red;" name="moneyFact" id="moneyFact"
	value='<s:if test="%{#af.moneyFact >0}"><s:property value="#af.moneyFact" /></s:if><s:else><s:if test="%{#af.moneyShould >0}"><s:property value="#af.moneyShould" /></s:if></s:else>' />
</p>
<p><label for="faPiaoStatus">发票状态</label> <input name="fapiaoStatus"
	type="radio" id="fapiaoStatus" value="1"
	onClick="document.getElementById('fapiaoTime').value=''"
	<s:if test="%{#af.fapiaoStatus == 1}">checked </s:if>> 发票未开 <input
	type="radio" name="fapiaoStatus" id="fapiaoStatus" value="0"
	<s:if test="%{#af.fapiaoStatus == 0}">checked</s:if> /> 已开发票</p>
<p><label for="fapiaoTime">开票时间</label> <input type="text"
	name="fapiaoTime"
	onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
	value='<s:date name="#af.fapiaoTime" format="yyyy-MM-dd HH:mm:ss" nice="false"/>' />
</p>
<p><label for="moneyStatus">结款状态</label> <input type="radio"
	name="moneyStatus" id="moneyStatus" value="1"
	<s:if test="%{#af.moneyStatus == 1}">checked</s:if> /> 未结清 <input
	type="radio" name="moneyStatus" id="moneyStatus" value="0"
	<s:if test="%{#af.moneyStatus == 0}">checked</s:if> /> 结清</p>
<p><label for="moneyTime">交款时间</label> <input type="text"
	name="moneyTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
	value='<s:date name="#af.moneyTime" format="yyyy-MM-dd HH:mm:ss" nice="false"/>' />
</p>
<p><label for="moneyRemark">结款备注</label> <textarea
	name="moneyRemark" id="moneyRemark" cols="45" rows="3"><s:property
	value="#af.moneyRemark" /></textarea></p>
<div style="margin-left: 150px;"><input type="submit" value="提交" />
<input type="reset" value="重置" /></div>
</form>
</div>
<br>
<br>
<hr>
<table width="750" border="0" align="center" cellpadding="3"
	cellspacing="0">
	<tr>
		<td height="50" colspan="3" valign="middle">
		<div align="center"><img src="../Library/images/title_jjd.gif"
			width="450" height="30"></div>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2" align="right"><span class="item"> <s:date
			name="#af.ad" nice="false" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<span class="sImpactred">No. <s:property value="#af.iso" /><s:property
			value="#af.afNo" /> &nbsp;</span></td>
	</tr>
</table>
<div>
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
			class="item">项目名称</span></td>
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
				<td width="70%" valign="top">
				<table width="100%" border="1" align="center" cellpadding="4"
					cellspacing="0" bordercolor="#000000"
					style="border-collapse: collapse">
					<tr>
						<td width="80" align="center"><span class="item">计价项目</span></td>
						<td width="80" align="center"><span class="item">车间</span></td>
						<td width="40" align="center"><span class="item">单价</span></td>
						<td align="left"><span class="item">备注</span></td>
						<td width="70" align="center"><span class="item">金额</span></td>
					</tr>
					<s:iterator value="#af.AfValuation" id="bj" status="st">
						<tr>
							<td align="left"><span class="text"> <s:property
								value="#bj.itemName" /> </span></td>
							<td align="center"><span class="text"> <s:property
								value="#bj.chejian" /> </span></td>
							<td align="left"><span class="text"> <s:property
								value="#bj.danjia" /> </span></td>
							<td align="left"><span class="text"> <s:property
								value="#bj.calProcess" /> </span></td>
							<td align="left"><span class="text"> <s:property
								value="#bj.totalAmount" /> </span></td>
						</tr>
					</s:iterator>
				</table>
				</td>
				<td width="4" valign="top"></td>
				<td valign="top">
				<table width="100%" border="1" align="center" cellpadding="4"
					cellspacing="0" bordercolor="#000000"
					style="border-collapse: collapse">
					<tr>
						<td width="80" align="center"><span class="item">车间</span></td>
						<td align="center"><span class="item">金额</span></td>
					</tr>
					<s:set name="zpn" value="0" />
					<s:iterator value="#af.AfValuation" id="zp" status="st">
						<s:if test="#zp.chejian.equals('照排')">
							<s:set name="zpm" value="#zpn = #zpn + #zp.totalAmount" />
						</s:if>
					</s:iterator>
					<s:if test="#zpn>0">
						<tr>
							<td><span class="text">照排</span></td>
							<td><span class="text"> <SCRIPT
								type="text/javascript">document.write(fixfloat(<s:property value="#zpn" />,2));</SCRIPT>
							</span></td>
						</tr>
					</s:if>
					<s:set name="zbn" value="0" />
					<s:iterator value="#af.AfValuation" id="zb" status="st">
						<s:if test="#zb.chejian.equals('制版车间')">
							<s:set name="zbm" value="#zbn = #zbn + #zb.totalAmount" />
						</s:if>
					</s:iterator>
					<s:if test="#zbn>0">
						<tr>
							<td><span class="text">制版车间</span></td>
							<td><span class="text"> <SCRIPT
								type="text/javascript">document.write(fixfloat(<s:property value="#zbn" />,2));</SCRIPT>
							</span></td>
						</tr>
					</s:if>
					<s:set name="ssn" value="0" />
					<s:iterator value="#af.AfValuation" id="ss" status="st">
						<s:if test="#ss.chejian.equals('四色')">
							<s:set name="ssm" value="#ssn = #ssn + #ss.totalAmount" />
						</s:if>
					</s:iterator>
					<s:if test="#ssn>0">
						<tr>
							<td><span class="text">四色</span></td>
							<td><span class="text"> <SCRIPT
								type="text/javascript">document.write(fixfloat(<s:property value="#ssn" />,2));</SCRIPT>
							</span></td>
						</tr>
					</s:if>
					<s:set name="smn" value="0" />
					<s:iterator value="#af.AfValuation" id="sm" status="st">
						<s:if test="#sm.chejian.equals('双面')">
							<s:set name="smm" value="#smn = #smn + #sm.totalAmount" />
						</s:if>
					</s:iterator>
					<s:if test="#smn>0">
						<tr>
							<td><span class="text">双面</span></td>
							<td><span class="text"> <SCRIPT
								type="text/javascript">document.write(fixfloat(<s:property value="#smn" />,2));</SCRIPT>
							</span></td>
						</tr>
					</s:if>
					<s:set name="j05n" value="0" />
					<s:iterator value="#af.AfValuation" id="j05" status="st">
						<s:if test="#j05.chejian.equals('05')">
							<s:set name="j05m" value="#j05n = #j05n + #j05.totalAmount" />
						</s:if>
					</s:iterator>
					<s:if test="#j05n>0">
						<tr>
							<td><span class="text">05</span></td>
							<td><span class="text"> <SCRIPT
								type="text/javascript">document.write(fixfloat(<s:property value="#j05n" />,2));</SCRIPT>
							</span></td>
						</tr>
					</s:if>
					<s:set name="j02n" value="0" />
					<s:iterator value="#af.AfValuation" id="j02" status="st">
						<s:if test="#j02.chejian.equals('02')">
							<s:set name="j02m" value="#j02n = #j02n + #j02.totalAmount" />
						</s:if>
					</s:iterator>
					<s:if test="#j02n>0">
						<tr>
							<td><span class="text">02</span></td>
							<td><span class="text"> <SCRIPT
								type="text/javascript">document.write(fixfloat(<s:property value="#j02n" />,2));</SCRIPT>
							</span></td>
						</tr>
					</s:if>
					<s:set name="xzbn" value="0" />
					<s:iterator value="#af.AfValuation" id="xzb" status="st">
						<s:if test="#xzb.chejian.equals('小制版')">
							<s:set name="xzbm" value="#xzbn = #xzbn + #xzb.totalAmount" />
						</s:if>
					</s:iterator>
					<s:if test="#xzbn>0">
						<tr>
							<td><span class="text">小制版</span></td>
							<td><span class="text"> <SCRIPT
								type="text/javascript">document.write(fixfloat(<s:property value="#xzbn" />,2));</SCRIPT>
							</span></td>
						</tr>
					</s:if>
					<s:set name="zdn" value="0" />
					<s:iterator value="#af.AfValuation" id="zd" status="st">
						<s:if test="#zd.chejian.equals('装订')">
							<s:set name="zdm" value="#zdn = #zdn + #zd.totalAmount" />
						</s:if>
					</s:iterator>
					<s:if test="#zdn>0">
						<tr>
							<td><span class="text">装订</span></td>
							<td><span class="text"> <SCRIPT
								type="text/javascript">document.write(fixfloat(<s:property value="#zdn" />,2));</SCRIPT>
							</span></td>
						</tr>
					</s:if>
					<s:set name="kfn" value="0" />
					<s:iterator value="#af.AfValuation" id="kf" status="st">
						<s:if test="#kf.chejian.equals('库房')">
							<s:set name="kfm" value="#kfn = #kfn + #kf.totalAmount" />
						</s:if>
					</s:iterator>
					<s:if test="#kfn>0">
						<tr>
							<td><span class="text">库房</span></td>
							<td><span class="text"> <SCRIPT
								type="text/javascript">document.write(fixfloat(<s:property value="#kfn" />,2));</SCRIPT>
							</span></td>
						</tr>
					</s:if>
					<s:set name="wjgn" value="0" />
					<s:iterator value="#af.AfValuation" id="wjg" status="st">
						<s:if test="#wjg.chejian.equals('外加工')">
							<s:set name="wjgm" value="#wjgn = #wjgn + #wjg.totalAmount" />
						</s:if>
					</s:iterator>
					<s:if test="#wjgn>0">
						<tr>
							<td><span class="text">外加工</span></td>
							<td><span class="text"> <SCRIPT
								type="text/javascript">document.write(fixfloat(<s:property value="#wjgn" />,2));</SCRIPT>
							</span></td>
						</tr>
					</s:if>
					<s:set name="qtn" value="0" />
					<s:iterator value="#af.AfValuation" id="qt" status="st">
						<s:if test="#qt.chejian.equals('其它')">
							<s:set name="qtm" value="#qtn = #qtn + #qt.totalAmount" />
						</s:if>
					</s:iterator>
					<s:if test="#qtn>0">
						<tr>
							<td><span class="text">其它</span></td>
							<td><span class="text"> <SCRIPT
								type="text/javascript">document.write(fixfloat(<s:property value="#qtn" />,2));</SCRIPT>
							</span></td>
						</tr>
					</s:if>
					<tr>
						<td align="center"><span class="item">总计</span></td>
						<td align="center"><span class="text"><s:property
							value="#af.moneyShould" /></span></td>
					</tr>
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
		<td align="center" bgcolor="#FFFFFF"><span class="text"> </span></td>
		<td colspan="2" align="center" bgcolor="#FFFFFF"><span
			class="item">实收金额(大写)</span></td>
		<td colspan="4" align="left" bgcolor="#FFFFFF"><s:if
			test="#af.moneyFact > 0">
			<span class="text"><script type="text/javascript">
var ssBig = cmycurd(<s:property value="#af.moneyFact" />);
document.write(ssBig);</script></span>
		</s:if></td>
		<td align="center" bgcolor="#FFFFFF"><span class="item">实收金额</span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="text"> </span></td>
	</tr>
</table>
<table width="100%" border="0" align="center" cellpadding="8"
	cellspacing="0">
	<tr>
		<td><span class="bottomtext">计价员: <s:property
			value="#af.moneyInputMan" /></span></td>
		<td><span class="bottomtext">计价时间: <s:date
			name="#af.moneyInputTime" format="yyyy-MM-dd HH:MM:SS" nice="false" /></span></td>
	</tr>
</table>
</div>
</body>
</html>
