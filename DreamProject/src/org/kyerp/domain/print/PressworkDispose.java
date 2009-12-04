package org.kyerp.domain.print;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author y109 2009-11-23上午11:59:19
 */
@Entity
public class PressworkDispose implements java.io.Serializable {
	/**
	 * 特殊工艺项目表
	 */
	private static final long	serialVersionUID	= 5411241021191011367L;
	@Id
	@GeneratedValue
	private long				id;
	/** 所属任务单 */
	@ManyToOne
	@JoinColumn(name = "presswork_id")
	private Presswork			presswork;

	private Long				amount;

	private String				type;

	private String				itemName;

	private Date				date;

	private String				afDFactory;

	private String				remark;

	public PressworkDispose() {
	}

	public String getAfDFactory() {
		return afDFactory;
	}

	public Long getAmount() {
		return amount;
	}

	public Date getDate() {
		return date;
	}

	public long getId() {
		return id;
	}

	public String getItemName() {
		return itemName;
	}

	public Presswork getPresswork() {
		return presswork;
	}

	public String getRemark() {
		return remark;
	}

	public String getType() {
		return type;
	}

	public void setAfDFactory(String afDFactory) {
		this.afDFactory = afDFactory;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setPresswork(Presswork presswork) {
		this.presswork = presswork;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setType(String type) {
		this.type = type;
	}

}
