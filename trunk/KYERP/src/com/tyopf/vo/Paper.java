package com.tyopf.vo;

import java.util.Date;

public class Paper implements java.io.Serializable {

	private Integer pid;
	private String type;
	private String bands;
	private String weight;
	private String width;
	private String height;
	private Double price;
	private Date updateDate;
	private String remark;

	public Paper() {
	}

	public Paper(String type, String bands, String weight, String width,
			String height, Double price, String remark) {
		this.type = type;
		this.bands = bands;
		this.weight = weight;
		this.width = width;
		this.height = height;
		this.price = price;
		this.remark = remark;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBands() {
		return bands;
	}

	public void setBands(String bands) {
		this.bands = bands;
	}

	public String getWeight() {
		return weight;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	
}
