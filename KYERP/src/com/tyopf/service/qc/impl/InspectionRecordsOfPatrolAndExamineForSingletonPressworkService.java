package com.tyopf.service.qc.impl;

import java.util.List;

import com.tyopf.dao.qc.impl.InspectionRecordsOfPatrolAndExamineForSingletonPressworkDAO;
import com.tyopf.service.qc.IInspectionRecordsOfPatrolAndExamineForSingletonPressworkService;
import com.tyopf.vo.qc.InspectionRecordsOfPatrolAndExamineForSingletonPresswork;

/**
 * @author y109 2009-11-30下午03:30:21
 */
public class InspectionRecordsOfPatrolAndExamineForSingletonPressworkService
		implements
		IInspectionRecordsOfPatrolAndExamineForSingletonPressworkService {
	private InspectionRecordsOfPatrolAndExamineForSingletonPressworkDAO	inspectionRecordsOfPatrolAndExamineForSingletonPressworkDAO;

	public InspectionRecordsOfPatrolAndExamineForSingletonPressworkDAO getinspectionRecordsOfPatrolAndExamineForSingletonPressworkDAO() {
		return inspectionRecordsOfPatrolAndExamineForSingletonPressworkDAO;
	}

	public void setinspectionRecordsOfPatrolAndExamineForSingletonPressworkDAO(
			InspectionRecordsOfPatrolAndExamineForSingletonPressworkDAO inspectionRecordsOfPatrolAndExamineForSingletonPressworkDAO) {
		this.inspectionRecordsOfPatrolAndExamineForSingletonPressworkDAO = inspectionRecordsOfPatrolAndExamineForSingletonPressworkDAO;
	}

	@Override
	public InspectionRecordsOfPatrolAndExamineForSingletonPresswork find(long id) {
		return this.inspectionRecordsOfPatrolAndExamineForSingletonPressworkDAO
				.find(id);
	}

	@Override
	public List<InspectionRecordsOfPatrolAndExamineForSingletonPresswork> getList(
			int currentPage, int pageSize) {
		return this.inspectionRecordsOfPatrolAndExamineForSingletonPressworkDAO
				.getList(currentPage, pageSize);
	}

	@Override
	public void remove(long id) {
		this.inspectionRecordsOfPatrolAndExamineForSingletonPressworkDAO
				.remove(id);
	}

	@Override
	public void save(
			InspectionRecordsOfPatrolAndExamineForSingletonPresswork ins) {
		this.inspectionRecordsOfPatrolAndExamineForSingletonPressworkDAO
				.save(ins);
	}

	@Override
	public int getCountAll() {
		return this.inspectionRecordsOfPatrolAndExamineForSingletonPressworkDAO
				.getCountAll();
	}

}
