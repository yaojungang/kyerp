/** ***************************************************************************** */
org.kyerp.warehouse.OutStockFormPanel = Ext.extend(Ext.form.FormPanel, {
	detailsGrid : null,
	selectSupplierWindow : null,
	constructor : function(_cfg) {
		if (_cfg == null)
			_cfg = {};
		Ext.apply(this, _cfg);
		this.detailsGrid = new org.kyerp.warehouse.OutStockDetailEditorGrid();
		this.selectSupplierWindow = new org.kyerp.warehouse.SelectSupplierWindow(
				{
					onSelect : function(rec, supplierWin) {
						supplierWin.hide();
						Ext.WindowMgr.getActive().form.form
								.findField('supplierId').setValue(rec.data.id);
					}
				});
		org.kyerp.warehouse.OutStockFormPanel.superclass.constructor.call(this,
				{
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
											fieldLabel : '出库时间',
											name : 'outDate',
											xtype : 'datefield',
											format : 'Y-m-d',
											emptyText : new Date()
													.format('Y-m-d')
										}]
							}, {
								columnWidth : .35,
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
									fieldLabel : '收发类型',
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
									fieldLabel : '领料部门',
									xtype : 'treecombobox',
									name : 'receiveDepartmentId',
									hiddenName : 'receiveDepartmentId',
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
									fieldLabel : '领料人',
									xtype : 'treecombobox',
									name : 'receiveEmployeeId',
									hiddenName : 'receiveEmployeeId',
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
								columnWidth : .35,
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
											fieldLabel : '经办人',
											xtype : 'treecombobox',
											name : 'keeperId',
											hiddenName : 'keeperId',
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

										}, {
											fieldLabel : '备注',
											name : 'remark',
											xtype : 'textarea',
											height : 50
										}]
							}]
						}]
					}, {
						region : 'south',
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
									flex : .8,
									items : [{
												xtype : "displayfield",
												fieldLabel : '编制人',
												name : "writeEmployeeName"
											}]
								}, {
									flex : 1.2,
									items : [{
												xtype : "displayfield",
												fieldLabel : '编制时间',
												name : "createTime"
											}]
								}, {
									flex : .8,
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
									flex : 1.2,
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
							failure : onSubmitFailureGetMessage,
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
			// this.onCancelClick();
		} else {
			// 设置createTime 的显示格式
			this.getForm().findField("createTime").setValue(_r.createTime
					.format('Y-m-d H:i:s'));
			// 设置入库类型
			this.getForm().findField("inOutTypeId").setValue(_r.inOutTypeName);
			this.getForm().findField("inOutTypeId").hiddenField.value = _r.inOutTypeId;
			// 设置经手人
			this.getForm().findField("keeperId").setValue(_r.keeperName);
			this.getForm().findField("keeperId").hiddenField.value = _r.keeperId;
			// 设置领料部门
			this.getForm().findField("receiveDepartmentId")
					.setValue(_r.receiveDepartmentName);
			this.getForm().findField("receiveDepartmentId").hiddenField.value = _r.receiveDepartmentId;
			// 设置领料人
			this.getForm().findField("receiveEmployeeId")
					.setValue(_r.receiveEmployeeName);
			this.getForm().findField("receiveEmployeeId").hiddenField.value = _r.receiveEmployeeId;
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
org.kyerp.warehouse.OutStockInfoWindow = Ext.extend(Ext.Window, {
			url : "",
			form : null,
			tb : null,
			constructor : function(_cfg) {
				Ext.apply(this, _cfg);
				this.form = new org.kyerp.warehouse.OutStockFormPanel({
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
						}, {
							text : '冲销',
							cls : 'x-btn-text-icon',
							icon : 'images/ext-extend/icons/new/mbi_036.gif',
							handler : this.onCongXiaoClick,
							hidden : true,
							scope : this
						}, '->', {
							text : '关闭',
							cls : 'x-btn-text-icon',
							icon : 'images/ext-extend/icons/cross.gif',
							handler : this.onCancelClick,
							scope : this
						}];
				org.kyerp.warehouse.OutStockInfoWindow.superclass.constructor
						.call(this, {
									plain : true,
									width : 790,
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
			},
			onCongXiao : function() {
			},
			onCongXiaoClick : function() {
				this.onCongXiao();
				this.onCancelClick();
				this.fireEvent("reloadStore");
			}

		});
/** ***************************************************************************** */
org.kyerp.warehouse.OutStockInsertWindow = Ext.extend(
		org.kyerp.warehouse.OutStockInfoWindow, {
			title : "添加出库单",
			iconCls : 'icon-utils-s-add',
			url : org.kyerp.warehouse.OutStockPanel_SAVE_URL
		});
/** ***************************************************************************** */
org.kyerp.warehouse.OutStockUpdateWindow = Ext.extend(
		org.kyerp.warehouse.OutStockInfoWindow, {
			title : "修改出库单",
			iconCls : 'icon-utils-s-edit',
			url : org.kyerp.warehouse.OutStockPanel_SAVE_URL,
			pnId : "",
			load : function(_r) {
				this.form.setValues(_r.data);
				this.pnId = _r.get("id");
				// 显示可操作的工具栏
				var tb = this.getTopToolbar();
				for (var i = 0; i < 5; i++) {
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
				// 显示 生成冲销单 按钮
				if (_r.data.statusString == '已审核') {
					tb.get(4).setVisible(true);
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
							url : org.kyerp.warehouse.OutStock_PostForCheck_URL
									+ "?id=" + this.pnId,
							success : function(response) {
								var data = Ext.util.JSON
										.decode(response.responseText);
								if (data.success) {
									Ext.MessageBox.alert('提示', "单据状态改变成功!");
								} else {
									Ext.MessageBox.alert('警告', data.message);
								}
							}
						})
			},
			// 返回编制
			onReturnForEdit : function(_r) {
				Ext.Ajax.request({
							url : org.kyerp.warehouse.OutStock_ReturnForEdit_URL
									+ "?id=" + this.pnId,
							success : function(response) {
								var data = Ext.util.JSON
										.decode(response.responseText);
								if (data.success) {
									Ext.MessageBox.alert('提示', "单据状态改变成功!");
								} else {
									Ext.MessageBox.alert('警告', data.message);
								}
							}
						})
			},
			// 冲销
			onCongXiao : function(_r) {
				Ext.Ajax.request({
							url : org.kyerp.warehouse.OutStock_CongXiao_URL
									+ "?id=" + this.pnId,
							success : function(response) {
								var data = Ext.decode(response.responseText);
								if (data.success) {
									Ext.MessageBox.alert('提示',
											"生成冲销单成功，请审核相应的入库单!");
								} else {
									Ext.MessageBox.alert('警告', data.message);
								}
							}
						})
			},
			// 审核
			onCheckBill : function(_r) {
				Ext.Ajax.request({
							url : org.kyerp.warehouse.OutStock_CheckBill_URL
									+ "?id=" + this.pnId,
							success : function(response) {
								var data = Ext.util.JSON
										.decode(response.responseText);
								if (data.success) {
									Ext.MessageBox.alert('提示', "单据状态改变成功!");
								} else {
									Ext.MessageBox.alert('警告', data.message);
								}
							}
						})
			}
		});
/** ***************************************************************************** */
org.kyerp.warehouse.OutStockGrid = Ext.extend(Ext.grid.GridPanel, {
	insertWin : null,
	updateWin : null,
	expander : null,
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.insertWin = new org.kyerp.warehouse.OutStockInsertWindow();
		this.updateWin = new org.kyerp.warehouse.OutStockUpdateWindow();
		this["store"] = org.kyerp.warehouse.OutStockStore;
		org.kyerp.warehouse.OutStockGrid.superclass.constructor.call(this, {
					stripeRows : true,
					plugins : this.expander,
					tbar : [{
								text : "添  加",
								iconCls : 'icon-utils-s-add',
								handler : function() {
									this.insertWin.show();
									//this.insertWin.maximize();
								},
								scope : this
							}, "-", {
								text : "打  开",
								iconCls : 'icon-utils-s-edit',
								handler : function() {
									// alert("edit");
									this.updateWin.show();
									//this.updateWin.maximize();
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
								hidden : true,
								iconCls : 'icon-utils-s-delete',
								handler : function() {
									Ext.Msg.confirm("系统提示", "你确定删除此记录吗?",
											this.onRemove, this);
								},
								scope : this
							}, '->', '收发类型：', {
								xtype : 'treecombobox',
								fieldLabel : '供应商类型',
								name : 'inOutTypeId',
								hiddenName : 'inOutTypeId',
								editable : false,
								mode : 'local',
								displayField : 'name',
								valueField : 'id',
								triggerAction : 'all',
								allowBlank : false,
								rootText : 'root',
								rootId : '0',
								forceSelection : true,
								rootVisible : false,
								treeUrl : org.kyerp.warehouse.InOutTypePanel_TREE_URL,
								onSelect : function(node) {
									var store = org.kyerp.warehouse.OutStockStore;
									store.setBaseParam("inOutTypeId", node.id);
									store.load();
								}
							}, "-", "搜索：", new Ext.ux.form.SearchField({
										store : this.getStore()
									})],
					enableColumnMove : false,
					colModel : new Ext.grid.ColumnModel([{
								header : "单据编号",
								dataIndex : "serialNumber",
								width : 100,
								menuDisabled : true
							}, {
								header : "单据时间",
								dataIndex : "createTime",
								renderer : Ext.util.Format
										.dateRenderer('Y-m-d'),
								width : 80,
								menuDisabled : true
							}, {
								header : '收发类型',
								width : 70,
								dataIndex : 'inOutTypeName'
							}, {
								header : '领料单位',
								width : 70,
								dataIndex : 'receiveDepartmentName'
							}, {
								header : '领用人',
								align : 'center',
								width : 50,
								dataIndex : 'receiveEmployeeName'
							}, {
								header : "经手人",
								align : 'center',
								width : 50,
								dataIndex : "keeperName",
								menuDisabled : true
							}, {
								header : "状态",
								align : 'center',
								dataIndex : "statusString",
								renderer : statusRenderer,
								menuDisabled : true
							}, {
								header : "数量",
								width : 80,
								dataIndex : "billCount",
								align : 'right',
								menuDisabled : true
							}, {
								header : "金额",
								width : 80,
								dataIndex : "billCost",
								renderer : 'usMoney',
								align : 'right',
								menuDisabled : true
							}, {
								header : "操作员",
								width : 60,
								align : 'center',
								dataIndex : "writeEmployeeName",
								menuDisabled : true
							}, {
								header : "备注",
								id : "remark",
								dataIndex : "remark",
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
							})
				});
		this.insertWin.on("submit", this.onInsertWinSubmit, this);
		this.updateWin.on("submit", this.onUpdateWinSubmit, this);
		this.updateWin.on("reloadStore", this.reloadStore, this);
		this.on("rowdblclick", this.onRowdblclick, this);
		this.on("rowselect", this.onRowSelect, this);
	},
	onRowdblclick : function() {
		this.updateWin.show();
		try {
			this.updateWin.load(this.getSelected());
		} catch (_err) {
			Ext.Msg.alert("修改失败", _err);
			this.updateWin.close();
		}
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
						url : org.kyerp.warehouse.OutStockPanel_DELETE_URL,
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
        var data = _sel.data;
        var store = org.kyerp.warehouse.OutStockDetailStore;
        store.setBaseParam("outStockId", data.id);
        store.load( {
            params : {
                start : 0,
                limit : 20
            }
        });
    },
	reloadStore : function() {
		this.store.reload();
	}
});