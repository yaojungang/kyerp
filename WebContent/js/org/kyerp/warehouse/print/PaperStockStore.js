/** ***************************************************************************** */
org.kyerp.warehouse.print.PaperStockStore = new Ext.data.Store( {
	autoLoad : {
		baseParams : {
			limit : 20
		}
	},
	url : org.kyerp.warehouse.print.PaperStockListPanel_STORE_URL,
	reader : new Ext.data.JsonReader( {
		totalProperty : "totalProperty",
		root : "rows",
		idProperty : "id"
	}, new Ext.data.Record.create( [ {
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
		name : "updateTime",
		type : "date",
		dateFormat : "Y-m-d H:i:s"
	}, {
		name : "totalAmount",
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
		name : 'price',
		type : 'float'
	}, {
		name : 'cost',
		type : 'float'
	}, {
		name : "remark",
		type : "string"
	}, {
		name : "details"
	}, {
		name : "ownerId",
		type : "int"
	}, {
		name : "ownerName",
		type : "string"
	} ]))
});