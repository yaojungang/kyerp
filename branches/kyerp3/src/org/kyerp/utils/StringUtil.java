/**
 * 
 */
package org.kyerp.utils;

/**
 * @author y109 2010-1-28下午04:47:27
 */
public class StringUtil {
	public static String Array2String(Long[] longs) {
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < longs.length; i++) {
			sb.append(String.valueOf(longs[i]) + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public static String Array2String(String[] strings) {
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < strings.length; i++) {
			sb.append(String.valueOf(strings[i]) + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

}
