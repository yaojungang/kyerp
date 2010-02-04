package org.kyerp.web.controller.warehouse;

/**
 * @author y109 2010-2-2下午04:52:45
 */
public class WarehouseExtGridRow {
	private Long	id;
	/** 仓库编号 */
	private String	serialNumber;
	/** 仓库名称 */
	private String	name;

	WarehouseExtGridRow() {
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

}
