package org.kyerp.domain.book;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.kyerp.domain.base.PaymentWay;
import org.kyerp.domain.crm.Contact;
import org.kyerp.domain.crm.Customer;
import org.kyerp.domain.org.Employee;

/**
 * 订单
 * 
 * @author y109 2009-11-29下午03:05:09
 */
@Entity
@Table(name = "orders")
public class Order implements Serializable {
	private static final long		serialVersionUID		= 1L;
	/** ID */
	@Id
	@GeneratedValue
	private long					id;
	/** 订单号 */
	private String					orderNo;
	/** 订单名称 */
	private String					name;
	/** 纸张项目 */
	@OneToMany(mappedBy = "order")
	private List<OrderPaper>		papers;
	/** 纸张小计 */
	private final BigDecimal		paperStandPay			= new BigDecimal(0);
	/** 纸张附加费 */
	private final BigDecimal		paperAddFee				= new BigDecimal(0);
	/** 纸张费用合计 */
	private final BigDecimal		paperPay				= new BigDecimal(0);

	/** 印前项目 */
	@OneToMany(mappedBy = "order")
	private List<OrderPrePress>		prePresses;
	/** 印前小计 */
	private final BigDecimal		perPressStandPay		= new BigDecimal(0);
	/** 印前附加费 */
	private final BigDecimal		perPressAddFee			= new BigDecimal(0);
	/** 印前费用合计 */
	private final BigDecimal		perPressPay				= new BigDecimal(0);

	/** 制版印刷项目 */
	@OneToMany(mappedBy = "order")
	private List<OrderPress>		presses;
	/** 制版印刷小计 */
	private final BigDecimal		pressesStandPay			= new BigDecimal(0);
	/** 制版印刷附加费 */
	private final BigDecimal		pressesAddFee			= new BigDecimal(0);
	/** 印前费用合计 */
	private final BigDecimal		pressPay				= new BigDecimal(0);

	/** 印后项目 */
	@OneToMany(mappedBy = "order")
	private List<OrderAfterPress>	afterPresses;
	/** 印后小计 */
	private final BigDecimal		afterPressesStandPay	= new BigDecimal(0);
	/** 印后附加费 */
	private final BigDecimal		afterPressesAddFee		= new BigDecimal(0);
	/** 印后费用合计 */
	private final BigDecimal		afterPressesPay			= new BigDecimal(0);

	/** 其它项目 */
	@OneToMany(mappedBy = "order")
	private List<OrderOther>		others;
	/** 其它费用合计 */
	private BigDecimal				othersPay				= new BigDecimal(0);

	/** 尺寸名称：如8开，32开 */
	private String					sizeType;
	/** 成品尺寸 */
	private String					sizeString;
	/** 数量 */
	private int						amount;
	/** 单位 */
	private String					module;
	/** 看/送样日期 */
	private Date					sampleDate;
	/** 完成日期 */
	private Date					finishDate;
	/** 客户 */
	@ManyToOne
	private Customer				customer;
	/** 联系人 */
	@ManyToOne
	private Contact					contact;
	/** 订单创建时间 */
	private Date					createTime				= new Date();
	/** 订单修改时间 */
	private Date					updateTime				= new Date();
	/** 订单状态 */
	private OrderState				state;
	/** 订单总金额 */
	private final BigDecimal		totalPrice				= new BigDecimal(0);
	/** 应付款(实际需要支付的费用) */
	private final BigDecimal		payablefee				= new BigDecimal(0);
	/** 配送费 */
	private final BigDecimal		deliverFee				= new BigDecimal(0);
	/** 顾客附言 */
	private String					note;
	/** 支付方式 */
	private PaymentWay				paymentWay;
	/** 支付状态 */
	private final Boolean			paymentstate			= false;
	/** 开单人 */
	@OneToOne
	private Employee				createOrderEmployee;
	/** 业务员 */
	@OneToOne
	private Employee				orderEmployee;
	/** 审核人 */
	@OneToOne
	private Employee				auditingEmployee;
	/** 审核时间 */
	private Date					auditingTime;
	/** 备注 */
	private String					remark;

