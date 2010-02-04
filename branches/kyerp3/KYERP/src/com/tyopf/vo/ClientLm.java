package com.tyopf.vo;

public class ClientLm implements java.io.Serializable {

	private static final long serialVersionUID = 5071961938195000343L;

	private long CLmId;

	//private String CCId;
	
	private ClientC ClientC;

	private String CLmLinkman;

	private String CLmJob;

	private String CLmTel;

	private String CLmMobile;
	private Integer mobileRemind;
	private String email;
	private Integer emailRemind;

	private String CLmRemark;
	

	public ClientLm() {
	}

	public ClientC getClientC() {
		return ClientC;
	}

	public void setClientC(ClientC clientC) {
		ClientC = clientC;
	}

	public long getCLmId() {
		return this.CLmId;
	}

	public Integer getMobileRemind() {
		return mobileRemind;
	}

	public void setMobileRemind(Integer mobileRemind) {
		this.mobileRemind = mobileRemind;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEmailRemind() {
		return emailRemind;
	}

	public void setEmailRemind(Integer emailRemind) {
		this.emailRemind = emailRemind;
	}

	public void setCLmId(long CLmId) {
		this.CLmId = CLmId;
	}


	public String getCLmLinkman() {
		return this.CLmLinkman;
	}

	public void setCLmLinkman(String CLmLinkman) {
		this.CLmLinkman = CLmLinkman;
	}

	public String getCLmJob() {
		return this.CLmJob;
	}

	public void setCLmJob(String CLmJob) {
		this.CLmJob = CLmJob;
	}

	public String getCLmTel() {
		return this.CLmTel;
	}

	public void setCLmTel(String CLmTel) {
		this.CLmTel = CLmTel;
	}

	public String getCLmMobile() {
		return this.CLmMobile;
	}

	public void setCLmMobile(String CLmMobile) {
		this.CLmMobile = CLmMobile;
	}

	public String getCLmRemark() {
		return this.CLmRemark;
	}

	public void setCLmRemark(String CLmRemark) {
		this.CLmRemark = CLmRemark;
	}

}
