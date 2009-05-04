package com.tyopf.service.impl;

import java.util.Date;
import java.util.List;

import com.tyopf.dao.IAFDAO;
import com.tyopf.dao.ISystemDAO;
import com.tyopf.service.IAFService;
import com.tyopf.service.Statistics.Chart_ValueOfOutput;
import com.tyopf.service.Statistics.ValueOfOutput;
import com.tyopf.service.Valuation.CalSK;
import com.tyopf.vo.AfBase;
import com.tyopf.vo.AfDispose;
import com.tyopf.vo.AfElement;
import com.tyopf.vo.AfProcess;
import com.tyopf.vo.AfValuation;

public class AFService implements IAFService {
	protected IAFDAO afDAO;
	private ISystemDAO systemDAO;
	public AfValuation bjItem;	

	public ISystemDAO getSystemDAO() {
		return systemDAO;
	}

	public void setSystemDAO(ISystemDAO systemDAO) {
		this.systemDAO = systemDAO;
	}

	public IAFDAO getAfDAO() {
		return afDAO;
	}

	public void setAfDAO(IAFDAO afDAO) {
		this.afDAO = afDAO;
	}

	public AfValuation getBjItem() {
		return bjItem;
	}

	public void setBjItem(AfValuation bjItem) {
		this.bjItem = bjItem;
	}

	public IAFDAO getAFDAO() {
		return afDAO;
	}

	public void setafDAO(IAFDAO afdao) {
		afDAO = afdao;
	}

	public void addAF(AfBase af) {
		afDAO.addAF(af);
	}

	public void editAF(AfBase af) {
		afDAO.editAF(af);
	}

	public List getAFByHql(String hql) {
		return afDAO.getAFByHql(hql);
	}

	public AfBase getAFById(long afId) {
		return afDAO.getAFById(afId);
	}

	public List getAFs(int currentPage, int pageSize) {
		return afDAO.getAFs(currentPage, pageSize);
	}

	public List getAllAFs() {
		return afDAO.getAllAFs();
	}

	public void removeAF(long afId) {
		afDAO.removeAF(afId);
	}

	public int getNextAFNo(String AfType) {
		return afDAO.getNextAFNo(AfType);
	}

	public List<String> getRecentColumnName(String columnName, int recentSize) {
		return afDAO.getRecentColumnName(columnName, recentSize);
	}

	public List<AfBase> getTodayAFs() {
		return afDAO.getTodayAFs();
	}

	public List<AfBase> getAFByAFNo(int AFNo) {
		return afDAO.getAFByAFNo(AFNo);
	}

	public List<AfBase> getAFByName(String Name) {
		return afDAO.getAFByName(Name);
	}

	public List<AfBase> getAFByType(String AFType, int currentPage, int pageSize) {
		return afDAO.getAFByType(AFType, currentPage, pageSize);
	}

	public int getTotalAFsByType(String AFType) {
		return afDAO.getTotalAFsByType(AFType);
	}

	public List getAFEs(int currentPage, int pageSize) {
		return afDAO.getAFEs(currentPage, pageSize);
	}

	public void editAFE(AfElement afe) {
		afDAO.editAFE(afe);
	}

	public AfElement getAFEById(long afEId) {
		return afDAO.getAFEById(afEId);
	}

	public int getTotalTodayAFs() {
		return afDAO.getTotalTodayAFs();
	}

	public List getAllAFEs() {
		return afDAO.getAllAFEs();
	}

	public List<AfBase> getAFByClient(String client, int currentPage,
			int pageSize) {
		return afDAO.getAFByClient(client, currentPage, pageSize);
	}

	public List<AfBase> getAFByDate(Date date) {
		return afDAO.getAFByDate(date);
	}

	public List<AfBase> getAllAFByClient(String client) {
		return afDAO.getAllAFByClient(client);
	}

	public List<AfDispose> getAFByBindingFactory(String BindingFactoryName,
			int currentPage, int pageSize) {
		return afDAO.getAFByBindingFactory(BindingFactoryName, currentPage,
				pageSize);
	}

	public List<AfDispose> getAllAFByBindingFactory(String BindingFactoryName) {
		return afDAO.getAllAFByBindingFactory(BindingFactoryName);
	}

	public List<AfDispose> getTodayAFsForBindingFactory(
			String BindingFactoryName) {
		return afDAO.getTodayAFsForBindingFactory(BindingFactoryName);
	}

