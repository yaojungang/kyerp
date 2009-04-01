<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>联系人列表首页</title>
</head>
<body>
<h1>联系人列表</h1>
<TABLE width="80%" border="0" cellpadding="2" cellspacing="1"
	bgcolor="#CCCCCC" id="table_results">
	<TR bgcolor="#EEEEEE">
		<TH>编号</TH>
		<TH>单位名称</TH>
		<TH>联系人</TH>
		<TH>职位</TH>
		<TH>公司电话</TH>
		<TH>手机</TH>
		<TH>备注</TH>
	</TR>
	<s:iterator value="#request['linkmanList']" id="linkman" status="st">
		<TR <s:if test="#st.even">style="background-color:#EAEAEA"</s:if>
			<s:if test="#st.odd">style="background-color:#FFffff"</s:if>>
			<TD><s:property value="#st.count" /></TD>
			<TD><s:property value="ClientC.CCCom" /></TD>
			<TD><s:property value="CLmLinkman" /></TD>
			<TD><s:property value="CLmJob" /></TD>
			<TD><s:property value="CLmTel" /></TD>
			<TD><s:property value="CLmMobile" /></TD>
			<TD><s:property value="CLmRemark" /></TD>
		</TR>
	</s:iterator>
</TABLE>
<br>
<br>
<s:form action="/Client/addClient.action" method="get">
	<input type="hidden" name="clientId"
		value="<s:property value="clientId"/>" />
	<s:submit value="增加联系人" />
</s:form>
</body>
</html>