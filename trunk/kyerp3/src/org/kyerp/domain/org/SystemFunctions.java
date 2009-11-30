package org.kyerp.domain.org;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class SystemFunctions implements Serializable {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3292606265739389350L;
	@Id
	@GeneratedValue
	private long				id;
	@ManyToOne
	private SystemModules		systemModule;
	@ManyToMany
	private List<Role>			roles				= new ArrayList<Role>();
	private String				name;
	private String				remark;

	public SystemFunctions() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SystemModules getSystemModule() {
		return systemModule;
	}

	public void setSystemModule(SystemModules systemModule) {
		this.systemModule = systemModule;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
