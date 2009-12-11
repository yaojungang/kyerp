<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>采购入库单-添加</title>
</head>
<body>
<h1>采购入库单-添加</h1>
<form action="add.html" method="post">
<label>供应商</label>
<select name="supplierId">
<c:forEach items="${suppliers}" var="s">
<option value="${s.id}">${s.nameSpell} [${s.name}]</option>
</c:forEach>
</select>
<br />

<input type="submit" value="提交">
</form>
</body>
</html>