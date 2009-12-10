package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.kyerp.domain.org.Employee;

/**
 * 采购入库单
 * 
 * @author y109 2009-11-29下午11:24:25
 */
@Entity
@DiscriminatorValue("buyer")
public abstract class BuyerOfEnteringMaterial extends EnteringMaterial
		implements Serializable {
	private static final long	serialVersionUID	= 1L;
	/** 采购入库单填单时间 */
	private Date				inputTime;
	/** 供应商 */
	private Supplier			supplier;
	/** 采购员 */
	private Employee			buyer;
	/** 采购入库单填写人 */
	private Employee			inputMan;

	public BuyerOfEnteringMaterial() {
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Employee getBuyer() {
		return buyer;
	}

	public void setBuyer(Employee buyer) {
		this.buyer = buyer;
	}

	public Employee getInputMan() {
		return inputMan;
	}

	public void setInputMan(Employee inputMan) {
		this.inputMan = inputMan;
	}

}
