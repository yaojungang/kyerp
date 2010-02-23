package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.kyerp.domain.BaseDomain;

/**
 * 物料批次
 * 
 * @author y109 2009-12-14上午11:53:51
 */
@Entity
public class MaterialBatch extends BaseDomain implements Serializable {
	private static final long	serialVersionUID	= 1L;
	/** 批次号 */
	private String				batchNumber;
	/** 物料 **/
	@ManyToOne
	private Material			material;
	/** 入库单 */
	@ManyToOne
	private EnteringMaterial	enteringMaterial;
	/** 供应商 */
	@ManyToOne
	private Supplier			supplier;
	/* 仓库 */
	@ManyToOne
	private Warehouse			warehouse;
	/** 单位 */
	@ManyToOne
	private Unit				unit;
	/** 数量 */
	private Float				amount;
	/** 单价 */
	private BigDecimal			price;
	/** 金额 */
	private BigDecimal			money;
	/** 备注 */
	private String				remark;

	public MaterialBatch() {
	}

	public MaterialBatch(String batchNumber) {
		super();
		this.batchNumber = batchNumber;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public Material getMaterial() {
		return material;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Float getAmount() {
		return amount;
	}

	public EnteringMaterial getEnteringMaterial() {
		return enteringMaterial;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setEnteringMaterial(EnteringMaterial enteringMaterial) {
		this.enteringMaterial = enteringMaterial;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
