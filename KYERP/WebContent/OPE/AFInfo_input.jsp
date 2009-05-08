<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#request['AFInfo']" name="af" id="af" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>清华园胶印厂生产任务单-<s:property value="#af.iso" /><s:property
	value="#af.afNo" /></title>
<script type="text/javascript"
	src="<s:url value="/Library/js/Validator.js" />"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/DatePicker/WdatePicker.js"></script>
<!--media=print 这个属性可以在打印时有效-->
<style media="print">
TABLE {
	display: none
}
</style>
<style media="screen">
.tips {
	display: none
}
</style>
<link
	href="${pageContext.request.contextPath}/Library/css/AFinfo_input.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/function.js">
</script>
<script type="text/javascript">
function checkAF(){

	var colorN=document.getElementById('EColorFrontN').value+document.getElementById('EColorBackN').value;
    if(colorN<1){
		alert("正面和背面的颜色数不能都为零，请检查！");
		return false;
    }
    return Validator.Validate(document.forms,2);
}
</script>
</head>
<body>
<form action="saveAF" method="post" id="forms" name="forms"
	onSubmit="return checkAF()">
	<s:token></s:token>
	<input
	name="af.afType" type="hidden" value="<s:property value="#af.afType"/>" />
<table width="98%" border="0" align="center" cellpadding="3"
	cellspacing="0">
	<tr>
		<td width="21%"><s:if test="#af.afNo>1">
			<input type="hidden" name="af.afId" value='<s:property value="#af.afId"/>' />
			<input type="hidden" name="af.viewTimes" value='<s:property value="#af.viewTimes"/>' />
		</s:if></td>
		<td width="58%">&nbsp;</td>
		<td width="21%">&nbsp;</td>
	</tr>
	<tr>
		<td height="50" colspan="3" valign="middle">
		<div align="center"><img src="../Library/images/title_rwd.jpg"
			width="450" height="30"></div>
		</td>
	</tr>
	<tr>
		<td align="center"><s:if test="#af.afNo>1">
			<s:hidden name="af.iso" value="%{#af.iso}" />
		</s:if> <s:hidden name="af.ad" value="%{#af.ad}" /> <s:if test="#af.afNo<1">
			<select name="af.iso">
				<option value="SK" <s:if test="#af.iso.equals('SK')">selected</s:if>>受控</option>
				<option value="LH" <s:if test="#af.iso.equals('LH')">selected</s:if>>零活</option>
			</select>
		</s:if></td>
		<td colspan="2" align="right">进度要求<select name="af.timeRank">
			<option value="0" <s:if test="#af.timeRank==0">selected</s:if>>一般</option>
			<option value="1" <s:if test="#af.timeRank==1">selected</s:if>>紧急</option>
		</select> &nbsp;&nbsp;是否本厂装订<select name="af.ourbinding">
			<option value="0" <s:if test="#af.ourbinding==0">selected</s:if>>否</option>
			<option value="1" <s:if test="#af.ourbinding==1">selected</s:if>>是</option>
		</select><s:property value="#af.ad" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<s:if test="#af.afNo>1">No.<s:property value="#af.iso" />
			<s:property value="#af.afNo" />
			<s:hidden name="af.afNo" value="%{#af.afNo}" />
		</s:if></td>
	</tr>
