package com.tyopf.service.qc.impl;

import java.util.List;

import com.tyopf.dao.qc.IInspectionRecordsOfFinshedGoodsDAO;
import com.tyopf.service.qc.IInspectionRecordsOfFinshedGoodsService;
import com.tyopf.vo.qc.InspectionRecordsOfFinshedGoods;

/**
 * @author y109 2009-11-30下午03:30:21
 */
public class InspectionRecordsOfFinshedGoodsService implements
		IInspectionRecordsOfFinshedGoodsService {
	private IInspectionRecordsOfFinshedGoodsDAO	InspectionRecordsOfFinshedGoodsDAO;

	public IInspectionRecordsOfFinshedGoodsDAO getInspectionRecordsOfFinshedGoodsDAO() {
		return InspectionRecordsOfFinshedGoodsDAO;
	}

	public void setInspectionRecordsOfFinshedGoodsDAO(
			IInspectionRecordsOfFinshedGoodsDAO InspectionRecordsOfFinshedGoodsDAO) {
		this.InspectionRecordsOfFinshedGoodsDAO = InspectionRecordsOfFinshedGoodsDAO;
	}

	@Override
	public InspectionRecordsOfFinshedGoods find(long id) {
		return this.InspectionRecordsOfFinshedGoodsDAO.find(id);
	}

	@Override
	public List<InspectionRecordsOfFinshedGoods> getList(int currentPage,
			int pageSize) {
		return this.InspectionRecordsOfFinshedGoodsDAO.getList(currentPage,
				pageSize);
	}

	@Override
	public void remove(long id) {
		this.InspectionRecordsOfFinshedGoodsDAO.remove(id);
	}

	@Override
	public void save(InspectionRecordsOfFinshedGoods insBindingBindingfinshed) {
		this.InspectionRecordsOfFinshedGoodsDAO.save(insBindingBindingfinshed);
	}

	@Override
	public int getCountAll() {
		return this.InspectionRecordsOfFinshedGoodsDAO.getCountAll();
	}

}
