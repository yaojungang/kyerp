package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.kyerp.domain.org.Department;
import org.kyerp.domain.org.Employee;

/**
 * 出库单
 * 
 * @author y109 2010-3-19下午06:49:15
 */
@Entity
public class OutStock extends Inventory implements Serializable{

	private static final long		serialVersionUID	= 1L;
	/** 领料部门 */
	@ManyToOne
	private Department				receiveDepartment;
	/** 领料人 */
	@ManyToOne
	private Employee				receiveEmployee;
	/** 出库时间 */
	private Date					outDate;
	/** 明细 **/
	@OneToMany(mappedBy = "outStock",cascade = { CascadeType.ALL })
	private List<OutStockDetail>	details				= new ArrayList<OutStockDetail>();

	public OutStock() {
	}
	
	@Override
	public String toString() {
		return "OutStock [getBillCount()=" + getBillCount()
				+ ", getBillCost()=" + getBillCost() + ", getId()=" + getId()
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((outDate == null) ? 0 : outDate.hashCode());
		result = prime
				* result
				+ ((receiveDepartment == null) ? 0 : receiveDepartment
						.hashCode());
		result = prime * result
				+ ((receiveEmployee == null) ? 0 : receiveEmployee.hashCode());
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
		OutStock other = (OutStock) obj;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (outDate == null) {
			if (other.outDate != null)
				return false;
		} else if (!outDate.equals(other.outDate))
			return false;
		if (receiveDepartment == null) {
			if (other.receiveDepartment != null)
				return false;
		} else if (!receiveDepartment.equals(other.receiveDepartment))
			return false;
		if (receiveEmployee == null) {
			if (other.receiveEmployee != null)
				return false;
		} else if (!receiveEmployee.equals(other.receiveEmployee))
			return false;
		return true;
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

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public List<OutStockDetail> OutStockDetail() {
		return details;
	}

	public void setDetails(List<OutStockDetail> details) {
		this.details = details;
	}

	public List<OutStockDetail> getDetails() {
		return details;
	}

}
