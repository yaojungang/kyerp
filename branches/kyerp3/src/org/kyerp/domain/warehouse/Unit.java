package org.kyerp.domain.warehouse;

import java.io.Serializable;

import javax.persistence.Entity;

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
}
