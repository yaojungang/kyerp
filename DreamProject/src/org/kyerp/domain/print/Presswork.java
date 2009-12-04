/**
 * 
 */
package org.kyerp.domain.print;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.kyerp.domain.crm.Contact;
import org.kyerp.domain.crm.Customer;
import org.kyerp.domain.org.Employee;

/**
 * @author y109
 * 
 */
@Entity
public class Presswork implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long		serialVersionUID	= 1L;
	@Id
	@GeneratedValue
	private long					id;
	private String					name;
	/** 常规工艺项目 */
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "pressworkElement_id")
	private List<PressworkElement>	pressworkElements	= new ArrayList<PressworkElement>();
	/** 特殊工艺项目 */
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "pressworkDispose_id")
	private List<PressworkDispose>	pressworkDisposes	= new ArrayList<PressworkDispose>();
	/** 开单日期 */
	private Date					createTime;
	/** 更新日期 */
	private Date					updateTime			= new Date();
	/** 任务单锁定 */
	private Boolean					locked				= false;

	/** 客户 */
	@ManyToOne
	private Customer				customer;
	@ManyToOne
	private Contact					contact;
	/** 印数 */
	private Integer					amount;

	/** 计价员 */
	@ManyToOne
	private Employee				moneyInputMan;
	/** 计价时间 */
	private Date					moneyInputTime;
	/** 计价备注 */
	private String					moneyInputRemark;

	/** 应收金额 */
	private BigDecimal				moneyShould;

	/** 收款人 */
	@ManyToOne
	private Employee				moneyGetMan;
	/** 收款时间 */
	private Date					moneyGetTime;

	/** 收款备注 */
	private String					moneyGetRemark;

	/** 交款人姓名 */
	private String					moneyGiveMan;
	/** 实收金额 */
	private BigDecimal				moneyFact;

	/** 结款状态 */
	private Boolean					moneyStatus;

	/** 任务单查看次数 */
	private Integer					viewTimes			= 1;

	public Integer getAmount() {
		return amount;
	}

	public Contact getContact() {
		return contact;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Customer getCustomer() {
		return customer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public Boolean getLocked() {
		return locked;
	}

	public BigDecimal getMoneyFact() {
		return moneyFact;
	}

	public Employee getMoneyGetMan() {
		return moneyGetMan;
	}

	public String getMoneyGetRemark() {
		return moneyGetRemark;
	}

	public Date getMoneyGetTime() {
		return moneyGetTime;
	}

	public String getMoneyGiveMan() {
		return moneyGiveMan;
	}

	public Employee getMoneyInputMan() {
		return moneyInputMan;
	}

	public String getMoneyInputRemark() {
		return moneyInputRemark;
	}

	public Date getMoneyInputTime() {
		return moneyInputTime;
	}

	public BigDecimal getMoneyShould() {
		return moneyShould;
	}

	public Boolean getMoneyStatus() {
		return moneyStatus;
	}

	public List<PressworkDispose> getPressworkDisposes() {
		return pressworkDisposes;
	}

	public List<PressworkElement> getPressworkElements() {
		return pressworkElements;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public Integer getViewTimes() {
		return viewTimes;
	}

	/** persist前自动设置cresteTime */
	@PrePersist
	public void perPersist() {
		this.createTime = new Date();
		this.updateTime = new Date();
	}

	/** update前自动设置updateTime */
	@PreUpdate
	public void preUpdate() {
		this.updateTime = new Date();
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public void setMoneyFact(BigDecimal moneyFact) {
		this.moneyFact = moneyFact;
	}

	public void setMoneyGetMan(Employee moneyGetMan) {
		this.moneyGetMan = moneyGetMan;
	}

	public void setMoneyGetRemark(String moneyGetRemark) {
		this.moneyGetRemark = moneyGetRemark;
	}

	public void setMoneyGetTime(Date moneyGetTime) {
		this.moneyGetTime = moneyGetTime;
	}

	public void setMoneyGiveMan(String moneyGiveMan) {
		this.moneyGiveMan = moneyGiveMan;
	}

	public void setMoneyInputMan(Employee moneyInputMan) {
		this.moneyInputMan = moneyInputMan;
	}

	public void setMoneyInputRemark(String moneyInputRemark) {
		this.moneyInputRemark = moneyInputRemark;
	}

	public void setMoneyInputTime(Date moneyInputTime) {
		this.moneyInputTime = moneyInputTime;
	}

	public void setMoneyShould(BigDecimal moneyShould) {
		this.moneyShould = moneyShould;
	}

	public void setMoneyStatus(Boolean moneyStatus) {
		this.moneyStatus = moneyStatus;
	}

	public void setPressworkDisposes(List<PressworkDispose> pressworkDisposes) {
		this.pressworkDisposes = pressworkDisposes;
	}

	public void setPressworkElements(List<PressworkElement> pressworkElements) {
		this.pressworkElements = pressworkElements;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setViewTimes(Integer viewTimes) {
		this.viewTimes = viewTimes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Presswork [id=").append(id).append(", name=").append(
				name).append("]");
		return builder.toString();
	}

}
