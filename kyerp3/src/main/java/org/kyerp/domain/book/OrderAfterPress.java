package org.kyerp.domain.book;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 订单:印后项目
 * 
 * @author y109 2009-11-29下午03:56:17
 */
@Entity
public class OrderAfterPress implements Serializable {
	private static final long	serialVersionUID	= -1473710320494145366L;
	@Id
	@GeneratedValue
	private long				id;
	/** 所属订单 */
	@ManyToOne
	private Order				order;
	/** 项目名称：胶订，骑马钉等 */
	private String				itemName;
	/** 项目说明 */
	private String				itemRemark;
	/** 单价 */
	private BigDecimal			price;
	/** 数量 */
	private int					amount;
	/** 金额 */
	private BigDecimal			pay;

	public OrderAfterPress() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public BigDecimal getPay() {
		return pay;
	}

	public void setPay(BigDecimal pay) {
		this.pay = pay;
	}
}
