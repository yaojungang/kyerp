Ext.onReady(function(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side';
	Ext.BLANK_IMAGE_URL = '../Library/js/ext/resources/images/default/s.gif';
	
    var storeYW = new Ext.data.SimpleStore({
    	url:'../OPE/jsongetAllYW.action',
    	fields:['value']
    });
    
    var storeClient = new Ext.data.JsonStore({
    	url:'../OPE/jsongetClientByYW.action',
    	fields:['CCCom','CCDa','clientLm']
    });
    var storeLinkman = new Ext.data.JsonStore({
    	data:[],
    	fields:['CLmLinkman','CLmTel']
    });

    var storePresswork = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({url:'../OPE/jsongetPresswork.action'}),
        reader: new Ext.data.ArrayReader({},[
            {name:'presswork'},
            {name:'seriesName'}
        ])
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
        resizable:true,
        minListWidth:380,
        pageSize: 5,
		applyTo: 'comboClient'
    });
    var comboLinkman = new Ext.form.ComboBox({
        store: storeLinkman,
        emptyText: '请选择联系人',
        width: 180,
        mode: 'local',
        triggerAction: 'all',
        valueField: 'CLmLinkman',
        displayField: 'CLmLinkman',
		applyTo: 'comboLinkman'
    });
    var comboTel = new Ext.form.ComboBox({
        store: storeLinkman,
        emptyText: '',
        width: 180,
        mode: 'local',
        triggerAction: 'all',
        valueField: 'CLmTel',
        displayField: 'CLmTel',
		applyTo: 'comboTel'
    });
    var comboPresswork = new Ext.form.ComboBox({
        store: storePresswork,
        emptyText: '印品名称',
        allowBlank: false,
        blankText: '必须填写印品名称',
        width: 380,
        mode: 'local',
        triggerAction: 'all',
        valueField: 'presswork',
        displayField: 'presswork',
        resizable:true,
        minListWidth:480,
		applyTo: 'comboPresswork'
    });
    var comboSeriesName = new Ext.form.ComboBox({
        store: storePresswork,
        emptyText: '',
        width: 380,
        mode: 'local',
        triggerAction: 'all',
        valueField: 'seriesName',
        displayField: 'seriesName',
        resizable:true,
        minListWidth:480,
		applyTo: 'comboSeriesName'
    });
    var comboSendPlace = new Ext.form.ComboBox({
        store: storeClient,
        emptyText: ' ',
        width: 480,
        mode: 'local',
        triggerAction: 'all',
        valueField: 'CCAddress',
        displayField: 'CCAddress',
        resizable:true,
        minListWidth:480,
		applyTo: 'comboSendPlace'
    });
    
    var storeaf_pcAf = new Ext.data.SimpleStore({
    	fields:['value'],
    	expandData:true,
    	data: ['YZ2010','YZ2009','YZ2008','TS10','TS09','TS08']
    });
    var comboaf_pcAf = new Ext.form.ComboBox({
    	store: storeaf_pcAf,
        emptyText: '',
        width: 180,
        mode: 'local',
        triggerAction: 'all',
        valueField: 'value',
        displayField: 'value',
        applyTo: 'af_pcAf'
    });
    
    var storeaf_isbn = new Ext.data.SimpleStore({
    	fields:['value'],
    	expandData:true,
    	data: ['978-7-302-']
    });
    var comboaf_isbn = new Ext.form.ComboBox({
    	store: storeaf_isbn,
        emptyText: '',
        width: 180,
        mode: 'local',
        triggerAction: 'all',
        valueField: 'value',
        displayField: 'value',
        applyTo: 'af_isbn'
    });

    var storeaf_format = new Ext.data.SimpleStore({
    	fields:['value'],
    	expandData:true,
    	data: ['异型','8开','大8开','16开','32开','大32开']
    });
    var comboaf_format = new Ext.form.ComboBox({
    	store: storeaf_format,
        emptyText: '16开',
        //allowBlank: false,
        //blankText: '必须填写开本',
        mode: 'local',
        triggerAction: 'all',
        valueField: 'value',
        displayField: 'value',
        applyTo: 'af_format'
    });
    
    var storeaf_fps = new Ext.data.SimpleStore({
    	fields:['value'],
    	expandData:true,
    	data: ['185*260','210*285','185*230']
    });
    var comboaf_fps = new Ext.form.ComboBox({
    	store: storeaf_fps,
        emptyText: '185*260',
        //allowBlank: false,
        //blankText: '必须填写成品尺寸',
        mode: 'local',
        triggerAction: 'all',
        valueField: 'value',
        displayField: 'value',
        applyTo: 'af_fps'
    });
    
    var storeaf_edition = new Ext.data.SimpleStore({
    	fields:['value'],
    	expandData:true,
    	data: ['1-1','1-2','1-3','1-4','1-5','1-6','2-1','2-2','2-3','3-1','3-2','3-3','4-1','4-2','4-3']
    });
    var comboaf_edition = new Ext.form.ComboBox({
    	store:storeaf_edition,
        emptyText: '1-1',
        allowBlank: true,
        mode: 'local',
        triggerAction: 'all',
        valueField: 'value',
        displayField: 'value',
        applyTo: 'af_edition'
    });
    
    var storeaf_bm = new Ext.data.SimpleStore({
    	fields:['value'],
    	expandData:true,
    	data: ['简装','胶订','骑马订','锁线订','胶订+铁钉','一折页','两折页','三折页']
    });
    var comboaf_bm = new Ext.form.ComboBox({
    	store: storeaf_bm,
        emptyText: '胶订',
        mode: 'local',
        triggerAction: 'all',
        valueField: 'value',
	    displayField: 'value',
        applyTo: 'af_bm'
    });
    var datepicker_planSendSample = new Ext.form.DateField({
    	format:"Y-m-d",
    	allowBlank:true,
    	applyTo: 'af_planSendSample'
    });
    var datepicker_planDeliver = new Ext.form.DateField({
    	format:"Y-m-d",
    	allowBlank:false,
    	value:new Date(),
    	applyTo: 'af_planDeliver'
    });

    storeYW.load();
    comboYW.on('select', function(comboBox){
        var value = comboBox.getValue();
        storeClient.load({params:{YWName:value}});
        
    });
    comboClient.on('select', function(comboBox,record){
        var value = comboBox.getValue();
        storeLinkman.loadData(record.get('clientLm'));
        //comboSendPlace.setValue(record.get('CCDa'));
        
        storePresswork.load({params:{client:value}});
    });
    comboLinkman.on('select', function(comboBox,record,indexOfStore){
        var value = comboBox.getValue();
        comboTel.setValue(record.get('CLmTel'));
    });
    
});