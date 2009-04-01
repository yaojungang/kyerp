<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产值统计图表-SK<s:property
	value="#request['ValueOfOutput.ValueOfOutput.SKStartAFNo']" />-SK<s:property
	value="#request['ValueOfOutput.ValueOfOutput.SKEndAFNo']" /> & LH<s:property
	value="#request['ValueOfOutput.LHStartAFNo']" /> - LH<s:property
	value="#request['ValueOfOutput.LHEndAFNo']" /></title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/flashChart/ampie/ampie/swfobject.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/function.js"></script>
<style type="text/css">
<!--
.item_title {
	padding-top: 0px;
	padding-bottom: 20px;
	clear: both;
	font-size: 16px;
	font-family: "黑体";
	text-align: center;
}

.item_item {
	font-size: 12px;
	font-family: 黑体； width :         60px;
}

.chart {
	width: 65%;
	float: right;
	padding: 0px;
}

.data {
	width: 35%;
	float: left;
	padding: 0px;
	text-align: center;
}

.item_i {
	clear: both;
}

.printer {
	font-size: 12px;
	text-align: left;
}

.tjfw {
	font-size: 12px;
	text-align: left;
}

.big_title {
	font-family: "黑体";
	text-align: center;
	font-size: 28px;
	padding-top: 30px;
	padding-bottom: 20px;
	clear: both;
}

-->
@media print {
	INPUT {
		display: none
	}
	.noprint {
		display: none
	}
	.height30 {
		height: 30px;
	}
}
</style>
</head>
<body>
<div class="noprint" style="color: red;">请把【页面设置】中【方向】设置成“横向”，“页眉”，“页脚”置空，页面边距全部设置成5，然后打印。</div>
<div class="big_title">各业务类型产值统计图表</div>
<div class="tjfw">统计范围：SK <s:property
	value="#request['ValueOfOutput.SKStartAFNo']" /> - SK <s:property
	value="#request['ValueOfOutput.SKEndAFNo']" /> ; LH <s:property
	value="#request['ValueOfOutput.LHStartAFNo']" /> - LH <s:property
	value="#request['ValueOfOutput.LHEndAFNo']" />
<hr />
</div>
<div align="center">
<div id="ywlx" class="item_i">
<div class="data" style="height: 280px;">
<div class="item_title">各业务类型产值统计表</div>
<table table width="100%" border="1" align="center" cellpadding="3"
	cellspacing="0" bordercolor="#000000" bgcolor="#000000"
	style="border-collapse: collapse">
	<tr>
		<td align="center" bgcolor="#FFFFFF">业务类型</td>
		<td align="center" bgcolor="#FFFFFF">应收款</td>
		<td align="center" bgcolor="#FFFFFF">实收款</td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF">受控</td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKMoneyShould']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKMoneyFact']" /></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF">零活</td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHMoneyShould']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHMoneyFact']" /></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF">合计</td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKMoneyShould'] + #request['ValueOfOutput.LHMoneyShould']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKMoneyFact'] + #request['ValueOfOutput.LHMoneyFact']" /></td>
	</tr>
