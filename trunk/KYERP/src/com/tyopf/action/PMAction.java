package com.tyopf.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IAFService;

@SuppressWarnings("serial")
public class PMAction extends ActionSupport {
	protected IAFService afService;
	private FilmBoxUnit filmBoxUnit;

	public FilmBoxUnit getFilmBoxUnit() {
		return filmBoxUnit;
	}

	public void setFilmBoxUnit(FilmBoxUnit filmBoxUnit) {
		this.filmBoxUnit = filmBoxUnit;
	}

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

	@SuppressWarnings("unchecked")
	public String index() throws Exception {
		List ListAF = afService.getTodayAFs();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", ListAF);
		return SUCCESS;
	}

}
