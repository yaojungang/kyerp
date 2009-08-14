package com.tyopf.dao;

import java.util.Date;
import java.util.List;

import com.tyopf.vo.AfBase;
import com.tyopf.vo.AfDispose;
import com.tyopf.vo.AfElement;
import com.tyopf.vo.AfProcess;
import com.tyopf.vo.AfQualityProblem;
import com.tyopf.vo.AfQualityProblemAttachment;
import com.tyopf.vo.AfValuation;

public interface IAFDAO {
	public List getAllAFs();
	public void addAF(AfBase af);
	public void removeAF(long afId);
	public void editAF(AfBase af);
	public void saveAF(AfBase af);
	public void saveAFE(AfElement afe);
	public List getAFs(int currentPage,int pageSize);
	public List getAFByHql(String hql);
	public AfBase getAFById(long afId);
	public AfBase getAFByNo(String AFNo);
	
	public int getNextAFNo(String AfType);
	public int getNextAFTypeNo(String AfType);
	public List<String> getRecentColumnName(String columnName,int recentSize);
	public List<AfBase> getTodayAFs();
	public int getTotalTodayAFs();
	
	public List<AfBase> getAFByType(String AFType,int currentPage,int pageSize);
	public List<AfBase> getAFByClient(String client,int currentPage,int pageSize);
	public List<AfBase> getAllAFByClient(String client);
	public List<AfBase> getAFByDate(Date date);
	public List<AfBase> getAFByAFNo(int AFNo);
	public List<AfBase> getAFByYZNo(int YZNo);
	public List<AfBase> getAFByName(String Name);
	public List<AfBase> getAFByYW(String YWName,int currentPage,int pageSize);
	public List<AfBase> getAFinAFNoList(String AFNoList);
	public List<AfBase> getAFinYZNoList(String YZNoList);
	
	public List<AfElement> getFinishedAFEByItem(String ItemName, int currentPage, int pageSize);
	public List<AfElement> getNotFinishedAFEByItem(String ItemName, int currentPage, int pageSize);
	public List<AfElement> getNotFinishedAFEByItemMachine(String ItemName,String Machine, int currentPage, int pageSize);
	public List<AfElement> getAFEByType(String AFType,int currentPage,int pageSize);
	public List<AfElement> getAFEByMachine(String MachineName,int currentPage,int pageSize);
	
	public List<AfDispose> getFinishedAFDByItem(String ItemName, int currentPage, int pageSize);
	public List<AfDispose> getNotFinishedAFDByItem(String ItemName, int currentPage, int pageSize);
	public List<AfBase> getFinishedDL(int currentPage, int pageSize);
	public List<AfBase> getNotFinishedDL(int currentPage, int pageSize);
	public List<AfBase> getTodayDL();
	public List<AfBase> getAFByNoRange(String AFType,long StartAFNo,long EndAFNo);
	public List<AfBase> getAFByNoRangeItemContent(String AFType, long StartAFNo,
			long EndAFNo,String Item,String Content);
	
	public List<AfBase> getAFByYWandType(String YWName,String AFType,long StartAFNo,long EndAFNo);
	
	public List<AfBase> getAFByClientNoRange(String client,String AFType,long StartAFNo,long EndAFNo);
	
	public double getMoneyByAFlist(List<AfBase> aflist);
	public double getMoneyGETByAFlist(List<AfBase> aflist);
	public double getMoneyByAFVlist(List<AfValuation> afvlist);
		
	public List<AfBase> getAFByChejian(String ChejianName,String AFType,long StartAFNo,long EndAFNo);
	public List<AfBase> getAFByMachine(String MachineName,int currentPage, int pageSize);
	public List<AfValuation> getAFVByChejian(String YWName,String ChejianName, String AFType, long StartAFNo, long EndAFNo);
		
	public int getTotalAFsByType(String AFType);
	public List<AfDispose> getAllAFByBindingFactory(String BindingFactoryName);
	public int getCountofAllAFByBindingFactory(String BindingFactoryName);
	public int getCountofCoverByMachineAndAFNo(String MachineName, long StartAFId, long EndAFId);	
	
	public List<AfDispose> getAFByBindingFactory(String BindingFactoryName,int currentPage,int pageSize);
	public List<AfDispose> getTodayAFsForBindingFactory(String BindingFactoryName);
		
	public List getAllAFEs();
	public int getCountofAllAFs();
	public int getCountofAllAFEs();
	public List getAFEs(int currentPage,int pageSize);
	public AfElement getAFEById(long afEId);
	public void editAFE(AfElement afe);
	public AfDispose getAFDById(long afDId);
	public void editAFD(AfDispose afd);
	public void removeAFE(long afEId);
	public void removeAFD(long afDId);
	public void removeAFV(long afVId);
	
	public int getCountofFinishedAFEByItem(String ItemName);
	public int getCountofNotFinishedAFEByItem(String ItemName);
	public int getCountofFinishedAFDByItem(String ItemName);
	public int getCountofNotFinishedAFDByItem(String ItemName);
	public int getCountofNotFinishedAFEByItemMachine(String ItemName,String machine);
	public int getCountofFinishedDL();
	public int getCountofNotFinishedDL();
	public int getCountofAFEbyType(String AFType);
	public int getCountofAFEbyMachine(String MachineName);
	public int getCountofAFbyYW(String YWName);
	
	public List getProcessByType(String processType,int currentPage,int pageSize);
	public int getCountofProcessByType(String processType);
	public void saveAfProcess(AfProcess afp);
	public void removeAfProcess(int afpId);
	public List<AfBase> searchAF(String searchOption, String searchValue);
	
	public List getAllQualityProblem(int currentPage,int pageSize);
	public AfQualityProblem getAFQPById(int id);
	public int getCountofAllQualityProblem();
	public void saveAfQualityProblem(AfQualityProblem afqp);
	public void removeAfQualityProblem(int id);
	public AfQualityProblem getLastAfQualityProblem();
	
	public void saveQpa(AfQualityProblemAttachment qpa);
	public void delQPAttachment(AfQualityProblemAttachment qpa);
	
	public AfQualityProblemAttachment getAFQPAttachmentById(int id);
}

