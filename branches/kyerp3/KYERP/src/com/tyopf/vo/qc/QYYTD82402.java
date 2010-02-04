package com.tyopf.vo.qc;

import java.util.Date;

import com.tyopf.vo.AfBase;
import com.tyopf.vo.Employee;

/**
 * QYYT-D-8.2.4-02-毛样书记录
 */
public class QYYTD82402 implements java.io.Serializable {
	private static final long	serialVersionUID	= 6059701803708388543L;
	/** id */
	private Long				id;
	/**
	 * 任务单
	 */
	private AfBase				afBase;
	/**
	 * 检查日期
	 */
	private Date				examdate;
	/**
	 * 检查项目(收集 检查合格 检查合格)
	 */
	private String				item;
	/**
	 * 负责人
	 */
	private String				operators;
	/**
	 * 检查人
	 */
	private Employee			examEmployee;
	/**
	 * 备注
	 */
	private String				remark;

	public QYYTD82402() {
	}

	public AfBase getAfBase() {
		return afBase;
	}

	public Date getExamdate() {
		return examdate;
	}

	public void setExamdate(Date examdate) {
		this.examdate = examdate;
	}

	public String getItem() {
		return item;
	}

	public String getRemark() {
		return remark;
	}

	public void setAfBase(AfBase afBase) {
		this.afBase = afBase;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperators() {
		return operators;
	}

	public void setOperators(String operators) {
		this.operators = operators;
	}

	public Employee getExamEmployee() {
		return examEmployee;
	}

	public void setExamEmployee(Employee examEmployee) {
		this.examEmployee = examEmployee;
	}

}
