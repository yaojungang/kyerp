package org.kyerp.web.controller.crm;

/**
 * @author y109 2010-3-9下午09:07:04
 */
public class ClientTypeExtGridRow {
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
	private String	childClientTypeIds;
	private String	childClientTypeNames;
	/** 所属父类 **/
	private long	parentClientTypeId;
	private String	parentClientTypeName;

	public ClientTypeExtGridRow() {

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

	public String getChildClientTypeIds() {
		return childClientTypeIds;
	}

	public void setChildClientTypeIds(String childClientTypeIds) {
		this.childClientTypeIds = childClientTypeIds;
	}

	public String getChildClientTypeNames() {
		return childClientTypeNames;
	}

	public void setChildClientTypeNames(String childClientTypeNames) {
		this.childClientTypeNames = childClientTypeNames;
	}

	public long getParentClientTypeId() {
		return parentClientTypeId;
	}

	public void setParentClientTypeId(long parentClientTypeId) {
		this.parentClientTypeId = parentClientTypeId;
	}

	public String getParentClientTypeName() {
		return parentClientTypeName;
	}

	public void setParentClientTypeName(String parentClientTypeName) {
		this.parentClientTypeName = parentClientTypeName;
	}
}
