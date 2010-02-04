package com.tyopf.vo;
@SuppressWarnings("serial")
public class AfValuation implements java.io.Serializable {

	// Fields    

	private long afVId;

	private AfBase afBase;

	private String itemName;

	private String chejian;

	private double danjia;
	
	private Integer amount;

	private String calProcess;

	private double totalAmount;

	public AfValuation() {
	}

	public AfBase getAfBase() {
		return afBase;
	}

	public void setAfBase(AfBase afBase) {
		this.afBase = afBase;
	}

	// Property accessors
	public long getAfVId() {
		return this.afVId;
	}

	public void setAfVId(long afVId) {
		this.afVId = afVId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getChejian() {
		return this.chejian;
	}

	public void setChejian(String chejian) {
		this.chejian = chejian;
	}

	public void setDanjia(Float danjia) {
		this.danjia = danjia;
	}

	public String getCalProcess() {
		return this.calProcess;
	}

	public void setCalProcess(String calProcess) {
		this.calProcess = calProcess;
	}

	public double getDanjia() {
		return danjia;
	}

	public void setDanjia(double danjia) {
		this.danjia = danjia;
	}

	public double getTotalAmount() {
		return com.tyopf.util.MathTools.round(totalAmount,2);
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = com.tyopf.util.MathTools.round(totalAmount,2);
	}

}
