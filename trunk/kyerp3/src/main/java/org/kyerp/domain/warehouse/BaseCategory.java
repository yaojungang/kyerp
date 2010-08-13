package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.kyerp.domain.BaseDomain;

/**
 *org.kyerp.domain.warehouse.BaseCategory.java
 * 
 * @author y109
 *         2010-4-4下午09:23:40
 */
@Entity
/**继承映射策略*/
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BaseCategory extends BaseDomain implements Serializable{

	private static final long			serialVersionUID	= 1L;
	/** 类别名称 **/
	private String						name;
	/** 编号 **/
	private String						serialNumber;
	/** 子类别 **/
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE },mappedBy = "parentCategory")
	private final List<BaseCategory>	childCategories		= new ArrayList<BaseCategory>();
	/** 所属父类 **/
	@ManyToOne(cascade = CascadeType.REFRESH)
	private BaseCategory				parentCategory;
	/** 备注 **/
	private String						remark;

	public BaseCategory() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public BaseCategory getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(BaseCategory parentCategory) {
		this.parentCategory = parentCategory;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<BaseCategory> getChildCategories() {
		return childCategories;
	}
}
