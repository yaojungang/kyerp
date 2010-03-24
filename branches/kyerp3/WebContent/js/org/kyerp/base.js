// 模块基类
org.kyerp.module = function(tab) {
	this.main = tab;
	this.init();
}
Ext.extend(org.kyerp.module, Ext.util.Observable, {
			init : Ext.emptyFn
		});
// 主程序类
org.kyerp.base = function() {
	this.init();
}
Ext.extend(org.kyerp.base, Ext.util.Observable, {
	init : function() {
		this.tree = new Ext.tree.TreePanel({
					region : 'west',
					title : '功能导航',
					// header : false,
					width : 150,
					minSize : 100,
					maxSize : 300,
					split : true,
					collapsible : true,
					margins : '0 0 5 5',
					cmargins : '0 0 0 0',
					rootVisible : false,
					autoScroll : true,
					animCollapse : false,
					animate : false,
					collapseMode : 'mini',
					loader : new Ext.tree.TreeLoader({
								url : 'js/org/kyerp/tree.js',
								requestMethod : 'GET'
							}),
					// 构造根节点
					rootVisible : false,
					root : new Ext.tree.AsyncTreeNode({
								id : 'root',
								text : '功能菜单',
								expanded : true
							})
				});
		this.tree.on('click', this.clickTree, this);

		// header部分
		this.pageHeader = new Ext.Panel({
			region : 'north',
			margins : '0 0 5 0',
			border : false,
			height : 39,
			html : '<div style="background:url(images/hd-bg.gif); height:39px;"><img src="images/logo.gif" /><div style="float:right;padding:10px 20px 0px 0px;color:#ffffff;">Version 3.0 bate</div></div>'			
		});
		// 首页
		this.indexTab = {
			xtype : 'portal',
			title : '首页',
			iconCls : 'icon-root-s',
			items : [{
				columnWidth : .5,
				style : 'padding:10px 0 10px 10px',
				defaults : {
					bodyStyle : 'padding:10px',
					tools : [{
								id : 'close',
								handler : function(e, target, panel) {
									panel.ownerCt.remove(panel, true);
								}
							}]
				},
				items : [{
					title : '清华大学焦点新闻',
					height:220,
					//xtype:'iframepanel',
					//defaultSrc:'http://news.tsinghua.edu.cn/new/headnews.php',
					//defaultHeight:220
					html : '<iframe src="http://news.tsinghua.edu.cn/new/headnews.php" frameborder="0" width="100%" height="100%"></iframe>'
				}, {
					title : '清华大学重要公告',
					height:200,
					html : '<iframe src="http://info.tsinghua.edu.cn/view/notice_beforelogin.htm" frameborder="0" width="100%" height="100%"></iframe>'
				}, {
					title : '清华大学最近七日信息汇总',
					height:115,
					collapsed : true,
					html : '<iframe src="http://oars.tsinghua.edu.cn/comm/news.nsf/portalpost?openview&count=10" frameborder="0" width="100%" height="100%"></iframe>'
				}]
			}, {
				columnWidth : .5,
				style : 'padding:10px 10px 10px 10px',
				defaults : {
					bodyStyle : 'padding:10px',
					tools : [{
								id : 'close',
								handler : function(e, target, panel) {
									panel.ownerCt.remove(panel, true);
								}
							}]
				},
				items : [{
					title : '清华大学办公通知',
					height:220,
					html : '<iframe src="http://oars.tsinghua.edu.cn/ztg/51029.nsf/portalpost?openview&count=100" frameborder="0" width="100%" height="100%"></iframe>'
				}, {
					title : '海报',
					height:200,
					html : '<iframe src="http://oars.tsinghua.edu.cn/ztg/92390.nsf/portalpost?openview&count=100" frameborder="0" width="100%" height="100%"></iframe>'
				}, {
					title : '个人信息',
					height:115,
					collapsed : true,
					autoLoad : {
						url : 'share/user.jsp'
					},
					scripts : true,
					disableCaching : true
				}]
			}]
		};
		//logout function
		this.logout = function() {
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
												location.reload();
//												Ext.Msg.alert("系统提示",
//														"退出时发生错误！");
											}
										});
							}
						});
			}
		// foot部分
		this.pageFoot = new Ext.Panel({
			region : 'south',
			border : false,
			bbar : [{
				text : '开始',
				iconCls : 'icon-plugin',
				menu : {
					xtype : 'menu',
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
						handler : this.logout
					}]
				}
			}, '->', '欢迎使用！', {
				text : '修改密码'
			}, {
				text : '退出',
				handler : this.logout
			}]
		});

		this.body = new Ext.TabPanel({
					region : 'center',
					margins : '0 5 5 0',
					activeTab : 0,
					autoScroll : true,
					enableTabScroll : true,
					defaults : {
						autoScroll : true
					},
					plugins : new Ext.ux.TabCloseMenu(),
					items : [this.indexTab]
				});
		this.body.on('tabchange', this.changeTab, this);

		var viewport = new Ext.Viewport({
					layout : 'border',
					items : [this.pageHeader, this.tree, this.body,
							this.pageFoot]
				});
		this.loadMask = new Ext.LoadMask(this.body.body);
	},
	// 切换TabPanel标签方法
	changeTab : function(p, t) {
		// 如果存在相应树节点，就选中,否则就清空选择状态
		var id = t.id.replace('tab-', '');
		var node = this.tree.getNodeById(id);
		if (node) {
			this.tree.getSelectionModel().select(node);
		} else {
			this.tree.getSelectionModel().clearSelections();
		}
	},

	// 点击菜单方法(node:被点击的节点)
	clickTree : function(node) {
		// 如果节点不是叶子则返回
		if (!node.isLeaf()) {
			return false;
		}
		var id = 'tab-' + node.id;
		var tab = Ext.getCmp(id);
		if (!tab) {
			tab = new Ext.Panel({
						id : id,
						title : node.text,
						layout : 'fit',
						border : false,
						bodyBorder : false,
						closable : true
					});
			this.body.add(tab);
			this.body.setActiveTab(tab);
			// 加载模块
			this.loadModel(node, tab);
		} else {
			this.body.setActiveTab(tab);
		}
	},

	// 加载模块方法(id:模块ID;tab:模块显示在哪里)
	loadModel : function(node, tab) {
		// alert(tab.getXTypes());
		var url = "";
		if (typeof node.attributes.ns != 'undefined') {
			url = node.attributes.ns + ".";
			url = url.replace(/\./g, "/");
		}
		url = url + node.id
		// 定义模块变量
		var model;
		if (this[node.id]) {
			// 如果模块类已存在，就直接实例化
			model = new this[node.id](tab);
		} else {
			this.loadMask.show();
			Ext.Ajax.request({
						method : 'GET',
						url : 'js/' + url + '.js',
						scope : this,
						success : function(response) {
							this[node.id] = eval(response.responseText);
							// 实例化模块类
							model = new this[node.id](tab);
							this.loadMask.hide();
						},
						failure : function() {
							this.loadMask.hide();
							Ext.Msg.alert('提示', '[' + node.text
											+ '] 尚未实现，敬请期待！');
							// 关闭tab
							this.body.remove(this.body.getActiveTab());
						}
					});
		}
	}
});

// 实例化主程序类
Ext.BLANK_IMAGE_URL = 'images/s.gif';
Ext.form.Field.prototype.msgTarget = 'side';
Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.lib.Ajax.defaultPostHeader += ";charset=utf-8";
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
	kyerpApp = new org.kyerp.base();
	setTimeout(function() {
				Ext.get('loading').remove();
			}, 250);
	// 超时重新登录
	Ext.override(Ext.data.Connection, {
		handleResponse : Ext.data.Connection.prototype.handleResponse
				.createInterceptor(function(response) {
							if (response.getResponseHeader("LOGINED") != "YES") {
								Ext.Msg.alert('提示', '会话超时，请重新登录!', function(
												btn, text) {
											if (btn == 'ok') {
												location.reload();
											}
										});
							};
							if ("200" != response.status) {
								alert(response.status);
							}
						})
	});
//Ext.DomHelper.append(Ext.getBody(), {tag: 'script',type:'text/javascript',src:'js/org/kyerp/warehouse/SelectSupplierWindow.js'});
				
});