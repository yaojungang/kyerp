package com.tyopf.service.qc;

import java.util.List;

import com.tyopf.vo.qc.InspectionRecordsOfPatrolAndExamineForMulticolorPresswork;

public interface IInspectionRecordsOfPatrolAndExamineForMulticolorPressworkService {

	public void save(
			InspectionRecordsOfPatrolAndExamineForMulticolorPresswork ins_bindingBindingfinshed);

	public void remove(long id);

	public InspectionRecordsOfPatrolAndExamineForMulticolorPresswork find(
			long id);

	public List<InspectionRecordsOfPatrolAndExamineForMulticolorPresswork> getList(
			int currentPage, int pageSize);

	public int getCountAll();
}