</table>
<table width="100%" border="1" align="center" cellpadding="3"
	cellspacing="0" bordercolor="#000000" bgcolor="#000000"
	style="border-collapse: collapse">
	<tr>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">委印单位</span></td>
		<td colspan="5" bgcolor="#FFFFFF" align="left"><span class="text"><s:textfield
			name="af.client" value="%{#af.client}" cssStyle="width:380px;" /></span></td>
		<td width="10%" align="center" bgcolor="#FFFFFF"><span
			class="item">印单号</span></td>
		<td colspan="2" bgcolor="#FFFFFF" width="18%"><span class="text"><s:textfield
			name="af.pcAf" value="%{#af.pcAf}" /></span></td>
	</tr>
	<tr>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">印品名称</span></td>
		<td colspan="5" bgcolor="#FFFFFF" align="left"><span class="text"><input
			name="af.presswork" value="<s:property value="#af.presswork" />"
			dataType="Require" msg="[印品名称]必须填写" style="width: 380px;" /></span></td>
		<td width="10%" align="center" bgcolor="#FFFFFF"><span
			class="item">经手人</span></td>
		<td colspan="2" bgcolor="#FFFFFF"><span class="text"><s:textfield
			name="af.linkman" value="%{#af.linkman}" /></span></td>
	</tr>
	<tr>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">丛书名称</span></td>
		<td colspan="5" bgcolor="#FFFFFF" align="left"><span class="text"><s:textfield
			name="af.seriesName" value="%{#af.seriesName}"
			cssStyle="width:380px;" /></span></td>
		<td width="10%" align="center" bgcolor="#FFFFFF"><span
			class="item">电话</span></td>
		<td colspan="2" bgcolor="#FFFFFF" class="text"><s:textfield
			name="af.tel" value="%{#af.tel}" /></td>
	</tr>
	<tr>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">书号</span></td>
		<td colspan="3" bgcolor="#FFFFFF" align="left"><span class="text"><s:textfield
			name="af.isbn" value="%{#af.isbn}" cssStyle="width:380px;" /></span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="item">版次</span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="text"><s:textfield
			name="af.edition" value="%{#af.edition}" /></span></td>
		<td width="10%" align="center" bgcolor="#FFFFFF"><span
			class="item">印数</span></td>
		<td colspan="2" bgcolor="#FFFFFF" class="text"><input
			name="af.amount" value="<s:property value="#af.amount" />"
			dataType="Integer" msg="[印数]请填写整数" /></td>
	</tr>
	<tr>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">成品尺寸</span></td>
		<td bgcolor="#FFFFFF"><span class="text"><input
			name="af.fps" value="<s:property value="#af.fps" />"
			dataType="Require" msg="[成品尺寸]必须填写" /></span></td>
		<td width="8%" align="center" bgcolor="#FFFFFF"><span
			class="item">开本</span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="text"><input
			name="af.format" value="<s:property value="#af.format" />"
			dataType="Require" msg="[开本]必须填写" /></span></td>
		<td width="8%" align="center" bgcolor="#FFFFFF"><span
			class="item">订法</span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="text"><s:textfield
			name="af.bm" value="%{#af.bm}" /></span></td>
		<td width="10%" align="center" bgcolor="#FFFFFF"><span
			class="item">总印张</span></td>
		<td colspan="2" bgcolor="#FFFFFF"><span class="text"><s:set
			name="teps" value="0" /> <s:iterator value="#af.AfElement">
			<s:if test="EPs">
				<s:set name="teps" value="#teps=#teps+EPs" />
			</s:if>
		</s:iterator> <s:property value="#teps" /></span></td>
	</tr>
	<tr>
		<td colspan="9" align="center" bgcolor="#FFFFFF">
		<table width="100%" border="1" align="center" cellpadding="3"
			cellspacing="0" bordercolor="#000000" bgcolor="#000000"
			style="border-collapse: collapse">
			<s:iterator value="#af.AfElement" status="stat">
				<tr>
					<td width="4%" rowspan="4" align="center" bgcolor="#FFFFFF"><s:if
						test="afEId>1">
						<input type="hidden"
							name="afes[<s:property value="#stat.index" />].afEId"
							value="<s:property value="afEId"/>" />
						<input type="hidden"
							name="afes[<s:property value="#stat.index" />].AfBase.afId"
							value="<s:property value="#af.afId"/>" />
					</s:if> <select name="afes[<s:property value="#stat.index" />].EType">
						<option value="Del">删除</option>
						<option value="BB" <s:if test="EType.equals('BB')">selected</s:if>>正文</option>
						<option value="Cover"
							<s:if test="EType.equals('Cover')">selected</s:if>>封面</option>
						<option value="CI" <s:if test="EType.equals('CI')">selected</s:if>>插页</option>
					</select></td>
					<td width="13%" align="center" bgcolor="#FFFFFF"><span
						class="item">机型</span></td>
					<td width="12%" align="left" bgcolor="#FFFFFF"><select
						name="afes[<s:property value="#stat.index" />].EMachine">
						<option value="四色"
							<s:if test="EMachine.equals('四色')">selected</s:if>>四色</option>
						<option value="双面"
							<s:if test="EMachine.equals('双面')">selected</s:if>>双面</option>
						<option value="05"
							<s:if test="EMachine.equals('05')">selected</s:if>>05</option>
						<option value="02"
							<s:if test="EMachine.equals('02')">selected</s:if>>02</option>
					</select></td>
					<td align="center" bgcolor="#FFFFFF"><span class="item">印张</span></td>
					<td align="left" bgcolor="#FFFFFF"><span class="text">
					<input type="text" size="3"
						name="afes[<s:property value="#stat.index" />].EPs"
						value="<s:property value="EPs"/>" dataType="Double"
						msg="[印张]请填写一个数字" /> <input type="hidden"
						name="afes[<s:property value="#stat.index" />
              ].EIdePress"
						value="<s:property value="EIdePress"/>" /> </span></td>
					<td width="8%" align="center" bgcolor="#FFFFFF"><span
						class="item">墨色</span></td>
					<td width="8%" align="left" bgcolor="#FFFFFF"><span
						class="text"> 正:<input size="1"
						name="afes[<s:property value="#stat.index" />].EColorFrontN"
						id="EColorFrontN" value="<s:property value="EColorFrontN"/>"
						dataType="Integer" msg="[正面墨色]请填写一个整数" /> 色:<input
						name="afes[<s:property value="#stat.index" />].EColorFront"
						size="8" value="<s:property value="EColorFront"/>" /> 背:<input
						size="1"
						name="afes[<s:property value="#stat.index" />].EColorBackN"
						id="EColorBackN" value="<s:property value="EColorBackN"/>"
						dataType="Integer" msg="[背面墨色]请填写一个整数" /> 色:<input
						name="afes[<s:property value="#stat.index" />].EColorBack"
						size="8" value="<s:property value="EColorBack"/>" /> </span></td>
					<td width="10%" align="center" bgcolor="#FFFFFF"><span
						class="item">用纸</span></td>
					<td align="left" bgcolor="#FFFFFF"><span class="text">
					<input type="text"
						name="afes[<s:property value="#stat.index" />].EPaper"
						value="<s:property value="EPaper"/>" dataType="Require"
						msg="[用纸]必须填写" /> <br>
					<select name="afes[<s:property value="#stat.index" />].EPaperFrom">
						<option value="自备"
							<s:if test="EPaperFrom.equals('自备')">selected</s:if>>自备</option>
						<option value="厂料"
							<s:if test="EPaperFrom.equals('厂料')">selected</s:if>>厂料</option>
						<option value="现购"
							<s:if test="EPaperFrom.equals('现购')">selected</s:if>>现购</option>
					</select> </span></td>
				</tr>
				<tr>
					<td align="center" bgcolor="#FFFFFF"><span class="item">用纸规格</span></td>
					<td width="12%" align="left" bgcolor="#FFFFFF"><span
						class="text"> <input type="text"
						name="afes[<s:property value="#stat.index" />].ESs"
						value="<s:property value="ESs"/>" dataType="Require"
						msg="[用纸规格]必须填写" /> </span></td>
					<td width="8%" align="center" bgcolor="#FFFFFF"><span
						class="item">开本</span></td>
					<td width="8%" align="left" bgcolor="#FFFFFF"><span
						class="text"> <input type="text" size="3"
						name="afes[<s:property value="#stat.index" />].EFormat"
						value="<s:property value="EFormat"/>" dataType="Require"
						msg="[开本]必须填写" /> </span></td>
					<td align="center" bgcolor="#FFFFFF"><span class="item">P数</span></td>
					<td width="8%" align="left" bgcolor="#FFFFFF"><span
						class="text"> <input type="text" size="3"
						name="afes[<s:property value="#stat.index" />].EP"
						value="<s:property value="EP"/>" dataType="Integer"
						msg="[P数]请填写一个整数" /> </span></td>
					<td width="10%" align="center" bgcolor="#FFFFFF"><span
						class="item">令数</span></td>
					<td align="left" bgcolor="#FFFFFF"><span class="text">
					<input type="text" dataType="Double" msg="[令数]请填写一个数字"
						name="afes[<s:property value="#stat.index" />].EReam"
						value="<s:property value="EReam"/>"
						id="EReam<s:property value="#stat.index"/>"
						onblur="(document.forms.totalPapers<s:property value="#stat.index" />.value=(document.forms.EReam<s:property value="#stat.index" />.value*1)+(document.forms.EOvers<s:property value="#stat.index" />.value*1))" />
					</span></td>
				</tr>
				<tr>
					<td align="center" bgcolor="#FFFFFF"><span class="item">制版日期</span></td>
					<td width="12%" align="left" bgcolor="#FFFFFF"><input type="text" name="afes[<s:property value="#stat.index" />].EPlanPm" onClick="WdatePicker()" value='<s:date name="EPlanPress" format="yyyy-MM-dd" nice="false"/>' dataType="Date" msg="[制版日期]请填写一个日期" /></td>
					<td width="8%" align="center" bgcolor="#FFFFFF"><span
						class="item">印数</span></td>
					<td width="8%" align="left" bgcolor="#FFFFFF"><input
						type="text" size="3"
						name="afes[<s:property value="#stat.index" />].EAmount"
						value="<s:property value="EAmount"/>" dataType="Integer"
						msg="[印数]请填写一个整数" /> <input type="hidden"
						name="afes[<s:property value="#stat.index" />].EIdePm"
						value="<s:property value="EIdePm"/>" /></td>
					<td align="center" bgcolor="#FFFFFF"><span class="item">印版</span></td>
					<td width="8%" align="left" bgcolor="#FFFFFF"><span
						class="text"> <input type="text" size="3"
						name="afes[<s:property value="#stat.index" />].EPlateType"
						value="<s:property value="EPlateType"/>" /> </span></td>
					<td width="10%" align="center" bgcolor="#FFFFFF"><span
						class="item">加放</span></td>
					<td align="left" bgcolor="#FFFFFF"><span class="text">
					<input type="text"
						name="afes[<s:property value="#stat.index" />].EOvers"
						dataType="Double" msg="[加放]请填写一个数字"
						value="<s:property value="EOvers"/>"
						id="EOvers<s:property value="#stat.index" />"
						onblur="(document.forms.totalPapers<s:property value="#stat.index" />.value=(document.forms.EReam<s:property value="#stat.index" />.value*1)+(document.forms.EOvers<s:property value="#stat.index" />.value*1))" />
					</span></td>
				</tr>
				<tr>
					<td align="center" bgcolor="#FFFFFF"><span class="item">印刷日期</span></td>
					<td width="12%" align="left" bgcolor="#FFFFFF"><input type="text" name="afes[<s:property value="#stat.index" />].EPlanPress" onClick="WdatePicker()" value='<s:date name="EPlanPress" format="yyyy-MM-dd" nice="false"/>' dataType="Date" msg="[印刷日期]请填写一个日期" /></td>
					<td width="8%" align="center" bgcolor="#FFFFFF">&nbsp;</td>
					<td width="8%" align="center" bgcolor="#FFFFFF">&nbsp;</td>
					<td align="center" bgcolor="#FFFFFF"><span class="item">版数</span></td>
					<td width="8%" align="left" bgcolor="#FFFFFF"><input
						type="text" size="3"
						name="afes[<s:property value="#stat.index" />].EPlateAmount"
						value="<s:property value="EPlateAmount"/>" dataType="Integer"
						msg="[版数]请填写一个整数" /></td>
					<td width="10%" align="center" bgcolor="#FFFFFF"><span
						class="item">总用量</span></td>
					<td align="left" bgcolor="#FFFFFF"><input type="text"
						id="totalPapers<s:property value="#stat.index" />" disabled="true" /></td>
				</tr>
			</s:iterator>
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
			<s:iterator value="#af.AfDispose" status="stat">
				<tr>
					<td bgcolor="#FFFFFF"><span class="text"> <s:if
						test="afDId>1">
						<input type="hidden"
							name="afds[<s:property value="#stat.index" />].afDId"
							value="<s:property value="afDId"/>" />
					</s:if> <input type="text"
						name="afds[<s:property value="#stat.index" />].afEType"
						value="<s:property value="afEType"/>" dataType="Require"
						msg="[项目名称]必须填写" /></span></td>
					<td bgcolor="#FFFFFF"><span class="text"> <input
						type="text"
						name="afds[<s:property value="#stat.index" />].afDItem"
						value="<s:property value="afDItem"/>" dataType="Require"
						msg="[工艺名称]必须填写" /> </span></td>
					<td bgcolor="#FFFFFF"><span class="text"> <input
						type="text"
						name="afds[<s:property value="#stat.index" />].afDAmount"
						value="<s:property value="afDAmount"/>" dataType="Integer"
						msg="[数量]请填写一个整数" /> </span></td>
					<td bgcolor="#FFFFFF"><span class="text"> <input
						type="text"
						name="afds[<s:property value="#stat.index" />].afDFactory"
						value="<s:property value="afDFactory"/>" /> </span></td>
					<td bgcolor="#FFFFFF"><span class="text"> <input
						type="text"
						name="afds[<s:property value="#stat.index" />].afDRemark"
						value="<s:property value="afDRemark"/>" /> </span></td>
				</tr>
			</s:iterator>
		</table>
		</td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item">送样书日期</span></td>
		<td align="left" bgcolor="#FFFFFF"><span class="text"> <input
			value="<s:date name="#af.planSendSample" format="yyyy-MM-dd" nice="false"/>"
			name="af.planSendSample" onClick="WdatePicker()" /> </span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="item">送样书备注</span></td>
		<td colspan="6" align="left" bgcolor="#FFFFFF"><span class="text">
		<s:textfield name="af.remarkSendSample"
			value="%{#af.remarkSendSample}" cssStyle="width:380px;" /></span></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item">送货日期</span></td>
		<td align="left" bgcolor="#FFFFFF"><span class="text"> <input value='<s:date name="#af.planDeliver" format="yyyy-MM-dd" nice="false"/>' name="af.planDeliver" dataType="Date" msg="[送货日期]请填写一个日期" onClick="WdatePicker()" /> </span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="item">送货地点</span></td>
		<td colspan="6" align="left" bgcolor="#FFFFFF"><span class="text">
		<s:textfield name="af.deliverAddress" value="%{#af.deliverAddress}"
			cssStyle="width:380px;" /></span></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item">印装次序</span></td>
		<td colspan="8" align="left" valign="top" bgcolor="#FFFFFF"><span
			class="text"> <s:textarea name="af.plateMakeOrder"
			value="%{#af.plateMakeOrder}" cssStyle="width:680px;height:160px;" /></span></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item">质量要求</span></td>
		<td colspan="8" align="left" valign="top" bgcolor="#FFFFFF"><span
			class="text"> <s:textarea name="af.oq" value="%{#af.oq}"
			cssStyle="width:680px;height:160px;" /></span></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item"><br />
		备 注<br />
		<br />
		</span></td>
		<td colspan="8" align="left" valign="top" bgcolor="#FFFFFF"><s:textarea
			name="af.remark" value="%{#af.remark}"
			cssStyle="width:680px;height:180px;" /></td>
	</tr>
