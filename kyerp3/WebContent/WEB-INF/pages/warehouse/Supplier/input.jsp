<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加供应商</title>
</head>
<body>
<h1>添加供应商</h1>
<form action="save.html" method="post">
<li><label>供应商简称</label><input type="text" name="name" /></li>
<li><label>供应商简拼</label><input type="text" name="nameSpell" /></li>
<li><label>供应商全称</label><input type="text" name="fullName" /></li>
<input type="submit" value="提交">
</form>
</body>
</html>