/** ****************************************************************************** */
MPInputFormPanel = Ext.extend(Ext.form.FormPanel, {
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		MPInputFormPanel.superclass.constructor.call(this, {
			labelWidth :60,
			labelAlign :"right",
			defaultType :'textfield',
			baseCls :"x-plain",
			layout :"form",
			defaults : {
				anchor :'98%'
			},
			items : [ {
				// xtype :'hidden',
				fieldLabel :"类型",
				name :'af.iso',
				value :'FP'
			}, {
				xtype :'datefield',
				fieldLabel :"时间",
				name :'af.ad',
				value :new Date(),
				format :'Y-m-d H:m:s'
			}, {
				fieldLabel :"委印单位",
				name :'af.client'
			}, {
				fieldLabel :"联系人",
				name :'af.linkman'
			}, {
				fieldLabel :"印品名称",
				// allowBlank: false,
				name :'af.presswork'
			} ]
		});
		this.addEvents('submit');
	},
	submit : function() {
		try {
			if (this.url != "")
				alert(this.getValues());
				this.getForm().submit( {
					url :this.url,
					success :this.onSubmit,
					waitTitle :'提交数据',
					//waitMsg :'数据提交中,请稍候~~',
					waitMsg :this.getValues(),
					scope :this
				});
		} catch (_err) {
			Ext.alert("系统提示", '提交数据出错了!');
		}
	},
	getValues : function() {
		if (this.getForm().isValid())
			return new Ext.data.Record(this.getForm().getValues());
		else
			throw Errow("表单验证未通过!");
	},
	setValues : function(_r) {
		this.getForm().loadRecord(_r);
	},
	reset : function() {
		this.getForm().reset();
	},
	onSubmit : function(_form, _action) {
		
		this.fireEvent("submit", this, _action, this.getValues());
	}
});

/** ****************************************************************************** */
MPInputWindow = Ext.extend(Ext.Window, {
	form :null,
	url :'',
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.form = new MPInputFormPanel( {
			url :this.url
		});
		MPInputWindow.superclass.constructor.call(this, {
			plain :true,
			closeAction :'hide',
			width :300,
			modal :true,
			items :this.form,
			buttons : [ {
				text :'确定',
				handler :this.onSubmitClick,
				scope :this
			}, {
				text :'取消',
				handler :this.onCanelClick,
				scope :this
			} ]
		});

		this.addEvents("submit");

		this.form.on('submit', this.onSubmit, this);
	},
	close : function() {
		this.form.reset();
		this.hide();
	},
	onSubmitClick : function() {
		// this.fireEvent("submit", this, this.form.getValues());
	// this.close();
	
	this.form.submit();
	// this.close();
	// alert(Ext.util.JSON.encode(this.form.getValues().data));
},
onCanelClick : function() {
	this.close();
},
onSubmit : function(_form, _action, _values) {
	// alert();
	try {
		this.fireEvent('submit', this, _values);
		
	} catch (_err) {
		return;
	}
	this.close();
}
});

/** ****************************************************************************** */
MPInputWindowInsert = Ext.extend(MPInputWindow, {
	title :"添加名片",
	url :'../test/JsonTest.action',
	onSubmit:function(_form , _action , _values){
	
	var _data = _values.data ;
	
	//Ext.apply(_data , {id:_action.result.id}) ;

	try{
		
		this.fireEvent("submit" , this , new Ext.data.Record(_data)) ;
		
	}catch(_err){
		
		return ;
	}
	
	this.close() ;
}
});
/** ****************************************************************************** */
MPInputWindowUpdate = Ext.extend(MPInputWindow, {
	title :"修改名片",
	load : function(_r) {
		this.form.setValues(_r);
	}
});
/** ****************************************************************************** */

MPListGridPanel = Ext.extend(Ext.grid.GridPanel, {
	insertWin :new MPInputWindowInsert(),
	updateWin :new MPInputWindowUpdate(),
	constructor : function() {
		var store_AFMP = new Ext.data.JsonStore( {
			url :'../test/ListAF.action',
			fields : [ 'ad','iso','afNo', 'client', 'presswork', 'linkman', 'tel' ]
		});
		store_AFMP.load();
		MPListGridPanel.superclass.constructor.call(this, {
			applyTo :'divMPListGridPanel',
			title :'名片任务列表',
			//height :500,
			autoHeight:true,
			autoExpandColumn :3,
			stripeRows :true,
			anchor :'98%',
			tbar : [
					{
						text :'添    加',
						handler : function() {
							this.insertWin.show();
							
						},
						scope :this
					},
					'-',
					{
						text :'修    改',
						handler : function() {
							this.updateWin.show();
							try {
								this.updateWin.load(this.getSelected());
							} catch (_err) {
								Ext.Msg.alert("系统提示", _err.description);
								this.updateWin.close();
							}
						},
						scope :this
					},
					'-',
					{
						text :'删    除',
						handler : function() {
							Ext.Msg.confirm("系统提示", "您确定删除这条记录吗?",
									this.onRemove, this);
							// this.remove();
						},
						scope :this
					} ],
			colModel :new Ext.grid.ColumnModel( [ {
				header :"开单日期",
				dataIndex :'ad',
				name :'ad',
				format :'Y-m-d',
				align :"left"
			}, {
				header :"类型",
				dataIndex :'iso',
				width :30,
				name :'af.iso',
				align :"center"
			}, {
				header :"任务单号",
				dataIndex :'afNo',
				width :60,
				name :'af.afNo',
				align :"left"
			}, {
				header :"委印单位",
				dataIndex :'client',
				width :200,
				name :'clent',
				align :"left"
			}, {
				header :"联系人",
				name :'linkman',
				dataIndex :'linkman',
				align :"center"
			}, {
				header :"印品名称",
				name :'af.presswork',
				dataIndex :'presswork',
				width :300,
				align :"center"
			}, {
				header :"联系电话",
				name :'tel',
				dataIndex :'tel',
				align :"center"
			} ]),
			store :store_AFMP,
			bbar :new Ext.PagingToolbar( {
				store :store_AFMP,
				pageSize :5
			})
		});
		this.insertWin.on("submit", this.onInsertWinSubmit, this);
		this.updateWin.on("submit", this.onUpdateWinSubmit, this);
		this.addEvents("rowselect");

	},
	getSelected : function() {
		var _sm = this.getSelectionModel();
		if (_sm.getCount() == 0)
			throw Error("请先选定一条记录!");
		return _sm.getSelected();
	},
	insert : function(_r) {
		
		this.getStore().insert(0, _r);
	},
	update : function(_r) {
		try {
			var _sr = this.getSelected();
			var _data = _sr.data;
			for ( var _i in _data) {
				_sr.set(_i, _r.get(_i));
				_sr.commit();
			}
		} catch (_err) {
			Ext.Msg.alert("系统提示", _err.description);
		}
	},
	remove : function() {
		try {
			this.getStore().remove(this.getSelected());
		} catch (_err) {
			Ext.Msg.alert("系统提示", _err.description);
		}
	},
	onInsertWinSubmit : function(_win, _r) {
		this.insert(_r);
	},
	onUpdateWinSubmit : function(_win, _r) {
		this.update(_r);

	},
	onRemove : function(_btn) {
		if (_btn == 'yes')
			this.remove();
	}
});

/** ****************************************************************************** */
