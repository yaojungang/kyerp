<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"
%>
<%@ include file="/WEB-INF/jsp/share/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>酷印通3 - KYERP3</title>
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
<!-- 系统模块 -->
</head>
<body>
<div id="loading-mask" style=""></div>
  <div id="loading">
    <div class="loading-indicator"><img src="js/ext/resources/images/default/shared/blue-loading.gif" width="32" height="32" style="margin-right:8px;" />Loading...</div>
  </div>
    <!-- include everything after the loading indicator -->
 <!-- Extjs库 -->
<script type="text/javascript" src="js/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="js/ext/ext-all.js"></script>
<script type="text/javascript" src="js/ext/ext-lang-zh_CN.js"></script>
<!-- Extjs 自带插件 -->
<script type="text/javascript" src="js/ext/ux/ux-all-debug.js"></script>
<!-- Extjs patch -->
<script type="text/javascript" src="js/org/kyerp/share/ux/ComboBox-patch.js"></script>
<script type="text/javascript" src="js/org/kyerp/share/ux/ExtDate.js"></script>
<!-- Extjs 自定义插件 -->
<script type="text/javascript" src="js/org/kyerp/share/ux/Ext.ux.form.LovCombo.js"></script>
<script type="text/javascript" src="js/org/kyerp/share/ux/pPageSize.js"></script>
<script type="text/javascript" src="js/org/kyerp/share/ux/TreeComboBox.js"></script>
<!-- 自定义的文件 -->
<script type="text/javascript" src="js/org/kyerp/ns.js"></script>
<script type="text/javascript" src="js/org/kyerp/share/LeftMenu.js"></script>
<script type="text/javascript" src="js/org/kyerp/share/MainPanel.js"></script>
<script type="text/javascript" src="js/org/kyerp/share/Main.js"></script>
<!-- 系统模块 -->
<script type="text/javascript" src="js/org/kyerp/org/DepartmentPanel.js"></script>
<script type="text/javascript" src="js/org/kyerp/org/EmployeePanel.js"></script>
<script type="text/javascript" src="js/org/kyerp/security/SystemModulePanel.js"></script>
<script type="text/javascript" src="js/org/kyerp/security/SystemResourcePanel.js"></script>
<script type="text/javascript" src="js/org/kyerp/security/RolePanel.js"></script>
<script type="text/javascript" src="js/org/kyerp/security/UserPanel.js"></script>

<script type="text/javascript" src="js/org/kyerp/warehouse/UnitPanel.js"></script>
<script type="text/javascript" src="js/org/kyerp/warehouse/BrandPanel.js"></script>
<script type="text/javascript" src="js/org/kyerp/warehouse/SupplierPanel.js"></script>
<script type="text/javascript" src="js/org/kyerp/warehouse/WarehousePanel.js"></script>
<script type="text/javascript" src="js/org/kyerp/warehouse/MaterialCategoryPanel.js"></script>
<script type="text/javascript" src="js/org/kyerp/warehouse/MaterialListPanel.js"></script>
<script type="text/javascript" src="js/org/kyerp/warehouse/MaterialPanel.js"></script>
<script type="text/javascript" src="js/org/kyerp/warehouse/EnteringMaterialPanel.js"></script>
</body>
</html>