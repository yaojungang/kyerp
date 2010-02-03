/** ***************************************************************************** */
org.kyerp.security.RolePanel_STORE_URL = "security/Role/jsonList.html";
/** ***************************************************************************** */
org.kyerp.security.RoleFormPanel = Ext.extend(Ext.form.FormPanel, {
			url : "",
			userStore : new Ext.data.Store({
						url : 'security/User/jsonList.html',
						reader : new Ext.data.JsonReader({
									totalProperty : "totalProperty",
									root : "rows",
									id : "id"
								}, new Ext.data.Record.create([{
											name : "id",
											type : "int"
										}, {
											name : "userName",
											type : "string"
										}]))
					}),
			departmentStore : new Ext.data.Store({
						url : org.kyerp.org.DepartmentPanel_STORE_URL ,
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
										}]))
					}),
			systemResourceStore : new Ext.data.Store({
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
					}),
			constructor : function(_cfg) {
				if (_cfg == null)
					_cfg = {};
				Ext.apply(this, _cfg);
				var _readOnly = this["readOnly"] == null
						? false
						: this["readOnly"];
				org.kyerp.security.RoleFormPanel.superclass.constructor.call(
						this, {
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
												fieldLabel : "所属部门",
												store : this.departmentStore,
												emptyText : '',
												name : 'departmentId',
												hiddenName : 'departmentId',
												editable : false,
												mode : 'remote',
												triggerAction : 'all',
												valueField : 'id',
												displayField : 'name',
												readOnly : true,
												allowBlank : false
											}), {
										fieldLabel : "名称",
										allowBlank : false,
										name : "name"
									}, {
										name : 'userIds',
										xtype : 'lovcombo',
										hiddenName : 'userIds',
										store : this.userStore,
										hideOnSelect : false,
										triggerAction : 'all',
										valueField : 'id',
										displayField : 'userName',
										mode : 'local',
										allowBlank : false,
										emptyText : '请选择',
										fieldLabel : '关联用户',
										beforeBlur : function() {
										}
									}, {
										name : 'systemResourceIds',
										xtype : 'lovcombo',
										hiddenName : 'systemResourceIds',
										store : this.systemResourceStore,
										hideOnSelect : false,
										triggerAction : 'all',
										valueField : 'id',
										displayField : 'name',
										mode : 'local',
										allowBlank : false,
										emptyText : '请选择',
										fieldLabel : '系统资源',
										beforeBlur : function() {
										}
									}, {
										fieldLabel : "备注",
										allowBlank : false,
										xtype : 'textarea',
										name : "remark"
									}, {
										name : 'departmentName',
										xtype : 'hidden'
									}, {
										name : 'userNames',
										xtype : 'hidden'
									}, {
										name : 'systemResourceNames',
										xtype : 'hidden'
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
org.kyerp.security.RoleInfoWindow = Ext.extend(Ext.Window, {
			url : "",
			form : null,
			constructor : function(_cfg) {
				Ext.apply(this, _cfg);
				this.form = new org.kyerp.security.RoleFormPanel({
							url : this.url
						});
				org.kyerp.security.RoleInfoWindow.superclass.constructor.call(
						this, {
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
org.kyerp.security.RoleInsertWindow = Ext.extend(
		org.kyerp.security.RoleInfoWindow, {
			title : "添 加",
			iconCls : 'icon-utils-s-add',
			url : 'security/Role/jsonSave.html',
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
org.kyerp.security.RoleUpdateWindow = Ext.extend(
		org.kyerp.security.RoleInfoWindow, {
			title : "修 改",
			iconCls : 'icon-utils-s-edit',
			url : 'security/Role/jsonSave.html',
			pnId : "",
			load : function(_r) {
				this.form.setValues(_r);
				this.pnId = _r.get("id");
				// 设置 所属部门
				// this.form.get(0).setValue(_r.get("departmentId"));
				// this.form.get(2).setValue([1, 2]);
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
org.kyerp.security.RolePanel = Ext.extend(Ext.grid.GridPanel, {
	insertWin : new org.kyerp.security.RoleInsertWindow(),
	updateWin : new org.kyerp.security.RoleUpdateWindow(),
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this["store"] = new Ext.data.Store({
					autoLoad : {
						baseParams : {
							limit : 20
						}
					},
					url : org.kyerp.security.RolePanel_STORE_URL,
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
										name : "remark",
										type : "string"
									}, {
										name : 'departmentId',
										type : 'int'
									}, {
										name : 'departmentName',
										type : 'string'
									}, {
										name : 'userIds',
										type : 'string'
									}, {
										name : 'userNames',
										type : 'string'
									}, {
										name : 'systemResourceIds',
										type : 'string'
									}, {
										name : 'systemResourceNames',
										type : 'string'
									}]))
				});
		org.kyerp.security.RolePanel.superclass.constructor.call(this, {
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
								header : "名称",
								dataIndex : "name",
								width : 80,
								menuDisabled : true
							}, {
								header : "部门",
								dataIndex : "departmentName",
								width : 100,
								menuDisabled : true
							}, {
								header : "用户",
								dataIndex : "userNames",
								width : 180,
								menuDisabled : true
							}, {
								header : "系统资源",
								dataIndex : "systemResourceNames",
								width : 280,
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
					// sm : new Ext.grid.CheckboxSelectionModel();
					bbar : new Ext.PagingToolbar({
								plugins : new Ext.ux.Andrie.pPageSize({
											beforeText : '每页显示',
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
					this.updateWin.form.departmentStore.load();
					this.updateWin.form.userStore.load();
					this.updateWin.form.systemResourceStore.load();
				}, this);
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
						url : "security/Role/jsonDelete.html",
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
