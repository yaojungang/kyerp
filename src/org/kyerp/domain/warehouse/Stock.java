package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.kyerp.domain.BaseDomain;

/**
 * 物料库存表
 * 
 * @author y109 2010-3-19下午06:40:24
 */
@Entity
public class Stock extends BaseDomain implements Serializable{

	private static final long	serialVersionUID	= 1L;
	/** 编号 **/
	private String				serialNumber;
	/** 物料 */
	@ManyToOne
	private Material			material;
	/** 物料明细 */
	@OneToMany(mappedBy = "stock",cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
	private List<StockDetail>	stockDetails		= new ArrayList<StockDetail>();
	/** 总数量 */
	@Column(precision = 12,scale = 4)
	private BigDecimal			totalAmount			= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 单位 */
	@ManyToOne
	private Unit				unit;
	/** 绝对平均价格 */
	@Column(precision = 12,scale = 4)
	private BigDecimal			price				= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 总金额 */
	@Column(precision = 12,scale = 4)
	private BigDecimal			cost				= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 备注 */
	private String				remark;

	public Stock() {
	}


	@Override
	public String toString() {
		return "Stock [serialNumber=" + serialNumber + ", getId()=" + getId()
				+ "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result
				+ ((material == null) ? 0 : material.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result
				+ ((serialNumber == null) ? 0 : serialNumber.hashCode());
		result = prime * result
				+ ((stockDetails == null) ? 0 : stockDetails.hashCode());
		result = prime * result
				+ ((totalAmount == null) ? 0 : totalAmount.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (material == null) {
			if (other.material != null)
				return false;
		} else if (!material.equals(other.material))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (serialNumber == null) {
			if (other.serialNumber != null)
				return false;
		} else if (!serialNumber.equals(other.serialNumber))
			return false;
		if (stockDetails == null) {
			if (other.stockDetails != null)
				return false;
		} else if (!stockDetails.equals(other.stockDetails))
			return false;
		if (totalAmount == null) {
			if (other.totalAmount != null)
				return false;
		} else if (!totalAmount.equals(other.totalAmount))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		return true;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public void setStockDetails(List<StockDetail> stockDetails) {
		this.stockDetails = stockDetails;
	}

}
