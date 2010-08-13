/** ***************************************************************************** */
org.kyerp.warehouse.OutStockPanel = Ext.extend(Ext.Panel, {
			store : org.kyerp.warehouse.InStockStore,
			layout : 'border',
			listGrid : null,
			detailGrid : null,
			border : false,
			initComponent : function() {
				this.listGrid = new org.kyerp.warehouse.OutStockGrid(
						{
							region : 'center',
							autoWidth : true,
							border : false
						});
				this.detailGrid = new org.kyerp.warehouse.OutStockDetailGrid(
						{
							height : 80,
							region : 'south',
							split : true,
							border : false
						});
				this.items = [this.listGrid, this.detailGrid];
				org.kyerp.warehouse.OutStockPanel.superclass.initComponent
						.call(this);
			}
		});

/** ***************************************************************************** */
Ext.extend(org.kyerp.module, {
			init : function() {
				require('WarehouseStore.js;' +
						'StockStore.js;' +
                        'StockDetailStore.js;' +
						'InventoryOwnerStore.js;' +
						'OutStockDetailStore.js;' +
						'OutStockStore.js;' +
						'SelectSupplierWindow.js;' +
						'SelectMaterialWindow.js;' +
						'OutStockGrid.js;' +
						'OutStockDetailGrid.js;' +
                        'OutStockDetailEditorGrid.js', {
							basedir : 'js/org/kyerp/warehouse'
						});
				this.body = new org.kyerp.warehouse.OutStockPanel({
							border : false,
							bodyBorder : false
						});
				this.main.add(this.body);
				this.main.doLayout();
			}
		});