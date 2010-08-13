package org.kyerp.domain.warehouse.print;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.kyerp.domain.warehouse.Inventory;
/**
 * 纸张出库单
 * @author y109
 *
 */
@Entity
public class PaperOutStock extends Inventory implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 生产任务单号 */
	private String				pressworkNo;
	/**
	 * 出库单明细
	 */
	@OneToMany(mappedBy = "paperOutStock",cascade = { CascadeType.ALL })
	private List<PaperOutStockDetail>	details				= new ArrayList<PaperOutStockDetail>();
	
	public PaperOutStock(){}

	public String getPressworkNo() {
		return pressworkNo;
	}

	public void setPressworkNo(String pressworkNo) {
		this.pressworkNo = pressworkNo;
	}

	public List<PaperOutStockDetail> getDetails() {
		return details;
	}

	public void setDetails(List<PaperOutStockDetail> details) {
		this.details = details;
	}
	
}
