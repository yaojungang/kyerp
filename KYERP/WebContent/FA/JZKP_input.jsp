<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>结账开票</title>
</head>
<body>
<span class="pageTitle">结账开票</span>
<h2>根据印制单号生成增值税发票销货清单-清华大学出版社专用</h2>
<div align="left" style="width: 100%;"><s:form
	action="getAFinYZNoList_CBS" method="post" target="_blank">
	<table border="0" style='work-break: break-all'>
		<tr>
			<td valign="top">开票单位：<input type="text" name="client"
				style="width: 400px;" value="清华大学出版社" /><br />
			<textarea rows="10" cols="70" name="YZNoList">YZ2009</textarea></td>
			<td width="400" align="left" valign="top">请在左边的输入框中输入需要生成开票的印制单号列表,单号之间用逗号（,）隔开。
			例如给清华大学出版社结账：<br>
			YZ20083550,YZ20083551,YZ20083549,YZ20083557</td>
		</tr>
		<tr>
			<td align="center"><input type="submit" value="提交" /></td>
			<td>&nbsp;</td>
		</tr>
	</table>
</s:form></div>
<h2>根据印制单号生成增值税发票销货清单</h2>
<div align="left" style="width: 100%;"><s:form
	action="getAFinYZNoList" method="post" target="_blank">
	<table border="0" style='work-break: break-all'>
		<tr>
			<td valign="top">开票单位：<input type="text" name="client"
				style="width: 400px;" value="新东方大愚文化传播公司" /><br />
			<textarea rows="10" cols="50" name="YZNoList">TS08</textarea></td>
			<td width="400" align="left" valign="top">请在左边的输入框中输入需要生成开票的印制单号列表,单号之间用逗号（,）隔开。
			例如新东方：<br>
			TS08330,TS08328,TS08314,TS080313,TS08311,TS08296</td>
		</tr>
		<tr>
			<td align="center"><input type="submit" value="提交" /></td>
			<td>&nbsp;</td>
		</tr>
	</table>
</s:form></div>
<h2>根据任务单号生成增值税发票销货清单</h2>
<div align="left" style="width: 100%;"><s:form
	action="getAFinAFNoList" method="post" target="_blank">
	<table border="0">
		<tr>
			<td valign="top">开票单位：<input type="text" name="client"
				style="width: 400px;" value="清华大学" /><br />
			<textarea rows="10" cols="50" name="AFNoList"></textarea></td>
			<td width="400" align="left" valign="top">请在左边的输入框中输入需要生成开票的任务单号列表,单号之间用逗号（,）隔开。
			例如：<br>
			SK20080001,SK20080234,SK20080235</td>
		</tr>
		<tr>
			<td align="center"><input type="submit" value="提交" /></td>
			<td>&nbsp;</td>
		</tr>
	</table>
</s:form></div>
</html>
