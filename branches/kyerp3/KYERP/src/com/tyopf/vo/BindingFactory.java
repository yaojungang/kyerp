package com.tyopf.vo;
public class BindingFactory implements java.io.Serializable {

	private static final long serialVersionUID = -7912350430024548621L;

	private long bfId;

	private String factory;

	private String linkman;

	private String tel;

	private String technics;

	private String remark;

	public BindingFactory() {
	}

	public long getBfId() {
		return this.bfId;
	}

	public void setBfId(long bfId) {
		this.bfId = bfId;
	}

	public String getFactory() {
		return this.factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getLinkman() {
		return this.linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTechnics() {
		return this.technics;
	}

	public void setTechnics(String technics) {
		this.technics = technics;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
