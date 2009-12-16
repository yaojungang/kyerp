<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加纸张</title>
</head>
<body>
<h1>添加纸张品种</h1>
<form action="add.html" method="post">
<label>类别</label>
<select name="materialCategory.id">
<c:forEach items="${materialCategories}" var="mc">
<option value="${mc.id}">${mc.serialNumber} ${mc.name}</option>
</c:forEach>
</select><br />
<label>克重</label><input type="text" name="paperWeight" /><br />
<label>品牌</label>
<select name="brand.id">
 <c:forEach items="${brands}" var="b">
  <option value="${b.id}">${b.nameSpell} ${b.name}</option>
 </c:forEach>
</select>
<br />
<label>纸张名称</label><input type="text" name="paperName" /><br />
<label>纸张大小(全开，对开，四开)</label><input type="text" name="paperSize" /><br />
<label>纸张规格(正度，大度)</label><input type="text" name="paperType" /><br />
<label>纸宽(mm)</label><input type="text" name="paperWidth" /><br />
<label>纸长(mm)</label><input type="text" name="paperHeight" /><br />
<label>吨价</label><input type="text" name="tonnePrice" /><br />
<label>每张价格</label><input type="text" name="price" /><br />
<label>每平米价格</label><input type="text" name="squareMetrePrice" /><br />
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