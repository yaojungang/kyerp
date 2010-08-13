
/** ***************************************************************************** */
org.kyerp.crm.ClientTypeStore = new Ext.data.Store({
			autoLoad : true,
			proxy : new Ext.data.HttpProxy({
						url : org.kyerp.crm.ClientTypePanel_LIST_URL
					}),
			reader : new Ext.data.JsonReader({
						totalProperty : "totalProperty",
						root : "rows",
						id : "id"
					}, ['id', 'createTime', 'updateTime', 'name',
							'serialNumber', 'note', 'childClientTypeIds',
							'childClientTypeNames', 'parentClientTypeId',
							'parentClientTypeName'])
		});
/** ***************************************************************************** */
org.kyerp.crm.ClientTypeTree = new Ext.tree.TreePanel({
	title : '客户分类',
	loader : new Ext.tree.TreeLoader({
				dataUrl : org.kyerp.crm.ClientTypePanel_TREE_URL
			}),
	root : {
		nodeType : 'async',
		id : 'root',
		text : '客户分类',
		expanded : true
	},
	tools : [{
				id : 'refresh',
				qtip : '刷新',
				handler : function() {
					org.kyerp.crm.ClientTypeTree.getRootNode().reload();
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
org.kyerp.crm.ClientTypeGrid = new Ext.grid.EditorGridPanel({
			title : '客户分类资料',
			store : org.kyerp.crm.ClientTypeStore,
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
						store : org.kyerp.crm.ClientTypeStore,
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
org.kyerp.crm.ClientTypePanel = Ext.extend(Ext.Panel, {
	tree : null,
	grid : null,
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.tree = org.kyerp.crm.ClientTypeTree;
		this.grid = org.kyerp.crm.ClientTypeGrid;
		org.kyerp.crm.ClientTypePanel.superclass.constructor.call(this,
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
										this.createClientType();
									},
									scope : this
								}, '-', {
									text : '删除',
									iconCls : 'icon-utils-s-delete',
									handler : function() {
										Ext.Msg.confirm("系统提示", "你确定删除此记录吗?",
												this.onDeleteClientTypeClick,
												this);
									},
									scope : this
								}, '->', '双击表格可以修改客户分类资料']
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
					var store = org.kyerp.crm.ClientTypeStore;
					store.setBaseParam("parentId", node.id);
					store.load();
				});
		this.grid.on('afteredit', function(e) {
					// alert(Ext.encode(e.record.data));
					Ext.Ajax.request({
								url : org.kyerp.crm.ClientTypePanel_SAVE_URL,
								params : {
									id : e.record.data.id,
									name : e.record.data.name,
									note : e.record.data.note,
									serialNumber : e.record.data.serialNumber
								},
								method : 'POST',
								success : function() {
									e.record.commit(false);
									var node = org.kyerp.crm.ClientTypeTree
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
	createClientType : function() {
		var node = this.tree.getSelectionModel().getSelectedNode();
		if (node) {
			Ext.Ajax.request({
						url : org.kyerp.crm.ClientTypePanel_SAVE_URL,
						params : {
							parentClientTypeId : node.id,
							name : '新客户分类'
						},
						success : function(response) {
							var data = Ext.decode(response.responseText);
							if (data.success) {
								node.appendChild(new Ext.tree.TreeNode({
											id : data.id,
											text : data.clientTypeExtGridRow.name,
											leaf : true
										}));
								node.getUI().removeClass('x-tree-node-leaf');
								node.getUI().addClass('x-tree-node-expanded');
								node.expand();
								org.kyerp.crm.ClientTypeGrid.getStore()
										.reload();
							} else {
								Ext.MessageBox.alert('警告', data.msg);
							}
						}
					})
		}

	},
	onDeleteClientTypeClick : function(_btn) {
		if (_btn == "yes") {
			this.onDeleteClientType();
		}
	},

	onDeleteClientType : function() {
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
						url : org.kyerp.crm.ClientTypePanel_DELETE_URL
								+ "?ids=" + id,
						method : 'POST',
						success : function(response) {
							var data = Ext.decode(response.responseText);
							if (data.success) {
								var snode = org.kyerp.crm.ClientTypeTree
										.getSelectionModel().getSelectedNode();
								if (snode.id == id) { // 当前树节点是要删除的节点
									snode.remove();
								} else {
									var node = snode.findChild('id', id);
									node.remove();
								}
								org.kyerp.crm.ClientTypeGrid.getStore()
										.reload();
								Ext.MessageBox.alert('警告', '删除客户分类资料完成。');
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
				this.body = new org.kyerp.crm.ClientTypePanel({
							border : false,
							bodyBorder : false
						});
				this.main.add(this.body);
				this.main.doLayout();
			}
		});