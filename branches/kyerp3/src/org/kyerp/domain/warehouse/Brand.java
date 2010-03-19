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
 * 品牌
 * 
 * @author y109 2009-11-29下午11:31:35
 */
@Entity
public class Brand extends BaseDomain implements Serializable {
	private static final long	serialVersionUID	= 1L;
	/** 名称 **/
	private String				name;
	/** 编号 **/
	private String				serialNumber;
	/** 简拼 **/
	private String				nameSpell;
	/** 摘要 **/
	private String				note;
	/** 子类别 **/
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE }, mappedBy = "parentBrand")
	private final List<Brand>	childBrands			= new ArrayList<Brand>();
	/** 所属父类 **/
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Brand				parentBrand;
	/** 是否可见 **/
	private Boolean				visible				= true;
	/** 相关物料 */
	@OneToMany(mappedBy = "brand")
	private List<Material>		materials			= new ArrayList<Material>();
	/** logo图片路径 如:/images/brand/2008/12/12/ooo.gif" **/
	private String				logopath;

	public Brand() {
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		return builder.toString();
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Brand getParentBrand() {
		return parentBrand;
	}

	public void setParentBrand(Brand parentBrand) {
		this.parentBrand = parentBrand;
	}

	public List<Brand> getChildBrands() {
		return childBrands;
	}

	public String getName() {
		return name;
	}

	public String getNameSpell() {
		return nameSpell;
	}

	public void setNameSpell(String nameSpell) {
		this.nameSpell = nameSpell;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getLogopath() {
		return logopath;
	}

	public void setLogopath(String logopath) {
		this.logopath = logopath;
	}
}
