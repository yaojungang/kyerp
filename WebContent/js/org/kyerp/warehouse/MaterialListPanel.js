/** ***************************************************************************** */
org.kyerp.warehouse.MaterialListPanel_STORE_URL = "warehouse/Material/jsonList.html";
org.kyerp.warehouse.MaterialListPanel_SAVE_URL = "warehouse/Material/jsonSave.html";
org.kyerp.warehouse.MaterialListPanel_DELETE_URL = "warehouse/Material/jsonDelete.html";
/** ***************************************************************************** */
org.kyerp.warehouse.MaterialListFormPanel = Ext.extend(Ext.form.FormPanel, {
	url : "",
	brandStore : null,
	supplierStore : null,
	unitStore : null,
	warehouseStore : null,
	constructor : function(_cfg) {
		if (_cfg == null)
			_cfg = {};
		Ext.apply(this, _cfg);
		this.brandStore = new Ext.data.Store({
					url : org.kyerp.warehouse.BrandPanel_STORE_URL,
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
				});
		this.supplierStore = new Ext.data.Store({
					url : org.kyerp.warehouse.SupplierPanel_STORE_URL,
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
				});
		this.unitStore = new Ext.data.Store({
					url : org.kyerp.warehouse.UnitPanel_STORE_URL,
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
										name : "nameSpell",
										type : "string"
									}, {
										name : "serialNumber",
										type : 'string'
									}]))
				});
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
		var _readOnly = this["readOnly"] == null ? false : this["readOnly"];
		org.kyerp.warehouse.MaterialListFormPanel.superclass.constructor.call(
				this, {
					labelWidth : 80,
					baseCls : 'x-plain',
					labelAlign : 'right',
					defaultType : "textfield",
					defaults : {
						anchor : "95%",
						msgTarget : 'side',
						readOnly : _readOnly
					},
					items : [{
						xtype : 'treecombobox',
						name : 'materialCategoryId',
						hiddenName : 'materialCategoryId',
						fieldLabel : '物料分类',
						editable : false,
						mode : 'local',
						displayField : 'name',
						valueField : 'id',
						triggerAction : 'all',
						allowBlank : false,
						treeUrl : org.kyerp.warehouse.MaterialCategoryPanel_TREE_URL,
						rootText : 'root',
						rootId : '0',
						forceSelection : true,
						rootVisible : false
					}, {
						fieldLabel : "编号",
						allowBlank : false,
						name : "serialNumber"
					}, {
						fieldLabel : "名称",
						allowBlank : false,
						name : "name"
					}, {
						fieldLabel : "规格",
						name : "specification"
					}, new Ext.form.ComboBox({
								fieldLabel : "默认单位",
								store : this.unitStore,
								emptyText : '',
								name : 'unitId',
								hiddenName : 'unitId',
								editable : false,
								mode : 'remote',
								triggerAction : 'all',
								valueField : 'id',
								displayField : 'name',
								readOnly : true,
								allowBlank : false,
								pageSize : 5
							}), new Ext.form.ComboBox({
								fieldLabel : "品牌",
								store : this.brandStore,
								emptyText : '',
								name : 'brandId',
								hiddenName : 'brandId',
								editable : false,
								mode : 'remote',
								triggerAction : 'all',
								valueField : 'id',
								displayField : 'name',
								readOnly : true,
								allowBlank : false,
								pageSize : 20
							}), new Ext.form.ComboBox({
								fieldLabel : "默认供应商",
								store : this.supplierStore,
								emptyText : '',
								name : 'supplierId',
								hiddenName : 'supplierId',
								editable : false,
								mode : 'remote',
								triggerAction : 'all',
								valueField : 'id',
								displayField : 'name',
								readOnly : true,
								allowBlank : false,
								pageSize : 20
							}), new Ext.form.ComboBox({
								fieldLabel : "默认仓库",
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
							})]
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
org.kyerp.warehouse.MaterialListInfoWindow = Ext.extend(Ext.Window, {
			url : "",
			form : null,
			constructor : function(_cfg) {
				Ext.apply(this, _cfg);
				this.form = new org.kyerp.warehouse.MaterialListFormPanel({
							url : this.url
						});
				org.kyerp.warehouse.MaterialListInfoWindow.superclass.constructor
						.call(this, {
									plain : true,
									width : 500,
									minWidth : 500,
									minHeight : 300,
									layout : 'fit',
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
org.kyerp.warehouse.MaterialListViewWindow = Ext.extend(Ext.Window, {
			title : "查看",
			iconCls : 'icon-utils-s-view',
			closeAction : 'hide',
			html : '查看'
		});
/** ***************************************************************************** */
org.kyerp.warehouse.MaterialListInsertWindow = Ext.extend(
		org.kyerp.warehouse.MaterialListInfoWindow, {
			title : "添 加",
			iconCls : 'icon-utils-s-add',
			url : org.kyerp.warehouse.MaterialListPanel_SAVE_URL,
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
org.kyerp.warehouse.MaterialListUpdateWindow = Ext.extend(
		org.kyerp.warehouse.MaterialListInfoWindow, {
			title : "修 改",
			iconCls : 'icon-utils-s-edit',
			url : org.kyerp.warehouse.MaterialListPanel_SAVE_URL,
			pnId : "",
			load : function(_r) {
				this.form.setValues(_r);
				this.pnId = _r.get("id");
				this.form.get(0).setValue(new Ext.tree.TreeNode({
							id : _r.get("materialCategoryId"),
							text : _r.get("materialCategoryName")
						}));
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
org.kyerp.warehouse.MaterialListPanel = Ext.extend(Ext.grid.GridPanel, {
	viewWin : new org.kyerp.warehouse.MaterialListViewWindow(),
	insertWin : new org.kyerp.warehouse.MaterialListInsertWindow(),
	updateWin : new org.kyerp.warehouse.MaterialListUpdateWindow(),
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this["store"] = new Ext.data.Store({
					autoLoad : {
						baseParams : {
							limit : 20
						}
					},
					url : org.kyerp.warehouse.MaterialListPanel_STORE_URL,
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
										name : "name",
										type : "string"
									}, {
										name : "amount",
										type : "int"
									}, {
										name : "materialCategoryId",
										type : "int"
									}, {
										name : "materialCategoryName",
										type : "string"
									}, {
										name : 'specification',
										type : 'string'
									}, {
										name : 'brandId',
										type : 'int'
									}, {
										name : 'brandName',
										type : 'string'
									}, {
										name : 'unitId',
										type : 'int'
									}, {
										name : 'unitName',
										type : 'string'
									}, {
										name : 'price',
										type : 'string'
									}, {
										name : 'supplierId',
										type : 'int'
									}, {
										name : 'supplierName',
										type : 'string'
									}, {
										name : 'warehouseId',
										type : 'int'
									}, {
										name : 'warehouseName',
										type : 'string'
									}]))
				});
		org.kyerp.warehouse.MaterialListPanel.superclass.constructor.call(this,
				{
					stripeRows : true,
					tbar : [{
								text : "查  看",
								iconCls : 'icon-utils-s-view',
								handler : function() {
									this.viewWin.show();
								},
								scope : this
							}, {
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
					colModel : new Ext.grid.ColumnModel([
							new Ext.grid.RowNumberer(), {
								header : "ID",
								dataIndex : "id",
								align : "center",
								width : 50,
								menuDisabled : true
							}, {
								header : "编号",
								dataIndex : "serialNumber",
								menuDisabled : true
							}, {
								header : "名称",
								dataIndex : "name",
								width : 150,
								menuDisabled : true
							}, {
								header : "规格",
								dataIndex : "specification",
								width : 100,
								menuDisabled : true
							}, {
								header : "单位",
								dataIndex : "unitName",
								width : 40,
								menuDisabled : true
							}, {
								header : "价格",
								dataIndex : "price",
								width : 60,
								menuDisabled : true
							}, {
								header : "库存数量",
								dataIndex : "amount",
								width : 60,
								menuDisabled : true
							}, {
								header : "物料类别",
								dataIndex : "materialCategoryName",
								width : 80,
								menuDisabled : true
							}, {
								header : "品牌",
								dataIndex : "brandName",
								width : 60,
								menuDisabled : true
							}, {
								header : "供应商",
								dataIndex : "supplierName",
								width : 80,
								menuDisabled : true
							}, {
								header : "默认仓库",
								dataIndex : "warehouseName",
								width : 80,
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
		this.on("show", function() {
					this.updateWin.form.brandStore.load();
					this.updateWin.form.supplierStore.load();
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
						url : org.kyerp.warehouse.MaterialListPanel_DELETE_URL,
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
