package com.tyopf.dao.qc;

import java.util.List;

import com.tyopf.vo.qc.QYYTD82410;

public interface IQYYTD82410DAO {

	public void save(QYYTD82410 ins);

	public void remove(long id);

	public QYYTD82410 find(long id);

	public List<QYYTD82410> getList(int currentPage, int pageSize);

	public int getCountAll();
}