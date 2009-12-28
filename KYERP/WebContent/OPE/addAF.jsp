<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>填写任务单基本信息-业务管理</title>
<script type="text/javascript"
	src="<s:url value="/Library/js/Validator.js" />"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Library/js/ext/resources/css/ext-all.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ext/adapter/jquery/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/CursorInsert.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ext/ext-all.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ext/source/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="addAF.js"></script>
<script type="text/javascript">
  function addD(){
   var oTbody = document.getElementById("D");
   var newIndex = document.getElementById("D").rows.length;
   var oTrNew = document.createElement("TR");
   var oTr = document.getElementById("trD");
   for(var m=0;m<oTr.childNodes.length;m++){
    var oTd = oTr.childNodes[m].cloneNode(true);
    var ohtml = oTd.innerHTML;
    var nhtml = ohtml.replace(/[0]/g,[newIndex]);
    oTd.innerHTML = nhtml;
    oTrNew.appendChild(oTd);
   }
   oTbody.appendChild(oTrNew);
  } 
</script>
</head>
<body>
<form action="<s:url value='/OPE/addAF_Base.action' />" method="post"
	name="addAF_Base" id="form1"
	onSubmit="return Validator.Validate(this,2)"><input
	name="af.afType" type="hidden" value="Books" />
	<input name="af.afId" type="hidden" value="0" />
