package com.tyopf.dao.qc;

import java.util.Date;
import java.util.List;

import com.tyopf.vo.qc.QYYTD82402;

public interface IQYYTD82402DAO {

	public void save(QYYTD82402 ins);

	public void remove(long id);

	public QYYTD82402 find(long id);

	public List<QYYTD82402> getList(int currentPage, int pageSize);

	public int getCountAll();

	public List<QYYTD82402> getByDateRange(Date startDate, Date endDate);

}
