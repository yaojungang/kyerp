package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.kyerp.domain.BaseDomain;

/**
 * 物料库存表
 * 
 * @author y109 2010-3-19下午06:40:24
 */
@Entity
public class Stock extends BaseDomain implements Serializable {

	private static final long		serialVersionUID	= 1L;
	/** 编号 **/
	private String					serialNumber;
	/** 物料 */
	@ManyToOne
	private Material				material;
	/** 物料明细 */
	@OneToMany(mappedBy = "stock", cascade = { CascadeType.ALL })
	private final List<StockDetail>	stockDetails		= new ArrayList<StockDetail>();
	/** 总数量 */
	private BigDecimal				totalAmount;
	/** 单位 */
	@ManyToOne
	private Unit					unit;
	/** 绝对平均价格 */
	private BigDecimal				price;
	/** 总金额 */
	private BigDecimal				cost;

	public Stock() {
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public List<StockDetail> getStockDetails() {
		return stockDetails;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

}
