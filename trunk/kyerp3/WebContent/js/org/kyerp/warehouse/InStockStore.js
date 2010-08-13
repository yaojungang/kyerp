/** ***************************************************************************** */
org.kyerp.warehouse.InStockStore = new Ext.data.Store({
			autoLoad : {
				baseParams : {
					limit : 20
				}
			},
			url : org.kyerp.warehouse.InStock_STORE_URL,
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
								name : "createTime",
								type : "date",
								dateFormat : "Y-m-d H:i:s"
							}, {
								name : "statusString",
								type : "string"
							}, {
								name : "writeDate",
								type : "date",
								dateFormat : "Y-m-d H:i:s"
							}, {
								name : "arriveDate",
								type : "date",
								dateFormat : "Y-m-d"
							}, {
								name : "billCount",
								type : 'float'
							}, {
								name : "billCost",
								type : "float"
							}, {
								name : "checkUserId",
								type : "int"
							}, {
								name : "checkUserName",
								type : "string"
							}, {
								name : "checkEmployeeId",
								type : "int"
							}, {
								name : "checkEmployeeName",
								type : "string"
							}, {
								name : "writeUserId",
								type : "int"
							}, {
								name : "writeUserName",
								type : "string"
							}, {
								name : "writeEmployeeId",
								type : "int"
							}, {
								name : "writeEmployeeName",
								type : "string"
							}, {
								name : "details"
							}, {
								name : "supplierId",
								type : "int"
							}, {
								name : "supplierName",
								type : "string"
							}, {
								name : "inOutTypeId"
							}, {
								name : "inOutTypeName"
							}, {
								name : "remark",
								type : "string"
							}, {
								name : "editAble"
							}, {
								name : 'keeperId',
								type : "int"
							}, {
								name : "keeperName",
								type : "string"
							}]))
		});