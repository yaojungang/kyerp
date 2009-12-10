package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.kyerp.domain.BaseDomain;

/**
 * 入库单明细表
 * 
 * @author y109 2009-12-10下午08:16:39
 */
@Entity
public class EnteringMaterialDetail extends BaseDomain implements Serializable {
	private static final long	serialVersionUID	= 1L;
	@ManyToOne
	private EnteringMaterial	enteringMaterial;
	/** 材料 **/
	@ManyToOne
	private Material			material;
	/** 单位 */
	private String				module;
	/** 价格 */
	private BigDecimal			price;
	/** 数量 */
	private Float				amount;

	public EnteringMaterialDetail() {
	}

	public EnteringMaterial getEnteringMaterial() {
		return enteringMaterial;
	}

	public void setEnteringMaterial(EnteringMaterial enteringMaterial) {
		this.enteringMaterial = enteringMaterial;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

}
