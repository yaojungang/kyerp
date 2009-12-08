package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * 品牌
 * 
 * @author y109 2009-11-29下午11:31:35
 */
@Entity
public class Brand implements Serializable {
	private static final long		serialVersionUID	= 1L;
	@Id
	@GeneratedValue
	private long					id;
	/** 品牌名称 **/
	private String					name;
	/** 是否可见 **/
	private Boolean					visible				= true;
	@OneToMany(mappedBy = "brand")
	private final List<Material>	materials			= new ArrayList<Material>();
	/** logo图片路径 如:/images/brand/2008/12/12/ooo.gif" **/
	private String					logopath;
	/** 建立时间 */
	private Date					createTime;
	/** 修改时间 */
	private Date					updateTime;

	public Brand() {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		return builder.toString();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getLogopath() {
		return logopath;
	}

	public void setLogopath(String logopath) {
		this.logopath = logopath;
	}
}
