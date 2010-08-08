/** ***************************************************************************** */
org.kyerp.warehouse.StockDetailStore = new Ext.data.Store( {
	url : org.kyerp.warehouse.StockDetail_URL,
	reader : new Ext.data.JsonReader( {
		totalProperty : "totalProperty",
		root : "rows",
		idProperty : "id"
	}, new Ext.data.Record.create( [ {
		name : "id",
		type : "int"
	}, {
		name : "createTime",
		type : "date",
		dateFormat : "Y-m-d H:i:s"
	}, {
		name : "updateTime",
		type : "date",
		dateFormat : "Y-m-d H:i:s"
	}, {
		name : 'batchNumber',
		type : 'string'
	}, {
		name : 'warehouseName',
		type : 'string'
	}, {
		name : 'batchNumber',
		type : 'string'
	}, {
		name : 'amount',
		type : 'float'
	}, {
		name : 'unitName',
		type : 'string'
	}, {
		name : 'price',
		type : 'float'
	}, {
		name : 'cost',
		type : 'float'
	}, {
		name : 'remark',
		type : 'string'
	} ]))
});
/** ***************************************************************************** */
org.kyerp.warehouse.StockDetailWindow = Ext.extend(Ext.Window, {
	grid : null,
	store : null,
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.store = org.kyerp.warehouse.StockDetailStore;
		this.grid = new Ext.grid.EditorGridPanel( {
			border : false,
			region : 'center',
			store : this.store,
			selModel : new Ext.grid.RowSelectionModel(),
			columns : [ new Ext.grid.RowNumberer(), {
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
				dataIndex : 'price',
				renderer : 'usMoney',
				align : 'right'
			}, {
				header : '数量',
				dataIndex : 'amount',
				align : 'right'
			}, {
				header : '金额',
				dataIndex : 'cost',
				renderer : 'usMoney',
				align : 'right'
			} ],
			bbar : new Ext.PagingToolbar( {
				plugins : new Ext.ux.Andrie.pPageSize( {
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
		org.kyerp.warehouse.StockDetailWindow.superclass.constructor.call(this,
				{
					title : "查看",
					width : 700,
					height : 250,
					layout : 'border',
					border : false,
					iconCls : 'icon-utils-s-view',
					closeAction : 'hide',
					items : [ this.grid ]
				});
	}
});