package org.kyerp.web.controller.warehouse;

import java.math.BigDecimal;

import org.kyerp.domain.warehouse.BillStatus;

/**
 * @author y109 2010-3-1下午02:17:55
 */

public class OutStockExtGridRow{
	private Long		id;
	/** 建立时间 */
	private String		createTime;
	/** 修改时间 */
	private String		updateTime;
	/** 申请单号 */
	private String		serialNumber;
	/** 领料部门 */
	private Long		receiveDepartmentId;
	private String		receiveDepartmentName;
	/** 领料人 */
	private Long		receiveEmployeeId;
	private String		receiveEmployeeName;
	/** 收发类型 */
	private Long		inOutTypeId;
	private String		inOutTypeName;
	/** 经手人 */
	private Long		keeperId;
	private String		keeperName;
	/** 备注 */
	private String		remark;
	/** 总数量 */
	private BigDecimal	billCount;
	/** 总费用 */
	private BigDecimal	billCost;
	/** 填单人 */
	private long		writeUserId;
	private String		writeUserName;
	private Long		writeEmployeeId;
	private String		writeEmployeeName;
	/** 审核人 */
	private long		checkUserId;
	private String		checkUserName;
	private Long		checkEmployeeId;
	private String		checkEmployeeName;
	/** 填写时间 */
	private String		writeDate;
	/** 审核时间 */
	private String		checkDate;
	/** 单据状态 */
	private BillStatus	status;
	private String		statusString;
	/** 明细 */
	private String		details;
	/** 出库时间 */
	private String		outDate;
	/** 能否编辑 */
	private String		editAble;

	public OutStockExtGridRow() {
	}

	public Long getId() {
		return id;
	}

	public String getOutDate() {
		return outDate;
	}

	public Long getKeeperId() {
		return keeperId;
	}

	public void setKeeperId(Long keeperId) {
		this.keeperId = keeperId;
	}

	public String getKeeperName() {
		return keeperName;
	}

	public void setKeeperName(String keeperName) {
		this.keeperName = keeperName;
	}

	public Long getReceiveDepartmentId() {
		return receiveDepartmentId;
	}

	public void setReceiveDepartmentId(Long receiveDepartmentId) {
		this.receiveDepartmentId = receiveDepartmentId;
	}

	public String getReceiveDepartmentName() {
		return receiveDepartmentName;
	}

	public void setReceiveDepartmentName(String receiveDepartmentName) {
		this.receiveDepartmentName = receiveDepartmentName;
	}

	public Long getReceiveEmployeeId() {
		return receiveEmployeeId;
	}

	public void setReceiveEmployeeId(Long receiveEmployeeId) {
		this.receiveEmployeeId = receiveEmployeeId;
	}

	public Long getWriteEmployeeId() {
		return writeEmployeeId;
	}

	public void setWriteEmployeeId(Long writeEmployeeId) {
		this.writeEmployeeId = writeEmployeeId;
	}

	public String getWriteEmployeeName() {
		return writeEmployeeName;
	}

	public void setWriteEmployeeName(String writeEmployeeName) {
		this.writeEmployeeName = writeEmployeeName;
	}

	public Long getCheckEmployeeId() {
		return checkEmployeeId;
	}

	public void setCheckEmployeeId(Long checkEmployeeId) {
		this.checkEmployeeId = checkEmployeeId;
	}

	public String getCheckEmployeeName() {
		return checkEmployeeName;
	}

	public void setCheckEmployeeName(String checkEmployeeName) {
		this.checkEmployeeName = checkEmployeeName;
	}

	public String getReceiveEmployeeName() {
		return receiveEmployeeName;
	}

	public void setReceiveEmployeeName(String receiveEmployeeName) {
		this.receiveEmployeeName = receiveEmployeeName;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Long getInOutTypeId() {
		return inOutTypeId;
	}

	public void setInOutTypeId(Long inOutTypeId) {
		this.inOutTypeId = inOutTypeId;
	}

	public String getInOutTypeName() {
		return inOutTypeName;
	}

	public void setInOutTypeName(String inOutTypeName) {
		this.inOutTypeName = inOutTypeName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public long getWriteUserId() {
		return writeUserId;
	}

	public void setWriteUserId(long writeUserId) {
		this.writeUserId = writeUserId;
	}

	public String getWriteUserName() {
		return writeUserName;
	}

	public void setWriteUserName(String writeUserName) {
		this.writeUserName = writeUserName;
	}

	public long getCheckUserId() {
		return checkUserId;
	}

	public void setCheckUserId(long checkUserId) {
		this.checkUserId = checkUserId;
	}

	public String getCheckUserName() {
		return checkUserName;
	}

	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public BillStatus getStatus() {
		return status;
	}

	public void setStatus(BillStatus status) {
		this.status = status;
	}

	public String getStatusString() {
		return statusString;
	}

	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getEditAble() {
		return editAble;
	}

	public void setEditAble(String editAble) {
		this.editAble = editAble;
	}

}
