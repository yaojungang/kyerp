package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.kyerp.domain.BaseDomain;

/**
 * 库存材料档案
 * 
 * @author y109 2009-11-29下午11:24:25
 */
@Entity
/**继承映射策略*/
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
/**标识字段定义*/
@DiscriminatorColumn(name = "material_type", discriminatorType = DiscriminatorType.STRING)
/**该类的标识*/
@DiscriminatorValue("material")
public class Material extends BaseDomain implements Serializable {
	private static final long	serialVersionUID	= 1L;
	/** 编号 **/
	private String				serialNumber;
	/** 名称 **/
	private String				name;
	/** 规格 **/
	private String				specification;
	/** 材料类别 */
	@ManyToOne
	private MaterialCategory	materialCategory;
	/** 材料品牌 */
	@ManyToOne
	private Brand				brand;
	/** 材料单位 */
	private String				module;
	/** 材料数量 */
	private Float				amount;
	/** 单价 */
	private BigDecimal			price;

	public Material() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Float getAmount() {
		return amount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public MaterialCategory getMaterialCategory() {
		return materialCategory;
	}

	public void setMaterialCategory(MaterialCategory materialCategory) {
		this.materialCategory = materialCategory;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

}
