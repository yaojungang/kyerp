package com.jzland.ChengDe.vo;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class SystemModules implements java.io.Serializable {
	private Integer id;
	private Set<SystemFunctions> systemFunctions = new HashSet<SystemFunctions>(); 
	private String name;
	private String chineseName;
	
	public SystemModules() {}
	
	public Integer getId() {
		return this.id;
	}
	
	public Set<SystemFunctions> getSystemFunctions() {
		return systemFunctions;
	}

	public void setSystemFunctions(Set<SystemFunctions> systemFunctions) {
		this.systemFunctions = systemFunctions;
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
	
	public String getChineseName() {
		return this.chineseName;
	}
	
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
}
