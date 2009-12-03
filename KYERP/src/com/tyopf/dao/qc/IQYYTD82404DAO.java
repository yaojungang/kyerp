package com.tyopf.dao.qc;

import java.util.List;

import com.tyopf.vo.qc.QYYTD82404;

public interface IQYYTD82404DAO {

	public void save(QYYTD82404 ins);

	public void remove(long id);

	public QYYTD82404 find(long id);

	public List<QYYTD82404> getList(int currentPage, int pageSize);

	public int getCountAll();
}
