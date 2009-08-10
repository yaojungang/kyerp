package com.tyopf.action;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IAFService;

@SuppressWarnings("serial")
public class QCAction extends ActionSupport {
	protected IAFService afService;

	public IAFService getAfService() {
		return afService;
	}

	public void setAfService(IAFService afService) {
		this.afService = afService;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String index() throws Exception {
		return SUCCESS;
	}
	public String getQualityProblemList() throws Exception {
		return SUCCESS;
	}

}
