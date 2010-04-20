package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * 入库单
 * 
 * @author y109 2010-3-19下午06:49:15
 */
@Entity
public class InStock extends Inventory implements Serializable{

	private static final long	serialVersionUID	= 1L;
	/** 申请单 */
	@ManyToOne
	private PurchaseOrder		purchaseOrder;
	/** 入库日期 */
	private Date				arriveDate;
	/** 供应商 */
	@ManyToOne
	private Supplier			supplier;
	/** 明细 **/
	@OneToMany(mappedBy = "inStock",cascade = { CascadeType.ALL })
	private List<InStockDetail>	details				= new ArrayList<InStockDetail>();

// /** 单号 */
// private String serialNumber;
// /** 收发类型 */
// @ManyToOne
// private InOutType inOutType;
// /** 供应商 */
// @ManyToOne
// private Supplier supplier;
// /** 备注 */
// private String remark;
// /** 总数量 */
// @Column(precision = 12,scale = 4)
// private BigDecimal billCount = new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
// /** 总费用 */
// @Column(precision = 12,scale = 4)
// private BigDecimal billCost = new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
// /** 填单人 */
// @ManyToOne
// private User writeUser;
// @ManyToOne
// private Employee writeEmployee;
// /** 审核人 */
// @ManyToOne
// private User checkUser;
// @ManyToOne
// private Employee checkEmployee;
// /** 经办人 */
// @ManyToOne
// private Employee keeper;
// /** 填写时间 */
// private Date writeDate;
// /** 审核时间 */
// private Date checkDate;
// /** 单据状态 */
// private BillStatus status;
	public InStock() {
	}

	@Override
	public void prePersist() {
		super.prePersist();
		this.preUpdate();
	}

	@Override
	public void preUpdate() {
		this.setBillCount(new BigDecimal("0"));
		this.setBillCost(new BigDecimal("0"));
		for (InStockDetail detail : this.getDetails()) {
			this.setBillCount(this.getBillCount().add(detail.getInStockCount()));
			this.setBillCost(this.getBillCost().add(detail.getBillCost()));
		}
		super.preUpdate();
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public Date getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}

	public List<InStockDetail> getDetails() {
		return details;
	}

	public void setDetails(List<InStockDetail> details) {
		this.details = details;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}
