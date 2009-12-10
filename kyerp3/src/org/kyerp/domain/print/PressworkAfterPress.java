/**
 * 
 */
package org.kyerp.domain.print;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.kyerp.domain.BaseDomain;

/**
 * 印后项目
 * 
 * @author y109 2009-11-29下午10:46:20
 */
@Entity
public class PressworkAfterPress extends BaseDomain implements Serializable {
	private static final long	serialVersionUID	= 3845835627705841650L;
	/** 所属任务单 */
	@ManyToOne
	private Presswork			presswork;
	/** 项目名称：封面，正文，插页 */
	private String				itemName;
	/** 项目说明 */
	private String				itemRemark;
	/** 项目数量 */
	private int					amount;
	/** 单位 */
	private String				module;

	public PressworkAfterPress() {
	}

	public Presswork getPresswork() {
		return presswork;
	}

	public void setPresswork(Presswork presswork) {
		this.presswork = presswork;
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

}
