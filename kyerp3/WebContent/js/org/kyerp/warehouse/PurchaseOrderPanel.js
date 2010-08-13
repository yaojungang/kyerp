/** ***************************************************************************** */
org.kyerp.warehouse.PurchaseOrderFormPanel = Ext.extend(Ext.form.FormPanel, {
	detailsGrid : null,
	selectSupplierWindow : null,
	constructor : function(_cfg) {
		if (_cfg == null)
			_cfg = {};
		Ext.apply(this, _cfg);
		this.detailsGrid = new org.kyerp.warehouse.PurchaseOrderItemsEditorGridPanel();
		this.selectSupplierWindow = new org.kyerp.warehouse.SelectSupplierWindow(
				{
					onSelect : function(rec, supplierWin) {
						supplierWin.hide();
						Ext.WindowMgr.getActive().form.form
								.findField('supplierId').setValue(rec.data.id);
					}
				});
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
											xtype : "displayfield",
											name : 'serialNumber'
										}, {
											fieldLabel : '单据状态',
											xtype : 'displayfield',
											name : 'statusString'
										}, {
											fieldLabel : '到货时间',
											name : 'arriveDate',
											xtype : 'datefield',
											format : 'Y-m-d',
											emptyText : new Date()
													.format('Y-m-d')
										}]
							}, {
								columnWidth : .3,
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
									fieldLabel : '申请类型',
									xtype : 'treecombobox',
									name : 'inOutTypeId',
									hiddenName : 'inOutTypeId',
									editable : false,
									width : 250,
									mode : 'local',
									displayField : 'name',
									valueField : 'id',
									triggerAction : 'all',
									allowBlank : false,
									rootText : 'root',
									rootId : '0',
									forceSelection : true,
									rootVisible : false,
									treeUrl : org.kyerp.warehouse.InOutTypePanel_TREE_URL
								}, {
									fieldLabel : '申请部门',
									xtype : 'treecombobox',
									name : 'applicationDepartmentId',
									hiddenName : 'applicationDepartmentId',
									allowUnLeafClick : true,
									editable : false,
									width : 250,
									mode : 'local',
									displayField : 'name',
									valueField : 'id',
									triggerAction : 'all',
									allowBlank : false,
									rootText : 'root',
									rootId : '0',
									forceSelection : true,
									rootVisible : false,
									treeUrl : org.kyerp.org.DepartmentPanel_TREE_URL
								}, {
									fieldLabel : '申请人',
									xtype : 'treecombobox',
									name : 'applicantId',
									hiddenName : 'applicantId',
									editable : false,
									width : 250,
									mode : 'local',
									displayField : 'name',
									valueField : 'id',
									triggerAction : 'all',
									allowBlank : false,
									rootText : 'root',
									rootId : '0',
									forceSelection : true,
									rootVisible : false,
									treeUrl : org.kyerp.org.Employee_TREE_URL
								}]
							}, {
								columnWidth : .4,
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
												store : org.kyerp.warehouse.supplierStore,
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
											cls : 'x-btn-icon',
											icon : 'images/ext-extend/icons/query.gif',
											handler : this.onSelectSupplierClick,
											scope : this
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
						// autoHeight : true,
						height : 40,
						frame : false,
						border : false,
						layout : {
							type : "hbox",
							aligh : "stretch",
							pack : "start",
							padding : 5
						},
						defaults : {
							frame : false,
							border : false,
							layout : 'form',
							labelWidth : 60,
							labelAlign : 'right',
							baseCls : "x-plain",
							height : 40
						},
						items : [{
									flex : 1,
									items : [{
												xtype : "displayfield",
												fieldLabel : '编制人',
												name : "writeEmployeeName"
											}]
								}, {
									flex : 1.5,
									items : [{
												xtype : "displayfield",
												fieldLabel : '编制时间',
												name : "createTime"
											}]
								}, {
									flex : 1,
									items : [{
												xtype : "displayfield",
												fieldLabel : '审核',
												name : "checkEmployeeName"
											}]
								}, {
									flex : 1,
									items : [{
												xtype : "displayfield",
												fieldLabel : '合计数量',
												name : "billCount"
											}]
								}, {
									flex : 1,
									items : [{
												xtype : "displayfield",
												fieldLabel : '合计金额',
												name : "billCost"
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
		if (_s.getCount() == 0) {
			alert("请至少加入一条物料！");
			return;
		}
		if (!this.getForm().isValid()) {
			alert("表单验证没有通过!");
			return;
		}
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
							success : this.onSubmitSuccess,
							failure : function(form, action) {
								Ext.Msg.alert('警告', '系统错误');
							},
							waitTitle : "数据传送",
							waitMsg : "数据传送中,请稍候...",
							scope : this
						});
		} catch (_err) {
		}
	},
	onSelectSupplierClick : function() {
		this.selectSupplierWindow.show();
	},
	getValues : function() {
		if (this.getForm().isValid())
			return new Ext.data.Record(this.getForm().getValues());
		else
			throw Error("表单验证没有通过");
	},
	setValues : function(_r) {
		this.getForm().loadRecord(new Ext.data.Record(_r));
		if (Ext.isEmpty(_r.id)) {
			Ext.Msg.alert("错误", "载入数据时发生错误！");
		} else {
			// 设置createTime 的显示格式
			this.getForm().findField("createTime").setValue(_r.createTime
					.format('Y-m-d H:i:s'));
			//设置申请类型
			this.getForm().findField("inOutTypeId").setValue(_r.inOutTypeName);
			this.getForm().findField("inOutTypeId").hiddenField.value =_r.inOutTypeId;
			//设置申请部门
			this.getForm().findField("applicationDepartmentId").setValue(_r.applicationDepartmentName);
			this.getForm().findField("applicationDepartmentId").hiddenField.value =_r.applicationDepartmentId;
			//设置申请人
			this.getForm().findField("applicantId").setValue(_r.applicantName);
			this.getForm().findField("applicantId").hiddenField.value =_r.applicantId;
			// 为detailsGrid设置值
			this.detailsGrid.getStore().loadData(
					Ext.util.JSON.decode(_r.details), false);
		}
	},
	reset : function() {
		this.detailsGrid.getStore().removeAll();
		this.getForm().reset();
	},
	onSubmitSuccess : function(_form, _action) {
		this.fireEvent("submit", this, _action, this.getValues());
	},
	onSubmitClick : function() {
		this.submit();
	}
});
/** ***************************************************************************** */
org.kyerp.warehouse.PurchaseOrderInfoWindow = Ext.extend(Ext.Window, {
			url : "",
			form : null,
			tb : null,
			constructor : function(_cfg) {
				Ext.apply(this, _cfg);
				this.form = new org.kyerp.warehouse.PurchaseOrderFormPanel({
							url : this.url
						});
				this.tb = [{
							text : '保存',
							cls : 'x-btn-text-icon',
							icon : 'images/ext-extend/icons/save.gif',
							handler : this.onSubmitClick,
							scope : this
						}, {
							text : '提交审核',
							cls : 'x-btn-text-icon',
							icon : 'images/ext-extend/icons/new/mbi_007.gif',
							hidden : true,
							handler : this.onPostForCheckClick,
							scope : this
						}, {
							text : '返回编制',
							cls : 'x-btn-text-icon',
							icon : 'images/ext-extend/icons/order_edit.gif',
							hidden : true,
							handler : this.onReturnForEditClick,
							scope : this
						}, {
							text : '审核',
							cls : 'x-btn-text-icon',
							icon : 'images/ext-extend/icons/new/icon2_089.png',
							hidden : true,
							handler : this.onCheckBillClick,
							scope : this
						},  '->',{
							text : '关闭',
							cls : 'x-btn-text-icon',
							icon : 'images/ext-extend/icons/cross.gif',
							handler : this.onCancelClick,
							scope : this
						}];
				org.kyerp.warehouse.PurchaseOrderInfoWindow.superclass.constructor
						.call(this, {
									plain : true,
									width : 760,
									height : 450,
									layout : 'fit',
									minimizable : true,
									maximizable : true,
									modal : true,
									items : this.form,
									border : false,
									closeAction : "hide",
									tbar : this.tb
								});

				this.addEvents("submit");
				this.addEvents("reloadStore");
				this.form.on("submit", this.onSubmitSuccess, this);
			},
			onSubmitSuccess : function(_form, _action, _values) {
				this.form.reset();
				this.hide();
				try {
					this.fireEvent("submit", this, _values);
				} catch (_err) {
					return;
				}
			},
			onSubmitClick : function() {
				this.form.submit();
			},
			onPostForCheck : function() {
			},
			onPostForCheckClick : function() {
				this.onPostForCheck();
				this.onCancelClick();
				this.fireEvent("reloadStore");
			},
			onReturnForEdit : function() {
			},
			onReturnForEditClick : function() {
				this.onReturnForEdit();
				this.onCancelClick();
				this.fireEvent("reloadStore");
			},
			onCheckBill : function() {
			},
			onCheckBillClick : function() {
				this.onCheckBill();
				this.onCancelClick();
				this.fireEvent("reloadStore");
			},
			onCancelClick : function() {
				this.form.reset();
				this.hide();
			}

		});
