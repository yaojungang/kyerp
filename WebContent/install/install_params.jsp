<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
<%@ include file="/WEB-INF/jsp/share/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统参数设置--KYEPR3安装向导</title>
<link href="img/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function formSubmit() {
	document.getElementById('beforeSubmit').style.display = "none";
	document.getElementById('afterSubmit').style.display = "";
}
</script>
</head>

<body>
<form action="initSystem.html" method="post" onsubmit="return formSubmit();">
<table width="900" align="center" style="border:#106DBA 1px solid; margin-top:30px;">
  <table width="900" align="center" style="border:#106DBA 1px solid; margin-top:10px;">
  <tr>
    <td bgcolor="#D1E9FA" align="center"><h1>安装KYERP3</h1></td>
  </tr>
  <tr><td bgcolor="#D1E9FA"><h3>2、参数设置</h3></td></tr>
  <tr>
    <td height="400" align="center" bgcolor="#F0F8FD"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="30%" height="30" align="right">单位名称：</td>
        <td width="22%" align="left"><input name="orgName" type="text" class="input" /></td>
        <td align="left">单位名称</td>
      </tr>
      <tr>
        <td height="30" align="right">管理员：</td>
        <td align="left"><input name="adminUserName" type="text" class="input" value="admin"/></td>
				<td align="left">管理员的用户名</td>
      </tr>
      <tr>
        <td height="30" align="right">密码：</td>
        <td align="left"><input name="adminPassword" type="text" class="input" type="password" value=""/></td>
				<td align="left">管理员的密码</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="30" align="center" bgcolor="#D1E9FA">
		<span id="beforeSubmit"><input type="submit" class="btn" value=" 提 交 " />
		</span>
		<span id="afterSubmit" style="display:none;color:red;">安装需要十几秒的时间，请您耐心等待...</span>
	</td>
  </tr>
</table>
</form>
</body>
</html>
