package com.tyopf.service.qc;

import java.util.Date;
import java.util.List;

import com.tyopf.vo.qc.QYYTD82404;

public interface IQYYTD82404Service {

	public void save(QYYTD82404 ins_bindingBindingfinshed);

	public void remove(long id);

	public QYYTD82404 find(long id);

	public List<QYYTD82404> getList(int currentPage, int pageSize);

	public int getCountAll();

	public List<QYYTD82404> getByDateRange(Date startDate, Date endDate);
}
