package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.kyerp.domain.BaseDomain;
import org.kyerp.domain.org.Employee;

/**
 * 入库单
 * 
 * @author y109 2009-11-29下午11:24:25
 */
@Entity
public abstract class EnteringMaterial extends BaseDomain implements
		Serializable {
	private static final long				serialVersionUID	= 1L;
	/** 入库时间 */
	private Date							enteringTime;
	/** 仓库名称 */
	private Warehouse						warehouse;
	/** 供应商 */
	private Supplier						supplier;
	/** 采购员 */
	private Employee						buyer;
	/** 库管员 */
	private Employee						keeper;
	/** 库管员确认时间 */
	private Date							confirmTime;
	/** 操作员 */
	private Employee						operator;

	/** 入库单明细 **/
	@OneToMany(mappedBy = "enteringMaterial")
	private List<EnteringMaterialDetail>	enteringMaterialDetails;

	public EnteringMaterial() {
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
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

	public Employee getKeeper() {
		return keeper;
	}

	public void setKeeper(Employee keeper) {
		this.keeper = keeper;
	}

	public Date getEnteringTime() {
		return enteringTime;
	}

	public void setEnteringTime(Date enteringTime) {
		this.enteringTime = enteringTime;
	}

	public Employee getOperator() {
		return operator;
	}

	public void setOperator(Employee operator) {
		this.operator = operator;
	}

	public List<EnteringMaterialDetail> getEnteringMaterialDetails() {
		return enteringMaterialDetails;
	}

	public void setEnteringMaterialDetails(
			List<EnteringMaterialDetail> enteringMaterialDetails) {
		this.enteringMaterialDetails = enteringMaterialDetails;
	}

}
