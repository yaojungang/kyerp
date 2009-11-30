<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QYYT-D-8.2.4-04-装订成品检验记录</title>
<link href="${pageContext.request.contextPath}/Library/css/ISOForm.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table>
<th><td>印品名称</td><td>任务单号</td></th>
<c:forEach items="${list}" var="item">
 <th><td>${item.afBase.presswork}</td><td>任务单号</td></th>
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