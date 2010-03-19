package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.kyerp.domain.BaseDomain;

/**
 * 入库单明细
 * 
 * @author y109 2010-3-19下午06:49:15
 */
@Entity
public class InStockDetail extends BaseDomain implements Serializable {

	private static final long	serialVersionUID	= 1L;

	/** 入库单 */
	@ManyToOne
	private InStock				inStock;
	/** 采购申请单明细 */
	@ManyToOne
	private PurchaseOrderDetail	purchaseOrderDetail;
	/** 物料 */
	@ManyToOne
	private Material			material;
	/** 仓库 */
	@ManyToOne
	private Warehouse			warehouse;
	/** 数量 */
	private float				amount;
	/** 单位 */
	@ManyToOne
	private Unit				unit;
	/** 价格 */
	private BigDecimal			price;
	/** 备注 */
	private String				remark;

	public InStockDetail() {
	}

	public InStock getInStock() {
		return inStock;
	}

	public void setInStock(InStock inStock) {
		this.inStock = inStock;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public PurchaseOrderDetail getPurchaseOrderDetail() {
		return purchaseOrderDetail;
	}

	public void setPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) {
		this.purchaseOrderDetail = purchaseOrderDetail;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
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

	/**
	 * @return
	 */
	public BigDecimal getBillCost() {
		return price.multiply(new BigDecimal(amount));
	}
}
