<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>输入纸张参数-物料管理</title>
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
				var comboPaperBands = new Ext.form.ComboBox( {
					store :new Ext.data.SimpleStore( {
						fields : [ 'value' ],
						expandData :true,
						data : [ '华夏太阳', '博汇', '银鸽', '金东太空梭', '东帆', '晨鸣' ]
					}),
					emptyText :'品牌',
					allowBlank :false,
					blankText :'必须填写品牌',
					mode :'local',
					triggerAction :'all',
					valueField :'value',
					displayField :'value',
					readOnly :false,
					applyTo :'inputPaperBands'
				});

				var comboPaperWeight = new Ext.form.ComboBox( {
					store :new Ext.data.SimpleStore( {
						fields : [ 'value' ],
						expandData :true,
						data : [ '80', '128', '157', '200', '250' ]
					}),
					emptyText :'0',
					allowBlank :false,
					blankText :'必须填写克重,单位克(g)',
					regex :/^\d+$/,
					regexText :'只能输入大于0的整数.',
					mode :'local',
					triggerAction :'all',
					valueField :'value',
					displayField :'value',
					readOnly :false,
					applyTo :'inputPaperWeight'
				});
				var comboPaperWidth = new Ext.form.ComboBox( {
					store :new Ext.data.SimpleStore( {
						fields : [ 'value' ],
						expandData :true,
						data : [ '787', '889' ]
					}),
					emptyText :'单位:厘米',
					allowBlank :false,
					blankText :'必须填写纸张宽度,单位厘米',
					regex :/^\d+$/,
					regexText :'只能输入大于0的整数.',
					mode :'local',
					triggerAction :'all',
					valueField :'value',
					displayField :'value',
					readOnly :false,
					applyTo :'inputPaperWidth'
				});
				var comboPaperHeight = new Ext.form.ComboBox( {
					store :new Ext.data.SimpleStore( {
						fields : [ 'value' ],
						expandData :true,
						data : [ '1092', '1194' ]
					}),
					emptyText :'单位:厘米',
					allowBlank :false,
					blankText :'必须填写纸张长度,单位:厘米',
					regex :/^\d+$/,
					regexText :'只能输入大于0的整数.',
					mode :'local',
					triggerAction :'all',
					valueField :'value',
					displayField :'value',
					readOnly :false,
					applyTo :'inputPaperHeight'
				});
				var comboPaperPrice = new Ext.form.ComboBox( {
					store :new Ext.data.SimpleStore( {
						fields : [ 'value' ],
						expandData :true,
						data : [ '5000', '6000' ]
					}),
					emptyText :'单位:元/吨',
					allowBlank :false,
					blankText :'必须填写纸张价格,单位元/吨',
					regex :/^\d+(\.\d+)?$/,
					regexText :'输入格式不对.',
					mode :'local',
					triggerAction :'all',
					valueField :'value',
					displayField :'value',
					readOnly :false,
					applyTo :'inputPaperPrice'
				});

			});
</script>
</head>
<body>
<span class="pageTitle">输入纸张参数</span>
<s:set value="#request.paper" name="paper" />
<form action="editPaper_save.action" class="cssform" method="post"
	id="paperInputForm"><input type="hidden" name="paper.pid"
	value='<s:property value="#paper.pid" />' />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>品名</td>
		<td><input id="inputPaperType" type="text" name="paper.type"
			value='<s:property value="#paper.type" />' /></td>
	</tr>
	<tr>
		<td>品牌</td>
		<td><input id="inputPaperBands" type="text" name="paper.bands"
			value='<s:property value="#paper.bands" />' /></td>
	</tr>
	<tr>
		<td>克重</td>
		<td><input id="inputPaperWeight" type="text" name="paper.weight"
			value='<s:property value="#paper.weight" />' /></td>
	</tr>
	<tr>
		<td>宽</td>
		<td><input id="inputPaperWidth" type="text" name="paper.width"
			value='<s:property value="#paper.width" />' /></td>
	</tr>
	<tr>
		<td>高</td>
		<td><input id="inputPaperHeight" type="text" name="paper.height"
			value='<s:property value="#paper.height" />' /></td>
	</tr>
	<tr>
		<td>价格</td>
		<td><input id="inputPaperPrice" type="text" name="paper.price"
			value='<s:property value="#paper.price" />' /></td>
	</tr>
	<tr>
		<td>备注</td>
		<td><textarea name="paper.remark" rows="3" cols="6">
<s:property value="#paper.remark" /></textarea></td>
	</tr>
</table>
<p><input type="submit" value="保  存"></p>
</form>
</body>
</html>