	public void editAFD(AfDispose afd) {
		afDAO.editAFD(afd);
	}

	public AfDispose getAFDById(long afDId) {
		return afDAO.getAFDById(afDId);
	}

	public List<AfElement> getFinishedAFEByItem(String ItemName,
			int currentPage, int pageSize) {
		return afDAO.getFinishedAFEByItem(ItemName, currentPage, pageSize);
	}

	public List<AfElement> getNotFinishedAFEByItem(String ItemName,
			int currentPage, int pageSize) {
		return afDAO.getNotFinishedAFEByItem(ItemName, currentPage, pageSize);
	}

	public List<AfDispose> getFinishedAFDByItem(String ItemName,
			int currentPage, int pageSize) {
		return afDAO.getFinishedAFDByItem(ItemName, currentPage, pageSize);
	}

	public List<AfBase> getFinishedDL(int currentPage, int pageSize) {
		return afDAO.getFinishedDL(currentPage, pageSize);
	}

	public List<AfDispose> getNotFinishedAFDByItem(String ItemName,
			int currentPage, int pageSize) {
		return afDAO.getNotFinishedAFDByItem(ItemName, currentPage, pageSize);
	}

	public List<AfBase> getNotFinishedDL(int currentPage, int pageSize) {
		return afDAO.getNotFinishedDL(currentPage, pageSize);
	}

	public List<AfBase> getTodayDL() {
		return afDAO.getTodayDL();
	}

	public int getCountofAllAFEs() {
		return afDAO.getCountofAllAFEs();
	}

	public int getCountofAllAFByBindingFactory(String BindingFactoryName) {
		return afDAO.getCountofAllAFByBindingFactory(BindingFactoryName);
	}

	public int getCountofFinishedAFDByItem(String ItemName) {
		return afDAO.getCountofFinishedAFDByItem(ItemName);
	}

	public int getCountofFinishedAFEByItem(String ItemName) {
		return afDAO.getCountofFinishedAFEByItem(ItemName);
	}

	public int getCountofFinishedDL() {
		return afDAO.getCountofFinishedDL();
	}

	public int getCountofNotFinishedAFDByItem(String ItemName) {
		return afDAO.getCountofNotFinishedAFDByItem(ItemName);
	}

	public int getCountofNotFinishedAFEByItem(String ItemName) {
		return afDAO.getCountofNotFinishedAFEByItem(ItemName);
	}

	public int getCountofNotFinishedDL() {
		return afDAO.getCountofNotFinishedDL();
	}

	public AfBase getAFByNo(String AFNo) {
		return afDAO.getAFByNo(AFNo);
	}

	public List<AfBase> getAFByNoRange(String AFType, long StartAFNo,
			long EndAFNo) {
		return afDAO.getAFByNoRange(AFType, StartAFNo, EndAFNo);
	}

	public int getCountofCoverByMachineAndAFNo(String MachineName,
			long StartAFId, long EndAFId) {
		return afDAO.getCountofCoverByMachineAndAFNo(MachineName, StartAFId,
				EndAFId);
	}

	@SuppressWarnings("unchecked")
	public List<AfValuation> AFcal(long afId) {		
		CalSK cal= new CalSK();
		return cal.calSK(afDAO.getAFById(afId));
	}

	public List<AfBase> getAFByMachine(String MachineName, int StartAFNo,
			int EndAFNo) {
		return afDAO.getAFByMachine(MachineName, StartAFNo, EndAFNo);
	}

	public List<AfElement> getNotFinishedAFEByItemMachine(String ItemName,
			String Machine, int currentPage, int pageSize) {
		return afDAO.getNotFinishedAFEByItemMachine(ItemName, Machine,
				currentPage, pageSize);
	}

	public List<AfElement> getAFEByMachine(String MachineName, int currentPage, int pageSize) {
		return afDAO.getAFEByMachine(MachineName, currentPage, pageSize);
	}

	public List<AfElement> getAFEByType(String AFType, int currentPage, int pageSize) {
		return afDAO.getAFEByType(AFType, currentPage, pageSize);
	}

	public int getCountofAFEbyMachine(String MachineName) {
		return afDAO.getCountofAFEbyMachine(MachineName);
	}

