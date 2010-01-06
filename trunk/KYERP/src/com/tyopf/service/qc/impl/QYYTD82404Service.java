package com.tyopf.service.qc.impl;

import java.util.Date;
import java.util.List;

import com.tyopf.dao.qc.IQYYTD82404DAO;
import com.tyopf.service.qc.IQYYTD82404Service;
import com.tyopf.vo.qc.QYYTD82404;

/**
 * @author y109 2009-11-30下午03:30:21
 */
public class QYYTD82404Service implements IQYYTD82404Service {
	private IQYYTD82404DAO	QYYTD82404DAO;

	public IQYYTD82404DAO getQYYTD82404DAO() {
		return QYYTD82404DAO;
	}

	public void setQYYTD82404DAO(IQYYTD82404DAO QYYTD82404DAO) {
		this.QYYTD82404DAO = QYYTD82404DAO;
	}

	@Override
	public QYYTD82404 find(long id) {
		return this.QYYTD82404DAO.find(id);
	}

	@Override
	public List<QYYTD82404> getList(int currentPage, int pageSize) {
		return this.QYYTD82404DAO.getList(currentPage, pageSize);
	}

	@Override
	public void remove(long id) {
		this.QYYTD82404DAO.remove(id);
	}

	@Override
	public void save(QYYTD82404 insBindingBindingfinshed) {
		this.QYYTD82404DAO.save(insBindingBindingfinshed);
	}

	@Override
	public int getCountAll() {
		return this.QYYTD82404DAO.getCountAll();
	}

	@Override
	public List<QYYTD82404> getByDateRange(Date startDate, Date endDate) {
		return this.QYYTD82404DAO.getByDateRange(startDate, endDate);
	}

}
