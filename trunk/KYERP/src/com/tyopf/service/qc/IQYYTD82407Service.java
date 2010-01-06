package com.tyopf.service.qc;

import java.util.Date;
import java.util.List;

import com.tyopf.vo.qc.QYYTD82407;

public interface IQYYTD82407Service {

	public void save(QYYTD82407 ins_bindingBindingfinshed);

	public void remove(long id);

	public QYYTD82407 find(long id);

	public List<QYYTD82407> getList(int currentPage, int pageSize);

	public int getCountAll();

	public List<QYYTD82407> getByDateRange(Date startDate, Date endDate);
}
