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
	/** 总数量 */
	private Integer				billCount;
	/** 收货数量 */
	private Integer				recvCount;
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
	 * 商品明细更新时同时更新订单的总数量及总订单金额
	 */

	@Override
	public void preUpdate() {
		updatePurchase();
	}

	/**
	 * 商品明细删除时同时更新订单的总数量及部订单金额
	 */
	@Override
	public void preDestory() {
		updatePurchase();
	}

	private void updatePurchase() {
		PurchaseOrder bill = this.getPurchaseOrder();
		bill.setBillCount(0);
		bill.setBillCost(new BigDecimal("0"));
		for (PurchaseOrderDetail detail : bill.getDetails()) {
			bill.setBillCount(bill.getBillCount() + detail.getBillCount());
			bill.setBillCost(bill.getBillCost().add(detail.getBillCost()));
		}
	}

	/**
	 * 订单的采购金额
	 */
	public BigDecimal getBillCost() {
		return price.multiply(new BigDecimal(billCount));
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
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

	public Integer getBillCount() {
		return billCount;
	}

	public void setBillCount(Integer billCount) {
		this.billCount = billCount;
	}

	public Integer getRecvCount() {
		return recvCount;
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

}
