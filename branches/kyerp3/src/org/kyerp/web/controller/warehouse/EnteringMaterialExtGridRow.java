package org.kyerp.web.controller.warehouse;

import java.util.Date;

/**
 * @author y109 2010-2-4下午03:44:16
 */
public class EnteringMaterialExtGridRow {
	private Long	id;
	/** 入库单号 */
	private String	serialNumber;
	/** 入库时间 */
	private Date	enteringTime;
	/** 仓库名称 */
	private Long	warehouseId;
	private String	warehouseName;
	/** 库管员 */
	private Long	keeperId;
	private String	keeperName;
	/** 操作员 */
	private Long	operatorId;
	private String	operatorName;
	/** 物料明细 **/
	private String	batchIds;

	EnteringMaterialExtGridRow() {
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

	public Date getEnteringTime() {
		return enteringTime;
	}

	public void setEnteringTime(Date enteringTime) {
		this.enteringTime = enteringTime;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public Long getKeeperId() {
		return keeperId;
	}

	public void setKeeperId(Long keeperId) {
		this.keeperId = keeperId;
	}

	public String getKeeperName() {
		return keeperName;
	}

	public void setKeeperName(String keeperName) {
		this.keeperName = keeperName;
	}

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getBatchIds() {
		return batchIds;
	}

	public void setBatchIds(String batchIds) {
		this.batchIds = batchIds;
	}

}
