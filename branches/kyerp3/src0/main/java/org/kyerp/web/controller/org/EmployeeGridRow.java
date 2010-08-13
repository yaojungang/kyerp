package org.kyerp.web.controller.org;

/**
 * @author y109 2010-2-4下午04:09:53
 */
public class EmployeeGridRow {
	private Long	id;
	private String	name;
	private Long	userId;
	private String	userName;
	private Long	departmentId;
	private String	departmentName;

	EmployeeGridRow() {
	}

	public Long getId() {
		return id;
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

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
