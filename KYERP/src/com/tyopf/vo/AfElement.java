package com.tyopf.vo;

import java.util.Date;

public class AfElement {
	private long afEId;

	private Long afId;
	
	private AfBase afBase;
	
	private CompanyFilm companyFilm;

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
	private Integer filmId;
	// Constructors

	/** default constructor */
	public AfElement() {
	}

	public CompanyFilm getCompanyFilm() {
		return companyFilm;
	}

	public void setCompanyFilm(CompanyFilm companyFilm) {
		this.companyFilm = companyFilm;
	}

	public Long getAfId() {
		return afId;
	}

	public void setAfId(Long afId) {
		this.afId = afId;
	}

	public Integer getPaperStatus() {
		return paperStatus;
	}

	public Integer getFilmId() {
		return filmId;
	}

	public void setFilmId(Integer filmId) {
		this.filmId = filmId;
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

	public AfBase getAfBase() {
		return afBase;
	}

	public void setAfBase(AfBase afBase) {
		this.afBase = afBase;
	}

	public long getAfEId() {
		return afEId;
	}

	public void setAfEId(long afEId) {
		this.afEId = afEId;
	}

	public Integer getEAmount() {
		return EAmount;
	}

	public void setEAmount(Integer amount) {
		EAmount = amount;
	}

	public String getEColorBack() {
		return EColorBack;
	}

	public void setEColorBack(String colorBack) {
		EColorBack = colorBack;
	}

	public Integer getEColorBackN() {
		return EColorBackN;
	}

	public void setEColorBackN(Integer colorBackN) {
		EColorBackN = colorBackN;
	}

	public String getEColorFront() {
		return EColorFront;
	}

	public void setEColorFront(String colorFront) {
		EColorFront = colorFront;
	}

	public Integer getEColorFrontN() {
		return EColorFrontN;
	}

	public void setEColorFrontN(Integer colorFrontN) {
		EColorFrontN = colorFrontN;
	}

	public Date getEComBp() {
		return EComBp;
	}

	public void setEComBp(Date comBp) {
		EComBp = comBp;
	}

	public Date getEComPm() {
		return EComPm;
	}

	public void setEComPm(Date comPm) {
		EComPm = comPm;
	}

	public Date getEComPress() {
		return EComPress;
	}

	public void setEComPress(Date comPress) {
		EComPress = comPress;
	}

	public Date getEComProof() {
		return EComProof;
	}

	public void setEComProof(Date comProof) {
		EComProof = comProof;
	}

	public String getEFormat() {
		return EFormat;
	}

	public void setEFormat(String format) {
		EFormat = format;
	}

	public String getEIdeBp() {
		return EIdeBp;
	}

	public void setEIdeBp(String ideBp) {
		EIdeBp = ideBp;
	}

	public String getEIdePm() {
		return EIdePm;
	}

	public void setEIdePm(String idePm) {
		EIdePm = idePm;
	}

	public String getEIdePress() {
		return EIdePress;
	}

	public void setEIdePress(String idePress) {
		EIdePress = idePress;
	}

	public String getEIdeProof() {
		return EIdeProof;
	}

	public void setEIdeProof(String ideProof) {
		EIdeProof = ideProof;
	}

	public String getEMachine() {
		return EMachine;
	}

	public void setEMachine(String machine) {
		EMachine = machine;
	}

	public Double getEOvers() {
		return EOvers;
	}

	public void setEOvers(Double overs) {
		EOvers = overs;
	}

	public Integer getEP() {
		return EP;
	}

	public void setEP(Integer ep) {
		EP = ep;
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

	public Date getEPlanBp() {
		return EPlanBp;
	}

	public void setEPlanBp(Date planBp) {
		EPlanBp = planBp;
	}

	public Date getEPlanPm() {
		return EPlanPm;
	}

	public void setEPlanPm(Date planPm) {
		EPlanPm = planPm;
	}

	public Date getEPlanPress() {
		return EPlanPress;
	}

	public void setEPlanPress(Date planPress) {
		EPlanPress = planPress;
	}

	public Date getEPlanProof() {
		return EPlanProof;
	}

	public void setEPlanProof(Date planProof) {
		EPlanProof = planProof;
	}


	public Integer getEPlateAmount() {
		return EPlateAmount;
	}

	public void setEPlateAmount(Integer plateAmount) {
		EPlateAmount = plateAmount;
	}

	public String getEPlateType() {
		return EPlateType;
	}

	public void setEPlateType(String plateType) {
		EPlateType = plateType;
	}

	public Double getEPs() {
		return EPs;
	}

	public void setEPs(Double ps) {
		EPs = ps;
	}

	public Double getEReam() {
		return EReam;
	}

	public void setEReam(Double ream) {
		EReam = ream;
	}

	public String getESs() {
		return ESs;
	}

	public void setESs(String ss) {
		ESs = ss;
	}

	public String getEType() {
		return EType;
	}

	public void setEType(String type) {
		EType = type;
	}

}
