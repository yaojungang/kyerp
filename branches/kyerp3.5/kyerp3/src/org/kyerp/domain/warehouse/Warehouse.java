package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.kyerp.domain.BaseDomain;

/**
 * 仓库信息表
 * 
 * @author y109 2009-12-10下午08:02:27
 */
@Entity
public class Warehouse extends BaseDomain implements Serializable {
	private static final long	serialVersionUID	= 1L;
	/** 仓库编号 */
	private String				serialNumber;
	/** 仓库名称 */
	private String				name;
	/** 摘要 **/
	private String				note;
	/** 子类别 **/
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE }, mappedBy = "parentWarehouse")
	private List<Warehouse>		childWarehouses		= new ArrayList<Warehouse>();
	/** 所属父类 **/
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Warehouse			parentWarehouse;

	public Warehouse() {
	}

	public String getName() {
		return name;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<Warehouse> getChildWarehouses() {
		return childWarehouses;
	}

	public void setChildWarehouses(List<Warehouse> childWarehouses) {
		this.childWarehouses = childWarehouses;
	}

	public Warehouse getParentWarehouse() {
		return parentWarehouse;
	}

	public void setParentWarehouse(Warehouse parentWarehouse) {
		this.parentWarehouse = parentWarehouse;
	}

}
