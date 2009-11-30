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
 * 彩色印刷品巡检记录
 */
@Entity
public class InspectionRecordsOfPatrolAndExamineForMulticolorPresswork
		implements Serializable {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 4120032957788650041L;
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
	 * 检验项目01-亮调： 合格 不合格
	 */
	private String				examItem01;
	/**
	 * 检验项目02-层次： 合格 不 合格
	 */
	private String				examItem02;
	/**
	 * 检验项目03-套印： 合格 不 合格
	 */
	private String				examItem03;
	/**
	 * 检验项目04-网点： 合格 不 合格
	 */
	private String				examItem04;
	/**
	 * 检验项目05-颜色： 合格 不 合格
	 */
	private String				examItem05;
	/**
	 * 检验项目06-版面： 合格 不 合格
	 */
	private String				examItem06;
	/**
	 * 检验项目07-接版： 合格 不 合格
	 */
	private String				examItem07;
	/**
	 * 检验项目08-图文： 合格 不 合格
	 */
	private String				examItem08;
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

	public InspectionRecordsOfPatrolAndExamineForMulticolorPresswork() {
	}

	public AfBase getAfBase() {
		return afBase;
	}

	public void setAfBase(AfBase afBase) {
		this.afBase = afBase;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getExamItem06() {
		return examItem06;
	}

	public void setExamItem06(String examItem06) {
		this.examItem06 = examItem06;
	}

	public String getExamItem07() {
		return examItem07;
	}

	public void setExamItem07(String examItem07) {
		this.examItem07 = examItem07;
	}

	public String getExamItem08() {
		return examItem08;
	}

	public void setExamItem08(String examItem08) {
		this.examItem08 = examItem08;
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

	public List<Employee> getOperatorEmployees() {
		return operatorEmployees;
	}

	public void setOperatorEmployees(List<Employee> operatorEmployees) {
		this.operatorEmployees = operatorEmployees;
	}

}
