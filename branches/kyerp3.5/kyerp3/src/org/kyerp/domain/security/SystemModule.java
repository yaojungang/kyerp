package org.kyerp.domain.security;

import java.io.Serializable;

import javax.persistence.Entity;

import org.kyerp.domain.BaseDomain;

@Entity
public class SystemModule extends BaseDomain implements Serializable {
	/**
	 * 系统模块
	 */
	private static final long	serialVersionUID	= 5493436465149931625L;
	/** 英文名称 */
	private String				name;
	/** 中文名称 */
	private String				chineseName;
	/** 英文简称 */
	private String				shortName;

	public SystemModule() {
	}

	/**
	 * @param name
	 * @param chineseName
	 * @param shortName
	 */
	public SystemModule(String name, String chineseName, String shortName) {
		super();
		this.name = name;
		this.chineseName = chineseName;
		this.shortName = shortName;
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
