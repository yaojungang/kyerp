package com.tyopf.vo.qc;

import java.util.Date;

import com.tyopf.vo.AfBase;
import com.tyopf.vo.Employee;

/**
 * QYYT-D-8.2.4-09-（单色）印刷品巡检记录
 */
public class QYYTD82409 {
	/** id */
	private long		id;
	/**
	 * 任务单
	 */
	private AfBase		afBase;

	/**
	 * 操作员
	 */
	private String		operators;
	/**
	 * 日期
	 */
	private Date		finishDate;
	/**
	 * 数量
	 */
	private int			amount;
	/**
	 * 巡检时间
	 */
	private Date		examDate;
	/**
	 * 检验项目01-正反套印： 合格 不合格
	 */
	private String		examItem01;
	/**
	 * 检验项目02-墨色： 合格 不 合格
	 */
	private String		examItem02;
	/**
	 * 检验项目03-外观： 合格 不 合格
	 */
	private String		examItem03;
	/**
	 * 检验项目04-文字： 合格 不 合格
	 */
	private String		examItem04;
	/**
	 * 检验项目05-网点： 合格 不 合格
	 */
	private String		examItem05;
	/**
	 * 结论： 合格 不 合格
	 */
	private String		examResult;

	/**
	 * 检验员
	 */
	private Employee	examEmployee;

	/**
	 * 不合格说明及处置
	 */
	private String		remark;

	public QYYTD82409() {
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

	public String getOperators() {
		return operators;
	}

	public void setOperators(String operators) {
		this.operators = operators;
	}

}
