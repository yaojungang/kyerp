package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.kyerp.domain.BaseDomain;
import org.kyerp.domain.org.Department;
import org.kyerp.domain.org.Employee;
import org.kyerp.domain.security.User;

/**
 * 采购申请单
 * 
 * @author y109 2010-3-1上午08:17:14
 */
@Entity
public class PurchaseOrder extends BaseDomain implements Serializable{
	private static final long			serialVersionUID	= 1L;
	/** 申请单号 */
	private String						serialNumber		= "";
	/** 供应商名称 */
	@ManyToOne
	private Supplier					Supplier;
	/** 申请类型 */
	@ManyToOne
	private InOutType					inOutType;
	/** 备注 */
	private String						remark;
	/** 总数量 */
	@Column(precision = 12,scale = 4)
	private BigDecimal					billCount			= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 总费用 */
	@Column(precision = 12,scale = 4)
	private BigDecimal					billCost			= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 申请部门 */
	@ManyToOne
	private Department					applicationDepartment;
	/** 申请人 */
	@ManyToOne
	private Employee					applicant;
	/** 填单人 */
	@ManyToOne
	private User						writeUser;
	@ManyToOne
	private Employee					writeEmployee;
	/** 审核人 */
	@ManyToOne
	private User						checkUser;
	@ManyToOne
	private Employee					checkEmployee;
	/** 填写时间 */
	private Date						writeDate;
	/** 审核时间 */
	private Date						checkDate;
	/** 单据状态 */
	private BillStatus					status;
	/** 到货日期 */
	private Date						arriveDate;

	/** 明细 **/
	@OneToMany(mappedBy = "purchaseOrder",cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
	private List<PurchaseOrderDetail>	details				= new ArrayList<PurchaseOrderDetail>();

	public PurchaseOrder() {
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public Date getArriveDate() {
		return arriveDate;
	}

	public Department getApplicationDepartment() {
		return applicationDepartment;
	}

	public InOutType getInOutType() {
		return inOutType;
	}

	public void setInOutType(InOutType inOutType) {
		this.inOutType = inOutType;
	}

	public void setApplicationDepartment(Department applicationDepartment) {
		this.applicationDepartment = applicationDepartment;
	}

	public Employee getApplicant() {
		return applicant;
	}

	public void setApplicant(Employee applicant) {
		this.applicant = applicant;
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

	public Employee getWriteEmployee() {
		return writeEmployee;
	}

	public void setWriteEmployee(Employee writeEmployee) {
		this.writeEmployee = writeEmployee;
	}

	public Employee getCheckEmployee() {
		return checkEmployee;
	}

	public void setCheckEmployee(Employee checkEmployee) {
		this.checkEmployee = checkEmployee;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public BigDecimal getBillCount() {
		return billCount;
	}

	public void setBillCount(BigDecimal billCount) {
		this.billCount = billCount;
	}
}
