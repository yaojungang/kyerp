package org.kyerp.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

/**
 * @author y109 2009-12-9上午08:11:03
 */
@MappedSuperclass
public class BaseDomain {
	/** id */
	@Id
	@GeneratedValue
	private Long	id;
	/** 建立时间 */
	private Date	createTime;
	/** 修改时间 */
	private Date	updateTime;
	@Version
	private Integer	version;

	/** 在对象新建前保存建立时间 */
	@PrePersist
	public void prePersist() {
		this.createTime = new Date();
	}

	/** 在对象更新前保存修改时间 */
	@PreUpdate
	public void preUpdate() {
		this.updateTime = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