	public Order() {
	}

	/** 在对象新建前保存建立时间 */
	@PrePersist
	public void prePersist() {
		this.createTime = new Date();
	}

	/** 在对象更新前保存修改时间 */
	@PreUpdate
	void preUpdate() {
		this.updateTime = new Date();
	}

	public String getSizeString() {
		return sizeString;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setSizeString(String sizeString) {
		this.sizeString = sizeString;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<OrderPaper> getPapers() {
		return papers;
	}

	public void setPapers(List<OrderPaper> papers) {
		this.papers = papers;
	}

	public List<OrderPrePress> getPrePresses() {
		return prePresses;
	}

	public void setPrePresses(List<OrderPrePress> prePresses) {
		this.prePresses = prePresses;
	}

	public List<OrderPress> getPresses() {
		return presses;
	}

	public void setPresses(List<OrderPress> presses) {
		this.presses = presses;
	}

	public List<OrderAfterPress> getAfterPresses() {
		return afterPresses;
	}

	public void setAfterPresses(List<OrderAfterPress> afterPresses) {
		this.afterPresses = afterPresses;
	}

	public List<OrderOther> getOthers() {
		return others;
	}

	public void setOthers(List<OrderOther> others) {
		this.others = others;
	}

	public BigDecimal getOthersPay() {
		return othersPay;
	}

	public void setOthersPay(BigDecimal othersPay) {
		this.othersPay = othersPay;
	}

	public String getSizeType() {
		return sizeType;
	}

	public void setSizeType(String sizeType) {
		this.sizeType = sizeType;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Date getSampleDate() {
		return sampleDate;
	}

	public void setSampleDate(Date sampleDate) {
		this.sampleDate = sampleDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public PaymentWay getPaymentWay() {
		return paymentWay;
	}

	public void setPaymentWay(PaymentWay paymentWay) {
		this.paymentWay = paymentWay;
	}

	public Employee getCreateOrderEmployee() {
		return createOrderEmployee;
	}

	public void setCreateOrderEmployee(Employee createOrderEmployee) {
		this.createOrderEmployee = createOrderEmployee;
	}

	public Employee getOrderEmployee() {
		return orderEmployee;
	}

	public void setOrderEmployee(Employee orderEmployee) {
		this.orderEmployee = orderEmployee;
	}

	public Employee getAuditingEmployee() {
		return auditingEmployee;
	}

	public void setAuditingEmployee(Employee auditingEmployee) {
		this.auditingEmployee = auditingEmployee;
	}

	public Date getAuditingTime() {
		return auditingTime;
	}

	public void setAuditingTime(Date auditingTime) {
		this.auditingTime = auditingTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getPaperStandPay() {
		return paperStandPay;
	}

	public BigDecimal getPaperAddFee() {
		return paperAddFee;
	}

	public BigDecimal getPaperPay() {
		return paperPay;
	}

	public BigDecimal getPerPressStandPay() {
		return perPressStandPay;
	}

	public BigDecimal getPerPressAddFee() {
		return perPressAddFee;
	}

	public BigDecimal getPerPressPay() {
		return perPressPay;
	}

	public BigDecimal getPressesStandPay() {
		return pressesStandPay;
	}

	public BigDecimal getPressesAddFee() {
		return pressesAddFee;
	}

	public BigDecimal getPressPay() {
		return pressPay;
	}

	public BigDecimal getAfterPressesStandPay() {
		return afterPressesStandPay;
	}

	public BigDecimal getAfterPressesAddFee() {
		return afterPressesAddFee;
	}

	public BigDecimal getAfterPressesPay() {
		return afterPressesPay;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public BigDecimal getPayablefee() {
		return payablefee;
	}

	public BigDecimal getDeliverFee() {
		return deliverFee;
	}

	public Boolean getPaymentstate() {
		return paymentstate;
	}

}
