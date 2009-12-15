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
	/** 物料ID */
	private long				materialId;
	/** 入库单 */
	private EnteringMaterial	enteringMaterial;
	/** 供应商 */
	@ManyToOne
	private Supplier			supplier;
	/** 单位 */
	private String				module;
	/** 数量 */
	private Float				amount;
	/** 单价 */
	private BigDecimal			price;
	/** 备注 */
	private String				remark;

	public MaterialBatch() {
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

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(long materialId) {
		this.materialId = materialId;
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
