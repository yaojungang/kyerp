package com.tyopf.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class AfQualityProblem implements java.io.Serializable {
	private int id;
	private int afId;
	private AfBase afBase;
	private Set<AfQualityProblemAttachment> attachments = new HashSet<AfQualityProblemAttachment>();
	private int workshop;
	private String personLiable;
	private String description;
	private String reason;
	private String solution;
	private String loss;
	private String disposal;
	private String personOpinion;
	private String verifyMan;
	private String inputMan;
	private Date inputTime;
	private Date verifyTime;
	
	public AfQualityProblem() {}

	public int getId() {
		return id;
	}

	public int getAfId() {
		return afId;
	}

	public void setAfId(int afId) {
		this.afId = afId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AfBase getAfBase() {
		return afBase;
	}

	public void setAfBase(AfBase afBase) {
		this.afBase = afBase;
	}


	public Set<AfQualityProblemAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<AfQualityProblemAttachment> attachments) {
		this.attachments = attachments;
	}

	public int getWorkshop() {
		return workshop;
	}

	public void setWorkshop(int workshop) {
		this.workshop = workshop;
	}

	public String getPersonLiable() {
		return personLiable;
	}

	public void setPersonLiable(String personLiable) {
		this.personLiable = personLiable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getLoss() {
		return loss;
	}

	public void setLoss(String loss) {
		this.loss = loss;
	}

	public String getDisposal() {
		return disposal;
	}

	public void setDisposal(String disposal) {
		this.disposal = disposal;
	}

	public String getPersonOpinion() {
		return personOpinion;
	}

	public void setPersonOpinion(String personOpinion) {
		this.personOpinion = personOpinion;
	}

	public String getVerifyMan() {
		return verifyMan;
	}

	public void setVerifyMan(String verifyMan) {
		this.verifyMan = verifyMan;
	}

	public String getInputMan() {
		return inputMan;
	}

	public void setInputMan(String inputMan) {
		this.inputMan = inputMan;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public Date getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}
	
	
}
