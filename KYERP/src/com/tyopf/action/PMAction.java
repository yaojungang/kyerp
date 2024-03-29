package com.tyopf.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IAFService;
import com.tyopf.service.ISystemService;
import com.tyopf.util.Pager;
import com.tyopf.vo.AfBase;
import com.tyopf.vo.AfElement;

@SuppressWarnings("serial")
public class PMAction extends ActionSupport implements SessionAware{
	private Integer currentPage = 1;
	private Integer pageSize = 50;
	protected IAFService afService;
	protected ISystemService systemService;
	private FilmBoxUnit filmBoxUnit;
	private int afId;
	private int afEId;
	private Map session;
	private AfElement afe;

	public int getAfEId() {
		return afEId;
	}

	public AfElement getAfe() {
		return afe;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setAfe(AfElement afe) {
		this.afe = afe;
	}

	public ISystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(ISystemService systemService) {
		this.systemService = systemService;
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public void setAfEId(int afEId) {
		this.afEId = afEId;
	}

	public int getAfId() {
		return afId;
	}

	public void setAfId(int afId) {
		this.afId = afId;
	}

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
	@SuppressWarnings("unchecked")
	public String PM_AFE_input() throws Exception {
		List deptTree = (List) session.get("DeptTree");
		if (null == deptTree) {
			deptTree = systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		AfElement afe = afService.getAFEById(afEId);
		AfBase af = afe.getAfBase();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("DeptTree", deptTree);
		request.put("AFInfo", af);
		request.put("AFEInfo", afe);
		return SUCCESS;
	}

	public String PM_AFE_save() throws Exception {
		afService.editAFE_PM(afe);
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String getAFEs() {
		List ListAFE = afService.getAFEs(currentPage, pageSize);
		Pager AFpager = new Pager(currentPage, afService.getCountofAllAFEs());
		AFpager.setPageSize(pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAFE", ListAFE);
		request.put("AFPager", AFpager);
		return SUCCESS;
	}

}
