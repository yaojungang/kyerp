<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<h1>BuyerOfEnteringMaterial</h1>
<form action="save.html" method="post">
<label>供应商</label><input type="text" name="supplier" /><br />
<label>采购员</label><input type="text" name="buyerId" /><br />
<input type="submit" value="提交">
</form>
</body>
</html>