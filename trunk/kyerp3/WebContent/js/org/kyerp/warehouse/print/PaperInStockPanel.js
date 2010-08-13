/** ***************************************************************************** */
org.kyerp.warehouse.print.PaperInStockPanel = Ext.extend(Ext.Panel, {
			store : org.kyerp.warehouse.InStockStore,
			layout : 'border',
			listGrid : null,
			detailGrid : null,
			border : false,
			initComponent : function() {
				this.listGrid = new org.kyerp.warehouse.InStockGrid(
						{
							region : 'center',
							autoWidth : true,
							border : false
						});
				this.detailGrid = new org.kyerp.warehouse.InStockDetailGrid(
						{
							height : 80,
							region : 'south',
							split : true,
							border : false
						});
				this.items = [this.listGrid, this.detailGrid];
				org.kyerp.warehouse.print.PaperInStockPanel.superclass.initComponent
						.call(this);
			}
		});

/** ***************************************************************************** */
Ext.extend(org.kyerp.module, {
			init : function() {
				require('WarehouseStore.js;' +
						'MaterialStore.js;' +
						'InventoryOwnerStore.js;' +
						'InStockDetailStore.js;' +
						'InStockStore.js;' +
						'SelectSupplierWindow.js;' +
						'SelectMaterialWindow.js;' +
						'InStockGrid.js;' +
						'InStockDetailGrid.js;'
						+ 'InStockDetailEditorGrid.js', {
							basedir : 'js/org/kyerp/warehouse'
						});
				this.body = new org.kyerp.warehouse.print.PaperInStockPanel({
							border : false,
							bodyBorder : false
						});
				this.main.add(this.body);
				this.main.doLayout();
			}
		});