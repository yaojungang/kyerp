/** ***************************************************************************** */
org.kyerp.warehouse.InStockDetailGrid = Ext.extend(Ext.grid.EditorGridPanel, {
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.store = org.kyerp.warehouse.InStockDetailStore;
		org.kyerp.warehouse.InStockDetailGrid.superclass.constructor.call(this,
				{
					stripeRows : true,
					viewConfig : {
						forceFit : true
					},
					colModel : new Ext.grid.ColumnModel([
							new Ext.grid.RowNumberer(), {
								header : '品名型号',
								width : 250,
								dataIndex : "materialName"
							}, {
								header : '库房',
								width : 80,
								dataIndex : "warehouseName"
							}, {
								header : '单位',
								width : 40,
								dataIndex : "unitName"
							}, {
								header : "数量",
								width : 70,
								dataIndex : "billCount"
							}, {
								header : "单价",
								width : 70,
								dataIndex : "price"
							}, {
								header : "金额",
								width : 80,
								dataIndex : "billCost"
							}, {
								header : "批次号",
								width : 100,
								dataIndex : 'batchNumber'
							}, {
								header : '备注',
								dataIndex : "remark",
								editor : new Ext.form.TextArea()
							}]),
					bbar : new Ext.PagingToolbar({
                                hide : true,
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
		this.on("afteredit", function(e) {
					Ext.Ajax.request({
								url : org.kyerp.warehouse.InStockDetail_SAVE_URL,
								params : {
									id : e.record.data.id,
									remark : e.record.data.remark
								},
								method : 'POST',
								success : function() {
									e.record.commit(false);
								}
							});
				});
	}
});