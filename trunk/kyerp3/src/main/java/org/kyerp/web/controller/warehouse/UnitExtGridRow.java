package org.kyerp.web.controller.warehouse;

/**
 * @author y109 2010-2-3上午11:03:31
 */
public class UnitExtGridRow {
	private Long	id;
	/** 编码 */
	private String	serialNumber;
	/** 名称 **/
	private String	name;
	/** 简拼 **/
	private String	nameSpell;
	/** 建立时间 */
	private String	createTime;
	/** 修改时间 */
	private String	updateTime;
	/** 摘要 **/
	private String	note;
	/** 子类别 **/
	private String	childUnitIds;
	private String	childUnitNames;
	/** 所属父类 **/
	private long	parentUnitId;
	private String	parentUnitName;

	UnitExtGridRow() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameSpell() {
		return nameSpell;
	}

	public void setNameSpell(String nameSpell) {
		this.nameSpell = nameSpell;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getChildUnitIds() {
		return childUnitIds;
	}

	public void setChildUnitIds(String childUnitIds) {
		this.childUnitIds = childUnitIds;
	}

	public String getChildUnitNames() {
		return childUnitNames;
	}

	public void setChildUnitNames(String childUnitNames) {
		this.childUnitNames = childUnitNames;
	}

	public long getParentUnitId() {
		return parentUnitId;
	}

	public void setParentUnitId(long parentUnitId) {
		this.parentUnitId = parentUnitId;
	}

	public String getParentUnitName() {
		return parentUnitName;
	}

	public void setParentUnitName(String parentUnitName) {
		this.parentUnitName = parentUnitName;
	}

}
