org.kyerp.warehouse.materialStore = new Ext.data.Store({
			autoLoad : {
				baseParams : {
					limit : 20
				}
			},
			url : org.kyerp.warehouse.MaterialListPanel_STORE_URL,
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
								name : "name",
								type : "string"
							}, {
								name : "amount",
								type : "int"
							}, {
								name : "materialCategoryId",
								type : "int"
							}, {
								name : "materialCategoryName",
								type : "string"
							}, {
								name : 'specification',
								type : 'string'
							}, {
								name : 'brandId',
								type : 'int'
							}, {
								name : 'brandName',
								type : 'string'
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
								name : 'supplierId',
								type : 'int'
							}, {
								name : 'supplierName',
								type : 'string'
							}, {
								name : 'warehouseId',
								type : 'int'
							}, {
								name : 'warehouseName',
								type : 'string'
							}]))
		});

/** ***************************************************************************** */
org.kyerp.warehouse.SelectMaterialWindow = Ext.extend(Ext.Window, {
			constructor : function(_cfg) {
				if (_cfg == null)
					_cfg = {};
				Ext.apply(this, _cfg);
				this.grid = new Ext.grid.GridPanel({
							store : org.kyerp.warehouse.materialStore,
							columns : [new Ext.grid.RowNumberer(), {
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
										header : "名称",
										dataIndex : "name",
										width : 150,
										menuDisabled : true
									}, {
										header : "规格",
										dataIndex : "specification",
										width : 100,
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
										header : "物料类别",
										dataIndex : "materialCategoryName",
										width : 80,
										menuDisabled : true
									}, {
										header : "品牌",
										dataIndex : "brandName",
										width : 60,
										menuDisabled : true
									}, {
										header : "供应商",
										dataIndex : "supplierName",
										width : 80,
										menuDisabled : true
									}, {
										header : "默认仓库",
										dataIndex : "warehouseName",
										width : 80,
										menuDisabled : true
									}],
							bbar : new Ext.PagingToolbar({
										pageSize : 20,
										store : org.kyerp.warehouse.materialStore,
										displayInfo : true
									}),
							border : false
						});
				org.kyerp.warehouse.SelectMaterialWindow.superclass.constructor
						.call(this, {
									title : '选择物料',
									width : 600,
									height : 400,
									closeAction : 'hide',
									modal : true,
									layout : 'fit',
									items : this.grid,
									buttons : [{
										text : '确定',
										handler : function() {
											var rec = this.grid
													.getSelectionModel()
													.getSelected();
											if (rec && _cfg['onSelect']) {
												_cfg['onSelect'](rec, this);
											}
										},
										scope : this
									}, {
										text : '取消',
										handler : function() {
											this.hide()
										},
										scope : this
									}]
								});
			}
		});