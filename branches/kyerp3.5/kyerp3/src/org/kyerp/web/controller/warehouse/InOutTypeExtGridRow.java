package org.kyerp.web.controller.warehouse;

/**
 * @author y109 2010-3-9下午09:07:04
 */
public class InOutTypeExtGridRow {
	private Long	id;
	/** 建立时间 */
	private String	createTime;
	/** 修改时间 */
	private String	updateTime;
	/** 名称 **/
	private String	name;
	/** 进出标记 */
	private String	inOutMark;
	/** 编号 **/
	private String	serialNumber;
	/** 摘要 **/
	private String	note;
	/** 子类别 **/
	private String	childInOutTypeIds;
	private String	childInOutTypeNames;
	/** 所属父类 **/
	private long	parentInOutTypeId;
	private String	parentInOutTypeName;

	public InOutTypeExtGridRow() {
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

	public String getInOutMark() {
		return inOutMark;
	}

	public void setInOutMark(String inOutMark) {
		this.inOutMark = inOutMark;
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

	public String getChildInOutTypeIds() {
		return childInOutTypeIds;
	}

	public void setChildInOutTypeIds(String childInOutTypeIds) {
		this.childInOutTypeIds = childInOutTypeIds;
	}

	public String getChildInOutTypeNames() {
		return childInOutTypeNames;
	}

	public void setChildInOutTypeNames(String childInOutTypeNames) {
		this.childInOutTypeNames = childInOutTypeNames;
	}

	public long getParentInOutTypeId() {
		return parentInOutTypeId;
	}

	public void setParentInOutTypeId(long parentInOutTypeId) {
		this.parentInOutTypeId = parentInOutTypeId;
	}

	public String getParentInOutTypeName() {
		return parentInOutTypeName;
	}

	public void setParentInOutTypeName(String parentInOutTypeName) {
		this.parentInOutTypeName = parentInOutTypeName;
	}

}
