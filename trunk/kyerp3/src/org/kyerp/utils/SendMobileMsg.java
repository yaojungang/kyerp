package org.kyerp.utils;

import java.util.HashMap;
import java.util.Map;

public class SendMobileMsg {
	public static String SendMsg(String myPhone, String myPassword,
			String toPhone, String msg) {
		Map<String, Object> requestParamsMap = new HashMap<String, Object>();
		requestParamsMap.put("phone", myPhone);
		requestParamsMap.put("fetion_password", myPassword);
		requestParamsMap.put("tophone", toPhone);
		requestParamsMap.put("message", msg);
		//        
		String requestUrl = "http://fetion.jzland.com/FetionSendMsg.php";
		//        
		String httpGet = org.kyerp.utils.SendHttpPost.sendPost(requestUrl,
				requestParamsMap);
		// System.out.println("webget:"+httpGet);
		return httpGet;
	}
}