</table>
<table width="90%" border="0" align="center" cellpadding="8"
	cellspacing="0">
	<tr>
		<td><span class="bottomtext">接洽人: <input name="af.cp"
			value="<s:property value="#af.cp"/>" dataType="Require"
			msg="[接洽人]必须填写" /></span></td>
		<td></td>
	</tr>
</table>
<s:hidden name="af.ideDeliver" value="%{#af.ideDeliver}" /> <s:hidden
	name="af.comDeliver" value="%{#af.comDeliver}" /> <s:hidden
	name="af.moneyShould" value="%{#af.moneyShould}" /> <s:hidden
	name="af.moneyFact" value="%{#af.moneyFact}" /> <s:hidden
	name="af.moneyTime" value="%{#af.moneyTime}" /> <s:hidden
	name="af.moneyInputTime" value="%{#af.moneyInputTime}" /> <s:hidden
	name="af.moneyInputMan" value="%{#af.moneyInputMan}" />
<div align="center"><script type="text/javascript">
function doSubmit(cmd){
    if(cmd == "saveAF"){
        document.getElementById('forms').action = "${pageContext.request.contextPath}/OPE/saveAF.action";
    } else if(cmd == "saveAndPrintAF_print"){
        document.getElementById('forms').action = "${pageContext.request.contextPath}/OPE/saveAndPrintAF_print.action";
    }
}
</script><s:submit value="保存任务单" onclick="doSubmit('saveAF');" /> <s:submit
	value="保存并打印" onclick="doSubmit('saveAndPrintAF_print');" /></div>
</form>
</body>
</html>