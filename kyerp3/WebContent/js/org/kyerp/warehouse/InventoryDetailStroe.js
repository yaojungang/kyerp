/** ***************************************************************************** */
org.kyerp.warehouse.InventoryDetailStroe = new Ext.data.Store( {
	url : org.kyerp.warehouse.InventoryDetail_STORE_URL,
	reader : new Ext.data.JsonReader( {
		totalProperty : "totalProperty",
		root : "rows",
		idProperty : "id"
	}, new Ext.data.Record.create( [ {
		name : "id",
		type : "int"
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
		name : 'batchNumber',
		type : 'string'
	}, {
		name : 'serialNumber',
		type : 'string'
	}, {
		name : 'pressworkNo',
		type : 'string'
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
		name : 'inStockCount',
		type : 'float'
	}, {
		name : 'outStockCount',
		type : 'float'
	}, {
		name : 'begingStockCount',
		type : 'float'
	}, {
		name : 'currentStockCount',
		type : 'float'
	}, {
		name : 'inOutType',
		type : 'string'
	}, {
		name : 'cost',
		type : 'float'
	}, {
		name : "remark",
		type : "string"
	}, {
		name : "ownerId",
		type : "int"
	}, {
		name : "ownerName",
		type : "string"
	} ]))
});