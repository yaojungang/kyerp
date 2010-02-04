<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QYYT-D-8.2.4-04-装订成品检验记录</title>
<link href="${pageContext.request.contextPath}/Library/css/ISOForm.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/Library/js/showPages.js"></script>
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/Library/js/DatePicker/WdatePicker.js"></script>
</head>
<body>
<h1>QYYT-D-8.2.4-07-产成品检验记录</h1>
<div style="margin: 10px;">
<form action="noSkin_getByDateRange.action" method="get">日期范围：
<input id="startDate" name="startDate" size="8" /><img onclick="WdatePicker({el:'startDate',dateFmt:'yyyy-MM-dd'})" src="${pageContext.request.contextPath}/Library/js/DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"> - 
<input id="endDate" name="endDate" size="8" /><img onclick="WdatePicker({el:'endDate',dateFmt:'yyyy-MM-dd'})" src="${pageContext.request.contextPath}/Library/js/DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
  <input type="submit" value="提交"/></form>
</div>
<table border="1" cellpadding="6" cellspacing="0">
<tr>
  <td>ID</td>
  <td>任务单号</td>
  <td>印品名称</td>  
  <td>不合格数</td>
  <td>墨  色</td>
  <td>文字印迹</td>
  <td>套  印</td>
  <td>外  观</td>
  <td>封面、插页</td>
  <td>书页与书贴</td>
  <td>胶 粘  钉</td>
  <td>铁丝平钉</td>
  <td>骑 马  钉</td>
  <td>成品</td>
  <td>综合结论</td>
  <td>检验员</td>
</tr>
<c:forEach items="${list}" var="item">
 <tr>
   <td>${item.id}</td>
   <td><a href="${pageContext.request.contextPath}/AF/AFInfo.action?afId=${item.afBase.afId}">${item.afBase.iso}${item.afBase.afNo}</a></td>
   <td>${item.afBase.presswork}</td>   
   <td>${item.unqualifiedAmount}</td>
   <td>${item.examItem01}</td>
   <td>${item.examItem02}</td>
   <td>${item.examItem03}</td>
   <td>${item.examItem04}</td>
   <td>${item.examItem05}</td>
   <td>${item.examItem06}</td>
   <td>${item.examItem07}</td>
   <td>${item.examItem08}</td>
   <td>${item.examItem09}</td>
   <td>${item.examItem10}</td>
   <td><a href="edit.action?afId=${item.afBase.afId}&id=${item.id}">${item.examResult}</a></td>
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