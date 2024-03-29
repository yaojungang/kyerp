/** ***************************************************************************** */
org.kyerp.warehouse.PaperOfMaterialStore =new Ext.data.Store({
					autoLoad : {
						baseParams : {
							limit : 20
						}
					},
					url : org.kyerp.warehouse.PaperOfMaterialPanel_STORE_URL,
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
										name : "materialName",
										type : "string"
									}, {
										name : 'specification',
										type : 'string'
									}, {
										name : "materialCategoryId",
										type : "int"
									}, {
										name : "materialCategoryName",
										type : "string"
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
										type : 'float'
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
									}, {
										name : 'paperName',
										type : 'string'
									}, {
										name : 'paperType',
										type : 'string'
									}, {
										name : 'paperHeight',
										type : 'int'
									}, {
										name : 'paperWidth',
										type : 'int'
									}, {
										name : 'paperSize',
										type : 'string'
									}, {
										name : 'paperWeight',
										type : 'float'
									}, {
										name : 'tonnePrice',
										type : 'float'
									}, {
										name : 'squareMetrePrice',
										type : 'float'
									}, {
										name : 'pricePrePage',
										type : 'float'
									}]))
				});
/** ***************************************************************************** */
org.kyerp.warehouse.PaperOfMaterialPanelFormPanel = Ext.extend(
		Ext.form.FormPanel, {
			url : "",
			constructor : function(_cfg) {
				if (_cfg == null)
					_cfg = {};
				Ext.apply(this, _cfg);
				var _readOnly = this["readOnly"] == null
						? false
						: this["readOnly"];
				org.kyerp.warehouse.PaperOfMaterialPanelFormPanel.superclass.constructor
						.call(this, {
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
								fieldLabel : "纸张名称",
								allowBlank : false,
								name : "paperName"
							}, {
								fieldLabel : "纸张克重 ",
								allowBlank : false,
								name : "paperWeight",
								xtype:'numberfield'
							}, {
								fieldLabel : "纸宽(mm)",
								allowBlank : false,
								name : "paperWidth",
								xtype:'numberfield'
							}, {
								fieldLabel : "纸长(mm)",
								allowBlank : false,
								name : "paperHeight",
								xtype:'numberfield'
							}, {
								fieldLabel : "纸张吨价",
								allowBlank : false,
								name : "tonnePrice",
								blankValue : 0,
								xtype:'numberfield'
							}, {
								fieldLabel : "单位",
								xtype : 'treecombobox',
								name : 'unitId',
								hiddenName : 'unitId',
								editable : false,
								mode : 'local',
								displayField : 'name',
								valueField : 'id',
								triggerAction : 'all',
								allowBlank : false,
								treeUrl : org.kyerp.warehouse.UnitPanel_TREE_URL,
								rootText : 'root',
								rootId : '0',
								forceSelection : true,
								rootVisible : false
							}, {
								fieldLabel : "供应商",
								name : 'supplierId',
								hiddenName : 'supplierId',
								xtype : 'treecombobox',
								editable : false,
								mode : 'local',
								displayField : 'name',
								valueField : 'id',
								triggerAction : 'all',
								allowBlank : false,
								treeUrl : org.kyerp.warehouse.Supplier_TREE_URL,
								rootText : 'root',
								rootId : '0',
								forceSelection : true,
								rootVisible : false
							}, {
								fieldLabel : "品牌",
								xtype : 'treecombobox',
								name : 'brandId',
								hiddenName : 'brandId',
								editable : false,
								mode : 'local',
								displayField : 'name',
								valueField : 'id',
								triggerAction : 'all',
								allowBlank : true,
								treeUrl : org.kyerp.warehouse.BrandPanel_TREE_URL,
								rootText : 'root',
								rootId : '0',
								forceSelection : true,
								rootVisible : false
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

				// 设置单位
				this.getForm().findField("unitId").setValue(_r.get('unitName'));
				this.getForm().findField("unitId").hiddenField.value = _r
						.get('unitId');
				// 设置品牌
				this.getForm().findField("brandId").setValue(_r
						.get('brandName'));
				this.getForm().findField("brandId").hiddenField.value = _r
						.get('brandId');
				// 设置供应商
				this.getForm().findField("supplierId").setValue(_r
						.get('supplierName'));
				this.getForm().findField("supplierId").hiddenField.value = _r
						.get('supplierId');
			},
			reset : function() {
				this.getForm().reset();
			},
			onSubmit : function(_form, _action) {
				this.fireEvent("submit", this, _action, this.getValues());
			}
		});
/** ***************************************************************************** */
org.kyerp.warehouse.PaperOfMaterialPanelInfoWindow = Ext.extend(Ext.Window, {
	url : "",
	form : null,
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.form = new org.kyerp.warehouse.PaperOfMaterialPanelFormPanel({
					url : this.url
				});
		org.kyerp.warehouse.PaperOfMaterialPanelInfoWindow.superclass.constructor
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
org.kyerp.warehouse.PaperOfMaterialPanelViewWindow = Ext.extend(Ext.Window, {
			title : "查看",
			iconCls : 'icon-utils-s-view',
			closeAction : 'hide',
			html : '查看'
		});
/** ***************************************************************************** */
org.kyerp.warehouse.PaperOfMaterialPanelInsertWindow = Ext.extend(
		org.kyerp.warehouse.PaperOfMaterialPanelInfoWindow, {
			title : "添 加",
			iconCls : 'icon-utils-s-add',
			url : org.kyerp.warehouse.PaperOfMaterialPanel_SAVE_URL,
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
org.kyerp.warehouse.PaperOfMaterialPanelUpdateWindow = Ext.extend(
		org.kyerp.warehouse.PaperOfMaterialPanelInfoWindow, {
			title : "修 改",
			iconCls : 'icon-utils-s-edit',
			url : org.kyerp.warehouse.PaperOfMaterialPanel_SAVE_URL,
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
org.kyerp.warehouse.PaperOfMaterialPanel = Ext.extend(Ext.grid.GridPanel, {
	viewWin : new org.kyerp.warehouse.PaperOfMaterialPanelViewWindow(),
	insertWin : new org.kyerp.warehouse.PaperOfMaterialPanelInsertWindow(),
	updateWin : new org.kyerp.warehouse.PaperOfMaterialPanelUpdateWindow(),
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this["store"] = org.kyerp.warehouse.PaperOfMaterialStore,
		org.kyerp.warehouse.PaperOfMaterialPanel.superclass.constructor.call(
				this, {
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
							}, '->', '类型：', {
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
						rootVisible : false,
						onSelect : function(node) {
							var store = org.kyerp.warehouse.PaperOfMaterialStore;
							store.setBaseParam("materialCategoryId", node.id);
							store.load();
						}
					}, "-", "搜索：", new Ext.ux.form.SearchField({
								store : this.getStore()
							})],
					enableColumnMove : false,
					colModel : new Ext.grid.ColumnModel([
							new Ext.grid.RowNumberer(), {
								header : "编号",
								dataIndex : "serialNumber",
								menuDisabled : true
							}, {
								header : "名称",
								dataIndex : "materialName",
								width : 250,
								menuDisabled : true
							}, {
								header : "规格",
								dataIndex : "specification",
								menuDisabled : true
							}, {
								header : "单位",
								dataIndex : "unitName",
								menuDisabled : true
							}, {
								header : "价格(元/吨)",
								dataIndex : "tonnePrice",
								menuDisabled : true
							}, {
								header : "价格(元/令)",
								dataIndex : "price",
								menuDisabled : true
							}, {
								dataIndex : 'pricePrePage',
								header : "价格(元/张)",
								menuDisabled : true
							}, {
								header : "物料类别",
								dataIndex : "materialCategoryName",
								menuDisabled : true
							}, {
								header : "品牌",
								dataIndex : "brandName",
								menuDisabled : true
							}, {
								header : "供应商",
								dataIndex : "supplierName",
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
		this.getStore().reload();
	},
	update : function(_r) {
		this.getStore().reload();
	},
	removeItem : function() {
		try {
			var _sr = this.getSelected();
			Ext.Ajax.request({
						url : org.kyerp.warehouse.PaperOfMaterialPanel_DELETE_URL,
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
Ext.extend(org.kyerp.module, {
			init : function() {
				this.body = new org.kyerp.warehouse.PaperOfMaterialPanel({
							border : false,
							bodyBorder : false
						});
				this.main.add(this.body);
				this.main.doLayout();
			}
		});