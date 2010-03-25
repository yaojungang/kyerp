<%@page contentType="text/html; charset=utf-8" language="java" import="org.kyerp.utils.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>安装完成--JEECMS安装向导</title>
<link href="img/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%
	String dbHost = request.getParameter("dbHost");
	String dbPort = request.getParameter("dbPort");
	String dbName = request.getParameter("dbName");
	String dbUser = request.getParameter("dbUser");
	String dbPassword = request.getParameter("dbPassword");

	String isCreateDb = request.getParameter("isCreateDb");
	String isCreateTable = request.getParameter("isCreateTable");
	String isInitData = request.getParameter("isInitData");
	String domain = request.getParameter("domain");
	String cxtPath = request.getParameter("cxtPath");
	String port = request.getParameter("port");
	String adminUserName = request.getParameter("adminUserName");
	String adminPassword = request.getParameter("adminPassword");

	String dbFileName = "/install/db/kyerp3_db.sql";
	String initFileName = "/install/db/kyerp3_init.sql";
	String dbXmlFileName = "/WEB-INF/classes/jdbc.properties";
	String webXmlFrom = "/install/config/web.xml";
	String webXmlTo = "/WEB-INF/web.xml_test";
	//创建数据库
	if ("true".equals(isCreateDb)) {
		Install.createDb(dbHost, dbPort, dbName, dbUser, dbPassword);
	} else {
		Install.changeDbCharset(dbHost, dbPort, dbName, dbUser, dbPassword);
	}
	//创建表
	if ("true".equals(isCreateTable)) {
		String sqlPath = application.getRealPath(dbFileName);
		List<String> sqlList = Install.readSql(sqlPath);
		Install.createTable(dbHost, dbPort, dbName, dbUser, dbPassword,
				sqlList);
	}
	//初始化数据
	if ("true".equals(isInitData)) {
		String initPath = application.getRealPath(initFileName);
		List<String> initList = Install.readSql(initPath);
		Install.createTable(dbHost, dbPort, dbName, dbUser, dbPassword,
				initList);
	}
	//更新配置
	//Install.updateConfig(dbHost, dbPort, dbName, dbUser, dbPassword,domain, cxtPath, port);
	//处理数据库配置文件
	String dbXmlPath = application.getRealPath(dbXmlFileName);
	Install
			.dbXml(dbXmlPath, dbHost, dbPort, dbName, dbUser,
					dbPassword);
	//处理web.xml
	String webXmlFromPath = application.getRealPath(webXmlFrom);
	String webXmlToPath = application.getRealPath(webXmlTo);
	Install.webXml(webXmlFromPath, webXmlToPath);
%>

<table width="600" align="center"
	style="border: #106DBA 1px solid; margin-top: 8%;">
	<tr>
		<td bgcolor="#D1E9FA">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="27%" height="60" rowspan="2" align="center"><img
					src="img/logo.gif" border="0" /></td>
				<td width="73%" height="30" class="f14b">3、系统安装完成</td>
			</tr>
			<tr>
				<td height="20" valign="top">&nbsp;&nbsp;&nbsp;&nbsp;安装系统安装完成，请重启TOMCAT服务。</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td height="280" align="left" bgcolor="#F0F8FD"	style="padding: 10px; line-height: 1.7em;">恭喜您，系统已经安装成功！<br />
		请重启TOMCAT服务。只有重启TOMCAT服务之后，安装才能生效。</td>
	</tr>
</table>

</body>
</html>
