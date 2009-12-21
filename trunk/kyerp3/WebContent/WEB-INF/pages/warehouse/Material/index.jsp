<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物料档案管理</title>

<script language="JavaScript">
<!--
 //到指定的分页页面
 function topage(page){
  var form = document.getElementById("myForm");
  form.page.value=page;
  form.submit();
 }
 
 function allSelect(items){
  var form = document.getElementById("myForm");
  var state = form.allselectbox.checked;
  var length = items.length;
  if(length){
   for(var i=0;i<length;i++){
    items[i].checked=state;
   }
  }else{
   items.checked=state;
  }
 }
 function _action(methodName){
  var form = document.getElementById("myForm");
  form.action='del.html';
  form.submit();
 }
 function selectItem(items){
  var form = document.getElementById("myForm");
  var length = items.length;
  if(length){
   for(var i=0;i<length;i++){
    if(items[i].checked) return true;
   }
  }else{
   return items.checked;
  }
  return false;
 }
//-->
</script>
<script language="javascript" src="${ctx}/js/MaterialCateTreePanel.js"></script>
<script language="javascript">
Ext.onReady(function(){ 
 var _materialCategoryTreePanel = new MaterialCategoryTreePanel() ;
 _materialCategoryTreePanel.applyToMarkup("materialCategoryTreePanel");
 _materialCategoryTreePanel.root.expand();
 _materialCategoryTreePanel.on("click", function(node){

	});
	 
 
}) ;
</script>
</head>
<body>
<h1>物料档案管理</h1>
<table width="100%">
  <tr>
<td valign="top" width="200"><div id="materialCategoryTreePanel">物料分类列表</div></td><td valign="top">
<h2><a href="addUI.html">添加物料档案</a></h2>
<form action="index.html" method="post" id="myForm">
<input type="hidden" name="page" value="${pageView.currentpage}"/>
 <table width="98%" border="1" cellspacing="1" cellpadding="2" align="center">
    <tr ><td colspan="12" align="right">
     <%@ include file="/WEB-INF/pages/share/pager.jsp" %>
   </td></tr>
    <tr>
      <td> <div align="center">选择</div></td>
      <td> <div align="center">ID</div></td>
      <td> <div align="center">编号</div></td>
      <td> <div align="center">名称</div></td>
      <td> <div align="center">规格</div></td>
      <td> <div align="center">类别</div></td>
      <td> <div align="center">操作</div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pageView.records}" var="entry">
    <tr>
      <td> <div align="center"><input type="checkbox" name="ids" value="${entry.id}"></div></td>
      <td> <div align="center">${entry.id}</div></td>
      <td> <div align="center">${entry.serialNumber}</div></td>
      <td> <div align="center">${entry.name}</div></td>
      <td> <div align="center">${entry.specification}</div></td>
      <td> <div align="center">${entry.materialCategory.name}</div></td>
      <td> <div align="center"><a href="${ctx}/warehouse/BuyerOfEnteringMaterial/addToEnteringMaterial.html?id=${entry.id}">添加到入库单</a></div></td>
      <td> <div align="center"><a href="edit.html?id=${entry.id}">编辑</a></div></td>
 </tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr>
      <td colspan="12" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td width="10%"><input type="checkbox" onClick="javascript:allSelect(this.form.ids)" name="allselectbox">全选</td>
              <td width="85%"><input type="button" class="frm_btn" value="删除" onClick="_action('allUnLock')"></td>
          </tr>
        </table></td>
    </tr>
  </table>
 </form>
 </td>
</tr></table>
</body>
</html>