/** ***************************************************************************** */
org.kyerp.warehouse.StockPanel = Ext.extend(Ext.Panel, {
	materialCategoryTree : null,
	materialList : null,
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.materialCategoryTree = new org.kyerp.warehouse.MaterialCategoryTreePanel(
				{
					title : '物料分类',
					region : 'west',
					border : false,
					split : true,
					collapsible : true,
					collapseMode : 'mini',
					width : 150,
					minSize : 100,
					maxSize : 500,
					autoScroll : true,
					cmargins : '3 3 3 3'
				});
		this.materialList = new org.kyerp.warehouse.StockListPanel({
					title : '库存明细',
					border : false,
					region : 'center'
				});

		org.kyerp.warehouse.StockPanel.superclass.constructor.call(this, {
					layout : 'border',
					border : false,
					defaults : {
						collapsible : true,
						split : true
					},
					items : [this.materialCategoryTree, this.materialList]
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
    	require('StockStore.js;' +
    			'StockDetailStore.js;' +
    			'StockDetailGrid.js;' +
    			'StockDetailWindow.js;' +
    			'InventoryDetailStroe.js;' +
    			'InventoryDetailWindow.js;' +
    			'InventoryOwnerStore.js;' +    			
    			'MaterialCategoryTreePanel.js;' +
    			'StockListPanel.js', {
					basedir : 'js/org/kyerp/warehouse'
				
				});
        this.body = new org.kyerp.warehouse.StockPanel({border : false,bodyBorder : false});
        this.main.add(this.body);
        this.main.doLayout();  
    }
});