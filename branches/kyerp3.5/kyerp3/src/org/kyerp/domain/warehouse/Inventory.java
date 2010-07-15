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
import org.kyerp.utils.WebUtil;

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
	public void prePersist() {
// 设置单据状态
		this.setStatus(BillStatus.WRITING);
		// 保存填单时间
		this.setWriteDate(new Date());
		// 保存填单人

		try {
			this.setWriteUser(WebUtil.getCurrentUser());
			this.setWriteEmployee(WebUtil.getCurrentEmployee());
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.prePersist();
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
