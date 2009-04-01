<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>javascript无限级联动菜单</title>
<script src="${pageContext.request.contextPath}/Library/js/prototype.js"></script>
<script src="${pageContext.request.contextPath}/Library/js/linkage.js"></script>
</head>
<body>
<xml id="clientData" style="height:0px; width:0px; visibility:hidden;">
<TreeNodes>
<s:iterator value="#request['ywList']" id="yw" status="yw">
	<s:if test="#yw.realname != null">
		<yw Value="<s:property value="#yw.realname" />">
		<s:iterator value="#request['clientList']" id="client" status="st">
			<s:if test="#yw.realname==#client.ywname">
				<client Value="<s:property value="#client.CCCom" />">
				<s:iterator value="#client.ClientLm">
					<linkman Value="<s:property value="CLmLinkman" />">
					<tel Value="<s:property value="CLmTel" />" />
					</linkman>
				</s:iterator>
				</client>
			</s:if>
		</s:iterator>
		</yw>
	</s:if>
</s:iterator>
</TreeNodes>
</xml>
<p id="intro_title">无限级SELECT联动菜单</p>
<form action="" id="form1" method="get">
<table width="90%" border="0" align="center" cellpadding="2"
	cellspacing="1" bgcolor="#CCCCCC">
	<tr bgcolor="F1F1F1">
		<td height="24" colspan="2" align="center"><b>同一页面示例二
		(数据源为页面中XML数据岛)</b></td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td width="12%" height="24" align="center">业务员:</td>
		<td><input name="af.cp" type='text' id="CP" style="width: 180px;" />
		<select name="afcp" id="afcp" USEDATA="clientData" SUBCLASS="1"
			onChange="document.getElementById('CP').value=this.options[this.selectedIndex].value">
		</select></td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td height="24" align="center">委印单位:</td>
		<td><select name="af.client" id="afclient" USEDATA="clientData"
			SUBCLASS="2"></select></td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td height="24" align="center">经手人:</td>
		<td><input name='af.linkman' type='text' class="wenbenkuang"
			id="Linkman" style="width: 180px;" value='' /> <select
			name="Linkman00" id="Linkman00" USEDATA="clientData" SUBCLASS="3"
			onChange="document.getElementById('Linkman').value=this.options[this.selectedIndex].value">
		</select></td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td height="24" align="center">联系电话:</td>
		<td><input name='af.tel' type='text' class="wenbenkuang" id="Tel"
			style="width: 180px;" /> <select name="Tel00" id="Tel00"
			USEDATA="clientData" SUBCLASS="4"
			onChange="document.getElementById('Tel').value=this.options[this.selectedIndex].value">
		</select></td>
	</tr>
	<tr bgcolor="F1F1F1">
		<td height="24" colspan="2" align="center"><input type="button"
			value="提交" onClick="testSubmit('clientData')"></td>
	</tr>
</table>
</form>
<script language="javascript">
function switchTab(tabid){
	for(var i=1; i<3; i++){
		Element.removeClassName($('imageTab' + i), "Selected");
		Element.addClassName($("container" + i), "hide");
	}	
	Element.addClassName($('imageTab' + tabid), "Selected");
	Element.removeClassName($("container" + tabid), "hide");
}

//测试函数
function testSubmit(theDataSrc) {
	var str = "";

	var selectNodes = document.getElementsByTagName("select");
	for (i = 0; i < selectNodes.length; i++) {
		if ($V(selectNodes[i] , "USEDATA") == theDataSrc) {
			str += "[" + $V(selectNodes[i] , "name") + "] : Value=" + $F(selectNodes[i]) + " ; Desc=" + selectNodes[i][selectNodes[i].selectedIndex].childNodes[0].nodeValue + "\n";
		}
	}
	alert(str);
}


var linkage2 = new Linkage("clientData");
linkage2.init();
//初始化数据
linkage2.initLinkage("clientData","孙玉萍",1);
// linkage2.initLinkage("clientData","清华大学出版社",2);
</script>
</body>
</html>
