package com.tyopf.dao;

import java.util.List;

import com.tyopf.vo.BindingFactory;

public interface IBindingFactoryDAO {
	public List<BindingFactory> getAllBindingFactorys();
	public List<String> getRecentColumnName(String columnName,int recentSize); 

}
