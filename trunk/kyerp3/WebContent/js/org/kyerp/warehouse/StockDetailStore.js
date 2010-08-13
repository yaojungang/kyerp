/** ***************************************************************************** */
org.kyerp.warehouse.StockDetailStore = new Ext.data.Store({
			url : org.kyerp.warehouse.StockDetail_URL,
			reader : new Ext.data.JsonReader({
						totalProperty : "totalProperty",
						root : "rows",
						idProperty : "id"
					}, new Ext.data.Record.create([{
								name : "id",
								type : "int"
							}, {
								name : "batchNumber",
								type : "string"
							}, {
								name : "createTime",
								type : "date",
								dateFormat : "Y-m-d H:i:s"
							}, {
								name : "updateTime",
								type : "date",
								dateFormat : "Y-m-d H:i:s"
							}, {
								name : "amount",
								type : 'float'
							}, {
								name : "materialId",
								type : "int"
							}, {
								name : "materialName",
								type : "string"
							}, {
								name : 'unitId',
								type : 'int'
							}, {
								name : 'unitName',
								type : 'string'
							}, {
								name : 'warehouseId',
								type : 'int'
							}, {
								name : 'warehouseName',
								type : 'string'
							}, {
								name : 'price',
								type : 'float'
							}, {
								name : 'cost',
								type : 'float'
							}, {
								name : 'cost',
								type : 'float'
							}]))
		});