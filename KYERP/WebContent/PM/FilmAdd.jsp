<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>制版车间-增加软片存储单元</title>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0">
<span class="pageTitle">增加软片存储单元</span>
<form name="form1" method="post" action="">
 <table width="90%" border="0" align="center" cellpadding="8"
		cellspacing="0" bgcolor="#ffffff">
    <tr>
      <td width="80" align="right">名称</td>
      <td><input type="text" style="width: 100px;" name="film.name" value='<s:property
				value="#film.name" />'/></td>
    </tr>
    <tr>
      <td align="right">备注</td>
      <td><textarea name="film.remark"
				style="width: 300px; height: 80px;"><s:property
				value="#film.remark" /></textarea></td>
    </tr>
    <tr>
      <td align="right">&nbsp;</td>
      <td><input type="submit" value="保存" name="submit" /></td>
    </tr>
  </table>
</form>
    
</body>
</html>