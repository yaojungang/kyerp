package org.kyerp.domain.org;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Department implements Serializable {
	private static final long		serialVersionUID	= -2976121492199875229L;
	@Id
	@GeneratedValue
	private long					id;
	@OneToMany
	private final List<Employee>	employees			= new ArrayList<Employee>();
	@OneToMany
	private List<Role>				roles				= new ArrayList<Role>();
	private String					name;
	private Integer					upDeptId;
	private String					employeeAmount;
	private Integer					deptOrder;
	private Integer					deptStatus;
	private String					remark;

	public Department() {
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Integer getDeptStatus() {
		return deptStatus;
	}

	public void setDeptStatus(Integer deptStatus) {
		this.deptStatus = deptStatus;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Employee> getEmployees() {
		return employees;
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
