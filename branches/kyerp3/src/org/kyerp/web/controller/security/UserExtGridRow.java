package org.kyerp.web.controller.security;

/**
 * @author y109 2010-1-28上午11:56:42
 */
public class UserExtGridRow {
	private Long	id;
	private String	userName;
	private String	roleIds;
	private String	roleNames;
	private String	remark;

	public UserExtGridRow() {
	}

	public Long getId() {
		return id;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
