package com.tyopf.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class AfBase {
	private long afId;
	private String afType;
	private Integer aftypeNo;
	private Integer number;
	private Integer colorFrontN;
	private String colorFront;
	private Integer colorBackN;
	private String colorBack;
	private String paper;
	private Double paperPrice;
	private Set<AfElement> afElement = new HashSet<AfElement>();
	private Set<AfDispose> afDispose = new HashSet<AfDispose>();
	private Set<AfValuation> AfValuation = new HashSet<AfValuation>();
	private Set<AfQualityProblem> afQualityProblem = new HashSet<AfQualityProblem>();
	private String iso;
	private Date ad;
	private Date lastModify;
	private Integer afStatus;
	private String client;
	private String linkman;
	private String presswork;
	private Integer ourbinding;
	private String isbn;
	private String tel;
	private Long amount;
	private String edition;
	private String format;
	private String fps;
	private String bm;
	private String plateMakeOrder;
	private String oq;
	private Date planTypeset;
	private Date planPm;
	private Date planPress;
	private Date planBind;
	private Date planDeliver;
	private Date comTypeset;
	private Date comPm;
	private Date comPress;
	private Date comBind;
	private Date comDeliver;
	private String pcAf;
	private String bindery;
	private String remark;
	private String cp;
	private String fmp;
	private String dp;
	private Integer afNo;
	private String seriesName;
	private String deliverAddress;
	private String auditer;
	private Date auditTime;
	private Integer timeRank;
	private Double moneyShould;
	private Double moneyFact;
	private Date moneyTime;
	private Date moneyInputTime;
	private String moneyInputMan;
	private String moneyGiveMan;
	private String moneyGetMan;
	private Integer moneyStatus;
	private Date fapiaoTime;
	private Integer fapiaoStatus;
	private String moneyInputRemark;
	private String moneyRemark;
	private String mobile;
	private Integer mobileRemind;
	private String email;
	private Integer emailRemind;
	private Date planSendSample;
	private String remarkSendSample;
	private Integer viewTimes;
	
	public Date getFapiaoTime() {
		return fapiaoTime;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public Integer getViewTimes() {
		return viewTimes;
	}
	
	public void setViewTimes(Integer viewTimes) {
		this.viewTimes = viewTimes;
	}
	
	public Set<AfQualityProblem> getAfQualityProblem() {
		return afQualityProblem;
	}

	public void setAfQualityProblem(Set<AfQualityProblem> afQualityProblem) {
		this.afQualityProblem = afQualityProblem;
	}

	public Date getPlanSendSample() {
		return planSendSample;
	}
	
	public void setPlanSendSample(Date planSendSample) {
		this.planSendSample = planSendSample;
	}
	
	public String getRemarkSendSample() {
		return remarkSendSample;
	}
	
	public void setRemarkSendSample(String remarkSendSample) {
		this.remarkSendSample = remarkSendSample;
	}
	
	public Integer getMobileRemind() {
		return mobileRemind;
	}
	
	public void setMobileRemind(Integer mobileRemind) {
		this.mobileRemind = mobileRemind;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getEmailRemind() {
		return emailRemind;
	}
	
	public Integer getAftypeNo() {
		return aftypeNo;
	}
	
	public void setAftypeNo(Integer aftypeNo) {
		this.aftypeNo = aftypeNo;
	}
	
	public void setEmailRemind(Integer emailRemind) {
		this.emailRemind = emailRemind;
	}
	
	public void setFapiaoTime(Date fapiaoTime) {
		this.fapiaoTime = fapiaoTime;
	}
	
	public String getMoneyInputRemark() {
		return moneyInputRemark;
	}
	
	public void setMoneyInputRemark(String moneyInputRemark) {
		this.moneyInputRemark = moneyInputRemark;
	}
	
	public Integer getFapiaoStatus() {
		return fapiaoStatus;
	}
	
	public void setFapiaoStatus(Integer fapiaoStatus) {
		this.fapiaoStatus = fapiaoStatus;
	}
	
	public String getAfType() {
		return afType;
	}
	
	public String getPlateMakeOrder() {
		return plateMakeOrder;
	}
	
	public void setPlateMakeOrder(String plateMakeOrder) {
		this.plateMakeOrder = plateMakeOrder;
	}
	
	public void setAfType(String afType) {
		this.afType = afType;
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public Integer getColorFrontN() {
		return colorFrontN;
	}
	
	public void setColorFrontN(Integer colorFrontN) {
		this.colorFrontN = colorFrontN;
	}
	
	public String getColorFront() {
		return colorFront;
	}
	
	public void setColorFront(String colorFront) {
		this.colorFront = colorFront;
	}
	
	public Integer getColorBackN() {
		return colorBackN;
	}
	
	public void setColorBackN(Integer colorBackN) {
		this.colorBackN = colorBackN;
	}
	
	public String getColorBack() {
		return colorBack;
	}
	
	public void setColorBack(String colorBack) {
		this.colorBack = colorBack;
	}
	
	public String getPaper() {
		return paper;
	}
	
	public void setPaper(String paper) {
		this.paper = paper;
	}
	
	public Double getPaperPrice() {
		return paperPrice;
	}
	
	public void setPaperPrice(Double paperPrice) {
		this.paperPrice = paperPrice;
	}
	
	public Date getPlanTypeset() {
		return planTypeset;
	}
	
	public void setPlanTypeset(Date planTypeset) {
		this.planTypeset = planTypeset;
	}
	
	public Date getPlanPm() {
		return planPm;
	}
	
	public void setPlanPm(Date planPm) {
		this.planPm = planPm;
	}
	
	public Date getPlanPress() {
		return planPress;
	}
	
	public void setPlanPress(Date planPress) {
		this.planPress = planPress;
	}
	
	public Date getPlanBind() {
		return planBind;
	}
	
	public void setPlanBind(Date planBind) {
		this.planBind = planBind;
	}
	
	public Date getComTypeset() {
		return comTypeset;
	}
	
	public void setComTypeset(Date comTypeset) {
		this.comTypeset = comTypeset;
	}
	
	public Date getComPm() {
		return comPm;
	}
	
	public void setComPm(Date comPm) {
		this.comPm = comPm;
	}
	
	public Date getComPress() {
		return comPress;
	}
	
	public void setComPress(Date comPress) {
		this.comPress = comPress;
	}
	
	public Date getComBind() {
		return comBind;
	}
	
	public void setComBind(Date comBind) {
		this.comBind = comBind;
	}
	
	public Integer getAfStatus() {
		return afStatus;
	}
	
	public void setAfStatus(Integer afStatus) {
		this.afStatus = afStatus;
	}
	
	public Integer getMoneyStatus() {
		return moneyStatus;
	}
	
	public void setMoneyStatus(Integer moneyStatus) {
		this.moneyStatus = moneyStatus;
	}
	
	public String getMoneyRemark() {
		return moneyRemark;
	}
	
	public void setMoneyRemark(String moneyRemark) {
		this.moneyRemark = moneyRemark;
	}
	
	public AfBase() {}
	
	public Date getLastModify() {
		return lastModify;
	}
	
	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}
	
	public Integer getOurbinding() {
		return ourbinding;
	}
	
	public void setOurbinding(Integer ourbinding) {
		this.ourbinding = ourbinding;
	}
	
	public String getMoneyGetMan() {
		return moneyGetMan;
	}
	
	public void setMoneyGetMan(String moneyGetMan) {
		this.moneyGetMan = moneyGetMan;
	}
	
	public String getMoneyGiveMan() {
		return moneyGiveMan;
	}
	
	public void setMoneyGiveMan(String moneyGiveMan) {
		this.moneyGiveMan = moneyGiveMan;
	}
	
	public Date getAd() {
		return ad;
	}
	
	public void setAd(Date ad) {
		this.ad = ad;
	}
	
	public long getAfId() {
		return afId;
	}
	
	public void setAfId(long afId) {
		this.afId = afId;
	}
	
	public Integer getAfNo() {
		return afNo;
	}
	
	public void setAfNo(Integer afNo) {
		this.afNo = afNo;
	}
	
	public Set<AfElement> getAfElement() {
		return afElement;
	}
	
	public void setAfElement(Set<AfElement> afElement) {
		this.afElement = afElement;
	}
	
	public Set<AfDispose> getAfDispose() {
		return afDispose;
	}
	
	public void setAfDispose(Set<AfDispose> afDispose) {
		this.afDispose = afDispose;
	}
	
	public Set<AfValuation> getAfValuation() {
		return AfValuation;
	}
	
	public void setAfValuation(Set<AfValuation> afValuation) {
		AfValuation = afValuation;
	}
	
	public Long getAmount() {
		return amount;
	}
	
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	public String getAuditer() {
		return auditer;
	}
	
	public void setAuditer(String auditer) {
		this.auditer = auditer;
	}
	
	public Date getAuditTime() {
		return auditTime;
	}
	
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	
	public String getBindery() {
		return bindery;
	}
	
	public void setBindery(String bindery) {
		this.bindery = bindery;
	}
	
	public String getBm() {
		return bm;
	}
	
	public void setBm(String bm) {
		this.bm = bm;
	}
	
	public String getClient() {
		return client;
	}
	
	public void setClient(String client) {
		this.client = client;
	}
	
	public Date getComDeliver() {
		return comDeliver;
	}
	
	public void setComDeliver(Date comDeliver) {
		this.comDeliver = comDeliver;
	}
	
	public String getCp() {
		return cp;
	}
	
	public void setCp(String cp) {
		this.cp = cp;
	}
	
	public String getDeliverAddress() {
		return deliverAddress;
	}
	
	public void setDeliverAddress(String deliverAddress) {
		this.deliverAddress = deliverAddress;
	}
	
	public String getDp() {
		return dp;
	}
	
	public void setDp(String dp) {
		this.dp = dp;
	}
	
	public String getEdition() {
		return edition;
	}
	
	public void setEdition(String edition) {
		this.edition = edition;
	}
	
	public String getFmp() {
		return fmp;
	}
	
	public void setFmp(String fmp) {
		this.fmp = fmp;
	}
	
	public String getFormat() {
		return format;
	}
	
	public void setFormat(String format) {
		this.format = format;
	}
	
	public String getFps() {
		return fps;
	}
	
	public void setFps(String fps) {
		this.fps = fps;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getIso() {
		return iso;
	}
	
	public void setIso(String iso) {
		this.iso = iso;
	}
	
	public String getLinkman() {
		return linkman;
	}
	
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	
	public Double getMoneyFact() {
		return moneyFact;
	}
	
	public void setMoneyFact(Double moneyFact) {
		this.moneyFact = moneyFact;
	}
	
	public String getMoneyInputMan() {
		return moneyInputMan;
	}
	
	public void setMoneyInputMan(String moneyInputMan) {
		this.moneyInputMan = moneyInputMan;
	}
	
	public Date getMoneyInputTime() {
		return moneyInputTime;
	}
	
	public void setMoneyInputTime(Date moneyInputTime) {
		this.moneyInputTime = moneyInputTime;
	}
	
	public Double getMoneyShould() {
		return moneyShould;
	}
	
	public void setMoneyShould(Double moneyShould) {
		this.moneyShould = moneyShould;
	}
	
	public Date getMoneyTime() {
		return moneyTime;
	}
	
	public void setMoneyTime(Date moneyTime) {
		this.moneyTime = moneyTime;
	}
	
	public String getOq() {
		return oq;
	}
	
	public void setOq(String oq) {
		this.oq = oq;
	}
	
	public String getPcAf() {
		return pcAf;
	}
	
	public void setPcAf(String pcAf) {
		this.pcAf = pcAf;
	}
	
	public Date getPlanDeliver() {
		return planDeliver;
	}
	
	public void setPlanDeliver(Date planDeliver) {
		this.planDeliver = planDeliver;
	}
	
	public String getPresswork() {
		return presswork;
	}
	
	public void setPresswork(String presswork) {
		this.presswork = presswork;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getSeriesName() {
		return seriesName;
	}
	
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public Integer getTimeRank() {
		return timeRank;
	}
	
	public void setTimeRank(Integer timeRank) {
		this.timeRank = timeRank;
	}
}
