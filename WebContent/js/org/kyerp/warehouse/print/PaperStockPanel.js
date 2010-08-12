/** ***************************************************************************** */
org.kyerp.warehouse.print.PaperStockPanel = Ext.extend(Ext.Panel, {
	materialCategoryTree : null,
	materialList : null,
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.materialCategoryTree = new org.kyerp.warehouse.MaterialCategoryTreePanel(
				{
					title : '纸张分类',
					region : 'west',
					border : false,
					split : true,
					collapsible : true,
					collapseMode : 'mini',
					width : 150,
					minSize : 100,
					maxSize : 500,
					autoScroll : true,
					cmargins : '3 3 3 3',
					url:org.kyerp.warehouse.MaterialCategoryPanel_TREE_URL+"?parentId=3"
				});
		this.materialList = new org.kyerp.warehouse.print.PaperStockListPanel({
					title : '纸张库存明细表',
					border : false,
					region : 'center'
				});

		org.kyerp.warehouse.print.PaperStockPanel.superclass.constructor.call(this, {
					layout : 'border',
					border : false,
					defaults : {
						collapsible : true,
						split : true
					},
					items : [this.materialList]
				});
		// 点击tree改变List的内容
		this.materialCategoryTree.on("click", function(node) {
					this.materialList.store.on('beforeload', function(thiz,
									options) {
								Ext.apply(thiz.baseParams, {
											mCategoryId : node.attributes.id
										});
							}, this);
					this.materialList.store.load({
								params : {
									start : 0,
									limit : 20,
									mCategoryId : node.attributes.id
								}
							});
					this.materialList.setTitle(node.attributes.text);
				}, this);
	}
});
/** ***************************************************************************** */
Ext.extend(org.kyerp.module,{
    init: function(){
    	require('StockDetailStore.js;' +
    			'StockDetailGrid.js;' +
    			'StockDetailWindow.js;' +
    			'InventoryDetailStroe.js;' +
    			'InventoryDetailWindow.js;' +
    			'InventoryOwnerStore.js;' +    			
    			'MaterialCategoryTreePanel.js', {
					basedir : 'js/org/kyerp/warehouse'
				
				});
    	require('PaperStockStore.js;' +
    			'PaperStockListPanel.js', {
					basedir : 'js/org/kyerp/warehouse/print'
				
				});
        this.body = new org.kyerp.warehouse.print.PaperStockPanel({border : false,bodyBorder : false});
        this.main.add(this.body);
        this.main.doLayout();  
    }
});