/** ***************************************************************************** */
org.kyerp.warehouse.StockStore = new Ext.data.Store({
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
/** ***************************************************************************** */
org.kyerp.warehouse.StockDetailStore = new Ext.data.Store({
					reader : new Ext.data.JsonReader({},
							new Ext.data.Record.create(['id', 'createTime',
									'updateTime', 'batchNumber',
									'warehouseName', 'amount', 'unitName',
									'price', 'cost','remark']))
				});
/** ***************************************************************************** */
org.kyerp.warehouse.StockListPanelViewWindow = Ext.extend(Ext.Window, {
			grid : null,
			store : null,
			constructor : function(_cfg) {
				Ext.apply(this, _cfg);
				this.store = org.kyerp.warehouse.StockDetailStore;
				this.grid = new Ext.grid.EditorGridPanel({
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
org.kyerp.warehouse.StockListGrid = Ext.extend(Ext.grid.EditorGridPanel, {
	expander : null,
	viewWin : null,
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this["store"] = org.kyerp.warehouse.StockStore;
		this.viewWin = new org.kyerp.warehouse.StockListPanelViewWindow();
		org.kyerp.warehouse.StockListGrid.superclass.constructor.call(this, {
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
					},{
						text : "导  出",
						iconCls : 'page_excel',
						handler : function() {
							window.open(org.kyerp.warehouse.Stock_EXCEL_URL);
						},
						scope : this
					}, '->', '物料分类：', {
						xtype : 'treecombobox',
						fieldLabel : '物料分类',
						name : 'mCategoryId',
						hiddenName : 'mCategoryId',
						editable : false,
						mode : 'local',
						displayField : 'name',
						valueField : 'id',
						triggerAction : 'all',
						allowBlank : false,
						rootText : 'root',
						rootId : '0',
						forceSelection : true,
						rootVisible : false,
						treeUrl : org.kyerp.warehouse.MaterialCategoryPanel_TREE_URL,
						onSelect : function(node) {
							var store = org.kyerp.warehouse.StockStore;
							store.setBaseParam("mCategoryId", node.id);
							store.load();
						}
					}, "-", "搜索：", new Ext.ux.form.SearchField({
								store : this.getStore()
							})],
			enableColumnMove : false,
			plugins : this.expander,
			colModel : new Ext.grid.ColumnModel([
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
					}, {
					header : '备注',
					dataIndex : 'remark',
					width : 200,
					editor: new Ext.form.TextArea()
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
		this.on("afteredit",function(e){
					Ext.Ajax.request({
								url : org.kyerp.warehouse.Stock_SAVE_URL,
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
	},
	getSelected : function(_grid) {
		var _sm = this.getSelectionModel();
		if (_sm.getCount() == 0)
			throw Error("你尚未选定一条记录");
		return _sm.getSelected();
	},
	onRowSelect : function(_sel, _index, _r) {
		org.kyerp.warehouse.StockDetailStore.loadData(Ext.decode(_sel.data.details), false);
		this.viewWin.setTitle(_sel.data.materialName);
	}
});
/** ***************************************************************************** */
org.kyerp.warehouse.StockDetailGrid = Ext.extend(Ext.grid.EditorGridPanel, {
	initComponent : function(_cfg) {
		Ext.apply(this, _cfg);
		this.store = org.kyerp.warehouse.StockDetailStore;
		this.columns = [new Ext.grid.RowNumberer(), {
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
				}, {
					header : '备注',
					dataIndex : 'remark',
					width : 200,
					editor: new Ext.form.TextArea()
				}];
		org.kyerp.warehouse.StockDetailGrid.superclass.initComponent.call(this);
		this.on("afteredit",function(e){
					// alert(Ext.encode(e.record.data));
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
/** ***************************************************************************** */
org.kyerp.warehouse.StockListPanel = Ext.extend(Ext.Panel, {
			store : org.kyerp.warehouse.StockStore,
			layout : 'border',
			stockListGrid : null,
			stockDetailGrid : null,
			border : false,
			initComponent : function() {
				this.stockListGrid = new org.kyerp.warehouse.StockListGrid({
							region : 'center',
							autoWidth : true,
							border : false
						});
				this.stockDetailGrid = new org.kyerp.warehouse.StockDetailGrid(
						{
							height : 80,
							region : 'south',
							split : true,
							border : false
						});
				this.items = [this.stockListGrid,this.stockDetailGrid];
				org.kyerp.warehouse.StockListPanel.superclass.initComponent
						.call(this);
			}
		});

/** ***************************************************************************** */
