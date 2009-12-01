package com.tyopf.dao.qc;

import java.util.List;

import com.tyopf.vo.qc.InspectionRecordsOfPatrolAndExamineForMulticolorPresswork;

public interface IInspectionRecordsOfPatrolAndExamineForMulticolorPressworkDAO {

	public void save(
			InspectionRecordsOfPatrolAndExamineForMulticolorPresswork ins);

	public void remove(long id);

	public InspectionRecordsOfPatrolAndExamineForMulticolorPresswork find(
			long id);

	public List<InspectionRecordsOfPatrolAndExamineForMulticolorPresswork> getList(
			int currentPage, int pageSize);

	public int getCountAll();
}
