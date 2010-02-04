package com.tyopf.service;

import java.util.List;

import com.tyopf.vo.BindingFactory;

public interface IBindingFactoryService {
	public List<BindingFactory> getAllBindingFactorys();
	public List<String> getRecentColumnName(String columnName,int recentSize); 
}
