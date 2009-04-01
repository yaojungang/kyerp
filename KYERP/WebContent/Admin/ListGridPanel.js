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
				fieldLabel :"课程编号",
				name :'classID'
			}, {
				fieldLabel :"课程批次",
				dataIndex :'order',
				name :'order'
			}, {
				fieldLabel :"课程名称",
				name :'className'
			}, {
				fieldLabel :"开始时间",
				name :'startTime',
				format:'Y-m-d'
			}, {
				fieldLabel :"结束时间",
				name :'endTime',
				format:'Y-m-d'
			}, {
				fieldLabel :"培训费",
				name :'fee1'
			}, {
				fieldLabel :"会员单位7折优惠价格",
				name :'fee2'
			}, {
				fieldLabel :"非会员单位5人以上8折优惠价格",
				name :'fee3'
			}, {
				fieldLabel :"非会员单位10人以上7折优惠价格",
				name :'fee4'
			}, {
				fieldLabel :"课程状态",
				name :'class_is_open'
			} ]
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
	alert(Ext.util.JSON.encode(this.form.getValues().data));
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
MPInputWindowInsert = Ext.extend(MPInputWindow, {
	title :"添加名片",
	url :'Course.php?cmd=Add',
	onSubmit : function(_form, _action, _values) {

		var _data = _values.data;
		Ext.apply(_data, {
			'id' :_action.result.cid,
			'cid' :_action.result.cid
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
MPInputWindowUpdate = Ext.extend(MPInputWindow, {
	title :"修改名片",
	url :'Course.php?cmd=Update',
	load : function(_r) {
		this.form.setValues(_r);
	},
	onSubmit : function(_form, _action, _values) {

		var _data = _values.data;
		Ext.apply(_data, {
			'af.afId' :_action.result.afId
		}, {
			'af.ad' :_action.result.ad
		}, {
			'af.afNo' :_action.result.afNo
		});
		//alert(_action.result.afNo);

		try {

			this.fireEvent("submit", this, new Ext.data.Record(_data));

		} catch (_err) {

			return;
		}

		this.close();
	}
});
/** ****************************************************************************** */

MPListGridPanel = Ext.extend(Ext.grid.GridPanel, {
	insertWin :new MPInputWindowInsert(),
	updateWin :new MPInputWindowUpdate(),
	constructor : function() {
		var store_Course = new Ext.data.Store( {
			url :'Course.php?cmd=List',
			//autoLoad :true,
			reader:new Ext.data.JsonReader({
				root:"rows",totalProperty :'totalCount'
				} , Ext.data.Record.create(["cid", "classID", "order", "className", "startTime", "endTime", "fee1", "fee2", "fee3", "fee4", "class_is_open"]))
		});
		store_Course.load();
		MPListGridPanel.superclass.constructor.call(this, {
			applyTo :'divMPListGridPanel',
			title :'名片任务列表',
			// height :500,
			autoHeight :true,
			// autoExpandColumn :5,
			stripeRows :true,
			anchor :'98%',
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
						//iconCls:'addIconCss',
						tooltip:'添加新纪录',
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
				header :"课程编号",
				width :40,
				dataIndex :'classID',
				name :'classID',
				align :"left"
			}, {
				header :"课程批次",
				dataIndex :'order',
				width :20,
				name :'order',
				align :"left"
			}, {
				header :"课程名称",
				dataIndex :'className',
				width :200,
				name :'className',
				align :"left"
			}, {
				header :"开始时间",
				dataIndex :'startTime',
				width :80,
				name :'startTime',
				format:'Y-m-d',
				align :"left"
			}, {
				header :"结束时间",
				dataIndex :'endTime',
				width :80,
				name :'endTime',
				format:'Y-m-d',
				align :"left"
			}, {
				header :"培训费",
				dataIndex :'fee1',
				width :80,
				name :'fee1',
				align :"left"
			}, {
				header :"会员单位7折优惠价格",
				dataIndex :'fee2',
				width :80,
				name :'fee2',
				align :"left"
			}, {
				header :"非会员单位5人以上8折优惠价格",
				dataIndex :'fee3',
				width :80,
				name :'fee3',
				align :"left"
			}, {
				header :"非会员单位10人以上7折优惠价格",
				dataIndex :'fee4',
				width :80,
				name :'fee4',
				align :"left"
			}, {
				header :"课程状态",
				dataIndex :'class_is_open',
				width :80,
				name :'class_is_open',
				align :"left"
			}]),
			store :store_Course,
			bbar :new Ext.PagingToolbar( {
				store :store_Course,
				pageSize :10,
				displayInfo: true,
				displayMsg: '显示第 {0} - {1} 条记录，共 {2}条记录',
				emptyMsg: "没有记录"

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
		Ext.Msg.alert("grid panel", Ext.util.JSON.encode(_r));

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
			Ext.Ajax.request( {
							url :"Course.php?cmd=Remove",
							params : {
								cid :_sr.get("cid")
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
