<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>统计客户回款情况</title>
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
            {name:'value'},
            {name:'value'}
        ])
    });
    var storeClient = new Ext.data.JsonStore({
    	url:'../OPE/jsongetClientByYW.action',
    	fields:['CCCom','CCDa','clientLm']
    });
	
    var comboYW = new Ext.form.ComboBox({
        store: storeYW,
        emptyText: '请选择接洽业务员',
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
    
    var comboClient = new Ext.form.ComboBox({
        store: storeClient,
        emptyText: '请选择委印单位',
        allowBlank: false,
        blankText: '必须选择一个系统中已有的委印单位',
        mode: 'local',
        width: 380,
        triggerAction: 'all',
        valueField: 'CCCom',
        displayField: 'CCCom',
        readOnly: true,
        resizable:true,
        minListWidth:380,
        pageSize: 5,
		applyTo: 'comboClient'
    });

    storeYW.load();
    comboYW.on('select', function(comboBox){
        var value = comboBox.getValue();
        storeClient.load({params:{YWName:value}});
    });
    
});
</script>
</head>
<body>
<s:form action="getMoneyByClientandType" method="post" target="_blank">
	<h2 align="left">统计客户回款情况</h2>
	<table width="90%" border="0" cellspacing="4" cellpadding="4">
		<tr>
			<td width="180" align="right">委印单位：</td>
			<td align="left"><input name="YWName" id="comboYW" type="text" />
			<input name="client" id="comboClient" type="text" /></td>
		</tr>
		<tr>
			<td width="180" align="right">业务类型：</td>
			<td align="left"><select name="AFType" id="AFType">
				<option value="SK">受控</option>
				<option value="LH" selected="selected">零活</option>
			</select></td>
		</tr>
		<tr>
			<td align="right">任务单号范围：</td>
			<td align="left"><input name="StartAFNo" type="text"
				id="StartAFNo" value="20080546"> — <input name="EndAFNo"
				type="text" id="EndAFNo" value="20080670"></td>
		</tr>
		<tr>
			<td align="right">&nbsp;</td>
			<td align="left"><input name="提交" type="submit" value="统计"></td>
		</tr>
	</table>
</s:form>
<br>
</html>
