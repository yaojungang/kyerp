<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OK</title>
</head>
<body>
<center><br>
<br>
<br>
<table width="604" border="0" cellpadding="0" cellspacing="1"
	bordercolor="#000000" bgcolor="#007C00">
	<tr>
		<td width="602" align="center" valign="middle" bgcolor="#FFFFFF">
		<table width="516" border="0" cellspacing="0" cellpadding="4">
			<tr>
				<td width="128" align="center" bgcolor="#FFFFFF"><a
					href="<s:property value="url"/>"><img
					src="../Library/images/ok.jpg" width="64" height="64" border="0"></a></td>
				<td>该任务已成功标记为完成!</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td align="left"><input type="button" value="返回"
					onClick="javascript:location.href='<s:property value="url"/>';"></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</center>
</body>
</html>