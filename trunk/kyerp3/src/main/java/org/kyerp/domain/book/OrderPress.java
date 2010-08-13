package org.kyerp.domain.book;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 订单:制版、印刷项目
 * 
 * @author y109 2009-11-29下午03:56:17
 */
@Entity
public class OrderPress implements Serializable {
	private static final long	serialVersionUID	= -1473710320494145366L;
	@Id
	@GeneratedValue
	private long				id;
	/** 所属订单 */
	@ManyToOne
	private Order				order;
	/** 项目名称：封面，正文，插页 */
	private String				itemName;
	/** 项目说明 */
	private String				itemRemark;
	/** 印色：1+1，2+2，4+0 */
	private String				colors;
	/** 专色名 */
	private String				specialColors;
	/** 单价 */
	private BigDecimal			price;
	/** 数量 */
	private int					amount;
	/** 金额 */
	private BigDecimal			pay					= new BigDecimal(0);

	public OrderPress() {
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

	public String getColors() {
		return colors;
	}

	public void setColors(String colors) {
		this.colors = colors;
	}

	public String getSpecialColors() {
		return specialColors;
	}

	public void setSpecialColors(String specialColors) {
		this.specialColors = specialColors;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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
