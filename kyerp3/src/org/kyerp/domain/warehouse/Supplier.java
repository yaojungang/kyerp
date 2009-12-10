package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.kyerp.domain.BaseDomain;

/**
 * 供应商
 * 
 * @author y109 2009-11-29下午11:31:35
 */
@Entity
public class Supplier extends BaseDomain implements Serializable {
	private static final long	serialVersionUID	= 1L;
	/** 供应商简称 **/
	private String				name;
	/** 供应商简拼 **/
	private String				nameSpell;
	/** 供应商全称 **/
	private String				fullName;
	/** 是否可见 **/
	private Boolean				visible				= true;
	/** 是否列入合格供方名录 **/
	private Boolean				qualified			= false;
	@ManyToMany
	private List<Brand>			brands				= new ArrayList<Brand>();
	@ManyToMany
	private List<Material>		materials			= new ArrayList<Material>();
	/** logo图片路径 如:/images/brand/2008/12/12/ooo.gif" **/
	private String				logopath;

	public Supplier() {
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		return builder.toString();
	}

	public Boolean getQualified() {
		return qualified;
	}

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	public void setQualified(Boolean qualified) {
		this.qualified = qualified;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

	public String getNameSpell() {
		return nameSpell;
	}

	public void setNameSpell(String nameSpell) {
		this.nameSpell = nameSpell;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getName() {
		return name;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public void setName(String name) {
		this.name = name;
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
