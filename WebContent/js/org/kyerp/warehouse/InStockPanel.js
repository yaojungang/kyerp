/** ***************************************************************************** */
org.kyerp.warehouse.InStockStoe = new Ext.data.Store({
					autoLoad : {
						baseParams : {
							limit : 20
						}
					},
					listeners : {
						loadexception : function(proxy, options, response) {
							var data = Ext.decode(response.responseText);
							top.Ext.Msg.alert("错误", "载入数据时发生错误:"
											+ data["exception.message"]);
						}
					},
					url : org.kyerp.warehouse.InStock_STORE_URL,
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
										name : "statusString",
										type : "string"
									}, {
										name : "writeDate",
										type : "date",
										dateFormat : "Y-m-d H:i:s"
									}, {
										name : "arriveDate",
										type : "date",
										dateFormat : "Y-m-d"
									}, {
										name : "billCount"
									}, {
										name : "billCost",
										type : "float",
										convert : function(value) {
											return "￥" + value + "元"
										}
									}, {
										name : "checkUserId",
										type : "int"
									}, {
										name : "checkUserName",
										type : "string"
									}, {
										name : "checkEmployeeId",
										type : "int"
									}, {
										name : "checkEmployeeName",
										type : "string"
									}, {
										name : "writeUserId",
										type : "int"
									}, {
										name : "writeUserName",
										type : "string"
									}, {
										name : "writeEmployeeId",
										type : "int"
									}, {
										name : "writeEmployeeName",
										type : "string"
									}, {
										name : "details"
									}, {
										name : "supplierId",
										type : "int"
									}, {
										name : "supplierName",
										type : "string"
									}, {
										name : "inOutTypeId"
									}, {
										name : "inOutTypeName"
									}, {
										name : "remark",
										type : "string"
									}, {
										name : "editAble"
									}, {
										name : 'keeperId',
										type : "int"
									}, {
										name : "keeperName",
										type : "string"
									}]))
				});

/** ***************************************************************************** */
org.kyerp.warehouse.InStockStoeDetainStore = new Ext.data.Store({
										reader : new Ext.data.JsonReader({},
												new Ext.data.Record.create([{
															name : "id",
															type : "int"
														}, {
															name : "billCount"
														}, {
															name : "billCost"
														}, {
															name : "materialId",
															type : "int"
														}, {
															name : "materialName",
															type : "string"
														}, {
															name : 'batchNumber',
															type : 'string'
														}, {
															name : "warehouseId",
															type : "int"
														}, {
															name : "warehouseName",
															type : "string"
														}, {
															name : "unitId",
															type : "int"
														}, {
															name : "unitName",
															type : "string"
														}, {
															name : "price"
														}, {
															name : "remark",
															type : "string"
														}]))
									});
/** ***************************************************************************** */
org.kyerp.warehouse.InStockDetailGrid = Ext.extend(Ext.grid.EditorGridPanel, {
	initComponent : function(_cfg) {
		Ext.apply(this, _cfg);
		this.store = org.kyerp.warehouse.InStockStoeDetainStore;
		this.columns = [new Ext.grid.RowNumberer(), {
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
							}];
		org.kyerp.warehouse.InStockDetailGrid.superclass.initComponent.call(this);
		this.on("afteredit",function(e){
					//alert(Ext.encode(e.record.data));
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
/** ***************************************************************************** */
org.kyerp.warehouse.InStockPanel = Ext.extend(Ext.Panel, {
			store : org.kyerp.warehouse.InStockStoe,
			layout : 'border',
			inStockListGrid : null,
			inStockDetailGrid : null,
			border : false,
			initComponent : function() {
				this.inStockListGrid = new org.kyerp.warehouse.InStockListGrid({
							region : 'center',
							autoWidth : true,
							border : false
						});
				this.inStockDetailGrid = new org.kyerp.warehouse.InStockDetailGrid(
						{
							height : 80,
							region : 'south',
							split : true,
							border : false
						});
				this.items = [this.inStockListGrid,this.inStockDetailGrid];
				org.kyerp.warehouse.InStockPanel.superclass.initComponent
						.call(this);
			}
		});


/** ***************************************************************************** */
Ext.extend(org.kyerp.module, {
			init : function() {
				require('SelectSupplierWindow.js;SelectMaterialWindow.js;InStockListGrid.js;'
								+ 'InStockItemsEditorGridPanel.js', {
							basedir : 'js/org/kyerp/warehouse'
						});
				// require('js/org/kyerp/warehouse/SelectSupplierWindow.js');
				this.body = new org.kyerp.warehouse.InStockPanel({
							border : false,
							bodyBorder : false
						});
				this.main.add(this.body);
				this.main.doLayout();
			}
		});