<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
<script LANGUAGE="JavaScript">
var url='<s:property value = "#session.user.url" />';
if(url != "") {
	window.location='${pageContext.request.contextPath}<s:property value = "#session.user.url" />;';
}else{
	window.location='${pageContext.request.contextPath}/User/index.action';
}
</script>
</body>
</html>
