<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>材料类别管理</title>
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
</head>
<body>

<h1>材料类别管理</h1>
<h2><a href="add.html?parentId=${parentId}">添加</a></h2>
<c:set var="navOut" value="" />
<c:if test="${!empty navMcs}">
<c:forEach items="${navMcs}" var="nav">
<c:set var="navOut" value="&gt; <a href='index.html?id=${nav.id}'>${nav.name}</a> ${navOut}"/>
</c:forEach>
</c:if>
<h5>导航：<a href="index.html">顶级分类</a><c:out value="${navOut}" escapeXml="false"/></h5>
<form action="index.html" method="post" id="myForm">
<input type="hidden" name="page" value="${pageView.currentpage}"/>
 <table width="98%" border="1" cellspacing="1" cellpadding="2" align="center">
    <tr ><td colspan="13" align="right">
     <%@ include file="/WEB-INF/pages/share/pager.jsp" %>
   </td></tr>
    <tr>
      <td> <div align="center">选择</div></td>
      <td> <div align="center">ID</div></td>
      <td> <div align="center">编号</div></td>
      <td> <div align="center">父类别</div></td>
      <td> <div align="center">类别名称</div></td>
      <td> <div align="center">子类别</div></td>
      <td> <div align="center">摘要</div></td>
      <td> <div align="center">操作</div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pageView.records}" var="entry">
    <tr>
      <td> <div align="center"><input type="checkbox" name="ids" value="${entry.id}"></div></td>
      <td> <div align="center">${entry.id}</div></td>
      <td> <div align="center">${entry.serialNumber}</div></td>
      <td> <div align="center">${entry.parentMaterialCategory.name }</div></td>
      <td> <div align="center"><a href="index.html?id=${entry.id}">${entry.name }</a></div></td>
      <td> <div align="center"><c:if test="${fn:length(entry.childMaterialCategories)>0}"><font color=red>(有${fn:length(entry.childMaterialCategories)}个子类)</font></c:if></div></td>
      <td> <div align="center">${entry.note}</div></td>
      <td><div align="center"><a href="edit.html?id=${entry.id}">编辑</a></div></td>
 </tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr>
      <td colspan="13" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td width="10%"><input type="checkbox" onclick="javascript:allSelect(this.form.ids)" name="allselectbox">全选</td>
              <td width="85%"><input type="button" class="frm_btn" value="删除" onclick="_action('allUnLock')"></td>
          </tr>
        </table></td>
    </tr>
  </table>
 </form>
</body>
</html>