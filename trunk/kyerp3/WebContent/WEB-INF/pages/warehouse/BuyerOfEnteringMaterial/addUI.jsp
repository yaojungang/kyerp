<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>采购入库单-添加</title>
</head>
<body>
<h1>采购入库单-添加</h1>
<form action="add.html" method="post">
<label>库房</label><select name="warehouse.id">
<c:forEach items="${warehouses}" var="s">
<option value="${s.id}">${s.serialNumber} [${s.name}]</option>
</c:forEach>
</select>
<table width="100%" border="1" cellspacing="0" cellpadding="0">
  <tr>
    <td>序号</td>
    <td>物品代码</td>
    <td>物品名称</td>
    <td>型号规格</td>
    <td>供应商</td>
    <td>单位</td>
    <td>数量</td>
    <td>单价</td>
    <td>金额</td>
    <td>备注</td>
  </tr>
<c:forEach items="${buyerOfEnteringMaterial.materialBatchs}" var="s" varStatus="loopStatus">
  <spring:bind path="buyerOfEnteringMaterial.materialBatchs[${loopStatus.index}]">
  <tr>
    <td>${s.batchNumber}</td>
    <td><input type="text" style="width:30px;" name="materialBatchs[${loopStatus.index}].material.id" value="${s.material.id}"/></td>
    <td></td>
    <td>&nbsp;</td>
    <td><select name="materialBatchs[${loopStatus.index}].supplier.id">
  <c:forEach items="${suppliers}" var="s">
  <option value="${s.id}">${s.nameSpell} [${s.name}]</option>
  </c:forEach>
  </select></td>
    <td><input type="text" style="width:30px;" name="materialBatchs[${loopStatus.index}].module" value="${s.module}"/></td>
    <td><input type="text" style="width:30px;" name="materialBatchs[${loopStatus.index}].amount" value="${s.amount}"/></td>
    <td><input type="text" style="width:30px;" name="materialBatchs[${loopStatus.index}].price" value="${s.price}"/></td>
    <td>&nbsp;</td>
    <td><input type="text" style="width:30px;" name="materialBatchs[${loopStatus.index}].remark" value="${s.remark}"/></td>
  </tr>
</spring:bind>
  </c:forEach>
</table>
<br />

<input type="submit" value="提交">
</form>
</body>
</html>