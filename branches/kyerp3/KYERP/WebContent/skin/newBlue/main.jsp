<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><decorator:title default="Welcome!" />-北京市清华园胶印厂信息管理系统</title>
<page:applyDecorator page="/skin/newBlue/head_main.jsp" name="pages" />
<decorator:head />
</head>
<body class="thrColHybHdr">
<page:applyDecorator page="/skin/newBlue/top.jsp" name="pages" />
<div style="min-height: 500px;">
<table width="100%" border="0" cellspacing="0" cellpadding="6">
	<tr>
		<s:if test="#session.user!=null">
			<td width="125" align="center" valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="6"
				align="center">
				<tr>
					<td><page:applyDecorator page="/skin/newBlue/left_myKY.jsp"
						name="pages" /></td>
				</tr>
			</table>
			</td>
		</s:if>
		<td valign="top">
		<s:if test="#request.message != null"><div class="pim2_errorMessage"><s:property value="#request.message" /></div></s:if>
		<decorator:body /></td>
	</tr>
</table>
</div>
<page:applyDecorator page="/skin/newBlue/bottom.jsp" name="pages" />
</body>
</html>
