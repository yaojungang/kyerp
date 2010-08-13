/**
 * 区域 实体
 */
package org.kyerp.domain.common;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author y109 2009-11-23下午03:06:51
 */
@Entity
public class Region implements java.io.Serializable {
	/** 地区类型 */
	public enum RegionType {
		COUNTRY, PROVINCE, CITY, DISTRICT;
		public String toSting() {
			switch (this) {
				case COUNTRY:
					return "国家";
				case PROVINCE:
					return "省";
				case CITY:
					return "市";
				case DISTRICT:
					return "区";
				default:
					return "未知区域";
			}
		}
	}

	private static final long	serialVersionUID	= 1L;
	/** id */
	@Id
	@GeneratedValue
	private Long				id;
	/** 上級id */
	private Long				parentId;

	/** 名称 */
	private String				name;
	/** 类型 */
	@Enumerated(EnumType.STRING)
	private RegionType			type;

	public Region() {
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getParentId() {
		return parentId;
	}

	public RegionType getType() {
		return type;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public void setType(RegionType type) {
		this.type = type;
	}

}
