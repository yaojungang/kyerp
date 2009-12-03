package com.tyopf.service.qc.impl;

import java.util.List;

import com.tyopf.dao.qc.impl.QYYTD82410DAO;
import com.tyopf.service.qc.IQYYTD82410Service;
import com.tyopf.vo.qc.QYYTD82410;

/**
 * @author y109 2009-11-30下午03:30:21
 */
public class QYYTD82410Service implements IQYYTD82410Service {
	private QYYTD82410DAO	QYYTD82410DAO;

	public QYYTD82410DAO getQYYTD82410DAO() {
		return QYYTD82410DAO;
	}

	public void setQYYTD82410DAO(QYYTD82410DAO QYYTD82410DAO) {
		this.QYYTD82410DAO = QYYTD82410DAO;
	}

	@Override
	public QYYTD82410 find(long id) {
		return this.QYYTD82410DAO.find(id);
	}

	@Override
	public List<QYYTD82410> getList(int currentPage, int pageSize) {
		return this.QYYTD82410DAO.getList(currentPage, pageSize);
	}

	@Override
	public void remove(long id) {
		this.QYYTD82410DAO.remove(id);
	}

	@Override
	public void save(QYYTD82410 ins) {
		this.QYYTD82410DAO.save(ins);
	}

	@Override
	public int getCountAll() {
		return this.QYYTD82410DAO.getCountAll();
	}

}
