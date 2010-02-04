<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#session['privilegesList']" name="privilegesList"
	id="privilegesList" />
<s:set value="#session['user']" name="user" id="user" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="#request['pageTitle']" /></title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/showPages.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/function.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/RMBtoBig.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/table2excel.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/DatePicker/WdatePicker.js"></script>
</head>
<body>
<div id="tableExcel">
<div style="margin: 10px;">
<div
	style="font-size: 24px; line-height: 180%; margin-left: auto; margin-right: auto;"><s:property
	value="#request['pageTitle']" /></div>
<form action="MoneyIn_BAT.action" method="POST">
<div>
<ul>
	<li><label for="fapiaoTime">开票时间</label> <input type="text"
		name="fapiaoTime"
		onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
		value='<s:date name="#request['fapiaoTime']" format="yyyy-MM-dd HH:mm:ss" nice="false"/>' /></li>
	<li><label for="moneyTime">交款时间</label> <input type="text"
		name="moneyTime"
		onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
		value='<s:date name="#request['moneyTime']" format="yyyy-MM-dd HH:mm:ss" nice="false"/>' /></li>
</ul>
</div>
<div>
<table width="100%" border="0" align="center" cellpadding="6"
	cellspacing="1" bordercolor="#000000" bgcolor="#000000"
	id="tableExcel0">
	<tr align="center">
		<td width="20" bgcolor="#FFFFFF">&nbsp;</td>
		<td width="20" bgcolor="#FFFFFF">序</td>
		<td width="64" bgcolor="#FFFFFF">任务单号</td>
		<td bgcolor="#FFFFFF"><span class="From_item">印品名称</span></td>
		<td width="40" bgcolor="#FFFFFF">数量</td>
		<td width="60" bgcolor="#FFFFFF">交款人</td>
		<td width="60" bgcolor="#FFFFFF">应收金额</td>
		<td width="70" bgcolor="#FFFFFF">实收金额</td>
		<td width="20" bgcolor="#FFFFFF">发票</td>
		<td width="20" bgcolor="#FFFFFF">未结清</td>
		<td width="20" bgcolor="#FFFFFF">结清</td>
		<td width="140" bgcolor="#FFFFFF">备注</td>
	</tr>
	<s:iterator value="#request['ListAF']" id="af" status="st">
		<tr>
			<td align="center" bgcolor="#FFFFFF"><input type="checkbox"
				name="afs.afId" value='<s:property value="#af.afId" />'
				checked="checked"></td>
			<td align="center" bgcolor="#FFFFFF"><s:property
				value="#st.index + 1" /></td>
			<td align="center" bgcolor="#FFFFFF"><a
				href="${pageContext.request.contextPath}/AF/AFInfo.action?afId=<s:property value="#af.afId" />"
				target="_blank"><s:property value="#af.iso" /><s:property
				value="#af.afNo" /></a></td>
			<td bgcolor="#FFFFFF"><s:if test="#af.pcAf != null">
				<s:property value="#af.pcAf" />
			</s:if> <s:property value="#af.presswork" /></td>
			<td align="center" bgcolor="#FFFFFF"><s:property
				value="#af.amount" /></td>
			<td align="center" bgcolor="#FFFFFF"><input type="text"
				name="afs[<s:property value="#st.index" />].moneyGiveMan"
				style="width: 40px;"
				value='<s:if test="%{#af.moneyGiveMan != null}"><s:property value="#af.moneyGiveMan" /></s:if><s:else><s:property value="#af.linkman" /></s:else>' /></td>
			<td align="center" bgcolor="#FFFFFF"><s:if
				test="#af.moneyShould > 0">
				<a
					href='<%=request.getContextPath()%>/OPE/CalAF.action?afId=<s:property value="#af.afId" />'
					target="_blank"><SCRIPT type="text/javascript">document.write(fixfloat(<s:property value="#af.moneyShould" />,2));</SCRIPT></a>
			</s:if></td>
			<td align="center" bgcolor="#FFFFFF"><input type="text"
				style="color: red; width: 60px;"
				name="afs[<s:property value="#st.index" />].moneyFact"
				value='<s:if test="%{#af.moneyFact != null}"><s:property value="#af.moneyFact" /></s:if><s:else><s:if test="%{#af.moneyShould != null}"><s:property value="#af.moneyShould" /></s:if></s:else>' /></td>
			<td align="center" bgcolor="#FFFFFF"><input
				name="afs[<s:property value="#st.index" />].fapiaoStatus"
				type="checkbox" value="0" checked="checked" /></td>
			<td align="center" bgcolor="#FFFFFF"><input
				name="afs[<s:property value="#st.index" />].moneyStatus"
				type="radio" value="1"/></td>
			<td align="center" bgcolor="#FFFFFF"><input
				name="afs[<s:property value="#st.index" />].moneyStatus"
				type="radio" value="0" checked="checked" /></td>
			<td align="left" bgcolor="#FFFFFF"><input type="text"
				name="afs[<s:property value="#st.index" />].moneyRemark"
				value='<s:if test="%{#af.moneyRemark != null}"><s:property value="#af.moneyRemark" /></s:if><s:else>批量收款</s:else>' /></td>
		</tr>
	</s:iterator>
</table>
</div>
<div
	style="text-align: right; font-size: 18px; line-height: 180%; color: red;">
<s:set name="tmoney" value="0" /> <s:iterator
	value="#request['ListAF']" status="st2">
	<s:if test="moneyShould">
		<s:set name="tmoney" value="#tmoney=#tmoney + moneyShould" />
	</s:if>
</s:iterator> 结款项目:0条; 金额合计: <s:if test="#tmoney > 0">
	<script type="text/javascript">document.write(fixfloat(<s:property value="#tmoney"/>,2));</script>
</s:if>元</div>
<div><input type="submit" value="提交" /></div>
</form>
</div>
</div>
<br />
<br>
<div align="center" style="padding: 10px;"><input type="button"
	onClick="javascript:talbe2excel_method1('tableExcel');"
	value="导出到EXCEL"> <input type="button"
	onClick="javascript:getXlsFromTbl('tableExcel0',null);" value="导出CVS文件">
</div>
</body>
</html>