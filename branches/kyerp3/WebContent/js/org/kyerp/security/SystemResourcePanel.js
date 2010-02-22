/** ***************************************************************************** */
org.kyerp.security.SystemResourcePanel_STORE_URL = "security/SystemResource/jsonList.html";
/** ***************************************************************************** */
org.kyerp.security.SystemResourceFormPanel = Ext.extend(Ext.form.FormPanel, {
			url : "",
			systemModuleStore : new Ext.data.Store({
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
					}),
			constructor : function(_cfg) {
				if (_cfg == null)
					_cfg = {};
				Ext.apply(this, _cfg);
				var _readOnly = this["readOnly"] == null
						? false
						: this["readOnly"];
				org.kyerp.security.SystemResourceFormPanel.superclass.constructor
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
							items : [new Ext.form.ComboBox({
												fieldLabel : "所属模块",
												store : this.systemModuleStore,
												emptyText : '',
												name : 'systemModuleId',
												hiddenName : 'systemModuleId',
												editable : false,
												mode : 'local',
												triggerAction : 'all',
												valueField : 'id',
												displayField : 'chineseName',
												readOnly : true,
												allowBlank : false
											}),{
											name:'systemModuleName',
											xtype:'hidden'
											}, new Ext.form.ComboBox({
												fieldLabel : "资源类型",
												store : new Ext.data.SimpleStore(
														{
															fields : ['value'],
															expandData : true,
															data : ['ROLE',
																	'URL',
																	'MENU']
														}),
												emptyText : '',
												name : 'type',
												mode : 'local',
												triggerAction : 'all',
												valueField : 'value',
												displayField : 'value',
												eidtable : false,
												readOnly : true,
												allowBlank : false
											}), {
										fieldLabel : "资源名称",
										allowBlank : false,
										name : "name"
									}, {
										fieldLabel : "资源内容",
										allowBlank : false,
										name : "content"
									}, {
										fieldLabel : "备注",
										allowBlank : false,
										name : "remark"
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
									failure : function() {
										Ext.Msg.alert('失败', '提交时发生错误，请检查输入后重试！');
									},
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
org.kyerp.security.SystemResourceInfoWindow = Ext.extend(Ext.Window, {
			url : "",
			form : null,
			constructor : function(_cfg) {
				Ext.apply(this, _cfg);
				this.form = new org.kyerp.security.SystemResourceFormPanel({
							url : this.url
						});
				org.kyerp.security.SystemResourceInfoWindow.superclass.constructor
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
				this.form.on("beforerender", this.onFormRenrender, this);
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
			onFormRenrender : function() {
				// 在form渲染前载入 系统模块信息
				this.form.systemModuleStore.load();
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
org.kyerp.security.SystemResourceInsertWindow = Ext.extend(
		org.kyerp.security.SystemResourceInfoWindow, {
			title : "添 加",
			iconCls : 'icon-utils-s-add',
			url : 'security/SystemResource/jsonSave.html',
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
org.kyerp.security.SystemResourceUpdateWindow = Ext.extend(
		org.kyerp.security.SystemResourceInfoWindow, {
			title : "修 改",
			iconCls : 'icon-utils-s-edit',
			url : 'security/SystemResource/jsonSave.html',
			pnId : "",
			load : function(_r) {
				this.form.setValues(_r);
				this.pnId = _r.get("id");
				// 为 所属模块 设置初始值
// this.form.get(0).setValue(_r.get("systemModule").id);
// this.form.get(0).nextSibling.value = _r.get("systemModule").chineseName;
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
org.kyerp.security.SystemResourcePanel = Ext.extend(Ext.grid.GridPanel, {
	insertWin : new org.kyerp.security.SystemResourceInsertWindow(),
	updateWin : new org.kyerp.security.SystemResourceUpdateWindow(),
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this["store"] = new Ext.data.Store({
					autoLoad : {
						baseParams : {
							limit : 20
						}
					},
					url : org.kyerp.security.SystemResourcePanel_STORE_URL,
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
										name : "content",
										type : "string"
									}, {
										name : "type",
										type : "string"
									}, {
										name : "remark",
										type : "string"
									}, {
										name : "systemModuleId",
										type : 'int'
									}, {
										name : "systemModuleName",
										type : 'string'
									}]))
				});
		org.kyerp.security.SystemResourcePanel.superclass.constructor.call(
				this, {
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
										Ext.Msg.alert("系统提示", _err);
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
								header : "资源名称",
								dataIndex : "name",
								width : 100,
								menuDisabled : true
							}, {
								header : "资源内容",
								dataIndex : "content",
								width : 150,
								menuDisabled : true
							}, {
								header : "所属模块",
								dataIndex : "systemModuleName",
								width : 100,
								menuDisabled : true
							}, {
								header : "类型",
								dataIndex : "type",
								width : 50,
								menuDisabled : true
							}, {
								header : "备注",
								dataIndex : "remark",
								width : 250,
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
											beforeText : '每页',
											afterText : '条'
										}),
								pageSize : 20,
								store : this.store,
								displayInfo : true,
								displayMsg : '显示  {0} - {1} 条记录,共有 {2} 条记录',
								emptyMsg : "没有数据",
								items : ['-', '搜索',
										new Ext.ux.form.SearchField({
													store : this.store,
													paramName : 'name'
												})]
							}),
					loadMask : {
						msg : '正在载入数据,请稍等...'
					}
				});
		this.insertWin.on("submit", this.onInsertWinSubmit, this);
		this.updateWin.on("submit", this.onUpdateWinSubmit, this);
		this.on("show", function() {
					this.updateWin.form.systemModuleStore.load();
				}, this);
		this.addEvents("rowselect");
	},
	getSelected : function(_grid) {
		var _sm = this.getSelectionModel();
		if (_sm.getCount() == 0)
			throw Error("请先选择一条记录");
		return _sm.getSelected();
	},
	insert : function(_r) {
		this.getStore().add(_r);
	},
	update : function(_r) {
		try {
			//_r.data.
			var _sr = this.getSelected();
			var _data = _sr.data;
			//alert(Ext.util.JSON.encode(_r.data)+"\n"+Ext.util.JSON.encode(_data)+"\n");
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
						url : "security/SystemResource/jsonDelete.html",
						params : {
							ids : _sr.get("id")
						}
					});
			this.getStore().remove(_sr);

		} catch (_err) {
			Ext.Msg.alert("系统提示", _err);
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
        this.body = new org.kyerp.security.SystemResourcePanel({border : false,bodyBorder : false});
        this.main.add(this.body);
        this.main.doLayout();  
    }
});