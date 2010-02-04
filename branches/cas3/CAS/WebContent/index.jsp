<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	response.sendRedirect(response.encodeUrl(request.getContextPath()
			+ "/login" + "?" + request.getQueryString()));
%>