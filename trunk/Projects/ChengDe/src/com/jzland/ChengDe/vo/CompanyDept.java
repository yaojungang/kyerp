package com.jzland.ChengDe.vo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompanyDept implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Set<Employee> employees = new HashSet<Employee>();
	private List<Role> roles = new ArrayList<Role>();
	private String name;
	private Integer upDeptId;
	private String employeeAmount;
	private Integer deptOrder;
	private Integer deptStatus;
	private String remark;

	public CompanyDept() {
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}


	public Integer getDeptStatus() {
		return deptStatus;
	}

	public void setDeptStatus(Integer deptStatus) {
		this.deptStatus = deptStatus;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Integer getId() {
		return this.id;
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

	public Integer getUpDeptId() {
		return this.upDeptId;
	}

	public void setUpDeptId(Integer upDeptId) {
		this.upDeptId = upDeptId;
	}

	public String getEmployeeAmount() {
		return this.employeeAmount;
	}

	public void setEmployeeAmount(String employeeAmount) {
		this.employeeAmount = employeeAmount;
	}

	public Integer getDeptOrder() {
		return this.deptOrder;
	}

	public void setDeptOrder(Integer deptOrder) {
		this.deptOrder = deptOrder;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
