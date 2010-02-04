<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>填写任务单-业务管理</title>
<script type="text/javascript"
	src="<s:url value="/Library/js/Validator.js" />"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Library/js/ext/resources/css/ext-all.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ext/ext-all.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ext/source/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="FPAddAF_MP.js"></script>
<SCRIPT language=javascript>

function addV(){
	   var oTbody = document.getElementById("V");
	   var newIndex = document.getElementById("V").rows.length;
	   var oTrNew = document.createElement("TR");
	   var oTr = document.getElementById("trV");
	   for(var m=0;m<oTr.childNodes.length;m++){
	    var oTd = oTr.childNodes[m].cloneNode(true);
	    var ohtml = oTd.innerHTML;
	    var nhtml = ohtml.replace(/[0]/g,[newIndex]);
	    oTd.innerHTML = nhtml;
	    oTrNew.appendChild(oTd);
	   }
	   oTbody.appendChild(oTrNew);
	  } 
  function removeRow(r) {
  var root = r.parentNode; 
  var allRows = root.getElementsByTagName('tr');
  if(allRows.length>1) 
      root.removeChild(r); 
  else 
      alert("至少保留一项"); 
}
      
</SCRIPT>
</head>
<body>
<form action="FPAddAF_save.action" method="post" name="FPAddAF_save"
	id="form1" onSubmit="return Validator.Validate(this,2)"><input
	name="af.afType" type="hidden" value="Card" /> <input name="af.iso"
	type="hidden" value="FP" />
