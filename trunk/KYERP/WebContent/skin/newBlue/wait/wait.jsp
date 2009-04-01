<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Please wait</title>
<meta http-equiv="refresh" content="5;url=<s:url includeParams="all" />" />
</head>
<body style="padding: 100px;">
请耐心等待，正在执行中..........
<p><img
	src="${pageContext.request.contextPath}/skin/default/wait/images/wait.gif"
	border="0" />
<p>
</body>
</html>
