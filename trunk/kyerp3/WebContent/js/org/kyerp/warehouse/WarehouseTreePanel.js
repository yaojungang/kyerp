/** ***************************************************************************** */
org.kyerp.warehouse.WarehouseTreePanel = Ext.extend(Ext.tree.TreePanel, {
	url : null,
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		org.kyerp.warehouse.WarehouseTreePanel.superclass.constructor.call(
				this, {
					rootVisible : false,
					enableDD : true,// 是否支持拖拽效果
					containerScroll : true,// 是否支持滚动条
					dataUrl : this.url||org.kyerp.warehouse.WarehousePanel_TREE_URL,
					tools : [{
								id : 'refresh',
								qtip : '刷新',
								handler : function() {
									this.getRootNode().reload();
								},
								scope : this
							}],
					root : new Ext.tree.AsyncTreeNode({
								text : "库房",
								id : "0",
								expanded : true
							})
				});
	}
});
/** ***************************************************************************** */