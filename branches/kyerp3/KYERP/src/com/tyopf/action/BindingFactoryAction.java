package com.tyopf.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IBindingFactoryService;


@SuppressWarnings("serial")
public class BindingFactoryAction extends ActionSupport {
	protected IBindingFactoryService bindingFactoryService;
	private List<String[]> columnNames;
	private String columnName="seriesName";
	private int recentSize=10;
	
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}


	public List<String[]> getColumnNames() {
		return columnNames;
	}


	public void setColumnNames(List<String[]> columnNames) {
		this.columnNames = columnNames;
	}



	public void setRecentSize(int recentSize) {
		this.recentSize = recentSize;
	}



	public IBindingFactoryService getBindingFactoryService() {
		return bindingFactoryService;
	}


	public void setBindingFactoryService(
			IBindingFactoryService bindingFactoryService) {
		this.bindingFactoryService = bindingFactoryService;
	}


	public String getColumnName() {
		return columnName;
	}


	public int getRecentSize() {
		return recentSize;
	}


	public String getRecentColumnName() throws Exception {
		columnNames = new ArrayList<String[]>();

		for(String c : bindingFactoryService.getRecentColumnName(columnName, recentSize)) {
			columnNames.add(new String[]{ c , c});
		}
		return SUCCESS;
	}

}
