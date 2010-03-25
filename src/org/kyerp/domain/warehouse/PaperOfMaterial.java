/**
 * 
 */
package org.kyerp.domain.warehouse;

import java.math.BigDecimal;

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
public class PaperOfMaterial extends Material{
	private static final long	serialVersionUID	= -4449340515383717173L;
	/** 纸张名称 */
	private String				paperName			= "";
	/** 纸长(mm) */
	private int					paperHeight			= 0;
	/** 纸宽(mm) */
	private int					paperWidth			= 0;
	/** 纸张克重 */
	private int					paperWeight			= 0;
	/** 纸张吨价 */
	private BigDecimal			tonnePrice			= new BigDecimal("0");
	/** 每平米价格 */
	private BigDecimal			squareMetrePrice	= new BigDecimal("0");
	/** 每张价格 */
	private BigDecimal			pricePrePage;

	public PaperOfMaterial() {
	}

	// 设置物料名称
	public void setMaterialName() {
		StringBuffer paperBuffer = new StringBuffer();
		paperBuffer.append("(").append(this.getPaperWeight()).append("克").append(super.getSupplier().getName()).append(")");
		if(null != super.getBrand()) {
			paperBuffer.append(super.getBrand().getName());
		}
		paperBuffer.append(this.paperName);
		super.setMaterialName(paperBuffer.toString());
	}

	// 设置物料规格
	public void setSpecification() {
		super.setSpecification(String.valueOf(this.getPaperWidth()) + "*" + String.valueOf(this.getPaperHeight()));
	}

	/** 在对象新建前保存建立时间 */
	@Override
	@PrePersist
	public void prePersist() {
		this.setMaterialName();
		this.setSpecification();
		super.prePersist();
	}

	/** 在对象更新前保存修改时间 */
	@PreUpdate
	@Override
	public void preUpdate() {
		this.setMaterialName();
		this.setSpecification();
		super.preUpdate();
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

	public int getPaperWeight() {
		return paperWeight;
	}

	public void setPaperWeight(int paperWeight) {
		this.paperWeight = paperWeight;
	}

	public BigDecimal getTonnePrice() {
		return tonnePrice;
	}

	public BigDecimal getPricePrePage() {
		return pricePrePage;
	}

	public void setPricePrePage(BigDecimal pricePrePage) {
		this.pricePrePage = pricePrePage;
	}

	public void setTonnePrice(BigDecimal tonnePrice) {
		this.tonnePrice = tonnePrice;
	}

	public BigDecimal getSquareMetrePrice() {
		return squareMetrePrice;
	}

	public void setSquareMetrePrice(BigDecimal squareMetrePrice) {
		this.squareMetrePrice = squareMetrePrice;
	}

}
