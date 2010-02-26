package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
public class EnteringMaterial extends BaseDomain implements Serializable {
	private static final long	serialVersionUID	= 1L;
	/** 入库单号 */
	private String				serialNumber		= "";
	/** 入库时间 */
	private Date				enteringTime;
	/** 供应商名称 */
	@ManyToOne
	private Supplier			Supplier;
	/** 库管员 */
	@ManyToOne
	private Employee			keeper;
	/** 操作员 */
	@ManyToOne
	private Employee			operator;
	/** 物料明细 **/
	@OneToMany(mappedBy = "enteringMaterial", cascade = { CascadeType.ALL })
	private List<MaterialBatch>	batchs				= new ArrayList<MaterialBatch>();

	/** 入库方式 */
	/** 货位 */

	public EnteringMaterial() {
	}

	public Date getEnteringTime() {
		return enteringTime;
	}

	public void setEnteringTime(Date enteringTime) {
		this.enteringTime = enteringTime;
	}

	public Supplier getSupplier() {
		return Supplier;
	}

	public void setSupplier(Supplier supplier) {
		Supplier = supplier;
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

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public List<MaterialBatch> getBatchs() {
		return batchs;
	}

	public void setBatchs(List<MaterialBatch> batchs) {
		this.batchs = batchs;
	}

	/**
	 * 添加物料批次项目
	 */
	public void addEnteringItem(MaterialBatch materialBatch) {
		this.batchs.add(materialBatch);
		materialBatch.setEnteringMaterial(this);
	}

}
