package com.tyopf.dao.qc;

import java.util.List;

import com.tyopf.vo.qc.InspectionRecordsOfFinshedGoods;

public interface IInspectionRecordsOfFinshedGoodsDAO {

	public void save(InspectionRecordsOfFinshedGoods ins);

	public void remove(long id);

	public InspectionRecordsOfFinshedGoods find(long id);

	public List<InspectionRecordsOfFinshedGoods> getList(int currentPage,
			int pageSize);

	public int getCountAll();
}
