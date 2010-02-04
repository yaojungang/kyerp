package org.kyerp.web.controller.warehouse;


/**
 * @author y109 2010-2-2下午05:04:03
 */
public class MaterialCategoryExtGridRow {
	private Long	id;
	/** 类别名称 **/
	private String	name;
	/** 编号 **/
	private String	serialNumber;
	/** 摘要 **/
	private String	note;
	/** 是否可见 **/
	private Boolean	visible	= true;
	/** 子类别 **/
	private String	childMaterialCategoryIds;
	private String	childMaterialCategoryNames;
	/** 所属父类 **/
	private Long	parentMaterialCategoryId;
	private String	parentMaterialCategoryName;

	MaterialCategoryExtGridRow() {
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

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getChildMaterialCategoryIds() {
		return childMaterialCategoryIds;
	}

	public void setChildMaterialCategoryIds(String childMaterialCategoryIds) {
		this.childMaterialCategoryIds = childMaterialCategoryIds;
	}

	public String getChildMaterialCategoryNames() {
		return childMaterialCategoryNames;
	}

	public void setChildMaterialCategoryNames(String childMaterialCategoryNames) {
		this.childMaterialCategoryNames = childMaterialCategoryNames;
	}

	public Long getParentMaterialCategoryId() {
		return parentMaterialCategoryId;
	}

	public void setParentMaterialCategoryId(Long parentMaterialCategoryId) {
		this.parentMaterialCategoryId = parentMaterialCategoryId;
	}

	public String getParentMaterialCategoryName() {
		return parentMaterialCategoryName;
	}

	public void setParentMaterialCategoryName(String parentMaterialCategoryName) {
		this.parentMaterialCategoryName = parentMaterialCategoryName;
	}

}
