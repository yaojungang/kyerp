package com.jzland.ChengDe.vo;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class SystemFunctions implements java.io.Serializable {
	private Integer id;
	//private Integer moduleId;
	private SystemModules systemModule;
	private Set<Role> roles = new HashSet<Role>();
	private String name;
	private String remark;
	
	public SystemFunctions() {}
	
	public Integer getId() {
		return this.id;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public SystemModules getSystemModule() {
		return systemModule;
	}

	public void setSystemModule(SystemModules systemModule) {
		this.systemModule = systemModule;
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
