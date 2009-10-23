package com.tyopf.vo;

import java.util.Date;

public class AfElement {
	private long afEId;
	private Long afId;
	private AfBase afBase;
	private String EType;
	private Integer EAmount;
	private Double EPs;
	private String EMachine;
	private String EFormat;
	private String EColorFront;
	private String EColorBack;
	private Integer EColorFrontN;
	private Integer EColorBackN;
	private String EPaper;
	private String EPaperFrom;
	private String ESs;
	private Double EReam;
	private Double EOvers;
	private String EPlateType;
	private Integer EPlateAmount;
	private Date EPlanPm;
	private Date EPlanBp;
	private Date EPlanProof;
	private Date EPlanPress;
	private String EIdePm;
	private String EIdeBp;
	private String EIdeProof;
	private String EIdePress;
	private Date EComPm;
	private Date EComBp;
	private Date EComProof;
	private Date EComPress;
	private Integer EP;
	private Integer paperStatus;
	private String paperSupplyMan;
	private String paperGetMan;
	private Date paperSupplyTime;
	private Integer filmPlace;
	private Date pmstartTime;
	private String pmbindMan;
	private Integer pmfilmCheck;
	private String pmfilmStatus;
	private String pmsaiMan;
	private String pmsaiMethod;
	private Integer pmoldPs;
	private Integer pmnewPs;
	private Double pmsaiLong;
	private Double pmsaiMpa;
	private Date pmpushSampleTime;
	private String pmpushSampleMan;
	
