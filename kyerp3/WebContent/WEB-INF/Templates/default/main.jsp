<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
 prefix="decorator"
%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ include file="/WEB-INF/pages/share/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><decorator:title />-KYERP3</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="${ctx}/css/reset.css" type="text/css" media="screen, projection">
<link rel="stylesheet" href="${ctx}/css/grid.css" type="text/css" media="screen, projection">
<link rel="stylesheet" href="${ctx}/css/type.css" type="text/css" media="screen, projection">
<link rel="stylesheet" href="${ctx}/css/plugins/gadgets.css" type="text/css" media="screen, projection">
<!--[if IE]>
 <link rel="stylesheet" href="${ctx}/css/ie.css" type="text/css" media="screen, projection">
 <![endif]-->
<link rel="stylesheet" href="${ctx}/css/page-layout.css" type="text/css" media="screen, projection">
<link rel="stylesheet" href="${ctx}/css/web.css" type="text/css" media="screen, projection">

<link rel="stylesheet" type="text/css" href="${ctx}/js/extjs/resources/css/ext-all.css">
<script type="text/javascript" src="${ctx}/js/extjs/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/js/extjs/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<decorator:head />
</head>
<body class="kyerp">
<decorator:body />
</body>
</html>