package org.kyerp.domain.org;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class EmployeeResume implements java.io.Serializable {

	private static final long	serialVersionUID	= 1778458047462901313L;
	@Id
	@GeneratedValue
	private long				id;
	@ManyToOne
	private Employee			employee;

	private Date				startTime;

	private Date				endTime;

	private String				company;

	private String				job;

	private Integer				resumeOrder;

	public EmployeeResume() {
	}

	public Integer getResumeOrder() {
		return resumeOrder;
	}

	public void setResumeOrder(Integer resumeOrder) {
		this.resumeOrder = resumeOrder;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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
