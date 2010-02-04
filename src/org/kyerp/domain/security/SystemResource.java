package org.kyerp.domain.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.kyerp.domain.BaseDomain;

/**
 * 系统资源
 * 
 * @author y109 2009-12-29下午02:58:48
 */
@Entity
public class SystemResource extends BaseDomain implements Serializable {

	private static final long	serialVersionUID	= 7570037266871625192L;
	/** 资源名 */
	private String				name;
	/** 资源类型 */
	@Enumerated(EnumType.STRING)
	private SystemResourceType	type;
	/** 资源内容 */
	private String				content;
	/** 资源所属角色 */
	@ManyToMany
	private List<Role>			roles				= new ArrayList<Role>();
	/** 资源所属模块 */
	@ManyToOne(fetch = FetchType.EAGER)
	private SystemModule		systemModule;
	/** 备注 */
	private String				remark;

	public SystemResource() {
	}

	public String getName() {
		return name;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SystemResourceType getType() {
		return type;
	}

	public void setType(SystemResourceType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SystemModule getSystemModule() {
		return systemModule;
	}

	public void setSystemModule(SystemModule systemModule) {
		this.systemModule = systemModule;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Role> getRoles() {
		return roles;
	}

}
