package org.kyerp.domain.org;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.kyerp.domain.BaseDomain;

@Entity
public class EmployeeResume extends BaseDomain implements java.io.Serializable {

	private static final long	serialVersionUID	= 1778458047462901313L;
	@ManyToOne
	private Employee			employee			= new Employee();

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
