/** ***************************************************************************** */
org.kyerp.warehouse.OutStockDetailGrid = Ext.extend(Ext.grid.EditorGridPanel, {
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.store = org.kyerp.warehouse.OutStockDetailStore;
		org.kyerp.warehouse.OutStockDetailGrid.superclass.constructor.call(this,
				{
					stripeRows : true,
					viewConfig : {
						forceFit : true
					},
					colModel : new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
                                        header : '所有者',
                                        width : 60,
                                        dataIndex : "ownerName"
                                    }, {
                                        header : '品名型号',
                                        width : 250,
                                        dataIndex : "materialName"
                                    }, {
                                        header : "批次号",
                                        width : 190,
                                        dataIndex : 'batchNumber'
                                    }, {
                                        header : "理论数量",
                                        width : 70,
                                        dataIndex : "outStockCount"
                                    }, {
                                        header : "实际数量",
                                        width : 70,
                                        dataIndex : "realOutStockCount"
                                    }, {
                                        header : '生产任务单号',
                                        dataIndex : "pressworkNo"
                                    }, {
                                        header : '库房',
                                        width : 80,
                                        dataIndex : "warehouseName"
                                    }, {
                                        header : '单位',
                                        width : 40,
                                        dataIndex : "unitName"
                                    }, {
                                        header : "单价",
                                        width : 50,
                                        dataIndex : "price"
                                    }, {
                                        header : '备注',
                                        dataIndex : "remark"
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
								url : org.kyerp.warehouse.OutStockDetail_SAVE_URL,
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