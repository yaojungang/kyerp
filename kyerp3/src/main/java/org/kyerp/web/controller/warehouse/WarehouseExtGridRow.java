package org.kyerp.web.controller.warehouse;

/**
 * @author y109 2010-3-9下午09:07:04
 */
public class WarehouseExtGridRow {
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
	private String	childWarehouseIds;
	private String	childWarehouseNames;
	/** 所属父类 **/
	private long	parentWarehouseId;
	private String	parentWarehouseName;

	public WarehouseExtGridRow() {
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

	public String getChildWarehouseIds() {
		return childWarehouseIds;
	}

	public void setChildWarehouseIds(String childWarehouseIds) {
		this.childWarehouseIds = childWarehouseIds;
	}

	public String getChildWarehouseNames() {
		return childWarehouseNames;
	}

	public void setChildWarehouseNames(String childWarehouseNames) {
		this.childWarehouseNames = childWarehouseNames;
	}

	public long getParentWarehouseId() {
		return parentWarehouseId;
	}

	public void setParentWarehouseId(long parentWarehouseId) {
		this.parentWarehouseId = parentWarehouseId;
	}

	public String getParentWarehouseName() {
		return parentWarehouseName;
	}

	public void setParentWarehouseName(String parentWarehouseName) {
		this.parentWarehouseName = parentWarehouseName;
	}

}
