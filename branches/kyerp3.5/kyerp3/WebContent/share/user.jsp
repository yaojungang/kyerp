<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ include file="/WEB-INF/jsp/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user</title>
</head>
<body>
用户名：
<sec:authentication property="name" /><br />
权限信息：<br />
<sec:authentication property="authorities" var="authorities" scope="page"/>
<c:forEach items="${authorities}" var="authority">
  ${authority.authority}<br />
</c:forEach>
</body>
</html>