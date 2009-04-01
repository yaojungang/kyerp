<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#request['ClientInfo']" name="client" id="client" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="#client.CCCom" />-客户管理</title>
<link
	href="${pageContext.request.contextPath}/Library/css/ClientInfo.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<s:url value="/Library/js/Validator.js" />"></script>
<link rel="stylesheet" type="text/css"
	href="../Library/js/ext/resources/css/ext-all.css" />
<script type="text/javascript"
	src="../Library/js/ext/adapter/jquery/jquery.js"></script>
<script type="text/javascript" src="../Library/js/jquery.caretInsert.js"></script>
<script type="text/javascript"
	src="../Library/js/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="../Library/js/ext/ext-all.js"></script>
<script type="text/javascript"
	src="../Library/js/ext/source/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript">
Ext.onReady(function(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side';
	Ext.BLANK_IMAGE_URL = '../Library/js/ext/resources/images/default/s.gif';
	
    var storeYW = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({url:'../OPE/jsongetAllYW.action'}),
        reader: new Ext.data.ArrayReader({},[
            {name:'value'},
            {name:'text'}
        ])
    });
   var comboYW = new Ext.form.ComboBox({
        store: storeYW,
        emptyText: '请选择主要负责业务员',
        allowBlank: false,
        blankText: '必须选择主要负责业务员',
        width: 180,
        mode: 'local',
        triggerAction: 'all',
        valueField: 'value',
        displayField: 'text',
        readOnly: true,
		applyTo: 'client_ywname'
    });
   storeYW.load();
});
</script>
</head>
<body>
<span class="pageTitle"><s:property value="#request['pageTitle']" /></span>
<form action="saveClient.action" method="post"><s:if
	test="#client.CCId>0">
	<input type="hidden" name="client.CCId"
		value="<s:property value="#client.CCId"/>" />
</s:if>
<table width="750" border="1" align="center" cellpadding="6"
	cellspacing="0">
	<tr>
		<td colspan="2" align="center" bgcolor="#f0f0f0"><span
			class="bigitem">客户基本信息</span></td>
	</tr>
	<tr>
		<td width="18%" align="right">单位名称</td>
		<td align="left"><input name="client.CCCom" type="text"
			id="client_CCCom" size="40"
			value="<s:property value="#client.CCCom" />"></td>
	</tr>
	<tr>
		<td align="right">主要负责业务员</td>
		<td align="left"><input name="client.ywname" id="client_ywname"
			type="text" value="<s:property value="#client.ywname" />" /></td>
	</tr>
	<tr>
		<td align="right">传真</td>
		<td align="left"><input name="client.CCFax" type="text"
			id="client_CCFax" size="16"
			value="<s:property value="#client.CCFax" />"></td>
	</tr>
	<tr>
		<td align="right">地址</td>
		<td align="left"><input name="client.CCAddress" type="text"
			id="client_CCAddress" size="50"
			value="<s:property value="#client.CCAddress" />"></td>
	</tr>
	<tr>
		<td align="right">邮编</td>
		<td align="left"><input name="client.CCPc" type="text"
			id="client_CCPc" size="10"
			value="<s:property value="#client.CCPc" />"></td>
	</tr>
	<tr>
		<td align="right">发行地址</td>
		<td align="left"><input name="client.CCDa" type="text"
			id="client_CCDa" size="50"
			value="<s:property value="#client.CCDa" />"></td>
	</tr>
	<tr>
		<td align="right">发行电话</td>
		<td align="left"><input name="client.CCDt" type="text"
			id="client_CCDt" size="16"
			value="<s:property value="#client.CCDt" />"></td>
	</tr>
	<tr>
		<td align="right">备注</td>
		<td align="left"><textarea name="client.CCRemark"
			id="client_CCRemark" cols="50" rows="5"
			value="<s:property value="#client.CCRemark" />"></textarea></td>
	</tr>
	<tr>
		<td colspan="2" align="center" bgcolor="#f0f0f0"><span
			class="bigitem">联系人</span></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><s:iterator
			value="#client.ClientLm" status="st">
			<table width="100%" border="1" align="center" cellpadding="6"
				cellspacing="0">
				<tr>
					<td colspan="2" align="left" bgcolor="#f0f0f0">联系人ID: <s:property
						value="CLmId" /><input type="hidden"
						name="linkmans[<s:property value="#st.index" />].CLmId"
						value='<s:property value="CLmId" />'></td>
				</tr>
				<tr>
					<td width="18%" align="right">姓名</td>
					<td align="left"><input type="text"
						name="linkmans[<s:property value="#st.index" />].CLmLinkman"
						size="8" value='<s:property value="CLmLinkman" />'>
					说明:欲删除这个联系人,请将本栏置空!</td>
				</tr>
				<tr>
					<td align="right">职务</td>
					<td align="left"><input
						name="linkmans[<s:property value="#st.index" />].CLmJob"
						type="text" size="16" value='<s:property value="CLmJob" />'></td>
				</tr>
				<tr>
					<td align="right">办公电话</td>
					<td align="left"><input
						name="linkmans[<s:property value="#st.index" />].CLmTel"
						type="text" size="16" value='<s:property value="CLmTel" />'></td>
				</tr>
				<tr>
					<td align="right">手机</td>
					<td align="left"><input
						name="linkmans[<s:property value="#st.index" />].CLmMobile"
						type="text" size="13" value='<s:property value="CLmMobile" />'></td>
				</tr>
				<tr>
					<td align="right">备注</td>
					<td align="left"><textarea
						name="linkmans[<s:property value="#st.index" />].CLmRemark"
						cols="50" rows="5" value='<s:property value="CLmRemark" />'></textarea></td>
				</tr>
			</table>
		</s:iterator></td>
	</tr>
</table>
<br>
<center><input type="submit" value="保存" /></center>
<form>
</body>
</html>