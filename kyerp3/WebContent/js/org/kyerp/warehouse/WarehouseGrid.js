
/** ***************************************************************************** */
org.kyerp.warehouse.WarehouseGrid = Ext.extend(Ext.grid.EditorGridPanel, {
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.store = org.kyerp.warehouse.WarehouseStore;
		org.kyerp.warehouse.WarehouseGrid.superclass.constructor.call(this, {
			stripeRows : true,
			viewConfig : {
				forceFit : true
			},
			store : this.store,
            title : '仓库列表',
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
			selModel : new Ext.grid.RowSelectionModel({
						singleSelect : true
					}),
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
									this.onDeleteWarehouseClick, this);
						},
						scope : this
					}, '->', '双击表格可以修改仓库资料'],
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
			createWarehouse : function() {
                var store = org.kyerp.warehouse.WarehouseStore;
                store.load();
                alert(Ext.encode(store.lastOptions.params.parentId));
				//var node = this.tree.getSelectionModel().getSelectedNode();
                /*
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
											text : data.warehouseExtGridRow.name,
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
				}*/

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
						var node = this.tree.getSelectionModel()
								.getSelectedNode();
						id = node.id;
					}
					Ext.Ajax.request({
								url : org.kyerp.warehouse.WarehousePanel_DELETE_URL
										+ "?ids=" + id,
								method : 'POST',
								success : function(response) {
									var data = Ext
											.decode(response.responseText);
									if (data.success) {
										var snode = org.kyerp.warehouse.WarehouseTree
												.getSelectionModel()
												.getSelectedNode();
										if (snode.id == id) { // 当前树节点是要删除的节点
											snode.remove();
										} else {
											var node = snode
													.findChild('id', id);
											node.remove();
										}
										org.kyerp.warehouse.WarehouseGrid
												.getStore().reload();
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
		this.on("afteredit", function(e) {
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
									var node = org.kyerp.warehouse.WarehouseTreePanel
											.getSelectionModel()
											.getSelectedNode().findChild('id',
													e.record.data.id);
									if (node) {
										node.setText(e.record.data.name);
									}
								}
							});
				});
	}
});