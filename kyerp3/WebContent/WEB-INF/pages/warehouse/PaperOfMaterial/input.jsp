<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加纸张</title>
</head>
<body>
<h1>添加纸张</h1>
<form action="save.html" method="post">
<li><label>克重</label><input type="text" name="paperWeight" /></li>
<li><label>供应商</label><input type="text" name="supplierId" value="1" /></li>
<li><label>纸张品牌</label><input type="text" name="brandId" value="1" /></li>
<li><label>纸张名称</label><input type="text" name="paperName" /></li>
<li><label>纸张大小</label><input type="text" name="paperSize" /></li>
<li><label>纸张规格</label><input type="text" name="paperType" /></li>
<li><label>纸长(mm)</label><input type="text" name="paperHeight" /></li>
<li><label>纸宽(mm)</label><input type="text" name="paperWidth" /></li>
<li><label>吨价</label><input type="text" name="tonnePrice" /></li>
<li><label>每张价格</label><input type="text" name="price" /></li>
<li><label>每平米价格</label><input type="text" name="squareMetrePrice" /></li>
<input type="submit" value="提交">
</form>
</body>
</html>