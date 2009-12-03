package com.tyopf.service.qc.impl;

import java.util.List;

import com.tyopf.dao.qc.IQYYTD82408DAO;
import com.tyopf.service.qc.IQYYTD82408Service;
import com.tyopf.vo.qc.QYYTD82408;

/**
 * @author y109 2009-11-30下午03:30:21
 */
public class QYYTD82408Service
		implements
		IQYYTD82408Service {
	private IQYYTD82408DAO	QYYTD82408DAO;

	public IQYYTD82408DAO getQYYTD82408DAO() {
		return QYYTD82408DAO;
	}

	public void setQYYTD82408DAO(
			IQYYTD82408DAO QYYTD82408DAO) {
		this.QYYTD82408DAO = QYYTD82408DAO;
	}

	@Override
	public QYYTD82408 find(
			long id) {
		return this.QYYTD82408DAO
				.find(id);
	}

	@Override
	public List<QYYTD82408> getList(
			int currentPage, int pageSize) {
		return this.QYYTD82408DAO
				.getList(currentPage, pageSize);
	}

	@Override
	public void remove(long id) {
		this.QYYTD82408DAO
				.remove(id);
	}

	@Override
	public void save(
			QYYTD82408 insBindingBindingfinshed) {
		this.QYYTD82408DAO
				.save(insBindingBindingfinshed);
	}

	@Override
	public int getCountAll() {
		return this.QYYTD82408DAO
				.getCountAll();
	}

}
