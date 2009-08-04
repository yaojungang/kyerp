package com.jzland.ChengDe.util;

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

}
