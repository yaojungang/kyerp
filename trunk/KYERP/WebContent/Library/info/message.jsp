<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>信息</title>
</head>
<body>
<s:if test="message != null">
<div class="pim2_errorMessage"><s:property value="message" /></div></s:if>
</body>
</html>
