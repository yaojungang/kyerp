package com.tyopf.service.qc;

import java.util.List;

import com.tyopf.vo.qc.InspectionRecordsOfSemifinshed;

public interface IInspectionRecordsOfSemifinshedService {

	public void save(InspectionRecordsOfSemifinshed ins);

	public void remove(long id);

	public InspectionRecordsOfSemifinshed find(long id);

	public List<InspectionRecordsOfSemifinshed> getList(int currentPage,
			int pageSize);

	public int getCountAll();
}
