package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.kyerp.domain.BaseDomain;
import org.kyerp.domain.org.Employee;

/**
 * 入库单
 * 
 * @author y109 2009-11-29下午11:24:25
 */
@Entity
/**继承映射策略*/
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
/**标识字段定义*/
@DiscriminatorColumn(name = "enteringMaterial_type", discriminatorType = DiscriminatorType.STRING)
/**该类的标识*/
@DiscriminatorValue("enteringMaterial")
public abstract class EnteringMaterial extends BaseDomain implements
		Serializable {
	private static final long				serialVersionUID	= 1L;
	/** 入库时间 */
	private Date							enteringTime;
	/** 仓库名称 */
	@ManyToOne
	private Warehouse						warehouse;
	/** 库管员 */
	@ManyToOne
	private Employee						keeper;
	/** 操作员 */
	@ManyToOne
	private Employee						operator;

	/** 入库单明细 **/
	@OneToMany(mappedBy = "enteringMaterial")
	private List<EnteringMaterialDetail>	enteringMaterialDetails;

	public EnteringMaterial() {
	}

	public Date getEnteringTime() {
		return enteringTime;
	}

	public void setEnteringTime(Date enteringTime) {
		this.enteringTime = enteringTime;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
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

	public List<EnteringMaterialDetail> getEnteringMaterialDetails() {
		return enteringMaterialDetails;
	}

	public void setEnteringMaterialDetails(
			List<EnteringMaterialDetail> enteringMaterialDetails) {
		this.enteringMaterialDetails = enteringMaterialDetails;
	}

	/**
	 * 添加入库明细项目
	 */
	public void addEnteringItem(EnteringMaterialDetail enteringMaterialDetail) {
		this.enteringMaterialDetails.add(enteringMaterialDetail);
		enteringMaterialDetail.setEnteringMaterial(this);
	}

}
