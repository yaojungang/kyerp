/** ***************************************************************************** */
org.kyerp.warehouse.StockDetailGrid = Ext.extend(Ext.grid.EditorGridPanel, {
			constructor : function(_cfg) {
				Ext.apply(this, _cfg);
				this.store = org.kyerp.warehouse.StockDetailStore;
				org.kyerp.warehouse.StockDetailGrid.superclass.constructor.call(this, {
							stripeRows : true,
							viewConfig : {
								forceFit : true
							},
							store : this.store,
							colModel : new Ext.grid.ColumnModel([
							new Ext.grid.RowNumberer(), {
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
									}, {
										header : '备注',
										dataIndex : 'remark',
										width : 200,
										editor : new Ext.form.TextArea()
									}]),
							selModel : new Ext.grid.RowSelectionModel({singleSelect : true}),
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
				this.on("afteredit", function(e) {
							Ext.Ajax.request({
										url : org.kyerp.warehouse.StockDetail_SAVE_URL,
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