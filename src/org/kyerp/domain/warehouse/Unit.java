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
 * 单位
 * 
 * @author y109 2010-2-3上午09:14:54
 */
@Entity
public class Unit extends BaseDomain implements Serializable {
	private static final long	serialVersionUID	= 1L;
	/** 编码 */
	private String				serialNumber;
	/** 名称 **/
	private String				name;
	/** 简拼 **/
	private String				nameSpell;
	/** 摘要 **/
	private String				note;
	/** 子类别 **/
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE }, mappedBy = "parentUnit")
	private List<Unit>			childUnits			= new ArrayList<Unit>();
	/** 所属父类 **/
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Unit				parentUnit;

	public Unit() {
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameSpell() {
		return nameSpell;
	}

	public void setNameSpell(String nameSpell) {
		this.nameSpell = nameSpell;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<Unit> getChildUnits() {
		return childUnits;
	}

	public void setChildUnits(List<Unit> childUnits) {
		this.childUnits = childUnits;
	}

	public Unit getParentUnit() {
		return parentUnit;
	}

	public void setParentUnit(Unit parentUnit) {
		this.parentUnit = parentUnit;
	}
}
