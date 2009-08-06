<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#request['Plate']" name="plate" id="plate" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>印版信息</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/function.js"></script>
<link href="${pageContext.request.contextPath}/Library/css/PlateInfo.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
<div class="pim2_secondMenu tnoprint">
<ul>
	<li><input name="b_print"
	type="button" class="ipt" onClick="printdiv('plateTable');"
	value=" 打印 "></li></ul></div>
<div id="plateTable">
<div class="BigTitle">印版信息表</div>
<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><table width="100%" align="center" class="TableBlock">
      <tr>
        <td class="TableHeader" colspan="4" align="right" bgcolor="#FFFFFF"><div align="center">基本资料</div></td>
      </tr>
      <tr>
        <td class="TableData" width="80" align="right" bgcolor="#FFFFFF">ID</td>
        <td class="TableData" align="left" bgcolor="#FFFFFF"><s:property value='#plate.id' /></td>
        <td class="TableData" width="80" align="left" bgcolor="#FFFFFF" class="TableData">档案号</td>
        <td class="TableData" align="left" bgcolor="#FFFFFF"><s:property value='#plate.danAnNo' /></td>
      </tr>
      <tr>
        <td class="TableData" width="80" align="right" bgcolor="#FFFFFF">印版名称</td>
        <td class="TableData" colspan="3" align="left" bgcolor="#FFFFFF"><s:property value='#plate.pname' /></td>
        </tr>
      <tr>
        <td class="TableData" align="right">位置编号</td>
        <td class="TableData" colspan="3" align="left"><s:property value='#plate.plateAddress' /></td>
      </tr>
      
      <tr>
        <td class="TableData" width="80" align="right">入库时间</td>
        <td class="TableData" align="left"><s:date name='#plate.inputDate' format='yyyy-MM-dd' nice='false' /></td>
        <td class="TableData" align="left">最后使用</td>
        <td class="TableData" align="left"><s:date name='#plate.lastUseDate' format='yyyy-MM-dd' nice='false' /></td>
      </tr>
      
      <tr>
        <td class="TableData" width="80" align="right">过期时间</td>
        <td class="TableData" align="left"><s:date name='#plate.expDate' format='yyyy-MM-dd' nice='false' /></td>
        <td class="TableData" align="left">版号</td>
        <td class="TableData" align="left"><s:property value='#plate.plateNo' /></td>
      </tr>

      <tr>
        <td class="TableData" width="80" align="right">版长</td>
        <td class="TableData" align="left"><s:property value='#plate.plateLength' /></td>
        <td class="TableData" align="left">版周</td>
        <td class="TableData" align="left"><s:property value='#plate.plateWidth' /></td>
      </tr>
      
      <tr>
        <td class="TableData" width="80" bgcolor="#FFFFFF" align="right">支数</td>
        <td class="TableData" bgcolor="#FFFFFF" align="left"><s:property value='#plate.plateAmount' /></td>
        <td class="TableData" bgcolor="#FFFFFF" align="left">共用版</td>
        <td class="TableData" bgcolor="#FFFFFF" align="left"><s:property value='#plate.plateShare' /></td>
      </tr>
      
      <tr>
        <td class="TableData" width="80" align="right" bgcolor="#FFFFFF">开单日期</td>
        <td class="TableData" align="left" bgcolor="#FFFFFF"><s:property value='#plate.startTime' /></td>
        <td class="TableData" align="left" bgcolor="#FFFFFF">开单人</td>
        <td class="TableData" align="left" bgcolor="#FFFFFF"><s:property value='#plate.startMan' /></td>
      </tr>

      <tr>
        <td class="TableData" width="80" align="right" bgcolor="#FFFFFF">修改时间</td>
        <td class="TableData" align="left" bgcolor="#FFFFFF"><s:property value='#plate.lastChangeTime' /></td>
        <td class="TableData" align="left" bgcolor="#FFFFFF">查看次数</td>
        <td class="TableData" align="left" bgcolor="#FFFFFF"><s:property value='#plate.viewTimes' /></td>
      </tr></table>
      </td>
    </tr>
  <tr>
    <td>&nbsp;</td>
    </tr>
  <tr>
    <td><table width="750" align="center" class="TableBlock">
      <tr>
        <td class="TableHeader" align="right" bgcolor="#FFFFFF"><div align="center">使用记录</div></td>
      </tr>
      <tr>
        <td align="right" bgcolor="#FFFFFF"><table width="100%" class="TableBlock">
            <tr>
              <td class="TableData" width="150"><div align="center">使用时间</div></td>
              <td class="TableData" width="100"><div align="center">操作员</div></td>
              <td class="TableData" width="100"><div align="center">印数 </div></td>
              <td class="TableData" ><div align="center">备注 </div></td>
            </tr>
            <s:iterator value="#plate.useLogs" status="stat">
              <tr>
                <td class="TableData"><div align="center">
                  <s:date format="yyyy-MM-dd" nice="false" name="useTime" />
                </div></td>
                <td class="TableData"><div align="center">
                  <s:property value="inputMan" />
                </div></td>
                <td class="TableData"><div align="center">
                  <s:property value="pressAmount" />
                </div></td>
                <td class="TableData"><s:property value="remark" /></td>
              </tr>
            </s:iterator>
        </table></td>
      </tr>
    </table></td>
    </tr>
</table>
</div>
</body>
</html>
