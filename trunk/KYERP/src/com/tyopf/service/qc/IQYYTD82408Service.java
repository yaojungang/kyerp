package com.tyopf.service.qc;

import java.util.List;

import com.tyopf.vo.qc.QYYTD82408;

public interface IQYYTD82408Service {

	public void save(QYYTD82408 ins_bindingBindingfinshed);

	public void remove(long id);

	public QYYTD82408 find(long id);

	public List<QYYTD82408> getList(int currentPage, int pageSize);

	public int getCountAll();
}
