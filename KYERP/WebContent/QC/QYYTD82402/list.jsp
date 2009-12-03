<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QYYT-D-8.2.4-02-毛样书记录</title>
<link href="${pageContext.request.contextPath}/Library/css/ISOForm.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/Library/js/showPages.js"></script>
</head>
<body>
<h1>QYYT-D-8.2.4-02-毛样书记录</h1>
<table border="1" cellpadding="6" cellspacing="0">
<tr>
  <td>ID</td>
  <td>任务单号</td>
  <td>印品名称</td>  
  <td>项目</td>
  <td>负责人</td>
</tr>
<c:forEach items="${list}" var="item">
 <tr>
   <td>${item.id}</td>
   <td><a href="${pageContext.request.contextPath}/AF/AFInfo.action?afId=${item.afBase.afId}">${item.afBase.iso}${item.afBase.afNo}</a></td>
   <td>${item.afBase.presswork}</td>   
   <td><a href="${pageContext.request.contextPath}/QC/InspectionRecordsOfSemifinshed/edit.action?afId=${item.afBase.afId}&id=${item.id}">${item.item}</a></td>
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