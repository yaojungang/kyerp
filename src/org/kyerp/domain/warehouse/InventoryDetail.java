package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;

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
	/** 物料 */
	@ManyToOne
	private Material			material;
	/** 物料批次号 */
	private String				batchNumber;
	/** 仓库 */
	@ManyToOne
	private Warehouse			warehouse;
	/** 入库数量 */
	@Column(precision = 12,scale = 4)
	private BigDecimal			inStockCount		= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 出库数量 */
	@Column(precision = 12,scale = 4)
	private BigDecimal			outStockCount		= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 单位 */
	@ManyToOne
	private Unit				unit;
	/** 价格 */
	@Column(precision = 12,scale = 4)
	private BigDecimal			price				= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 备注 */
	private String				remark;

	public InventoryDetail() {
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
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

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getInStockCount() {
		return inStockCount;
	}

	public void setInStockCount(BigDecimal inStockCount) {
		this.inStockCount = inStockCount;
	}

	public BigDecimal getOutStockCount() {
		return outStockCount;
	}

	public void setOutStockCount(BigDecimal outStockCount) {
		this.outStockCount = outStockCount;
	}

}
