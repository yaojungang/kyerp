<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.*,java.net.*"%>
<HTML>
<HEAD>
<TITLE>帮助</TITLE>
</HEAD>
<BODY>

<%!private String getIp() {
		String strTmp = "";
		try {
			strTmp = InetAddress.getLocalHost().getHostAddress();
			return strTmp;
		} catch (Exception e) {
			return strTmp;
		}
	}
%>
<%out.println(getIp()); %>

<script LANGUAGE="JavaScript">
var url='<s:property value = "#session.user.url" />';
var _serverIp = "<%out.print(getIp());%>";
var _contentPath="${pageContext.request.contextPath}";
if("192.168.0.12" == _serverIp){
 _contentPath = "http://erp.tyopf.com";
} 
alert(_contentPath);
//if(url != "") {
 //window.location='_contentPath/<s:property value = "#session.user.url" />;';
//}else{
 //window.location='_contentPath/User/index.action';
//}
</script>
</BODY>
</HTML>
