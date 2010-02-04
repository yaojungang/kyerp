<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>任务单</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/showPages.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/table2excel.js"></script>
</head>
<body>
<span class="pageTitle"><s:property value="#request['pageTitle']" /></span>
<table width="98%" border="0" cellpadding="6" cellspacing="1"
	bgcolor="#CCCCCC" id="tableExcel">
	<tr align="center">
		<td width="85" bgcolor="#EEEEEE"><span class="From_item">任务单号</span></td>
		<td bgcolor="#EEEEEE">印品名称</td>
		<td width="50" bgcolor="#EEEEEE">业务员</td>
		<td width="50" bgcolor="#EEEEEE">印数</td>
		<td width="50" bgcolor="#EEEEEE">成品尺寸</td>
		<td width="50" bgcolor="#EEEEEE">开本</td>
		<td width="50" bgcolor="#EEEEEE">应收款</td>
		<td width="50" bgcolor="#EEEEEE">实收款</td>
	</tr>
	<s:iterator value="#request['ListAF']" id="af" status="st">
		<tr <s:if test="#st.even">style="background-color:#EAEAEA"</s:if>
			<s:if test="#st.odd">style="background-color:#FFffff"</s:if>>
			<td align="center"><s:property value="#af.iso" /><s:property
				value="#af.afNo" /></td>
			<td><s:property value="#af.presswork" /></td>
			<td align="center"><s:property value="#af.cp" /></td>
			<td align="center"><s:property value="#af.amount" /></td>
			<td align="center"><s:property value="#af.fps" /></td>
			<td align="center"><s:property value="#af.format" /></td>
			<td align="center"><s:property value="#af.moneyShould" /></td>
			<td align="center"><s:property value="#af.moneyFact" /></td>
		</tr>
	</s:iterator>
</table>
<br />
<br />
<s:set value="#request['AFPager']" name="pager" />
<script language="JavaScript">
<!--
var pg = new showPages('pg');
pg.pageCount =<s:property value="#pager.totalPage" />;  // 定义总页数(必要)
pg.argName = 'currentPage';  // 定义参数名(可选,默认为page)
pg.printHtml(1);
//-->
</script>
<br>
<div align="center" style="padding: 10px;"><input type="button"
	onClick="javascript:talbe2excel_method1('tableExcel');"
	value="导出到EXCEL"> <input type="button"
	onClick="javascript:getXlsFromTbl('tableExcel',null);" value="导出CVS文件">
</div>
</body>
</html>