package org.kyerp.domain.warehouse.model;

import java.math.BigDecimal;

import org.kyerp.domain.warehouse.Material;
import org.kyerp.domain.warehouse.Supplier;

/**
 * 入库项目
 * 
 * @author y109 2009-12-11下午02:23:01
 */
public class EnteringMaterialItem {
	/** 供应商 */
	private Supplier	supplier;
	/** 材料 **/
	private Material	material;
	/** 单位 */
	private String		module;
	/** 单价 */
	private BigDecimal	price;
	/** 数量 */
	private BigDecimal	amount;

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
