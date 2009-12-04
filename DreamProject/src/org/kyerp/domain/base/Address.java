/**
 * 地址 实体
 */
package org.kyerp.domain.base;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author y109 2009-11-23下午03:06:51
 */
@Entity
public class Address implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/** id */
	@Id
	@GeneratedValue
	private Long				id;

	/** 区域 ：国家，省，市，区 */
	@OneToOne(cascade = { CascadeType.ALL })
	private Region				region;

	/** 具体地址 */
	private String				address;

	public Address() {
	}

	public String getAddress() {
		return address;
	}

	public Long getId() {
		return id;
	}

	public Region getRegion() {
		return region;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}
