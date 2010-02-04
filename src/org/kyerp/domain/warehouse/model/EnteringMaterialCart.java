package org.kyerp.domain.warehouse.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.kyerp.domain.org.Employee;
import org.kyerp.domain.warehouse.Warehouse;

/**
 * 入库车
 * 
 * @author y109 2009-12-11下午02:23:01
 */
public class EnteringMaterialCart {
	/** 入库项 */
	private List<EnteringMaterialCartItem>	items	= new ArrayList<EnteringMaterialCartItem>();
	/** 采购员 */
	private Employee					buyer;
	/** 仓库名称 */
	private Warehouse					warehouse;

	public List<EnteringMaterialCartItem> getItems() {
		return items;
	}

	public void setItems(List<EnteringMaterialCartItem> items) {
		this.items = items;
	}

	public Employee getBuyer() {
		return buyer;
	}

	public void setBuyer(Employee buyer) {
		this.buyer = buyer;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	/**
	 * 添加入库项
	 */
	public void add(EnteringMaterialCartItem item) {
		if (this.items.contains(item)) {
			for (EnteringMaterialCartItem it : this.items) {
				if (it.equals(item)) {
					it.setAmount(it.getAmount().add(new BigDecimal("1")));
					break;
				}
			}
		} else {
			this.items.add(item);
		}
	}

	/**
	 * 删除指定入库项目
	 */
	public void delete(EnteringMaterialCartItem item) {
		if (this.items.contains(item)) {
			this.items.remove(item);
		}
	}

	/**
	 * 清空
	 */
	public void deleteAll() {
		this.items.clear();
	}

	/**
	 * 计算总价
	 */
	public BigDecimal getTotalSellPrice() {
		BigDecimal totalprice = new BigDecimal("0");
		for (EnteringMaterialCartItem item : this.items) {
			totalprice.add(item.getMaterial().getPrice().multiply(
					item.getAmount()));
		}
		return totalprice;
	}

}
