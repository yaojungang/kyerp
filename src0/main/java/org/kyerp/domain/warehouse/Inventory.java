package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.kyerp.domain.BaseDomain;
import org.kyerp.domain.org.Employee;
import org.kyerp.domain.security.User;

/**
 * 库存表
 *org.kyerp.domain.warehouse.Inventory.java
 * 
 * @author y109
 *         2010-4-19上午09:12:18
 */
@Entity
public class Inventory extends BaseDomain implements Serializable{

	private static final long	serialVersionUID	= 1L;
	/** 单号 */
	private String				serialNumber;
	/** 收发类型 */
	@ManyToOne
	private InOutType			inOutType;

	/** 总数量 */
	@Column(precision = 12,scale = 4)
	private BigDecimal			billCount			= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 总费用 */
	@Column(precision = 12,scale = 4)
	private BigDecimal			billCost			= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	/** 填单人 */
	@ManyToOne
	private User				writeUser;
	@ManyToOne
	private Employee			writeEmployee;
	/** 审核人 */
	@ManyToOne
	private User				checkUser;
	@ManyToOne
	private Employee			checkEmployee;
	/** 经办人 */
	@ManyToOne
	private Employee			keeper;
	/** 填写时间 */
	private Date				writeDate;
	/** 审核时间 */
	private Date				checkDate;
	/** 单据状态 */
	private BillStatus			status;
	/** 备注 */
	private String				remark;

	public Inventory() {
	}


	@Override
	public String toString() {
		return "Inventory [serialNumber=" + serialNumber + ", inOutType="
				+ inOutType + ", billCount=" + billCount + ", billCost="
				+ billCost + ", writeUser=" + writeUser + ", writeEmployee="
				+ writeEmployee + ", checkUser=" + checkUser
				+ ", checkEmployee=" + checkEmployee + ", keeper=" + keeper
				+ ", writeDate=" + writeDate + ", checkDate=" + checkDate
				+ ", status=" + status + ", remark=" + remark + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((billCost == null) ? 0 : billCost.hashCode());
		result = prime * result
				+ ((billCount == null) ? 0 : billCount.hashCode());
		result = prime * result
				+ ((checkDate == null) ? 0 : checkDate.hashCode());
		result = prime * result
				+ ((checkEmployee == null) ? 0 : checkEmployee.hashCode());
		result = prime * result
				+ ((checkUser == null) ? 0 : checkUser.hashCode());
		result = prime * result
				+ ((inOutType == null) ? 0 : inOutType.hashCode());
		result = prime * result + ((keeper == null) ? 0 : keeper.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result
				+ ((serialNumber == null) ? 0 : serialNumber.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((writeDate == null) ? 0 : writeDate.hashCode());
		result = prime * result
				+ ((writeEmployee == null) ? 0 : writeEmployee.hashCode());
		result = prime * result
				+ ((writeUser == null) ? 0 : writeUser.hashCode());
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
		Inventory other = (Inventory) obj;
		if (billCost == null) {
			if (other.billCost != null)
				return false;
		} else if (!billCost.equals(other.billCost))
			return false;
		if (billCount == null) {
			if (other.billCount != null)
				return false;
		} else if (!billCount.equals(other.billCount))
			return false;
		if (checkDate == null) {
			if (other.checkDate != null)
				return false;
		} else if (!checkDate.equals(other.checkDate))
			return false;
		if (checkEmployee == null) {
			if (other.checkEmployee != null)
				return false;
		} else if (!checkEmployee.equals(other.checkEmployee))
			return false;
		if (checkUser == null) {
			if (other.checkUser != null)
				return false;
		} else if (!checkUser.equals(other.checkUser))
			return false;
		if (inOutType == null) {
			if (other.inOutType != null)
				return false;
		} else if (!inOutType.equals(other.inOutType))
			return false;
		if (keeper == null) {
			if (other.keeper != null)
				return false;
		} else if (!keeper.equals(other.keeper))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (serialNumber == null) {
			if (other.serialNumber != null)
				return false;
		} else if (!serialNumber.equals(other.serialNumber))
			return false;
		if (status != other.status)
			return false;
		if (writeDate == null) {
			if (other.writeDate != null)
				return false;
		} else if (!writeDate.equals(other.writeDate))
			return false;
		if (writeEmployee == null) {
			if (other.writeEmployee != null)
				return false;
		} else if (!writeEmployee.equals(other.writeEmployee))
			return false;
		if (writeUser == null) {
			if (other.writeUser != null)
				return false;
		} else if (!writeUser.equals(other.writeUser))
			return false;
		return true;
	}


	public String nextCode() {
		return null;
	};

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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

	public Employee getWriteEmployee() {
		return writeEmployee;
	}

	public void setWriteEmployee(Employee writeEmployee) {
		this.writeEmployee = writeEmployee;
	}

	public User getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(User checkUser) {
		this.checkUser = checkUser;
	}

	public Employee getCheckEmployee() {
		return checkEmployee;
	}

	public void setCheckEmployee(Employee checkEmployee) {
		this.checkEmployee = checkEmployee;
	}

	public Employee getKeeper() {
		return keeper;
	}

	public void setKeeper(Employee keeper) {
		this.keeper = keeper;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
