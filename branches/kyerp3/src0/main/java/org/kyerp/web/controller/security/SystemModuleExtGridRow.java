package org.kyerp.web.controller.security;

/**
 * @author y109 2010-2-4下午09:56:29
 */
public class SystemModuleExtGridRow {
	private Long	id;
	/** 英文名称 */
	private String	name;
	/** 中文名称 */
	private String	chineseName;
	/** 英文简称 */
	private String	shortName;

	SystemModuleExtGridRow() {
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

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

}
