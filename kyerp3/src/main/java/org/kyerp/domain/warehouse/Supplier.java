package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
	/** 供应商分类 */
	@ManyToOne
	private SupplierType		supplierType;
	/** 供应商编码 */
	private String				serialNumber;
	/** 供应商简拼 **/
	private String				nameSpell;
	/** 供应商全称 **/
	private String				fullName;
	/** 是否可见 **/
	private Boolean				visible				= true;
	/** 是否列入合格供方名录 **/
	private Boolean				qualified			= true;
	@ManyToMany
	private List<Brand>			brands				= new ArrayList<Brand>();
	@ManyToMany
	private List<Material>		materials			= new ArrayList<Material>();
	/** 备注 */
	private String				remark;
	/** 地址 */
	private String				address;
	/** 邮编 */
	private String				postcode;
	/** 电话 */
	private String				phone;
	/** 传真 */
	private String				fax;
	/** 网站 */
	private String				www;
	/** 邮箱 */
	private String				email;
	/** 助记码 */
	private String				helpCode;
	/** 应付款 */
	private BigDecimal			payCost;
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

	public SupplierType getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(SupplierType supplierType) {
		this.supplierType = supplierType;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWww() {
		return www;
	}

	public void setWww(String www) {
		this.www = www;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHelpCode() {
		return helpCode;
	}

	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}

	public BigDecimal getPayCost() {
		return payCost;
	}

	public void setPayCost(BigDecimal payCost) {
		this.payCost = payCost;
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
