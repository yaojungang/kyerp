package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * 出库单明细
 * 
 * @author y109 2010-3-19下午06:49:15
 */
@Entity
public class OutStockDetail extends InventoryDetail implements Serializable{
	private static final long	serialVersionUID	= 1L;
	/** 出库单 */
	@ManyToOne
	private OutStock			outStock;
	/** 生产任务单号 */
	private String				pressworkNo;
	/** 实际出库数量 */
	@Column(precision = 12,scale = 4,nullable = false)
	private BigDecimal			realOutStockCount		= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	
	public OutStockDetail() {
	}

	@Override
	public StockDetail caculateStockDetail(StockDetail stockDetail,
			InventoryDetail inventoryDetail) throws Exception {
		logger.debug("出库：" + stockDetail.getBatchNumber() + "数量"
				+ stockDetail.getAmount() + " - "
				+ inventoryDetail.getOutStockCount());
		stockDetail.setAmount(stockDetail.getAmount().subtract(
				inventoryDetail.getOutStockCount()));
		return stockDetail;
	}

	@Override
	public String toString() {
		try {
			return "OutStockDetail [pressworkNo=" + pressworkNo + ", getId()="
					+ getId() + ", getOutStockCount()=" + getOutStockCount() + "]";
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
		return super.getPrice().multiply(super.getOutStockCount());
	}
	public OutStock getOutStock() {
		return outStock;
	}

	public void setOutStock(OutStock outStock) {
		this.outStock = outStock;
	}

	public String getPressworkNo() {
		return pressworkNo;
	}

	public void setPressworkNo(String pressworkNo) {
		this.pressworkNo = pressworkNo;
	}

	public BigDecimal getRealOutStockCount() {
		return realOutStockCount;
	}

	public void setRealOutStockCount(BigDecimal realOutStockCount) {
		this.realOutStockCount = realOutStockCount;
	}

}
