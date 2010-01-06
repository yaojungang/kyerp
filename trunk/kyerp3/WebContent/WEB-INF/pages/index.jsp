<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/WEB-INF/pages/share/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
 "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>KYERP</title>
<SCRIPT> 
function switchSysBar() 
{ 
if (switchPoint.innerText==3) 
{ 
switchPoint.innerText=4 
document.all("page_left").style.display="none";
document.all("page_main").className="fluid tc page-main-height";

} 
else 
{ 
switchPoint.innerText=3 
document.all("page_left").style.display="";
} 
} 
</SCRIPT> 
</head>
<body>

<div class="main gridlines">
<div class="hp tc page-header">
<h2>Header</h2>
</div>
<div class="clear"></div>
<div id="page_left" class="dl20 tc page-main-height page-left">

<div id="menu_tree">
<ul>
 <li><a href="${ctx}/index.jsp">首页</a></li>
 <li>采购管理
    <ul><li><a href="${ctx}/warehouse/BuyerOfEnteringMaterial/index.html" target="mainIframe">采购入库单</a></li></ul>
 </li>
 <li><a href="${ctx}/warehouse/index.html" target="mainIframe">物料管理</a>
 <ul>
  <li><a href="${ctx}/warehouse/EnteringMaterial/index.html" target="mainIframe">入库单管理</a></li>
  <li><a href="${ctx}/warehouse/DeliveryMaterial/index.html" target="mainIframe">出库单管理</a></li>
  <li><a href="${ctx}/warehouse/MaterialCategory/index.html" target="mainIframe">物料类别管理</a></li>
  <li><a href="${ctx}/warehouse/Material/index.html" target="mainIframe">物料档案管理</a></li>
  <li><a href="${ctx}/warehouse/Supplier/index.html" target="mainIframe">供应商管理</a></li>
  <li><a href="${ctx}/warehouse/Brand/index.html" target="mainIframe">品牌管理</a></li>
  <li><a href="${ctx}/warehouse/Warehouse/index.html" target="mainIframe">仓库管理</a></li>
  <li><a href="${ctx}/warehouse/PaperOfMaterial/index.html" target="mainIframe">纸张管理</a></li>
 </ul>
 </li>
 <li><a href="#" target="mainIframe">业务管理</a></li>
 <li><a href="#" target="mainIframe">生产管理</a></li>
 <li><a href="#" target="mainIframe">系统设置</a></li>
</ul>
</div>

</div>
<div><SPAN class=navPoint id=switchPoint title=关闭/打开左栏  style="HEIGHT: 100%;CURSOR: hand;" ONCLICK=switchSysBar()>3</SPAN></div>
<div id="page_main" class="fluid tc ml20 page-main-height">
<div id="page-mainContent"><iframe id="mainIframe"
 name="mainIframe" style="width: 100%; height: 100%;" src="main.html"
 frameborder="0" scrolling=yes
></iframe></div>
</div>
<div class="clear"></div>
<div class="hp tc page-footer">
<h2>Footer</h2>
</div>
</div>
</body>
</html>