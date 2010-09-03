/** ***************************************************************************** */
org.kyerp.warehouse.WarehouseStore = new Ext.data.Store({
            autoLoad : {
                baseParams : {
                    limit : 20
                }
            },
            url : org.kyerp.warehouse.Warehouse_ALL_LIST_URL,
            reader : new Ext.data.JsonReader({
                        totalProperty : "totalProperty",
                        root : "rows",
                        id : "id"
                    }, ['id', 'createTime', 'updateTime', 'name',
                            'serialNumber', 'note', 'childWarehouseIds',
                            'childWarehouseNames', 'parentWarehouseId',
                            'parentWarehouseName'])
});
/** ***************************************************************************** */
org.kyerp.warehouse.WarehouseTree = new Ext.tree.TreePanel({
	title : '仓库',
	loader : new Ext.tree.TreeLoader({
				dataUrl : org.kyerp.warehouse.WarehousePanel_TREE_URL
			}),
	root : {
		nodeType : 'async',
		id : 'root',
		text : '仓库',
		expanded : true
	},
	tools : [{
				id : 'refresh',
				qtip : '刷新',
				handler : function() {
					org.kyerp.warehouse.WarehouseTree.getRootNode().reload();
				}
			}],
	rootVisible : false,
	border : false,
	autoScroll : true,
	listeners : {
		load : function(node) {
			node.select();
		}
	}
});
/** ***************************************************************************** */
org.kyerp.warehouse.WarehouseGrid = new Ext.grid.EditorGridPanel({
			title : '仓库资料',
			store : org.kyerp.warehouse.WarehouseStore,
			colModel : new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
                        header : '编码',
                        width : 100,
                        sortable : true,
                        dataIndex : 'serialNumber',
                        editor : new Ext.form.TextField()
                    }, {
                        header : '名称',
                        width : 150,
                        sortable : true,
                        dataIndex : 'name',
                        editor : new Ext.form.TextField()
                    }, {
                        header : '说明',
                        width : 300,
                        sortable : true,
                        dataIndex : 'note',
                        editor : new Ext.form.TextField()
                    }]),
			border : false,
			selModel : new Ext.grid.RowSelectionModel(),
			bbar : new Ext.PagingToolbar({
						plugins : new Ext.ux.Andrie.pPageSize({
									beforeText : '每页显示',
									afterText : '条'
								}),
						pageSize : 20,
						store : org.kyerp.warehouse.WarehouseStore,
						displayInfo : true,
						displayMsg : '显示  {0} - {1} 条记录,共有 {2} 条记录',
						emptyMsg : "没有数据"
					}),
			loadMask : {
				msg : '正在载入数据,请稍等...'
			},
			getGridSelected : function(_grid) {
				var _sm = this.getSelectionModel();
				if (_sm.getCount() == 0)
					throw Error("你尚未选定一条记录");
				return _sm.getSelected();
			}
		});
/** ***************************************************************************** */
org.kyerp.warehouse.WarehousePanel = Ext.extend(Ext.Panel, {
	tree : null,
	grid : null,
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.tree = org.kyerp.warehouse.WarehouseTree;
		this.grid = org.kyerp.warehouse.WarehouseGrid;
		org.kyerp.warehouse.WarehousePanel.superclass.constructor.call(this,
				{
					layout : 'border',
					border : false,
					defaults : {
						split : true
					},
					items : [{
						region : 'north',
						border : false,
						tbar : [{
									text : '新增',
									iconCls : 'icon-utils-s-add',
									handler : function() {
										this.createWarehouse();
									},
									scope : this
								}, '-', {
									text : '删除',
									iconCls : 'icon-utils-s-delete',
									handler : function() {
										Ext.Msg.confirm("系统提示", "你确定删除此记录吗?",
												this.onDeleteWarehouseClick,
												this);
									},
									scope : this
								}, '->', '双击表格可以修改仓库资料']
					}, {
						region : 'west',
						layout : 'fit',
						width : 180,
						split : true,
						// collapsible : true,
						collapseMode : 'mini',
						autoScroll : true,
						border : false,
						items : this.tree
					}, {
						region : 'center',
						layout : 'fit',
						border : false,
						items : this.grid
					}]
				});
		// 点击tree改变List的内容
		this.tree.on("click", function(node) {
					node.expand();
					node.select();
					var store = org.kyerp.warehouse.WarehouseStore;
					store.setBaseParam("parentId", node.id);
					store.load();
				});
		this.grid.on('afteredit', function(e) {
					// alert(Ext.encode(e.record.data));
					Ext.Ajax.request({
								url : org.kyerp.warehouse.WarehousePanel_SAVE_URL,
								params : {
									id : e.record.data.id,
									name : e.record.data.name,
									note : e.record.data.note,
									serialNumber : e.record.data.serialNumber
								},
								method : 'POST',
								success : function() {
									e.record.commit(false);
									var node = org.kyerp.warehouse.WarehouseTree
											.getSelectionModel()
											.getSelectedNode().findChild('id',
													e.record.data.id);
									if (node) {
										node.setText(e.record.data.name);
									}
								}
							});
				});
	},
	createWarehouse : function() {
		var node = this.tree.getSelectionModel().getSelectedNode();
		if (node) {
			Ext.Ajax.request({
						url : org.kyerp.warehouse.WarehousePanel_SAVE_URL,
						params : {
							parentWarehouseId : node.id,
							name : '新仓库'
						},
						success : function(response) {
							var data = Ext.decode(response.responseText);
							if (data.success) {
								node.appendChild(new Ext.tree.TreeNode({
											id : data.id,
											text : data.supplierTypeExtGridRow.name,
											leaf : true
										}));
								node.getUI().removeClass('x-tree-node-leaf');
								node.getUI().addClass('x-tree-node-expanded');
								node.expand();
								org.kyerp.warehouse.WarehouseGrid.getStore()
										.reload();
							} else {
								Ext.MessageBox.alert('警告', data.msg);
							}
						}
					})
		}

	},
	onDeleteWarehouseClick : function(_btn) {
		if (_btn == "yes") {
			this.onDeleteWarehouse();
		}
	},

	onDeleteWarehouse : function() {
		try {
			var id;
			// var rec = this.grid.getSelectionModel().getSelected();
			var rec = this.grid.getGridSelected()
			if (rec) {
				id = rec.data.id;
			} else {
				var node = this.tree.getSelectionModel().getSelectedNode();
				id = node.id;
			};
			Ext.Ajax.request({
						url : org.kyerp.warehouse.WarehousePanel_DELETE_URL
								+ "?ids=" + id,
						method : 'POST',
						success : function(response) {
							var data = Ext.decode(response.responseText);
							if (data.success) {
								var snode = org.kyerp.warehouse.WarehouseTree
										.getSelectionModel().getSelectedNode();
								if (snode.id == id) { // 当前树节点是要删除的节点
									snode.remove();
								} else {
									var node = snode.findChild('id', id);
									node.remove();
								}
								org.kyerp.warehouse.WarehouseGrid.getStore()
										.reload();
								Ext.MessageBox.alert('警告', '删除仓库资料完成。');
							} else {
								Ext.MessageBox.alert('警告', data.msg);
							}
						}
					})
		} catch (_err) {
			Ext.Msg.alert("删除失败", _err);
		}
	}
});
/** ***************************************************************************** */
Ext.extend(org.kyerp.module, {
			init : function() {
				this.body = new org.kyerp.warehouse.WarehousePanel({
							border : false,
							bodyBorder : false
						});
				this.main.add(this.body);
				this.main.doLayout();
			}
		});