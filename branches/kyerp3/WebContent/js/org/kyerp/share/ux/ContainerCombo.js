Ext.ux.ContainerCombo = Ext.extend(Ext.form.ComboBox,{
	renderId : "",
	obj : null, // 要挂载到comboBox的组件
	tpl : "",
	store : new Ext.data.ArrayStore({fields:[],data:[[]]}),
	triggerAction : "all",
	mode : "local",
	selectedClass : '',   
    onSelect : Ext.emptyFn,
	initComponent : function(){
		this.renderId = Ext.id();
		this.tpl = "<div id='"+this.renderId+"'></div>";
		this.on('afterrender', this._toListener, this);		
		Ext.ux.ContainerCombo.superclass.initComponent.call(this);
	},
	_toListener : function(){
		if(this.obj){
			this.on('expand', this._toRender, this);
		}else{
			throw "obj is null or undefined";
		}		
	},
	_toRender : function(){
		this.obj.render(this.renderId);
	}
});
Ext.reg('ContainerCombo',Ext.ux.ContainerCombo);