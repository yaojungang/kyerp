/**
 * 
 */
package org.kyerp.web.controller.security;

import org.kyerp.domain.security.SystemResourceType;

/**
 * @author y109 2010-1-28下午01:57:31
 */
public class SystemResourceExtGridRow {
	private Long				id;
	private String				name;
	private String				content;
	private SystemResourceType	type;
	private String				remark;
	private Long				systemModuleId;
	private String				systemModuleName;

	public SystemResourceExtGridRow() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SystemResourceType getType() {
		return type;
	}

	public void setType(SystemResourceType type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getSystemModuleId() {
		return systemModuleId;
	}

	public void setSystemModuleId(Long systemModuleId) {
		this.systemModuleId = systemModuleId;
	}

	public String getSystemModuleName() {
		return systemModuleName;
	}

	public void setSystemModuleName(String systemModuleName) {
		this.systemModuleName = systemModuleName;
	}

}
