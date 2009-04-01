<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Loading by AJAX (content loading from html file)</title>
<link rel="STYLESHEET" type="text/css"
	href="${pageContext.request.contextPath}/Library/js/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.css">
<script
	src="${pageContext.request.contextPath}/Library/js/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxcommon.js"></script>
<script
	src="${pageContext.request.contextPath}/Library/js/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.js"></script>
</head>
<body>
<table>
	<tr>
		<td>
		<div id="a_tabbar" style="width: 830; height: 320;" />
		</td>
	</tr>
</table>
<br>
<script>

			tabbar=new dhtmlXTabBar("a_tabbar","top");
            tabbar.setImagePath("${pageContext.request.contextPath}/Library/js/dhtmlxSuite/dhtmlxTabbar/codebase/imgs/");
            
            tabbar.loadXML("AF_list.xml");
			tabbar.setSkinColors("white","#FFFACD");

	</script>
</body>
</html>
