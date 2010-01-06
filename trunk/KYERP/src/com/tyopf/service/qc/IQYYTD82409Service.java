package com.tyopf.service.qc;

import java.util.Date;
import java.util.List;

import com.tyopf.vo.qc.QYYTD82409;

public interface IQYYTD82409Service {

	public void save(QYYTD82409 ins);

	public void remove(long id);

	public QYYTD82409 find(long id);

	public List<QYYTD82409> getList(int currentPage, int pageSize);

	public int getCountAll();

	public List<QYYTD82409> getByDateRange(Date startDate, Date endDate);
}
