package com.tyopf.vo;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class ClientC implements java.io.Serializable {

	private long	CCId;

	private Set		ClientLm	= new HashSet();
	private String	ywname;

	private String	CCCom;

	private String	CCFax;

	private String	CCAddress;

	private String	CCPc;

	private String	CCDa;

	private String	CCDt;

	private String	CCRemark;

	public ClientC() {
	}

	public Set getClientLm() {
		return ClientLm;
	}

	public String getYwname() {
		return ywname;
	}

	public void setYwname(String ywname) {
		this.ywname = ywname;
	}

	public void setClientLm(Set clientLm) {
		ClientLm = clientLm;
	}

	public long getCCId() {
		return this.CCId;
	}

	public void setCCId(long CCId) {
		this.CCId = CCId;
	}

	public String getCCCom() {
		return this.CCCom;
	}

	public void setCCCom(String CCCom) {
		this.CCCom = CCCom;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClientC [CCAddress=").append(CCAddress).append(
				", CCCom=").append(CCCom).append(", CCDa=").append(CCDa)
				.append(", CCDt=").append(CCDt).append(", CCFax=")
				.append(CCFax).append(", CCId=").append(CCId).append(", CCPc=")
				.append(CCPc).append(", CCRemark=").append(CCRemark).append(
						", ClientLm=").append(ClientLm).append(", ywname=")
				.append(ywname).append("]");
		return builder.toString();
	}

	public String getCCFax() {
		return this.CCFax;
	}

	public void setCCFax(String CCFax) {
		this.CCFax = CCFax;
	}

	public String getCCAddress() {
		return this.CCAddress;
	}

	public void setCCAddress(String CCAddress) {
		this.CCAddress = CCAddress;
	}

	public String getCCPc() {
		return this.CCPc;
	}

	public void setCCPc(String CCPc) {
		this.CCPc = CCPc;
	}

	public String getCCDa() {
		return this.CCDa;
	}

	public void setCCDa(String CCDa) {
		this.CCDa = CCDa;
	}

	public String getCCDt() {
		return this.CCDt;
	}

	public void setCCDt(String CCDt) {
		this.CCDt = CCDt;
	}

	public String getCCRemark() {
		return this.CCRemark;
	}

	public void setCCRemark(String CCRemark) {
		this.CCRemark = CCRemark;
	}

}
