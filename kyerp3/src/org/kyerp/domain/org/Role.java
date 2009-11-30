package org.kyerp.domain.org;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Role implements Serializable {
	/**
	 * 
	 */
	private static final long		serialVersionUID	= -1090016624109826363L;
	@Id
	@GeneratedValue
	private int						id;
	private String					rname;
	@ManyToOne
	private Department				companyDept;
	@OneToMany
	private List<User>				users				= new ArrayList<User>();
	@OneToMany
	private List<SystemFunctions>	systemFunctions		= new ArrayList<SystemFunctions>();
	private String					rdescribe;
	private String					rremark;

	public Role() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public Department getCompanyDept() {
		return companyDept;
	}

	public void setCompanyDept(Department companyDept) {
		this.companyDept = companyDept;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<SystemFunctions> getSystemFunctions() {
		return systemFunctions;
	}

	public void setSystemFunctions(List<SystemFunctions> systemFunctions) {
		this.systemFunctions = systemFunctions;
	}

	public String getRdescribe() {
		return rdescribe;
	}

	public void setRdescribe(String rdescribe) {
		this.rdescribe = rdescribe;
	}

	public String getRremark() {
		return rremark;
	}

	public void setRremark(String rremark) {
		this.rremark = rremark;
	}

}
