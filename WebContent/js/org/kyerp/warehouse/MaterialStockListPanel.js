/** ***************************************************************************** */
org.kyerp.warehouse.MaterialStockListPanel = Ext.extend(Ext.grid.GridPanel, {
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this["store"] = new Ext.data.Store({
					autoLoad : {
						baseParams : {
							limit : 20
						}
					},
					url : org.kyerp.warehouse.MaterialStockListPanel_STORE_URL,
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
										name : "amount",
										type : "int"
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
										name : 'cost',
										type : 'string'
									}, {
										name : 'warehouseId',
										type : 'int'
									}, {
										name : 'warehouseName',
										type : 'string'
									}]))
				});
		org.kyerp.warehouse.MaterialStockListPanel.superclass.constructor.call(this,
				{
					stripeRows : true,
					tbar : [{
								text : "查  看",
								iconCls : 'icon-utils-s-view',
								handler : function() {
									this.viewWin.show();
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
								header : "库存数量",
								dataIndex : "amount",
								width : 60,
								menuDisabled : true
							}, {
								header : "仓库",
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
		this.addEvents("rowselect");
	},
	getSelected : function(_grid) {
		var _sm = this.getSelectionModel();
		if (_sm.getCount() == 0)
			throw Error("你尚未选定一条记录");
		return _sm.getSelected();
	},
	onRowSelect : function(_sel, _index, _r) {
		this.fireEvent("rowselect", _r);
	}
});
/** ***************************************************************************** */