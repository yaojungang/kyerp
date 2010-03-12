package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.kyerp.domain.BaseDomain;
import org.kyerp.domain.security.User;
import org.kyerp.utils.WebUtil;

/**
 * 采购申请单
 * 
 * @author y109 2010-3-1上午08:17:14
 */
@Entity
public class PurchaseOrder extends BaseDomain implements Serializable {
	private static final long			serialVersionUID	= 1L;
	/** 申请单号 */
	private String						serialNumber		= "";
	/** 供应商名称 */
	@ManyToOne
	private Supplier					Supplier;
	/** 备注 */
	private String						remark;
	/** 总数量 */
	private Integer						billCount;
	/** 总费用 */
	private BigDecimal					billCost;
	/** 填单人 */
	@ManyToOne
	private User						writeUser;
	/** 审核人 */
	@ManyToOne
	private User						checkUser;
	/** 填写时间 */
	private Date						writeDate;
	/** 审核时间 */
	private Date						checkDate;
	/** 单据状态 */
	private BillStatus					status;
	/** 到货日期 */
	private Date						arriveDate;

	/** 明细 **/
	@OneToMany(mappedBy = "purchaseOrder", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private List<PurchaseOrderDetail>	details				= new ArrayList<PurchaseOrderDetail>();

	public PurchaseOrder() {
	}

	@Override
	public void prePersist() {
		// 设置填单时间
		// this.setWriteDate(new Date());
		// 设置单据状态
		this.setStatus(BillStatus.WRITING);
		// 保存填单人
		this.setWriteUser(WebUtil.getCurrentUser());
		super.prePersist();
		this.preUpdate();
	}

	@Override
	public void preUpdate() {
		this.setBillCount(0);
		this.setBillCost(new BigDecimal("0"));
		for (PurchaseOrderDetail detail : this.getDetails()) {
			this.setBillCount(this.getBillCount() + detail.getBillCount());
			this.setBillCost(this.getBillCost().add(detail.getBillCost()));
		}
		super.preUpdate();
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public Date getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
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

	public List<PurchaseOrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<PurchaseOrderDetail> details) {
		this.details = details;
	}
}
