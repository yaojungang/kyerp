package com.tyopf.action;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IAFService;
import com.tyopf.service.IWareHouseService;
import com.tyopf.util.Pager;
import com.tyopf.vo.Paper;

public class WareHouseAction extends ActionSupport {
	private static final long serialVersionUID = -7610467352775875664L;
	protected IWareHouseService wareHouseService;
	protected IAFService afService;
	public int currentPage = 1;
	public int pageSize = 100;
	public Pager pager;
	public Paper paper;
	public int pid;
	public long afEId;
	public String type;
	public String paperGetMan;

	public String getType() {
		return type;
	}

	public long getAfEId() {
		return afEId;
	}

	public void setAfEId(long afEId) {
		this.afEId = afEId;
	}

	public String getPaperGetMan() {
		return paperGetMan;
	}

	public void setPaperGetMan(String paperGetMan) {
		this.paperGetMan = paperGetMan;
	}

	public IAFService getAfService() {
		return afService;
	}

	public void setAfService(IAFService afService) {
		this.afService = afService;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Paper> paperList;

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public IWareHouseService getWareHouseService() {
		return wareHouseService;
	}

	public void setWareHouseService(IWareHouseService wareHouseService) {
		this.wareHouseService = wareHouseService;
	}

	public List<Paper> getPaperList() {
		return paperList;
	}

	public void setPaperList(List<Paper> paperList) {
		this.paperList = paperList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	@SuppressWarnings("unchecked")
	public String getPapers() {
		paperList = wareHouseService.getPaperList(currentPage, pageSize);
		pager = new Pager(currentPage, wareHouseService.getCountOfPaperList());
		pager.setPageSize(pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("paperList", paperList);
		request.put("pager", pager);
		request.put("pageTitle", "纸张列表");
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getPaperByType() {
		paperList = wareHouseService
				.getPaperByType(type, currentPage, pageSize);
		pager = new Pager(currentPage, wareHouseService
				.getCountOfPaperByType(type));
		pager.setPageSize(pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("paperList", paperList);
		request.put("pager", pager);
		request.put("pageTitle", type + "纸张列表");
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getPaperById() {
		paper = wareHouseService.getPaperById(pid);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("paper", paper);
		return SUCCESS;
	}

	public String addPaper() {
		return SUCCESS;
	}

	public String editPaper_save() {
		Timestamp t = new Timestamp(new GregorianCalendar(TimeZone
				.getTimeZone("GMT")).getTimeInMillis());
		paper.setUpdateDate(t);
		wareHouseService.savePaper(paper);
		return SUCCESS;
	}

	public String removePaper() {
		wareHouseService.removePaper(pid);
		return SUCCESS;
	}

	public String PaperSupply() {
		Pager AFpager = new Pager(currentPage, afService.getCountofAllAFEs());
		AFpager.setPageSize(pageSize);
		List ListAFE = afService.getAFEs(currentPage, pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAFE", ListAFE);
		request.put("AFPager", AFpager);
		return SUCCESS;
	}

	public String paperSupplyInput() {
		wareHouseService.paperSupplyInput(afEId, paperGetMan);
		return SUCCESS;
	}

}
