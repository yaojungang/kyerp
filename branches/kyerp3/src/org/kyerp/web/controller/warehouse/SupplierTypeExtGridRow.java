package org.kyerp.web.controller.warehouse;


/**
 * @author y109 2010-3-9下午09:07:04
 */
public class SupplierTypeExtGridRow {
	private Long	id;
	/** 建立时间 */
	private String	createTime;
	/** 修改时间 */
	private String	updateTime;
	/** 类别名称 **/
	private String	name;
	/** 编号 **/
	private String	serialNumber;
	/** 摘要 **/
	private String	note;
	/** 子类别 **/
	private String	childSupplierTypeIds;
	private String	childSupplierTypeNames;
	/** 所属父类 **/
	private long	parentSupplierTypeId;
	private String	parentSupplierTypeName;

	public SupplierTypeExtGridRow() {

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

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getChildSupplierTypeIds() {
		return childSupplierTypeIds;
	}

	public void setChildSupplierTypeIds(String childSupplierTypeIds) {
		this.childSupplierTypeIds = childSupplierTypeIds;
	}

	public String getChildSupplierTypeNames() {
		return childSupplierTypeNames;
	}

	public void setChildSupplierTypeNames(String childSupplierTypeNames) {
		this.childSupplierTypeNames = childSupplierTypeNames;
	}

	public long getParentSupplierTypeId() {
		return parentSupplierTypeId;
	}

	public void setParentSupplierTypeId(long parentSupplierTypeId) {
		this.parentSupplierTypeId = parentSupplierTypeId;
	}

	public String getParentSupplierTypeName() {
		return parentSupplierTypeName;
	}

	public void setParentSupplierTypeName(String parentSupplierTypeName) {
		this.parentSupplierTypeName = parentSupplierTypeName;
	}
}
