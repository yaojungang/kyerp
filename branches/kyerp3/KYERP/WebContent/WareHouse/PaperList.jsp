<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="#request.pageTitle" />-物料管理</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/showPages.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Library/js/ext/resources/css/ext-all.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ext/adapter/jquery/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ext/ext-all.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ext/source/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript">
	Ext.onReady( function() {
				Ext.QuickTips.init();
				Ext.form.Field.prototype.msgTarget = 'side';
				Ext.BLANK_IMAGE_URL = '../Library/js/ext/resources/images/default/s.gif';

				var comboPaperType = new Ext.form.ComboBox( {
					store :new Ext.data.SimpleStore( {
						fields : [ 'value' ],
						expandData :true,
						data : [ '双铜', '亚光铜', '铜卡', '轻涂', '双胶', '白板', '白卡', '复印纸', '牛皮纸','书写','新闻纸']
					}),
					emptyText :'品名',
					allowBlank :false,
					blankText :'必须选择一种',
					mode :'local',
					triggerAction :'all',
					valueField :'value',
					displayField :'value',
					readOnly :true,
					applyTo :'inputPaperType'
				});
				$(".stripe_tb tr").mouseover(function(){
				    $(this).addClass("over");}).mouseout(function(){
				    $(this).removeClass("over");}).click(function(){	
					$(this).toggleClass("click").removeClass("alt")})
				 	$(".stripe_tb tr:even").addClass("alt");
});
</script>
</head>
<body>
<span class="pageTitle"><s:property value="#request.pageTitle" /></span>
<form action="getPaperByType.action" method="post">
<table width="80%" border="0" cellpadding="0" cellspacing="4">
	<tr>
		<td width="190"><input name="type" id="inputPaperType"
			type="text" /></td>
		<td align="left"><input name="s" id="ss" type="submit" value="查找"></td>
		<td align="left"><a href="addPaper.action">增加新纸</a></td>
	</tr>
</table>
</form>
<table width="100%" border="0" align="center" cellpadding="6"
	class="stripe_tb" cellspacing="0">
	<thead>
		<tr align="center">
			<th style="width: 30px;">编号</th>
			<th style="width: 60px;">纸张类型</th>
			<th style="width: 80px;">品牌</th>
			<th style="width: 40px;">克重</th>
			<th style="width: 30px;">宽度</th>
			<th style="width: 30px;">长度</th>
			<th style="width: 60px;">价格</th>
			<th style="width: 120px;">修改时间</th>
			<th>备注</th>
			<th style="width: 180px;">管理</th>
		</tr>
	</thead>
	<s:iterator value="#request.paperList" id="paper" status="st">
		<tr>
			<td style="width: 30px;"><a
				href='viewPaper.action?pid=<s:property value="#paper.pid" />'><s:property
				value="#paper.pid" /></a></td>
			<td style="width: 60px;"><s:property value="#paper.type" /></td>
			<td style="width: 80px;"><s:property value="#paper.bands" /></td>
			<td style="width: 40px;"><s:property value="#paper.weight" /></td>
			<td style="width: 30px;"><s:property value="#paper.width" /></td>
			<td style="width: 30px;"><s:property value="#paper.height" /></td>
			<td style="width: 60px;"><s:property value="#paper.price" /></td>
			<td style="width: 120px;"><s:date name="#paper.updateDate"
				format="yyyy-MM-dd HH:MM:SS" nice="false" /></td>
			<td><s:property value="#paper.remark" /></td>
			<td style="width: 180px;"><a
				href='viewPaper.action?pid=<s:property value="#paper.pid" />'>查看</a>
			<a href='editPaper.action?pid=<s:property value="#paper.pid" />'>修改</a>
			<a href='removePaper.action?pid=<s:property value="#paper.pid" />'
				onclick='return checkit("删除后不能恢复,您确认要删除吗?")'>删除</a></td>
		</tr>
	</s:iterator>
</table>
<s:set value="#request.pager" name="p" />
<s:if test="#p.totalPage > 1">
	<br />
	<br />
	<script language="JavaScript">
<!--
var pg = new showPages('pg');
pg.pageCount ='<s:property value="#p.totalPage" />';  // 定义总页数(必要)
pg.argName = 'currentPage';  // 定义参数名(可选,默认为page)
pg.printHtml(1);
//-->
</script>
</s:if>
</body>
</html>