<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
KYERP3
<a href="${pageContext.request.contextPath}/Print/Presswork/Presswork.html">presswork</a>
<br />
${pageContext.request.contextPath}
<br />
Expression
 说明<br />
 
${pageContext.request.queryString}
 取得请求的参数字符串<br />
 
${pageContext.request.requestURL}
 取得请求的URL，但不包括请求之参数字符串,即servlet的HTTP地址。<br />
 
${pageContext.request.contextPath}
 服务的webapplication的名称
 <br />
${pageContext.request.method}
 取得HTTP的方法(GET、POST)
 <br />
${pageContext.request.protocol}
 取得使用的协议(HTTP/1.1、HTTP/1.0)
 <br />
${pageContext.request.remoteUser}
 取得用户名称
 <br />
${pageContext.request.remoteAddr}
 取得用户的IP地址
 <br />
${pageContext.session.new}
 判断session是否为新的，所谓新的session，表示刚由server产生而client尚未使用
 <br />
${pageContext.session.id}
 取得session的ID
 <br />
${pageContext.servletContext.serverInfo}
 取得主机端的服务信息
 <br />

</body>
</html>
