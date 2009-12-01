package com.tyopf.service.qc;

import java.util.List;

import com.tyopf.vo.qc.InspectionRecordsOfFinshedGoods;

public interface IInspectionRecordsOfFinshedGoodsService {

	public void save(InspectionRecordsOfFinshedGoods ins_bindingBindingfinshed);

	public void remove(long id);

	public InspectionRecordsOfFinshedGoods find(long id);

	public List<InspectionRecordsOfFinshedGoods> getList(int currentPage,
			int pageSize);

	public int getCountAll();
}
