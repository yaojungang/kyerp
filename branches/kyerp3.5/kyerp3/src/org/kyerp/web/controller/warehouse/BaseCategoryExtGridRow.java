package org.kyerp.web.controller.warehouse;

/**
 * @author y109 2010-2-2下午05:04:03
 */
public class BaseCategoryExtGridRow{
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
	private String	remark;
	/** 子类别 **/
	private String	childCategoryIds;
	private String	childCategoryNames;
	/** 所属父类 **/
	private long	parentCategoryId;
	private String	parentCategoryName;

	BaseCategoryExtGridRow() {
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getChildCategoryIds() {
		return childCategoryIds;
	}

	public void setChildCategoryIds(String childCategoryIds) {
		this.childCategoryIds = childCategoryIds;
	}

	public String getChildCategoryNames() {
		return childCategoryNames;
	}

	public void setChildCategoryNames(String childCategoryNames) {
		this.childCategoryNames = childCategoryNames;
	}

	public long getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public String getParentCategoryName() {
		return parentCategoryName;
	}

	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}

}
