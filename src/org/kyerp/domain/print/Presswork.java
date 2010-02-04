package org.kyerp.domain.print;

/**
 * 生产任务单 *
 * */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.kyerp.domain.BaseDomain;
import org.kyerp.domain.book.Order;
import org.kyerp.domain.crm.Contact;
import org.kyerp.domain.crm.Customer;
import org.kyerp.domain.org.Employee;

@Entity
public class Presswork extends BaseDomain implements Serializable {
	private static final long				serialVersionUID		= -7784970638877997638L;
	/** 所属订单 */
	@OneToOne
	private Order							order;
	/** 任务单号 */
	private String							pressworkNo;
	/** 任务名称 */
	private String							name;
	/** 开纸张项目 */
	@OneToMany(mappedBy = "presswork")
	private List<PressworkPaper>			pressworkPapers;
	/** 制版印刷项目 */
	@OneToMany(mappedBy = "presswork")
	private final List<PressworkPress>		pressworkPresses		= new ArrayList<PressworkPress>();
	/** 印后项目 */
	@OneToMany(mappedBy = "presswork")
	private final List<PressworkAfterPress>	pressworkAfterPresses	= new ArrayList<PressworkAfterPress>();
	/** 客户 */
	@ManyToOne
	private Customer						customer;
	/** 联系人 */
	@ManyToOne
	private Contact							contact;
	/** 尺寸名称：如8开，32开 */
	private String							sizeType;
	/** 成品尺寸 */
	private String							sizeString;
	/** 数量 */
	private int								amount;
	/** 单位 */
	private String							module;
	/** 看/送样日期 */
	private Date							sampleDate;
	/** 完成日期 */
	private Date							finishDate;
	/** 打包方式 */
	private String							packageType;
	/** 每包数量 */
	private int								packageEveryAmount;
	/** 运送方式 */
	private String							deliverWay;
	/** 查看次数 */
	private final int						viewTimes				= 0;
	/** 开纸说明 */
	private String							paperRemark;
	/** 制版说明 */
	private String							prePressRemark;
	/** 印刷说明 */
	private String							pressRemark;
	/** 开单人 */
	@OneToOne
	private Employee						createPressworkEmployee;
	/** 业务员 */
	@OneToOne
	private Employee						pressworkEmployee;
	/** 审核人 */
	@OneToOne
	private Employee						auditingEmployee;
	/** 审核时间 */
	private Date							auditingTime;
	/** 备注 */
	private String							remark;

	public Presswork() {
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getPressworkNo() {
		return pressworkNo;
	}

	public void setPressworkNo(String pressworkNo) {
		this.pressworkNo = pressworkNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PressworkPaper> getPressworkPapers() {
		return pressworkPapers;
	}

	public void setPressworkPapers(List<PressworkPaper> pressworkPapers) {
		this.pressworkPapers = pressworkPapers;
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

	public String getSizeType() {
		return sizeType;
	}

	public void setSizeType(String sizeType) {
		this.sizeType = sizeType;
	}

	public String getSizeString() {
		return sizeString;
	}

	public void setSizeString(String sizeString) {
		this.sizeString = sizeString;
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

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public int getPackageEveryAmount() {
		return packageEveryAmount;
	}

	public void setPackageEveryAmount(int packageEveryAmount) {
		this.packageEveryAmount = packageEveryAmount;
	}

	public String getDeliverWay() {
		return deliverWay;
	}

	public void setDeliverWay(String deliverWay) {
		this.deliverWay = deliverWay;
	}

	public String getPaperRemark() {
		return paperRemark;
	}

	public void setPaperRemark(String paperRemark) {
		this.paperRemark = paperRemark;
	}

	public String getPrePressRemark() {
		return prePressRemark;
	}

	public void setPrePressRemark(String prePressRemark) {
		this.prePressRemark = prePressRemark;
	}

	public String getPressRemark() {
		return pressRemark;
	}

	public void setPressRemark(String pressRemark) {
		this.pressRemark = pressRemark;
	}

	public Employee getCreatePressworkEmployee() {
		return createPressworkEmployee;
	}

	public void setCreatePressworkEmployee(Employee createPressworkEmployee) {
		this.createPressworkEmployee = createPressworkEmployee;
	}

	public Employee getPressworkEmployee() {
		return pressworkEmployee;
	}

	public void setPressworkEmployee(Employee pressworkEmployee) {
		this.pressworkEmployee = pressworkEmployee;
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

	public List<PressworkPress> getPressworkPresses() {
		return pressworkPresses;
	}

	public List<PressworkAfterPress> getPressworkAfterPresses() {
		return pressworkAfterPresses;
	}

	public int getViewTimes() {
		return viewTimes;
	}

}
