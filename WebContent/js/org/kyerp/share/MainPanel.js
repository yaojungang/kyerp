org.kyerp.MainPanel = Ext.extend(Ext.TabPanel, {
			plugins : new Ext.ux.TabCloseMenu(),
			resizeTabs : true,
			minTabWidth : 98,
			tabWidth : 100,
			enableTabScroll : true,
			border : true,
			defaults : {
				autoScroll : true,
				iconCls : 'icon-style-tab'
			},
			initComponent : function() {
				// 一些初始化工作
				org.kyerp.MainPanel.superclass.initComponent.call(this);
				this._cache = {};

			},
			loadTab : function(node) {
				// 根据节点的id找到TabPanel中的子组件
				var n = this.getComponent(node.id);
				if (n) {
					this.setActiveTab(n);
				} else {
					// 如果没有找到就构建它
					var c = {
						'id' : node.id,
						'title' : node.text,
						closable : true
					};
					var pn = this.findPanel(node);
					n = this.add(pn ? new pn(c) : Ext.apply(c, {
								html : '<div style="padding:10px;">' + node.ns
										+ '.' + node.id + '<br />' + node.text
										+ '功能还没有实现，敬请期待！</div>'
							}))
					// 显示并进行布局
					n.show().doLayout();
				}
				if (n.ds)
					n.ds.load({
								params : {
									start : 0,
									limit : 10
								}
							});
			},
			findPanel : function(node) {
				// 从手动注册的集合中寻找
				var ret = this._cache[node.id];
				if (!ret) {
					// 采用指定的ns命名空间来构建，如果没有指定命名空间，采用默认的命名空间
					// pn就是返回的带命名空间的面板名称
					var pn = (node.ns ? node.ns : 'org.kyerp') + "."
							// + Ext.util.Format.capitalize(node.id) + 'Panel';
							+ node.id;
					var ret = eval(pn);
					// alert(pn);
				}
				// alert(ret);
				return ret;
			},
			addPanel : function(name, panel) {
				if (!this._cache)
					// 手动注册的Tab集合
					this._cache = {};
				this._cache[name] = panel;
			}
		});
