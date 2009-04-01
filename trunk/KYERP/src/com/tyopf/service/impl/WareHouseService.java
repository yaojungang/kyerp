package com.tyopf.service.impl;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.opensymphony.xwork2.ActionContext;
import com.tyopf.dao.IAFDAO;
import com.tyopf.dao.ISystemDAO;
import com.tyopf.dao.IWareHouseDAO;
import com.tyopf.service.IWareHouseService;
import com.tyopf.vo.AfElement;
import com.tyopf.vo.Paper;
import com.tyopf.vo.User;

public class WareHouseService implements IWareHouseService {
	private IWareHouseDAO wareHouseDAO;
	protected IAFDAO afDAO;
	private ISystemDAO systemDAO;
	public IAFDAO getAfDAO() {
		return afDAO;
	}

	public ISystemDAO getSystemDAO() {
		return systemDAO;
	}

	public void setSystemDAO(ISystemDAO systemDAO) {
		this.systemDAO = systemDAO;
	}

	public void setAfDAO(IAFDAO afDAO) {
		this.afDAO = afDAO;
	}

	public IWareHouseDAO getWareHouseDAO() {
		return wareHouseDAO;
	}

	public void setWareHouseDAO(IWareHouseDAO wareHouseDAO) {
		this.wareHouseDAO = wareHouseDAO;
	}

	public Paper getPaperById(int pid) {
		return wareHouseDAO.getPaperById(pid);
	}

	public List<Paper> getPaperByType(String type, int currentPage, int pageSize) {
		return wareHouseDAO.getPaperByType(type, currentPage, pageSize);
	}

	public List<Paper> getPaperList(int currentPage, int pageSize) {
		return wareHouseDAO.getPaperList(currentPage, pageSize);
	}

	public void removePaper(int pid) {
		wareHouseDAO.removePaper(pid);
	}

	public void savePaper(Paper paper) {
		wareHouseDAO.savePaper(paper);
	}

	public int getCountOfPaperByType(String type) {
		return wareHouseDAO.getCountOfPaperByType(type);
	}

	public int getCountOfPaperList() {
		return wareHouseDAO.getCountOfPaperList();
	}

	@SuppressWarnings("unchecked")
	public void paperSupplyInput(long afEId,String paperGetMan) {
		AfElement afe = new AfElement();
		afe = afDAO.getAFEById(afEId);
		afe.setPaperGetMan(paperGetMan);
		afe.setPaperStatus(0);
		
		Map session = ActionContext.getContext().getSession();
		User u = (User) session.get("user");
		afe.setPaperSupplyMan(u.getEmployee().getRealname());
		
		Timestamp t = new Timestamp(new GregorianCalendar(TimeZone
				.getTimeZone("GMT")).getTimeInMillis());
		afe.setPaperSupplyTime(t);
		afDAO.saveAFE(afe);
	}

}
