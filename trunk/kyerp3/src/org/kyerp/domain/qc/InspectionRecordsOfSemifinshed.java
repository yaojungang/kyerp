package org.kyerp.domain.qc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.tyopf.vo.org.Employee;
import com.tyopf.vo.print.AfBase;

/**
 * QYYT-D-8.2.4-02-毛样书记录
 */
@Entity
public class InspectionRecordsOfSemifinshed implements Serializable {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 6059701803708388543L;
	/** id */
	@Id
	@GeneratedValue
	private Long				id;
	/**
	 * 任务单
	 */
	@OneToOne
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
	@OneToMany
	private List<Employee>		operatorsEmployees	= new ArrayList<Employee>();
	/**
	 * 备注
	 */
	private String				remark;

	private InspectionRecordsOfSemifinshed() {
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

	public List<Employee> getOperatorsEmployees() {
		return operatorsEmployees;
	}

	public void setOperatorsEmployees(List<Employee> operatorsEmployees) {
		this.operatorsEmployees = operatorsEmployees;
	}

}
