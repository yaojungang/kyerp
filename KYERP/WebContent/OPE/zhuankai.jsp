<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>转开任务单</title>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0">
<br>
<table width="100%" border="0" cellspacing="0" cellpadding="8">
	<tr>
		<td align="left" valign="top"><img
			src="images/pageTitle_zkrwd.jpg" width="450" height="80"></td>
	</tr>
	<tr>
		<td align="left" valign="top">
		<form name="form1" method="get" action="ZK.action">请输入完整任务单号: <input
			type="text" name="AFNo" id="AFNo"> <input type="submit"
			name="button" id="button" value="提交"> <br>
		<br>
		(注意:完整任务单号是标识一个任务的唯一标识,包括字母部分和数字部分,如: SK20081234)</form>
		<br>
		</td>
	</tr>
</table>
</body>
</html>