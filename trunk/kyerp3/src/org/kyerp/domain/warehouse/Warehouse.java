package org.kyerp.domain.warehouse;

import java.io.Serializable;

import javax.persistence.Entity;

import org.kyerp.domain.BaseDomain;

/**
 * 仓库信息表
 * 
 * @author y109 2009-12-10下午08:02:27
 */
@Entity
public class Warehouse extends BaseDomain implements Serializable {
	private static final long	serialVersionUID	= 1L;
	/** 仓库编号 */
	private String				serialNumber;
	/** 仓库名称 */
	private String				name;

	public Warehouse() {
	}

	public String getName() {
		return name;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

}