/** ***************************************************************************** */
org.kyerp.warehouse.PurchaseOrderInsertWindow = Ext.extend(
		org.kyerp.warehouse.PurchaseOrderInfoWindow, {
			title : "添 加",
			iconCls : 'icon-utils-s-add',
			url : org.kyerp.warehouse.PurchaseOrderPanel_SAVE_URL
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
				// 显示可操作的工具栏
				var tb = this.getTopToolbar();
				for (var i = 0; i < 4; i++) {
					tb.items.get(i).setVisible(false);
				}
				// 保存和提交审核 可见
				if (_r.data.editAble == "true") {
					tb.items.get(0).setVisible(true);
					if (!Ext.isEmpty(_r.id)) {
						tb.items.get(1).setVisible(true);
					}
				}
				// 未审核
				if (_r.data.statusString == '等待审核') {
					tb.get(2).setVisible(true);
					tb.get(3).setVisible(true);
				}
			},
			onSubmitClick : function() {
				this.form.submit({
							id : this.pnId
						});
			},
			// 保存单据后提交审核
			onPostForCheck : function(_r) {
				Ext.Ajax.request({
							url : org.kyerp.warehouse.PurchaseOrder_PostForCheck_URL
									+ "?id=" + this.pnId,
							success : function(response) {
								var data = Ext.util.JSON
										.decode(response.responseText);
								if (data.success) {
									Ext.MessageBox.alert('提示', "单据状态改变成功!");
								} else {
									Ext.MessageBox.alert('警告', data.msg);
								}
							}
						})
			},// 返回编制
			onReturnForEdit : function(_r) {
				Ext.Ajax.request({
							url : org.kyerp.warehouse.PurchaseOrder_ReturnForEdit_URL
									+ "?id=" + this.pnId,
							success : function(response) {
								var data = Ext.util.JSON
										.decode(response.responseText);
								if (data.success) {
									Ext.MessageBox.alert('提示', "单据状态改变成功!");
								} else {
									Ext.MessageBox.alert('警告', data.msg);
								}
							}
						})
			},// 审核
			onCheckBill : function(_r) {
				Ext.Ajax.request({
							url : org.kyerp.warehouse.PurchaseOrder_CheckBill_URL
									+ "?id=" + this.pnId,
							success : function(response) {
								var data = Ext.util.JSON
										.decode(response.responseText);
								if (data.success) {
									Ext.MessageBox.alert('提示', "单据状态改变成功!");
								} else {
									Ext.MessageBox.alert('警告', data.msg);
								}
							}
						})
			}
		});
