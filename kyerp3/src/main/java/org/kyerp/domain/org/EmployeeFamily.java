package org.kyerp.domain.org;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.kyerp.domain.BaseDomain;

@Entity
public class EmployeeFamily extends BaseDomain implements java.io.Serializable {

	private static final long	serialVersionUID	= 733919187108234282L;
	@ManyToOne
	private Employee			employee			= new Employee();

	private String				name;

	private String				relation;

	private String				polity;

	private String				company;

	private String				job;

	private Integer				familyOrder;

	public EmployeeFamily() {
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getFamilyOrder() {
		return familyOrder;
	}

	public void setFamilyOrder(Integer familyOrder) {
		this.familyOrder = familyOrder;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelation() {
		return this.relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getPolity() {
		return this.polity;
	}

	public void setPolity(String polity) {
		this.polity = polity;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

}
