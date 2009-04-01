<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#session['privilegesList']" name="privilegesList"
	id="privilegesList" />
<s:set value="#session['user']" name="user" id="user" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><decorator:title default="" />-北京市清华园胶印厂信息管理系统</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/skin/newBlue/newStyle.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/Library/js/ext/resources/css/ext-all.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/ext/ext-all.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/ext/source/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript">
	Ext.BLANK_IMAGE_URL = 'lib/ext/resources/images/default/s.gif';
	Ext.QuickTips.init();
	var main, menu, header;
	Ext.onReady( function() {
		header = new Ext.Panel( {
			border :false,
			region :'north',
			height:125,
			el:'page-head',
			baseCls :"x-plain",
			autoScroll :false
		});
		left = new Ext.Panel( {
				region:'west',
				id:'west-panel',
				split:true,
				width: 200,
				minSize: 175,
				maxSize: 400,
				margins:'0 0 0 0',
				layout:'accordion',
				title:'系统菜单',
				collapseMode:'mini',//在分割线处出现按钮
				collapsible :true,
				layoutConfig:{
					animate:true
					},
			    items: [
				    {
						title:'新建任务单',
						border:false,
						html:new Ext.DatePicker()
						//html:'<div id="tree-div" style="overflow:auto;width:100%;height:100%"></div>'
						//iconCls:'nav'
				    },{
						title:'信息中心',
						border:false,
						//iconCls:'settings',
						html:'<div id="tree" style="overflow:auto;width:100%;height:100%"></div>'
				    },{
						title:'系统设置',
						border:false,
						//iconCls:'settings',
						html:'<div id="tree3" style="overflow:auto;width:100%;height:100%"></div>'
				    }]
		});

		bottom = new Ext.Panel( {
			border :false,
			region :'south',
			contentEl :'page-bottom',
			baseCls :"x-plain"
		});
		center = new Ext.Panel( {
			border :true,
			region :'center',
			split :true,
			collapsible :true,
			contentEl :'page-center',
			autoScroll :true
		});

		var viewport = new Ext.Viewport( {
			layout :'border',
			items : [ header, left, center, bottom ]
		});

	});
</script>
<decorator:head />
</head>
<body>
<div id="page-head">
<div id="logo"><img
	src="<%=request.getContextPath()%>/skin/newBlue/images/logo.jpg" /></div>
<div id="banner"><img
	src="<%=request.getContextPath()%>/skin/newBlue/images/logo-end.jpg" /></div>
<br class="clearfloat" />
<div id="menu" class="bluetabs">
<ul>
	<s:if test="#session.user==null">
		<li><a href="${pageContext.request.contextPath}" title="Home"><span>首页</span></a></li>
		<li><a href="${pageContext.request.contextPath}" title="jianjie"><span>系统简介</span></a></li>
		<li><a href="${pageContext.request.contextPath}" title="Contact"><span>帮助</span></a></li>
	</s:if>
	<s:else>
		<li><a href="${pageContext.request.contextPath}/" title="Home"><span>首页</span></a></li>
		<s:if
			test="#user.userType.equals('Admin') or 'OPEUser' in #privilegesList">
			<li><a
				href="${pageContext.request.contextPath}/Client/index.action"
				class="head" rel="client"><span>客户管理</span></a></li>
			<li><a
				href="${pageContext.request.contextPath}/OPE/index.action"
				class="head" rel="ope"><span>业务管理</span></a></li>
		</s:if>
		<s:if
			test="#user.userType.equals('Admin') or 'MAUser' in #privilegesList">
			<li><a href="${pageContext.request.contextPath}/MA/index.action"
				class="head" rel="ma"><span>生产管理</span></a></li>
			<li><a href="${pageContext.request.contextPath}/MR/index.jsp"
				class="head" rel="mr"><span>物资管理</span></a></li>
		</s:if>
		<s:if
			test="#user.userType.equals('Admin') or 'FAUser' in #privilegesList">
			<li><a href="${pageContext.request.contextPath}/FA/index.action"
				class="head" rel="fa"><span>财务管理</span></a></li>
		</s:if>
		<s:if
			test="#user.userType.equals('Admin') or 'UserAdmin' in #privilegesList">
			<li><a
				href="${pageContext.request.contextPath}/User/getAllEmployee.action"
				class="head" rel="employee"><span>人员管理</span></a></li>
			<li><a
				href="${pageContext.request.contextPath}/User/index.action"
				class="head" rel="user"><span>用户管理</span></a></li>
		</s:if>
	</s:else>
</ul>
</div>
<br class="clearfloat" />
</div>
<div id="page-center"><decorator:body /></div>
<div id="page-bottom">
<div
	style="float: left; margin: 5px; font: normal 12px tahoma, arial, sans-serif, 宋体;">
Copyright &reg; 2005-2009</div>
<div
	style="float: right; margin: 5px; font: normal 12px tahoma, arial, sans-serif, 宋体;">
<span style="color: blue">北京市清华园胶印厂 </span> All Rights Reserved</div>
</div>
</body>
</html>
