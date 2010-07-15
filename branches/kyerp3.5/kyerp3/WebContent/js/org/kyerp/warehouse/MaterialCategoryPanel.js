
/** ***************************************************************************** */
org.kyerp.warehouse.MaterialCategoryStore = new Ext.data.Store({
			autoLoad : true,
			proxy : new Ext.data.HttpProxy({
						url : org.kyerp.warehouse.MaterialCategoryPanel_LIST_URL
					}),
			reader : new Ext.data.JsonReader({
						totalProperty : "totalProperty",
						root : "rows",
						id : "id"
					}, ['id', 'createTime', 'updateTime', 'name',
							'serialNumber', 'note', 'childMaterialCategoryIds',
							'childMaterialCategoryNames', 'parentMaterialCategoryId',
							'parentMaterialCategoryName'])
		});
/** ***************************************************************************** */
org.kyerp.warehouse.MaterialCategoryTree = new Ext.tree.TreePanel({
	title : '原料分类',
	loader : new Ext.tree.TreeLoader({
				dataUrl : org.kyerp.warehouse.MaterialCategoryPanel_TREE_URL
			}),
	root : {
		nodeType : 'async',
		id : 'root',
		text : '原料分类',
		expanded : true
	},
	tools : [{
				id : 'refresh',
				qtip : '刷新',
				handler : function() {
					org.kyerp.warehouse.MaterialCategoryTree.getRootNode().reload();
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
org.kyerp.warehouse.MaterialCategoryGrid = new Ext.grid.EditorGridPanel({
			title : '原料分类资料',
			store : org.kyerp.warehouse.MaterialCategoryStore,
			columns : [new Ext.grid.RowNumberer(), {
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
					}],
			border : false,
			selModel : new Ext.grid.RowSelectionModel(),
			bbar : new Ext.PagingToolbar({
						plugins : new Ext.ux.Andrie.pPageSize({
									beforeText : '每页显示',
									afterText : '条'
								}),
						pageSize : 20,
						store : org.kyerp.warehouse.MaterialCategoryStore,
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
org.kyerp.warehouse.MaterialCategoryPanel = Ext.extend(Ext.Panel, {
	tree : null,
	grid : null,
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.tree = org.kyerp.warehouse.MaterialCategoryTree;
		this.grid = org.kyerp.warehouse.MaterialCategoryGrid;
		org.kyerp.warehouse.MaterialCategoryPanel.superclass.constructor.call(this,
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
										this.createMaterialCategory();
									},
									scope : this
								}, '-', {
									text : '删除',
									iconCls : 'icon-utils-s-delete',
									handler : function() {
										Ext.Msg.confirm("系统提示", "你确定删除此记录吗?",
												this.onDeleteMaterialCategoryClick,
												this);
									},
									scope : this
								}, '->', '双击表格可以修改原料分类资料']
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
					var store = org.kyerp.warehouse.MaterialCategoryStore;
					store.setBaseParam("parentId", node.id);
					store.load();
				});
		this.grid.on('afteredit', function(e) {
					// alert(Ext.encode(e.record.data));
					Ext.Ajax.request({
								url : org.kyerp.warehouse.MaterialCategoryPanel_SAVE_URL,
								params : {
									id : e.record.data.id,
									name : e.record.data.name,
									note : e.record.data.note,
									serialNumber : e.record.data.serialNumber
								},
								method : 'POST',
								success : function() {
									e.record.commit(false);
									var node = org.kyerp.warehouse.MaterialCategoryTree
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
	createMaterialCategory : function() {
		var node = this.tree.getSelectionModel().getSelectedNode();
		if (node) {
			Ext.Ajax.request({
						url : org.kyerp.warehouse.MaterialCategoryPanel_SAVE_URL,
						params : {
							parentMaterialCategoryId : node.id,
							name : '新原料分类'
						},
						success : function(response) {
							var data = Ext.decode(response.responseText);
							if (data.success) {
								node.appendChild(new Ext.tree.TreeNode({
											id : data.id,
											text : data.materialCategoryExtGridRow.name,
											leaf : true
										}));
								node.getUI().removeClass('x-tree-node-leaf');
								node.getUI().addClass('x-tree-node-expanded');
								node.expand();
								org.kyerp.warehouse.MaterialCategoryGrid.getStore()
										.reload();
							} else {
								Ext.MessageBox.alert('警告', data.msg);
							}
						}
					})
		}

	},
	onDeleteMaterialCategoryClick : function(_btn) {
		if (_btn == "yes") {
			this.onDeleteMaterialCategory();
		}
	},

	onDeleteMaterialCategory : function() {
		try {
			var id;
			// var rec = this.grid.getSelectionModel().getSelected();
			var rec = this.grid.getGridSelected()
			if (rec) {
				id = rec.data.id;
			} else {
				var node = this.tree.getSelectionModel().getSelectedNode();
				id = node.id;
			}
			Ext.Ajax.request({
						url : org.kyerp.warehouse.MaterialCategoryPanel_DELETE_URL
								+ "?ids=" + id,
						method : 'POST',
						success : function(response) {
							var data = Ext.decode(response.responseText);
							if (data.success) {
								var snode = org.kyerp.warehouse.MaterialCategoryTree
										.getSelectionModel().getSelectedNode();
								if (snode.id == id) { // 当前树节点是要删除的节点
									snode.remove();
								} else {
									var node = snode.findChild('id', id);
									node.remove();
								}
								org.kyerp.warehouse.MaterialCategoryGrid.getStore()
										.reload();
								Ext.MessageBox.alert('警告', '删除原料分类资料完成。');
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
				this.body = new org.kyerp.warehouse.MaterialCategoryPanel({
							border : false,
							bodyBorder : false
						});
				this.main.add(this.body);
				this.main.doLayout();
			}
		});