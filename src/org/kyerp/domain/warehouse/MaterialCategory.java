package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.kyerp.domain.BaseDomain;

/**
 * 材料类别 category
 * 
 * @author y109 2009-11-29下午11:15:51
 */
@Entity
public class MaterialCategory extends BaseDomain implements Serializable {
	private static final long		serialVersionUID		= -3328204953749595577L;
	/** 类别名称 **/
	private String					name;
	/** 编号 **/
	private String					serialNumber;
	/** 摘要 **/
	private String					note;
	/** 是否可见 **/
	private Boolean					visible					= true;
	/** 子类别 **/
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE }, mappedBy = "parentMaterialCategory")
	private List<MaterialCategory>	childMaterialCategories	= new ArrayList<MaterialCategory>();
	/** 所属父类 **/
	@ManyToOne(cascade = CascadeType.REFRESH)
	private MaterialCategory		parentMaterialCategory;

	public MaterialCategory() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public MaterialCategory getParentMaterialCategory() {
		return parentMaterialCategory;
	}

	public void setParentMaterialCategory(
			MaterialCategory parentMaterialCategory) {
		this.parentMaterialCategory = parentMaterialCategory;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setChildMaterialCategories(
			List<MaterialCategory> childMaterialCategories) {
		this.childMaterialCategories = childMaterialCategories;
	}

	public List<MaterialCategory> getChildMaterialCategories() {
		return childMaterialCategories;
	}

}