<table width="100%" border="0" cellspacing="4" cellpadding="0">
	<tr bgcolor="#C2D3FC">
		<td width="100" align="center" valign="middle">客户资料</td>
		<td align="left" bgcolor="#FFFFFF">
		<table width="100%" border="0" cellspacing="4" cellpadding="0">
			<tr bgcolor="#FFFFFF">
				<td width="80" align="right">委印单位:</td>
				<td align="left"><input name="af.client" id="comboClient"
					type="text" dataType="Require" msg="[委印单位]必须选择" /> <a
					href="${pageContext.request.contextPath}/Client/addClient.action"
					target="_blank">增加新客户</a></td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td align="right">经手人:</td>
				<td align="left"><input name='af.linkman' id="comboLinkman"
					type="text" /></td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td align="right">联系电话:</td>
				<td align="left"><input name='af.tel' id="comboTel" type="text" /></td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td align="right">送书地点:</td>
				<td align="left"><input type="text" id="comboSendPlace"
					name="af.deliverAddress" /></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td align="center" valign="middle" bgcolor="#C2D3FC">基本信息</td>
		<td align="left" bgcolor="#FFFFFF">
		<table border="0" cellpadding="0" cellspacing="4">
			<tr>
				<td width="80" align="right"><span class="From_item">接洽人:
				</span></td>
				<td align="left"><input name="af.cp" id="comboYW" type="text"
					value='<s:property value="#session.user.employee.realname" />'
					dataType="Require" msg="[接洽人]必须选择一个业务室成员" /></td>
				<td align="left"><span class="From_item">任务级别:</span></td>
				<td align="left"><select name="af.timeRank">
					<option value="0">一般</option>
					<option value="1">紧急</option>
				</select></td>
			</tr>
			<tr>
				<td align="right">印品名称:</td>
				<td align="left"><input name="af.presswork" id="comboPresswork"
					type="text" dataType="Require" msg="[印品名称]必须填写" /></td>
				<td align="right"><span class="From_item">盒数:</span></td>
				<td align="left"><input name="af.amount" datatype="Integer"
					msg="[盒数]填写不正确,必须填写一个整数" style="width: 50px; text-align: left;"
					onKeyUp="this.value=this.value.replace(/\D/g,'');document.getElementById('afvamount').value=this.value"
					onafterpaste="this.value=this.value.replace(/\D/g,'');document.getElementById('afvamount').value=this.value" /></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td align="center" valign="middle" bgcolor="#C2D3FC">工序/计价<br>
		<input type="button" value="增加" onClick="addV()" /></td>
		<td>
		<table>
			<thead>
				<tr>
					<td width="160">项目</td>
					<td width="80">车间</td>
					<td>单价</td>
					<td>数量</td>
					<td>备注</td>
					<td></td>
				</tr>
			</thead>
			<tbody id="V">
				<tr id="trV">
					<td><input name="afvs[0].itemName" value="名片"
						dataType="Require" msg="[工序名称]必须选择" /></td>
					<td><select name="afvs[0].chejian">
						<option value="照排">照排</option>
						<option value="制版车间">制版车间</option>
						<option value="四色">四色</option>
						<option value="双面">双面</option>
						<option value="零五">零五</option>
						<option value="零二">零二</option>
						<option value="小制版">小制版</option>
						<option value="装订">装订</option>
						<option value="库房">库房</option>
						<option value="外加工">外加工</option>
						<option value="其它">其它</option>
					</select></td>
					<td><input name="afvs[0].danjia" type="text" size="8"
						dataType="Require" msg="[工序单价]必须选择" /></td>
					<td><input name="afvs[0].amount" type="text" size="8"
						id="afvamount" datatype="Integer" msg="[工序数量]填写不正确,必须填写一个整数" /></td>
					<td><input name="afvs[0].remark" type="text" size="38" /></td>
					<td><input type="button" value="删除"
						onClick="removeRow(this.parentNode.parentNode)" /></td>
				</tr>
			</tbody>
		</table>
		</td>
	</tr>
	<tr>
		<td align="center" valign="middle" bgcolor="#C2D3FC">参数</td>
		<td bgcolor="#FFFFFF">
		<table width="100%" border="0" cellspacing="4" cellpadding="0">
			<tr>
				<td width="80" align="right"><span class="From_item">纸&nbsp;&nbsp;张:</span></td>
				<td><input name="af.paper" id="AfPaper"
					style="width: 180px; text-align: left;" dataType="Require"
					msg="[用纸]必须填写" /> <select name="AfPaper00" class="wenbenkuang"
					id="AfPaper00"
					onChange="(document.getElementById('AfPaper').value=document.getElementById('AfPaper').value+this.options[this.selectedIndex].value)">
					<option value="">常用纸张</option>
					<option value="薄布纹">薄布纹</option>
					<option value="厚布纹">厚布纹</option>
					<option value="薄白卡">薄白卡</option>
					<option value="厚白卡">厚白卡</option>
					<option value="立夫卡">立夫卡</option>
					<option value="黄岗古">黄岗古</option>
					<option value="荷兰卡">荷兰卡</option>
					<option value="莱尼纹">莱尼纹</option>
					<option value="康克龙">康克龙</option>
				</select></td>
			</tr>
			<tr>
				<td align="right"><span class="From_item">正&nbsp;&nbsp;面:</span></td>
				<td>色数: <select name="af.colorFrontN" id="colorFrontN"
					class="wenbenkuang">
					<option value="0">0</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
				</select> &nbsp;墨色: <input name="af.colorFront" id="colorFront"
					Style="width: 180px; text-align: left;" value="无" /> &nbsp;&nbsp;
				<select name="colorFront00" class="wenbenkuang" id="colorFront00"
					onChange="(document.getElementById('colorFront').value=this.options[this.selectedIndex].value)">
					<option value="">选择墨色</option>
					<option value="黑色">黑色</option>
					<option value="双色">双色</option>
					<option value="四色">四色</option>
				</select></td>
			</tr>
			<tr>
				<td align="right"><span class="From_item">背&nbsp;&nbsp;面:</span></td>
				<td>色数: <select name="af.colorBackN" id="colorBackN"
					class="wenbenkuang">
					<option value="0">0</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
				</select> &nbsp;墨色: <input name="af.colorBack" id="colorBack"
					Style="width: 180px; text-align: left;" value="无" /> &nbsp;&nbsp;
				<select name="colorBack0" class="wenbenkuang" id="colorBack0"
					onChange="(document.getElementById('colorBack').value=this.options[this.selectedIndex].value)">
					<option value="">选择墨色</option>
					<option value="黑色">黑色</option>
					<option value="双色">双色</option>
					<option value="四色">四色</option>
				</select></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td align="center" valign="middle" bgcolor="#C2D3FC">进度要求</td>
		<td bgcolor="#FFFFFF">
		<table width="100%" border="0" cellspacing="4" cellpadding="0">
			<tr>
				<td width="80" align="right"><span class="From_item">看样日期:</span></td>
				<td><input name="af.planPress" id="af_planPress" /></td>
			</tr>
			<tr>
				<td align="right"><span class="From_item">交货日期:</span></td>
				<td><input name="af.planDeliver" id="af_planDeliver"
					dataType="Require" msg="[交货日期]必须填写" /></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td align="center" valign="middle" bgcolor="#C2D3FC">备注</td>
		<td bgcolor="#FFFFFF"><textarea name="af.remark"
			style="width: 580px; height: 150px;"></textarea></td>
	</tr>
	<tr>
		<td align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
		<td bgcolor="#FFFFFF"><label> <input type="submit"
			name="button" id="button" value="提交"> </label></td>
	</tr>
</table>
</form>
</body>
</html>
