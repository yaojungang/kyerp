package org.kyerp.web.controller.warehouse;

import java.math.BigDecimal;

/**
 * @author y109 2010-3-2下午09:47:20
 */
public class PurchaseOrderItemExtGridRow {
	private Long		id;
	/** 建立时间 */
	private String		createTime;
	/** 修改时间 */
	private String		updateTime;
	/** 采购申请单 */
	private long		purchaseOrderId;
	private String		purchaseOrderSerialNumber;
	/** 物料 */
	private long		materialId;
	private String		materialName;
	/** 单位 */
	private long		unitId;
	private String		unitName;
	/** 总数量 */
	private float		billCount;
	/** 收货数量 */
	private float		recvCount;
	/** 价格 */
	private BigDecimal	price;
	/** 备注 */
	private String		remark;
	/** 金额 */
	private BigDecimal	billCost;

	public PurchaseOrderItemExtGridRow() {
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

	public long getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public String getPurchaseOrderSerialNumber() {
		return purchaseOrderSerialNumber;
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

	public void setPurchaseOrderSerialNumber(String purchaseOrderSerialNumber) {
		this.purchaseOrderSerialNumber = purchaseOrderSerialNumber;
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

	public void setRecvCount(Integer recvCount) {
		this.recvCount = recvCount;
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

	public float getBillCount() {
		return billCount;
	}

	public void setBillCount(float billCount) {
		this.billCount = billCount;
	}

	public float getRecvCount() {
		return recvCount;
	}

	public void setRecvCount(float recvCount) {
		this.recvCount = recvCount;
	}
}