</table>
</div>
<div class="chart" style="height: 280px;">
<div id="flashcontent_ywlx"><strong>业务类型图表</strong></div>
<script type="text/javascript">
		// <![CDATA[		
		var so = new SWFObject("${pageContext.request.contextPath}/Library/flashChart/ampie/ampie/ampie.swf", "ampie", "90%", "90%", "8", "#ffffff");
		so.addVariable("path", "${pageContext.request.contextPath}/Library/flashChart/ampie/ampie/");
		so.addVariable("settings_file", escape("${pageContext.request.contextPath}/MA/ampie_settings_ywlx.xml"));
		// so.addVariable("data_file", escape("${pageContext.request.contextPath}/MA/ampie_data.txt"));
		so.addVariable("chart_data", "受控;<s:property value="#request['ValueOfOutput.SKMoneyShould']" />\n零活;<s:property value="#request['ValueOfOutput.LHMoneyShould']" />");
		so.addVariable("preloader_color", "#ffffff");
		so.write("flashcontent_ywlx");
		// ]]>
	</script></div>
</div>
<div class="printer">
<hr noshade="noshade" size="1" />
北京市清华园胶印厂-酷印通 http://erp.tyopf.com <span class="bottomtext">打印者:
<s:property value="#session.user.employee.realname" /></span> <span
	class="bottomtext">打印时间: <%
 			String t = (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
 			.format(new java.util.Date());//日期：存储日期);
 	out.print(t);
 %> </span></div>
<div style="page-break-after: always"></div>
<div class="big_title">各车间产值统计图表</div>
<div class="tjfw">统计范围：SK <s:property
	value="#request['ValueOfOutput.SKStartAFNo']" /> - SK <s:property
	value="#request['ValueOfOutput.SKEndAFNo']" /> ; LH <s:property
	value="#request['ValueOfOutput.LHStartAFNo']" /> - LH <s:property
	value="#request['ValueOfOutput.LHEndAFNo']" />
<hr />
</div>
<div id="cjtj" class="item_i"><span class="data"
	style="height: 380px;">
<div class="item_title">各车间产值统计表</div>
<table table width="100%" border="1" align="center" cellpadding="3"
	cellspacing="0" bordercolor="#000000" bgcolor="#000000"
	style="border-collapse: collapse">
	<tr>
		<td width="22%" align="center" bgcolor="#FFFFFF">车间</td>
		<td align="center" bgcolor="#FFFFFF">受控业务</td>
		<td align="center" bgcolor="#FFFFFF">零活业务</td>
		<td align="center" bgcolor="#FFFFFF">合计</td>
	</tr>
	<tr>
		<td bgcolor="#FFFFFF">照排</td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKzp']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHzp']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKzp'] + #request['ValueOfOutput.LHzp']" /></td>
	</tr>
	<tr>
		<td bgcolor="#FFFFFF">制版车间</td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKzbcj']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHzbcj']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKzbcj'] + #request['ValueOfOutput.LHzbcj']" /></td>
	</tr>
	<tr>
		<td bgcolor="#FFFFFF">四色</td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKss']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHss']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKss'] + #request['ValueOfOutput.LHss']" /></td>
	</tr>
	<tr>
		<td bgcolor="#FFFFFF">双面</td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKsm']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHsm']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKsm'] + #request['ValueOfOutput.LHsm']" /></td>
	</tr>
	<tr>
		<td bgcolor="#FFFFFF">05</td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SK05']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SK05']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SK05'] + #request['ValueOfOutput.SK05']" /></td>
	</tr>
	<tr>
		<td bgcolor="#FFFFFF">02</td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SK02']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LH02']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SK02'] + #request['ValueOfOutput.LH02']" /></td>
	</tr>
	<tr>
		<td bgcolor="#FFFFFF">小制版</td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKxzb']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHxzb']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKxzb'] + #request['ValueOfOutput.LHxzb']" /></td>
	</tr>
	<tr>
		<td bgcolor="#FFFFFF">装订</td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKzd']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHzd']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKzd'] + #request['ValueOfOutput.LHzd']" /></td>
	</tr>
	<tr>
		<td bgcolor="#FFFFFF">库房</td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKkf']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHkf']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKkf'] + #request['ValueOfOutput.LHkf']" /></td>
	</tr>
	<tr>
		<td bgcolor="#FFFFFF">外加工</td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKwjg']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHwjg']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKwjg'] + #request['ValueOfOutput.LHwjg']" /></td>
	</tr>
	<tr>
		<td bgcolor="#FFFFFF">其它</td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKqt']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHqt']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKqt'] + #request['ValueOfOutput.LHqt']" /></td>
	</tr>
