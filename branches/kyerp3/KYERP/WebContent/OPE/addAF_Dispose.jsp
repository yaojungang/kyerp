<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#session['af']" name="af" id="af" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加特殊加工项目-业务管理</title>
<script type="text/javascript"
	src="<s:url value="/Library/js/Validator.js" />">
 </script>
</head>
<body>
<form action='<s:url value="/OPE/addAF_Dispose.action" />' method="post"
	id="form1" name="forms" onSubmit="return Validator.Validate(this,2)">
<table width="480" border="1" cellpadding="4" cellspacing="1"
	bgcolor="#FFFFCC">
	<tr>
		<td width="80" align="right" bgcolor="#FAFAFA">项目名称</td>
		<td><s:select name="afd.afEType"
			list="{'封面覆膜','装订','模切','照排','其它'}" /></td>
	</tr>
	<tr>
		<td width="80" align="right" bgcolor="#FAFAFA">工艺名称</td>
		<td><input name="afd.afDItem" dataType="Require" msg="[工艺名称]必须填写"
			type="text" id="afDItem0" style="width: 81px" /> <s:select
			name="afDItemSelect0"
			list="{'请选择工艺名称','覆亚膜','覆光膜','勒口','烫金','烫红','胶订','骑马订','三折页','胶订+铁钉','一折页','两折页','光边','打字','复印','出片'}"
			cssStyle="width: 91px;text-align:left;"
			onchange="(document.form1.afDItem0.value=this.options[this.selectedIndex].value)" /></td>
	</tr>
	<tr>
		<td width="80" align="right" bgcolor="#FAFAFA">数量</td>
		<td><input type="text" name="afd.afDAmount"
			value="<s:property value="#af.amount"/>" dataType="Integer"
			msg="[数量]请填写整数" /></td>
	</tr>
	<tr>
		<td width="80" align="right" bgcolor="#FAFAFA">厂家</td>
		<td><input name='afd.afDFactory' dataType="Require"
			msg="[厂家]必须填写" type='text' class="wenbenkuang" id="bindingFactory0"
			style="width: 141px" value='' /> <SELECT
			name="bindingFactoryselect0" size="1" class="wenbenkuang"
			onChange="(document.form1.bindingFactory0.value=this.options[this.selectedIndex].value)">
			<OPTION selected value=>==请选装订厂==</OPTION>
			<s:iterator value="#request['bindingFactoryList']"
				id="bindingFactory">
				<option value="<s:property value="#bindingFactory.factory"/>"><s:property
					value="#bindingFactory.factory" /></option>
			</s:iterator>
		</select></td>
	</tr>
	<tr>
		<td width="80" align="right" bgcolor="#FAFAFA">备注</td>
		<td><s:textarea name="afd.afDRemark"
			cssStyle="width: 321px;text-align:left;" /></td>
	</tr>
</table>
<table>
	<tr>
		<td></td>
	</tr>
</table>
<script type="text/javascript">
function doSubmit(cmd){
    if(cmd == "addD"){
        document.forms.action = "${pageContext.request.contextPath}/OPE/addAF_Dispose.action";
    } else if(cmd == "submit"){
        document.forms.action = "${pageContext.request.contextPath}/OPE/addAF_Dispose_view.action";
    }
}
</script> <s:submit value="继续增加特殊加工项目" onclick="doSubmit('addD');" /> <s:submit
	value="查看任务单" onclick="doSubmit('submit');" /></form>
</body>
</html>