
/** ***************************************************************************** */
org.kyerp.org.DepartmentStore = new Ext.data.Store({
			autoLoad : true,
			proxy : new Ext.data.HttpProxy({
						url : org.kyerp.org.DepartmentPanel_LIST_URL
					}),
			reader : new Ext.data.JsonReader({
						totalProperty : "totalProperty",
						root : "rows",
						id : "id"
					}, ['id', 'createTime', 'updateTime', 'name',
							'serialNumber', 'remark', 'childDepartmentIds',
							'childDepartmentNames', 'parentDepartmentId',
							'parentDepartmentName'])
		});
/** ***************************************************************************** */
org.kyerp.org.DepartmentTree = new Ext.tree.TreePanel({
	title : '部门',
	loader : new Ext.tree.TreeLoader({
				dataUrl : org.kyerp.org.DepartmentPanel_TREE_URL
			}),
	root : {
		nodeType : 'async',
		id : 'root',
		text : '部门',
		expanded : true
	},
	tools : [{
				id : 'refresh',
				qtip : '刷新',
				handler : function() {
					org.kyerp.org.DepartmentTree.getRootNode().reload();
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
org.kyerp.org.DepartmentGrid = new Ext.grid.EditorGridPanel({
			title : '部门资料',
			store : org.kyerp.org.DepartmentStore,
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
						dataIndex : 'remark',
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
						store : org.kyerp.org.DepartmentStore,
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
org.kyerp.org.DepartmentPanel = Ext.extend(Ext.Panel, {
	tree : null,
	grid : null,
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.tree = org.kyerp.org.DepartmentTree;
		this.grid = org.kyerp.org.DepartmentGrid;
		org.kyerp.org.DepartmentPanel.superclass.constructor.call(this,
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
										this.createDepartment();
									},
									scope : this
								}, '-', {
									text : '删除',
									iconCls : 'icon-utils-s-delete',
									handler : function() {
										Ext.Msg.confirm("系统提示", "你确定删除此记录吗?",
												this.onDeleteDepartmentClick,
												this);
									},
									scope : this
								}, '->', '双击表格可以修改部门资料']
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
					var store = org.kyerp.org.DepartmentStore;
					store.setBaseParam("parentId", node.id);
					store.load();
				});
		this.grid.on('afteredit', function(e) {
					// alert(Ext.encode(e.record.data));
					Ext.Ajax.request({
								url : org.kyerp.org.DepartmentPanel_SAVE_URL,
								params : {
									id : e.record.data.id,
									name : e.record.data.name,
									note : e.record.data.note,
									serialNumber : e.record.data.serialNumber
								},
								method : 'POST',
								success : function() {
									e.record.commit(false);
									var node = org.kyerp.org.DepartmentTree
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
	createDepartment : function() {
		var node = this.tree.getSelectionModel().getSelectedNode();
		if (node) {
			Ext.Ajax.request({
						url : org.kyerp.org.DepartmentPanel_SAVE_URL,
						params : {
							parentDepartmentId : node.id,
							name : '新部门'
						},
						success : function(response) {
							var data = Ext.decode(response.responseText);
							if (data.success) {
								node.appendChild(new Ext.tree.TreeNode({
											id : data.id,
											text : data.departmentExtGridRow.name,
											leaf : true
										}));
								node.getUI().removeClass('x-tree-node-leaf');
								node.getUI().addClass('x-tree-node-expanded');
								node.expand();
								org.kyerp.org.DepartmentGrid.getStore()
										.reload();
							} else {
								Ext.MessageBox.alert('警告', data.msg);
							}
						}
					})
		}

	},
	onDeleteDepartmentClick : function(_btn) {
		if (_btn == "yes") {
			this.onDeleteDepartment();
		}
	},

	onDeleteDepartment : function() {
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
						url : org.kyerp.org.DepartmentPanel_DELETE_URL
								+ "?ids=" + id,
						method : 'POST',
						success : function(response) {
							var data = Ext.decode(response.responseText);
							if (data.success) {
								var snode = org.kyerp.org.DepartmentTree
										.getSelectionModel().getSelectedNode();
								if (snode.id == id) { // 当前树节点是要删除的节点
									snode.remove();
								} else {
									var node = snode.findChild('id', id);
									node.remove();
								}
								org.kyerp.org.DepartmentGrid.getStore()
										.reload();
								Ext.MessageBox.alert('警告', '删除部门资料完成。');
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
				this.body = new org.kyerp.org.DepartmentPanel({
							border : false,
							bodyBorder : false
						});
				this.main.add(this.body);
				this.main.doLayout();
			}
		});