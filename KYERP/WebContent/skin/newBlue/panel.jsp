<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<DIV class=headers><IMG class=showstate
	onClick="expandcontent(this, '<decorator:title default="面板" />')"
	src="${pageContext.request.contextPath}/Library/images/switch_minus_jian.gif">&nbsp;<decorator:title
	default="面板" /></DIV>
<DIV class=switchcontent style="padding-left: 0px;"
	id="<decorator:title default="面板" />"><decorator:body /></DIV>