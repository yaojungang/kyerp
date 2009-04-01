<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生产管理首页</title>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0">
<table width="100%" border="0" cellspacing="0" cellpadding="8">
	<tr>
		<td width="100%" align="center" valign="top">
		<table width="454" height="454" border="0" cellpadding="0"
			cellspacing="0" align="left">
			<tr>
				<td colspan="3"><img src="images/title_sck.jpg" width="454"
					height="56"></td>
			</tr>
			<tr>
				<td><a href="getTodayAFs.action" target="_blank"><img
					src="images/todayAF.jpg" alt="" width="151" height="151" border="0"></a></td>
				<td><a href="MASchedule.action" target="_blank"><img
					src="images/scjd.jpg" alt="" width="152" height="151" border="0"></a></td>
				<td><img src="images/shrwd.jpg" width="151" height="151" alt=""></td>
			</tr>
			<tr>
				<td><a href="getAFList_input.action" target="_blank"><img
					src="images/czrwd.jpg" width="151" height="152" alt="" border="0"></a></td>
				<td><a href="taizhang_input.action" target="_blank"><img
					src="images/sctj.jpg" alt="" width="152" height="152" border="0"></a></td>
				<td><a href="cztjtb_input.action" target="_blank"><img
					src="images/cztjtb.jpg" width="151" height="152" alt="" border="0"></a></td>
			</tr>
			<tr>
				<td><a href="getSKAFs.action" target="_blank"><img
					src="images/skrwd.jpg" alt="" width="151" height="151" border="0"></a></td>
				<td><a href="getLHAFs.action" target="_blank"><img
					src="images/lhrwd.jpg" alt="" width="152" height="151" border="0"></a></td>
				<td><a href="getAFDetialList.action" target="_blank"><img
					src="images/xxrw.jpg" alt="" width="151" height="151" border="0"></a></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td align="left" valign="top"><img src="images/JR.jpg"
			width="454" height="50"><br>
		<table width="100%" border="0" cellpadding="6" cellspacing="1"
			bgcolor="#CCCCCC">
			<tr align="center">
				<td width="25" bgcolor="#EEEEEE"><span class="From_item">序号</span></td>
				<td width="85" bgcolor="#EEEEEE"><span class="From_item">任务单号</span></td>
				<td bgcolor="#EEEEEE">印品名称</td>
				<td width="30" bgcolor="#EEEEEE">装订</td>
				<td width="50" bgcolor="#EEEEEE">印数</td>
				<td width="50" bgcolor="#EEEEEE">打印</td>
			</tr>
			<s:iterator value="#request['ListAF']" id="af" status="st">
				<tr <s:if test="#st.even">style="background-color:#EAEAEA"</s:if>
					<s:if test="#st.odd">style="background-color:#FFffff"</s:if>>
					<td align="center"><s:property value="#st.index +1 " /></td>
					<td align="center"><s:property value="#af.iso" /><s:property
						value="#af.afNo" /></td>
					<td><a
						href="${pageContext.request.contextPath}/AF/AFInfo.action?afId=<s:property value="#af.afId" />"
						target="_blank"><s:property value="#af.presswork" /></a></td>
					<td align="center"><s:if test="#af.ourbinding==1">
						<img
							src="${pageContext.request.contextPath}/Library/images/minico/yes.gif">
					</s:if></td>
					<td align="center"><s:property value="#af.amount" /></td>
					<td align="center"><a
						href="${pageContext.request.contextPath}/AF/AFInfo_print.action?afId=<s:property value="#af.afId" />"
						target="_blank">打印</a></td>
				</tr>
			</s:iterator>
		</table>
		</td>
	</tr>
</table>
</body>
</html>