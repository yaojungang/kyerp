package com.tyopf.vo.qc;

import java.util.Date;

import com.tyopf.vo.AfBase;
import com.tyopf.vo.Employee;

/**
 * QYYT-D-8.2.4-03-印刷成品检验记录
 */
public class QYYTD82403 implements java.io.Serializable {
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
	/** 代数 */
	private String				daiShu;
	/** 下机数 */
	private Integer				xiajishu;
	/** 合格数 */
	private Integer				qualifiedAmount;
	/** 不合格数 */
	private Integer				unqualifiedAmount;
	/** 双张 */
	private Integer				b1;
	/** 规矩 */
	private Integer				b2;
	/** 带脏 */
	private Integer				b3;
	/** 重影 */
	private Integer				b4;
	/** 打褶 */
	private Integer				b5;
	/** 套印 */
	private Integer				b6;
	/** 掉网 */
	private Integer				b7;
	/** 颜色 */
	private Integer				b8;
	/** 其它 */
	private Integer				b9;
	/**
	 * 操作员
	 */
	private String				operators;
	/**
	 * 检验员
	 */
	private Employee			examEmployee;
	/**
	 * 备注
	 */
	private String				remark;

	public QYYTD82403() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AfBase getAfBase() {
		return afBase;
	}

	public void setAfBase(AfBase afBase) {
		this.afBase = afBase;
	}

	public Date getExamdate() {
		return examdate;
	}

	public void setExamdate(Date examdate) {
		this.examdate = examdate;
	}

	public String getDaiShu() {
		return daiShu;
	}

	public void setDaiShu(String daiShu) {
		this.daiShu = daiShu;
	}

	public Integer getXiajishu() {
		return xiajishu;
	}

	public void setXiajishu(Integer xiajishu) {
		this.xiajishu = xiajishu;
	}

	public Integer getQualifiedAmount() {
		return qualifiedAmount;
	}

	public void setQualifiedAmount(Integer qualifiedAmount) {
		this.qualifiedAmount = qualifiedAmount;
	}

	public Integer getUnqualifiedAmount() {
		return unqualifiedAmount;
	}

	public void setUnqualifiedAmount(Integer unqualifiedAmount) {
		this.unqualifiedAmount = unqualifiedAmount;
	}

	public Integer getB1() {
		return b1;
	}

	public void setB1(Integer b1) {
		this.b1 = b1;
	}

	public Integer getB2() {
		return b2;
	}

	public void setB2(Integer b2) {
		this.b2 = b2;
	}

	public Integer getB3() {
		return b3;
	}

	public void setB3(Integer b3) {
		this.b3 = b3;
	}

	public Integer getB4() {
		return b4;
	}

	public void setB4(Integer b4) {
		this.b4 = b4;
	}

	public Integer getB5() {
		return b5;
	}

	public void setB5(Integer b5) {
		this.b5 = b5;
	}

	public Integer getB6() {
		return b6;
	}

	public void setB6(Integer b6) {
		this.b6 = b6;
	}

	public Integer getB7() {
		return b7;
	}

	public void setB7(Integer b7) {
		this.b7 = b7;
	}

	public Integer getB8() {
		return b8;
	}

	public void setB8(Integer b8) {
		this.b8 = b8;
	}

	public Integer getB9() {
		return b9;
	}

	public void setB9(Integer b9) {
		this.b9 = b9;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
