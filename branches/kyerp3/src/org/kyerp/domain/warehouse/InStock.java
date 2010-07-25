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

	@Override
	public String toString() {
		return "InStock [getId()=" + getId() + ", getBillCount()="
				+ getBillCount() + ", getBillCost()=" + getBillCost() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((arriveDate == null) ? 0 : arriveDate.hashCode());
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result
				+ ((purchaseOrder == null) ? 0 : purchaseOrder.hashCode());
		result = prime * result
				+ ((supplier == null) ? 0 : supplier.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		InStock other = (InStock) obj;
		if (arriveDate == null) {
			if (other.arriveDate != null)
				return false;
		} else if (!arriveDate.equals(other.arriveDate))
			return false;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (purchaseOrder == null) {
			if (other.purchaseOrder != null)
				return false;
		} else if (!purchaseOrder.equals(other.purchaseOrder))
			return false;
		if (supplier == null) {
			if (other.supplier != null)
				return false;
		} else if (!supplier.equals(other.supplier))
			return false;
		return true;
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
