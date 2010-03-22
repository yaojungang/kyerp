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
import org.kyerp.domain.org.Department;
import org.kyerp.domain.org.Employee;
import org.kyerp.domain.security.User;
import org.kyerp.utils.WebUtil;

/**
 * 出库单
 * 
 * @author y109 2010-3-19下午06:49:15
 */
@Entity
public class OutStock extends BaseDomain implements Serializable{

	private static final long		serialVersionUID	= 1L;

	/** 单号 */
	private String					serialNumber;
	/** 收发类型 */
	@ManyToOne
	private InOutType				inOutType;
	/** 领料部门 */
	@ManyToOne
	private Department				receiveDepartment;
	@ManyToOne
	private Employee				receiveEmployee;
	/** 备注 */
	private String					remark;
	/** 总数量 */
	private BigDecimal				billCount;
	/** 总费用 */
	private BigDecimal				billCost;
	/** 填单人 */
	@ManyToOne
	private User					writeUser;
	/** 审核人 */
	@ManyToOne
	private User					checkUser;
	/** 填写时间 */
	private Date					writeDate;
	/** 审核时间 */
	private Date					checkDate;
	/** 单据状态 */
	private BillStatus				status;

	/** 明细 **/
	@OneToMany(mappedBy = "outStock",cascade = { CascadeType.ALL })
	private List<OutStockDetail>	details				= new ArrayList<OutStockDetail>();

	public OutStock() {
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
		this.setBillCount(new BigDecimal("0"));
		this.setBillCost(new BigDecimal("0"));
		for (OutStockDetail detail : this.getDetails()) {
			this.setBillCount(this.getBillCount().add(detail.getBillCount()));
			this.setBillCost(this.getBillCost().add(detail.getBillCost()));
		}
		super.preUpdate();
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public InOutType getInOutType() {
		return inOutType;
	}

	public void setInOutType(InOutType inOutType) {
		this.inOutType = inOutType;
	}

	public BigDecimal getBillCount() {
		return billCount;
	}

	public Department getReceiveDepartment() {
		return receiveDepartment;
	}

	public void setReceiveDepartment(Department receiveDepartment) {
		this.receiveDepartment = receiveDepartment;
	}

	public Employee getReceiveEmployee() {
		return receiveEmployee;
	}

	public void setReceiveEmployee(Employee receiveEmployee) {
		this.receiveEmployee = receiveEmployee;
	}

	public void setBillCount(BigDecimal billCount) {
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

	public List<OutStockDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OutStockDetail> details) {
		this.details = details;
	}
}
