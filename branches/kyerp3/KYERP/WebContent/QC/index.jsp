<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>质量管理</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/Library/js/showPages.js"></script>
</head>
<body>
<h1>质量管理</h1>
<table border="1" cellpadding="6" cellspacing="0" bordercolor="black" width="100%">
<tr><td>任务单号</td><td>印品名称</td>
<td>毛样书记录</td>
<td>装订成品检查记录</td>
<td>产成品检验记录</td>
<td>（彩色）印刷品巡检记录</td>
<td>（单色）印刷品巡检记录</td>
<td>装订产品巡检记录</td>
</tr>
<c:forEach items="${list}" var="item">
<tr>
<td><a href="${pageContext.request.contextPath}/AF/AFInfo.action?afId=${item.afId}">${item.iso}${item.afNo}</a></td>
<td>${item.presswork}</td>
<td>
<c:if test="${not empty item.QYYTD82402}"><a href="${pageContext.request.contextPath}/QC/QYYTD82402/edit.action?afId=${item.afId}&id=${item.QYYTD82402.id}"><img border="0" src="${pageContext.request.contextPath}/Library/images/minico/yes.gif" /></a></c:if>
<c:if test="${empty item.QYYTD82402}"><a href="${pageContext.request.contextPath}/QC/QYYTD82402/add.action?afId=${item.afId}"><img border="0" src="${pageContext.request.contextPath}/Library/images/notFinish.gif" /></a></c:if>
</td>
<td>
<c:if test="${not empty item.QYYTD82404}"><a href="${pageContext.request.contextPath}/QC/QYYTD82404/edit.action?afId=${item.afId}&id=${item.QYYTD82404.id}"><img border="0" src="${pageContext.request.contextPath}/Library/images/minico/yes.gif" /></a></c:if>
<c:if test="${empty item.QYYTD82404}"><a href="${pageContext.request.contextPath}/QC/QYYTD82404/add.action?afId=${item.afId}"><img border="0" src="${pageContext.request.contextPath}/Library/images/notFinish.gif" /></a></c:if>
</td>
<td>
<c:if test="${not empty item.QYYTD82407}"><a href="${pageContext.request.contextPath}/QC/QYYTD82407/edit.action?afId=${item.afId}&id=${item.QYYTD82407.id}"><img border="0" src="${pageContext.request.contextPath}/Library/images/minico/yes.gif" /></a></c:if>
<c:if test="${empty item.QYYTD82407}"><a href="${pageContext.request.contextPath}/QC/QYYTD82407/add.action?afId=${item.afId}"><img border="0" src="${pageContext.request.contextPath}/Library/images/notFinish.gif" /></a></c:if>
</td>
<td>
<c:if test="${not empty item.QYYTD82408}"><a href="${pageContext.request.contextPath}/QC/QYYTD82408/edit.action?afId=${item.afId}&id=${item.QYYTD82408.id}"><img border="0" src="${pageContext.request.contextPath}/Library/images/minico/yes.gif" /></a></c:if>
<c:if test="${empty item.QYYTD82408}"><a href="${pageContext.request.contextPath}/QC/QYYTD82408/add.action?afId=${item.afId}"><img border="0" src="${pageContext.request.contextPath}/Library/images/notFinish.gif" /></a></c:if>
</td>
<td>
<c:if test="${not empty item.QYYTD82409}"><a href="${pageContext.request.contextPath}/QC/QYYTD82409/edit.action?afId=${item.afId}&id=${item.QYYTD82409.id}"><img border="0" src="${pageContext.request.contextPath}/Library/images/minico/yes.gif" /></a></c:if>
<c:if test="${empty item.QYYTD82409}"><a href="${pageContext.request.contextPath}/QC/QYYTD82409/add.action?afId=${item.afId}"><img border="0" src="${pageContext.request.contextPath}/Library/images/notFinish.gif" /></a></c:if>
</td>
<td>
<c:if test="${not empty item.QYYTD82410}"><a href="${pageContext.request.contextPath}/QC/QYYTD82410/edit.action?afId=${item.afId}&id=${item.QYYTD82410.id}"><img border="0" src="${pageContext.request.contextPath}/Library/images/minico/yes.gif" /></a></c:if>
<c:if test="${empty item.QYYTD82410}"><a href="${pageContext.request.contextPath}/QC/QYYTD82410/add.action?afId=${item.afId}"><img border="0" src="${pageContext.request.contextPath}/Library/images/notFinish.gif" /></a></c:if>
</td>

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