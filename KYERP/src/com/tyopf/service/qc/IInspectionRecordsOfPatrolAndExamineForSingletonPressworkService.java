package com.tyopf.service.qc;

import java.util.List;

import com.tyopf.vo.qc.InspectionRecordsOfPatrolAndExamineForSingletonPresswork;

public interface IInspectionRecordsOfPatrolAndExamineForSingletonPressworkService {

	public void save(
			InspectionRecordsOfPatrolAndExamineForSingletonPresswork ins);

	public void remove(long id);

	public InspectionRecordsOfPatrolAndExamineForSingletonPresswork find(long id);

	public List<InspectionRecordsOfPatrolAndExamineForSingletonPresswork> getList(
			int currentPage, int pageSize);

	public int getCountAll();
}
