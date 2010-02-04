<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<title><decorator:title default="Welcome!" />-北京市清华园胶印厂信息管理系统</title>
<link rel="Shortcut Icon"
	href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/skin/default/style.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/skin/default/DropDownTab/dropdowntabs.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/function.js">
</script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/skin/default/DropDownTab/bluetabs.css" />
<link rel="stylesheet" type="text/css" media="all"
	href="${pageContext.request.contextPath}/Library/js/jscalendar/calendar-blue2.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/jscalendar/calendar.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/jscalendar/lang/cn_utf8.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/jscalendar/calendar-setup.js"></script>
<s:head theme="ajax" />
<decorator:head />
</head>
<body>
<decorator:body />
</body>
</html>
