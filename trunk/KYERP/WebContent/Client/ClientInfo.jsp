<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#request['ClientInfo']" name="client" id="client" />
<s:set value="#session['privilegesList']" name="privilegesList"
	id="privilegesList" />
<s:set value="#session['user']" name="user" id="user" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>清华园胶印厂客户信息-<s:property value="#client.CCCom" /></title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/function.js"></script>
<!--media=print 这个属性可以在打印时有效-->
<style media="print">
.noprint {
	display: none
}
</style>
<link
	href="${pageContext.request.contextPath}/Library/css/ClientInfo.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
<table width="98%" border="0" align="center" cellpadding="3"
	cellspacing="0" class="noprint">
	<tr>
		<td align="left"><s:if test="#user.userType.equals('Admin')">
			<s:form action="ClientInfo_print" namespace="/Client" method="get">
				<input type="hidden" name="clientId"
					value="<s:property value="#client.CCId" />" />
				<input type="submit" value="打印客户信息表" />
			</s:form>
		</s:if> <s:if
			test="#user.userType.equals('Admin') or 'OPEUser' in #privilegesList">
			<s:form action="editClient" namespace="/Client" method="get">
				<input type="hidden" name="clientId"
					value="<s:property value="#client.CCId" />" />
				<input type="submit" value="修改客户信息表" />
			</s:form>
		</s:if> <s:if
			test="#user.userType.equals('Admin') or 'OPEUser' in #privilegesList">
			<s:form action="addLinkman" namespace="/Client" method="get">
				<input type="hidden" name="clientId"
					value="<s:property value="#client.CCId" />" />
				<input type="submit" value="添加联系人" />
			</s:form>
		</s:if> <s:if
			test="#user.userType.equals('Admin') or 'OPEUser' in #privilegesList">
			<s:form action="getAFByClient" namespace="/OPE" method="post"
				target="_blank">
				<input type="hidden" name="client"
					value="<s:property value="#client.CCCom" />" />
				<input type="submit" value="查看相关业务" />
			</s:form>
		</s:if> <s:if
			test="#user.userType.equals('Admin') or 'OPEUser' in #privilegesList">
			<s:form action="tjAFByClient" namespace="/OPE" method="post"
				target="_blank">
				<input type="hidden" name="client"
					value="<s:property value="#client.CCCom" />" />
				<input type="submit" value="统计回款情况" />
			</s:form>
		</s:if></td>
		<td width="80" align="center"><s:if
			test="#user.userType.equals('Admin') or 'OPEUser' in #privilegesList">
			<a
				href="${pageContext.request.contextPath}/Client/delClient.action?clientId=<s:property value="#client.CCId" />"
				target="_blank" onclick="return checkit('删除后不能恢复,您确认要删除这个客户吗?')">删除</a>
		</s:if></td>
	</tr>
</table>
<div class="height30"></div>
<div style="position: relative; left: 0px; top: 0px">
<table width="750" border="0" align="center" cellpadding="3"
	cellspacing="0">
	<tr>
		<td height="50" colspan="3" valign="middle">
		<div align="center"><img src="../Library/images/title_khxxb.gif"
			width="450" height="30"></div>
		</td>
	</tr>
	<tr>
		<td align="center"></td>
		<td colspan="2" align="right"><span class="item"><s:date
			name="#af.ad" nice="false" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<span class="sImpactred">No.<s:property value="#client.CCId" />&nbsp;</span></td>
	</tr>
</table>
<table width="750" border="1" align="center" cellpadding="3"
	cellspacing="0" bordercolor="#000000" bgcolor="#000000"
	style="border-collapse: collapse">
	<tr>
		<td width="18%" align="right" bgcolor="#FFFFFF"><span
			class="item">单位名称</span></td>
		<td align="left" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#client.CCCom" /></span></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF"><span class="item">主要负责业务员</span></td>
		<td align="left" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#client.ywname" /></span></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF"><span class="item">传真</span></td>
		<td align="left" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#client.CCFax" /></span></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF"><span class="item">地址</span><br>
		</td>
		<td align="left" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#client.CCAddress" /></span></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF"><span class="item">邮编</span><br>
		</td>
		<td align="left" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#client.CCPc" /></span></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF"><span class="item">发行地址</span><br>
		</td>
		<td align="left" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#client.CCDa" /></span></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#FFFFFF"><span class="item">发行电话</span><br>
		</td>
		<td align="left" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#client.CCDt" /></span></td>
	</tr>
	<tr>
		<td colspan="2" align="center" bgcolor="#f0f0f0"><span
			class="bigitem">联系人</span></td>
	</tr>
	<tr>
		<td colspan="2" align="center" bgcolor="#FFFFFF">
		<TABLE width="100%" border="1" cellpadding="3" cellspacing="0"
			bordercolor="#000000" style="border-collapse: collapse">
			<TR bgcolor="#EEEEEE">
				<td>序号</td>
				<td>联系人</td>
				<td>职位</td>
				<td>公司电话</td>
				<td>手机</td>
				<td>备注</td>
			</TR>
			<s:iterator value="#client.ClientLm" id="linkman" status="st">
				<TR>
					<TD><s:property value="#st.count" /></TD>
					<TD><s:property value="CLmLinkman" /></TD>
					<TD><s:property value="CLmJob" /></TD>
					<TD><s:property value="CLmTel" /></TD>
					<TD><s:property value="CLmMobile" /></TD>
					<TD><s:property value="CLmRemark" /></TD>
				</TR>
			</s:iterator>
		</TABLE>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center" bgcolor="#F0F0F0"><span
			class="bigitem">备注</span></td>
	</tr>
	<tr>
		<td colspan="2" align="left" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#client.CCRemark" /><br>
		<br>
		</span></td>
	</tr>
</table>
</div>
</body>
</html>