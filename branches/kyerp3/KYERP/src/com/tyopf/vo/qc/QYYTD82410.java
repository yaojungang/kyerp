package com.tyopf.vo.qc;

import com.tyopf.vo.AfBase;
import com.tyopf.vo.Employee;

/**
 * QYYT-D-8.2.4-10-装订产品巡检记录
 */
public class QYYTD82410 {

	/** id */
	private long		id;
	/**
	 * 任务单
	 */
	private AfBase		afBase;
	/**
	 * 检验结果01-配页
	 */
	private String		examItem01;
	/**
	 * 检验结果02-折页
	 */
	private String		examItem02;
	/**
	 * 检验结果03-骑马钉
	 */
	private String		examItem03;
	/**
	 * 检验结果04-胶订
	 */
	private String		examItem04;
	/**
	 * 检验结果05-裁切成品
	 */
	private String		examItem05;

	/**
	 * 综合结论
	 */
	private String		examResult;
	/**
	 * 检验员
	 */
	private Employee	examEmployee;
	/**
	 * 不合格品的说明及处理
	 */
	private String		remark;

	public AfBase getAfBase() {
		return afBase;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public void setAfBase(AfBase afBase) {
		this.afBase = afBase;
	}

}
