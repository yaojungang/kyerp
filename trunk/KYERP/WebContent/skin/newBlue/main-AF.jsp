<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<title><decorator:title default="Welcome!" />-北京市清华园胶印厂信息管理系统</title>
<page:applyDecorator page="/skin/newBlue/head_main.jsp" name="pages" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/Validator.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/JCalendar.js"
	defer="defer"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ZheDie.js"></script>
<decorator:head />
</head>
<body class="thrColHybHdr">
<page:applyDecorator page="/skin/newBlue/top.jsp" name="pages" />
<div style="min-height: 500px;">
<table width="100%" border="0" cellspacing="0" cellpadding="6">
	<tr>
		<td width="125" align="center" valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="6">
			<tr>
				<td><page:applyDecorator page="/skin/newBlue/left_myKY.jsp"
					name="pages" /></td>
			</tr>
			<tr>
				<td><page:applyDecorator page="/skin/newBlue/left_date.jsp"
					name="pages" /></td>
			</tr>
			<tr>
				<td><page:applyDecorator page="/skin/newBlue/left_search.jsp"
					name="panel" /></td>
			</tr>
			<tr>
				<td><page:applyDecorator page="/skin/newBlue/left_ywy.jsp"
					name="panel" /></td>
			</tr>
		</table>
		</td>
		<td valign="top"><s:if test="#request.message != null">
			<div class="pim2_errorMessage"><s:property
				value="#request.message" /></div>
		</s:if> 
		<div style="float: left">搜索：<form action="${pageContext.request.contextPath}/AF/searchAF.action" method="post" target="_blank">
    <select name="searchOption">
    	<option value="afNo">任务单号</option>
    	<option value="pcAf">印制单号</option>
    	<option value="presswork">印品名称</option>
    	<option value="isbn">书号</option>
    	<option value="client">委印单位</option>
    	<option value="linkman">经手人</option>
    	<option value="cp">接洽人</option>
    	<option value="fmp ">开单人 </option>
    </select>
    <input name="searchValue" /><input type="submit" value="搜索" /></form></div>
    <div style="clear: both;"></div>
		<decorator:body /></td>
	</tr>
</table>
</div>
<page:applyDecorator page="/skin/newBlue/bottom.jsp" name="pages" />
</body>
</html>
