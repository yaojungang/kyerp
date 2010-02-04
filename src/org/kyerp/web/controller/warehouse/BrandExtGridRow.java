package org.kyerp.web.controller.warehouse;

/**
 * @author y109 2010-2-2下午03:50:46
 */
public class BrandExtGridRow {
	private Long	id;
	private String	name;
	private String	nameSpell;
	private String	visable;

	BrandExtGridRow() {
	}

	public String getNameSpell() {
		return nameSpell;
	}

	public void setNameSpell(String nameSpell) {
		this.nameSpell = nameSpell;
	}

	public Long getId() {
		return id;
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

	public String getVisable() {
		return visable;
	}

	public void setVisable(String visable) {
		this.visable = visable;
	}

}
