package org.kyerp.domain.book;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 订单项目：其它
 * 
 * @author y109 2009-11-29下午03:56:17
 */
@Entity
public class OrderOther implements Serializable {
	private static final long	serialVersionUID	= -1473710320494145366L;
	@Id
	@GeneratedValue
	private long				id;
	/** 所属订单 */
	@ManyToOne
	private Order				order;
	/** 项目名称 */
	private String				itemName;
	/** 项目说明 */
	private String				itemRemark;

	/** 金额 */
	private BigDecimal			pay					= new BigDecimal(0);

	public OrderOther() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemRemark() {
		return itemRemark;
	}

	public void setItemRemark(String itemRemark) {
		this.itemRemark = itemRemark;
	}

	public BigDecimal getPay() {
		return pay;
	}

	public void setPay(BigDecimal pay) {
		this.pay = pay;
	}

}
