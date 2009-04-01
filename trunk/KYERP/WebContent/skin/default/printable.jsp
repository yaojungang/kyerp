<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<html>
<head>
<title>Print - <decorator:title default="Welcome!" /></title>
<decorator:head />
</head>
<body>
Printed on
<%=new java.util.Date()%>.
<br />
<hr noshade="noshade" size="1" />
<br />
<decorator:body />
</body>
</html>