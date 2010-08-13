/** ***************************************************************************** */
org.kyerp.warehouse.SupplierStore = new Ext.data.Store({
			autoLoad : {
				baseParams : {
					limit : 20
				}
			},
			url : org.kyerp.warehouse.SupplierPanel_STORE_URL,
			reader : new Ext.data.JsonReader({
						totalProperty : "totalProperty",
						root : "rows",
						idProperty : "id"
					}, new Ext.data.Record.create([{
								name : "id",
								type : "int"
							}, {
								name : "typeId"
							}, {
								name : "typeName"
							}, {
								name : "serialNumber"
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
							}, {
								name : "remark"
							}, {
								name : "address"
							}, {
								name : "postcode"
							}, {
								name : "phone"
							}, {
								name : "fax"
							}, {
								name : "www"
							}, {
								name : "email"
							}, {
								name : "helpCode"
							}, {
								name : "payCost"
							}, {
								name : "logopath"
							}]))
		});
/** ***************************************************************************** */
org.kyerp.warehouse.SupplierFormPanel = Ext.extend(Ext.form.FormPanel, {
	url : "",
	constructor : function(_cfg) {
		if (_cfg == null)
			_cfg = {};
		Ext.apply(this, _cfg);
		var _readOnly = this["readOnly"] == null ? false : this["readOnly"];
		org.kyerp.warehouse.SupplierFormPanel.superclass.constructor.call(this,
				{
					labelWidth : 80,
					labelAlign : 'right',
					bodyStyle : 'padding:10px',
					defaults : {
						msgTarget : 'side',
						readOnly : _readOnly
					},
					items : [{
						xtype : 'fieldset',
						title : '基本资料',
						autoHeight : true,
						items : [{
							layout : 'column',
							border : false,
							defaults : {
								border : false
							},
							items : [{
										columnWidth : .5,
										layout : 'form',
										defaultType : 'textfield',
										defaults : {
											width : 200
										},
										items : [{
													fieldLabel : '供应商编码',
													name : 'serialNumber'
												}, {
													fieldLabel : '供应商简称',
													name : 'name'
												}, {
													fieldLabel : '合格供方',
													name : 'qualified',
													xtype : "radiogroup",
													items : [{
																boxLabel : '是',
																name : 'qualified',
																inputValue:"true"
															}, {
																boxLabel : '否',
																name : 'qualified',
																inputValue:"false"
															}]
												}]
									}, {
										columnWidth : .5,
										layout : 'form',
										defaultType : 'textfield',
										defaults : {
											anchor : "-20px"
										},
										items : [{
											xtype : 'treecombobox',
											fieldLabel : '供应商类型',
											name : 'typeId',
											hiddenName : 'typeId',
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
											treeUrl : org.kyerp.warehouse.SupplierTypePanel_TREE_URL
										}, {
											fieldLabel : "简拼",
											allowBlank : false,
											name : "nameSpell"
										}, {
											fieldLabel : '助记码',
											name : 'helpCode'
										}]
									}]
						}, {
							xtype : 'textfield',
							fieldLabel : '供应商全称',
							name : 'fullName',
							width : 498
						}, {
							xtype : 'textfield',
							fieldLabel : '说明',
							name : 'remark',
							width : 498
						}]
					}, {
						xtype : 'fieldset',
						title : '联系资料',
						autoHeight : true,
						items : [{
									layout : 'column',
									border : false,
									defaults : {
										border : false
									},
									items : [{
												columnWidth : .5,
												layout : 'form',
												defaultType : 'textfield',
												defaults : {
													anchor : "-20px"
												},
												items : [{
															fieldLabel : '地址',
															name : 'address'
														}, {
															fieldLabel : '电话',
															name : 'phone'
														}, {
															fieldLabel : '网址',
															name : 'www'
														}]
											}, {
												columnWidth : .5,
												layout : 'form',
												defaultType : 'textfield',
												defaults : {
													anchor : "-20px"
												},
												items : [{
															fieldLabel : '邮编',
															name : 'postcode'
														}, {
															fieldLabel : '传真',
															name : 'fax'
														}, {
															fieldLabel : '电子邮件',
															name : 'email'
														}]
											}]
								}]
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
org.kyerp.warehouse.SupplierInfoWindow = Ext.extend(Ext.Window, {
			url : "",
			form : null,
			constructor : function(_cfg) {
				Ext.apply(this, _cfg);
				this.form = new org.kyerp.warehouse.SupplierFormPanel({
							url : this.url
						});
				org.kyerp.warehouse.SupplierInfoWindow.superclass.constructor
						.call(this, {
									// plain : true,
									width : 655,
									height : 370,
									layout : 'fit',
									modal : true,
									items : this.form,
									border : false,
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
org.kyerp.warehouse.SupplierInsertWindow = Ext.extend(
		org.kyerp.warehouse.SupplierInfoWindow, {
			title : "添 加",
			iconCls : 'icon-utils-s-add',
			url : org.kyerp.warehouse.SupplierPanel_SAVE_URL,
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
org.kyerp.warehouse.SupplierUpdateWindow = Ext.extend(
		org.kyerp.warehouse.SupplierInfoWindow, {
			title : "修 改",
			iconCls : 'icon-utils-s-edit',
			url : org.kyerp.warehouse.SupplierPanel_SAVE_URL,
			pnId : "",
			load : function(_r) {
				this.form.setValues(_r);
				this.pnId = _r.get("id");
				this.form.form.findField('typeId')
						.setValue(new Ext.tree.TreeNode({
									id : _r.get("typeId"),
									text : _r.get("typeName")
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
org.kyerp.warehouse.SupplierPanel = Ext.extend(Ext.grid.GridPanel, {
	insertWin : new org.kyerp.warehouse.SupplierInsertWindow(),
	updateWin : new org.kyerp.warehouse.SupplierUpdateWindow(),
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this["store"] = org.kyerp.warehouse.SupplierStore;
		org.kyerp.warehouse.SupplierPanel.superclass.constructor.call(this, {
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
					}, '->', '供应商分类：', {
						xtype : 'treecombobox',
						fieldLabel : '供应商类型',
						name : 'typeId',
						hiddenName : 'typeId',
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
						treeUrl : org.kyerp.warehouse.SupplierTypePanel_TREE_URL,
						onSelect : function(node) {
							var store = org.kyerp.warehouse.SupplierStore;
							store.setBaseParam("typeId", node.id);
							store.load();
						}
					}, "-", "搜索：", new Ext.ux.form.SearchField({
								store : this.getStore()
							})],
			enableColumnMove : false,
			columns : [new Ext.grid.RowNumberer(), {
						header : "ID",
						dataIndex : "id",
						align : "center",
						width : 50,
						menuDisabled : true
					}, {
						header : '供应商编码',
						width : 70,
						sortable : true,
						dataIndex : 'serialNumber'
					}, {
						header : '供应商名称',
						width : 100,
						sortable : true,
						dataIndex : 'name'
					}, {
						header : '分类',
						width : 100,
						sortable : true,
						dataIndex : 'typeName'
					}, {
						header : '邮编',
						width : 60,
						sortable : true,
						dataIndex : 'postcode'
					}, {
						header : '地址',
						width : 200,
						sortable : true,
						dataIndex : 'address'
					}, {
						header : '电话',
						width : 100,
						sortable : true,
						dataIndex : 'phone'
					}, {
						header : '传真',
						width : 100,
						sortable : true,
						dataIndex : 'fax'
					}, {
						header : '网址',
						width : 100,
						sortable : true,
						dataIndex : 'www'
					}, {
						header : '电子邮件',
						width : 100,
						sortable : true,
						dataIndex : 'email'
					}, {
						header : "全称",
						dataIndex : "fullName",
						width : 150,
						menuDisabled : true
					}, {
						header : "合格供方",
						dataIndex : "qualified",
						menuDisabled : true
					}, {
						header : '说明',
						width : 200,
						sortable : true,
						dataIndex : 'remark'
					}],
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
						url : org.kyerp.warehouse.SupplierPanel_DELETE_URL,
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
		this.store.reload();
	},
	onUpdateWinSubmit : function(_win, _r) {
		this.update(_r);
		this.store.reload();
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
				this.body = new org.kyerp.warehouse.SupplierPanel({
							border : false,
							bodyBorder : false
						});
				this.main.add(this.body);
				this.main.doLayout();
			}
		});
