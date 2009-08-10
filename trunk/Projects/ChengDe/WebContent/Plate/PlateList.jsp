<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>印版列表</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/showPages.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ymPrompt/ymPrompt.js"></script>
<link rel="stylesheet" id='skin' type="text/css"
	href="${pageContext.request.contextPath}/Library/js/ymPrompt/skin/qq/ymPrompt.css" />
<script src="${pageContext.request.contextPath}/Library/js/jquery.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/function.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/table2excel.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    $(".stripe_tb tr").mouseover(function(){
    $(this).addClass("over");}).mouseout(function(){
    $(this).removeClass("over");}).click(function(){	
	$(this).toggleClass("click").removeClass("alt")})
 	$(".stripe_tb tr:even").addClass("alt");
});
</script>
</head>

<body>
<div class="pim2_secondMenu">
<div style="float: left;">
<ul>
	<li><input type="button" value="增加印版" onClick='javascript:window.location.href="addPlate.action"'></li>
	<li><input type="button" value="全部印版" onClick='javascript:window.location.href="PlateAdmin.action"'></li>
	<li><input type="button" value="过期印版" onClick='javascript:window.location.href="ExpPlateList.action"'></li>
</ul></div>
<div style="float: right">搜索：<form action="searchPlate.action" method="post" target="_blank">
    <select name="searchOption">
    	<option value="pname">版名</option>
    	<option value="danAnNo">档案号</option>
    	<option value="plateNo">版号</option>
    	<option value="plateLength">版长</option>
    	<option value="plateWidth">版周</option>
    	<option value="plateShare">共用版</option>
    	<option value="plateAddress">位置编号</option>
    </select>
    <input name="searchValue" /><input type="submit" value="搜索" /></form></div>
</div>
<div id="div_print">
<span class="pageTitle"><s:property value="#request['pageTitle']" /></span>
<table width="100%" border="0" align="center" cellpadding="6"
	class="stripe_tb" cellspacing="0" id="tableExcel0">
	<thead><tr>
    <th><div align="center">ID</div></th>
    <th><div align="center">档案号</div></th>
    <th><div align="center">版名</div></th>
    <th><div align="center">版号</div></th>
    <th><div align="center">版长</div></th>
    <th><div align="center">版周</div></th>
    <th><div align="center">支数</div></th>
    <th><div align="center">共用版</div></th>
    <th><div align="center">位置编号</div></th>
    <th><div align="center">入库时间</div></th>
    <th><div align="center">最后使用时间</div></th>
    <th><div align="center">过期时间</div></th>
    <th><div align="center">管理</div></th>
  </tr>
  </thead>
  <s:iterator value="#request['PlateList']" id="plate" status="st">
  <tr>
    <td><s:property value="#plate.id" /></td>
    <td><a target="_blank" href="PlateInfo.action?id=<s:property value='#plate.id' />"><s:property value="#plate.danAnNo" /></a></td>
    <td><a target="_blank" href="PlateInfo.action?id=<s:property value='#plate.id' />"><s:property value="#plate.pname" /></a></td>
    <td><s:property value="#plate.plateNo" /></td>
    <td><s:property value="#plate.plateLength" /></td>
    <td><s:property value="#plate.plateWidth" /></td>
    <td><s:property value="#plate.plateAmount" /></td>
    <td><s:property value="#plate.plateShare" /></td>
    <td><s:property value="#plate.plateAddress" /></td>
    <td><s:date name="#plate.inputDate" format="yyyy-MM-dd" nice="false" /></td>
    <td><s:date name="#plate.lastUseDate" format="yyyy-MM-dd" nice="false" /></td>
    <td><s:date name="#plate.expDate" format="yyyy-MM-dd" nice="false" /></td>
    <td>
	<a href="delPlate.action?id=<s:property value='#plate.id' />">删除</a>
	<a href="editPlate.action?id=<s:property value='#plate.id' />">修改</a>	
	</td>
  </tr>
  </s:iterator>
</table>
</div>
<s:set value="#request['Pager']" name="pager" />
<script language="JavaScript">
<!--
var pg = new showPages('pg');
pg.pageCount =<s:property value="#pager.totalPage" />;  // 定义总页数(必要)
pg.argName = 'currentPage';  // 定义参数名(可选,默认为page)
pg.printHtml(1);
//-->
</script>
<div align="center" style="padding: 20px;"><input name="b_print"
	type="button" class="ipt" onClick="printdiv('div_print');"
	value=" 打印报表 "> <input type="button"
	onClick="javascript:talbe2excel_method1('div_print');" value="导出到EXCEL">
<input type="button"
	onClick="javascript:getXlsFromTbl('tableExcel0',null);" value="导出CVS文件">
</div>
</body>
</html>
