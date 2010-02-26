/** ***************************************************************************** */
org.kyerp.warehouse.EnteringMaterialItems = Ext.extend(
		Ext.grid.EditorGridPanel, {
			inserted : [],
			materialCombo : null,
			warehouseCombo : null,
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
							_rs.set('warehouseId', _data.warehouseId);
							_rs.set('unit', _data.unitName);
							_rs.set('price', _data.price);
						},
						scope : this
					}
				});
				this.warehouseCombo = new Ext.form.ComboBox({
							hiddenName : 'warehouseId',
							lazyRender : true,
							pageSize : 20,
							listWidth : 360,
							valueField : 'id',
							displayField : 'name',
							mode : 'remote',
							allowBlank : false,
							emptyText : '请选择',
							triggerAction : 'all',
							displayField : "name",
							store : new Ext.data.Store({
										autoLoad : {
											baseParams : {
												limit : 20
											}
										},
										url : "warehouse/Warehouse/jsonList.html",
										reader : new Ext.data.JsonReader({
													totalProperty : "totalProperty",
													root : "rows",
													id : "id"
												}, new Ext.data.Record.create([
														{
															name : "id",
															type : "int"
														}, {
															name : "name",
															type : "string"
														}, {
															name : "serialNumber",
															type : "string"
														}]))
									})
						});
				org.kyerp.warehouse.EnteringMaterialItems.superclass.constructor
						.call(this, {
									autoHeight : true,
									height : 300,
									autoScroll : true,
									sm : new Ext.grid.RowSelectionModel({
												singleSelect : true
											}),
									tbar : [{
												text : "保存",
												handler : this.onSaveButtonClick,
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
									store : new Ext.data.SimpleStore(),
									columns : [{
										header : '品名型号',
										dataIndex : "materialId",
										renderer : Ext.ux.renderer
												.Combo(this.materialCombo),
										editor : this.materialCombo
									}, {
										header : "仓库",
										dataIndex : "warehouseId",
										renderer : Ext.ux.renderer
												.Combo(this.warehouseCombo),
										editor : this.warehouseCombo
									}, {
										header : '单位',
										width : 50,
										dataIndex : "unit"
									}, {
										header : "数量",
										width : 70,
										dataIndex : "amount",
										editor : new Ext.form.NumberField({
													allowBlank : false
												})
									}, {
										header : "单价",
										dataIndex : "price",
										editor : new Ext.form.NumberField({
													allowBlank : false,
													minValue : 0
												})
									}, {
										header : "金额",
										width : 80,
										dataIndex : "money",
										editor : new Ext.form.NumberField({
													allowBlank : false,
													minValue : 0
												})
									}, {
										header : '批号',
										width : 90,
										dataIndex : "batchNumber",
										editor : new Ext.form.TextField()
									}, {
										header : '备注',
										dataIndex : "remark",
										editor : new Ext.form.TextArea()
									}]
								});
			},

			onSaveButtonClick : function() {
				var _m = this.getStore().modified;
				// alert(Ext.util.JSON.encode(_m));
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
				this.conn.on("requestcomplete", this.onSaveInsertData, this);
				this.conn.request({
					url : "http://localhost/extjstest/server/app/test/18/post.asp",
					params : {
						content : Ext.util.JSON.encode(_temp)
					}
				});
				// alert(Ext.util.JSON.encode(_temp));
				this.getStore().commitChanges();

			},
			onInsertButtonClick : function() {
				var _rs = new Ext.data.Record({
							materialId : '',
							unit : '',
							price : 0,
							amount : 1,
							warehouseId : '',
							money : 0,
							batchNumber : '',
							remark : ''
						});
				_rs.set('price', 0);
				_rs.set('amount', 1);
				_rs.set('warehouseId', 1);
				_rs.set("money", 0);
				_rs.set('batchNumber', '');
				_rs.set('remark', '');
				this.inserted.push(_rs);
				this.getStore().add(_rs);
				// 选中加入的行
				this.getSelectionModel().selectRow(this.getStore().getCount()
						- 1);
				this
						.fireEvent('rowclick', this, this.getStore().getCount()
										- 1)

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
					Ext.Msg.confirm("系统询问", "你是否确认删除此条记录?",
							this.onRemoveQuestion, this);
				} catch (_err) {
					Ext.Msg.alert("系统提示", _err);
				}
			},
			onRemoveQuestion : function(_btn) {
				if (_btn == "yes") {
					var _rs = this.getSelectionModel().getSelected();
					this.getStore().remove(_rs);
					if (_rs.get("id") != "") {
						this.conn.un("requestcomplete", this.onSaveInsertData,
								this);
						this.conn.request({
							url : "http://localhost/extjstest/server/app/test/18/delete.asp",
							params : {
								id : _rs.get("id")
							}
						});
					} else {
						this.inserted.remove(_rs);
						this.getStore().modified.remove(_rs);
					}
				}
			}
		});
