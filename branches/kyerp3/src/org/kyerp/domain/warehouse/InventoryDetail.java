package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.kyerp.domain.BaseDomain;

/**
 * 库存表明细
 *org.kyerp.domain.warehouse.InventoryDetail.java
 * 
 * @author y109
 *         2010-4-19上午09:46:01
 */
@Entity
public class InventoryDetail extends BaseDomain implements Serializable{
	private static final long	serialVersionUID	= 1L;
	/**
	 * 所有者
	 */
	@ManyToOne
	private InventoryOwner owner;
	/** 物料 */
	@ManyToOne
	private Material			material;
	/** 物料批次号 */
	private String				batchNumber;
	/** 仓库 */
	@ManyToOne
	private Warehouse			warehouse;
	/** 入库数量 */
	@Column(precision = 12,scale = 4,nullable = false)
	private BigDecimal			begingStockCount		= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	
	/** 入库数量 */
	@Column(precision = 12,scale = 4,nullable = false)
	private BigDecimal			inStockCount		= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 出库数量 */
	@Column(precision = 12,scale = 4,nullable = false)
	private BigDecimal			outStockCount		= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 当前数量 */
	@Column(precision = 12,scale = 4,nullable = false)
	private BigDecimal			currentStockCount		= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 单位 */
	@ManyToOne
	private Unit				unit;
	/** 价格 */
	@Column(precision = 12,scale = 4)
	private BigDecimal			price				= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 备注 */
	private String				remark;
	
	public StockDetail caculateStockDetail(StockDetail stockDetail,InventoryDetail inventoryDetail) throws Exception {
		throw new Exception("必须实现caculateStockDetail 方法！");
	}
	/**
	 * 发生日期
	 */
	private Date happenDate;
	public InventoryDetail() {
	}
	//获取单据日期
	public Date getInputDate(){
		return super.getUpdateTime();
		};

	@Override
	public String toString() {
		return "InventoryDetail [owner=" + owner + ", batchNumber="
				+ batchNumber + ", inStockCount=" + inStockCount
				+ ", material=" + material + ", outStockCount=" + outStockCount
				+ ", getId()=" + getId() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((batchNumber == null) ? 0 : batchNumber.hashCode());
		result = prime
				* result
				+ ((begingStockCount == null) ? 0 : begingStockCount.hashCode());
		result = prime
				* result
				+ ((currentStockCount == null) ? 0 : currentStockCount
						.hashCode());
		result = prime * result
				+ ((inStockCount == null) ? 0 : inStockCount.hashCode());
		result = prime * result
				+ ((material == null) ? 0 : material.hashCode());
		result = prime * result
				+ ((outStockCount == null) ? 0 : outStockCount.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		result = prime * result
				+ ((warehouse == null) ? 0 : warehouse.hashCode());
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
		InventoryDetail other = (InventoryDetail) obj;
		if (batchNumber == null) {
			if (other.batchNumber != null)
				return false;
		} else if (!batchNumber.equals(other.batchNumber))
			return false;
		if (begingStockCount == null) {
			if (other.begingStockCount != null)
				return false;
		} else if (!begingStockCount.equals(other.begingStockCount))
			return false;
		if (currentStockCount == null) {
			if (other.currentStockCount != null)
				return false;
		} else if (!currentStockCount.equals(other.currentStockCount))
			return false;
		if (inStockCount == null) {
			if (other.inStockCount != null)
				return false;
		} else if (!inStockCount.equals(other.inStockCount))
			return false;
		if (material == null) {
			if (other.material != null)
				return false;
		} else if (!material.equals(other.material))
			return false;
		if (outStockCount == null) {
			if (other.outStockCount != null)
				return false;
		} else if (!outStockCount.equals(other.outStockCount))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
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
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		if (warehouse == null) {
			if (other.warehouse != null)
				return false;
		} else if (!warehouse.equals(other.warehouse))
			return false;
		return true;
	}

	public BigDecimal getCost() {

		return this.price.multiply(this.inStockCount.subtract(this.outStockCount));
	}

	public Date getHappenDate() {
		return happenDate;
	}
	public void setHappenDate(Date happenDate) {
		this.happenDate = happenDate;
	}
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public BigDecimal getBegingStockCount() {
		return begingStockCount;
	}

	public void setBegingStockCount(BigDecimal begingStockCount) {
		this.begingStockCount = begingStockCount;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
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

	public BigDecimal getCurrentStockCount() {
		return currentStockCount;
	}

	public void setCurrentStockCount(BigDecimal currentStockCount) {
		this.currentStockCount = currentStockCount;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getInStockCount() throws Exception {
		return inStockCount;
	}

	public void setInStockCount(BigDecimal inStockCount) {
		this.inStockCount = inStockCount;
	}

	public BigDecimal getOutStockCount() throws Exception {
		return outStockCount;
	}

	public void setOutStockCount(BigDecimal outStockCount) {
		this.outStockCount = outStockCount;
	}

	public InventoryOwner getOwner() {
		return owner;
	}

	public void setOwner(InventoryOwner owner) {
		this.owner = owner;
	}

}
