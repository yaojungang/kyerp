package org.kyerp.utils;

import java.math.BigDecimal;

/**
 *org.kyerp.utils.PrintUtils.java
 * 
 * @author y109
 *         2010-3-25下午06:15:22
 */
public class PrintUtils{

	/**
	 * 吨价转令价
	 * 1 吨=1000*1000 克
	 * 克价=吨价/(1000*1000) ---以克为单位
	 * 正度1令重量=定量*面积*张数=定量*(1.092*0.787)*500=定量*430 ---以克为单位
	 * 那么1令单价=克价*重量=[吨价/(1000*1000)] *[定量*(1.092*0.787)*500]=0.43*定量*吨价÷1000
	 * 
	 * 大度不外乎把尺寸规格换一下,算出来就是0.531*定量*吨价÷1000
	 * */
	public static BigDecimal getPaperLinPriceByTonePrice(BigDecimal tonePrice, int paperWeight, int paperHeight, int paperWidth) {
		// 克重
		BigDecimal paperWeightB = new BigDecimal(paperWeight);
		// 纸长
		BigDecimal paperHeightB = new BigDecimal(paperHeight).divide(new BigDecimal("1000"));
		// 纸宽
		BigDecimal paperWidthB = new BigDecimal(paperWidth).divide(new BigDecimal("1000"));
		// 克价
		BigDecimal gPrice = tonePrice.divide(new BigDecimal("1000000"));
		// System.out.println("克价:" + gPrice);
		// 令重
		BigDecimal gWeight = paperWeightB.multiply(paperHeightB.multiply(paperWidthB)).multiply(new BigDecimal("500"));
		// System.out.println("令重:" + gWeight);
		// 令价
		BigDecimal linPrice = gPrice.multiply(gWeight);
		System.out.println("令价:" + linPrice);
		return linPrice;
	}

	/**
	 * 吨价转张价
	 * 
	 * 500张完全相同的纸页叫做一令
	 * 每张的价格就是 令价/500
	 * */
	public static BigDecimal getPaperPagePriceByTonePrice(BigDecimal tonePrice, int paperWeight, int paperHeight, int paperWidth) {
		// 令价
		BigDecimal linPrice = getPaperLinPriceByTonePrice(tonePrice, paperWeight, paperHeight, paperWidth);
		// System.out.println("令价:" + linPrice);
		BigDecimal pricePrePage = linPrice.divide(new BigDecimal("500"));
		return pricePrePage;
	}
}
