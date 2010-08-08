/** ***************************************************************************** */
org.kyerp.warehouse.InventoryOwnerStore = new Ext.data.Store( {
	autoLoad : {
		baseParams : {
			limit : 20
		}
	},
	url : org.kyerp.warehouse.InventoryOwnerPanel_STORE_URL,
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
		name : "name",
		type : "string"
	} ]))
});