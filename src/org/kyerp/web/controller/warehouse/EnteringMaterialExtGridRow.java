package org.kyerp.web.controller.warehouse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kyerp.domain.warehouse.MaterialBatch;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * @author y109 2010-2-4下午03:44:16
 */
public class EnteringMaterialExtGridRow {
	private Long				id;
	/** 入库单号 */
	private String				serialNumber;
	/** 入库时间 */
	@DateTimeFormat(iso = ISO.DATE)
	private Date				enteringTime;
	/** 供应商名称 */
	private Long				supplierId;
	private String				supplierName;
	/** 库管员 */
	private Long				keeperId;
	private String				keeperName;
	/** 操作员 */
	private Long				operatorId;
	private String				operatorName;
	/** 物料明细 **/
	// 用于传递json序列
	private String				opeItems;
	private String				batchIds;
	private String				batchNames;
	private List<MaterialBatch>	batchs;

	private List<MaterialBatch>	itemList	= new ArrayList<MaterialBatch>();

	EnteringMaterialExtGridRow() {
	}

	public String getOpeItems() {
		return opeItems;
	}

	public void setOpeItems(String opeItems) {
		this.opeItems = opeItems;
	}

	public String getBatchNames() {
		return batchNames;
	}

	public void setBatchNames(String batchNames) {
		this.batchNames = batchNames;
	}

	public List<MaterialBatch> getBatchs() {
		return batchs;
	}

	public void setBatchs(List<MaterialBatch> batchs) {
		this.batchs = batchs;
	}

	public List<MaterialBatch> getItemList() {
		return itemList;
	}

	public void setItemList(List<MaterialBatch> itemList) {
		this.itemList = itemList;
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

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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
