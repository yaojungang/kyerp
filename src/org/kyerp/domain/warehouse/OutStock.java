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

// /** 单号 */
// private String serialNumber;
// /** 收发类型 */
// @ManyToOne
// private InOutType inOutType;
// /** 备注 */
// private String remark;
// /** 总数量 */
// @Column(precision = 12,scale = 4)
// private BigDecimal billCount = new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
// /** 总费用 */
// @Column(precision = 12,scale = 4)
// private BigDecimal billCost = new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
// /** 经办人 */
// @ManyToOne
// private Employee keeper;
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
// /** 填写时间 */
// private Date writeDate;
// /** 审核时间 */
// private Date checkDate;
// /** 单据状态 */
// private BillStatus status;

	public OutStock() {
	}

	@Override
	public void prePersist() {
		super.prePersist();
		this.preUpdate();
	}

	@Override
	public void preUpdate() {
		this.setBillCount(new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP));
		this.setBillCost(new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP));
		for (OutStockDetail detail : this.getDetails()) {
			this.setBillCount(this.getBillCount().add(detail.getOutStockCount()));
			this.setBillCost(this.getBillCost().add(detail.getBillCost()));
		}
		super.preUpdate();
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

	public List<OutStockDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OutStockDetail> details) {
		this.details = details;
	}

}
