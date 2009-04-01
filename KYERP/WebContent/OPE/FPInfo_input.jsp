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
<form action="FPAddAF_save.action" method="post" id="forms" name="forms"
	onSubmit="return checkAF()"><input name="af.afType"
	type="hidden" value="<s:property value="#af.afType"/>" /> <input
	name="af.aftypeNo" type="hidden"
	value="<s:property value="#af.aftypeNo"/>" />
<table width="98%" border="0" align="center" cellpadding="3"
	cellspacing="0">
	<tr>
		<td width="21%"><s:if test="#af.afNo>1">
			<input type="hidden" name="af.afId"
				value="<s:property value="#af.afId"/>" />
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
		</s:if> <s:hidden name="af.ad" value="%{#af.ad}" /> <s:if test="#af.afNo<1"></s:if></td>
		<td colspan="2" align="right">进度要求<select name="af.timeRank">
			<option value="0" <s:if test="#af.timeRank==0">selected</s:if>>一般</option>
			<option value="1" <s:if test="#af.timeRank==1">selected</s:if>>紧急</option>
		</select> &nbsp;&nbsp; <s:property value="#af.ad" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
			class="item">经手人</span></td>
		<td bgcolor="#FFFFFF"><span class="text"> <s:textfield
			name="af.linkman" value="%{#af.linkman}" /> </span></td>
	</tr>
	<tr>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">印品名称</span></td>
		<td colspan="5" bgcolor="#FFFFFF" align="left"><span class="text"><input
			name="af.presswork" value="<s:property value="#af.presswork" />"
			dataType="Require" msg="[印品名称]必须填写" style="width: 380px;" /></span></td>
		<td width="10%" align="center" bgcolor="#FFFFFF"><span
			class="item">电话</span></td>
		<td bgcolor="#FFFFFF" class="text"><s:textfield name="af.tel"
			value="%{#af.tel}" /></td>
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
		<td align="center" bgcolor="#FFFFFF"><span class="item">印数</span></td>
		<td bgcolor="#FFFFFF" class="text"><input name="af.amount"
			value="<s:property value="#af.amount" />
  " dataType="Integer"
			msg="[印数]请填写整数" /></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item">工序/计价</span></td>
		<td colspan="7" align="center" bgcolor="#FFFFFF">
		<table width="100%" border="1" align="center" cellpadding="4"
			cellspacing="0" bordercolor="#000000"
			style="border-collapse: collapse">
			<tr>
				<td width="100" align="center"><span class="item">计价项目</span></td>
				<td width="80" align="center"><span class="item">车间</span></td>
				<td width="40" align="center"><span class="item">单价</span></td>
				<td width="40" align="center">数量</td>
				<td align="left"><span class="item">备注</span></td>
				<td width="70" align="center"><span class="item">金额</span></td>
			</tr>
			<s:iterator value="#af.AfValuation" id="afv" status="stat">
				<tr>
					<td align="left"><input type="hidden"
						name="afvs[<s:property value="#stat.index" />].afVId"
						value="<s:property value="afVId"/>" /> <input type="text"
						name="afvs[<s:property value="#stat.index" />].itemName"
						value="<s:property value="itemName"/>" dataType="Require"
						msg="[计价名称]必须填写" /></td>
					<td align="center"><select
						name="afvs[<s:property value="#stat.index" />].chejian">
						<option value="Del">删除</option>
						<option value="照排"
							<s:if test="chejian.equals('照排')">selected="selected"</s:if>>照排</option>
						<option value="制版车间"
							<s:if test="chejian.equals('制版车间')">selected="selected"</s:if>>制版车间</option>
						<option value="四色"
							<s:if test="chejian.equals('四色')">selected="selected"</s:if>>四色</option>
						<option value="双面"
							<s:if test="chejian.equals('双面')">selected="selected"</s:if>>双面</option>
						<option value="05"
							<s:if test="chejian.equals('05')">selected="selected"</s:if>>05</option>
						<option value="02"
							<s:if test="chejian.equals('02')">selected="selected"</s:if>>02</option>
						<option value="小制版"
							<s:if test="chejian.equals('小制版')">selected="selected"</s:if>>小制版</option>
						<option value="装订"
							<s:if test="chejian.equals('装订')">selected="selected"</s:if>>装订</option>
						<option value="库房"
							<s:if test="chejian.equals('库房')">selected="selected"</s:if>>库房</option>
						<option value="外加工"
							<s:if test="chejian.equals('外加工')">selected="selected"</s:if>>外加工</option>
						<option value="其它"
							<s:if test="chejian.equals('其它')">selected="selected"</s:if>>其它</option>
					</select></td>
					<td align="left"><input type="text"
						name="afvs[<s:property value="#stat.index" />].danjia"
						value="<s:property value="danjia"/>" dataType="Require"
						msg="[单价]必须填写" /></td>
					<td align="left"><input type="text"
						name="afvs[<s:property value="#stat.index" />].amount"
						value="<s:property value="amount"/>" dataType="Require"
						msg="[数量]必须填写" /></td>
					<td align="left"><input type="text"
						name="afvs[<s:property value="#stat.index" />].calProcess"
						value="<s:property value="calProcess"/>" /></td>
					<td align="left"><span class="text"> <s:property
						value="totalAmount" /> </span></td>
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
		<td colspan="7" align="left" valign="top" bgcolor="#FFFFFF"><s:textarea
			name="af.remark" value="%{#af.remark}"
			cssStyle="width:680px;height:180px;" /></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item">送货日期</span></td>
		<td align="left" bgcolor="#FFFFFF"><span class="text"> <input
			value="<s:date name="#af.planDeliver" format="yyyy-MM-dd" nice="false"/>"
			name="af.planDeliver" dataType="Date" msg="[送货日期]请填写一个日期"
			onClick="WdatePicker()" /> </span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="item">送货地点</span></td>
		<td colspan="5" align="left" bgcolor="#FFFFFF"><span class="text">
		<s:textfield name="af.deliverAddress" value="%{#af.deliverAddress}"
			cssStyle="width:380px;" /></span></td>
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
</script> <s:submit value="保存任务单" onclick="doSubmit('newSaveAF.action');" /> <s:submit
	value="保存并打印" onclick="doSubmit('saveAndPrintAF_print');" /></div>
</form>
</body>
</html>