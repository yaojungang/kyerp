<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/share/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>酷印通印刷企业管理系统</title>
<link rel="SHORTCUT ICON" href="images/favicon.ico" />
<!-- Extjs库 -->
<link rel="stylesheet" type="text/css" href="js/ext/resources/css/ext-all.css"/>
<!-- Extjs 自带插件 -->
<link rel="stylesheet" type="text/css" href="js/ext/ux/css/ux-all.css" />
<!-- Extjs patch -->
<!-- Extjs 自定义插件 -->
<link rel="stylesheet" type="text/css" href="css/Ext.ux.form.LovCombo.css"/>
<!-- 自定义的文件 -->
<link rel="stylesheet" type="text/css" href="css/ext-patch.css" />
<link rel="stylesheet" type="text/css" href="css/ext-extend.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<!-- 系统模块 -->
</head>
<body>
<div id="loading">
<div class="loading-indicator"><img src="js/ext/resources/images/default/shared/blue-loading.gif" width="32" height="32" style="margin-right: 8px; float: left; vertical-align: top;"/> 
酷印通印刷企业管理系统 <br />
<span id="loading-msg">加载样式和图片...</span></div>
</div>
<script type="text/javascript">document.getElementById('loading-msg').innerHTML = '加载Extjs库 ...';</script>
<script type="text/javascript" src="js/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="js/ext/ext-all-debug.js"></script>
<script type="text/javascript" src="js/ext/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/ext/ux/ux-all-debug.js"></script>
<script type="text/javascript">document.getElementById('loading-msg').innerHTML = '加载 Extjs patch ...';</script>
<script type="text/javascript" src="js/org/kyerp/share/ux/ComboBox-patch.js"></script>
<script type="text/javascript">document.getElementById('loading-msg').innerHTML = '加载Extjs 自定义插件 ...';</script>
<script type="text/javascript" src="js/org/kyerp/share/ux/Ext.ux.Util.js"></script>
<script type="text/javascript" src="js/org/kyerp/share/ux/Ext.ux.form.LovCombo.js"></script>
<script type="text/javascript" src="js/org/kyerp/share/ux/pPageSize.js"></script>
<script type="text/javascript" src="js/org/kyerp/share/ux/TreeComboBox.js"></script>
<script type="text/javascript" src="js/org/kyerp/share/ux/Ext.ux.form.DateTime.js"></script>
<script type="text/javascript" src="js/org/kyerp/share/ux/Ext.ux.renderer.ComboRenderer.js"></script>
<script type="text/javascript">document.getElementById('loading-msg').innerHTML = '加载基本类库 ...';</script>
<script type="text/javascript" src="js/org/kyerp/share/Exception.js"></script>
<script type="text/javascript" src="js/org/kyerp/ns.js"></script>
<script type="text/javascript" src="js/org/kyerp/base.js"></script>
</body>
</html>