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
 * 供应商类别
 * 
 * @author y109 2010-3-9下午09:00:33
 */
@Entity
public class SupplierType extends BaseDomain implements Serializable {
	private static final long	serialVersionUID	= 1L;
	/** 类别名称 **/
	private String				name;
	/** 编号 **/
	private String				serialNumber;
	/** 摘要 **/
	private String				note;
	/** 子类别 **/
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE }, mappedBy = "parentSupplierType")
	private List<SupplierType>	childSupplierTypes	= new ArrayList<SupplierType>();
	/** 所属父类 **/
	@ManyToOne(cascade = CascadeType.REFRESH)
	private SupplierType		parentSupplierType;

	public SupplierType() {
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<SupplierType> getChildSupplierTypes() {
		return childSupplierTypes;
	}

	public void setChildSupplierTypes(List<SupplierType> childSupplierTypes) {
		this.childSupplierTypes = childSupplierTypes;
	}

	public SupplierType getParentSupplierType() {
		return parentSupplierType;
	}

	public void setParentSupplierType(SupplierType parentSupplierType) {
		this.parentSupplierType = parentSupplierType;
	}

}
