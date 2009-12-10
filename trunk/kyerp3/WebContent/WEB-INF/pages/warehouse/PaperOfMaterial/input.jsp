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
<form action="save.html" method="post">
<label>克重</label><input type="text" name="paperWeight" /><br />
<label>纸张品牌</label><input type="text" name="brandId" value="1" /><br />
<label>纸张名称</label><input type="text" name="paperName" /><br />
<label>纸张大小(全开，对开，四开)</label><input type="text" name="paperSize" /><br />
<label>纸张规格(正度，大度)</label><input type="text" name="paperType" /><br />
<label>纸宽(mm)</label><input type="text" name="paperWidth" /><br />
<label>纸长(mm)</label><input type="text" name="paperHeight" /><br />
<label>吨价</label><input type="text" name="tonnePrice" /><br />
<label>每张价格</label><input type="text" name="price" /><br />
<label>每平米价格</label><input type="text" name="squareMetrePrice" /><br />
<input type="submit" value="提交">
</form>
</body>
</html>