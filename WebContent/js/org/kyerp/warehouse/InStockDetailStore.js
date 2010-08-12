/** ***************************************************************************** */
org.kyerp.warehouse.InStockDetailStore = new Ext.data.Store({
			url : org.kyerp.warehouse.InStockDetail_STORE_URL,
			reader : new Ext.data.JsonReader({
						totalProperty : "totalProperty",
						root : "rows",
						idProperty : "id"
					}, new Ext.data.Record.create([{
								name : "id",
								type : "int"
							}, {
								name : "billCount"
							}, {
								name : "billCost"
							}, {
								name : "materialId",
								type : "int"
							}, {
								name : "materialName",
								type : "string"
							}, {
								name : 'batchNumber',
								type : 'string'
							}, {
								name : "warehouseId",
								type : "int"
							}, {
								name : "warehouseName",
								type : "string"
							}, {
								name : "unitId",
								type : "int"
							}, {
								name : "unitName",
								type : "string"
							}, {
								name : "price"
							}, {
								name : "remark",
								type : "string"
							}, {
								name : "ownerId",
								type : "int"
							}, {
								name : "ownerName",
								type : "string"
							}])),
			listeners : {
				update : function(store, record, operation) {
					var _rData = record.data;
					record.set('billCost', Number(_rData.price
									* _rData.billCount).toFixed(2));

				}
			}
		});