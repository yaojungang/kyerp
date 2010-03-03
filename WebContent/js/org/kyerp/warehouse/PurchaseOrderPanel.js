org.kyerp.warehouse.PurchaseOrderPanel_STORE_URL = "warehouse/PurchaseOrder/jsonList.html";
org.kyerp.warehouse.PurchaseOrderPanel_SAVE_URL = "warehouse/PurchaseOrder/jsonSave.html";
org.kyerp.warehouse.PurchaseOrderPanel_DELETE_URL = "warehouse/PurchaseOrder/jsonDelete.html";
/** ***************************************************************************** */
org.kyerp.warehouse.PurchaseOrderItems = Ext.extend(Ext.grid.EditorGridPanel, {
	inserted : [],
	materialCombo : null,
	conn : new Ext.data.Connection(),
	constructor : function(_cfg) {
		if (_cfg == null)
			_cfg = {};
		Ext.apply(this, _cfg);
		this.materialCombo = new Ext.form.ComboBox({
					hiddenName : 'materialId',
					typeAhead : true,
					lazyRender : true,
					pageSize : 20,
					listWidth : 360,
					valueField : 'id',
					displayField : 'name',
					mode : 'remote',
					selectOnFocus : true,
					allowBlank : false,
					emptyText : '请选择',
					triggerAction : 'all',
					store : new Ext.data.Store({
								autoLoad : true,
								autoLoad : {
									baseParams : {
										limit : 20
									}
								},
								url : "warehouse/Material/jsonList.html",
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
							}),
					listeners : {
						select : function(comboBox) {
							var value = comboBox.getValue();
							var _rs = this.getSelectionModel().getSelected();
							_data = comboBox.store.getById(value).data;
							// alert(Ext.util.JSON.encode(_data));
							_rs.set('unitName', _data.unitName);
							_rs.set('price', _data.price);
						},
						scope : this
					}
				});
		org.kyerp.warehouse.PurchaseOrderItems.superclass.constructor.call(
				this, {
					store : new Ext.data.Store({
								reader : new Ext.data.JsonReader({},
										new Ext.data.Record.create([{
													name : "id"
												}, {
													name : "billCount"
												}, {
													name : "materialId"
												}, {
													name : "materialName"
												}, {
													name : "unitId"
												}, {
													name : "unitName"
												}, {
													name : "price"
												}, {
													name : "remark"
												}])),
								listeners : {}
							}),
					autoScroll : true,
					sm : new Ext.grid.RowSelectionModel({
								singleSelect : true
							}),
					tbar : [{
								text : "保存",
								handler : this.onSaveButtonClick,
								// hidden:true,
								scope : this
							}, "-", {
								text : "添加",
								handler : this.onInsertButtonClick,
								scope : this
							}, "-", {
								text : "删除",
								handler : this.onRemoveButtonClick,
								scope : this
							}],
					columns : [new Ext.grid.RowNumberer(), {
								header : '品名型号',
								width : 150,
								dataIndex : "materialId",
								renderer : Ext.ux.renderer
										.Combo(this.materialCombo),
								editor : this.materialCombo
							}, {
								header : '单位',
								width : 40,
								dataIndex : "unitName"
							}, {
								header : "数量",
								width : 70,
								dataIndex : "billCount",
								editor : new Ext.form.NumberField({
											allowBlank : false
										})
							}, {
								header : "单价",
								width : 70,
								dataIndex : "price",
								editor : new Ext.form.NumberField({
											allowBlank : false,
											minValue : 0
										})
							}, {
								header : "金额",
								width : 80,
								dataIndex : "billCost"
							}, {
								header : '备注',
								dataIndex : "remark",
								editor : new Ext.form.TextArea()
							}]
				});
	},

	onSaveButtonClick : function() {
		var _m = this.getStore().modified;
		var _temp = [];
		for (var _i = 0; _i < _m.length; _i++) {
			if (_m[_i].get("id") == "")
				continue;
			var _data = {};
			var _j = "";
			for (_j in _m[_i].modified)
				_data[_j] = _m[_i].get(_j);
			_temp.push(Ext.apply(_data, {
						id : _m[_i].get("id")
					}));

		}
		for (var _i = 0; _i < this.inserted.length; _i++)
			_temp.push(this.inserted[_i].data);
		alert("Data: " + Ext.util.JSON.encode(_temp));
		this.getStore().commitChanges();

	},
	onInsertButtonClick : function() {
		var _rs = new Ext.data.Record({
					id : '',
					materialId : '',
					unitId : '',
					unitName : '',
					price : 0,
					amount : 1,
					warehouseId : '',
					money : 0,
					batchNumber : '',
					remark : ''
				});
		this.inserted.push(_rs);
		this.getStore().add(_rs);
		// 选中加入的行
		this.getSelectionModel().selectRow(this.getStore().getCount() - 1);
		this.fireEvent('rowclick', this, this.getStore().getCount() - 1)

		this.startEditing(this.getStore().getCount() - 1, 0);
	},
	onSaveInsertData : function(_conn, _response) {
		var _xml = _response.responseXML;
		var _root = _xml.documentElement;
		for (var _i = 0; _i < _root.childNodes.length; _i++) {
			this.inserted[_i].set("id", _root.childNodes[_i].text);
		}
		this.inserted = [];
	},
	onRemoveButtonClick : function() {
		var _sm = this.getSelectionModel();
		try {
			if (_sm.getCount() == 0)
				throw Error("尚未选定一条记录");
			Ext.Msg
					.confirm("系统询问", "你是否确认删除此条记录?", this.onRemoveQuestion,
							this);
		} catch (_err) {
			Ext.Msg.alert("系统提示", _err);
		}
	},
	onRemoveQuestion : function(_btn) {
		if (_btn == "yes") {
			var _rs = this.getSelectionModel().getSelected();
			this.getStore().remove(_rs);
			if (_rs.get("id") != "") {
				this.conn.un("requestcomplete", this.onSaveInsertData, this);
				this.conn.request({
							url : "warehouse/MaterialBatch/jsonDelete.html",
							params : {
								ids : _rs.get("id")
							}
						});
			} else {
				this.inserted.remove(_rs);
				this.getStore().modified.remove(_rs);
			}
		}
	}
});
org.kyerp.warehouse.PurchaseOrderFormPanel = Ext.extend(Ext.form.FormPanel, {
	detailsGrid : null,
	constructor : function(_cfg) {
		if (_cfg == null)
			_cfg = {};
		Ext.apply(this, _cfg);
		this.detailsGrid = new org.kyerp.warehouse.PurchaseOrderItems();
		org.kyerp.warehouse.PurchaseOrderFormPanel.superclass.constructor.call(
				this, {
					layout : 'border',
					border : false,
					defaults : {
						border : false,
						baseCls : "x-plain"
					},
					items : [{
						region : 'north',
						autoHeight : true,
						items : [{
							xtype : 'panel',
							layout : 'column',
							baseCls : "x-plain",
							bodyStyle : 'padding:10px 0 0 10px',
							items : [{
								columnWidth : .3,
								xtype : 'panel',
								layout : 'form',
								baseCls : "x-plain",
								labelWidth : 60,
								labelAlign : 'right',
								defaultType : "textfield",
								defaults : {
									anchor : '-20px',
									labelAlign : 'left'
								},
								items : [{
											fieldLabel : '单据编号',
											name : 'serialNumber',
											readOnly : true
										}, {
											fieldLabel : '单据日期',
											xtype : 'datefield',
											format : 'Y-m-d',
											emptyText : new Date()
													.format('Y-m-d')
										}, {
											fieldLabel : '到货时间',
											name : 'enteringTime',
											xtype : 'datefield',
											format : 'Y-m-d',
											emptyText : new Date()
													.format('Y-m-d')
										}]
							}, {
								columnWidth : .25,
								xtype : 'panel',
								layout : 'form',
								labelWidth : 60,
								baseCls : "x-plain",
								labelAlign : 'right',
								defaultType : "textfield",
								defaults : {
									anchor : '-20px'
								},
								items : [{
											fieldLabel : '申请类型'
										}, {
											fieldLabel : '申请部门'
										}, {
											fieldLabel : '负责人'
										}]
							}, {
								columnWidth : .45,
								xtype : 'panel',
								layout : 'form',
								labelWidth : 60,
								baseCls : "x-plain",
								labelAlign : 'right',
								defaultType : "textfield",
								defaults : {
									anchor : '-20px'
								},
								items : [{
									xtype : 'panel',
									layout : 'column',
									baseCls : "x-plain",
									border : false,
									defaults : {
										border : false
									},
									items : [{
										columnWidth : 1,
										baseCls : "x-plain",
										items : {
											layout : 'form',
											border : false,
											baseCls : "x-plain",
											items : {
												fieldLabel : '供应商',
												xtype : 'combo',
												anchor : '-3px',
												msgTarget : 'qtip',
												store : new Ext.data.Store({
													autoLoad : {
														baseParams : {
															limit : 20
														}
													},
													url : "warehouse/Supplier/jsonList.html",
													reader : new Ext.data.JsonReader(
															{
																totalProperty : "totalProperty",
																root : "rows",
																id : "id"
															},
															new Ext.data.Record.create([
																	{
																		name : "id",
																		type : "int"
																	}, {
																		name : "name",
																		type : "string"
																	}, {
																		name : "nameSpell",
																		type : "string"
																	}, {
																		name : "fullName",
																		type : "string"
																	}, {
																		name : "qualified",
																		type : 'boolean'
																	}]))
												}),
												hiddenName : 'supplierId',
												pageSize : 20,
												valueField : 'id',
												displayField : 'name',
												mode : 'local',
												allowBlank : false,
												emptyText : '请选择',
												triggerAction : 'all'
											}
										}
									}, {
										xtype : 'panel',
										width : 25,
										baseCls : "x-plain",
										items : {
											xtype : 'button',
											id : 'btn-select-provider',
											cls : 'x-btn-icon',
											icon : 'images/ext-extend/icons/query.gif',
											handler : function() {
											}
										}
									}]
								}, {
									fieldLabel : '附加说明',
									name : 'remark',
									xtype : 'textarea',
									height : 50
								}]
							}]
						}]
					}, {
						region : 'south',
						autoHeight : true,
						items : [{
									xtype : 'panel',
									layout : 'column',
									baseCls : "x-plain",
									defaults : {
										bodyStyle : 'padding:0 10px 10px 10px',
										height : 30
									},
									items : [{
												xtype : 'panel',
												layout : 'form',
												baseCls : "x-plain",
												labelAlign : 'right',
												labelWidth : 60,
												defaultType : "label",
												items : [{
															fieldLabel : '编制人'
														}]
											}, {
												xtype : 'panel',
												layout : 'form',
												baseCls : "x-plain",
												labelAlign : 'right',
												labelWidth : 80,
												defaultType : "label",
												items : [{
															fieldLabel : '编制时间',
															id : 'createTime'
														}]
											}, {
												xtype : 'panel',
												layout : 'form',
												labelWidth : 40,
												baseCls : "x-plain",
												labelAlign : 'right',
												defaultType : "label",
												items : [{
															fieldLabel : '审核'
														}]
											}, {
												xtype : 'panel',
												layout : 'form',
												labelWidth : 80,
												baseCls : "x-plain",
												labelAlign : 'right',
												defaultType : "label",
												items : [{
															fieldLabel : '合计数量'
														}]
											}, {
												xtype : 'panel',
												layout : 'form',
												labelWidth : 80,
												baseCls : "x-plain",
												labelAlign : 'right',
												defaultType : "label",
												items : [{
															fieldLabel : '合计金额'
														}]
											}]
								}]
					}, {
						region : 'center',
						layout : 'fit',
						bodyStyle : 'padding:10px',
						items : this.detailsGrid
					}]
				});
		this.addEvents("submit");
	},
	submit : function(_params) {
		var _opeItemStr = [];
		var _s = this.detailsGrid.getStore();
		_s.each(function(rec) {
					_opeItemStr.push(rec.data);
				})
		if (_params == null)
			_params = {};
		Ext.apply(_params, {
					details : Ext.util.JSON.encode(_opeItemStr)
				});
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
		this.getForm().loadRecord(new Ext.data.Record(_r));
		this.findById("createTime")
				.setText(_r.createTime.format('Y-m-d H:i:s'));
		// 为detailsGrid设置值
		this.detailsGrid.getStore().loadData(Ext.util.JSON.decode(_r.details),
				false);
	},
	reset : function() {
		this.getForm().reset();
	},
	onSubmit : function(_form, _action) {
		this.fireEvent("submit", this, _action, this.getValues());
	}
});
/** ***************************************************************************** */
org.kyerp.warehouse.PurchaseOrderInfoWindow = Ext.extend(Ext.Window, {
	url : "",
	form : null,
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.form = new org.kyerp.warehouse.PurchaseOrderFormPanel({
					url : this.url
				});
		org.kyerp.warehouse.PurchaseOrderInfoWindow.superclass.constructor
				.call(this, {
					plain : true,
					width : 760,
					height : 450,
					layout : 'fit',
					tbar : [{
								text : '保存',
								cls : 'x-btn-text-icon',
								icon : 'images/ext-extend/icons/save.gif',
								hidden : false,
								handler : this.onSubmitClick,
								scope : this
							}, {
								text : '提交审核',
								cls : 'x-btn-text-icon',
								icon : 'images/ext-extend/icons/new/mbi_007.gif',
								hidden : false
							}, {
								text : '返回编制',
								cls : 'x-btn-text-icon',
								icon : 'images/ext-extend/icons/order_edit.gif',
								hidden : false
							}, {
								text : '审核',
								cls : 'x-btn-text-icon',
								icon : 'images/ext-extend/icons/new/icon2_089.png',
								hidden : false
							}, {
								text : '关闭',
								cls : 'x-btn-text-icon',
								icon : 'images/ext-extend/icons/cross.gif',
								handler : this.onCancelClick,
								scope : this
							}, '->', {
								xtype : 'panel',
								id : 'bill-status',
								style : 'font-size:12px;color:red;padding-right:5px;'
							}],
					minimizable : true,
					maximizable : true,
					modal : true,
					items : this.form,
					border : false,
					closeAction : "hide"
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
			return _err;
		}
		this.close();
	}

});
/** ***************************************************************************** */
org.kyerp.warehouse.PurchaseOrderInsertWindow = Ext.extend(
		org.kyerp.warehouse.PurchaseOrderInfoWindow, {
			title : "添 加",
			iconCls : 'icon-utils-s-add',
			url : org.kyerp.warehouse.PurchaseOrderPanel_SAVE_URL,
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
org.kyerp.warehouse.PurchaseOrderUpdateWindow = Ext.extend(
		org.kyerp.warehouse.PurchaseOrderInfoWindow, {
			title : "修改",
			iconCls : 'icon-utils-s-edit',
			url : org.kyerp.warehouse.PurchaseOrderPanel_SAVE_URL,
			pnId : "",
			load : function(_r) {
				this.form.setValues(_r.data);
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
org.kyerp.warehouse.PurchaseOrderPanel = Ext.extend(Ext.grid.GridPanel, {
	insertWin : new org.kyerp.warehouse.PurchaseOrderInsertWindow(),
	updateWin : new org.kyerp.warehouse.PurchaseOrderUpdateWindow(),
	expander : null,
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.expander = new Ext.ux.grid.RowExpander({
					tpl : new Ext.Template('<p><b>入库产品编号:</b>')
				});
		this["store"] = new Ext.data.Store({
					autoLoad : {
						baseParams : {
							limit : 20
						}
					},
					listeners : {
						loadexception : function(proxy, options, response) {
							var data = Ext.decode(response.responseText);
							top.Ext.Msg.alert("错误", "载入数据时发生错误:"
											+ data["exception.message"]);
						}
					},
					url : org.kyerp.warehouse.PurchaseOrderPanel_STORE_URL,
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
										name : "createTime",
										type : "date",
										dateFormat : "Y-m-d H:i:s"
									}, {
										name : "statusString"
									}, {
										name : "writeDate",
										type : "date",
										dateFormat : "Y-m-d H:i:s"
									}, {
										name : "billCount"
									}, {
										name : "billCost"
									}, {
										name : "checkUserId"
									}, {
										name : "checkUserName"
									}, {
										name : "writeUserId"
									}, {
										name : "writeUserName"
									}, {
										name : "details"
									}]))
				});
		org.kyerp.warehouse.PurchaseOrderPanel.superclass.constructor.call(
				this, {
					stripeRows : true,
					plugins : this.expander,
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
					colModel : new Ext.grid.ColumnModel([this.expander, {
								header : "单据编号",
								dataIndex : "serialNumber",
								width : 100,
								menuDisabled : true
							}, {
								header : "单据时间",
								dataIndex : "createTime",
								renderer : Ext.util.Format
										.dateRenderer('Y-m-d'),
								width : 100,
								menuDisabled : true
							}, {
								header : '供应商',
								dataIndex : 'supplierName'
							}, {
								header : "订单数量",
								dataIndex : "billCount",
								menuDisabled : true
							}, {
								header : "订单金额",
								dataIndex : "billCost",
								menuDisabled : true
							}, {
								header : "备注",
								id : "remark",
								dataIndex : "remark",
								menuDisabled : true
							}, {
								header : "状态",
								dataIndex : "statusString",
								menuDisabled : true
							}]),
					autoExpandColumn : 'remark',
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
		this.on("show", this.loadStore, this);
		this.insertWin.on("submit", this.onInsertWinSubmit, this);
		this.updateWin.on("submit", this.onUpdateWinSubmit, this);
		this.addEvents("rowselect");
	},
	loadStore : function() {
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
						url : org.kyerp.warehouse.PurchaseOrderPanel_DELETE_URL,
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
				this.body = new org.kyerp.warehouse.PurchaseOrderPanel({
							border : false,
							bodyBorder : false
						});
				this.main.add(this.body);
				this.main.doLayout();
			}
		});