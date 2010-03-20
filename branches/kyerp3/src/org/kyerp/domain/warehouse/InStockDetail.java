package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.kyerp.domain.BaseDomain;

/**
 * 入库单明细
 * 
 * @author y109 2010-3-19下午06:49:15
 */
@Entity
public class InStockDetail extends BaseDomain implements Serializable {

	private static final long	serialVersionUID	= 1L;

	/** 入库单 */
	@ManyToOne
	private InStock				inStock;
	/** 采购申请单明细 */
	@ManyToOne
	private InStockDetail		inStockDetail;
	/** 物料 */
	@ManyToOne
	private Material			material;
	/** 物料批次号 */
	private String				batchNumber;
	/** 仓库 */
	@ManyToOne
	private Warehouse			warehouse;
	/** 数量 */
	private float				billCount;
	/** 单位 */
	@ManyToOne
	private Unit				unit;
	/** 价格 */
	private BigDecimal			price;
	/** 备注 */
	private String				remark;

	public InStockDetail() {
	}

	/**
	 * 明细保存时同时更新入库单的总数量及总入库单金额
	 */
	@Override
	public void postPersist() {
		super.postPersist();
		updatePurchase();
	}

	/**
	 * 明细更新时同时更新入库单的总数量及总入库单金额
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
		InStock bill = this.getInStock();
		bill.setBillCount(0);
		bill.setBillCost(new BigDecimal("0"));
		for (InStockDetail detail : bill.getDetails()) {
			bill.setBillCount(bill.getBillCount() + detail.getBillCount());
			bill.setBillCost(bill.getBillCost().add(detail.getBillCost()));
		}
	}

	/**
	 * 获取金额
	 * 
	 * @return
	 */
	public BigDecimal getBillCost() {
		return price.multiply(new BigDecimal(billCount));
	}

	public InStock getInStock() {
		return inStock;
	}

	public void setInStock(InStock inStock) {
		this.inStock = inStock;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public InStockDetail getInStockDetail() {
		return inStockDetail;
	}

	public void setInStockDetail(InStockDetail inStockDetail) {
		this.inStockDetail = inStockDetail;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
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

}
