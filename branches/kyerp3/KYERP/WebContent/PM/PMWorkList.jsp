<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>制版车间工作记录</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/showPages.js"></script>
</head>
<body>
<c:if test="${requestScope.ListAFE !=null }">
<span class="pageTitle">制版车间工作记录</span>
<table width="100%" border="1" cellpadding="8" cellspacing="0" bordercolor="#000000">
  <tr>
    <td rowspan="2"><div align="center">任务单号</div></td>
    <td rowspan="2"><div align="center">印品名称</div></td>
    <td rowspan="2"><div align="center">类型</div></td>
    <td rowspan="2"><div align="center">色数</div></td>
    <td rowspan="2"><div align="center">接活时间</div></td>
    <td colspan="2"><div align="center">拼版</div></td>
    <td colspan="5"><div align="center">晒版</div></td>
    <td colspan="2"><div align="center">机组领单</div></td>
    <td colspan="2"><div align="center">软片</div></td>
  </tr>
  <tr>
    <td><div align="center">人员</div></td>
    <td><div align="center">数量</div></td>
    <td><div align="center">人员</div></td>
    <td><div align="center">方法</div></td>
    <td><div align="center">PS版数</div></td>
    <td><div align="center">曝光时间</div></td>
    <td><div align="center">真空度</div></td>
    <td><div align="center">领单人</div></td>
    <td><div align="center">时间</div></td>
    <td><div align="center">状态</div></td>
    <td><div align="center">位置</div></td>
  </tr>
 <s:iterator value="#request['ListAFE']" id="afe" status="st">
  <tr>
    <td>${afe.afBase.iso}${afe.afBase.afNo}</td>
    <td>${afe.afBase.presswork}</td>
    <td>
<s:if test="#afe.EType.equals('BB')">正文</s:if>
<s:if test="#afe.EType.equals('Cover')">封面</s:if>
<s:if test="#afe.EType.equals('CI')">插页</s:if>
    </td>
    <td>${afe.EColorFrontN}+${afe.EColorBackN }</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</s:iterator>
</table>
</c:if>
<c:if test="${requestScope.AFPager !=null }">

<script language="JavaScript">
<!--
var pg = new showPages('pg');
pg.pageCount =${requestScope.AFPager.totalPage};  // 定义总页数(必要)
pg.argName = 'currentPage';  // 定义参数名(可选,默认为page)
pg.printHtml(1);
//-->
</script>
</c:if>
</body>
</html>
