<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#session['af']" name="af" id="af" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加工序</title>
<script type="text/javascript"
	src="<s:url value="/Library/js/Validator.js" />">
 </script>
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
<form action='FPAddDispose_save.action' method="post" id="form1"
	name="forms" onSubmit="return Validator.Validate(this,2)"><input
	type="button" value="增加" onClick="addV()" /> <br>
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
			<td><input name="afvs[0].itemName" dataType="Require"
				msg="[工序名称]必须选择" /></td>
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
<s:submit value="查看任务单" /></form>
</body>
</html>