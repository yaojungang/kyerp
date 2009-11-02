<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user</title>
</head>
<body>

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