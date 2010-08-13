package org.kyerp.domain.security;

/**
 * 岗位角色实体
 * **/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.kyerp.domain.BaseDomain;
import org.kyerp.domain.org.Department;

@Entity
public class Role extends BaseDomain implements Serializable {
	private static final long		serialVersionUID	= -1090016624109826363L;
	/** 角色名称 */
	private String					name;
	/** 所属部门 */
	@ManyToOne
	private Department				department;
	/** 关联的用户 */
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<User>				users				= new ArrayList<User>();
	/** 所属系统资源 */
	@ManyToMany(fetch = FetchType.EAGER)
	private List<SystemResource>	systemResources;
	/** 角色备注 */
	private String					remark;

	public Role() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<SystemResource> getSystemResources() {
		return systemResources;
	}

	public void setSystemResources(List<SystemResource> systemResources) {
		this.systemResources = systemResources;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
