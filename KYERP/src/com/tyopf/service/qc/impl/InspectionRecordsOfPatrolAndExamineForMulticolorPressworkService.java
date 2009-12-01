package com.tyopf.service.qc.impl;

import java.util.List;

import com.tyopf.dao.qc.IInspectionRecordsOfPatrolAndExamineForMulticolorPressworkDAO;
import com.tyopf.service.qc.IInspectionRecordsOfPatrolAndExamineForMulticolorPressworkService;
import com.tyopf.vo.qc.InspectionRecordsOfPatrolAndExamineForMulticolorPresswork;

/**
 * @author y109 2009-11-30下午03:30:21
 */
public class InspectionRecordsOfPatrolAndExamineForMulticolorPressworkService
		implements
		IInspectionRecordsOfPatrolAndExamineForMulticolorPressworkService {
	private IInspectionRecordsOfPatrolAndExamineForMulticolorPressworkDAO	InspectionRecordsOfPatrolAndExamineForMulticolorPressworkDAO;

	public IInspectionRecordsOfPatrolAndExamineForMulticolorPressworkDAO getInspectionRecordsOfPatrolAndExamineForMulticolorPressworkDAO() {
		return InspectionRecordsOfPatrolAndExamineForMulticolorPressworkDAO;
	}

	public void setInspectionRecordsOfPatrolAndExamineForMulticolorPressworkDAO(
			IInspectionRecordsOfPatrolAndExamineForMulticolorPressworkDAO InspectionRecordsOfPatrolAndExamineForMulticolorPressworkDAO) {
		this.InspectionRecordsOfPatrolAndExamineForMulticolorPressworkDAO = InspectionRecordsOfPatrolAndExamineForMulticolorPressworkDAO;
	}

	@Override
	public InspectionRecordsOfPatrolAndExamineForMulticolorPresswork find(
			long id) {
		return this.InspectionRecordsOfPatrolAndExamineForMulticolorPressworkDAO
				.find(id);
	}

	@Override
	public List<InspectionRecordsOfPatrolAndExamineForMulticolorPresswork> getList(
			int currentPage, int pageSize) {
		return this.InspectionRecordsOfPatrolAndExamineForMulticolorPressworkDAO
				.getList(currentPage, pageSize);
	}

	@Override
	public void remove(long id) {
		this.InspectionRecordsOfPatrolAndExamineForMulticolorPressworkDAO
				.remove(id);
	}

	@Override
	public void save(
			InspectionRecordsOfPatrolAndExamineForMulticolorPresswork insBindingBindingfinshed) {
		this.InspectionRecordsOfPatrolAndExamineForMulticolorPressworkDAO
				.save(insBindingBindingfinshed);
	}

	@Override
	public int getCountAll() {
		return this.InspectionRecordsOfPatrolAndExamineForMulticolorPressworkDAO
				.getCountAll();
	}

}
