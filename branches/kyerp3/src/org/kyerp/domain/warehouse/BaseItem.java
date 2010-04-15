package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.kyerp.domain.BaseDomain;

/**
 * 基本库存项目类
 *org.kyerp.domain.warehouse.BaseItem.java
 * 
 * @author y109
 *         2010-4-2下午07:56:54
 */
@Entity
public abstract class BaseItem extends BaseDomain implements Serializable{
	private static final long	serialVersionUID	= 1L;
	/** 编号 **/
	private String				serialNumber;
	/** 名称 **/
	private String				name;
	/** 主单位 */
	@ManyToOne
	private Unit				unit;
	/** 数量 */
	@Column(precision = 12,scale = 4)
	private BigDecimal			amount				= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 单价 */
	@Column(precision = 12,scale = 4)
	private BigDecimal			price				= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 类别 */
	@ManyToOne
	private BaseCategory		category;

	public BaseItem() {
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public BaseCategory getCategory() {
		return category;
	}

	public void setCategory(BaseCategory category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
