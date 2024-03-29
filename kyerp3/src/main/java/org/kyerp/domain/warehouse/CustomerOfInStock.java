package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.kyerp.domain.crm.Client;
import org.kyerp.domain.org.Employee;

/**
 * 客户自备料入库单
 * 
 * @author y109 2009-11-29下午11:24:25
 */
@Entity
@DiscriminatorValue("customer")
public class CustomerOfInStock extends InStock implements Serializable{
	private static final long	serialVersionUID	= 1L;
	/** 客户自备料入库单填单时间 */
	private Date				inputTime;
	/** 客户 */
	@ManyToOne
	private Client				customer;
	/** 收货人 */
	@ManyToOne
	private Employee			taker;
	/** 客户自备料入库单填写人 */
	@ManyToOne
	private Employee			inputMan;

	public CustomerOfInStock() {
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public Client getCustomer() {
		return customer;
	}

	public void setCustomer(Client customer) {
		this.customer = customer;
	}

	public Employee getTaker() {
		return taker;
	}

	public void setTaker(Employee taker) {
		this.taker = taker;
	}

	public Employee getInputMan() {
		return inputMan;
	}

	public void setInputMan(Employee inputMan) {
		this.inputMan = inputMan;
	}

}
