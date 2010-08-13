package org.kyerp.domain.book;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.kyerp.domain.common.MaterialFrom;


/**
 * 订单：用纸
 * 
 * @author y109 2009-11-29下午03:56:17
 */
@Entity
public class OrderPaper implements Serializable {
	private static final long	serialVersionUID	= -1473710320494145366L;
	@Id
	@GeneratedValue
	private long				id;
	/** 所属订单 */
	@ManyToOne
	private Order				order;
	/** 项目名称：封面，正文，插页 */
	private String				itemName;
	/** 纸张：名称 */
	private String				paperName;
	/** 纸张：来源 */
	private MaterialFrom		paperFrom;
	/** 纸张张数：正数 */
	private int					paperStandAmount;
	/** 纸张张数：加放 */
	private int					paperAddAmount;
	/** 纸张张数：总数 */
	private int					paperAmount;
	/** 纸张：吨价 */
	private BigDecimal			tonnePrice;
	/** 纸张：单价、张价 */
	private BigDecimal			price;
	/** 纸张：金额 */
	private BigDecimal			payTotal;

	public OrderPaper() {
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

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public MaterialFrom getPaperFrom() {
		return paperFrom;
	}

	public void setPaperFrom(MaterialFrom paperFrom) {
		this.paperFrom = paperFrom;
	}

	public int getPaperStandAmount() {
		return paperStandAmount;
	}

	public void setPaperStandAmount(int paperStandAmount) {
		this.paperStandAmount = paperStandAmount;
	}

	public int getPaperAddAmount() {
		return paperAddAmount;
	}

	public void setPaperAddAmount(int paperAddAmount) {
		this.paperAddAmount = paperAddAmount;
	}

	public int getPaperAmount() {
		return paperAmount;
	}

	public void setPaperAmount(int paperAmount) {
		this.paperAmount = paperAmount;
	}

	public BigDecimal getTonnePrice() {
		return tonnePrice;
	}

	public void setTonnePrice(BigDecimal tonnePrice) {
		this.tonnePrice = tonnePrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPayTotal() {
		return payTotal;
	}

	public void setPayTotal(BigDecimal payTotal) {
		this.payTotal = payTotal;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
