org.kyerp.LeftMenu = function(config) {
	var d = Ext.apply({
				split : true,
				region : 'west',
				width : 200,
				minSize : 100,
				maxSize : 500,
				margins : '0 0 5 5',
				collapseMode : 'mini',
				bodyStyle : 'padding-bottom: 1px;',
				iconCls :'icon-style-tab',
				//不显示title
				header : false,
				defaults : {
					border : false
				},
				layoutConfig : {
					animate : true
				}
			}, config || {});
	config = Ext.apply(d, {
				layout : 'accordion',
				collapsible : true
			});

	org.kyerp.LeftMenu.superclass.constructor.call(this, config || {});
	// 改进，并为增加了个配置项tree!
	for (var i = 0; i < this.trees.length; i++)
		this.add({
					title : this.trees[i].getRootNode().text,
					iconCls : this.trees[i].iconCls,
					border:false,
					autoScroll:true,
					items : [this.trees[i]]
				});

	// 事件处理
	this.addEvents('nodeClick');
	this.initTreeEvent();
}

Ext.extend(org.kyerp.LeftMenu, Ext.Panel, {
			initTreeEvent : function() {
				if (!this.items)
					return;
				for (var i = 0; i < this.items.length; i++) {
					var p = this.items.itemAt(i);
					if (p)
						var t = p.items.itemAt(0);
					if (t)
						t.on({
									'click' : function(node, event) {
										if (node && node.isLeaf()) {
											event.stopEvent();
											this.fireEvent('nodeClick',
													node.attributes);
										}
									},
									scope : this
								});
				}
			}
		})
