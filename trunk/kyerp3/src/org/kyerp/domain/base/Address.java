package org.kyerp.domain.base;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.kyerp.domain.BaseDomain;

/**
 * 地址 实体
 * 
 * @author y109 2009-11-23下午03:06:51
 */
@Entity
public class Address extends BaseDomain implements java.io.Serializable {
	private static final long	serialVersionUID	= 1L;

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

	public Region getRegion() {
		return region;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}