	public int getCountofAFEbyType(String AFType) {
		return afDAO.getCountofAFEbyType(AFType);
	}

	public List<AfBase> getAFByYW(String YWName, int currentPage, int pageSize) {
		return afDAO.getAFByYW(YWName, currentPage, pageSize);
	}

	public int getCountofAFbyYW(String YWName) {
		return afDAO.getCountofAFbyYW(YWName);
	}

	public void removeAFD(long afDId) {
		afDAO.removeAFD(afDId);
	}

	public void removeAFE(long afEId) {
		afDAO.removeAFE(afEId);
	}

	public void removeAFV(long afVId) {
		afDAO.removeAFV(afVId);
	}

	public void saveAF(AfBase af) {
		afDAO.saveAF(af);
	}

	public void saveAFE(AfElement afe) {
		afDAO.saveAFE(afe);
	}

	public List<AfBase> getAFByYWandType(String YWName, String AFType, long StartAFNo, long EndAFNo) {
		return afDAO.getAFByYWandType(YWName, AFType, StartAFNo, EndAFNo);
	}


	public List<AfBase> getAFByClientNoRange(String client, String AFType, long StartAFNo, long EndAFNo) {
		return afDAO.getAFByClientNoRange(client, AFType, StartAFNo, EndAFNo);
	}

	public double getMoneyByAFlist(List<AfBase> aflist) {
		return afDAO.getMoneyByAFlist(aflist);
	}

	public List<AfBase> getAFByChejian(String ChejianName, String AFType, long StartAFNo, long EndAFNo) {
		return afDAO.getAFByChejian(ChejianName, AFType, StartAFNo, EndAFNo);
	}

	public List<AfValuation> getAFVByChejian(String YWName,String ChejianName, String AFType, long StartAFNo, long EndAFNo) {
		return afDAO.getAFVByChejian(YWName,ChejianName, AFType, StartAFNo, EndAFNo);
	}

	public double getMoneyByAFVlist(List<AfValuation> afvlist) {
		return afDAO.getMoneyByAFVlist(afvlist);
	}

	public double getMoneyGETByAFlist(List<AfBase> aflist) {
		return afDAO.getMoneyGETByAFlist(aflist);
	}

	public ValueOfOutput getChart_ValueOfOutput(long SKStartAFNo,
			long LHStartAFNo, long SKEndAFNo, long LHEndAFNo) {
		Chart_ValueOfOutput cv = new Chart_ValueOfOutput();
		return cv.getChart_ValueOfOutput(SKStartAFNo, LHStartAFNo, SKEndAFNo, LHEndAFNo);
	}

	public List<AfBase> getAFinAFNoList(String AFNoList) {
		return afDAO.getAFinAFNoList(AFNoList);
	}

	public List<AfBase> getAFinYZNoList(String YZNoList) {
		return afDAO.getAFinYZNoList(YZNoList);
	}

	public List<AfBase> getAFByNoRangeItemContent(String AFType,
			long StartAFNo, long EndAFNo, String Item, String Content) {
		return afDAO.getAFByNoRangeItemContent(AFType, StartAFNo, EndAFNo, Item, Content);
	}

	public int getCountofProcessByType(String processType) {
		return afDAO.getCountofProcessByType(processType);
	}

	public List getProcessByType(String processType, int currentPage,
			int pageSize) {
		return afDAO.getProcessByType(processType, currentPage, pageSize);
	}

	public void removeAfProcess(int afpId) {
		afDAO.removeAfProcess(afpId);
	}

	public void saveAfProcess(AfProcess afp) {
		afDAO.saveAfProcess(afp);
	}

	public int getCountofAllAFs() {
		return afDAO.getCountofAllAFs();
	}

	public int getCountofNotFinishedAFEByItemMachine(String ItemName,
			String machine) {
		return afDAO.getCountofNotFinishedAFEByItemMachine(ItemName, machine);
	}

	public int getNextAFTypeNo(String AfType) {
		return afDAO.getNextAFTypeNo(AfType);
	}

	@Override
	public List<AfBase> getAFByYZNo(int YZNo) {
		return afDAO.getAFByYZNo(YZNo);
	}

	@Override
	public void filmPlaceChange(long afEId, int filmPlace) {
		AfElement afe = afDAO.getAFEById(afEId);
		afe.setFilmPlace(filmPlace);
		afDAO.saveAFE(afe);
	}


}
