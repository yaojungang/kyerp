package com.jzland.ChengDe.vo;

import java.util.Date;

@SuppressWarnings("serial")
public class PlateUseLog implements java.io.Serializable {
	private Integer id;
	private Plate plate;
	private int plateId;
	private Date useTime;
	private String inputMan;
	private int pressAmount;
	private String remark;
	
	public PlateUseLog() {}
	
	public Integer getId() {
		return this.id;
	}
	
	public Plate getPlate() {
		return plate;
	}

	public void setPlate(Plate plate) {
		this.plate = plate;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public int getPlateId() {
		return this.plateId;
	}
	
	public void setPlateId(int plateId) {
		this.plateId = plateId;
	}
	public Date getUseTime() {
		return useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	public String getInputMan() {
		return inputMan;
	}

	public void setInputMan(String inputMan) {
		this.inputMan = inputMan;
	}

	public int getPressAmount() {
		return this.pressAmount;
	}
	
	public void setPressAmount(int pressAmount) {
		this.pressAmount = pressAmount;
	}
	
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
