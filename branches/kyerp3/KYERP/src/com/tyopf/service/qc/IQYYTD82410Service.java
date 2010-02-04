package com.tyopf.service.qc;

import java.util.Date;
import java.util.List;

import com.tyopf.vo.qc.QYYTD82410;

public interface IQYYTD82410Service {

	public void save(QYYTD82410 ins);

	public void remove(long id);

	public QYYTD82410 find(long id);

	public List<QYYTD82410> getList(int currentPage, int pageSize);

	public int getCountAll();

	public List<QYYTD82410> getByDateRange(Date startDate, Date endDate);
}
