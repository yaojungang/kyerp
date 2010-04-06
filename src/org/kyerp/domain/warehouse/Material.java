package org.kyerp.domain.warehouse;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * 库存原材料档案
 * 
 * @author y109 2009-11-29下午11:24:25
 */
@Entity
/**该类的标识*/
@DiscriminatorValue("material")
/**继承映射策略*/
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Material extends BaseItem implements Serializable{
	private static final long	serialVersionUID	= 1L;
	/** 编号 **/
	// private String serialNumber = "";
	/** 物料全称(名称+规格) **/
	// private String name = "";
	/** 物料名称 */
	private String				materialName;
	/** 规格 **/
	private String				specification;
	/** 类别 */
	@ManyToOne
	private MaterialCategory	materialCategory;
	/** 品牌 */
	@ManyToOne
	private Brand				brand;
	/** 单位 */
// @ManyToOne
// private Unit unit;
	/** 材料数量 */
	// private BigDecimal amount = new BigDecimal("0");
	/** 单价 */
	// private BigDecimal price = new BigDecimal("0");
	/** 默认仓库 */
// @ManyToOne
// private Warehouse warehouse;
	/** 默认供应商 */
// @ManyToOne
// private Supplier supplier;
	/** 隐藏 */
	private Boolean				visible				= true;

	public Material() {
	}

	/** 在对象新建前设置物料全称 */
	@Override
	@PrePersist
	public void prePersist() {
		this.setFullName();
		super.prePersist();
	}

	/** 在对象更新前设置物料全称 */
	@Override
	@PreUpdate
	public void preUpdate() {
		this.setFullName();
		super.preUpdate();
	}

	// 设置物料全称
	public void setFullName() {
		logger.debug("设置物料全称为:" + this.getMaterialName() + "(" + this.getSpecification() + ")");
		this.setName(this.getMaterialName() + "(" + this.getSpecification() + ")");
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
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

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

}
