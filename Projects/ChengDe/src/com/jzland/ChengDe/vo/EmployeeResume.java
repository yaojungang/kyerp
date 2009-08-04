package com.jzland.ChengDe.vo;

import java.util.Date;


public class EmployeeResume implements java.io.Serializable {

	private static final long serialVersionUID = 1778458047462901313L;

	private int id;
	
	// private int eid;
	
	private Employee employee;

	private Date startTime;

	private Date endTime;

	private String company;

	private String job;
	
	private Integer resumeOrder;

	public EmployeeResume() {
	}

	public Employee getEmployee() {
		return employee;
	}

	public Integer getResumeOrder() {
		return resumeOrder;
	}

	public void setResumeOrder(Integer resumeOrder) {
		this.resumeOrder = resumeOrder;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
