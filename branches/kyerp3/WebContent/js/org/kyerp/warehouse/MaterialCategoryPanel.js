/** ***************************************************************************** */
org.kyerp.warehouse.MaterialCategoryPanel_STORE_URL = "warehouse/MaterialCategory/jsonList.html";
org.kyerp.warehouse.MaterialCategoryPanel_TREE_URL = "warehouse/MaterialCategory/jsonTree.html";
org.kyerp.warehouse.MaterialCategoryPanel_DATA_SAVE_URL = "warehouse/MaterialCategory/jsonSave.html";
org.kyerp.warehouse.MaterialCategoryPanel_DATA_DELETE_URL = "warehouse/MaterialCategory/jsonDelete.html";
/** ***************************************************************************** */
org.kyerp.warehouse.MaterialCategoryFormPanel = Ext.extend(Ext.form.FormPanel,
		{
			url : "",
			materialCategoryStore : null,
			constructor : function(_cfg) {
				if (_cfg == null)
					_cfg = {};
				Ext.apply(this, _cfg);
				this.materialCategoryStore = new Ext.data.Store({
							url : org.kyerp.warehouse.MaterialCategoryPanel_STORE_URL,
							reader : new Ext.data.JsonReader({
										totalProperty : "totalProperty",
										root : "rows",
										id : "id"
									}, new Ext.data.Record.create([{
												name : "id",
												type : "int"
											}, {
												name : "name",
												type : "string"
											}, {
												name : "nameSpell",
												type : "string"
											}, {
												name : "parentMaterialCategoryId",
												type : "int"
											}, {
												name : "parentMaterialCategoryName",
												type : "string"
											}, {
												name : "visible"
											}]))
						});
				org.kyerp.warehouse.MaterialCategoryFormPanel.superclass.constructor
						.call(this, {
							labelWidth : 80,
							labelAlign : 'right',
							defaultType : "textfield",
							defaults : {
								anchor : "90%",
								msgTarget : 'side'
							},
							baseCls : "x-plain",
							items : [{
										fieldLabel : "名称",
										allowBlank : false,
										name : "name"
									}, {
										xtype : 'treecombobox',
										name : 'parentMaterialCategoryId',
										hiddenName : 'parentMaterialCategoryId',
										fieldLabel : '物料分类',
										editable : false,
										mode : 'local',
										displayField : 'name',
										valueField : 'id',
										triggerAction : 'all',
										allowBlank : false,
										treeUrl : org.kyerp.warehouse.MaterialCategoryPanel_TREE_URL,
										rootText : 'root',
										rootId : '0',
										forceSelection : true,
										rootVisible : false
									}]
						});
				this.addEvents("submit");
			},
			submit : function(_params) {
				if (_params == null)
					_params = {};
				try {
					if (this.url != "")
						this.getForm().submit({
									url : this.url,
									params : _params,
									success : this.onSubmit,
									waitTitle : "数据传送",
									waitMsg : "数据传送中,请稍候...",
									scope : this
								});

				} catch (_err) {
				}
			},
			getValues : function() {
				if (this.getForm().isValid())
					return new Ext.data.Record(this.getForm().getValues());
				else
					throw Error("表单验证没有通过");
			},
			setValues : function(_r) {
				this.getForm().loadRecord(_r);
			},
			reset : function() {
				this.getForm().reset();
			},
			onSubmit : function(_form, _action) {
				this.fireEvent("submit", this, _action, this.getValues());
			}
		});
/** ***************************************************************************** */
org.kyerp.warehouse.MaterialCategoryInfoWindow = Ext.extend(Ext.Window, {
			url : "",
			form : null,
			constructor : function(_cfg) {
				Ext.apply(this, _cfg);
				this.form = new org.kyerp.warehouse.MaterialCategoryFormPanel({
							url : this.url
						});
				org.kyerp.warehouse.MaterialCategoryInfoWindow.superclass.constructor
						.call(this, {
									plain : true,
									width : 500,
									modal : true,
									items : this.form,
									layout : 'fit',
									closeAction : "hide",
									buttons : [{
												text : "确 定",
												handler : this.onSubmitClick,
												scope : this
											}, {
												text : "取 消",
												handler : this.onCancelClick,
												scope : this
											}]
								});

				this.addEvents("submit");
				this.form.on("submit", this.onSubmit, this);
			},
			close : function() {
				this.form.reset();
				this.hide();
			},
			onSubmitClick : function() {
				this.form.submit();
			},
			onCancelClick : function() {
				this.close();
			},
			onSubmit : function(_form, _action, _values) {
				try {
					this.fireEvent("submit", this, _values);
				} catch (_err) {
					return;
				}
				this.close();
			}

		});
/** ***************************************************************************** */
org.kyerp.warehouse.MaterialCategoryUpdateWindow = Ext.extend(
		org.kyerp.warehouse.MaterialCategoryInfoWindow, {
			title : "修 改",
			iconCls : 'icon-utils-s-edit',
			url : org.kyerp.warehouse.MaterialCategoryPanel_DATA_SAVE_URL,
			pnId : "",
			load : function(_id) {
				this.pnId = _id;
				_r = this.form.materialCategoryStore.getById(_id);
				this.form.setValues(_r);
				this.form.get(1).setValue(new Ext.tree.TreeNode({
							id : _r.get("parentMaterialCategoryId"),
							text : _r.get("parentMaterialCategoryName")
						}));
			},
			onSubmitClick : function() {
				this.form.submit({
							id : this.pnId
						});
			},
			onSubmit : function(_form, _action, _values) {
				var _data = _values.data;
				Ext.apply(_data, {
							id : this.pnId
						});
				try {
					this.fireEvent("submit", this, new Ext.data.Record(_data));
				} catch (_err) {
					return;
				}
				this.close();
			}
		});
/** ***************************************************************************** */
org.kyerp.warehouse.MaterialCategoryPanel = Ext.extend(Ext.tree.TreePanel, {
	menu : null,
	updateWin : new org.kyerp.warehouse.MaterialCategoryUpdateWindow(),
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
								handler : function() {
									this.updateWin.show();
									try {
										this.updateWin
												.load(this.menu["currentNode"].id);
									} catch (_err) {
										Ext.Msg.alert("修改失败", _err);
										this.updateWin.close();
									}
								},
								scope : this
							}]
				});
		org.kyerp.warehouse.MaterialCategoryPanel.superclass.constructor.call(
				this, {
					rootVisible : false,
					enableDD : true,// 是否支持拖拽效果
					containerScroll : true,// 是否支持滚动条
					dataUrl : org.kyerp.warehouse.MaterialCategoryPanel_TREE_URL,
					tools : [{
								id : 'refresh',
								qtip : '刷新',
								handler : function() {
									this.getRootNode().reload();
								},
								scope : this
							}],
					listeners : {
						"contextmenu" : {
							fn : this.onContextmenu,
							scope : this
						}
					},
					root : new Ext.tree.AsyncTreeNode({
								text : "物料分类",
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
		this.on("show", function() {
					this.updateWin.form.materialCategoryStore.load({
								params : {
									start : 0,
									limit : 2000
								}
							});
				}, this);
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
						url : org.kyerp.warehouse.MaterialCategoryPanel_DATA_SAVE_URL,
						params : {
							parentMaterialCategoryId : _parent.id,
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
					url : org.kyerp.warehouse.MaterialCategoryPanel_DATA_SAVE_URL,
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
				url : org.kyerp.warehouse.MaterialCategoryPanel_DATA_DELETE_URL,
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
