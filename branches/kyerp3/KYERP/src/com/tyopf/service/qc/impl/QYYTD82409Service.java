package com.tyopf.service.qc.impl;

import java.util.Date;
import java.util.List;

import com.tyopf.dao.qc.impl.QYYTD82409DAO;
import com.tyopf.service.qc.IQYYTD82409Service;
import com.tyopf.vo.qc.QYYTD82409;

/**
 * @author y109 2009-11-30下午03:30:21
 */
public class QYYTD82409Service implements IQYYTD82409Service {
	private QYYTD82409DAO	QYYTD82409DAO;

	public QYYTD82409DAO getQYYTD82409DAO() {
		return QYYTD82409DAO;
	}

	public void setQYYTD82409DAO(QYYTD82409DAO QYYTD82409DAO) {
		this.QYYTD82409DAO = QYYTD82409DAO;
	}

	@Override
	public QYYTD82409 find(long id) {
		return this.QYYTD82409DAO.find(id);
	}

	@Override
	public List<QYYTD82409> getList(int currentPage, int pageSize) {
		return this.QYYTD82409DAO.getList(currentPage, pageSize);
	}

	@Override
	public void remove(long id) {
		this.QYYTD82409DAO.remove(id);
	}

	@Override
	public void save(QYYTD82409 ins) {
		this.QYYTD82409DAO.save(ins);
	}

	@Override
	public int getCountAll() {
		return this.QYYTD82409DAO.getCountAll();
	}

	@Override
	public List<QYYTD82409> getByDateRange(Date startDate, Date endDate) {
		return this.QYYTD82409DAO.getByDateRange(startDate, endDate);
	}

}
