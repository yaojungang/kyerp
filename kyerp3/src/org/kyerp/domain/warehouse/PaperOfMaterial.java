/**
 * 
 */
package org.kyerp.domain.warehouse;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 库存：纸张
 * 
 * @author y109 2009-11-29下午11:34:48
 */
@Entity
@DiscriminatorValue("paper")
public class PaperOfMaterial extends Material {
	private static final long	serialVersionUID	= -4449340515383717173L;
	/** 纸张规格：正度、大度 */
	private String				paperType;
	/** 纸张(mm) */
	private int					paperHeight;
	/** 纸宽(mm) */
	private int					paperWidth;
	/** 纸张大小：全开、对开、四开 */
	private String				paperSize;
	/** 纸张克重 */
	private int					paperWeight;
	/** 纸张吨价 */
	private BigDecimal			tonnePrice;
	/** 每张价格 */
	private BigDecimal			price;
	/** 每平米价格 */
	private BigDecimal			squareMetrePrice;

	public PaperOfMaterial() {
	}

	public String getPaperType() {
		return paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public int getPaperHeight() {
		return paperHeight;
	}

	public void setPaperHeight(int paperHeight) {
		this.paperHeight = paperHeight;
	}

	public int getPaperWidth() {
		return paperWidth;
	}

	public void setPaperWidth(int paperWidth) {
		this.paperWidth = paperWidth;
	}

	public String getPaperSize() {
		return paperSize;
	}

	public void setPaperSize(String paperSize) {
		this.paperSize = paperSize;
	}

	public int getPaperWeight() {
		return paperWeight;
	}

	public void setPaperWeight(int paperWeight) {
		this.paperWeight = paperWeight;
	}

	public BigDecimal getTonnePrice() {
		return tonnePrice;
	}

	public void setTonnePrice(BigDecimal tonnePrice) {
		this.tonnePrice = tonnePrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getSquareMetrePrice() {
		return squareMetrePrice;
	}

	public void setSquareMetrePrice(BigDecimal squareMetrePrice) {
		this.squareMetrePrice = squareMetrePrice;
	}

}