	public AfElement() {
	}
	public Date getPmstartTime() {
		return pmstartTime;
	}
	public Integer getPmfilmCheck() {
		return pmfilmCheck;
	}
	public void setPmfilmCheck(Integer pmfilmCheck) {
		this.pmfilmCheck = pmfilmCheck;
	}
	public String getPmsaiMethod() {
		return pmsaiMethod;
	}
	public void setPmsaiMethod(String pmsaiMethod) {
		this.pmsaiMethod = pmsaiMethod;
	}
	public void setPmstartTime(Date pmstartTime) {
		this.pmstartTime = pmstartTime;
	}
	public String getPmbindMan() {
		return pmbindMan;
	}
	public void setPmbindMan(String pmbindMan) {
		this.pmbindMan = pmbindMan;
	}
	public String getPmfilmStatus() {
		return pmfilmStatus;
	}
	public void setPmfilmStatus(String pmfilmStatus) {
		this.pmfilmStatus = pmfilmStatus;
	}
	public String getPmsaiMan() {
		return pmsaiMan;
	}
	public void setPmsaiMan(String pmsaiMan) {
		this.pmsaiMan = pmsaiMan;
	}
	public Integer getPmoldPs() {
		return pmoldPs;
	}
	public void setPmoldPs(Integer pmoldPs) {
		this.pmoldPs = pmoldPs;
	}
	public Integer getPmnewPs() {
		return pmnewPs;
	}
	public void setPmnewPs(Integer pmnewPs) {
		this.pmnewPs = pmnewPs;
	}
	public Double getPmsaiLong() {
		return pmsaiLong;
	}
	public void setPmsaiLong(Double pmsaiLong) {
		this.pmsaiLong = pmsaiLong;
	}
	public Double getPmsaiMpa() {
		return pmsaiMpa;
	}
	public void setPmsaiMpa(Double pmsaiMpa) {
		this.pmsaiMpa = pmsaiMpa;
	}
	public Date getPmpushSampleTime() {
		return pmpushSampleTime;
	}
	public void setPmpushSampleTime(Date pmpushSampleTime) {
		this.pmpushSampleTime = pmpushSampleTime;
	}
	public String getPmpushSampleMan() {
		return pmpushSampleMan;
	}
	public void setPmpushSampleMan(String pmpushSampleMan) {
		this.pmpushSampleMan = pmpushSampleMan;
	}
	public long getAfEId() {
		return afEId;
	}
	public void setAfEId(long afEId) {
		this.afEId = afEId;
	}
	public Long getAfId() {
		return afId;
	}
	public void setAfId(Long afId) {
		this.afId = afId;
	}
	public AfBase getAfBase() {
		return afBase;
	}
	public void setAfBase(AfBase afBase) {
		this.afBase = afBase;
	}
	public String getEType() {
		return EType;
	}
	public void setEType(String type) {
		EType = type;
	}
	public Integer getEAmount() {
		return EAmount;
	}
	public void setEAmount(Integer amount) {
		EAmount = amount;
	}
	public Double getEPs() {
		return EPs;
	}
	public void setEPs(Double ps) {
		EPs = ps;
	}
	public String getEMachine() {
		return EMachine;
	}
	public void setEMachine(String machine) {
		EMachine = machine;
	}
	public String getEFormat() {
		return EFormat;
	}
	public void setEFormat(String format) {
		EFormat = format;
	}
	public String getEColorFront() {
		return EColorFront;
	}
	public void setEColorFront(String colorFront) {
		EColorFront = colorFront;
	}
	public String getEColorBack() {
		return EColorBack;
	}
	public void setEColorBack(String colorBack) {
		EColorBack = colorBack;
	}
	public Integer getEColorFrontN() {
		return EColorFrontN;
	}
	public void setEColorFrontN(Integer colorFrontN) {
		EColorFrontN = colorFrontN;
	}
	public Integer getEColorBackN() {
		return EColorBackN;
	}
	public void setEColorBackN(Integer colorBackN) {
		EColorBackN = colorBackN;
	}
	public String getEPaper() {
		return EPaper;
	}
	public void setEPaper(String paper) {
		EPaper = paper;
	}
	public String getEPaperFrom() {
		return EPaperFrom;
	}
	public void setEPaperFrom(String paperFrom) {
		EPaperFrom = paperFrom;
	}
	public String getESs() {
		return ESs;
	}
	public void setESs(String ss) {
		ESs = ss;
	}
	public Double getEReam() {
		return EReam;
	}
	public void setEReam(Double ream) {
		EReam = ream;
	}
	public Double getEOvers() {
		return EOvers;
	}
	public void setEOvers(Double overs) {
		EOvers = overs;
	}
	public String getEPlateType() {
		return EPlateType;
	}
	public void setEPlateType(String plateType) {
		EPlateType = plateType;
	}
	public Integer getEPlateAmount() {
		return EPlateAmount;
	}
	public void setEPlateAmount(Integer plateAmount) {
		EPlateAmount = plateAmount;
	}
	public Date getEPlanPm() {
		return EPlanPm;
	}
	public void setEPlanPm(Date planPm) {
		EPlanPm = planPm;
	}
	public Date getEPlanBp() {
		return EPlanBp;
	}
	public void setEPlanBp(Date planBp) {
		EPlanBp = planBp;
	}
	public Date getEPlanProof() {
		return EPlanProof;
	}
	public void setEPlanProof(Date planProof) {
		EPlanProof = planProof;
	}
	public Date getEPlanPress() {
		return EPlanPress;
	}
	public void setEPlanPress(Date planPress) {
		EPlanPress = planPress;
	}
	public String getEIdePm() {
		return EIdePm;
	}
	public void setEIdePm(String idePm) {
		EIdePm = idePm;
	}
	public String getEIdeBp() {
		return EIdeBp;
	}
	public void setEIdeBp(String ideBp) {
		EIdeBp = ideBp;
	}
	public String getEIdeProof() {
		return EIdeProof;
	}
	public void setEIdeProof(String ideProof) {
		EIdeProof = ideProof;
	}
	public String getEIdePress() {
		return EIdePress;
	}
	public void setEIdePress(String idePress) {
		EIdePress = idePress;
	}
	public Date getEComPm() {
		return EComPm;
	}
	public void setEComPm(Date comPm) {
		EComPm = comPm;
	}
	public Date getEComBp() {
		return EComBp;
	}
	public void setEComBp(Date comBp) {
		EComBp = comBp;
	}
	public Date getEComProof() {
		return EComProof;
	}
	public void setEComProof(Date comProof) {
		EComProof = comProof;
	}
	public Date getEComPress() {
		return EComPress;
	}
	public void setEComPress(Date comPress) {
		EComPress = comPress;
	}
	public Integer getEP() {
		return EP;
	}
	public void setEP(Integer ep) {
		EP = ep;
	}
	public Integer getPaperStatus() {
		return paperStatus;
	}
	public void setPaperStatus(Integer paperStatus) {
		this.paperStatus = paperStatus;
	}
	public String getPaperSupplyMan() {
		return paperSupplyMan;
	}
	public void setPaperSupplyMan(String paperSupplyMan) {
		this.paperSupplyMan = paperSupplyMan;
	}
	public String getPaperGetMan() {
		return paperGetMan;
	}
	public void setPaperGetMan(String paperGetMan) {
		this.paperGetMan = paperGetMan;
	}
	public Date getPaperSupplyTime() {
		return paperSupplyTime;
	}
	public void setPaperSupplyTime(Date paperSupplyTime) {
		this.paperSupplyTime = paperSupplyTime;
	}
	public Integer getFilmPlace() {
		return filmPlace;
	}
	public void setFilmPlace(Integer filmPlace) {
		this.filmPlace = filmPlace;
	}

}
