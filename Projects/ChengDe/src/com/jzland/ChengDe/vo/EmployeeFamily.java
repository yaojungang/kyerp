package com.jzland.ChengDe.vo;

public class EmployeeFamily implements java.io.Serializable {
 
	private static final long serialVersionUID = 733919187108234282L;

	private int id;

	// private int eid;
	
	private Employee employee;

	private String name;

	private String relation;

	private String polity;

	private String company;

	private String job;
	
	private Integer familyOrder;

	public EmployeeFamily() {
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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
