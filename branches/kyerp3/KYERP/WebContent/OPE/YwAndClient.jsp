<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>业务员和客户</title>
<link rel="stylesheet" type="text/css"
	href="../Library/js/ext/resources/css/ext-all.css" />
<script type="text/javascript"
	src="../Library/js/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="../Library/js/ext/ext-all.js"></script>
<script type="text/javascript">
Ext.onReady(function(){
    var storeYW = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({url:'http://localhost:8080/PIM2/test/jsongetAllYW.action'}),
        reader: new Ext.data.ArrayReader({},[
            {name:'value'},
            {name:'text'}
        ])
    });
    var stortClient = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({url:'http://localhost:8080/PIM2/test/jsongetClientByYW.action'}),
        reader: new Ext.data.ArrayReader({},[
            {name:'value'},
            {name:'text'}
        ])
    });
    var stortLinkman = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({url:'http://localhost:8080/PIM2/test/jsongetLinkmanByClient.action'}),
        reader: new Ext.data.ArrayReader({},[
            {name:'value'},
            {name:'text'}
        ])
    });
    var stortTel = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({url:'http://localhost:8080/PIM2/test/jsongetTelByLinkman.action'}),
        reader: new Ext.data.ArrayReader({},[
            {name:'value'},
            {name:'text'}
        ])
    });
    var stortPresswork = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({url:'http://localhost:8080/PIM2/test/jsongetPresswork.action'}),
        reader: new Ext.data.ArrayReader({},[
            {name:'value'},
            {name:'text'}
        ])
    });
    var stortSeriesName = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({url:'http://localhost:8080/PIM2/test/jsongetSeriesName.action'}),
        reader: new Ext.data.ArrayReader({},[
            {name:'value'},
            {name:'text'}
        ])
    });
	
	
    var comboYW = new Ext.form.ComboBox({
        store: storeYW,
        emptyText: '请选择接洽业务员',
        width: '380',
        mode: 'local',
        triggerAction: 'all',
        valueField: 'value',
        displayField: 'text',
        readOnly: true,
		applyTo: 'comboYW'
    });
    var comboClient = new Ext.form.ComboBox({
        store: stortClient,
        emptyText: '请选择委印单位',
        mode: 'local',
        width: '580',
        triggerAction: 'all',
        valueField: 'value',
        displayField: 'text',
        readOnly: true,
        resizable:true,
        minListWidth:380,
        pageSize: 5,
		applyTo: 'comboClient'
    });
    var comboLinkman = new Ext.form.ComboBox({
        store: stortLinkman,
        emptyText: '请选择联系人',
        width: '380',
        mode: 'local',
        triggerAction: 'all',
        valueField: 'value',
        displayField: 'text',
        readOnly: false,
		applyTo: 'comboLinkman'
    });
    var comboTel = new Ext.form.ComboBox({
        store: stortTel,
        emptyText: '请选择联系电话',
        width: '380',
        mode: 'local',
        triggerAction: 'all',
        valueField: 'value',
        displayField: 'text',
        readOnly: false,
		applyTo: 'comboTel'
    });
    var comboPresswork = new Ext.form.ComboBox({
        store: stortPresswork,
        emptyText: '印品名称',
        width: '380',
        mode: 'local',
        triggerAction: 'all',
        valueField: 'value',
        displayField: 'text',
        readOnly: false,
        resizable:true,
        minListWidth:480,
        pageSize: 10,
		applyTo: 'comboPresswork'
    });
    var comboSeriesName = new Ext.form.ComboBox({
        store: stortSeriesName,
        emptyText: '丛书名',
        width: '380',
        mode: 'local',
        triggerAction: 'all',
        valueField: 'value',
        displayField: 'text',
        readOnly: false,
        resizable:true,
        minListWidth:480,
        pageSize: 10,
		applyTo: 'comboSeriesName'
    });

    storeYW.load();
    comboYW.on('select', function(comboBox){
        var value = comboBox.getValue();
        stortClient.load({params:{YWName:value}});
        stortPresswork.load({params:{YWName:value}});
        stortSeriesName.load({params:{YWName:value}});
    });
    comboClient.on('select', function(comboBox){
        var value = comboBox.getValue();
        stortLinkman.load({params:{clientName:value}});
    });
    comboLinkman.on('select', function(comboBox){
        var value = comboBox.getValue();
        stortTel.load({params:{linkmanName:value}});
    });
    comboTel.on('select', function(comboBox){
        alert(comboYW.getValue() + '-' + comboClient.getValue() + '-' + comboLinkman.getValue());
    });
    
});
        </script>
</head>
<body>
<script type="text/javascript" src="../examples.js"></script>
<div style="text-align: left;">接洽人:<input id="comboYW" type="text" /><br>
委印单位:<input id="comboClient" type="text" /><br>
联系人:<input id="comboLinkman" type="text" /><br>
联系电话:<input id="comboTel" type="text" /><br>
<br>
印品名称:<input id="comboPresswork" type="text" /><br>
丛书名:<input id="comboSeriesName" type="text" /><br>
</div>
</body>
</html>
