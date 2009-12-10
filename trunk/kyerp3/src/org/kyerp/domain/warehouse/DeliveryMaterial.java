package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.kyerp.domain.BaseDomain;
import org.kyerp.domain.org.Department;
import org.kyerp.domain.org.Employee;

/**
 * 出库单
 * 
 * @author y109 2009-12-10下午08:39:57
 */
@Entity
public class DeliveryMaterial extends BaseDomain implements Serializable {
	private static final long				serialVersionUID	= 1L;
	/** 出库时间 */
	private Date							deliveryTime;
	/** 仓库名称 */
	private Warehouse						warehouse;
	/** 领料车间 */
	private Department						department;
	/** 领料人员 */
	private Employee						delivery;
	/** 库管员 */
	private Employee						keeper;
	/** 操作员 */
	private Employee						operator;

	/** 出库单明细 **/
	@OneToMany(mappedBy = "deliveryMaterial")
	private List<DeliveryMaterialDetail>	deliveryMaterialDetails;

	public DeliveryMaterial() {
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee getDelivery() {
		return delivery;
	}

	public void setDelivery(Employee delivery) {
		this.delivery = delivery;
	}

	public Employee getKeeper() {
		return keeper;
	}

	public void setKeeper(Employee keeper) {
		this.keeper = keeper;
	}

	public Employee getOperator() {
		return operator;
	}

	public void setOperator(Employee operator) {
		this.operator = operator;
	}

	public List<DeliveryMaterialDetail> getDeliveryMaterialDetails() {
		return deliveryMaterialDetails;
	}

	public void setDeliveryMaterialDetails(
			List<DeliveryMaterialDetail> deliveryMaterialDetails) {
		this.deliveryMaterialDetails = deliveryMaterialDetails;
	}

}
