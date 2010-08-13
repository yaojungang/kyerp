/** ***************************************************************************** */
org.kyerp.warehouse.StockDetailWindow = Ext.extend(Ext.Window, {
	grid : null,
	store : null,
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.store = org.kyerp.warehouse.StockDetailStore;
		this.grid = new org.kyerp.warehouse.StockDetailGrid({
			region : 'center',
			autoWidth : true
		});
		org.kyerp.warehouse.StockDetailWindow.superclass.constructor.call(this,
				{
					title : "查看",
					width : 700,
					height : 250,
					layout : 'border',
					border : false,
					iconCls : 'icon-utils-s-view',
					closeAction : 'hide',
					items : [ this.grid ]
				});
	}
});