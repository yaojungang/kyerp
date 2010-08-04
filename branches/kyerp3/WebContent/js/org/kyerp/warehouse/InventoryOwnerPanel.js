/** ***************************************************************************** */
org.kyerp.warehouse.InventoryOwnerStore = new Ext.data.Store( {
	autoLoad : {
		baseParams : {
			limit : 20
		}
	},
	url : org.kyerp.warehouse.InventoryOwnerPanel_STORE_URL,
	reader : new Ext.data.JsonReader( {
		totalProperty : "totalProperty",
		root : "rows",
		idProperty : "id"
	}, new Ext.data.Record.create( [ {
		name : "id",
		type : "int"
	}, {
		name : "serialNumber"
	}, {
		name : "name",
		type : "string"
	} ]))
});

/** ***************************************************************************** */
org.kyerp.warehouse.InventoryOwnerPanel = Ext
		.extend(
				Ext.grid.EditorGridPanel,
				{
					constructor : function(_cfg) {
						Ext.apply(this, _cfg);
						org.kyerp.warehouse.InventoryOwnerPanel.superclass.constructor
								.call(
										this,
										{
											store : org.kyerp.warehouse.InventoryOwnerStore,
											stripeRows : true,
											tbar : [
													{
														text : "添加",
														iconCls : 'icon-utils-s-add',
														handler : this.onInsertButtonClick,
														scope : this
													},
													"-",
													{
														text : "修改",
														iconCls : 'icon-utils-s-edit',
														handler : function() {
															this.updateWin
																	.show();
															try {
																this.updateWin
																		.load(this
																				.getSelected());
															} catch (_err) {
																Ext.Msg.alert(
																		"修改失败",
																		_err);
																this.updateWin
																		.close();
															}
														},
														scope : this
													},
													"-",
													{
														text : "删除",
														iconCls : 'icon-utils-s-delete',
														handler : function() {
															Ext.Msg
																	.confirm(
																			"系统提示",
																			"你确定删除此记录吗?",
																			this.onRemove,
																			this);
														},
														scope : this
													},
													'->',
													"搜索：",
													new Ext.ux.form.SearchField(
															{
																store : this
																		.getStore()
															}) ],
											enableColumnMove : false,
											columns : [
													new Ext.grid.RowNumberer(),
													{
														header : "ID",
														dataIndex : "id",
														align : "center",
														width : 50,
														menuDisabled : true
													},
													{
														header : '编码',
														width : 70,
														sortable : true,
														dataIndex : 'serialNumber',
														editor : new Ext.form.TextField()
													},
													{
														header : '名称',
														width : 100,
														sortable : true,
														dataIndex : 'name',
														editor : new Ext.form.TextField()
													} ],
											selModel : new Ext.grid.RowSelectionModel(
													{
														singleSelect : true,
														listeners : {
															"rowselect" : {
																fn : function(
																		_sel,
																		_index,
																		_r) {

																	this
																			.fireEvent(
																					"rowselect",
																					_r);
																},
																scope : this
															}
														}
													}),
											bbar : new Ext.PagingToolbar(
													{
														plugins : new Ext.ux.Andrie.pPageSize(
																{
																	beforeText : '每页显示',
																	afterText : '条'
																}),
														pageSize : 20,
														store : org.kyerp.warehouse.InventoryOwnerStore,
														displayInfo : true,
														displayMsg : '显示  {0} - {1} 条记录,共有 {2} 条记录',
														emptyMsg : "没有数据"
													})
										});
						this
								.on(
										"afteredit",
										function(e) {
											Ext.Ajax
													.request( {
														url : org.kyerp.warehouse.InventoryOwnerPanel_SAVE_URL,
														params : {
															id : e.record.data.id,
															name : e.record.data.name,
															serialNumber : e.record.data.serialNumber
														},
														method : 'POST',
														success : function(response) {
															var data = Ext.decode(response.responseText);
															showMsg("提示",data.message);
															e.record
																	.commit(false);
														},
														failure : onSubmitFailureGetMessage
													});
										});
					},
					getSelected : function(_grid) {
						var _sm = this.getSelectionModel();
						if (_sm.getCount() == 0)
							throw Error("你尚未选定一条记录");
						return _sm.getSelected();
					},
					onInsertButtonClick : function() {
						var _rs = new Ext.data.Record( {
							serialNumber : '0000',
							name : '名称'
						});
						this.getStore().add(_rs);
						// 选中加入的行
						this.getSelectionModel().selectRow(
								this.getStore().getCount() - 1);
						this.fireEvent('rowclick', this, this.getStore()
								.getCount() - 1);
						this.startEditing(this.getStore().getCount() - 1, 0);
					},
					update : function(_r) {
						try {
							var _sr = this.getSelected();
							var _data = _sr.data;
							for ( var _i in _data) {
								_sr.set(_i, _r.get(_i));
							}
							_sr.commit();
						} catch (_err) {
						}
					},
					removeItem : function() {
						try {
							var _sr = this.getSelected();
							Ext.Ajax
									.request( {
										url : org.kyerp.warehouse.InventoryOwnerPanel_DELETE_URL,
										params : {
											ids : _sr.get("id")
										}
									});
							this.getStore().remove(_sr);

						} catch (_err) {
							Ext.Msg.alert("删除失败", _err);
						}
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
		this.body = new org.kyerp.warehouse.InventoryOwnerPanel( {
			border : false,
			bodyBorder : false
		});
		this.main.add(this.body);
		this.main.doLayout();
	}
});
