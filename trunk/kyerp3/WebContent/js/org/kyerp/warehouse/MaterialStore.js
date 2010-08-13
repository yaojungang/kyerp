/** ***************************************************************************** */
org.kyerp.warehouse.MaterialStore = new Ext.data.Store({
			url : org.kyerp.warehouse.MaterialPanel_STORE_URL,
			reader : new Ext.data.JsonReader({
						totalProperty : "totalProperty",
						root : "rows",
						idProperty : "id"
					}, new Ext.data.Record.create([{
								name : "id",
								type : "int"
							}, {
								name : "serialNumber",
								type : "string"
							}, {
								name : "name",
								type : "string"
							}, {
								name : 'materialName',
								type : 'string'
							}, {
								name : "amount",
								type : "float"
							}, {
								name : "materialCategoryId",
								type : "int"
							}, {
								name : "materialCategoryName",
								type : "string"
							}, {
								name : 'specification',
								type : 'string'
							}, {
								name : 'brandId',
								type : 'int'
							}, {
								name : 'brandName',
								type : 'string'
							}, {
								name : 'unitId',
								type : 'int'
							}, {
								name : 'unitName',
								type : 'string'
							}, {
								name : 'price',
								type : 'float'
							}, {
								name : 'supplierId',
								type : 'int'
							}, {
								name : 'supplierName',
								type : 'string'
							}]))
		});