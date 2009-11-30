package org.kyerp.domain.qc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.tyopf.vo.org.Employee;
import com.tyopf.vo.print.AfBase;

/**
 * 单色印刷品巡检记录
 */
@Entity
public class InspectionRecordsOfPatrolAndExamineForSingletonPresswork implements
		Serializable {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5116901317180150169L;
	/** id */
	@Id
	@GeneratedValue
	private long				id;
	/**
	 * 任务单
	 */
	@OneToOne
	private AfBase				afBase;

	/**
	 * 操作员
	 */
	@Transient
	private List<Employee>		operatorEmployees	= new ArrayList<Employee>();
	/**
	 * 日期
	 */
	private Date				finishDate;
	/**
	 * 数量
	 */
	private int					amount;
	/**
	 * 巡检时间
	 */
	private Date				examDate;
	/**
	 * 检验项目01-正反套印： 合格 不合格
	 */
	private String				examItem01;
	/**
	 * 检验项目02-墨色： 合格 不 合格
	 */
	private String				examItem02;
	/**
	 * 检验项目03-外观： 合格 不 合格
	 */
	private String				examItem03;
	/**
	 * 检验项目04-文字： 合格 不 合格
	 */
	private String				examItem04;
	/**
	 * 检验项目05-网点： 合格 不 合格
	 */
	private String				examItem05;
	/**
	 * 结论： 合格 不 合格
	 */
	private String				examResult;

	/**
	 * 检验员
	 */
	@OneToOne
	private Employee			examEmployee;

	/**
	 * 不合格说明及处置
	 */
	private String				remark;

	public InspectionRecordsOfPatrolAndExamineForSingletonPresswork() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Employee> getOperatorEmployees() {
		return operatorEmployees;
	}

	public void setOperatorEmployees(List<Employee> operatorEmployees) {
		this.operatorEmployees = operatorEmployees;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public AfBase getAfBase() {
		return afBase;
	}

	public void setAfBase(AfBase afBase) {
		this.afBase = afBase;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getExamItem01() {
		return examItem01;
	}

	public void setExamItem01(String examItem01) {
		this.examItem01 = examItem01;
	}

	public String getExamItem02() {
		return examItem02;
	}

	public void setExamItem02(String examItem02) {
		this.examItem02 = examItem02;
	}

	public String getExamItem03() {
		return examItem03;
	}

	public void setExamItem03(String examItem03) {
		this.examItem03 = examItem03;
	}

	public String getExamItem04() {
		return examItem04;
	}

	public void setExamItem04(String examItem04) {
		this.examItem04 = examItem04;
	}

	public String getExamItem05() {
		return examItem05;
	}

	public void setExamItem05(String examItem05) {
		this.examItem05 = examItem05;
	}

	public String getExamResult() {
		return examResult;
	}

	public void setExamResult(String examResult) {
		this.examResult = examResult;
	}

	public Employee getExamEmployee() {
		return examEmployee;
	}

	public void setExamEmployee(Employee examEmployee) {
		this.examEmployee = examEmployee;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
