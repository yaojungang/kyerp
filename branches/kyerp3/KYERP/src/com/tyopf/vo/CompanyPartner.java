package com.tyopf.vo;

public class CompanyPartner implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String type;
	private String linkman;
	private String tel;
	private String technics;
	private String remark;

	public CompanyPartner() {
	}

	public CompanyPartner(String name, String type, String linkman, String tel,
			String technics, String remark) {
		this.name = name;
		this.type = type;
		this.linkman = linkman;
		this.tel = tel;
		this.technics = technics;
		this.remark = remark;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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
