package org.kyerp.web.controller.warehouse;

import java.math.BigDecimal;

/**
 * @author y109 2010-3-1下午02:17:55
 */

public class InStockExtGridRow{
	private Long		id;
	/** 建立时间 */
	private String		createTime;
	/** 修改时间 */
	private String		updateTime;
	/** 申请单号 */
	private String		serialNumber;
	/** 收发类型 */
	private Long		inOutTypeId;
	private String		inOutTypeName;
	/** 供应商 */
	private Long		supplierId;
	private String		supplierName;
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
	/** 经手人 */
	private Long		keeperId;
	private String		keeperName;
	/** 填写时间 */
	private String		writeDate;
	/** 审核时间 */
	private String		checkDate;
	/** 单据状态 */
	private String		statusString;
	/** 明细 */
	private String		details;
	/** 到货日期 */
	private String		arriveDate;
	/** 能否编辑 */
	private String		editAble;

	public InStockExtGridRow() {
	}

	public Long getId() {
		return id;
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

	public Long getWriteEmployeeId() {
		return writeEmployeeId;
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

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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

	public String getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(String arriveDate) {
		this.arriveDate = arriveDate;
	}

	public String getEditAble() {
		return editAble;
	}

	public void setEditAble(String editAble) {
		this.editAble = editAble;
	}

}
