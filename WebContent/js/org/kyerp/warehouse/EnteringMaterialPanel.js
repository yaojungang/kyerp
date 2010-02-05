/** ***************************************************************************** */
org.kyerp.warehouse.EnteringMaterialPanel_STORE_URL = "warehouse/EnteringMaterial/jsonList.html";
org.kyerp.warehouse.EnteringMaterialPanel_SAVE_URL = "warehouse/EnteringMaterial/jsonSave.html";
org.kyerp.warehouse.EnteringMaterialPanel_DELETE_URL = "warehouse/EnteringMaterial/jsonDelete.html";
/** ***************************************************************************** */
org.kyerp.warehouse.EnteringMaterialFormPanel = Ext.extend(Ext.form.FormPanel,
		{
			url : "",
			warehouseStore : null,
			constructor : function(_cfg) {
				if (_cfg == null)
					_cfg = {};
				Ext.apply(this, _cfg);
				this.warehouseStore = new Ext.data.Store({
							url : org.kyerp.warehouse.WarehousePanel_STORE_URL,
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
												name : "serialNumber",
												type : "string"
											}]))
						});
				var _readOnly = this["readOnly"] == null
						? false
						: this["readOnly"];
				org.kyerp.warehouse.EnteringMaterialFormPanel.superclass.constructor
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
														fieldLabel : "仓库",
														store : this.warehouseStore,
														emptyText : '',
														name : 'warehouseId',
														hiddenName : 'warehouseId',
														editable : false,
														mode : 'remote',
														triggerAction : 'all',
														valueField : 'id',
														displayField : 'name',
														readOnly : true,
														allowBlank : false,
														pageSize : 20
													}), {
												fieldLabel : '编号',
												name : 'serialNumber',
												allowBlank : false
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
org.kyerp.warehouse.EnteringMaterialInfoWindow = Ext.extend(Ext.Window, {
			url : "",
			form : null,
			constructor : function(_cfg) {
				Ext.apply(this, _cfg);
				this.form = new org.kyerp.warehouse.EnteringMaterialFormPanel({
							url : this.url
						});
				org.kyerp.warehouse.EnteringMaterialInfoWindow.superclass.constructor
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
org.kyerp.warehouse.EnteringMaterialInsertWindow = Ext.extend(
		org.kyerp.warehouse.EnteringMaterialInfoWindow, {
			title : "添 加",
			iconCls : 'icon-utils-s-add',
			url : org.kyerp.warehouse.EnteringMaterialPanel_SAVE_URL,
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
org.kyerp.warehouse.EnteringMaterialUpdateWindow = Ext.extend(
		org.kyerp.warehouse.EnteringMaterialInfoWindow, {
			title : "修 改",
			iconCls : 'icon-utils-s-edit',
			url : org.kyerp.warehouse.EnteringMaterialPanel_SAVE_URL,
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
org.kyerp.warehouse.EnteringMaterialPanel = Ext.extend(Ext.grid.GridPanel, {
	insertWin : new org.kyerp.warehouse.EnteringMaterialInsertWindow(),
	updateWin : new org.kyerp.warehouse.EnteringMaterialUpdateWindow(),
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this["store"] = new Ext.data.Store({
					autoLoad : {
						baseParams : {
							limit : 20
						}
					},
					url : org.kyerp.warehouse.EnteringMaterialPanel_STORE_URL,
					reader : new Ext.data.JsonReader({
								totalProperty : "totalProperty",
								root : "rows",
								id : "id"
							}, new Ext.data.Record.create([{
										name : "id",
										type : "int"
									}, {
										name : "serialNumber",
										type : "string"
									}, {
										name : "enteringTime"
									}, {
										name : "warehouseId",
										type : "int"
									}, {
										name : "warehouseName",
										type : "string"
									}, {
										name : "keeperId",
										type : "int"
									}, {
										name : "keeperName",
										type : "string"
									}, {
										name : "operatorId",
										type : "int"
									}, {
										name : "operatorName",
										type : "string"
									}, {
										name : "materialBatchIds",
										type : "string"
									}]))
				});
		org.kyerp.warehouse.EnteringMaterialPanel.superclass.constructor.call(
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
								header : "编号",
								dataIndex : "serialNumber",
								width : 100,
								menuDisabled : true
							}, {
								header : "入库时间",
								dataIndex : "enteringTime",
								renderer : renderDate('Y-m-d'),
								menuDisabled : true
							}, {
								header : "仓库",
								dataIndex : "warehouseName",
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
						url : org.kyerp.warehouse.EnteringMaterialPanel_DELETE_URL,
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

