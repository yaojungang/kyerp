/** ***************************************************************************** */
org.kyerp.warehouse.WarehouseStore = new Ext.data.Store({
			autoLoad : {
				baseParams : {
					limit : 20
				}
			},
			url : org.kyerp.warehouse.Warehouse_ALL_LIST_URL,
			reader : new Ext.data.JsonReader({
						totalProperty : "totalProperty",
						root : "rows",
						id : "id"
					}, ['id', 'createTime', 'updateTime', 'name',
							'serialNumber', 'note', 'childWarehouseIds',
							'childWarehouseNames', 'parentWarehouseId',
							'parentWarehouseName'])
		});