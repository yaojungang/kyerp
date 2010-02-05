/**
 * 
 */
package org.kyerp.web.controller.org;

/**
 * @author y109 2010-1-29下午04:14:45
 */
public class DepartmentGridRow {
	private Long	id;
	private String	name;
	/** 上级部门 **/
	private Long	parentDepartmentId;
	private String	parentDepartmentName;
	/** 下级部门 **/
	private String	childDepartmentIds;
	private String	childDepartmentNames;
	/** 下属员工 */
	private String	employeeIds;
	private String	employeeNames;
	/** 下属 职位角色 */
	private String	roleIds;
	private String	roleNames;
	/** 排序 */
	private Integer	deptOrder;
	/** 状态 */
	private Boolean	deptStatus;
	/** 备注 */
	private String	remark;

	public DepartmentGridRow() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentDepartmentId() {
		return parentDepartmentId;
	}

	public void setParentDepartmentId(Long parentDepartmentId) {
		this.parentDepartmentId = parentDepartmentId;
	}

	public String getParentDepartmentName() {
		return parentDepartmentName;
	}

	public void setParentDepartmentName(String parentDepartmentName) {
		this.parentDepartmentName = parentDepartmentName;
	}

	public String getChildDepartmentIds() {
		return childDepartmentIds;
	}

	public void setChildDepartmentIds(String childDepartmentIds) {
		this.childDepartmentIds = childDepartmentIds;
	}

	public String getChildDepartmentNames() {
		return childDepartmentNames;
	}

	public void setChildDepartmentNames(String childDepartmentNames) {
		this.childDepartmentNames = childDepartmentNames;
	}

	public String getEmployeeIds() {
		return employeeIds;
	}

	public void setEmployeeIds(String employeeIds) {
		this.employeeIds = employeeIds;
	}

	public String getEmployeeNames() {
		return employeeNames;
	}

	public void setEmployeeNames(String employeeNames) {
		this.employeeNames = employeeNames;
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

	public Integer getDeptOrder() {
		return deptOrder;
	}

	public void setDeptOrder(Integer deptOrder) {
		this.deptOrder = deptOrder;
	}

	public Boolean getDeptStatus() {
		return deptStatus;
	}

	public void setDeptStatus(Boolean deptStatus) {
		this.deptStatus = deptStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
