package com.tyopf.vo;

import java.util.HashSet;
import java.util.Set;
@SuppressWarnings("serial")
public class CompanyFilm implements java.io.Serializable {
	private Integer id;
	private Set<AfElement> afElement = new HashSet<AfElement>();
	private String name;
	private String remark;
	
	public CompanyFilm() {}
	
	public Set<AfElement> getAfElement() {
		return afElement;
	}

	public void setAfElement(Set<AfElement> afElement) {
		this.afElement = afElement;
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
	
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
