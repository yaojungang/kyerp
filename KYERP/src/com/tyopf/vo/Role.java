package com.tyopf.vo;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class Role implements java.io.Serializable {

	private int id;
	private String rname;
	private CompanyDept companyDept;
	private Set<User> users = new HashSet<User>();
	private Set<SystemFunctions> systemFunctions = new HashSet<SystemFunctions>();
	private String rdescribe;
	private String rremark;

	public Role() {
	}

	public int getId() {
		return id;
	}


	public Set<SystemFunctions> getSystemFunctions() {
		return systemFunctions;
	}

	public void setSystemFunctions(Set<SystemFunctions> systemFunctions) {
		this.systemFunctions = systemFunctions;
	}

	public CompanyDept getCompanyDept() {
		return companyDept;
	}

	public void setCompanyDept(CompanyDept companyDept) {
		this.companyDept = companyDept;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRdescribe() {
		return rdescribe;
	}

	public void setRdescribe(String rdescribe) {
		this.rdescribe = rdescribe;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getRremark() {
		return rremark;
	}

	public void setRremark(String rremark) {
		this.rremark = rremark;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
