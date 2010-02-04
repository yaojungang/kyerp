/** ****************************************************************************** */
AfProcessInputFormPanel = Ext.extend(Ext.form.FormPanel, {
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		AfProcessInputFormPanel.superclass.constructor.call(this, {
			labelWidth :60,
			labelAlign :"right",
			defaultType :'textfield',
			baseCls :"x-plain",
			layout :"form",
			defaults : {
				anchor :'98%'
			},
			items : [ {
				fieldLabel :"ID",
				xtype:'hidden',
				name :'afp.id'
			},{
				fieldLabel :"工序类型",
				name :'afp.type',
				width :60,
				xtype :"combo",
				store :new Ext.data.SimpleStore( {
					data : [ [ "印前工序" ], [ "印刷工序" ], [ "印后工序" ] ],
					fields : [ "value" ]
				}),
				value :'印前工序',
				blankText :'请选择工序',
				mode :'local',
				triggerAction :'all',
				valueField :'value',
				displayField :'value',
				readOnly :true
			},{
				fieldLabel :"发生部门",
				name :'afp.department',
				width :60,
				xtype :"combo",
				store :new Ext.data.SimpleStore( {
					data : [ [ "照排" ], [ "制版" ], [ "小制版" ]  ,[ "02" ], [ "05" ], [ "双面" ], [ "装订" ], [ "发行" ]   ],
					fields : [ "value" ]
				}),
				value :'照排',
				blankText :'请选择发生部门',
				mode :'local',
				triggerAction :'all',
				valueField :'value',
				displayField :'value',
				readOnly :true
			}, {
				fieldLabel :"工序名称",
				name :'afp.name'
			}, {
				fieldLabel :"单价",
				name :'afp.price'
			}, {
				fieldLabel :"数量",
				name :'afp.amount'
			}, {
				fieldLabel :"单位",
				name :'afp.unit',
				width :60,
				xtype :"combo",
				store :new Ext.data.SimpleStore( {
					data : [ [ "张" ], [ "件" ], [ "份" ]  ,[ "盒" ] ],
					fields : [ "value" ]
				}),
				value :'张',
				blankText :'请选择单位',
				mode :'local',
				triggerAction :'all',
				valueField :'value',
				displayField :'value',
				readOnly :true
			}, {
				fieldLabel :"公式",
				name :'afp.formula'
			}, {
				fieldLabel :"备注",
				name :'afp.remark',
				xtype:'textarea'
			}]
		});
		this.addEvents('submit');
	},
	submit : function() {
		try {
			if (this.url != "")
				this.getForm().submit( {
					url :this.url,
					success :this.onSubmit,
					waitTitle :'提交数据',
					waitMsg :'数据提交中,请稍候~~',
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
AfProcessInputWindow = Ext.extend(Ext.Window, {
	form :null,
	url :'',
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.form = new AfProcessInputFormPanel( {
			url :this.url
		});
		AfProcessInputWindow.superclass.constructor.call(this, {
			plain :true,
			closeAction :'hide',
			collapsible :true,
			width :380,
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
		// alert("window panel : "+Ext.util.JSON.encode(this.form.getValues()));
	// this.fireEvent("submit", this, this.form.getValues());
	// this.close();
	// alert(Ext.util.JSON.encode(this.getValues());
	this.form.submit();
	// this.close();
	//alert(Ext.util.JSON.encode(this.form.getValues().data));
},
onCanelClick : function() {
	this.close();
},
onSubmit : function(_form, _action, _values) {
	// alert();
	// alert(Ext.util.JSON.encode(this.getValues());
	try {
		this.fireEvent('submit', this, _values);
	} catch (_err) {
		return;
	}
	this.close();
}
});

/** ****************************************************************************** */
AfProcessInputWindowInsert = Ext.extend(AfProcessInputWindow, {
	title :"添加新工序",
	url :'../test/saveAFP.action',
	onSubmit : function(_form, _action, _values) {

		var _data = _values.data;
		Ext.apply(_data, {
			'id' :_action.result.afpid,
			'afp.id' :_action.result.afpid
		});

		try {

			this.fireEvent("submit", this, new Ext.data.Record(_data));

		} catch (_err) {

			return;
		}

		this.close();
	}
});
/** ****************************************************************************** */
AfProcessInputWindowUpdate = Ext.extend(AfProcessInputWindow, {
	title :"修改名片",
	url :'../test/saveAFP.action',
	load : function(_r) {
		this.form.setValues(_r);
	},
	onSubmit : function(_form, _action, _values) {

		var _data = _values.data;
//		Ext.apply(_data, {
//			'af.afId' :_action.result.afId
//		}, {
//			'af.ad' :_action.result.ad
//		}, {
//			'af.afNo' :_action.result.afNo
//		});
		// alert(_action.result.afNo);

	try {

		this.fireEvent("submit", this, new Ext.data.Record(_data));

	} catch (_err) {

		return;
	}

	this.close();
}
});
/** ****************************************************************************** */
_jsonReader = new Ext.data.JsonReader( {
	root : 'list',
	totalProperty : 'totalCount',
	id : 'id',
	successProperty : '@success'
}, [ {
	name : 'afp.id',
	mapping : 'id',
	type : 'int'
}, {
	name : 'afp.type',
	mapping : 'type'
}, {
	name : 'afp.department',
	mapping : 'department'
}, {
	name : 'afp.name',
	mapping : 'name'
}, {
	name : 'afp.price',
	mapping : 'price'
}, {
	name : 'afp.amount',
	mapping : 'amount'
}, {
	name : 'afp.unit',
	mapping : 'unit'
}, {
	name : 'afp.formula',
	mapping : 'formula'
}, {
	name : 'afp.remark',
	mapping : 'remark'
}]);
AfProcessListGridPanel = Ext.extend(Ext.grid.GridPanel, {
	insertWin :new AfProcessInputWindowInsert(),
	updateWin :new AfProcessInputWindowUpdate(),
	
	store : new Ext.data.Store( {
		url :'../test/AFPjsonExecute.action',
		reader :_jsonReader
	}),
	constructor : function() {		
		this.store.load();
		AfProcessListGridPanel.superclass.constructor.call(this, {
			applyTo :'divAfProcessListGridPanel',
			title :'工序列表',
			// height :500,
			autoHeight :true,
			// autoExpandColumn :5,
			//stripeRows :true,
			anchor :'98%',
			loadMask : true,// 载入遮罩动画
			autoShow : true,
			tbar : [
					{
						text :'查    看',
						handler : function() {
							try {
								Ext.Msg.alert("查看JSON数据", Ext.util.JSON
										.encode(this.getSelected().data));
							} catch (_err) {
								Ext.Msg.alert("系统提示", _err.description);
							}
						},
						scope :this
					},
					'-',
					{
						text :'添    加',
						// iconCls:'addIconCss',
						tooltip :'添加新纪录',
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
			colModel :new Ext.grid.ColumnModel( [ new Ext.grid.RowNumberer(),{
				header :"id",
				width :40,
				dataIndex :'afp.id',
				align :"left"
			},{
				header :"工序类型",
				dataIndex :'afp.type'
			},{
				header :"发生部门",
				dataIndex :'afp.department'
			}, {
				header :"工序名称",
				dataIndex :'afp.name'
			}, {
				header :"单价",
				dataIndex :'afp.price'
			}, {
				header :"数量",
				dataIndex :'afp.amount'
			}, {
				header :"单位",
				dataIndex :'afp.unit'
			}, {
				header :"公式",
				dataIndex :'afp.formula'
			}, {
				header :"备注",
				dataIndex :'afp.remark'
			} ]),
			store :this.store,
			bbar :new Ext.PagingToolbar( {
				store :this.store,
				pageSize :10,
				displayInfo :true,
				displayMsg :'显示第 {0} - {1} 条记录，共 {2}条记录',
				emptyMsg :"没有记录"

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
		//Ext.Msg.alert("grid panel", Ext.util.JSON.encode(_r));

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
			var _sr = this.getSelected();
			Ext.Ajax.request( {
				url :"../test/removeAFP.action",
				params : {
					afpId :_sr.get("afp.id")
				}
			});
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
