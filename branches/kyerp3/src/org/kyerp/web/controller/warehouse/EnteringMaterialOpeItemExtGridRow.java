package org.kyerp.web.controller.warehouse;

/**
 * 入库单的入库项目
 * 
 * @author y109 2010-2-26上午08:38:33
 */
public class EnteringMaterialOpeItemExtGridRow {
	private Long	id;
	/** 批次号 */
	private String	batchNumber;
	/** 物料 **/
	private long	materialId;
	private String	materialName;
	/** 入库单 */
	/** 供应商 */
	/* 仓库 */
	private long	warehouseId;
	private String	warehouseName;
	/** 单位 */
	private long	unitId;
	private String	unitName;
	/** 数量 */
	private float	amount;
	/** 单价 */
	private float	price;
	/** 金额 */
	private float	money;
	/** 备注 */
	private String	remark;

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
