package com.tyopf.vo.qc;

import com.tyopf.vo.AfBase;
import com.tyopf.vo.Employee;

/**
 * 装订成品检查记录
 */
public class InspectionRecordsOfBindingfinshed {

	/** id */
	private long		id;
	/**
	 * 任务单
	 */
	private AfBase		afBase;

	/**
	 * 正品数
	 */
	private int			totalAmount			= 0;
	/**
	 * 不合格数
	 */
	private int			unqualifiedAmount	= 0;

	/**
	 * 检验结果01-毛样书
	 */
	private String		examItem01;
	/**
	 * 检验结果02-折页
	 */
	private String		examItem02;
	/**
	 * 检验结果03-配页
	 */
	private String		examItem03;
	/**
	 * 检验结果04-胶订
	 */
	private String		examItem04;
	/**
	 * 检验结果05-铁丝平订
	 */
	private String		examItem05;
	/**
	 * 检验结果06-骑马钉
	 */
	private String		examItem06;
	/**
	 * 检验结果07-锁线订
	 */
	private String		examItem07;
	/**
	 * 检验结果08-粘页机
	 */
	private String		examItem08;
	/**
	 * 检验结果09-三面切书切纸机
	 */
	private String		examItem09;
	/**
	 * 检验结果10-成品
	 */
	private String		examItem10;
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

	public void setAfBase(AfBase afBase) {
		this.afBase = afBase;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getExamItem09() {
		return examItem09;
	}

	public void setExamItem09(String examItem09) {
		this.examItem09 = examItem09;
	}

	public String getExamItem10() {
		return examItem10;
	}

	public void setExamItem10(String examItem10) {
		this.examItem10 = examItem10;
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

	public int getUnqualifiedAmount() {
		return unqualifiedAmount;
	}

	public void setUnqualifiedAmount(int unqualifiedAmount) {
		this.unqualifiedAmount = unqualifiedAmount;
	}
}
