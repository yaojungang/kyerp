<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QYYT-D-8.2.4-09-（单色）印刷品巡检记录</title>
<link href="${pageContext.request.contextPath}/Library/css/ISOForm.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
 src="${pageContext.request.contextPath}/Library/js/function.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Library/js/showPages.js"></script>
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/Library/js/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/Library/js/table2excel.js"></script>
</head>
<body>

<div id="div_print">
<div class="height30"></div>
<h1>（单色）印刷品巡检记录</h1>
<div align="left">QYYT-D-8.2.4-09</div>
<table width="100%" border="1" align="center" cellpadding="0"
 cellspacing="0" bordercolor="#000000" id="tableExcel0">
<tr>
  <td>ID</td>
  <td>任务单号</td>
  <td>印品名称</td>  
  <td>正反套印</td>
  <td>墨色</td>
  <td> 外观</td>
  <td> 文字</td>
  <td>网点 </td>
  <td>综合结论</td>
  <td>检验员</td>
</tr>
<c:forEach items="${list}" var="item">
 <tr>
   <td>${item.id}</td>
   <td><a href="${pageContext.request.contextPath}/AF/AFInfo.action?afId=${item.afBase.afId}">${item.afBase.iso}${item.afBase.afNo}</a></td>
   <td>${item.afBase.presswork}</td>   
   <td>${item.examItem01}</td>
   <td>${item.examItem02}</td>
   <td>${item.examItem03}</td>
   <td>${item.examItem04}</td>
   <td>${item.examItem05}</td>
   <td><a href="edit.action?afId=${item.afBase.afId}&id=${item.id}">${item.examResult}</a></td>
   <td>${item.examEmployee.realname}</td>
 </tr>
</c:forEach>
</table>

</div>
<div align="center" style="padding: 20px;"><input name="b_print"
 type="button" class="ipt" onClick="printdiv('div_print');"
 value=" 打印报表 "> <input type="button"
 onClick="javascript:talbe2excel_method1('div_print');" value="导出到EXCEL">
<input type="button"
 onClick="javascript:getXlsFromTbl('tableExcel0',null);" value="导出CVS文件">
</div>
</body>
</html>