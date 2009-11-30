package com.tyopf.dao.qc;

import java.util.List;

import com.tyopf.vo.qc.InspectionRecordsOfBindingfinshed;

public interface IInspectionRecordsOfBindingfinshedDAO {

	public void save(InspectionRecordsOfBindingfinshed ins);

	public void remove(long id);

	public InspectionRecordsOfBindingfinshed find(long id);

	public List<InspectionRecordsOfBindingfinshed> getList(int currentPage,
			int pageSize);

	public int getCountAll();
}
