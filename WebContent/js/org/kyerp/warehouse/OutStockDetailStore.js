/** ***************************************************************************** */
org.kyerp.warehouse.OutStockDetailStore = new Ext.data.Store({
           url : org.kyerp.warehouse.OutStockDetail_STORE_URL,
            reader : new Ext.data.JsonReader({
                        totalProperty : "totalProperty",
                        root : "rows",
                        idProperty : "id"
                    },new Ext.data.Record.create([{
                                name : "id",
                                type : "int"
                            }, {
                                name : "billCount",
                                type : "float"
                            }, {
                                name : "billCost",
                                type : "float"
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
                                name : "price",
                                type : "float"
                            }, {
                                name : "remark",
                                type : "string"
                            }, {
                                name : 'pressworkNo',
                                type : 'string'
                            }, {
                                name : "ownerId",
                                type : "int"
                            }, {
                                name : "ownerName",
                                type : "string"
                            }]))
        });