/** ***************************************************************************** */
org.kyerp.warehouse.MaterialCategoryTreePanel = Ext.extend(Ext.tree.TreePanel, {
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		org.kyerp.warehouse.MaterialCategoryTreePanel.superclass.constructor.call(
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
					root : new Ext.tree.AsyncTreeNode({
								text : "物料分类",
								id : "0",
								expanded : true
							})
				});
	}
});
/** ***************************************************************************** */