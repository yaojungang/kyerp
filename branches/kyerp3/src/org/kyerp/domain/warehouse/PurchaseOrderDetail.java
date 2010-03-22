package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.kyerp.domain.BaseDomain;

/**
 * 采购申请单明细
 * 
 * @author y109 2010-3-1上午08:33:26
 */
@Entity
public class PurchaseOrderDetail extends BaseDomain implements Serializable {
	private static final long	serialVersionUID	= 1L;
	/** 采购申请单 */
	@ManyToOne
	private PurchaseOrder		purchaseOrder;
	/** 物料 */
	@ManyToOne
	private Material			material;
	/** 数量 */
	private BigDecimal			billCount;
	/** 收货数量 */
	private BigDecimal			recvCount;
	/** 单位 */
	@ManyToOne
	private Unit				unit;
	/** 价格 */
	private BigDecimal			price;
	/** 备注 */
	private String				remark;

	public PurchaseOrderDetail() {
	}

	/**
	 * 明细保存时同时更新订单的总数量及总订单金额
	 */
	@Override
	public void postPersist() {
		super.postPersist();
		updatePurchase();
	}

	/**
	 * 明细更新时同时更新订单的总数量及总订单金额
	 */
	@Override
	public void preUpdate() {
		super.preUpdate();
		updatePurchase();
	}

	/**
	 * 明细删除时同时更新订单的总数量及部订单金额
	 */
	@Override
	public void preDestory() {
		super.preDestory();
		updatePurchase();
	}

	private void updatePurchase() {
		PurchaseOrder bill = this.getPurchaseOrder();
		bill.setBillCount(new BigDecimal("0"));
		bill.setBillCost(new BigDecimal("0"));
		for (PurchaseOrderDetail detail : bill.getDetails()) {
			bill.setBillCount(bill.getBillCount().add(detail.getBillCount()));
			bill.setBillCost(bill.getBillCost().add(detail.getBillCost()));
		}
	}

	/**
	 * 订单的采购金额
	 */
	public BigDecimal getBillCost() {
		return price.multiply(billCount);
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
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

	public void setBillCost(BigDecimal billCost) {
	}

	public BigDecimal getBillCount() {
		return billCount;
	}

	public void setBillCount(BigDecimal billCount) {
		this.billCount = billCount;
	}

	public BigDecimal getRecvCount() {
		return recvCount;
	}

	public void setRecvCount(BigDecimal recvCount) {
		this.recvCount = recvCount;
	}

}