org.kyerp.warehouse.EnteringMaterialFormPanel = Ext.extend(Ext.form.FormPanel,
		{
			url : "",
			warehouseStore : null,
			materialListStroe : null,
			opeaItems : null,
			constructor : function(_cfg) {
				if (_cfg == null)
					_cfg = {};
				Ext.apply(this, _cfg);
				this.opeaItems = new org.kyerp.warehouse.EnteringMaterialItems();
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
				this.materialListStroe = new Ext.data.Store({
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
				org.kyerp.warehouse.EnteringMaterialFormPanel.superclass.constructor
						.call(this, {
							labelWidth : 60,
							labelAlign : 'right',
							defaultType : "textfield",
							defaults : {
								anchor : "100%",
								msgTarget : 'side'
							},
							baseCls : "x-plain",
							items : [{
								xtype : 'panel',
								layout : 'column',
								baseCls : "x-plain",
								items : [{
											columnWidth : .3,
											xtype : 'panel',
											layout : 'form',
											baseCls : "x-plain",
											labelAlign : 'right',
											defaultType : "textfield",
											defaults : {
												anchor : '92%'
											},
											items : [{
														fieldLabel : '入库类型'
													}, {
														fieldLabel : '联系人'
													}, {
														fieldLabel : '入库时间',
														xtype : 'datefield',
														format : 'Y-m-d'
													}]
										}, {
											columnWidth : .25,
											xtype : 'panel',
											layout : 'form',
											baseCls : "x-plain",
											labelAlign : 'right',
											defaultType : "textfield",
											defaults : {
												anchor : '92%'
											},
											items : [{
														fieldLabel : '库管员'
													}, {
														fieldLabel : '联系电话'
													}, {
														fieldLabel : '负责人'
													}]
										}, {
											columnWidth : .45,
											xtype : 'panel',
											layout : 'form',
											baseCls : "x-plain",
											labelAlign : 'right',
											defaultType : "textfield",
											defaults : {
												anchor : '92%'
											},
											items : [{
												fieldLabel : '供应商',
												xtype : 'combo',
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
											}, {
												fieldLabel : '联系地址'
											}, {
												fieldLabel : '备注'
											}]
										}]
							}, this.opeaItems]
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
									width : 760,
									height : 450,
									layout : 'fit',
									minimizable : true,
									maximizable : true,
									modal : true,
									items : this.form,
									bodyStyle : 'padding:5 1 5 0',
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
			onSubmitClick : function() {
				var _temp = [];
				var _s = this.form.opeaItems.getStore();
				_s.each(function(rec) {
							// alert(Ext.util.JSON.encode(rec.data));
							_temp.push(rec.data);
						})
				//alert("添加" + Ext.util.JSON.encode(_temp));
				this.form.submit({
							opeItems : Ext.util.JSON.encode(_temp)
						});
			},
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
	expander : null,
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.expander = new Ext.ux.grid.RowExpander({
					tpl : new Ext.Template('<p><b>入库产品编号:</b> {batchIds}</p>',
							'<p><b>入库产品名称:</b> {batchNames}</p>')
				});
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
										name : "supplierId",
										type : "int"
									}, {
										name : "supplierName",
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
									}, {
										name : 'batchIds',
										type : 'string'
									}, {
										name : 'batchNames',
										type : 'string'
									}]))
				});
		org.kyerp.warehouse.EnteringMaterialPanel.superclass.constructor.call(
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
								header : "编号",
								dataIndex : "serialNumber",
								width : 100,
								menuDisabled : true
							}, {
								header : "入库产品",
								id : 'batchNames',
								dataIndex : "batchNames",
								width : 100,
								menuDisabled : true
							}, {
								header : '供应商',
								dataIndex : 'supplierName'
							}, {
								header : "入库时间",
								width : 125,
								dataIndex : "enteringTime",
								renderer : renderDate('Y-m-d H:m:s'),
								menuDisabled : true
							}]),
					autoExpandColumn : 'batchNames',
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
		this.insertWin.form.warehouseStore.load();
		this.insertWin.form.materialListStroe.load();
		this.updateWin.form.warehouseStore.load();
		this.updateWin.form.materialListStroe.load();
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

Ext.extend(org.kyerp.module, {
			init : function() {
				this.body = new org.kyerp.warehouse.EnteringMaterialPanel({
							border : false,
							bodyBorder : false
						});
				this.main.add(this.body);
				this.main.doLayout();
			}
		});