package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * 库存类别 category
 * 
 * @author y109 2009-11-29下午11:15:51
 */
@Entity
public class MaterialCategory implements Serializable {
	private static final long				serialVersionUID		= -3328204953749595577L;
	@Id
	@GeneratedValue
	/** Id*/
	private long							id;
	/** 类别名称 **/
	private String							name;
	/** 备注 **/
	private String							note;
	/** 是否可见 **/
	private final Boolean					visible					= true;
	/** 子类别 **/
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE }, mappedBy = "parentMaterialCategory")
	private final List<MaterialCategory>	childMaterialCategories	= new ArrayList<MaterialCategory>();
	/** 所属父类 **/
	@ManyToOne(cascade = CascadeType.REFRESH)
	private MaterialCategory				parentMaterialCategory;
	/** 下属库存 **/
	@OneToMany(mappedBy = "materialCategory")
	private final List<Material>			material				= new ArrayList<Material>();
	/** 建立时间 */
	private Date							createTime;
	/** 修改时间 */
	private Date							updateTime;

	public MaterialCategory() {
	}

	/** 在对象新建前保存建立时间 */
	@PrePersist
	public void prePersist() {
		this.createTime = new Date();
	}

	/** 在对象更新前保存修改时间 */
	@PreUpdate
	void preUpdate() {
		this.updateTime = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public MaterialCategory getParentMaterialCategory() {
		return parentMaterialCategory;
	}

	public void setParentMaterialCategory(
			MaterialCategory parentMaterialCategory) {
		this.parentMaterialCategory = parentMaterialCategory;
	}

	public Boolean getVisible() {
		return visible;
	}

	public List<MaterialCategory> getChildMaterialCategories() {
		return childMaterialCategories;
	}

	public List<Material> getMaterial() {
		return material;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
