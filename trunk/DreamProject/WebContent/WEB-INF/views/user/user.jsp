<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/share/taglib.jsp" %>
<%@ taglib uri="/WEB-INF/tld/jmesa.tld" prefix="jmesa" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/css/web.css"></link>
  <link rel="stylesheet" type="text/css" href="${ctx}/css/jmesa.css"></link>
  <script type="text/javascript" src="${ctx}/js/jquery-1.3.min.js"></script>
  <script type="text/javascript" src="${ctx}/js/jquery.bgiframe.pack.js"></script>
  <script type="text/javascript" src="${ctx}/js/jquery.jmesa.js"></script>
  <script type="text/javascript" src="${ctx}/js/jmesa.js"></script>
<title>user</title>
</head>
<body>
<jmesa:tableFacade id="tag" items="${userList}" maxRows="12" exportTypes="csv,excel" 
maxRowsIncrements="12,24,60,120"
var="bean"
> 
<jmesa:htmlTable>
   <jmesa:htmlRow>                     
     <jmesa:htmlColumn property="id" title="ID"/>
     <jmesa:htmlColumn property="userName" title="用户名"/>
     <jmesa:htmlColumn property="passWord" title="密码"/>
     <jmesa:htmlColumn property="lastUseTime" title="最后登录时间"/>
   </jmesa:htmlRow> 
</jmesa:htmlTable> 
</jmesa:tableFacade>
<script type="text/javascript">
            function onInvokeAction(id) {
                $.jmesa.setExportToLimit(id, '');
                $.jmesa.createHiddenInputFieldsForLimitAndSubmit(id);
            }
            function onInvokeExportAction(id) {
                var parameterString = $.jmesa.createParameterStringForLimit(id);
                location.href = '${ctx}/tag.run?' + parameterString;
            }
        </script>
  <hr />
<!-- name 是action的list 如果你把他放在session中了就不用写requestURI 否则就一定要写。requestURI值action的路径 -->
  <display:table name="userList"/>
  
  <hr />
<table>
<tr>
	<th>index</th>
	<th>count</th>
    <th>id</th>
    <th>userName</th>
    <th>lastUseTime</th>    
</tr>
<c:forEach var="user" items= "${userList}" varStatus="status">
  <tr>
  	<td>${status.index}</td>
  	<td>${status.count}</td>
  	<td>${user.id}</td>
    <td>${user.userName}</td>
    <td>${user.lastUseTime}</td>
  </tr>
</c:forEach>
</table>

</body>
</html>