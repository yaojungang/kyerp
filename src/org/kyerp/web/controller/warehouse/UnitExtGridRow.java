package org.kyerp.web.controller.warehouse;

/**
 * @author y109 2010-2-3上午11:03:31
 */
public class UnitExtGridRow {
	private Long	id;
	/** 编码 */
	private String	serialNumber;
	/** 名称 **/
	private String	name;
	/** 简拼 **/
	private String	nameSpell;

	UnitExtGridRow() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
