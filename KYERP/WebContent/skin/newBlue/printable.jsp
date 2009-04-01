<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>打印-<decorator:title default="Welcome!" /></title>
<decorator:head />
</head>
<body onLoad="window.print();window.opener=null;window.close();">
<decorator:body />
<div class="printer">
<hr noshade="noshade" size="1" />
<span class="bottomtext">北京市清华园胶印厂-酷印通 http://erp.tyopf.com | </span> <span
	class="bottomtext">打印者: <s:property
	value="#session.user.employee.realname" /></span> <span class="bottomtext">打印时间:
<%
     String t = (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
 		    .format(new java.util.Date());//日期：存储日期);
     out.print(t);
 %> </span></div>
</body>
</html>
