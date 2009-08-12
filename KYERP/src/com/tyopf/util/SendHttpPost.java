package com.tyopf.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.sun.xml.internal.ws.handler.HandlerException;

public class SendHttpPost {
	/**
	 * Map<String, Object> requestParamsMap = new HashMap<String, Object>(); //
	 * 认证的类型SESSION requestParamsMap.put("authType",SESSION); //
	 * 认证的类型为session时用的SessionID requestParamsMap.put("sessionID", sessionID);
	 * // 取得的数据的类型是基本信息 requestParamsMap.put("dataType", basic); // String
	 * requestUrl = "http://127.0.0.1:8080/test_toolkit"; //
	 * sendPostByHC(requestUrl, requestParamsMap); 使用HttpClient向指定URL send
	 * Post请求
	 * 
	 * @param requestUrl
	 *            请求的URL
	 * @param requestParamsMap
	 *            请求的参数
	 * @return response结果
	 * @author Jingbao Xu
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public static String sendPostByHC(String requestUrl, Map<String, Object> requestParamsMap) {
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(requestUrl);
		postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		String sResponseBody = null;
		NameValuePair[] nameValuePairPairs = null;
		NameValuePair nameValuePair = null;
		try {
			if (requestParamsMap != null && requestParamsMap.size() > 0) {
				nameValuePairPairs = new NameValuePair[requestParamsMap.size()];
				int i = 0;
				// 组织请求参数
				Iterator it = requestParamsMap.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry element = (Map.Entry) it.next();
					nameValuePair = new NameValuePair();
					nameValuePair.setName(String.valueOf(element.getKey()));
					nameValuePair.setValue(String.valueOf(element.getValue()));
					nameValuePairPairs[i++] = nameValuePair;
				}
			}
			// use post
			postMethod.setRequestBody(nameValuePairPairs);
			// execute this method
			client.executeMethod(postMethod);
			int responseCode = postMethod.getStatusCode();
			if (responseCode == HttpStatus.SC_OK) {
				// System.out.println("Post Success!");
				sResponseBody = postMethod.getResponseBodyAsString();
			}
			else {
				// System.out.println(" Error===" + responseCode);
			}
		}
		catch (HandlerException he) {
			// System.out.println("HTTP Problem: " + he.getMessage());
		}
		catch (IOException ioe) {
			// System.out.println("IO Exeception: " + ioe.getMessage());
		}
		finally {
			postMethod.releaseConnection();
			postMethod.recycle();
		}
		//sResponseBody = Html2Text(sResponseBody);
		return sResponseBody;
	}
	
	/**
	 * 向指定URL发送POST方法的请求
	 * 
	 * @param requestUrl
	 *            请求的URL
	 * @param requestParamsMap
	 *            请求的参数
	 * @return response结果
	 * @author Jingbao Xu
	 */
	@SuppressWarnings( { "unchecked", "unused" })
	static String sendPost(String requestUrl, Map<String, Object> requestParamsMap) {
		PrintWriter printWriter = null;
		BufferedReader bufferedReader = null;
		StringBuffer responseResult = new StringBuffer();
		StringBuffer params = new StringBuffer();
		HttpURLConnection httpURLConnection = null;
		// 组织请求参数
		Iterator it = requestParamsMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry element = (Map.Entry) it.next();
			params.append(element.getKey());
			params.append("=");
			params.append(element.getValue());
			params.append("&");
		}
		if (params.length() > 0) {
			params.deleteCharAt(params.length() - 1);
		}
		try {
			URL realUrl = new URL(requestUrl);
			// 打开和URL之间的连接
			httpURLConnection = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			httpURLConnection.setRequestProperty("accept", "*/*");
			httpURLConnection.setRequestProperty("connection", "Keep-Alive");
			httpURLConnection.setRequestProperty("Content-Length", String.valueOf(params.length()));
			// 发送POST请求必须设置如下两行
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			printWriter = new PrintWriter(httpURLConnection.getOutputStream());
			// 发送请求参数
			printWriter.write(params.toString());
			// flush输出流的缓冲
			printWriter.flush();
			// 根据ResponseCode判断连接是否成功
			int responseCode = httpURLConnection.getResponseCode();
			if (responseCode != 200) {
				// System.out.println(" Error===" + responseCode);
			}
			else {
				// System.out.println("Post Success!");
			}
			// 定义BufferedReader输入流来读取URL的ResponseData
			bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				responseResult.append("\n").append(line);
			}
		}
		catch (Exception e) {
			// System.out.println("send post request error!" + e);
		}
		finally {
			httpURLConnection.disconnect();
			try {
				if (printWriter != null) {
					printWriter.close();
				}
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		String t = Html2Text(responseResult.toString());
		return t;
		
	}
	
	public static String Html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
																										// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
																									// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签
			textStr = htmlStr;
		}
		catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		return textStr;// 返回文本字符串
	}
}
