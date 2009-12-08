/**
 * 
 */
package org.kyerp.domain.warehouse;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * 库存：纸张
 * 
 * @author y109 2009-11-29下午11:34:48
 */
@Entity
@DiscriminatorValue("paper")
public class PaperOfMaterial extends Material {
	private static final long	serialVersionUID	= -4449340515383717173L;
	/** 纸张名称 */
	private String				paperName;
	/** 纸张规格：正度、大度 */
	private String				paperType;
	/** 纸长(mm) */
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

	public void setName() {

		String paperNameString = this.getPaperWeight() + "克"
				+ super.getBrand().getName() + this.paperName + "("
				+ this.getPaperWidth() + "*" + this.getPaperHeight() + ")";
		System.out.println(paperNameString);
		super.setName(paperNameString);
	}

	/** 在对象新建前保存建立时间 */
	@Override
	@PrePersist
	public void prePersist() {
		this.setName();
		super.createTime = new Date();
	}

	/** 在对象更新前保存修改时间 */
	@Override
	@PreUpdate
	void preUpdate() {
		this.setName();
		super.updateTime = new Date();
	}

	public String getPaperType() {
		return paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
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
