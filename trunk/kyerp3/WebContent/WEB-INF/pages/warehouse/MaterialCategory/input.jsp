<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<h1>材料类别</h1>
<form action="save.html" method="post">
<c:if test="${!empty entry.id}"><input type="hidden" name="id" value="${entry.id}"/></c:if>
<label>编号</label><input type="text" name="serialNumber" value="${entry.serialNumber}" /><br />
<label>名称</label><input type="text" name="name" value="${entry.name}" /><br />
<label>摘要</label><input type="text" name="note" value="${entry.note}" /><br />
<label>父类别</label>
<select name="parentId">
<option value="0">顶级分类</option>
<c:forEach items="${materialCategories}" var="category">
<option value="${category.id}" <c:if test="${category.id == entry.parentMaterialCategory.id}">selected="selected"</c:if> >[${category.serialNumber}]${category.name }</option>
</c:forEach>
</select>
<br />
<input type="submit" value="提交">
</form>
</body>
</html>