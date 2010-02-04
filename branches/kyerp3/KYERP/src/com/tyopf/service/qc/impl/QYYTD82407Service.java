package com.tyopf.service.qc.impl;

import java.util.Date;
import java.util.List;

import com.tyopf.dao.qc.IQYYTD82407DAO;
import com.tyopf.service.qc.IQYYTD82407Service;
import com.tyopf.vo.qc.QYYTD82407;

/**
 * @author y109 2009-11-30下午03:30:21
 */
public class QYYTD82407Service implements IQYYTD82407Service {
	private IQYYTD82407DAO	QYYTD82407DAO;

	public IQYYTD82407DAO getQYYTD82407DAO() {
		return QYYTD82407DAO;
	}

	public void setQYYTD82407DAO(IQYYTD82407DAO QYYTD82407DAO) {
		this.QYYTD82407DAO = QYYTD82407DAO;
	}

	@Override
	public QYYTD82407 find(long id) {
		return this.QYYTD82407DAO.find(id);
	}

	@Override
	public List<QYYTD82407> getList(int currentPage, int pageSize) {
		return this.QYYTD82407DAO.getList(currentPage, pageSize);
	}

	@Override
	public void remove(long id) {
		this.QYYTD82407DAO.remove(id);
	}

	@Override
	public void save(QYYTD82407 insBindingBindingfinshed) {
		this.QYYTD82407DAO.save(insBindingBindingfinshed);
	}

	@Override
	public int getCountAll() {
		return this.QYYTD82407DAO.getCountAll();
	}

	@Override
	public List<QYYTD82407> getByDateRange(Date startDate, Date endDate) {
		return this.QYYTD82407DAO.getByDateRange(startDate, endDate);
	}

}