/** ***************************************************************************** */
org.kyerp.warehouse.PurchaseOrderPanel = Ext.extend(Ext.grid.GridPanel, {
	insertWin : null,
	updateWin : null,
	expander : null,
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.expander = new Ext.ux.grid.RowExpander({
					tpl : new Ext.Template('<p><b>明细:</b>')
				});
		this.insertWin = new org.kyerp.warehouse.PurchaseOrderInsertWindow();
		this.updateWin = new org.kyerp.warehouse.PurchaseOrderUpdateWindow();
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
								idProperty : "id"
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
										name : "statusString",
										type : "string"
									}, {
										name : "writeDate",
										type : "date",
										dateFormat : "Y-m-d H:i:s"
									}, {
										name : "arriveDate",
										type : "date",
										dateFormat : "Y-m-d"
									}, {
										name : "billCount",
										type : "int"
									}, {
										name : "billCost",
										type : "float",
										convert : function(value) {
											return "￥" + value + "元"
										}
									}, {
										name : "checkUserId",
										type : "int"
									}, {
										name : "checkUserName",
										type : "string"
									}, {
										name : "checkEmployeeId",
										type : "int"
									}, {
										name : "checkEmployeeName",
										type : "string"
									}, {
										name : "writeUserId",
										type : "int"
									}, {
										name : "writeUserName",
										type : "string"
									}, {
										name : "writeEmployeeId",
										type : "int"
									}, {
										name : "writeEmployeeName",
										type : "string"
									}, {
										name : "details"
									}, {
										name : "supplierId",
										type : "int"
									}, {
										name : "supplierName",
										type : "string"
									}, {
										name : "remark",
										type : "string"
									}, {
										name : "editAble"
									}, {
										name : 'inOutTypeId'
									}, {
										name : 'inOutTypeName'
									}, {
										name : 'applicationDepartmentId'
									}, {
										name : 'applicationDepartmentName'
									}, {
										name : 'applicantId'
									}, {
										name : 'applicantName'
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
		this.insertWin.on("submit", this.onInsertWinSubmit, this);
		this.updateWin.on("submit", this.onUpdateWinSubmit, this);
		this.updateWin.on("reloadStore", this.reloadStore, this);
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
		// this.insert(_r);
		this.store.reload();
	},
	onUpdateWinSubmit : function(_win, _r) {
		// this.update(_r);
		this.store.reload();
	},
	onRemove : function(_btn) {
		if (_btn == "yes")
			this.removeItem();
	},
	onRowSelect : function(_sel, _index, _r) {
		this.fireEvent("rowselect", _r);
	},
	reloadStore : function() {
		this.store.reload();
	}
});
/** ***************************************************************************** */
Ext.extend(org.kyerp.module, {
			init : function() {
				require('SelectSupplierWindow.js;' + 'SelectMaterialWindow.js;'
								+ 'PurchaseOrderItemsEditorGridPanel.js', {
							basedir : 'js/org/kyerp/warehouse'
						});
				this.body = new org.kyerp.warehouse.PurchaseOrderPanel({
							border : false,
							bodyBorder : false
						});
				this.main.add(this.body);
				this.main.doLayout();
			}
		});