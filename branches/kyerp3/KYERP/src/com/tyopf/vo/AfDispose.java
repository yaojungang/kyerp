package com.tyopf.vo;

import java.util.Date;

public class AfDispose implements java.io.Serializable {

	private static final long serialVersionUID = 8821867449084525092L;

	private long afDId;

	//private String afId;
	private AfBase afBase;

	private Long afDAmount;

	private String afEType;

	private String afDItem;
	
	private Date afDDate;

	private String afDFactory;

	private String afDRemark;

	public AfDispose() {
	}

	public long getAfDId() {
		return this.afDId;
	}

	public void setAfDId(long afDId) {
		this.afDId = afDId;
	}

	public AfBase getAfBase() {
		return afBase;
	}

	public void setAfBase(AfBase afBase) {
		this.afBase = afBase;
	}

	public Long getAfDAmount() {
		return afDAmount;
	}

	public void setAfDAmount(Long afDAmount) {
		this.afDAmount = afDAmount;
	}

	public String getAfEType() {
		return this.afEType;
	}

	public void setAfEType(String afEType) {
		this.afEType = afEType;
	}

	public String getAfDItem() {
		return this.afDItem;
	}

	public void setAfDItem(String afDItem) {
		this.afDItem = afDItem;
	}

	public String getAfDFactory() {
		return this.afDFactory;
	}

	public void setAfDFactory(String afDFactory) {
		this.afDFactory = afDFactory;
	}

	public String getAfDRemark() {
		return this.afDRemark;
	}

	public void setAfDRemark(String afDRemark) {
		this.afDRemark = afDRemark;
	}

	public Date getAfDDate() {
		return afDDate;
	}

	public void setAfDDate(Date afDDate) {
		this.afDDate = afDDate;
	}

}
