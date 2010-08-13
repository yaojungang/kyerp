/** ***************************************************************************** */
org.kyerp.warehouse.InventoryDetailWindow = Ext.extend(Ext.Window, {
	grid : null,
	store : null,
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.store = org.kyerp.warehouse.InventoryDetailStroe;
		this.grid = new Ext.grid.EditorGridPanel( {
			border : false,
			region : 'center',
			store : this.store,
			tbar : [{
				text : "所有者"
			}, {
				xtype : 'combo',
				emptyText : '所有者',
				store : org.kyerp.warehouse.InventoryOwnerStore,
				mode : 'local',
				displayField : 'name',
				valueField : 'id',
				hiddenName : 'ownerId',
				triggerAction : 'all',
				width : 80,
				listeners : {
					select : function(comboBox) {
						var value = comboBox.getValue();
						var store1 = org.kyerp.warehouse.InventoryDetailStroe;
						store1.setBaseParam("ownerId", value);
					},
					scope : this
				}
			}, "-", "批次号:", {
				xtype : 'textfield',
				name : 'batchNumber'
			}, '-', "日期:", {
				xtype : 'datefield',
				name : 'startDate',
				format:'Y-m-d'
			}, "到", {
				xtype : 'datefield',
				name : 'endDate',
				format:'Y-m-d'
			}, '-', {
				type : 'submit',
				text : '查询',
				iconCls : 'icon-order-s-search',
				handler : function() {
					var store = org.kyerp.warehouse.InventoryDetailStroe;
					var batchNumber = this.grid.getTopToolbar().find('name','batchNumber')[0].getValue();
					var startDate = this.grid.getTopToolbar().find('name','startDate')[0].getValue();
					if(startDate){
						startDate = new Date(startDate).format('Y-m-d');
					}
					var endDate =this.grid.getTopToolbar().find('name','endDate')[0].getValue();
					if(endDate){
						endDate = new Date(endDate).format('Y-m-d');
					}
					//if(batchNumber.length > 0){
						store.setBaseParam("batchNumber", batchNumber);
					//}
					//if(endDate){
						store.setBaseParam("startDate", startDate);
					//}
					//if(endDate){
						store.setBaseParam("endDate", endDate);
					//}
					store.load( {
						params : {
						start : 0,
						limit : 20
						}
					});
				},scope : this
			} ],
			selModel : new Ext.grid.RowSelectionModel(),
			columns : [ new Ext.grid.RowNumberer(), {
				header : '时间',
				width : 80,
				dataIndex : 'createTime',
				renderer : Ext.util.Format.dateRenderer('Y-m-d')
			}, {
				header : '所有者',
				width : 60,
				dataIndex : 'ownerName'
			}, {
				header : '收发类型',
				width : 60,
				dataIndex : 'inOutType'
			}, {
				header : '批次号',
				width : 80,
				dataIndex : 'batchNumber'
			}, {
				header : '期初余额',
				width : 60,
				dataIndex : 'begingStockCount',
				align : 'right'
			}, {
				header : '入库数量',
				width : 60,
				dataIndex : 'inStockCount',
				align : 'right'
			}, {
				header : '出库数量',
				width : 60,
				dataIndex : 'outStockCount',
				align : 'right'
			}, {
				header : '当前余额',
				width : 60,
				dataIndex : 'currentStockCount',
				align : 'right'
			}, {
				header : '任务单号',
				width : 80,
				dataIndex : 'pressworkNo'
			}, {
				header : '单据号',
				width : 80,
				dataIndex : 'serialNumber'
			}, {
				header : '单位',
				width : 60,
				dataIndex : 'unitName',
				align : 'center'
			}, {
				header : '仓库',
				width : 60,
				dataIndex : 'warehouseName'
			}, {
				header : '单价',
				width : 60,
				dataIndex : 'price',
				renderer : 'usMoney',
				align : 'right'
			}, {
				header : '金额',
				width : 60,
				dataIndex : 'cost',
				renderer : 'usMoney',
				align : 'right'
			} ]
		});
		org.kyerp.warehouse.InventoryDetailWindow.superclass.constructor.call(
				this, {
					title : "查看",
					width : 780,
					height : 480,
					layout : 'border',
					border : false,
					iconCls : 'icon-utils-s-view',
					closeAction : 'hide',
					items : [ this.grid ],
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
	}
});