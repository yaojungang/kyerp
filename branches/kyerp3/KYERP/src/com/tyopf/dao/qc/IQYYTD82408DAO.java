package com.tyopf.dao.qc;

import java.util.Date;
import java.util.List;

import com.tyopf.vo.qc.QYYTD82408;

public interface IQYYTD82408DAO {

	public void save(QYYTD82408 ins);

	public void remove(long id);

	public QYYTD82408 find(long id);

	public List<QYYTD82408> getList(int currentPage, int pageSize);

	public int getCountAll();

	public List<QYYTD82408> getByDateRange(Date startDate, Date endDate);
}
