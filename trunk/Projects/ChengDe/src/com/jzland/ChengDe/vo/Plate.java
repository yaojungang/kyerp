package com.jzland.ChengDe.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Plate {
	private Integer id;
	private List<PlateUseLog> useLogs = new ArrayList<PlateUseLog>();
	private Date startTime;
	private Date lastChangeTime;
	private String startMan;
	private String lastChangeMan;
	private Integer viewTimes;
	private Date inputDate;
	private Date lastUseDate;
	private Date expDate;
	private String danAnNo;
	private String pname;
	private String plateNo;
	private Double plateLength;
	private Double plateWidth;
	private Integer plateAmount;
	private String plateShare;
	private String plateAddress;
	public Plate() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<PlateUseLog> getUseLogs() {
		return useLogs;
	}
	public void setUseLogs(List<PlateUseLog> useLogs) {
		this.useLogs = useLogs;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getLastChangeTime() {
		return lastChangeTime;
	}
	public void setLastChangeTime(Date lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
	}
	public String getStartMan() {
		return startMan;
	}
	public void setStartMan(String startMan) {
		this.startMan = startMan;
	}
	public String getLastChangeMan() {
		return lastChangeMan;
	}
	public void setLastChangeMan(String lastChangeMan) {
		this.lastChangeMan = lastChangeMan;
	}
	public Integer getViewTimes() {
		return viewTimes;
	}
	public void setViewTimes(Integer viewTimes) {
		this.viewTimes = viewTimes;
	}
	public Date getInputDate() {
		return inputDate;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
	public Date getLastUseDate() {
		return lastUseDate;
	}
	public void setLastUseDate(Date lastUseDate) {
		this.lastUseDate = lastUseDate;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public String getDanAnNo() {
		return danAnNo;
	}
	public void setDanAnNo(String danAnNo) {
		this.danAnNo = danAnNo;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public Double getPlateLength() {
		return plateLength;
	}
	public void setPlateLength(Double plateLength) {
		this.plateLength = plateLength;
	}
	public Double getPlateWidth() {
		return plateWidth;
	}
	public void setPlateWidth(Double plateWidth) {
		this.plateWidth = plateWidth;
	}
	public Integer getPlateAmount() {
		return plateAmount;
	}
	public void setPlateAmount(Integer plateAmount) {
		this.plateAmount = plateAmount;
	}
	public String getPlateShare() {
		return plateShare;
	}
	public void setPlateShare(String plateShare) {
		this.plateShare = plateShare;
	}
	public String getPlateAddress() {
		return plateAddress;
	}
	public void setPlateAddress(String plateAddress) {
		this.plateAddress = plateAddress;
	}
}