</table>
</span> <span class="chart" style="height: 380px;">
<div id="flashcontent_cj"><strong>车间柱状图</strong></div>
<script type="text/javascript">
		// <![CDATA[		
		var so = new SWFObject("${pageContext.request.contextPath}/Library/flashChart/amcolumn/amcolumn/amcolumn.swf", "amcolumn", "90%", "90%", "8", "#ffffff");
		so.addVariable("path", "${pageContext.request.contextPath}/Library/flashChart/amcolumn/amcolumn/");
		so.addVariable("settings_file", escape("${pageContext.request.contextPath}/MA/amcolumn_settings_cj.xml"));
		//so.addVariable("data_file", escape("${pageContext.request.contextPath}/MA/amcolumn_data.txt"));
		so.addVariable("chart_data", "照排;<s:property value="#request['ValueOfOutput.SKzp']" />;<s:property value="#request['ValueOfOutput.LHzp']" />\n制版车间;<s:property value="#request['ValueOfOutput.SKzbcj']" />;<s:property value="#request['ValueOfOutput.LHzbcj']" />\n四色;<s:property value="#request['ValueOfOutput.SKss']" />;<s:property value="#request['ValueOfOutput.LHss']" />\n双面;<s:property value="#request['ValueOfOutput.SKsm']" />;<s:property value="#request['ValueOfOutput.LHsm']" />\n05;<s:property value="#request['ValueOfOutput.SK05']" />;<s:property value="#request['ValueOfOutput.LH05']" />\n02;<s:property value="#request['ValueOfOutput.SK02']" />;<s:property value="#request['ValueOfOutput.LH02']" />\n小制版;<s:property value="#request['ValueOfOutput.SKxzb']" />;<s:property value="#request['ValueOfOutput.LHxzb']" />\n库房;<s:property value="#request['ValueOfOutput.SKkf']" />;<s:property value="#request['ValueOfOutput.LHkf']" />\n外加工;<s:property value="#request['ValueOfOutput.SKwjg']" />;<s:property value="#request['ValueOfOutput.LHwjg']" />\n其它;<s:property value="#request['ValueOfOutput.SKqt']" />;<s:property value="#request['ValueOfOutput.LHqt']" />");
		so.addVariable("preloader_color", "#ffffff");
		so.write("flashcontent_cj");
		// ]]>
	</script> </span></div>
<div class="printer">
<hr noshade="noshade" size="1" />
北京市清华园胶印厂-酷印通 http://erp.tyopf.com <span class="bottomtext">打印者:
<s:property value="#session.user.employee.realname" /></span> <span
	class="bottomtext">打印时间: <%
 			String t2 = (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
 			.format(new java.util.Date());//日期：存储日期);
 	out.print(t);
 %> </span></div>
<div style="page-break-after: always"></div>
<div class="big_title">业务员产值统计图表</div>
<div class="tjfw">统计范围：SK <s:property
	value="#request['ValueOfOutput.SKStartAFNo']" /> - SK <s:property
	value="#request['ValueOfOutput.SKEndAFNo']" /> ; LH <s:property
	value="#request['ValueOfOutput.LHStartAFNo']" /> - LH <s:property
	value="#request['ValueOfOutput.LHEndAFNo']" />
<hr />
</div>
<div id="ywycltj" class="item_i"><span class="data"
	style="height: 380px;">
<div class="item_title">业务员产值统计表</div>
<table table width="100%" border="1" align="center" cellpadding="3"
	cellspacing="0" bordercolor="#000000" bgcolor="#000000"
	style="border-collapse: collapse">
	<tr>
		<td rowspan="2" align="center" bgcolor="#FFFFFF">业务员</td>
		<td colspan="2" align="center" bgcolor="#FFFFFF">受控业务</td>
		<td colspan="2" align="center" bgcolor="#FFFFFF">零活业务</td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF">应收款</td>
		<td align="center" bgcolor="#FFFFFF">实收款</td>
		<td align="center" bgcolor="#FFFFFF">应收款</td>
		<td align="center" bgcolor="#FFFFFF">实收款</td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF">王秀云</td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKYWYShouldwxy']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKYWYFactwxy']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHYWYShouldwxy']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHYWYFactwxy']" /></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF">陈桂芝</td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKYWYShouldcgz']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKYWYFactcgz']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHYWYShouldcgz']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHYWYFactcgz']" /></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF">杨爱荣</td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKYWYShouldyar']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKYWYFactyar']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHYWYShouldyar']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHYWYFactyar']" /></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF">孙玉萍</td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKYWYShouldsyp']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKYWYFactsyp']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHYWYShouldsyp']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHYWYFactsyp']" /></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF">孙纪文</td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKYWYShouldsjw']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKYWYFactsjw']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHYWYShouldsjw']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHYWYFactsjw']" /></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF">吴宝举</td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKYWYShouldwbj']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.SKYWYFactwbj']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHYWYShouldwbj']" /></td>
		<td bgcolor="#FFFFFF"><s:property
			value="#request['ValueOfOutput.LHYWYFactwbj']" /></td>
	</tr>
</table>
</span> <span class="chart" style="height: 380px;">
<div id="flashcontent_ywy"><strong>业务员柱状图</strong></div>
<script type="text/javascript">
		// <![CDATA[		
		var so = new SWFObject("${pageContext.request.contextPath}/Library/flashChart/amcolumn/amcolumn/amcolumn.swf", "amcolumn", "90%", "90%", "8", "#ffffff");
		so.addVariable("path", "${pageContext.request.contextPath}/Library/flashChart/amcolumn/amcolumn/");
		so.addVariable("settings_file", escape("${pageContext.request.contextPath}/MA/amcolumn_settings_ywy.xml"));
		//so.addVariable("data_file", escape("${pageContext.request.contextPath}/MA/amcolumn_data.txt"));
		so.addVariable("chart_data", "王秀云;<s:property value="#request['ValueOfOutput.SKYWYShouldwxy']" />;<s:property value="#request['ValueOfOutput.LHYWYShouldwxy']" />\n陈桂芝;<s:property value="#request['ValueOfOutput.SKYWYShouldcgz']" />;<s:property value="#request['ValueOfOutput.LHYWYShouldcgz']" />\n杨爱荣;<s:property value="#request['ValueOfOutput.SKYWYShouldyar']" />;<s:property value="#request['ValueOfOutput.LHYWYShouldyar']" />\n孙玉萍;<s:property value="#request['ValueOfOutput.SKYWYShouldsyp']" />;<s:property value="#request['ValueOfOutput.LHYWYShouldsyp']" />\n孙纪文;<s:property value="#request['ValueOfOutput.SKYWYShouldsjw']" />;<s:property value="#request['ValueOfOutput.LHYWYShouldsjw']" />\n吴宝举;<s:property value="#request['ValueOfOutput.SKYWYShouldwbj']" />;<s:property value="#request['ValueOfOutput.LHYWYShouldwbj']" />");
		so.addVariable("preloader_color", "#ffffff");
		so.write("flashcontent_ywy");
		// ]]>
	</script> </span></div>
<div class="printer">
<hr />
北京市清华园胶印厂-酷印通 http://erp.tyopf.com <span class="bottomtext">打印者:
<s:property value="#session.user.employee.realname" /></span> <span
	class="bottomtext">打印时间: <%
 			String t3 = (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
 			.format(new java.util.Date());//日期：存储日期);
 	out.print(t3);
 %> </span></div>
</html>
