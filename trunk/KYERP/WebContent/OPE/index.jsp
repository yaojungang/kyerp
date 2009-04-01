<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#session['userSystemFunctionList']" name="userSystemFunctionList"
	id="userSystemFunctionList" />
<s:set value="#session['user']" name="user" id="user" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>业务室首页</title>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0">
<table width="100%" border="0" cellspacing="0" cellpadding="8">
	<tr>
		<td align="left" valign="top">
		<table id="__01" width="454" height="454" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td colspan="3"><img src="images/title_yws.jpg" width="454"
					height="56"></td>
			</tr>
			<tr>
				<td><a href="getMyAF.action" target="_blank"><img
					src="images/wdrwd.jpg" alt="" width="151" height="151" border="0"></a></td>
				<td><a href="newAF.action" target="_blank"><img
					src="images/zjkd.jpg" alt="" width="152" height="151" border="0"></a></td>
				<td><a href="zhuankai.jsp" target="_blank"><img
					src="images/zkrwd.jpg" alt="" width="151" height="151" border="0"></a></td>
			</tr>
			<tr>
				<td><img src="images/bj.jpg" width="151" height="152" alt=""></td>
				<td><a href="getAFList_input.action" target="_blank"><img
					src="images/czrwd.jpg" width="152" height="152" alt="" border="0"></a></td>
				<td><a href="TentAF.action" target="_blank"><img
					src="images/cztj.jpg" alt="" width="151" height="152" border="0"></a></td>
			</tr>
			<tr>
				<td><a href="MASchedule.action" target="_blank"><img
					src="images/scjd.jpg" alt="" width="151" height="151" border="0"></a></td>
				<td><a href="getTodayAFs.action" target="_blank"><img
					src="images/jrrwd.jpg" alt="" width="152" height="151" border="0"></a></td>
				<td><a href="getAFs.action" target="_blank"><img
					src="images/qbrwd.jpg" alt="" width="151" height="151" border="0"></a></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td align="left" valign="top">
		<table width="454" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="151"><a href="../Client/MyClient.action"
					target="_blank"><img src="images/wdkh.jpg" alt="" width="151"
					height="151" border="0"></a></td>
				<td width="152"><img src="images/fxgl.jpg" alt="" width="152"
					height="151" border="0"></td>
				<td width="151"><a href="../Client/addClient.action"
					target="_blank"><img src="images/zjkh.jpg" alt="" width="151"
					height="151" border="0"></a></td>
			</tr>
			<tr>
				<td><img src="images/hktj.jpg" width="151" height="152" alt=""></td>
				<td><a href="taizhang_input.action" target="_blank"><img
					src="images/czyb.jpg" alt="" width="152" height="152" border="0"></a></td>
				<td><a href="cztjtb_input.action" target="_blank"><img
					src="images/cztjtb.jpg" alt="" width="151" height="152" border="0"></a></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td align="left" valign="top"><img src="../MA/images/JR.jpg"
			width="454" height="50"><br>
		<table width="98%" border="0" cellpadding="6" cellspacing="1"
			bgcolor="#CCCCCC">
			<tr align="center">
				<td width="25px" bgcolor="#EEEEEE"><span class="From_item">序号</span></td>
				<td width="85px" bgcolor="#EEEEEE"><span class="From_item">任务单号</span></td>
				<td bgcolor="#EEEEEE">印品名称</td>
				<td width="30" bgcolor="#EEEEEE">装订</td>
				<td width="50px" bgcolor="#EEEEEE">开单人</td>
				<td width="180px" bgcolor="#EEEEEE">管理</td>
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
					<td align="center"><s:property value="#af.fmp" /></td>
					<td align="left">
					<table width="100%" border="0">
						<tr>
							<td><a
								href="${pageContext.request.contextPath}/AF/AFInfo_print.action?afId=<s:property value="#af.afId" />"
								target="_blank">打印</a></td>
							<s:if
								test="#user.userType.equals('Admin') or 'OM-AF-Edit' in #userSystemFunctionList">
								<s:if test="#af.moneyShould > 0"></s:if>
								<s:else>
									<td><a
										href="editAF.action?afId=<s:property value="#af.afId" />"
										target="_blank">修改</a></td>
								</s:else>
							</s:if>
							
							<s:if test="#af.moneyShould > 0">
								<s:if test="#user.userType.equals('Admin') or 'FM-AF-ViewMoneyShould' in #userSystemFunctionList">
									<td><a
										href="CalAF.action?afId=<s:property value="#af.afId" />"
										target="_blank"><s:property value="#af.moneyShould" /></a></td>
								</s:if>
							</s:if>
							<s:if
								test="#user.userType.equals('Admin') or 'OM-AF-CalAF' in #userSystemFunctionList">
								<s:if test="#af.moneyShould > 0"></s:if>
								<s:else>
									<td><a
										href="CalAF_input.action?afId=<s:property value="#af.afId" />"
										target="_blank">计价</a></td>
								</s:else>
							</s:if>
						</tr>
					</table>
					</td>
				</tr>
			</s:iterator>
		</table>
		</td>
	</tr>
</table>
</body>
</html>