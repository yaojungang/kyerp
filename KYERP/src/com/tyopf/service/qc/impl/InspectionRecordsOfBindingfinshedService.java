package com.tyopf.service.qc.impl;

import java.util.List;

import com.tyopf.dao.qc.IInspectionRecordsOfBindingfinshedDAO;
import com.tyopf.service.qc.IInspectionRecordsOfBindingfinshedService;
import com.tyopf.vo.qc.InspectionRecordsOfBindingfinshed;

/**
 * @author y109 2009-11-30下午03:30:21
 */
public class InspectionRecordsOfBindingfinshedService implements
		IInspectionRecordsOfBindingfinshedService {
	private IInspectionRecordsOfBindingfinshedDAO	inspectionRecordsOfBindingfinshedDAO;

	public IInspectionRecordsOfBindingfinshedDAO getInspectionRecordsOfBindingfinshedDAO() {
		return inspectionRecordsOfBindingfinshedDAO;
	}

	public void setInspectionRecordsOfBindingfinshedDAO(
			IInspectionRecordsOfBindingfinshedDAO inspectionRecordsOfBindingfinshedDAO) {
		this.inspectionRecordsOfBindingfinshedDAO = inspectionRecordsOfBindingfinshedDAO;
	}

	@Override
	public InspectionRecordsOfBindingfinshed find(long id) {
		return this.inspectionRecordsOfBindingfinshedDAO.find(id);
	}

	@Override
	public List<InspectionRecordsOfBindingfinshed> getList(int currentPage,
			int pageSize) {
		return this.inspectionRecordsOfBindingfinshedDAO.getList(currentPage,
				pageSize);
	}

	@Override
	public void remove(long id) {
		this.inspectionRecordsOfBindingfinshedDAO.remove(id);
	}

	@Override
	public void save(InspectionRecordsOfBindingfinshed insBindingBindingfinshed) {
		this.inspectionRecordsOfBindingfinshedDAO
				.save(insBindingBindingfinshed);
	}

	@Override
	public int getCountAll() {
		return this.inspectionRecordsOfBindingfinshedDAO.getCountAll();
	}

}
