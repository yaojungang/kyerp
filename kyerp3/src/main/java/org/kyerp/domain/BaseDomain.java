package org.kyerp.domain;

import java.util.Date;

import javax.annotation.PreDestroy;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author y109 2009-12-9上午08:11:03
 */
@MappedSuperclass
public class BaseDomain{
	@Transient
	// 不写入数据库
	protected final Log	logger	= LogFactory.getLog(getClass());
	/** id */
	@Id
	@GeneratedValue
	private Long		id;
	/** 建立时间 */
	private Date		createTime;
	/** 修改时间 */
	private Date		updateTime;
	@Version
	private Integer		version;

	/** 在对象新建前保存建立时间 
	 * @throws Exception */
	@PrePersist
	public void prePersist() throws Exception {
		this.createTime = new Date();
	}

	/** 在实体成为持久实体后，调用该实体的 @PostPersist 回调方法 */
	@PostPersist
	public void postPersist()throws Exception  {
	}

	/** 在对象更新前保存修改时间 */
	@PreUpdate
	public void preUpdate()throws Exception  {
		this.updateTime = new Date();
	}

	/** 在对实体数据进行了数据库更新操作后，调用实体的 @PostUpdate 回调方法 */
	@PostUpdate
	public void postUpdate() throws Exception {
	}

	/** 在对象删除前执行 */
	@PreDestroy
	public void preDestory()throws Exception  {
	}

	/** 在删除实体后，调用该实体的 @PostRemove 回调方法 */
	@PostRemove
	public void postRemove() throws Exception {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		BaseDomain other = (BaseDomain) obj;
		if(id == null) {
			if(other.id != null) {
				return false;
			}
		} else if(!id.equals(other.id)) {
			return false;
		}
		if(version == null) {
			if(other.version != null) {
				return false;
			}
		} else if(!version.equals(other.version)) {
			return false;
		}
		return true;
	}

}
