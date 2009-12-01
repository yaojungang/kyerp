package com.tyopf.service.qc.impl;

import java.util.List;

import com.tyopf.dao.qc.IInspectionRecordsOfSemifinshedDAO;
import com.tyopf.service.qc.IInspectionRecordsOfSemifinshedService;
import com.tyopf.vo.qc.InspectionRecordsOfSemifinshed;

/**
 * @author y109 2009-11-30下午03:30:21
 */
public class InspectionRecordsOfSemifinshedService implements
		IInspectionRecordsOfSemifinshedService {
	private IInspectionRecordsOfSemifinshedDAO	InspectionRecordsOfSemifinshedDAO;

	public IInspectionRecordsOfSemifinshedDAO getInspectionRecordsOfSemifinshedDAO() {
		return InspectionRecordsOfSemifinshedDAO;
	}

	public void setInspectionRecordsOfSemifinshedDAO(
			IInspectionRecordsOfSemifinshedDAO InspectionRecordsOfSemifinshedDAO) {
		this.InspectionRecordsOfSemifinshedDAO = InspectionRecordsOfSemifinshedDAO;
	}

	@Override
	public InspectionRecordsOfSemifinshed find(long id) {
		return this.InspectionRecordsOfSemifinshedDAO.find(id);
	}

	@Override
	public List<InspectionRecordsOfSemifinshed> getList(int currentPage,
			int pageSize) {
		return this.InspectionRecordsOfSemifinshedDAO.getList(currentPage,
				pageSize);
	}

	@Override
	public void remove(long id) {
		this.InspectionRecordsOfSemifinshedDAO.remove(id);
	}

	@Override
	public void save(InspectionRecordsOfSemifinshed insBindingBindingfinshed) {
		this.InspectionRecordsOfSemifinshedDAO.save(insBindingBindingfinshed);
	}

	@Override
	public int getCountAll() {
		return this.InspectionRecordsOfSemifinshedDAO.getCountAll();
	}

}
