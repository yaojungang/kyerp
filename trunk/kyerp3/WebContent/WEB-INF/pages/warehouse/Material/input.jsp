<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加物料档案</title>
</head>
<body>
<h1>添加物料档案</h1>
<form action="save.html" method="post">
<label>分类</label>
<select name="cateId">
<c:forEach items="${materialCategories}" var="category">
<option value="${category.id}" <c:if test="${category.id == entry.parentMaterialCategory.id}">selected="selected"</c:if> >[${category.serialNumber}]${category.name }</option>
</c:forEach>
</select>
<label>名称</label><input type="text" name="name" /><br />
<label>规格</label><input type="text" name="specification" /><br />
<input type="submit" value="提交">
</form>
</body>
</html>