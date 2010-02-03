/** ***************************************************************************** */
org.kyerp.org.DepartmentPanel_STORE_URL = "org/Department/jsonList.html";
org.kyerp.org.DepartmentPanel_TREE_URL = "org/Department/jsonTree.html";
org.kyerp.org.DepartmentPanel_DATA_INSERT_URL = "org/Department/jsonTreeInsert.html";
org.kyerp.org.DepartmentPanel_DATA_UPDATE_URL = "org/Department/jsonUpdate.html";
org.kyerp.org.DepartmentPanel_DATA_DELETE_URL = "org/Department/jsonTreeDelete.html";
/** ***************************************************************************** */
org.kyerp.org.DepartmentPanel = Ext.extend(Ext.tree.TreePanel, {
	menu : null,
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.menu = new Ext.menu.Menu({
					items : [{
								text : "添加",
								iconCls : 'icon-utils-s-add',
								handler : this.onInsertNode,
								scope : this
							}, {
								text : "删除",
								iconCls : 'icon-utils-s-delete',
								handler : this.onDeleteNode,
								scope : this
							}, {
								text : "修改",
								iconCls : 'icon-utils-s-edit',
								handler : this.onUpdateNode,
								scope : this
							}]
				});
		org.kyerp.org.DepartmentPanel.superclass.constructor.call(this, {
					rootVisible : false,
					enableDD : true,// 是否支持拖拽效果
					containerScroll : true,// 是否支持滚动条
					dataUrl : org.kyerp.org.DepartmentPanel_TREE_URL,
					listeners : {
						"contextmenu" : {
							fn : this.onContextmenu,
							scope : this
						}
					},
					root : new Ext.tree.AsyncTreeNode({
								text : "组织机构",
								id : "0",
								expanded : true
							})
				});
		this.on('nodedrop', function(e) {

			if (e.point == 'append') {
				alert('当前"' + e.dropNode.text + '"被"' + e.target.text + '"录取！');
			} else if (e.point == 'above') {
				alert('当前"' + e.dropNode.text + '"放在了"' + e.target.text
						+ '"上面！');
			} else if (e.point == 'below') {
				alert('当前"' + e.dropNode.text + '"放在了"' + e.target.text
						+ '"下面！');
			}
		});
	},
	onContextmenu : function(_node, _e) {
		this.menu["currentNode"] = _node;
		if (_node.id == "0" || _node.id == "1" || !_node.leaf)
			this.menu.items.itemAt(1).setDisabled(true);
		else
			this.menu.items.itemAt(1).setDisabled(false);
		this.menu.showAt(_e.getXY());
	},
	onInsertNode : function() {
		Ext.Msg.prompt("请输入名称", "名称", this.onInsertNodePrompt, this);
	},
	onDeleteNode : function() {
		Ext.Msg.confirm("系统提示", "你是否确定删除?", this.onDeleteNodeConfirm, this);
	},
	onUpdateNode : function() {
		Ext.Msg.prompt("请输入新名称", "新名称", this.onUpdateNodePrompt, this, false,
				this.menu["currentNode"].text);
	},
	onInsertNodePrompt : function(_btn, _text) {
		if (_btn == "ok") {
			var _parent = this.menu["currentNode"]
					? this.menu["currentNode"]
					: this.root;
			_parent.leaf = false;
			var _node = new Ext.tree.AsyncTreeNode({
						text : _text,
						leaf : true,
						id : Ext.id()
					});
			Ext.Ajax.request({
						url : org.kyerp.org.DepartmentPanel_DATA_INSERT_URL,
						params : {
							parentId : _parent.id,
							name : _node.text
						}
					});
			_parent.appendChild(_node);
			// this.root.reload();
		}
	},
	onUpdateNodePrompt : function(_btn, _text) {
		if (_btn == "ok") {
			if (this.menu["currentNode"].text != _text.trim()) {
				this.menu["currentNode"].setText(_text);
				Ext.Ajax.request({
							url : org.kyerp.org.DepartmentPanel_DATA_UPDATE_URL,
							params : {
								id : this.menu["currentNode"].id,
								name : this.menu["currentNode"].text
							}
						});
			}
		}
	},
	onDeleteNodeConfirm : function(_btn) {
		if (_btn == "yes") {
			var _node = this.menu["currentNode"];
			Ext.Ajax.request({
						url : org.kyerp.org.DepartmentPanel_DATA_DELETE_URL,
						params : {
							id : _node.id
						}
					});
			_node.remove();
			this.root.reload();
		}
	}

});
/** ***************************************************************************** */
