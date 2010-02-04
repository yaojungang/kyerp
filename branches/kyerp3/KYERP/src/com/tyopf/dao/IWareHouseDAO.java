package com.tyopf.dao;

import java.util.List;

import com.tyopf.vo.Paper;

public interface IWareHouseDAO {
	public List<Paper> getPaperList(int currentPage,int pageSize);	
	public List<Paper> getPaperByType(String type,int currentPage,int pageSize);
	
	public int getCountOfPaperList();
	public int getCountOfPaperByType(String type);
	
	public Paper getPaperById(int pid);
	public void savePaper(Paper paper);
	public void removePaper(int pid);
	
}
