package org.kyerp.domain.qc;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.tyopf.vo.org.Employee;
import com.tyopf.vo.print.AfBase;

/**
 * 产成品检验记录
 */
@Entity
public class InspectionRecordsOfFinshedGoods {
	/** id */
	@Id
	@GeneratedValue
	private long		id;
	/**
	 * 任务单
	 */
	@OneToOne
	private AfBase		afBase;

	/**
	 * 样本数
	 */
	private int			sampleAmount;
	/**
	 * 不合格判定数
	 */
	private int			unqualifiedStandNumber;
	/**
	 * 不合格品数
	 */
	private int			unqualifiedAmount;
	/**
	 * 检验项目01-墨色
	 */
	private String		examItem01;
	/**
	 * 检验项目02-文字印迹
	 */
	private String		examItem02;
	/**
	 * 检验项目03-套印
	 */
	private String		examItem03;
	/**
	 * 检验项目04-外 观
	 */
	private String		examItem04;
	/**
	 * 检验项目05-封面、插页
	 */
	private String		examItem05;
	/**
	 * 检验项目06-书页与书贴
	 */
	private String		examItem06;
	/**
	 * 检验项目07-胶 粘 钉
	 */
	private String		examItem07;
	/**
	 * 检验项目08-铁丝平钉
	 */
	private String		examItem08;
	/**
	 * 检验项目09-骑 马 钉
	 */
	private String		examItem09;
	/**
	 * 检验项目10-成 品
	 */
	private String		examItem10;
	/**
	 * 综合结论
	 */
	private String		examResult;
	/**
	 * 检验员
	 */
	@OneToOne
	private Employee	examEmployee;
	/**
	 * 备注
	 */
	private String		remark;

	public InspectionRecordsOfFinshedGoods() {
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

	public int getSampleAmount() {
		return sampleAmount;
	}

	public void setSampleAmount(int sampleAmount) {
		this.sampleAmount = sampleAmount;
	}

	public int getUnqualifiedStandNumber() {
		return unqualifiedStandNumber;
	}

	public void setUnqualifiedStandNumber(int unqualifiedStandNumber) {
		this.unqualifiedStandNumber = unqualifiedStandNumber;
	}

	public int getUnqualifiedAmount() {
		return unqualifiedAmount;
	}

	public void setUnqualifiedAmount(int unqualifiedAmount) {
		this.unqualifiedAmount = unqualifiedAmount;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
