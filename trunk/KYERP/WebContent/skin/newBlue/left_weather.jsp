<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html>
<head>
<title>天气预报</title>
</head>
<body>
<table width="100%" border="0" cellpadding="3" cellspacing="0"
	bgcolor="#6088FF">
	<tr bgcolor="#FFFFFF">
		<td align="center"><!-- IFRAME ID='ifm2' WIDTH='100%' HEIGHT='70' ALIGN='CENTER' MARGINWIDTH='0' MARGINHEIGHT='0' HSPACE='0' VSPACE='0' FRAMEBORDER='0' SCROLLING='NO' src="http://news.sina.com.cn/iframe/weather/110100.html"  -->
		<IFRAME WIDTH='100%' HEIGHT='70' ALIGN='CENTER' MARGINWIDTH='0'
			MARGINHEIGHT='0' HSPACE='0' VSPACE='0' FRAMEBORDER='0' SCROLLING='NO'
			src="${pageContext.request.contextPath}/Library/noSkin_weather.jsp" /></td>
	</tr>
</table>
</body>
</html>