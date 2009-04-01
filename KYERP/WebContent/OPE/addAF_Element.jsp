<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#session['af']" name="af" id="af" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加元件-业务管理</title>
<script type="text/javascript"
	src="<s:url value="/Library/js/Validator.js" />">
 </script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function doSubmit(cmd){
    if(cmd == "addE"){
        document.getElementById('forms').action = "${pageContext.request.contextPath}/OPE/addAF_Element.action";
    } else if(cmd == "submit"){
        document.getElementById('forms').action = "${pageContext.request.contextPath}/OPE/addAF_Element_view.action";
    }
}
</script>
<script type="text/javascript">
function checkAFE(){
	var colorN=document.getElementById('EColorFrontN').value+document.getElementById('EColorBackN').value;
    if(colorN<1){
		alert("正面和背面的颜色数都为零，请检查！");
		return false;
    }
    return Validator.Validate(document.forms,2);
}
</script>
</head>
<body>
<form action='<s:url value="/OPE/addAF_Element.action" />' method="post"
	id="forms" name="formsafe" onSubmit="return checkAFE()">
<table width="750" border="1" cellpadding="8" cellspacing="0"
	bordercolor="#666666">
	<tr>
		<td width="80" align="right">类型:</td>
		<td align="left"><input type="radio" name="afe.EType"
			value="Cover" checked="checked" />封面 <input type="radio"
			name="afe.EType" value="BB" />正文 <input type="radio"
			name="afe.EType" value="CI" />插页</td>
	</tr>
	<tr>
		<td align="right">印数:</td>
		<td align="left"><input type="text" name="afe.EAmount"
			dataType="Integer" msg="[印数]请填写整数"
			value="<s:property value="#af.amount" />" size="4"
			onkeyup="this.value=this.value.replace(/\D/g,'')"
			onafterpaste="this.value=this.value.replace(/\D/g,'')" /> P数: <input
			name="afe.EP" value="0" size="2" dataType="Integer" msg="[Ｐ数]请填写整数" /></td>
	</tr>
	<tr>
		<td align="right">制版时间:</td>
		<td align="left"><input name="afe.EPlanPm" id="afe_EPlanPm"
			value='<s:date name="#session['today']" format="yyyy-MM-dd" nice="false"/>'
			onClick="WdatePicker()" /></td>
	</tr>
	<tr>
		<td align="right">印刷时间:</td>
		<td align="left"><input name="afe.EPlanPress" id="afe_EPlanPress"
			value='<s:date name="#session['today']" format="yyyy-MM-dd" nice="false"/>'
			onClick="WdatePicker()" /></td>
	</tr>
	<tr>
		<td align="right">机型:</td>
		<td align="left"><input name="afe.EMachine" type="radio"
			value="四色" checked> 四色&nbsp;&nbsp; <input name="afe.EMachine"
			type="radio" value="双面">双面&nbsp;&nbsp; <input
			name="afe.EMachine" type="radio" value="05">05&nbsp;&nbsp; <input
			name="afe.EMachine" type="radio" value="02">02&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td align="right">正面墨色</td>
		<td align="left">色数: <select name="afe.EColorFrontN"
			id="EColorFrontN" class="wenbenkuang">
			<option value="0">0</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
		</select> &nbsp;墨色: <input name="afe.EColorFront" id="EColorFront"
			Style="width: 180px; text-align: left;" value="无" /> &nbsp;&nbsp; <select
			name="E_Paper00" class="wenbenkuang" id="E_Paper00"
			onChange="(document.getElementById('EColorFront').value=this.options[this.selectedIndex].value)">
			<option value="">选择墨色</option>
			<option value="黑色">黑色</option>
			<option value="双色">双色</option>
			<option value="四色">四色</option>
			<option value="四色+银">四色+银</option>
			<option value="四色+红">四色+红</option>
		</select></td>
	</tr>
	<tr>
		<td align="right">背面墨色</td>
		<td align="left">色数: <select name="afe.EColorBackN"
			id="EColorBackN" class="wenbenkuang">
			<option value="0">0</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
		</select> &nbsp;墨色: <input name="afe.EColorBack" id="EColorBack"
			Style="width: 180px; text-align: left;" value="无" /> &nbsp;&nbsp; <select
			name="EColorBack0" class="wenbenkuang" id="EColorBack0"
			onChange="(document.getElementById('EColorBack').value=this.options[this.selectedIndex].value)">
			<option value="">选择墨色</option>
			<option value="黑色">黑色</option>
			<option value="双色">双色</option>
			<option value="四色">四色</option>
			<option value="四色+银">四色+银</option>
			<option value="四色+红">四色+红</option>
		</select></td>
	</tr>
	<tr>
		<td align="right">开本</td>
		<td align="left"><input name="afe.EFormat" id="EFormat"
			style="width: 80px; text-align: left;" dataType="Require"
			msg="[开本]必须填写" /> <select name="EFormat00" class="wenbenkuang"
			id="EFormat00"
			onChange="document.getElementById('EFormat').value=this.options[this.selectedIndex].value">
			<option value="">开本</option>
			<option value="异型">异型</option>
			<option value="8开">8开</option>
			<option value="大8开">大8开</option>
			<option value="16开">16开</option>
			<option value="大16开">大16开</option>
			<option value="32开">32开</option>
			<option value="大32开">大32开</option>
		</select></td>
	</tr>
	<tr>
		<td align="right">纸张来源</td>
		<td align="left"><select name="afe.EPaperFrom"
			class="wenbenkuang">
			<option value="自备" selected>自备</option>
			<option value="厂料">厂料</option>
			<option value="现购">现购</option>
		</select></td>
	</tr>
	<tr>
		<td align="right">用纸</td>
		<td align="left"><input name="afe.EPaper" id="E_Paper"
			style="width: 180px; text-align: left;" dataType="Require"
			msg="[用纸]必须填写" /> <select name="E_Paper00" class="wenbenkuang"
			id="E_Paper00"
			onChange="(document.getElementById('E_Paper').value=document.getElementById('E_Paper').value+this.options[this.selectedIndex].value)">
			<option value="">令重</option>
			<option value="55g">55g</option>
			<option value="60g">60g</option>
			<option value="157g">157g</option>
			<option value="200g">200g</option>
			<option value="230g">230g</option>
			<option value="250g">250g</option>
		</select> <select name="E_Paper001" class="wenbenkuang" id="E_Paper001"
			onChange="(document.getElementById('E_Paper').value=document.getElementById('E_Paper').value+this.options[this.selectedIndex].value)">
			<option value="">品牌</option>
			<option value="晨鸣">晨鸣</option>
			<option value="汇林">汇林</option>
			<option value="紫兴">紫兴</option>
			<option value="寿光">寿光</option>
		</select> <select name="E_Paper002" class="wenbenkuang" id="E_Paper002"
			onChange="(document.getElementById('E_Paper').value=document.getElementById('E_Paper').value+this.options[this.selectedIndex].value)">
			<option value="">胶/铜</option>
			<option value="胶">胶</option>
			<option value="铜">铜</option>
		</select> <select name="E_Paper003" class="wenbenkuang" id="E_Paper003"
			onChange="(document.getElementById('E_Paper').value=document.getElementById('E_Paper').value+this.options[this.selectedIndex].value)">
			<option value="">纸号</option>
			<option value="DD2009">DD2009</option>
			<option value="DD2008">DD2008</option>
			<option value="DD2007">DD2007</option>
			<option value="DD2006">DD2006</option>
		</select></td>
	</tr>
	<tr>
		<td align="right">规格</td>
		<td align="left"><input name="afe.ESs" id="ESs"
			style="width: 90px; text-align: left;" dataType="Require"
			msg="[规格]必须填写" /> <select name="ESs00" class="wenbenkuang"
			id="ESs00"
			onChange="document.getElementById('ESs').value=this.options[this.selectedIndex].value">
			<option value="">纸张规格</option>
			<option value="787*960">787*960</option>
			<option value="787*1092">787*1092</option>
			<option value="850*1168">850*1168</option>
			<option value="889*1194">889*1194</option>
			<option value="880*1230">880*1230</option>
		</select></td>
	</tr>
	<tr>
		<td align="right">印张</td>
		<td align="left"><input name="afe.EPs" dataType="Double"
			msg="[印张]请填数字" /></td>
	</tr>
	<tr>
		<td align="right">令数</td>
		<td align="left"><input name="afe.EReam" dataType="Double"
			msg="[令数]请填数字" id="EReam"
			onBlur="(document.getElementById('totalPapers').value=(document.getElementById('EReam').value*1)+(document.getElementById('EOvers').value*1))" />
		加放:<input name="afe.EOvers" id="EOvers" dataType="Double"
			msg="[加放]请填数字"
			onBlur="(document.getElementById('totalPapers').value=(document.getElementById('EReam').value*1)+(document.getElementById('EOvers').value*1))" />
		总纸数: <s:textfield id="totalPapers" disabled="true" /></td>
	</tr>
	<tr>
		<td align="right">印版类型</td>
		<td align="left"><select name="afe.EPlateType" id="select">
			<option value="2K">对开</option>
			<option value="4K">四开</option>
			<option value="8K">八开</option>
		</select> 版数: <input name="afe.EPlateAmount" dataType="Integer"
			msg="[版数]请填一个整数" /></td>
	</tr>
</table>
<table>
	<tr>
		<td></td>
	</tr>
</table>
<s:submit value="继续增加元件" onclick="doSubmit('addE');" /> <s:submit
	value="查看任务单" onclick="doSubmit('submit');" /></form>
</body>
</html>