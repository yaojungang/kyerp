package org.kyerp.utils;

import java.math.BigDecimal;

public class MathTools {
	public static double round0(double v,int scale){ 
		//四舍五入函数１
		String temp="#,##0."; 
		for (int i=0;i<scale ;i++ ) 
		{ 
		temp+="0"; 
		} 
		return Double.valueOf(new java.text.DecimalFormat(temp).format(v)); 
		} 
	public static double round(double d, int scale) {
//		四舍五入函数２
        long temp=1;
        for (int i=scale; i>0; i--) {
                temp*=10;
        }
        d*=temp;
        long dl=Math.round(d);
        return (double)(dl)/temp;
	}
	//去掉多余的0
	public static String removeTailZero(BigDecimal b) {
		 String s = b.toString();
		 int i, len = s.length();
		 for (i = 0; i < len; i++)
		  if (s.charAt(len - 1 - i) != '0')
		   break;
		 if (s.charAt(len - i - 1) == '.')
		  return s.substring(0, len - i - 1);
		 return s.substring(0, len - i);
		} 
}
