package org.kyerp.web.controller.warehouse;

import java.math.BigDecimal;

/**
 *org.kyerp.web.controller.warehouse.InventoryDetailExtGridRow.java
 * 
 * @author y109
 *         2010-5-17上午10:05:29
 */
public class InventoryDetailExtGridRow{
	private Long		id;
	/** 建立时间 */
	private String		createTime;
	/** 修改时间 */
	private String		updateTime;
	/** 物料 */
	private Long		materialId;
	private String		materialName;
	/** 批次号 */
	private String		batchNumber;
	// 生产任务单号
	private String		pressworkNo;
	// 收发类型
	private String		inOutType;
	/** 单据号 */
	private String		serialNumber;
	/** 仓库 */
	private Long		warehouseId;
	private String		warehouseName;
	/** 入库数量 */
	private BigDecimal	inStockCount	= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 出库数量 */
	private BigDecimal	outStockCount	= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 单位 */
	private Long		unitId;
	private String		unitName;
	/** 价格 */
	private BigDecimal	price			= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 金额 */
	private BigDecimal	cost			= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 备注 */
	private String		remark;

	public InventoryDetailExtGridRow() {
	}

	public Long getMaterialId() {
		return materialId;
	}

	public String getInOutType() {
		return inOutType;
	}

	public void setInOutType(String inOutType) {
		this.inOutType = inOutType;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
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

	public BigDecimal getInStockCount() {
		return inStockCount;
	}

	public void setInStockCount(BigDecimal inStockCount) {
		this.inStockCount = inStockCount;
	}

	public BigDecimal getOutStockCount() {
		return outStockCount;
	}

	public String getPressworkNo() {
		return pressworkNo;
	}

	public void setPressworkNo(String pressworkNo) {
		this.pressworkNo = pressworkNo;
	}

	public void setOutStockCount(BigDecimal outStockCount) {
		this.outStockCount = outStockCount;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

}
