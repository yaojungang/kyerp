package org.kyerp.web.controller.warehouse;

import java.math.BigDecimal;

/**
 * @author y109 2010-3-2下午09:47:20
 */
public class InStockItemExtGridRow {
	private Long		id;
	/** 建立时间 */
	private String		createTime;
	/** 修改时间 */
	private String		updateTime;
	/** 采购申请单 */
	private long		inStockId;
	private String		inStockSerialNumber;
	/** 物料 */
	private long		materialId;
	private String		materialName;
	/** 单位 */
	private long		unitId;
	private String		unitName;
	/** 总数量 */
	private Integer		billCount;
	/** 数量 */
	private Float		amount;
	/** 价格 */
	private BigDecimal	price;
	/** 备注 */
	private String		remark;
	/** 金额 */
	private BigDecimal	billCost;

	public InStockItemExtGridRow() {
	}

	public BigDecimal getBillCost() {
		return billCost;
	}

	public void setBillCost(BigDecimal billCost) {
		this.billCost = billCost;
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

	public long getInStockId() {
		return inStockId;
	}

	public void setInStockId(long inStockId) {
		this.inStockId = inStockId;
	}

	public String getInStockSerialNumber() {
		return inStockSerialNumber;
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

	public void setInStockSerialNumber(String inStockSerialNumber) {
		this.inStockSerialNumber = inStockSerialNumber;
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

	public Integer getBillCount() {
		return billCount;
	}

	public void setBillCount(Integer billCount) {
		this.billCount = billCount;
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

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

}
