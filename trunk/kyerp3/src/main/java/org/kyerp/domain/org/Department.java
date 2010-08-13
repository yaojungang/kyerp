package org.kyerp.domain.org;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.kyerp.domain.BaseDomain;
import org.kyerp.domain.security.Role;

/**
 * 部门管理
 * */
@Entity
public class Department extends BaseDomain implements Serializable {
	private static final long		serialVersionUID	= -2976121492199875229L;
	/** 名称 */
	private String					name;
	/** 编号 **/
	private String					serialNumber;
	/** 上级部门 **/
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Department				parentDepartment;
	/** 下级部门 **/
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE }, mappedBy = "parentDepartment")
	private final List<Department>	childDepartments	= new ArrayList<Department>();
	/** 下属员工 */
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<Employee>			employees			= new ArrayList<Employee>();
	/** 下属 职位角色 */
	@OneToMany
	private List<Role>				roles				= new ArrayList<Role>();
	/** 排序 */
	private Integer					deptOrder;
	/** 状态 */
	private Boolean					deptStatus;
	/** 备注 */
	private String					remark;

	public Department() {
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Department getParentDepartment() {
		return parentDepartment;
	}

	public void setParentDepartment(Department parentDepartment) {
		this.parentDepartment = parentDepartment;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
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

	public List<Department> getChildDepartments() {
		return childDepartments;
	}

}
