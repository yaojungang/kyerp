package com.tyopf.vo;

public class AfQualityProblemAttachment implements java.io.Serializable {
	private int id;
	private int afqpId;
	private String fileName;
	
	public AfQualityProblemAttachment() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAfqpId() {
		return afqpId;
	}

	public void setAfqpId(int afqpId) {
		this.afqpId = afqpId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
