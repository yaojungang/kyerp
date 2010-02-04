<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
<s:if test="#session.user==null">
<p />
<p />
<p />
<p />
<p />
<p />
<p style="width:100%">
<center><h1>欢迎使用酷印通ERP系统！</h1></center></p>
<script LANGUAGE="JavaScript">
window.location='http://erp.tyopf.com/User/index.action';
//window.location='${pageContext.request.contextPath}/User/index.action';
</script>
</s:if>
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
