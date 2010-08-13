package org.kyerp.domain.warehouse;

import java.io.Serializable;
/**
 * 库存所有者
 */

import javax.persistence.Entity;

import org.kyerp.domain.BaseDomain;
@Entity
public class InventoryOwner extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	private InventoryOwnerType type;
	private Long inventoryOwnerId;
	private String name;
	/** 编号 **/
	private String	serialNumber;
	
	@Override
	public String toString() {
		return "InventoryOwner [name=" + name + ", serialNumber="
				+ serialNumber + ", getId()=" + getId() + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((inventoryOwnerId == null) ? 0 : inventoryOwnerId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((serialNumber == null) ? 0 : serialNumber.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryOwner other = (InventoryOwner) obj;
		if (inventoryOwnerId == null) {
			if (other.inventoryOwnerId != null)
				return false;
		} else if (!inventoryOwnerId.equals(other.inventoryOwnerId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (serialNumber == null) {
			if (other.serialNumber != null)
				return false;
		} else if (!serialNumber.equals(other.serialNumber))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	public InventoryOwner() {		
	}
	public InventoryOwnerType getType() {
		return type;
	}
	public void setType(InventoryOwnerType type) {
		this.type = type;
	}
	public Long getInventoryOwnerId() {
		return inventoryOwnerId;
	}
	public void setInventoryOwnerId(Long inventoryOwnerId) {
		this.inventoryOwnerId = inventoryOwnerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
}
