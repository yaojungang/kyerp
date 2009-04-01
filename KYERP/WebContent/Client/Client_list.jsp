<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="#request['pageTitle']" />-客户管理</title>
<link rel="stylesheet" type="text/css"
	href="../Library/js/ext/resources/css/ext-all.css" />
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
            {name:'value'}
        ])
    });
    var storeClient = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({url:'../OPE/jsongetClientByYW.action'}),
        reader: new Ext.data.ArrayReader({},[
            {name:'value'}
        ])
    });
	
    var comboYW = new Ext.form.ComboBox({
        store: storeYW,
        emptyText: '主要负责业务员',
        allowBlank: false,
        blankText: '必须选择业务员',
        width: 180,
        mode: 'local',
        triggerAction: 'all',
        valueField: 'value',
        displayField: 'value',
        readOnly: true,
		applyTo: 'comboYW'
    });

    storeYW.load();
    
});
</script>
</head>
<body>
<span class="pageTitle"><s:property value="#request['pageTitle']" /></span>
<form action="getClientByYW.action" method="post">
<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="4">
	<tr>
		<td width="65" align="right">查找客户:</td>
		<td width="190"><input name="YWName" id="comboYW" type="text" />
		</td>
		<td align="left"><input name="s" id="ss" type="submit" value="查找"></td>
	</tr>
</table>
</form>
<table width="100%" border="0" align="center" cellpadding="2"
	cellspacing="1" bgcolor="#CCCCCC">
	<tr align="center">
		<td bgcolor="#EEEEEE" width="40">序号</td>
		<td bgcolor="#EEEEEE" width="80">客户编号</td>
		<td bgcolor="#EEEEEE">单位名称</td>
		<td bgcolor="#EEEEEE">主要负责业务员</td>
		<td bgcolor="#EEEEEE">地址</td>
		<td bgcolor="#EEEEEE">发行电话</td>
		<td bgcolor="#EEEEEE">管理</td>
	</tr>
	<s:iterator value="#request['clientList']" id="client" status="st">
		<tr <s:if test="#st.even">style="background-color:#EAEAEA"</s:if>
			<s:if test="#st.odd">style="background-color:#FFffff"</s:if>>
			<td height="19" align="center"><s:property value="#st.index + 1" /></td>
			<td height="19" align="center"><s:property value="#client.CCId" /></td>
			<td align="center"><a
				href="${pageContext.request.contextPath}/Client/ClientInfo.action?clientId=<s:property value="#client.CCId" />">
			<s:property value="#client.CCCom" /></a></td>
			<td align="center"><s:property value="#client.ywname" /></td>
			<td align="center"><s:property value="#client.CCAddress" /></td>
			<td align="center"><s:property value="#client.CCDt" /></td>
			<td align="center"><a
				href="${pageContext.request.contextPath}/Client/editClient.action?clientId=<s:property value="#client.CCId" />">修改</a>
			</td>
		</tr>
	</s:iterator>
</table>
<p></p>
<p><br>
<br>
</p>
</body>
</html>