<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加合作单位</title>
<style type="text/css">
<!--
.LabelItem {
	text-align: right;
	padding: 3px;
	width: 80px;
}
-->
</style>
</head>
<body>
<h1 align="center">增加联系人</h1>
<s:form action="/Client/addClient_save.action" method="post">
	<table width="680" border="1" align="center" cellpadding="6"
		cellspacing="0">
		<tr>
			<td align="right"><span class="LabelItem">单位名称</span></td>
			<td align="left"><SELECT name="clientId">
				<OPTION selected value=>==请选合作单位==</OPTION>
				<s:iterator value="#request['clientList']" id="client">
					<option value="<s:property value="#client.CCId"/>"
						<s:if test="%{#client.CCId==clientId}">selected</s:if>><s:property
						value="#client.CCCom" /></option>
				</s:iterator>
			</select></td>
		</tr>
		<tr>
			<td align="right">姓名</td>
			<td align="left"><input type="text" name="linkman.CLmLinkman"
				size="8"></td>
		</tr>
		<tr>
			<td align="right">职务<br>
			</td>
			<td align="left"><s:autocompleter theme="simple"
				name="linkman.CLmJob" list="{'印制员'}"
				cssStyle="width: 80px;text-align:left;" autoComplete="false"
				searchType="substring" /></td>
		</tr>
		<tr>
			<td align="right"><span class="LabelItem">办公电话</span><br>
			</td>
			<td align="left"><input name="linkman.CLmTel" type="text"
				size="16"></td>
		</tr>
		<tr>
			<td align="right"><span class="LabelItem">手机</span><br>
			</td>
			<td align="left"><input name="linkman.CLmMobile" type="text"
				size="13"></td>
		</tr>
		<tr>
			<td align="right"><span class="LabelItem">备注</span></td>
			<td align="left"><textarea name="linkman.CLmRemark" cols="50"
				rows="5"></textarea></td>
		</tr>
	</table>
	<br>
	<br>
	<s:submit value="提交" />
</s:form>
</body>
</html>