/**
 * 
 */
package org.kyerp.web.controller.security;

/**
 * @author y109 2010-1-28上午09:26:06
 */
public class RoleExtGridRow {
	private Long	id;
	private String	name;
	private Long	departmentId;
	private String	departmentName;
	private String	userIds;
	private String	userNames;
	private String	systemResourceIds;
	private String	systemResourceNames;
	private String	remark;

	public RoleExtGridRow() {
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

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public String getSystemResourceIds() {
		return systemResourceIds;
	}

	public void setSystemResourceIds(String systemResourceIds) {
		this.systemResourceIds = systemResourceIds;
	}

	public String getSystemResourceNames() {
		return systemResourceNames;
	}

	public void setSystemResourceNames(String systemResourceNames) {
		this.systemResourceNames = systemResourceNames;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
