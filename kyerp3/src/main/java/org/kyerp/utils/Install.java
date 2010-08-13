package org.kyerp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * 安装类
 * 
 * @author liufang
 * 
 */
public class Install{
	public static void dbXml(String fileName, String dbHost, String dbPort, String dbName, String dbUser, String dbPassword) throws Exception {
		String s = FileUtils.readFileToString(new File(fileName));
		s = s.replaceFirst("DB_HOST", dbHost);
		s = s.replaceFirst("DB_PORT", dbPort);
		s = s.replaceFirst("DB_NAME", dbName);
		s = s.replaceFirst("DB_USER", dbUser);
		s = s.replaceFirst("DB_PASSWORD", dbPassword);
		FileUtils.writeStringToFile(new File(fileName), s);
	}

	public static Connection getConn(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String connStr = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?user=" + dbUser + "&password=" + dbPassword + "&characterEncoding=UTF-8";
		Connection conn = DriverManager.getConnection(connStr);
		return conn;
	}

	public static void webXml(String fromFile, String toFile) throws Exception {
		FileUtils.copyFile(new File(fromFile), new File(toFile));
	}

	/**
	 * 创建数据库
	 * 
	 * @param dbHost
	 * @param dbName
	 * @param dbPort
	 * @param dbUser
	 * @param dbPassword
	 * @throws Exception
	 */
	public static void createDb(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		;
		String connStr = "jdbc:mysql://" + dbHost + ":" + dbPort + "?user=" + dbUser + "&password=" + dbPassword + "&characterEncoding=UTF-8";
		Connection conn = DriverManager.getConnection(connStr);
		Statement stat = conn.createStatement();
		String sql = "drop database if exists " + dbName;
		stat.execute(sql);
		sql = "create database " + dbName + " CHARACTER SET utf8 COLLATE utf8_general_ci";
		stat.execute(sql);
		stat.close();
		conn.close();
	}

	public static void changeDbCharset(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword) throws Exception {
		Connection conn = getConn(dbHost, dbPort, dbName, dbUser, dbPassword);
		Statement stat = conn.createStatement();
		String sql = "ALTER DATABASE " + dbName + " CHARACTER SET utf8 COLLATE utf8_general_ci";
		stat.execute(sql);
		stat.close();
		conn.close();
	}

	/**
	 * 创建表
	 * 
	 * @param dbHost
	 * @param dbName
	 * @param dbPort
	 * @param dbUser
	 * @param dbPassword
	 * @param sqlList
	 * @throws Exception
	 */
	public static void createTable(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword, List<String> sqlList) throws Exception {
		Connection conn = getConn(dbHost, dbPort, dbName, dbUser, dbPassword);
		Statement stat = conn.createStatement();
		for (String dllsql : sqlList) {
			stat.addBatch(dllsql);
		}
		stat.executeBatch();
		stat.close();
		conn.close();
	}

	/**
	 * 更新配置
	 * 
	 * @param dbHost
	 * @param dbName
	 * @param dbPort
	 * @param dbUser
	 * @param dbPassword
	 * @param domain
	 * @param cxtPath
	 * @param port
	 * @throws Exception
	 */
	public static void updateConfig(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword, String userName, String password, String port) throws Exception {
		Connection conn = getConn(dbHost, dbPort, dbName, dbUser, dbPassword);
		Statement stat = conn.createStatement();
		String sql = "";
// sql = "update CORE_WEBSITE set DOMAIN='" + domain + "'";
// stat.executeUpdate(sql);
// sql = "update CORE_GLOBAL set CONTEXT_PATH='" + cxtPath + "',PORT=" + port;
// stat.executeUpdate(sql);
		sql = "update user set userName='" + userName + "',password=" + password;
		stat.executeUpdate(sql);
		stat.close();
		conn.close();
	}

	/**
	 * 读取sql语句。“/*”开头为注释，“;”为sql结束。
	 * 
	 * @param fileName
	 *            sql文件地址
	 * @return list of sql
	 * @throws Exception
	 */
	public static List<String> readSql(String fileName) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
		List<String> sqlList = new ArrayList<String>();
		StringBuilder sqlSb = new StringBuilder();
		String s = null;
		while ((s = br.readLine()) != null) {
			if(s.startsWith("/*")) {
				continue;
			}
			if(s.endsWith(";")) {
				sqlSb.append(s);
				sqlSb.setLength(sqlSb.length() - 1);
				sqlList.add(sqlSb.toString());
				sqlSb.setLength(0);
			} else {
				sqlSb.append(s);
			}
		}
		br.close();
		return sqlList;
	}
}
