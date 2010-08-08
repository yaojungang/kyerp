package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * 入库单明细
 * 
 * @author y109 2010-3-19下午06:49:15
 */
@Entity
public class InStockDetail extends InventoryDetail implements Serializable{

	private static final long	serialVersionUID	= 1L;

	/** 入库单 */
	@ManyToOne
	private InStock				inStock;

	/** 采购申请单明细 */

	public InStockDetail() {
	}

	@Override
	public StockDetail caculateStockDetail(StockDetail stockDetail,
			InventoryDetail inventoryDetail) throws Exception {
		logger.debug("入库：" + stockDetail.getBatchNumber() + "数量"
				+ stockDetail.getAmount() + " + "
				+ inventoryDetail.getInStockCount());
		stockDetail.setAmount(stockDetail.getAmount().add(
				inventoryDetail.getInStockCount()));
		return stockDetail;
	}

	@Override
	public String toString() {
		try {
			return "InStockDetail [getId()=" + getId() + ", getBatchNumber()="
					+ getBatchNumber() + ", getInStockCount()=" + getInStockCount()
					+ "]";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取金额
	 * 
	 * @return
	 * @throws Exception 
	 */
	public BigDecimal getBillCost() throws Exception {
		return super.getPrice().multiply(super.getInStockCount());
	}


	public InStock getInStock() {
		return inStock;
	}

	public void setInStock(InStock inStock) {
		this.inStock = inStock;
	}

}
