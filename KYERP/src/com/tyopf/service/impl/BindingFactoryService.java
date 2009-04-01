package com.tyopf.service.impl;

import java.util.List;

import com.tyopf.dao.IBindingFactoryDAO;
import com.tyopf.service.IBindingFactoryService;
import com.tyopf.vo.BindingFactory;

public class BindingFactoryService implements IBindingFactoryService {
	protected IBindingFactoryDAO bindingFactoryDAO;
	

	public IBindingFactoryDAO getBindingFactoryDAO() {
		return bindingFactoryDAO;
	}


	public void setBindingFactoryDAO(IBindingFactoryDAO bindingFactoryDAO) {
		this.bindingFactoryDAO = bindingFactoryDAO;
	}


	public List<BindingFactory> getAllBindingFactorys() {
		return bindingFactoryDAO.getAllBindingFactorys();
	}


	public List<String> getRecentColumnName(String columnName, int recentSize) {
		return bindingFactoryDAO.getRecentColumnName(columnName, recentSize);
	}

}
