package org.kyerp.web.controller.warehouse;

/**
 * @author y109 2010-2-2下午03:50:46
 */
public class BrandExtGridRow {
	private Long	id;
	private String	name;
	private String	nameSpell;
	private String	visable;
	/** 建立时间 */
	private String	createTime;
	/** 修改时间 */
	private String	updateTime;
	/** 编号 **/
	private String	serialNumber;
	/** 摘要 **/
	private String	note;
	/** 子类别 **/
	private String	childBrandIds;
	private String	childBrandNames;
	/** 所属父类 **/
	private long	parentBrandId;
	private String	parentBrandName;

	BrandExtGridRow() {
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

	public String getChildBrandIds() {
		return childBrandIds;
	}

	public void setChildBrandIds(String childBrandIds) {
		this.childBrandIds = childBrandIds;
	}

	public String getChildBrandNames() {
		return childBrandNames;
	}

	public void setChildBrandNames(String childBrandNames) {
		this.childBrandNames = childBrandNames;
	}

	public long getParentBrandId() {
		return parentBrandId;
	}

	public void setParentBrandId(long parentBrandId) {
		this.parentBrandId = parentBrandId;
	}

	public String getParentBrandName() {
		return parentBrandName;
	}

	public void setParentBrandName(String parentBrandName) {
		this.parentBrandName = parentBrandName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVisable() {
		return visable;
	}

	public void setVisable(String visable) {
		this.visable = visable;
	}

}
