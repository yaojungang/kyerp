﻿[{
		id : "PaperOfMaterialStock",
		text : "纸张库存",
		expanded : false,
		children : [ {
			id : "PaperOfMaterialPanel",
			text : "纸张档案",
			ns : 'org.kyerp.warehouse.print',
			leaf : true
		},{
			id : "PaperInStockPanel",
			ns : 'org.kyerp.warehouse.print',
			text : "纸张入库",
			leaf : true
		}, {
			id : "PaperOutStockPanel",
			ns : 'org.kyerp.warehouse.print',
			text : "纸张出库",
			leaf : true
		}, {
			id : "PaperStockPanel",
			text : "纸张库存",
			ns : 'org.kyerp.warehouse.print',
			leaf : true
		} ]
	},{
	text : "库存管理",
	expanded : true,
	children : [ {
		id : "StoreFileManager",
		text : "库存档案",
		expanded : false,
		children : [{
			id : "MaterialCategoryPanel",
			text : "原料分类",
			ns : 'org.kyerp.warehouse',
			leaf : true
		}, {
			id : "MaterialPanel",
			ns : 'org.kyerp.warehouse',
			text : "原料档案",
			leaf : true
		} ]
	}, {
		id : "MaterialStock",
		text : "原料库存",
		expanded : false,
		children : [ {
			id : "InStockPanel",
			ns : 'org.kyerp.warehouse',
			text : "入库管理",
			leaf : true
		}, {
			id : "OutStockPanel",
			ns : 'org.kyerp.warehouse',
			text : "出库管理",
			leaf : true
		}, {
			id : "StockPanel",
			text : "库存状况",
			ns : 'org.kyerp.warehouse',
			leaf : true
		} ]
	}, {
		id : "WarehouseManager",
		text : "日常管理",
		expanded : false,
		children : [ {
			id : "InventoryPanel",
			ns : 'org.kyerp.warehouse',
			text : "库存盘点",
			leaf : true
		}, {
			id : "TransferPanel",
			ns : 'org.kyerp.warehouse',
			text : "仓库调拨",
			leaf : true
		}, {
			text : "统计汇总",
			leaf : true
		} ]
	}, {
		id : "WarehouseBasicInfo",
		text : "基础资料",
		expanded : false,
		children : [ {
			id : "WarehousePanel",
			ns : 'org.kyerp.warehouse',
			text : "仓库资料",
			leaf : true
		}, {
			id : "BrandPanel",
			ns : 'org.kyerp.warehouse',
			text : "品牌管理",
			leaf : true
		}, {
			id : "UnitPanel",
			ns : 'org.kyerp.warehouse',
			text : "计量单位",
			leaf : true
		}, {
			id : "InOutTypePanel",
			ns : 'org.kyerp.warehouse',
			text : "收发类别",
			leaf : true
		}, {
			id : "InventoryOwnerPanel",
			ns : 'org.kyerp.warehouse',
			text : "所有者",
			leaf : true
		} ]
	} ]
}, {
	id : "BuyerManager",
	text : "采购管理",
	expanded : false,
	children : [ {
		id : "PurchaseOrderPanel",
		text : "采购申请",
		ns : 'org.kyerp.warehouse',
		leaf : true
	}, {
		id : "BuyerOfInStockPanel",
		ns : 'org.kyerp.warehouse',
		text : "购货入库",
		leaf : true
	}, {
		id : "BuyerOfPayPanel",
		ns : 'org.kyerp.warehouse',
		text : "采购付款",
		leaf : true
	} ]
}, {
	id : "SupplierManager",
	ns : 'org.kyerp.warehouse',
	text : "供应商管理",
	expanded : false,
	children : [ {
		id : "SupplierPanel",
		text : "供应商档案",
		ns : 'org.kyerp.warehouse',
		leaf : true
	}, {
		id : "SupplierTypePanel",
		text : "供应商类型",
		ns : 'org.kyerp.warehouse',
		leaf : true
	} ]

}, {
	id : "SellManager",
	ns : 'org.kyerp.sell',
	text : "业务管理",
	expanded : false,
	children : [ {
		text : "销售订单",
		ns : 'org.kyerp.crm',
		leaf : true
	}, {
		text : "发行管理",
		ns : 'org.kyerp.crm',
		leaf : true
	}, {
		text : "销售收款",
		ns : 'org.kyerp.crm',
		leaf : true
	}, {
		text : "销售统计",
		ns : 'org.kyerp.crm',
		leaf : true
	}, {
		id : "EndItemsStock",
		text : "成品库存",
		expanded : false,
		children : [ {
			id : "EndItemsInStockPanel",
			ns : 'org.kyerp.warehouse',
			text : "入库管理",
			leaf : true
		}, {
			id : "EndItemsOutStockPanel",
			ns : 'org.kyerp.warehouse',
			text : "出库管理",
			leaf : true
		}, {
			id : "EndItemsCategoryPanel",
			text : "成品分类",
			ns : 'org.kyerp.warehouse',
			leaf : true
		}, {
			id : "EndItemsPanel",
			text : "成品档案",
			ns : 'org.kyerp.warehouse',
			leaf : true
		}, {
			id : "EndItemsStockPanel",
			text : "库存状况",
			ns : 'org.kyerp.warehouse',
			leaf : true
		} ]
	} ]

}, {
	id : "MAManager",
	ns : 'org.kyerp.sell',
	text : "生产管理",
	expanded : false,
	children : [ {
		text : "生产任务",
		ns : 'org.kyerp.crm',
		leaf : true
	}, {
		text : "生产统计",
		ns : 'org.kyerp.crm',
		leaf : true
	} ]

}, {
	id : "ClientManager",
	ns : 'org.kyerp.crm',
	text : "客户管理",
	expanded : false,
	children : [ {
		id : "ClientPanel",
		text : "客户档案",
		ns : 'org.kyerp.crm',
		leaf : true
	}, {
		id : "ClientTypePanel",
		text : "客户分类",
		ns : 'org.kyerp.crm',
		leaf : true
	} ]

}, {
	text : "人力资源管理",
	expanded : false,
	children : [ {
		id : 'DepartmentPanel',
		ns : 'org.kyerp.org',
		text : '组织机构',
		leaf : true
	}, {
		id : 'EmployeePanel',
		ns : 'org.kyerp.org',
		text : '职工档案',
		leaf : true
	} ]
}, {
	text : "系统管理",
	expanded : false,
	children : [ {
		id : 'SystemResourcesPanel',
		text : '系统资源',
		expanded : false,
		children : [ {
			id : 'SystemModulePanel',
			ns : 'org.kyerp.security',
			text : '模块定义',
			leaf : true
		}, {
			id : 'SystemResourcePanel',
			ns : 'org.kyerp.security',
			text : '资源管理',
			leaf : true
		} ]
	}, {
		text : '库存系统',
		expanded : false,
		children : [ {
			id : "BaseCategoryPanel",
			text : "库存总分类",
			ns : 'org.kyerp.warehouse',
			leaf : true
		} ]
	}, {
		text : '权限设置',
		expanded : false,
		children : [ {
			id : 'RolePanel',
			ns : 'org.kyerp.security',
			text : '职位角色',
			leaf : true
		}, {
			id : 'UserPanel',
			ns : 'org.kyerp.security',
			text : '用户管理',
			leaf : true
		} ]
	} ]
} ]