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

import org.kyerp.domain.BaseDomain;
import org.kyerp.domain.security.User;

/**
 * 入库单
 * 
 * @author y109 2010-3-19下午06:49:15
 */
@Entity
public class InStock extends BaseDomain implements Serializable {

	private static final long	serialVersionUID	= 1L;
	/** 申请单 */
	@ManyToOne
	private PurchaseOrder		purchaseOrder;
	/** 单号 */
	private String				serialNumber;
	/** 供应商 */
	@ManyToOne
	private Supplier			Supplier;
	/** 备注 */
	private String				remark;
	/** 总数量 */
	private Integer				billCount;
	/** 总费用 */
	private BigDecimal			billCost;
	/** 填单人 */
	@ManyToOne
	private User				writeUser;
	/** 审核人 */
	@ManyToOne
	private User				checkUser;
	/** 填写时间 */
	private Date				writeDate;
	/** 审核时间 */
	private Date				checkDate;
	/** 单据状态 */
	private BillStatus			status;
	/** 到货日期 */
	private Date				arriveDate;

	/** 明细 **/
	@OneToMany(mappedBy = "inStock", cascade = { CascadeType.ALL })
	private List<InStockDetail>	details				= new ArrayList<InStockDetail>();

	public InStock() {
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Supplier getSupplier() {
		return Supplier;
	}

	public void setSupplier(Supplier supplier) {
		Supplier = supplier;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getBillCount() {
		return billCount;
	}

	public void setBillCount(Integer billCount) {
		this.billCount = billCount;
	}

	public BigDecimal getBillCost() {
		return billCost;
	}

	public void setBillCost(BigDecimal billCost) {
		this.billCost = billCost;
	}

	public User getWriteUser() {
		return writeUser;
	}

	public void setWriteUser(User writeUser) {
		this.writeUser = writeUser;
	}

	public User getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(User checkUser) {
		this.checkUser = checkUser;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public BillStatus getStatus() {
		return status;
	}

	public void setStatus(BillStatus status) {
		this.status = status;
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
}