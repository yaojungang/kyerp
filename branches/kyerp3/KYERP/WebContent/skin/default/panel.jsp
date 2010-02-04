<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<table width="100%" border="0" cellpadding="3" cellspacing="1"
	bgcolor="#6088FF">
	<tr bgcolor="#6088FF">
		<td height="25" align="center"><span class="panelTitle"><decorator:title
			default="面板" /></span></td>
	</tr>
	<tr>
		<td bgcolor="#FFFFFF" class="panelBody" align="center"><decorator:body />
		</td>
	</tr>
</table>