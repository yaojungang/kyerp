package com.tyopf.service.qc;

import java.util.List;

import com.tyopf.vo.qc.InspectionRecordsOfBindingfinshed;

public interface IInspectionRecordsOfBindingfinshedService {

	public void save(InspectionRecordsOfBindingfinshed ins_bindingBindingfinshed);

	public void remove(long id);

	public InspectionRecordsOfBindingfinshed find(long id);

	public List<InspectionRecordsOfBindingfinshed> getList(int currentPage,
			int pageSize);

	public int getCountAll();
}
