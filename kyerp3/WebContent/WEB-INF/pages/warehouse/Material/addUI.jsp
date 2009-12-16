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
<form action="add.html" method="post">
<label>分类</label>
<select name="materialCategory.id">
<c:forEach items="${materialCategories}" var="category">
<option value="${category.id}" <c:if test="${category.id == entry.parentMaterialCategory.id}">selected="selected"</c:if> >[${category.serialNumber}]${category.name }</option>
</c:forEach>
</select><br />
<label>名称</label><input type="text" name="name" /><br />
<label>规格</label><input type="text" name="specification" /><br />
<label>品牌</label>
<select name="brand.id">
 <c:forEach items="${brands}" var="b">
  <option value="${b.id}">${b.nameSpell} ${b.name}</option>
 </c:forEach>
</select>
<br />
<label>默认仓库</label><select name="warehouse.id">
<c:forEach items="${warehouses}" var="w">
<option value="${w.id}">${w.serialNumber} [${w.name}]</option>
</c:forEach>
</select><br />
<label>默认供应商</label><select name="supplier.id">
  <c:forEach items="${suppliers}" var="s">
  <option value="${s.id}">${s.nameSpell} [${s.name}]</option>
  </c:forEach>
  </select>
<input type="submit" value="提交">
</form>
</body>
</html>