/** ***************************************************************************** */
org.kyerp.warehouse.WarehousePanel = Ext.extend(Ext.Panel, {
			tree : null,
			grid : null,
			constructor : function(_cfg) {
				Ext.apply(this, _cfg);
				this.tree = new org.kyerp.warehouse.WarehouseTreePanel({
							border : false
						});
				this.grid = new org.kyerp.warehouse.WarehouseGrid({
							border : false
						});
				org.kyerp.warehouse.WarehousePanel.superclass.constructor.call(
						this, {
							layout : 'border',
							border : false,
							defaults : {
								split : true
							},
							items : [{
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
			}
		});
/** ***************************************************************************** */
Ext.extend(org.kyerp.module, {
			init : function() {
				require('WarehouseStore.js;' +
                        'WarehouseTreePanel.js;'
								+ 'WarehouseGrid.js', {
							basedir : 'js/org/kyerp/warehouse'

						});
				this.body = new org.kyerp.warehouse.WarehousePanel({
							border : false,
							bodyBorder : false
						});
				this.main.add(this.body);
				this.main.doLayout();
			}
		});