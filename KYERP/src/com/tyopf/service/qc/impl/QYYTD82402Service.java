package com.tyopf.service.qc.impl;

import java.util.List;

import com.tyopf.dao.qc.IQYYTD82402DAO;
import com.tyopf.service.qc.IQYYTD82402Service;
import com.tyopf.vo.qc.QYYTD82402;

/**
 * @author y109 2009-11-30下午03:30:21
 */
public class QYYTD82402Service implements
		IQYYTD82402Service {
	private IQYYTD82402DAO	QYYTD82402DAO;

	public IQYYTD82402DAO getQYYTD82402DAO() {
		return QYYTD82402DAO;
	}

	public void setQYYTD82402DAO(
			IQYYTD82402DAO QYYTD82402DAO) {
		this.QYYTD82402DAO = QYYTD82402DAO;
	}

	@Override
	public QYYTD82402 find(long id) {
		return this.QYYTD82402DAO.find(id);
	}

	@Override
	public List<QYYTD82402> getList(int currentPage,
			int pageSize) {
		return this.QYYTD82402DAO.getList(currentPage,
				pageSize);
	}

	@Override
	public void remove(long id) {
		this.QYYTD82402DAO.remove(id);
	}

	@Override
	public void save(QYYTD82402 insBindingBindingfinshed) {
		this.QYYTD82402DAO.save(insBindingBindingfinshed);
	}

	@Override
	public int getCountAll() {
		return this.QYYTD82402DAO.getCountAll();
	}

}
