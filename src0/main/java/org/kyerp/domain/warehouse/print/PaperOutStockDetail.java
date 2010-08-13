package org.kyerp.domain.warehouse.print;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.kyerp.domain.org.Department;
import org.kyerp.domain.org.Employee;
import org.kyerp.domain.warehouse.InventoryDetail;
import org.kyerp.domain.warehouse.StockDetail;
@Entity
public class PaperOutStockDetail extends InventoryDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 纸张出库单
	 */
	@ManyToOne
	private PaperOutStock paperOutStock;
	/** 领料部门 */
	@ManyToOne
	private Department				receiveDepartment;
	/** 领料人 */
	@ManyToOne
	private Employee				receiveEmployee;
	/** 出库时间 */
	private Date					outDate;
	/** 实际出库数量 */
	@Column(precision = 12,scale = 4,nullable = false)
	private BigDecimal			realOutStockCount		= new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
	public PaperOutStockDetail(){}
	
	@Override
	public StockDetail caculateStockDetail(StockDetail stockDetail,
			InventoryDetail inventoryDetail) throws Exception {
		stockDetail.setAmount(stockDetail.getAmount().subtract(
				inventoryDetail.getOutStockCount()));
		return stockDetail;
	}

	public PaperOutStock getPaperOutStock() {
		return paperOutStock;
	}
	public void setPaperOutStock(PaperOutStock paperOutStock) {
		this.paperOutStock = paperOutStock;
	}
	public Department getReceiveDepartment() {
		return receiveDepartment;
	}
	public void setReceiveDepartment(Department receiveDepartment) {
		this.receiveDepartment = receiveDepartment;
	}
	public Employee getReceiveEmployee() {
		return receiveEmployee;
	}
	public void setReceiveEmployee(Employee receiveEmployee) {
		this.receiveEmployee = receiveEmployee;
	}
	public Date getOutDate() {
		return outDate;
	}
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	public BigDecimal getRealOutStockCount() {
		return realOutStockCount;
	}
	public void setRealOutStockCount(BigDecimal realOutStockCount) {
		this.realOutStockCount = realOutStockCount;
	}
	
}
