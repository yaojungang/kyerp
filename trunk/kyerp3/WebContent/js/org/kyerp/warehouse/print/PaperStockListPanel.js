/** ***************************************************************************** */
org.kyerp.warehouse.print.PaperStockListGrid = Ext.extend(Ext.grid.EditorGridPanel, {
	expander : null,
	stockDetailWin : null,
	inOutDetailWin : null,
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this["store"] = org.kyerp.warehouse.print.PaperStockStore;
		this.stockDetailWin = new org.kyerp.warehouse.StockDetailWindow();
		this.inOutDetailWin = new org.kyerp.warehouse.InventoryDetailWindow();
		org.kyerp.warehouse.print.PaperStockListGrid.superclass.constructor.call(this, {
			stripeRows : true,
			viewConfig : {
				forceFit : true
			},
			tbar : [ {
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
					select : function(comboBox,record,index) {
						var store = org.kyerp.warehouse.print.PaperStockStore;
						store.setBaseParam("ownerId", record.data.id);
						store.load( {
							params : {
								start : 0,
								limit : 20
							}
						});
						var store1 = org.kyerp.warehouse.InventoryDetailStroe;
						store1.removeAll();
						//store1.setBaseParam("ownerId", record.data.id);
					},
					scope : this
				}
			}, "-", {
				text : "库存明细",
				iconCls : 'icon-utils-s-view',
				handler : function() {
					this.stockDetailWin.show();
				},
				scope : this
			}, "-", {
				text : "出入明细",
				iconCls : 'icon-utils-s-view',
				handler : function() {
					var data = this.getSelected().data;
					var store = org.kyerp.warehouse.InventoryDetailStroe;
					store.setBaseParam("materialId", data.materialId);
					store.load( {
						params : {
							start : 0,
							limit : 20
						}
					});
					this.inOutDetailWin.setTitle(data.materialName + "-出入明细");
					this.inOutDetailWin.show();
				},
				scope : this
			}, "->", {
				text : "导  出",
				iconCls : 'page_excel',
				handler : function() {
					window.open(org.kyerp.warehouse.print.PaperStock_EXCEL_URL);
				},
				scope : this
			}, "-", '物料分类：', {
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
					var store = org.kyerp.warehouse.print.PaperStockStore;
					store.setBaseParam("mCategoryId", node.id);
					store.load();
				}
			}, "-", "搜索：", new Ext.ux.form.SearchField( {
				store : this.getStore()
			}) ],
			enableColumnMove : false,
			plugins : this.expander,
			colModel : new Ext.grid.ColumnModel( [ new Ext.grid.RowNumberer(),
					{
						header : "物料名称",
						dataIndex : "materialName",
						width : 150,
						menuDisabled : true
					}, {
						header : "单位",
						dataIndex : "unitName",
						align : 'center',
						width : 40,
						menuDisabled : true
					}, {
						header : "数量",
						dataIndex : "totalAmount",
						renderer : function(v) {
							if (v < 0) {
								return "<font color=red>" + v + "</font>";
							}
							return v;
						},
						align : 'right',
						width : 60,
						menuDisabled : true
					}, {
						header : "价格",
						dataIndex : "price",
						renderer : 'usMoney',
						align : 'right',
						width : 60,
						menuDisabled : true
					}, {
						header : "金额",
						dataIndex : "cost",
						renderer : 'usMoney',
						align : 'right',
						width : 60,
						menuDisabled : true
					}, {
						header : '所有者',
						dataIndex : 'ownerName',
						width : 60
					}, {
						header : '备注',
						dataIndex : 'remark',
						width : 150,
						editor : new Ext.form.TextArea()
					} ]),
			selModel : new Ext.grid.RowSelectionModel( {
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
		this.on("rowselect", this.onRowSelect, this);
		this.on("rowdblclick", this.onRowdblclick, this);
		this.on("afteredit", function(e) {
			Ext.Ajax.request( {
				url : org.kyerp.warehouse.print.PaperStock_SAVE_URL,
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
	onRowdblclick : function() {
		this.stockDetailWin.show();
	},
	getSelected : function(_grid) {
		var _sm = this.getSelectionModel();
		if (_sm.getCount() == 0)
			throw Error("你尚未选定一条记录");
		return _sm.getSelected();
	},
	onRowSelect : function(_sel, _index, _r) {
		// org.kyerp.warehouse.StockDetailStore.loadData(Ext
		// .decode(_sel.data.details), false);
		var data = _sel.data;
		var store = org.kyerp.warehouse.StockDetailStore;
		store.setBaseParam("stockId", data.id);
		store.load( {
			params : {
				start : 0,
				limit : 20
			}
		});
		this.stockDetailWin.setTitle(_sel.data.materialName + "-库存明细");
	}
});
/** ***************************************************************************** */
org.kyerp.warehouse.print.PaperStockListPanel = Ext.extend(Ext.Panel, {
	store : org.kyerp.warehouse.print.PaperStockStore,
	layout : 'border',
	stockListGrid : null,
	stockDetailGrid : null,
	border : false,
	initComponent : function() {
		this.stockListGrid = new org.kyerp.warehouse.print.PaperStockListGrid( {
			region : 'center',
			autoWidth : true,
			border : false
		});
		this.stockDetailGrid = new org.kyerp.warehouse.StockDetailGrid( {
			height : 80,
			region : 'south',
			split : true,
			border : false
		});
		this.items = [ this.stockListGrid, this.stockDetailGrid ];
		org.kyerp.warehouse.print.PaperStockListPanel.superclass.initComponent.call(this);
	}
});

/** ***************************************************************************** */
