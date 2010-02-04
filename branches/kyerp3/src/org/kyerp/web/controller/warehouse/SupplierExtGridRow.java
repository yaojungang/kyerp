package org.kyerp.web.controller.warehouse;

/**
 * @author y109 2010-2-2下午04:00:23
 */
public class SupplierExtGridRow {
	private Long	id;
	/** 供应商简称 **/
	private String	name;
	/** 供应商简拼 **/
	private String	nameSpell;
	/** 供应商全称 **/
	private String	fullName;
	/** 是否列入合格供方名录 **/
	private Boolean	qualified	= true;

	SupplierExtGridRow() {
	}

	public Long getId() {
		return id;
	}

	public Boolean getQualified() {
		return qualified;
	}

	public void setQualified(Boolean qualified) {
		this.qualified = qualified;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
