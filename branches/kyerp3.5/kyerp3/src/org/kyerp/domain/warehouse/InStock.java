package org.kyerp.domain.warehouse;

import java.io.Serializable;
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

	public InStock() {
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
