/** ***************************************************************************** */
org.kyerp.security.SystemModulePanel_STORE_URL = "security/SystemModule/jsonList.html";
/** ***************************************************************************** */
org.kyerp.security.SystemModuleFormPanel = Ext.extend(Ext.form.FormPanel, {
			url : "",
			constructor : function(_cfg) {
				if (_cfg == null)
					_cfg = {};
				Ext.apply(this, _cfg);
				var _readOnly = this["readOnly"] == null
						? false
						: this["readOnly"];
				org.kyerp.security.SystemModuleFormPanel.superclass.constructor
						.call(this, {
									labelWidth : 80,
									labelAlign : 'right',
									defaultType : "textfield",
									defaults : {
										anchor : "90%",
										msgTarget : 'side',
										readOnly : _readOnly
									},
									baseCls : "x-plain",
									items : [{
												fieldLabel : "英文名",
												allowBlank : false,
												name : "name"
											}, {
												fieldLabel : "英文简称",
												allowBlank : false,
												name : "shortName"
											}, {
												fieldLabel : "中文名",
												allowBlank : false,
												name : "chineseName"
											}]
								});
				this.addEvents("submit");
			},
			submit : function(_params) {
				if (_params == null)
					_params = {};
				try {
					if (this.url != "")
						this.getForm().submit({
									url : this.url,
									params : _params,
									success : this.onSubmit,
									waitTitle : "数据传送",
									waitMsg : "数据传送中,请稍候...",
									scope : this
								});

				} catch (_err) {
				}
			},
			getValues : function() {
				if (this.getForm().isValid())
					return new Ext.data.Record(this.getForm().getValues());
				else
					throw Error("表单验证没有通过");
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
/** ***************************************************************************** */
org.kyerp.security.SystemModuleInfoWindow = Ext.extend(Ext.Window, {
			url : "",
			form : null,
			constructor : function(_cfg) {
				Ext.apply(this, _cfg);
				this.form = new org.kyerp.security.SystemModuleFormPanel({
							url : this.url
						});
				org.kyerp.security.SystemModuleInfoWindow.superclass.constructor
						.call(this, {
									plain : true,
									width : 300,
									modal : true,
									items : this.form,
									closeAction : "hide",
									buttons : [{
												text : "确 定",
												handler : this.onSubmitClick,
												scope : this
											}, {
												text : "取 消",
												handler : this.onCancelClick,
												scope : this
											}]
								});

				this.addEvents("submit");
				this.form.on("submit", this.onSubmit, this);
			},
			close : function() {
				this.form.reset();
				this.hide();
			},
			onSubmitClick : function() {
				this.form.submit();
			},
			onCancelClick : function() {

				this.close();
			},
			onSubmit : function(_form, _action, _values) {
				try {
					this.fireEvent("submit", this, _values);
				} catch (_err) {
					return;
				}
				this.close();
			}

		});
/** ***************************************************************************** */
org.kyerp.security.SystemModuleInsertWindow = Ext.extend(
		org.kyerp.security.SystemModuleInfoWindow, {
			title : "添 加",
			iconCls : 'icon-utils-s-add',
			url : 'security/SystemModule/jsonSave.html',
			onSubmit : function(_form, _action, _values) {
				var _data = _values.data;
				Ext.apply(_data, {
							id : _action.result.id
						});
				try {
					this.fireEvent("submit", this, new Ext.data.Record(_data));
				} catch (_err) {
					return;
				}
				this.close();
			}
		});
/** ***************************************************************************** */
org.kyerp.security.SystemModuleUpdateWindow = Ext.extend(
		org.kyerp.security.SystemModuleInfoWindow, {
			title : "修 改",
			iconCls : 'icon-utils-s-edit',
			url : 'security/SystemModule/jsonSave.html',
			pnId : "",
			load : function(_r) {
				this.form.setValues(_r);
				this.pnId = _r.get("id");
			},
			onSubmitClick : function() {
				this.form.submit({
							id : this.pnId
						});
			},
			onSubmit : function(_form, _action, _values) {
				var _data = _values.data;
				Ext.apply(_data, {
							id : this.pnId
						});
				try {
					this.fireEvent("submit", this, new Ext.data.Record(_data));
				} catch (_err) {
					return;
				}
				this.close();
			}
		});
/** ***************************************************************************** */
org.kyerp.security.SystemModulePanel = Ext.extend(Ext.grid.GridPanel, {
	insertWin : new org.kyerp.security.SystemModuleInsertWindow(),
	updateWin : new org.kyerp.security.SystemModuleUpdateWindow(),
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this["store"] = new Ext.data.Store({
					autoLoad : {
						baseParams : {
							limit : 20
						}
					},
					url : org.kyerp.security.SystemModulePanel_STORE_URL,
					reader : new Ext.data.JsonReader({
								totalProperty : "totalProperty",
								root : "rows",
								id : "id"
							}, new Ext.data.Record.create([{
										name : "id",
										type : "int"
									}, {
										name : "name",
										type : "string"
									}, {
										name : "shortName",
										type : "string"
									}, {
										name : "chineseName",
										type : "string"
									}]))
				});
		org.kyerp.security.SystemModulePanel.superclass.constructor.call(this,
				{
					stripeRows : true,
					tbar : [{
								text : "添  加",
								iconCls : 'icon-utils-s-add',
								handler : function() {
									this.insertWin.show();
								},
								scope : this
							}, "-", {
								text : "修  改",
								iconCls : 'icon-utils-s-edit',
								handler : function() {
									this.updateWin.show();
									try {
										this.updateWin.load(this.getSelected());
									} catch (_err) {
										Ext.Msg.alert("修改失败", _err);
										this.updateWin.close();
									}
								},
								scope : this
							}, "-", {
								text : "删  除",
								iconCls : 'icon-utils-s-delete',
								handler : function() {
									Ext.Msg.confirm("系统提示", "你确定删除此记录吗?",
											this.onRemove, this);
								},
								scope : this
							}],
					enableColumnMove : false,
					colModel : new Ext.grid.ColumnModel([{
								header : "ID",
								align : "center",
								width : 50,
								menuDisabled : true
							}, {
								header : "简称",
								dataIndex : "shortName",
								width : 50,
								menuDisabled : true
							}, {
								header : "中文名称",
								dataIndex : "chineseName",
								menuDisabled : true
							}, {
								header : "英文名称",
								dataIndex : "name",
								width : 150,
								menuDisabled : true
							}]),
					selModel : new Ext.grid.RowSelectionModel({
								singleSelect : true,
								listeners : {
									"rowselect" : {
										fn : function(_sel, _index, _r) {

											this.fireEvent("rowselect", _r);
										},
										scope : this
									}
								}
							}),
					bbar : new Ext.PagingToolbar({
								plugins : new Ext.ux.Andrie.pPageSize({
											beforeText : '每页显示',
											afterText : '条'
										}),
								pageSize : 20,
								store : this.store,
								displayInfo : true,
								displayMsg : '显示  {0} - {1} 条记录,共有 {2} 条记录',
								emptyMsg : "没有数据"
							}),
					loadMask : {
						msg : '正在载入数据,请稍等...'
					}
				});
		this.insertWin.on("submit", this.onInsertWinSubmit, this);
		this.updateWin.on("submit", this.onUpdateWinSubmit, this);
		this.addEvents("rowselect");
	},
	getSelected : function(_grid) {
		var _sm = this.getSelectionModel();
		if (_sm.getCount() == 0)
			throw Error("你尚未选定一条记录");
		return _sm.getSelected();
	},
	insert : function(_r) {
		this.getStore().add(_r);
	},
	update : function(_r) {
		try {
			var _sr = this.getSelected();
			var _data = _sr.data;
			for (var _i in _data) {
				_sr.set(_i, _r.get(_i));
			}
			_sr.commit();
		} catch (_err) {
		}
	},
	removeItem : function() {
		try {
			var _sr = this.getSelected();
			Ext.Ajax.request({
						url : "security/SystemModule/jsonDelete.html",
						params : {
							ids : _sr.get("id")
						}
					});
			this.getStore().remove(_sr);

		} catch (_err) {
			Ext.Msg.alert("删除失败", _err);
		}
	},
	onInsertWinSubmit : function(_win, _r) {
		this.insert(_r);
	},
	onUpdateWinSubmit : function(_win, _r) {
		this.update(_r);
	},
	onRemove : function(_btn) {
		if (_btn == "yes")
			this.removeItem();
	},
	onRowSelect : function(_sel, _index, _r) {
		this.fireEvent("rowselect", _r);
	}
});
/** ***************************************************************************** */

Ext.extend(org.kyerp.module,{
    init: function(){
        this.body = new org.kyerp.security.SystemModulePanel({border : false,bodyBorder : false});
        this.main.add(this.body);
        this.main.doLayout();  
    }
});
