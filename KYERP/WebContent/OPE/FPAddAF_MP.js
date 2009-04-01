Ext.onReady(function(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side';
	Ext.BLANK_IMAGE_URL = '../Library/js/ext/resources/images/default/s.gif';
	
    var storeYW = new Ext.data.SimpleStore({
    	url:'../OPE/jsongetAllYW.action',
    	fields:['value']
    });
    
    var storeClient = new Ext.data.JsonStore( {
			url :'../Client/jsongetAllClient.action',
			fields : [ 'CCCom', 'CCDa', 'clientLm', 'CCAddress' ]
		});
    var storeLinkman = new Ext.data.JsonStore({
    	data:[],
    	fields:['CLmLinkman','CLmTel']
    });

    var storePresswork = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({url:'../OPE/jsongetPresswork.action'}),
        reader: new Ext.data.ArrayReader({},[
            {name:'presswork'},
            {name:'seriesName'},
        ])
    });
	
    var comboYW = new Ext.form.ComboBox({
        store: storeYW,
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
        allowBlank: false,
        mode: 'local',
        width: 380,
        triggerAction: 'all',
        valueField: 'CCCom',
        displayField: 'CCCom',
        readOnly: false,
        resizable:true,
		applyTo: 'comboClient'
    });
    var comboLinkman = new Ext.form.ComboBox({
        store: storeLinkman,
        value:'',
        width: 180,
        mode: 'local',
        triggerAction: 'all',
        valueField: 'CLmLinkman',
        displayField: 'CLmLinkman',
        readOnly: false,
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
        readOnly: false,
		applyTo: 'comboTel'
    });
    var comboPresswork = new Ext.form.ComboBox({
        store: storePresswork,
        allowBlank: false,
        blankText: '必须填写印品名称',
        value:'名片',
        width: 180,
        mode: 'local',
        triggerAction: 'all',
        valueField: 'presswork',
        displayField: 'presswork',
        readOnly: false,
        resizable:true,
        minListWidth:480,
		applyTo: 'comboPresswork'
    });
   
    var comboSendPlace = new Ext.form.ComboBox({
        store: storeClient,
        emptyText: ' ',
        width: 480,
        mode: 'local',
        triggerAction: 'all',
        valueField: 'CCAddress',
        displayField: 'CCAddress',
        readOnly: false,
        resizable:true,
        minListWidth:480,
		applyTo: 'comboSendPlace'
    });
    

    var datepicker_planPress = new Ext.form.DateField({
    	format:"Y-m-d",
    	readOnly: true,
    	applyTo: 'af_planPress'
    });
    var datepicker_planDeliver = new Ext.form.DateField({
    	format:"Y-m-d",
    	allowBlank:false,
    	readOnly: true,
    	applyTo: 'af_planDeliver',
    	value: new Date()
    });

    storeYW.load();
    storeClient.load();
    comboYW.on('select', function(comboBox){
        var value = comboBox.getValue();
        //storeClient.load({params:{YWName:value}});
        
    });
    comboClient.on('select', function(comboBox,record){
        var value = comboBox.getValue();
        storeLinkman.loadData(record.get('clientLm'));
        comboSendPlace.setValue(record.get('CCDa'));        
        storePresswork.load({params:{client:value}});
    });
    comboLinkman.on('select', function(comboBox,record,indexOfStore){
        var value = comboBox.getValue();
        comboTel.setValue(record.get('CLmTel'));
    });
    
});