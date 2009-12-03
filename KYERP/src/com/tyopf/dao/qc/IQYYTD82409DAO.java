package com.tyopf.dao.qc;

import java.util.List;

import com.tyopf.vo.qc.QYYTD82409;

public interface IQYYTD82409DAO {

	public void save(QYYTD82409 ins);

	public void remove(long id);

	public QYYTD82409 find(long id);

	public List<QYYTD82409> getList(int currentPage, int pageSize);

	public int getCountAll();
}
