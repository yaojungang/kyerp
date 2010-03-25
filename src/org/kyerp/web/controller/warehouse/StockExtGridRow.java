package org.kyerp.web.controller.warehouse;

import java.math.BigDecimal;

/**
 * @author y109 2010-3-19下午07:51:07
 */
public class StockExtGridRow{
	private Long		id;
	/** 建立时间 */
	private String		createTime;
	/** 修改时间 */
	private String		updateTime;
	/** 物料 */
	private Long		materialId;
	private String		materialName;
	/** 库存物料全称 */
	private String		stockDetailName;
	/** 数量 */
	private BigDecimal	totalAmount;
	/** 单位 */
	private Long		unitId;
	private String		unitName;
	/** 价格 */
	private BigDecimal	price;
	/** 金额 */
	private BigDecimal	cost;
	/** 明细表 */
	private String		details;

	public StockExtGridRow() {
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

	public Long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getStockDetailName() {
		return stockDetailName;
	}

	public void setStockDetailName(String stockDetailName) {
		this.stockDetailName = stockDetailName;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
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

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