<table width="100%" border="0" cellpadding="0" cellspacing="8"
	bgcolor="#FFFFFF">
	<tr>
		<td align="right" bgcolor="#FFFFFF" width="101"><span
			class="From_item">是否受控:</span></td>
		<td width="92%" align="left" bgcolor="#FFFFFF"><input
			name="af.iso" type="radio" value="SK"
			onClick="document.getElementById('nobczd').checked=true"> 受控
		<input type="radio" name="af.iso" value="LH"
			onClick="document.getElementById('bczd').checked=true"
			dataType="Group" msg="请选择任务单类型"> 零活 &nbsp;&nbsp; <span
			class="From_item">是否本厂装订:</span> <input type="radio"
			name="af.ourbinding" value="1" dataType="Group" msg="请选择是否本厂装订"
			id="bczd" /> 是 <input type="radio" name="af.ourbinding" value="0"
			id="nobczd" /> 否</td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF"><span class="From_item">任务级别:</span></td>
		<td align="left" bgcolor="#FFFFFF"><select name="af.timeRank">
			<option value="0">一般</option>
			<option value="1">紧急</option>
		</select></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF"><span class="From_item">接洽人:
		</span></td>
		<td align="left" bgcolor="#FFFFFF"><input name="af.cp"
			id="comboYW" type="text" dataType="Require" msg="[接洽人]必须选择一个业务室成员" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF">委印单位:</td>
		<td align="left" bgcolor="#FFFFFF"><input name="af.client"
			id="comboClient" type="text" dataType="Require" msg="[委印单位]必须选择" />
		<a href="${pageContext.request.contextPath}/Client/addClient.action"
			target="_blank">增加新客户</a></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF">经手人:</td>
		<td align="left" bgcolor="#FFFFFF"><input name='af.linkman'
			id="comboLinkman" type="text" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF">联系电话:</td>
		<td align="left" bgcolor="#FFFFFF"><input name='af.tel'
			id="comboTel" type="text" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF">印品名称:</td>
		<td align="left" bgcolor="#FFFFFF"><input name="af.presswork"
			id="comboPresswork" type="text" dataType="Require" msg="[印品名称]必须填写" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF"><span class="From_item">印制单号:</span></td>
		<td align="left" bgcolor="#FFFFFF"><input name="af.pcAf"
			id="af_pcAf" type="text" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF"><span class="From_item">书号:</span>
		</td>
		<td align="left" bgcolor="#FFFFFF"><input type="text"
			name="af.isbn" id="af_isbn" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF">丛书名:</td>
		<td align="left" bgcolor="#FFFFFF"><input name="af.seriesName"
			id="comboSeriesName" type="text" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF"><span class="From_item">开本:</span></td>
		<td align="left" bgcolor="#FFFFFF"><input id="af_format"
			name="af.format" type="text" dataType="Require" msg="[开本]必须填写" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF"><span class="From_item">成品尺寸:</span></td>
		<td align="left" bgcolor="#FFFFFF"><input id="af_fps"
			name="af.fps" type="text" dataType="Require" msg="[成品尺寸]必须填写" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF"><span class="From_item">印次:</span></td>
		<td align="left" bgcolor="#FFFFFF"><input id="af_edition"
			style="width: 60px; text-align: left;" name="af.edition" type="text"
			dataType="Require" msg="[印次]必须填写" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF"><span class="From_item">总印数:</span>
		</td>
		<td align="left" bgcolor="#FFFFFF"><input name="af.amount"
			dataType="Integer" msg="[总印数]填写不正确,必须填写一个整数"
			Style="width: 50px; text-align: left;"
			onKeyUp="this.value=this.value.replace(/\D/g,'');document.getElementById('afdamount').value=this.value"
			onafterpaste="this.value=this.value.replace(/\D/g,'');document.getElementById('afdamount').value=this.value" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF"><span class="From_item">订法:</span></td>
		<td align="left" bgcolor="#FFFFFF"><input id="af_bm"
			style="width: 180px; text-align: left;" name="af.bm" type="text" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF"><span class="From_item">印装次序:</span></td>
		<td align="left" bgcolor="#FFFFFF">
		<table width="100%" border="0" cellspacing="4" cellpadding="0">
			<tr>
				<td width="480" valign="top"><textarea name="af.plateMakeOrder"
					id="af_plateMakeOrder" style="width: 480px; height: 150px;"
					onselect="setCaret(this);" onclick="setCaret(this);"
					onkeyup="setCaret(this);"></textarea></td>
				<td valign="top">
				<table width="100%" border="0" cellspacing="4" cellpadding="0">
					<tr>
						<td>常用项目</td>
					</tr>
					<tr>
						<td><input id="cy_bh00" type="hidden"
							value="① ;② ;③ ;④ ;⑤ ;⑥ ;⑦ ;⑧ ;⑨ ;⑩ ;" /> <INPUT id="cy_bh"
							type=button value="①②③ …"
							onclick="insertAtCaret(this.form.af_plateMakeOrder,this.form.cy_bh00.value);" />
					</tr>
					<tr>
						<td><INPUT id="cy_cw" type=button value="策委"
							onclick="insertAtCaret(this.form.af_plateMakeOrder,this.value);" />
						<input id="cy_bz" type=button value="白张"
							onclick="insertAtCaret(this.form.af_plateMakeOrder,this.value);" /></td>
					</tr>
					<tr>
						<td><INPUT id="cy_fy" type=button value="扉页"
							onclick="insertAtCaret(this.form.af_plateMakeOrder,this.value);" />
						<input id="cy_bq" type=button value="版权"
							onclick="insertAtCaret(this.form.af_plateMakeOrder,this.value);" /></td>
					</tr>
					<tr>
						<td><input id="cy_qy" type=button value="前言"
							onclick="insertAtCaret(this.form.af_plateMakeOrder,this.value);" />
						<input id="cy_ml" type=button value="目录"
							onclick="insertAtCaret(this.form.af_plateMakeOrder,this.value);" /></td>
					</tr>
					<tr>
						<td><input id="cy_zw" type=button value="正文"
							onclick="insertAtCaret(this.form.af_plateMakeOrder,this.value);" />
						<input id="cy_bb" type=button value="背白"
							onclick="insertAtCaret(this.form.af_plateMakeOrder,this.value);" /></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF"><span class="From_item">质量要求:</span></td>
		<td align="left" bgcolor="#FFFFFF">
		<table width="100%" border="0" cellspacing="4" cellpadding="0">
			<tr>
				<td width="480" valign="top"><textarea name="af.oq" id="af_oq"
					style="width: 480px; height: 150px;" onselect="setCaret(this);"
					onclick="setCaret(this);" onkeyup="setCaret(this);"></textarea></td>
				<td valign="top">
				<table width="100%" border="0" cellspacing="4" cellpadding="0">
					<tr>
						<td>常用项目</td>
					</tr>
					<tr>
						<td><input id="msjy" type=button value="墨色均匀"
							onclick="insertAtCaret(this.form.af_oq,this.value);" /> <input
							id="hbqy" type=button value="换版权页"
							onclick="insertAtCaret(this.form.af_oq,this.value);" /></td>
					</tr>
					<tr>
						<td><input id="bqybcsyqw" type=button value="版权页版次上有浅网"
							onclick="insertAtCaret(this.form.af_oq,this.value);" /></td>
					</tr>
					<tr>
						<td><input id="z30" type=button value="正文30页上加黑框"
							onclick="insertAtCaret(this.form.af_oq,this.value);" /></td>
					</tr>
					<tr>
						<td><input id="ays" type=button value="按样书、打样印刷"
							onclick="insertAtCaret(this.form.af_oq,this.value);" /></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="8"
	bordercolor="#FFFFFF" bgcolor="#FFFFFF">
	<tr>
		<td width="8%" align="right" bgcolor="#FFFFFF"><span
			class="From_item">其它项目:</span><br />
		<input type="button" onClick="addD();" value="增加一项"></td>
		<td width="92%" align="left" bgcolor="#FFFFFF">
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
			<TBODY id="D">
				<TR id="trd">
					<TD>
					<table width="580" border="1" cellpadding="4" cellspacing="1">
						<tr>
							<td width="29" rowspan="5" align="center" bgcolor="#CCCCCC">0</td>
							<td width="131" align="right">工艺类型</td>
							<td width="284"><s:select name="afds[0].afEType"
								list="{'封面覆膜','装订','模切','照排','其它'}" /></td>
						</tr>
						<tr>
							<td width="131" align="right">工艺名称</td>
							<td><input name="afds[0].afDItem" dataType="Require"
								msg="[工艺名称]必须填写" type="text" id="afDItem0" style="width: 81px" />
							<s:select name="afDItemSelect0"
								list="{'请选择工艺名称','覆亚膜','覆光膜','勒口','烫金','烫红','胶订','骑马订','三折页','胶订+铁钉','一折页','两折页','光边','打字','复印','出片'}"
								cssStyle="width: 91px;text-align:left;"
								onchange="(document.getElementById('afDItem0').value=this.options[this.selectedIndex].value)" /></td>
						</tr>
						<tr>
							<td width="131" align="right">数量</td>
							<td><input name="afds[0].afDAmount" dataType="Integer"
								msg="[数量]请填写整数" id="afdamount"
								Style="width: 51px; text-align: left;"
								onKeyUp="this.value=this.value.replace(/\D/g,'')"
								onafterpaste="this.value=this.value.replace(/\D/g,'')" /></td>
						</tr>
						<tr>
							<td width="131" align="right">厂家</td>
							<td><input name='afds[0].afDFactory' dataType="Require"
								msg="[厂家]必须填写" type='text' class="wenbenkuang"
								id="bindingFactory0" style="width: 141px" value='' /> <SELECT
								name="bindingFactoryselect0" size="1" class="wenbenkuang"
								onChange="(document.getElementById('bindingFactory0').value=this.options[this.selectedIndex].value)">
								<OPTION selected value=>==请选装订厂==</OPTION>
								<s:iterator value="#request['bindingFactoryList']"
									id="bindingFactory">
									<option value="<s:property value="#bindingFactory.factory"/>"><s:property
										value="#bindingFactory.factory" /></option>
								</s:iterator>
							</select></td>
						</tr>
						<tr>
							<td width="131" align="right">备注</td>
							<td><s:textarea name="afds[0].afDRemark"
								cssStyle="width: 321px;text-align:left;" /></td>
						</tr>
					</table>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		</td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="8"
	bordercolor="#FFFFFF" bgcolor="#FFFFFF">
	<tr>
		<td width="8%" align="right" bgcolor="#FFFFFF"><span
			class="From_item">送样书时间:</span></td>
		<td width="92%" align="left" bgcolor="#FFFFFF"><input type="text"
			id="af_planSendSample" name="af.planSendSample" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF"><span class="From_item">送样书备注:</span></td>
		<td align="left" bgcolor="#FFFFFF"><input
			name="af.remarkSendSample" type="text" /></td>
	</tr>
	<tr>
		<td width="8%" align="right" bgcolor="#FFFFFF"><span
			class="From_item">送书地点:</span></td>
		<td width="92%" align="left" bgcolor="#FFFFFF"><input type="text"
			id="comboSendPlace" name="af.deliverAddress" style="width: 450px;" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF"><span class="From_item">送书时间:</span></td>
		<td align="left" bgcolor="#FFFFFF"><input name="af.planDeliver"
			id="af_planDeliver"
			value='<s:date name="#session['today']" format="yyyy-MM-dd" nice="false"/>' /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF"><span class="From_item">备注:</span></td>
		<td align="left" bgcolor="#FFFFFF"><textarea name="af.remark"
			style="width: 580px; height: 150px;"></textarea></td>
	</tr>
</table>
<s:submit value=" 增加元件" /></form>
</body>
</html>
