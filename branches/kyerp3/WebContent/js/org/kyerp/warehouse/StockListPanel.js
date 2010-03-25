/** ***************************************************************************** */
org.kyerp.warehouse.StockListPanelViewWindow = Ext.extend(Ext.Window, {
	grid : null,
	store : null,
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.store = new Ext.data.Store({
					reader : new Ext.data.JsonReader(
							{},
							new Ext.data.Record.create(['id', 'createTime',
									'updateTime', 'batchNumber',
									'warehouseName', 'amount','unitName', 'price', 'cost']))
				});
		this.grid = new Ext.grid.GridPanel({
					border : false,
					region : 'center',
					store : this.store,
					selModel : new Ext.grid.RowSelectionModel(),
					columns : [new Ext.grid.RowNumberer(), {
								header : '批次号',
								width : 100,
								dataIndex : 'batchNumber'
							}, {
								header : '仓库',
								width : 100,
								dataIndex : 'warehouseName'
							}, {
								header : '单位',
								dataIndex : 'unitName'
							}, {
								header : '单价',
								dataIndex : 'price'
							}, {
								header : '数量',
								dataIndex : 'amount'
							}, {
								header : '金额',
								dataIndex : 'cost'
							}]
				});
		org.kyerp.warehouse.StockListPanelViewWindow.superclass.constructor
				.call(this, {
							title : "查看",
							width : 700,
							height : 250,
							layout : 'border',
							border : false,
							iconCls : 'icon-utils-s-view',
							closeAction : 'hide',
							items : [this.grid]
						});
	}
});
/** ***************************************************************************** */
org.kyerp.warehouse.StockListPanel = Ext.extend(Ext.grid.GridPanel, {
	expander : null,
	viewWin : null,
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.expander = new Ext.ux.grid.RowExpander({
					lazyRender : true,
					tpl : new Ext.XTemplate('<p><b>明细:</b></p>',
							'<tpl for=".">', '{materialName}=={details}',
							'<tpl for="details">', '{materialName}', '</tpl>',
							'</tpl>')
				});
		this["store"] = new Ext.data.Store({
					autoLoad : {
						baseParams : {
							limit : 20
						}
					},
					url : org.kyerp.warehouse.StockListPanel_STORE_URL,
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
										name : "updateTime",
										type : "date",
										dateFormat : "Y-m-d H:i:s"
									}, {
										name : "totalAmount"
									}, {
										name : "materialId",
										type : "int"
									}, {
										name : "materialName",
										type : "string"
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
										name : 'cost'
									}, {
										name : "details"
									}]))
				});
		this.viewWin = new org.kyerp.warehouse.StockListPanelViewWindow();
		org.kyerp.warehouse.StockListPanel.superclass.constructor.call(
				this, {
					stripeRows : true,
					viewConfig : {
						forceFit : true
					},
					tbar : [{
								text : "查  看",
								iconCls : 'icon-utils-s-view',
								handler : function() {
									this.viewWin.show();
								},
								scope : this
							}],
					enableColumnMove : false,
					plugins : this.expander,
					colModel : new Ext.grid.ColumnModel([this.expander,
							new Ext.grid.RowNumberer(), {
								header : "物料名称",
								dataIndex : "materialName",
								width : 150,
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
								header : "数量",
								dataIndex : "totalAmount",
								width : 60,
								menuDisabled : true
							}, {
								header : "金额",
								dataIndex : "cost",
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
		// this.addEvents("rowselect");
		this.on("rowselect", this.onRowSelect, this);
	},
	getSelected : function(_grid) {
		var _sm = this.getSelectionModel();
		if (_sm.getCount() == 0)
			throw Error("你尚未选定一条记录");
		return _sm.getSelected();
	},
	onRowSelect : function(_sel, _index, _r) {
		this.viewWin.store.loadData(Ext.decode(_sel.data.details), false);
		this.viewWin.setTitle(_sel.data.materialName);
	}
});
/** ***************************************************************************** */
