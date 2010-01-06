<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
 prefix="decorator"
%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ include file="/WEB-INF/pages/share/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/css/web.css"></link>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jsTree/lib/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/js/jsTree/jquery.tree.js"></script>
<script type="text/javascript" src="${ctx}/js/jsTree/plugins/jquery.tree.cookie.js"></script>
<title><decorator:title />-KYERP3</title>
<decorator:head />
<style type="text/css">
.page-top {
 height: 90px;
 border-bottom: 1px solid black;
}

.page-left {
 float: left;
 background-color: #BBB;
 height: 100%;
 width: 160px;
 padding-top: 10px;
}

.page-center {
 float: left;
}
</style>
</head>
<body>

<script type="text/javascript">

$(function () { 
 $("#basic_tree").tree({
  plugins : {
   cookie : { prefix : "erpMenuTree_" }
  },callback:{
	  onselect : function(node) {
           if (document.location.href != $(node)
             .children("a:eq(0)").attr("href")) {
            if ("leaf" == $(node).attr("class")
              || "last leaf" == $(node).attr(
                "class"))
             gotoPage($(node).children("a")
               .attr("href"));
           }
          }
	   }
 });
});

function gotoPage(url) {
  window.location = url;
}
</script>
<div class="page-top">KYERP System</div>
<div class="page-left" id="basic_tree">
<ul>
 <li id="phtml_0"><a href="${ctx}/index.jsp"><ins>&nbsp;</ins>首页</a></li>
 <li id="phtml_1"><a href="${ctx}/warehouse/index.html"><ins>&nbsp;</ins>物料管理</a>
 <ul>
  <li id="phtml_2"><a href="${ctx}/warehouse/MaterialCategory/index.html"><ins>&nbsp;</ins>材料类别管理</a></li>
  <li id="phtml_3"><a href="${ctx}/warehouse/Supplier/index.html"><ins>&nbsp;</ins>供应商管理</a></li>
  <li id="phtml_4"><a href="${ctx}/warehouse/Brand/index.html"><ins>&nbsp;</ins>品牌管理</a></li>
  <li id="phtml_5"><a href="${ctx}/warehouse/PaperOfMaterial/index.html" ><ins>&nbsp;</ins>纸张管理</a></li>
 </ul>
 </li>
 <li id="phtml_6"><a href="#"><ins>&nbsp;</ins>业务管理</a></li>
 <li id="phtml_7"><a href="#"><ins>&nbsp;</ins>生产管理</a></li>
 <li id="phtml_8"><a href="#"><ins>&nbsp;</ins>系统设置</a></li>
</ul>
</div>
<div class="page-center" id="content"><decorator:body /></div>

</body>

</html>