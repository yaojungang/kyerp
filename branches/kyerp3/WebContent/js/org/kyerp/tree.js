[{
			text : "库存管理",
			expanded : true,
			children : [{
						id : "MaterialManager",
						text : "物料管理",
						expanded : true,
						children : [{
									id : "MaterialCategoryPanel",
									text : "物料分类",
									ns : 'org.kyerp.warehouse',
									leaf : true
								}, {
									id : "MaterialPanel",
									ns : 'org.kyerp.warehouse',
									text : "库存档案",
									leaf : true
								}]
					}, {
						id : "BuyerManager",
						text : "采购管理",
						expanded : true,
						children : [{
									id : "BuyerApplicationPanel",
									text : "采购申请",
									ns : 'org.kyerp.warehouse',
									leaf : true
								}, {
									id : "BuyerOfEnteringMaterialPanel",
									ns : 'org.kyerp.warehouse',
									text : "采购入库",
									leaf : true
								}, {
									id : "BrandPanel",
									ns : 'org.kyerp.warehouse',
									text : "品牌管理",
									leaf : true
								}, {
									id : "BuyTypePanel",
									ns : 'org.kyerp.warehouse',
									text : "采购类型",
									leaf : true
								}, {
									id : "SupplierManager",
									ns : 'org.kyerp.warehouse',
									text : "供应商",
									expanded : false,
									children : [{
												id : "SupplierPanel",
												text : "供应商档案",
												ns : 'org.kyerp.warehouse',
												leaf : true
											}, {
												id : "SupplierTypePanel",
												text : "供应商分类",
												ns : 'org.kyerp.warehouse',
												leaf : true
											}]

								}]
					}, {
						id : "WarehouseManager",
						text : "库存管理",
						expanded : true,
						children : [{
									id : "EnteringMaterialPanel",
									ns : 'org.kyerp.warehouse',
									text : "入库单",
									leaf : true
								}, {
									id : "DeliveryMaterialPanel",
									ns : 'org.kyerp.warehouse',
									text : "出库单",
									leaf : true
								}, {
									id : "MaterialDetailPanel",
									text : "库存流水",
									ns : 'org.kyerp.warehouse',
									leaf : true
								}, {
									id : "PaperOfMaterialPanel",
									text : "纸张管理",
									ns : 'org.kyerp.warehouse',
									leaf : true
								}, {
									id : "WarehousePanel",
									ns : 'org.kyerp.warehouse',
									text : "仓库档案",
									leaf : true
								}, {
									id : "UnitPanel",
									ns : 'org.kyerp.warehouse',
									text : "计量单位",
									leaf : true
								}, {
									id : "EnterOutPanel",
									ns : 'org.kyerp.warehouse',
									text : "收发类别",
									leaf : true
								}]
					}]
		}, {
			id : "ClientManager",
			ns : 'org.kyerp.crm',
			text : "客户管理",
			expanded : false,
			children : [{
						id : "ClientPanel",
						text : "客户档案",
						ns : 'org.kyerp.crm',
						leaf : true
					}, {
						id : "ClientTypePanel",
						text : "客户分类",
						ns : 'org.kyerp.crm',
						leaf : true
					}]

		}, {
			text : "人力资源管理",
			expanded : false,
			children : [{
						id : 'DepartmentPanel',
						ns : 'org.kyerp.org',
						text : '组织机构',
						leaf : true
					}, {
						id : 'EmployeePanel',
						ns : 'org.kyerp.org',
						text : '职工档案',
						leaf : true
					}]
		}, {
			text : "系统管理",
			expanded : false,
			children : [{
						id : 'DepartmentPanel',
						ns : 'org.kyerp.org',
						text : '部门档案',
						leaf : true
					}, {
						id : 'RolePanel',
						ns : 'org.kyerp.security',
						text : '职位角色',
						leaf : true
					}, {
						id : 'UserPanel',
						ns : 'org.kyerp.security',
						text : '用户管理',
						leaf : true
					}, {
						id : 'SystemResourcesPanel',
						text : '系统资源',
						expanded : false,
						children : [{
									id : 'SystemModulePanel',
									ns : 'org.kyerp.security',
									text : '模块定义',
									leaf : true
								}, {
									id : 'SystemResourcePanel',
									ns : 'org.kyerp.security',
									text : '资源管理',
									leaf : true
								}]
					}]
		}]