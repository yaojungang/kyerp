<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QYYT-D-8.2.4-10-装订产品巡检记录</title>
<link href="${pageContext.request.contextPath}/Library/css/ISOForm.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/Library/js/showPages.js"></script>
</head>
<body>
<h1>QYYT-D-8.2.4-10-装订产品巡检记录</h1>
<table border="1" cellpadding="6" cellspacing="0">
<tr>
  <td>ID</td>
  <td>检查日期</td>
  <td>操作员</td>
  <td>任务单号</td>
  <td>印品名称</td>  
  <td>数量</td>
  <td>配页</td>
  <td>折页</td>
  <td>骑马钉</td>
  <td>胶订</td>
  <td>裁切成品</td>
  <td>结论</td>
  <td>不合格说明及处置</td>
  <td>检验员</td>
</tr>
<c:forEach items="${list}" var="item">
 <tr>
   <td>${item.id}</td>
   <td>${item.examDate}</td>
   <td>${item.operators}</td>
   <td><a href="${pageContext.request.contextPath}/AF/AFInfo.action?afId=${item.afBase.afId}">${item.afBase.iso}${item.afBase.afNo}</a></td>
   <td>${item.afBase.presswork}</td>   
   <td>${item.afBase.amount}</td>   
   <td>${item.examItem01}</td>
   <td>${item.examItem02}</td>
   <td>${item.examItem03}</td>
   <td>${item.examItem04}</td>
   <td>${item.examItem05}</td>
   <td><a href="edit.action?afId=${item.afBase.afId}&id=${item.id}">${item.examResult}</a></td>
   <td>${item.examEmployee.remark}</td>
   <td>${item.examEmployee.realname}</td>
 </tr>
</c:forEach>
</table>

<script language="JavaScript">
<!--
var pg = new showPages('pg');
pg.pageCount =${pager.totalPage};  // 定义总页数(必要)
pg.argName = 'currentPage';  // 定义参数名(可选,默认为page)
pg.printHtml(1);
//-->
</script>
</body>
</html>