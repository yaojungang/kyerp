<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><decorator:title default="" />-北京市清华园胶印厂信息管理系统</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/Library/js/ext/resources/css/ext-all.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/ext/ext-all.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/ext/source/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript">
//主panel
MainPanel = function() {
	this.openTab = function(panel, id) {
		var o = (typeof panel == "string" ? panel : id || panel.id);
		var tab = this.getComponent(o);		
		if (tab) {
			this.setActiveTab(tab);
		} else if(typeof panel!="string"){
			panel.id = o;
			var p = this.add(panel);
			this.setActiveTab(p);
		}
	};
	this.closeTab = function(panel, id) {
		var o = (typeof panel == "string" ? panel : id || panel.id);
		var tab = this.getComponent(o);
		if (tab) {
			this.remove(tab);
		}
	};
	MainPanel.superclass.constructor.call(this, {
		id : 'main',
		region : 'center',
		margins : '0 5 5 0',
		resizeTabs : true,
		minTabWidth : 135,
		tabWidth : 135,
		enableTabScroll : true,
		activeTab : 0,
		items : {
			id : 'mainPage',
			title : '<decorator:title default="Welcom" />',
			closable : false,
			contentEl :'page-center',
			autoScroll : true
		}
	});
};
Ext.extend(MainPanel, Ext.TabPanel);
</script>
<script type="text/javascript">
	Ext.BLANK_IMAGE_URL = 'lib/ext/resources/images/default/s.gif';
	Ext.QuickTips.init();
	var main, menu, header;
	Ext.onReady( function() {
		header = new Ext.Panel( {
			border :false,
			region :'north',
			height:125,
			autoLoad : {
				url :'<%=request.getContextPath()%>/skin/extStyle/header.jsp'
			},
			autoScroll :false
		});
		left = new Ext.Panel( {
			border :true,
			title:'系统菜单',
			region :'west',
			split: true,
			collapsible:true,
			width:180,
			minSize:180,
			maxSize:280,
			autoLoad : {
				url :'<%=request.getContextPath()%>/skin/extStyle/left.jsp'
			},
			autoScroll :false
		});
		var tabsCenter = new Ext.TabPanel({
		    //renderTo: 'my-tabs',
		    activeTab: 0,
		    items:[
		        {contentEl:'page-center', title:'page-center'}
		    ]
		});
		main = new MainPanel();		
		bottom = new Ext.Panel( {
			border :false,
			region :'south',
			height :'80',
			contentEl :'page-bottom',
			baseCls :"x-plain"
		});
		center = new Ext.Panel( {
			border :true,
			region :'center',
			split: true,
			collapsible:true,
			//contentEl :'page-center',
			autoScroll :true
		});

		var viewport = new Ext.Viewport( {
			layout :'border',
			items : [ header, left, bottom, main ]
		});

	});
</script>
<decorator:head />
</head>
<body>
<div id="page-head"></div>
<div id="page-center" class="x-hide-display"><decorator:body /></div>
<div id="page-bottom" class="x-hide-display"
	style="padding: 8px; height: 18px; text-align: center;">Copyright
&reg;2005-2008 北京市清华园胶印厂 erp.tyopf.com All Rights Reserved</div>
</body>
</html>
