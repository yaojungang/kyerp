Ext.onReady(function() {
	Ext.BLANK_IMAGE_URL = 'images/s.gif';
	Ext.QuickTips.init();
	Ext.lib.Ajax.defaultPostHeader += ";charset=utf-8";
	// header部分
	var pageHeader = new Ext.Panel({
		region : 'north',
		margins : '0 0 5 0',
		border : false,
		html : '<div style="background:#dfe8f6; height:39px;"><h1 style="color:black;padding:10px 10px 10px 10px">酷印通2010</h1></div>',
		height : 39
	});
	// foot部分
	var pageFoot = new Ext.Panel({
		region : 'south',
		border : false,
		bbar : [{
			text : '开始',
			iconCls : 'icon-plugin',
			menu : new Ext.menu.Menu({
				items : [{
					text : '关于系统',
					iconCls : 'icon-cmp',
					handler : function() {
						new Ext.Window({
							closeAction : 'close',
							iconCls : 'icon-cmp',
							resizable : false,
							modal : true,
							title : '关于',
							html : '<div style="padding : 10px; font-size:12px;">本系统采用前台使用了ExtJs技术,所以实现了跨浏览器<br>'
									+ '本程序在IE6,IE7,FireFox3均测试通过!<br><br>主要技术: Spring + JPA(Hibernate) + ExtJs<br><br>'
									+ '数&nbsp;&nbsp;据&nbsp;&nbsp;库: MySQL</div>',
							width : 300,
							height : 250
						}).show();
					}
				}, {
					text : '退出系统',
					iconCls : 'icon-application_go',
					handler : function() {
						Ext.Msg.confirm('操作提示', '您确定要退出本系统?', function(btn) {
							if ('yes' == btn) {
								Ext.Ajax.request({
											url : 'logout',
											success : function() {
												this.window.opener = null;
												window.close();
												if (!Ext.isIE) {
													location = 'http://www.tyopf.com';
												}
											},
											failure : function() {
												Ext.Msg.alert("系统提示",
														"退出时发生错误！");
											}
										});
							}
						});
					}
				}]
			})
		}]
	});

	var warehouseMenuTree = new Ext.tree.TreePanel({
		border : false,
		rootVisible : false,
		iconCls : 'icon-application_go',
		root : new Ext.tree.AsyncTreeNode({
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
											text : "库存总账",
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
											id : "SupplierPanel",
											ns : 'org.kyerp.warehouse',
											text : "供应商",
											leaf : true
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
											id : "permissionType",
											ns : 'org.kyerp.warehouse',
											text : "缺货预警",
											leaf : true
										}, {
											id : "WarehousePanel",
											ns : 'org.kyerp.warehouse',
											text : "库房管理",
											leaf : true
										}, {
											id : "UnitPanel",
											ns : 'org.kyerp.warehouse',
											text : "计量单位",
											leaf : true
										}]
							}]
				})

	});
	var hrMenuTree = new Ext.tree.TreePanel({
				border : false,
				rootVisible : false,
				iconCls : 'icon-application_go',
				root : new Ext.tree.AsyncTreeNode({
							text : "人力资源管理",
							expanded : true,
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
						})

			});
	var securityMenuTree = new Ext.tree.TreePanel({
				border : false,
				rootVisible : false,
				iconCls : 'icon-application_go',
				root : new Ext.tree.AsyncTreeNode({
							text : "系统管理",
							expanded : true,
							children : [{
										id : 'DepartmentPanel',
										ns : 'org.kyerp.org',
										text : '部门管理',
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
										expanded : true,
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
						})

			});
	// leftMenu部分
	var leftMenu = new org.kyerp.LeftMenu({
				title : 'KYERP',
				trees : [warehouseMenuTree, hrMenuTree, securityMenuTree]
			});
	// 主工作区部分 MainTab
	var mainTab = new org.kyerp.MainPanel({
		margins : '0 5 5 0',
		autoScroll : true,
		region : 'center',
		deferredRender : false,
		activeTab : 0,
		items : [{
			title : '系统首页',
			iconCls : 'icon-root-s',
			xtype : 'portal',
			items : [{
						columnWidth : .7,
						style : 'padding:10px 0 10px 10px',
						items : [{
									title : '公告通知',
									layout : 'fit'
								}, {
									title : '今日任务单',
									layout : 'fit'
								}, {
									title : '待完成生产任务',
									html : ''
								}]
					}, {
						columnWidth : .3,
						style : 'padding:10px 10px 10px 10px',
						items : [{
							title : '个人信息',
							html : '<iframe id="frame1" src="share/user.jsp" frameborder="0" width="100%" height="100%"></iframe>'
						}, {
							title : '即时贴',
							html : ''
						}, {
							title : '在线用户',
							html : ''
						}]
					}]
		}]
	});
	// 建立leftMenu 和 MainTab 之间的关系
	leftMenu.on("nodeClick", function(nodeAttr) {
				mainTab.loadTab(nodeAttr);
			});
	// 创建布局
	var viewport = new Ext.Viewport({
				layout : 'border',
				items : [pageHeader, pageFoot, leftMenu, mainTab]
			});

	Ext.override(Ext.data.Connection, {
				handleResponse : Ext.data.Connection.prototype.handleResponse
						.createInterceptor(function(response) {

									var sessionStatus = response
											.getResponseHeader("sessionStatus");
									if (sessionStatus != "YES") {
										Ext.Msg.alert('提示', '会话超时，请重新登录!',
												function(btn, text) {
													if (btn == 'ok') {
														location.reload();
													}
												});
									}
									var serverMessage = response
											.getResponseHeader("serverMessage");

									if (typeof(serverMessage) != "undefined") {
										alert(serverMessage);
									}
								})
			});

});