<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Presswork</title>
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
  form.action='${ctx}/Print/Presswork/del.html';
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
Presswork:${presswork.name} ${presswork.createTime}<br />
Paper:${paper.name}${paper.createTime} ${paper.paperHeight} ${paper.brand.name} ${paper.price }
<br />
<form action="${ctx}/Print/Presswork/list.html" method="post" id="myForm">
<input type="hidden" name="page" value="${page}"/>
 <table width="98%" border="1" cellspacing="1" cellpadding="2" align="center">
    <tr ><td colspan="12" align="right">
     <%@ include file="/WEB-INF/pages/share/pager.jsp" %>
   </td></tr>
    <tr>
      <td width="4%"> <div align="center">选择</div></td>
      <td width="8%"> <div align="center">ID</div></td>
      <td width="8%"> <div align="center">名称</div></td>
   <td> <div align="center">操作</div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pageView.records}" var="entry">
    <tr>
      <td> <div align="center"><input type="checkbox" name="ids" value="${entry.id}"></div></td>
      <td> <div align="center">${entry.id}</div></td>
      <td> <div align="center">${entry.name }</div></td>
      <td></td>
 </tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr>
      <td colspan="12" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
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