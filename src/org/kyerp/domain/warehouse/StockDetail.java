package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.kyerp.domain.BaseDomain;

/**
 * 物料库存明细表
 * 
 * @author y109 2010-3-19下午06:40:24
 */
@Entity
public class StockDetail extends BaseDomain implements Serializable{

	private static final long	serialVersionUID	= 1L;
	/** 库存表 */
	@ManyToOne
	private Stock				stock;
	/** 物料批次号 **/
	private String				batchNumber;
	/** 仓库 */
	@ManyToOne
	private Warehouse			warehouse;
	/** 数量 */
	@Column(precision = 12,scale = 4)
	private BigDecimal			amount				= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 单位 */
	@ManyToOne
	private Unit				unit;
	/** 价格 */
	@Column(precision = 12,scale = 4)
	private BigDecimal			price				= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 金额 */
	@Column(precision = 12,scale = 4)
	private BigDecimal			cost				= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 备注 */
	private String				remark;

	public StockDetail() {
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
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

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

}
