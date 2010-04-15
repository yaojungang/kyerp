package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.kyerp.domain.BaseDomain;

/**
 * 出库单明细
 * 
 * @author y109 2010-3-19下午06:49:15
 */
@Entity
public class OutStockDetail extends BaseDomain implements Serializable{

	private static final long	serialVersionUID	= 1L;

	/** 出库单 */
	@ManyToOne
	private OutStock			outStock;

	/** 物料 */
	@ManyToOne
	private Material			material;
	/** 物料批次号 */
	private String				batchNumber;
	/** 仓库 */
	@ManyToOne
	private Warehouse			warehouse;
	/** 数量 */
	@Column(precision = 12,scale = 4)
	private BigDecimal			billCount			= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 单位 */
	@ManyToOne
	private Unit				unit;
	/** 价格 */
	@Column(precision = 12,scale = 4)
	private BigDecimal			price				= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 生产任务单号 */
	private String				pressworkNo;
	/** 备注 */
	private String				remark;

	public OutStockDetail() {
	}

	/**
	 * 明细保存时同时更新出库单的总数量及总入库单金额
	 */
	@Override
	public void postPersist() {
		super.postPersist();
		updatePurchase();
	}

	/**
	 * 明细更新时同时更新出库单的总数量及总入库单金额
	 */
	@Override
	public void preUpdate() {
		super.preUpdate();
		updatePurchase();
	}

	/**
	 * 明细删除时同时更新入库单的总数量及部入库单金额
	 */
	@Override
	public void preDestory() {
		super.preDestory();
		updatePurchase();
	}

	private void updatePurchase() {
		OutStock bill = this.getOutStock();
		bill.setBillCount(new BigDecimal("0"));
		bill.setBillCost(new BigDecimal("0"));
		for (OutStockDetail detail : bill.getDetails()) {
			bill.setBillCount(bill.getBillCount().add(detail.getBillCount()));
			bill.setBillCost(bill.getBillCost().add(detail.getBillCost()));
		}
	}

	/**
	 * 获取金额
	 * 
	 * @return
	 */
	public BigDecimal getBillCost() {
		return price.multiply(billCount);
	}

	public OutStock getOutStock() {
		return outStock;
	}

	public void setOutStock(OutStock outStock) {
		this.outStock = outStock;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public BigDecimal getBillCount() {
		return billCount;
	}

	public void setBillCount(BigDecimal billCount) {
		this.billCount = billCount;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public String getPressworkNo() {
		return pressworkNo;
	}

	public void setPressworkNo(String pressworkNo) {
		this.pressworkNo = pressworkNo;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
