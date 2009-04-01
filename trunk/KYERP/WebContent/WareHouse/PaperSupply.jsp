<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>纸张发放记录表</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/showPages.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ymPrompt/ymPrompt.js"></script>
<link rel="stylesheet" id='skin' type="text/css"
	href="${pageContext.request.contextPath}/Library/js/ymPrompt/skin/qq/ymPrompt.css" />
</head>
<body>
<span class="pageTitle"><s:property value="#request['pageTitle']" /></span>
<table width="98%" border="0" cellpadding="6" cellspacing="1"
	bgcolor="#CCCCCC">
	<tr align="center">
		<td width="85" bgcolor="#EEEEEE"><span class="From_item">任务单号</span></td>
		<td bgcolor="#EEEEEE">印品名称</td>
		<td bgcolor="#EEEEEE">类型</td>
		<td bgcolor="#EEEEEE">车间</td>
		<td bgcolor="#EEEEEE">纸张</td>
		<td bgcolor="#EEEEEE">规格</td>
		<td bgcolor="#EEEEEE">令数</td>
		<td bgcolor="#EEEEEE">发纸</td>
	</tr>
	<s:iterator value="#request['ListAFE']" id="afe" status="st">
		<tr <s:if test="#st.even">style="background-color:#EAEAEA"</s:if>
			<s:if test="#st.odd">style="background-color:#FFffff"</s:if>>
			<td align="center"><s:property value="#afe.afBase.iso" /><s:property
				value="#afe.afBase.afNo" /></td>
			<td><a
				href="${pageContext.request.contextPath}/AF/AFInfo.action?afId=<s:property value="#afe.afBase.afId" />"
				target="_blank"><s:property value="#afe.afBase.presswork" /></a></td>
			<td><s:if test="#afe.EType.equals('BB')">正文</s:if> <s:if
				test="#afe.EType.equals('Cover')">封面</s:if> <s:if
				test="#afe.EType.equals('CI')">插页</s:if></td>
			<td align="left"><s:property value="#afe.EMachine" /></td>
			<td align="left"><s:property value="#afe.EPaper" /></td>
			<td align="left"><s:property value="#afe.ESs" /></td>
			<td align="left"><a
				href="${pageContext.request.contextPath}/AF/AFInfo.action?afId=<s:property value="#afe.afBase.afId" />"
				target="_blank"><s:property value="EReam" /> + <s:property
				value="EOvers" />=<SCRIPT type="text/javascript">document.write(fixfloat(<s:property value="EReam+EOvers" />,3));</SCRIPT></a></td>
			<td align="center"><s:if test="#afe.paperStatus==0">
				<input type="image"
					src="${pageContext.request.contextPath}/Library/images/minico/yes.gif"
					onclick="ymPrompt.win(document.getElementById('popWindowPaper<s:property value="#afe.afEId" />').innerHTML,400,260,'发纸-<s:property value="#afe.afEId" />')" />
				<div id='popWindowPaper<s:property value="#afe.afEId" />'
					style="display: none;">
				<div class="popWindowBody">
				<ul>
					<li><span>领纸人: </span><s:property value="#afe.paperGetMan" /></li>
					<li><span>发纸人: </span><s:property value="#afe.paperSupplyMan" /></li>
					<li><span>发纸时间: </span><s:date name="#afe.paperSupplyTime"
						format="yyyy-MM-dd HH:MM:SS" nice="false" /></li>
				</ul>
				</div>
				</div>
			</s:if> <s:else>
				<input type="button" value="发纸"
					onclick="ymPrompt.win(document.getElementById('popWindowPaper<s:property value="#afe.afEId" />').innerHTML,400,260,'发纸-<s:property value="#afe.afEId" />')" />
				<div id="popWindowPaper<s:property value="#afe.afEId" />"
					style='display: none;'>
				<div class="popWindowBody">
				<form action="paperSupplyInput.action" method="post" id="forms"
					name="forms">领纸人: <input type="hidden" name="afEId"
					value="<s:property value="#afe.afEId" />" /> <input type="text"
					name="paperGetMan" id="paperGetMan" /> <input type="submit"
					value="提交" /></form>
				</div>
				</div>
			</s:else></td>
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
//ymPrompt.setDefaultCfg({maskAlpha:0.2,maskAlphaColor:'#00f',fixPosition: false});
</script>
</body>
</html>
