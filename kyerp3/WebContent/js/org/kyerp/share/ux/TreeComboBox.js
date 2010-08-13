// http://www.javaeye.com/topic/550201
Ext.namespace("Ext.ux.form");
Ext.ux.form.TreeComboBox = Ext.extend(Ext.form.ComboBox, {
			allowUnLeafClick : false,
			resizable : true,
			store : new Ext.data.SimpleStore({
						fields : [],
						data : [[]]
					}),
			onSelect : function(node) {
			},
			onViewClick : function(){
				return;
			},
			initComponent : function(ct, position) {
				this.divId = 'tree-' + Ext.id();
				if (isNaN(this.maxHeight))
					this.maxHeight = 200;
				Ext.apply(this, {
							tpl : '<tpl>' + '<div style="height:'
									+ this.maxHeight + 'px;">' + '<div id="'
									+ this.divId + '"></div>' + '</div></tpl>'
						});

				this.tree = new Ext.tree.TreePanel({
							root:new Ext.tree.AsyncTreeNode({id:'0',text:''}),
							loader:new Ext.tree.TreeLoader({dataUrl:this.treeUrl,clearOnLoad : true}),
							rootVisible : this.rootVisible,
							border : false,
							autoScroll:true,
							tools : [{
										id : 'refresh',
										qtip : '刷新',
										handler : function() {
											this.tree.getRootNode().reload();
										},
										scope : this
									}]
						});
				var combo = this;
				this.tree.on('click', function(node) {
							if (combo.allowUnLeafClick == true) {
								combo.setValue(node.text);
								combo.hiddenField.value = node.id;
								combo.collapse();
								combo.onSelect(node);
							} else if (node.leaf == true) {
								combo.setValue(node.text);
								combo.hiddenField.value = node.id;
								combo.collapse();
								combo.onSelect(node);
							}
						});

				Ext.ux.form.TreeComboBox.superclass.initComponent.call(this);
			},

			onRender : function(ct, position) {
				Ext.ux.form.TreeComboBox.superclass.onRender.call(this, ct,
						position);
				this.on("expand", function() {
							if (!this.tree.rendered) {
								this.tree.render(this.divId);
							}
						}, this)
			}
		});

Ext.reg('treecombobox', Ext.ux.form.TreeComboBox);