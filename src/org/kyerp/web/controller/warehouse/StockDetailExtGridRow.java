package org.kyerp.web.controller.warehouse;

import java.math.BigDecimal;

/**
 * @author y109 2010-3-2下午09:47:20
 */
public class StockDetailExtGridRow{
	private Long		id;
	/** 建立时间 */
	private String		createTime;
	/** 修改时间 */
	private String		updateTime;
	/** 库存单 */
	private Long		stockId;
	private String		stockSerialNumber;
	/** 物料 */
	private Long		materialId;
	private String		materialName;
	/** 库存物料全称 */
	private String		stockDetailName;
	/** 仓库 */
	private Long		warehouseId;
	private String		warehouseName;
	/** 批次号 */
	private String		batchNumber;
	/** 单位 */
	private long		unitId;
	private String		unitName;
	/** 总数量 */
	private BigDecimal	amount;
	/** 价格 */
	private BigDecimal	price;
	/** 金额 */
	private BigDecimal	cost;

	public StockDetailExtGridRow() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStockDetailName() {
		return stockDetailName;
	}

	public void setStockDetailName(String stockDetailName) {
		this.stockDetailName = stockDetailName;
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

	public long getStockId() {
		return stockId;
	}

	public void setStockId(long stockId) {
		this.stockId = stockId;
	}

	public String getStockSerialNumber() {
		return stockSerialNumber;
	}

	public void setStockSerialNumber(String stockSerialNumber) {
		this.stockSerialNumber = stockSerialNumber;
	}

	public long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(long materialId) {
		this.materialId = materialId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public long getUnitId() {
		return unitId;
	}

	public void setUnitId(long unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

}
