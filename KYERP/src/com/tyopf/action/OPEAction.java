package com.tyopf.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IAFService;
import com.tyopf.service.IBindingFactoryService;
import com.tyopf.service.IClientService;
import com.tyopf.service.IUserService;
import com.tyopf.util.Pager;
import com.tyopf.vo.AfBase;
import com.tyopf.vo.AfDispose;
import com.tyopf.vo.AfElement;
import com.tyopf.vo.AfValuation;
import com.tyopf.vo.ClientC;
import com.tyopf.vo.Employee;
import com.tyopf.vo.User;

@SuppressWarnings("serial")
public class OPEAction extends ActionSupport {

	protected AfBase					af;

	protected IAFService				afService;

	protected IClientService			clientService;

	protected IUserService				userService;

	protected IBindingFactoryService	bindingFactoryService;

	protected AfElement					afe;

	protected AfDispose					afd;

	private List<AfElement>				afes;

	private List<AfDispose>				afds;

	private List<AfValuation>			afvs;

	private List<AfValuation>			bj;

	private Integer						moneyStatus;

	private Integer						fapiaoStatus;

	private String						moneyInputRemark;

	private String						moneyRemark;

	private Integer						currentPage		= 1;

	private Timestamp					moneyTime;

	private Timestamp					fapiaoTime;

	private long						afId;

	private String						message;

	private String						AFType			= "SK";

	private String						AFPage			= "Books";

	private String						pressworkName	= "";

	private String						AFNo			= "";

	private Integer						intAFNo			= 0;
	private Integer						intYZNo			= 0;
	private Integer						recentSize		= 100;

	private double						moneyFact		= 0;

	private long						StartAFNo		= 20070001;

	private long						EndAFNo			= 20079999;

	private String						YWName;

	private String						client;

	private String						moneyGiveMan;

	private String						ChejianName;

	private String						columnName;

	private String						clientName;

	private String						linkmanName;

	private List<String[]>				columnNames;

	private List<String[]>				ywlist;

	private List<ClientC>				clientList;

	private List<String[]>				pressworklist;

	public List<ClientC> getClientList() {
		return clientList;
	}

	public void setClientList(List<ClientC> clientList) {
		this.clientList = clientList;
	}

	public List<AfValuation> getAfvs() {
		return afvs;
	}

	public void setAfvs(List<AfValuation> afvs) {
		this.afvs = afvs;
	}

	public String getAFPage() {
		return AFPage;
	}

	public void setAFPage(String page) {
		AFPage = page;
	}

	public Integer getIntYZNo() {
		return intYZNo;
	}

	public void setIntYZNo(Integer intYZNo) {
		this.intYZNo = intYZNo;
	}

	public Timestamp getMoneyTime() {
		return moneyTime;
	}

	public Timestamp getFapiaoTime() {
		return fapiaoTime;
	}

	public void setFapiaoTime(Timestamp fapiaoTime) {
		this.fapiaoTime = fapiaoTime;
	}

	public Integer getFapiaoStatus() {
		return fapiaoStatus;
	}

	public void setFapiaoStatus(Integer fapiaoStatus) {
		this.fapiaoStatus = fapiaoStatus;
	}

	public void setMoneyTime(Timestamp moneyTime) {
		this.moneyTime = moneyTime;
	}

	public String getMoneyInputRemark() {
		return moneyInputRemark;
	}

	public void setMoneyInputRemark(String moneyInputRemark) {
		this.moneyInputRemark = moneyInputRemark;
	}

	public Integer getMoneyStatus() {
		return moneyStatus;
	}

	public void setMoneyStatus(Integer moneyStatus) {
		this.moneyStatus = moneyStatus;
	}

	public String getMoneyRemark() {
		return moneyRemark;
	}

	public void setMoneyRemark(String moneyRemark) {
		this.moneyRemark = moneyRemark;
	}

	public List<String[]> getPressworklist() {
		return pressworklist;
	}

	public void setPressworklist(List<String[]> pressworklist) {
		this.pressworklist = pressworklist;
	}

	public String getLinkmanName() {
		return linkmanName;
	}

	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public List<String[]> getYwlist() {
		return ywlist;
	}

	public void setYwlist(List<String[]> ywlist) {
		this.ywlist = ywlist;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Integer getRecentSize() {
		return recentSize;
	}

	public void setRecentSize(Integer recentSize) {
		this.recentSize = recentSize;
	}

	public List<String[]> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(List<String[]> columnNames) {
		this.columnNames = columnNames;
	}

	public String getChejianName() {
		return ChejianName;
	}

	public void setChejianName(String chejianName) {
		ChejianName = chejianName;
	}

	public String getMoneyGiveMan() {
		return moneyGiveMan;
	}

	public void setMoneyGiveMan(String moneyGiveMan) {
		this.moneyGiveMan = moneyGiveMan;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getYWName() {
		return YWName;
	}

	public void setYWName(String name) {
		YWName = name;
	}

	public long getEndAFNo() {
		return EndAFNo;
	}

	public void setEndAFNo(long endAFNo) {
		EndAFNo = endAFNo;
	}

	public long getStartAFNo() {
		return StartAFNo;
	}

	public void setStartAFNo(long startAFNo) {
		StartAFNo = startAFNo;
	}

	public double getMoneyFact() {
		return moneyFact;
	}

	public void setMoneyFact(double moneyFact) {
		this.moneyFact = moneyFact;
	}

	public Integer getIntAFNo() {
		return intAFNo;
	}

	public void setIntAFNo(Integer intAFNo) {
		this.intAFNo = intAFNo;
	}

	public String getAFNo() {
		return AFNo;
	}

	public void setAFNo(String no) {
		AFNo = no;
	}

	public String getPressworkName() {
		return pressworkName;
	}

	public void setPressworkName(String pressworkName) {
		this.pressworkName = pressworkName;
	}

	public String getAFType() {
		return AFType;
	}

	public void setAFType(String type) {
		AFType = type;
	}

	public IBindingFactoryService getBindingFactoryService() {
		return bindingFactoryService;
	}

	public void setBindingFactoryService(
			IBindingFactoryService bindingFactoryService) {
		this.bindingFactoryService = bindingFactoryService;
	}

	public AfDispose getAfd() {
		return afd;
	}

	public void setAfd(AfDispose afd) {
		this.afd = afd;
	}

	public IClientService getClientService() {
		return clientService;
	}

	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

	public AfElement getAfe() {
		return afe;
	}

	public void setAfe(AfElement afe) {
		this.afe = afe;
	}

	public AfBase getAf() {
		return af;
	}

	public void setAf(AfBase af) {
		this.af = af;
	}

	public long getAfId() {
		return afId;
	}

	public void setAfId(long afId) {
		this.afId = afId;
	}

	public IAFService getAfService() {
		return afService;
	}

	public void setAfService(IAFService afService) {
		this.afService = afService;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<AfElement> getAfes() {
		return afes;
	}

	public void setAfes(List<AfElement> afes) {
		this.afes = afes;
	}

	public List<AfDispose> getAfds() {
		return afds;
	}

	public void setAfds(List<AfDispose> afds) {
		this.afds = afds;
	}

	public List<AfValuation> getBj() {
		return bj;
	}

	public void setBj(List<AfValuation> bj) {
		this.bj = bj;
	}

	@SuppressWarnings("unchecked")
	public String index() throws Exception {
		List ListAF = afService.getTodayAFs();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", ListAF);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String ListAF_Eva() throws Exception {
		int TotalAFs = afService.getCountofAllAFs();
		Pager AFpager = new Pager(currentPage, TotalAFs);
		AFpager.setPageSize(50);
		List ListAF = afService.getAFs(currentPage, AFpager.getPageSize());
		List TodayListAF = afService.getTodayAFs();
		List ListAF_SK = afService.getAFByType("SK", currentPage, 50);
		List ListAF_LH = afService.getAFByType("LH", currentPage, 50);

		Map request = (Map) ActionContext.getContext().get("request");

		request.put("ListAF", ListAF);
		request.put("TodayListAF", TodayListAF);
		request.put("ListAF_SK", ListAF_SK);
		request.put("ListAF_LH", ListAF_LH);

		request.put("AFPager", AFpager);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getAFByType() throws Exception {

		int TotalAFs = afService.getTotalAFsByType(AFType);
		Pager AFpager = new Pager(currentPage, TotalAFs);
		AFpager.setPageSize(50);
		List ListAF = afService.getAFByType(AFType, currentPage, 50);

		Map request = (Map) ActionContext.getContext().get("request");

		request.put("ListAF", ListAF);

		request.put("AFPager", AFpager);
		return SUCCESS;

	}

	@SuppressWarnings("unchecked")
	public String getAFByName() throws Exception {
		List ListAF = afService.getAFByName(pressworkName);
		Map request = (Map) ActionContext.getContext().get("request");
		Pager AFpager = new Pager(currentPage, ListAF.size());
		AFpager.setPageSize(ListAF.size());
		request.put("AFPager", AFpager);
		request.put("ListAF", ListAF);
		request.put("pageTitle", "按印品名称查询任务单");
		return SUCCESS;

	}

	@SuppressWarnings("unchecked")
	public String getAFByAFNo() throws Exception {
		List ListAF = afService.getAFByAFNo(intAFNo);
		Map request = (Map) ActionContext.getContext().get("request");
		Pager AFpager = new Pager(currentPage, ListAF.size());
		AFpager.setPageSize(ListAF.size());
		request.put("AFPager", AFpager);
		request.put("ListAF", ListAF);
		request.put("pageTitle", "按任务单号查询任务单");
		return SUCCESS;

	}

	@SuppressWarnings("unchecked")
	public String getAFByYZNo() throws Exception {
		List ListAF = afService.getAFByYZNo(intYZNo);
		Map request = (Map) ActionContext.getContext().get("request");
		Pager AFpager = new Pager(currentPage, ListAF.size());
		AFpager.setPageSize(ListAF.size());
		request.put("AFPager", AFpager);
		request.put("ListAF", ListAF);
		request.put("pageTitle", "按印制单号查询任务单");
		return SUCCESS;

	}

	@SuppressWarnings("unchecked")
	public String addAF() throws Exception {
		Map session = ActionContext.getContext().getSession();
		User u = (User) session.get("user");
		Timestamp today = new Timestamp(new GregorianCalendar(TimeZone
				.getTimeZone("GMT")).getTimeInMillis());
		session.put("today", today);
		List bindingFactoryList = bindingFactoryService.getAllBindingFactorys();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("bindingFactoryList", bindingFactoryList);
		Logger logger = Logger.getLogger(this.getClass());
		logger.warn(u.getUsername() + " Start a New AF !");
		session.put("af", null);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String addAF_Base() throws Exception {
		// 设置任务单状态为可修改
		af.setAfStatus(1);
		Set afdSet = new HashSet();
		if (afds != null) {
			for (Iterator iterator = afds.iterator(); iterator.hasNext();) {
				AfDispose afd = (AfDispose) iterator.next();
				if (!(null == afd.getAfDItem() || "".equals(afd.getAfDItem()
						.trim()))) {
					// af.getAfDispose().add(afd);
					afd.setAfBase(af);
					afdSet.add(afd);
				}
			}
		}
		af.setAfDispose(afdSet);
		Timestamp t = new Timestamp(new GregorianCalendar(TimeZone
				.getTimeZone("GMT+8")).getTimeInMillis());
		af.setAd(t);

		Map session = ActionContext.getContext().getSession();
		session.put("af", af);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String addAF_Element() throws Exception {
		Map session = ActionContext.getContext().getSession();
		AfBase af = (AfBase) session.get("af");

		af.getAfElement().add(afe);
		afe.setAfBase(af);

		session.put("af", af);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("AFInfo", af);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String addAF_Dispose() throws Exception {
		Map session = ActionContext.getContext().getSession();
		AfBase af = (AfBase) session.get("af");

		af.getAfDispose().add(afd);
		afd.setAfBase(af);

		session.put("af", af);
		List bindingFactoryList = bindingFactoryService.getAllBindingFactorys();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("bindingFactoryList", bindingFactoryList);
		request.put("AFInfo", af);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String addElement() throws Exception {
		Map session = ActionContext.getContext().getSession();
		AfBase af = (AfBase) afService.getAFById(afId);
		session.put("af", af);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String addDispose() throws Exception {
		AfBase af = (AfBase) afService.getAFById(afId);
		Map session = ActionContext.getContext().getSession();
		session.put("af", af);
		List bindingFactoryList = bindingFactoryService.getAllBindingFactorys();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("bindingFactoryList", bindingFactoryList);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String FPAddDispose() throws Exception {
		AfBase af = (AfBase) afService.getAFById(afId);
		Map session = ActionContext.getContext().getSession();
		session.put("af", af);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String FPAF_save() throws Exception {
		Map session = ActionContext.getContext().getSession();
		Set afdSet = new HashSet();
		Set afvSet = new HashSet();
		if (afvs != null) {
			for (Iterator iterator = afvs.iterator(); iterator.hasNext();) {
				AfValuation afv = (AfValuation) iterator.next();

				if ("零二".equals(afv.getChejian())) {
					afv.setChejian("02");
				}
				if ("零五".equals(afv.getChejian())) {
					afv.setChejian("05");
				}

				AfDispose afd = new AfDispose();
				afd.setAfDItem(afv.getItemName());
				afd.setAfDFactory(afv.getChejian());
				afd.setAfDRemark(afv.getCalProcess());
				afd.setAfDAmount(new Long(afv.getAmount()));
				af.getAfDispose().add(afd);
				afd.setAfBase(af);
				afdSet.add(afd);

				afv.setTotalAmount(afv.getDanjia() * afv.getAmount());
				afv.setCalProcess(afv.getDanjia() + "x" + afv.getAmount());
				af.getAfValuation().add(afv);
				afv.setAfBase(af);
				afvSet.add(afv);

			}
		}
		// 计算应付金额
		double moneyShould = 0.0;
		if (afvSet != null) {
			for (Iterator iterator = afvSet.iterator(); iterator.hasNext();) {
				AfValuation afvs = (AfValuation) iterator.next();
				if (!(null == afvs.getItemName() || "".equals(afvs
						.getItemName().trim()))) {
					moneyShould = moneyShould + afvs.getTotalAmount();
				}
			}
		}
		af.setMoneyShould(moneyShould);
		af.setAfDispose(afdSet);
		af.setAfValuation(afvSet);
		// 修改开单人为当前用户
		User u = (User) session.get("user");
		af.setFmp(u.getEmployee().getRealname());
		// 修改时间为当前
		Timestamp t = new Timestamp(new GregorianCalendar(TimeZone
				.getTimeZone("GMT")).getTimeInMillis());
		af.setLastModify(t);
		// 保存任务单
		afService.saveAF(af);
		Logger logger = Logger.getLogger(this.getClass());
		logger.warn(u.getUsername() + " save AF:" + af.getIso() + af.getAfNo());
		AfBase afg = (AfBase) afService.getAFById(af.getAfId());
		if (afg != null) {
			Map request = (Map) ActionContext.getContext().get("request");
			request.put("AFInfo", afg);
			AFPage = afg.getAfType();
			if ("Books".equals(afg.getAfType()) || "".equals(afg.getAfType())) {
				AFPage = "AF";
			}
			return SUCCESS;
		}
		message = "获取任务单失败!";
		return ERROR;
	}

	@SuppressWarnings("unchecked")
	public String FPAddDispose_save() throws Exception {
		Map session = ActionContext.getContext().getSession();
		AfBase af = (AfBase) session.get("af");

		Set afdSet = af.getAfDispose();
		Set afvSet = af.getAfValuation();
// Set afdSet = new HashSet();
// Set afvSet = new HashSet();
		if (afvs != null) {
			for (Iterator iterator = afvs.iterator(); iterator.hasNext();) {
				AfValuation afv = (AfValuation) iterator.next();

				if ("零二".equals(afv.getChejian())) {
					afv.setChejian("02");
				}
				if ("零五".equals(afv.getChejian())) {
					afv.setChejian("05");
				}

				AfDispose afd = new AfDispose();
				afd.setAfDItem(afv.getItemName());
				afd.setAfDFactory(afv.getChejian());
				afd.setAfDRemark(afv.getCalProcess());
				afd.setAfDAmount(new Long(afv.getAmount()));
				af.getAfDispose().add(afd);
				afd.setAfBase(af);
				afdSet.add(afd);

				afv.setTotalAmount(afv.getDanjia() * afv.getAmount());
				afv.setCalProcess(afv.getDanjia() + "x" + afv.getAmount());
				af.getAfValuation().add(afv);
				afv.setAfBase(af);
				afvSet.add(afv);

			}
		}
		// 计算应付金额
		double moneyShould = 0.0;
		if (afvSet != null) {
			for (Iterator iterator = afvSet.iterator(); iterator.hasNext();) {
				AfValuation afvs = (AfValuation) iterator.next();
				if (!(null == afvs.getItemName() || "".equals(afvs
						.getItemName().trim()))) {
					moneyShould = moneyShould + afvs.getTotalAmount();
				}
			}
		}
		af.setMoneyShould(moneyShould);
		af.setAfDispose(afdSet);
		af.setAfValuation(afvSet);
		// 修改开单人为当前用户
		User u = (User) session.get("user");
		af.setFmp(u.getEmployee().getRealname());
		// 修改时间为当前
		Timestamp t = new Timestamp(new GregorianCalendar(TimeZone
				.getTimeZone("GMT")).getTimeInMillis());
		af.setLastModify(t);
		// 保存任务单
		afService.saveAF(af);
		Logger logger = Logger.getLogger(this.getClass());
		logger.warn(u.getUsername() + " save AF:" + af.getIso() + af.getAfNo());
		AfBase afg = (AfBase) afService.getAFById(af.getAfId());
		if (afg != null) {
			Map request = (Map) ActionContext.getContext().get("request");
			request.put("AFInfo", afg);
			AFPage = afg.getAfType();
			if ("Books".equals(afg.getAfType()) || "".equals(afg.getAfType())) {
				AFPage = "AF";
			}
			return SUCCESS;
		}
		message = "获取任务单失败!";
		return ERROR;
	}

	public String delAF() throws Exception {
		afService.removeAF(afId);
		message = message + "删除成功!";
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String saveAF() throws Exception {
		Set afeSet = new HashSet();
		Set afdSet = new HashSet();
		Timestamp t = new Timestamp(new GregorianCalendar(TimeZone
				.getTimeZone("GMT")).getTimeInMillis());
		if ("".equals(af.getAfId()) || 0 == af.getAfId()) {
			af.setAfNo(afService.getNextAFNo(af.getIso()));
			af.setAd(t);
		}
		if (afes != null) {
			for (Iterator iterator = afes.iterator(); iterator.hasNext();) {
				AfElement afe = (AfElement) iterator.next();
				if (!("Del".equals(afe.getEType().trim()))) {
					afe.setAfBase(af);
					afeSet.add(afe);
				} else {
					if (afe.getAfEId() != 0) {
						afService.removeAFE(afe.getAfEId());
					}
				}
			}

		}

		if (afds != null) {
			for (Iterator iterator = afds.iterator(); iterator.hasNext();) {
				AfDispose afd = (AfDispose) iterator.next();
				if (!(null == afd.getAfDItem() || "".equals(afd.getAfDItem()
						.trim()))) {
					afd.setAfBase(af);
					afdSet.add(afd);
				} else {
					if (afd.getAfDId() != 0) {
						afService.removeAFD(afd.getAfDId());
					}
				}
			}
		}
		af.setAfElement(afeSet);
		af.setAfDispose(afdSet);
		// 修改开单人为当前用户
		Map session = ActionContext.getContext().getSession();
		User u = (User) session.get("user");
		af.setFmp(u.getEmployee().getRealname());
		// 修改时间为当前

		af.setLastModify(t);
		// 保存任务单
		afService.saveAF(af);
		Logger logger = Logger.getLogger(this.getClass());
		logger.warn(u.getUsername() + " save AF:" + af.getIso() + af.getAfNo());
		AfBase afg = (AfBase) afService.getAFById(af.getAfId());
		if (afg != null) {
			Map request = (Map) ActionContext.getContext().get("request");
			request.put("AFInfo", afg);
			AFPage = afg.getAfType();
			if ("Books".equals(afg.getAfType()) || "".equals(afg.getAfType())) {
				AFPage = "AF";
			}
			return SUCCESS;
		}
		message = "获取任务单失败!";
		return ERROR;
	}

	public String FPAddAF_save() throws Exception {
		Set afdSet = new HashSet();
		Set afvSet = new HashSet();
		Timestamp t = new Timestamp(new GregorianCalendar(TimeZone
				.getTimeZone("GMT")).getTimeInMillis());
		if ("".equals(af.getAfId()) || 0 == af.getAfId()) {
			af.setAfNo(afService.getNextAFNo(af.getIso()));
			af.setAftypeNo(afService.getNextAFTypeNo(af.getAfType()));
			af.setAd(t);
		} else {
			AfBase aft = (AfBase) afService.getAFById(af.getAfId());
			if (aft.getAfDispose() != null) {
				for (Iterator iterator = aft.getAfDispose().iterator(); iterator
						.hasNext();) {
					AfDispose afd = (AfDispose) iterator.next();
					afService.removeAFD(afd.getAfDId());

				}
			}
		}
		if (afvs != null) {
			for (Iterator iterator = afvs.iterator(); iterator.hasNext();) {
				AfValuation afv = (AfValuation) iterator.next();
				if (!("Del".equals(afv.getChejian()))) {
					AfDispose afd = new AfDispose();
					afd.setAfEType("快印");
					afd.setAfDItem(afv.getItemName());
					if ("零二".equals(afv.getChejian())) {
						afv.setChejian("02");
					}
					if ("零五".equals(afv.getChejian())) {
						afv.setChejian("05");
					}
					afd.setAfDFactory(afv.getChejian());
					afd.setAfDRemark(afv.getCalProcess());
					afd.setAfDAmount(new Long(afv.getAmount()));
					af.getAfDispose().add(afd);
					afd.setAfBase(af);
					afdSet.add(afd);

					afv.setTotalAmount(afv.getDanjia() * afv.getAmount());
					afv.setCalProcess(afv.getDanjia() + "x" + afv.getAmount());
					af.getAfValuation().add(afv);
					afv.setAfBase(af);
					afvSet.add(afv);

				} else {
					if (afv.getAfVId() != 0) {
						afService.removeAFV(afv.getAfVId());
					}
				}
			}
		}
		// 计算应付金额
		double moneyShould = 0.0;
		if (afvSet != null) {
			for (Iterator iterator = afvSet.iterator(); iterator.hasNext();) {
				AfValuation afvs = (AfValuation) iterator.next();
				if (!(null == afvs.getItemName() || "".equals(afvs
						.getItemName().trim()))) {
					moneyShould = moneyShould + afvs.getTotalAmount();

				}
			}
		}
		af.setMoneyShould(moneyShould);
		af.setAfDispose(afdSet);
		af.setAfValuation(afvSet);
		// 修改开单人为当前用户
		Map session = ActionContext.getContext().getSession();
		User u = (User) session.get("user");
		af.setFmp(u.getEmployee().getRealname());
		// 修改时间为当前
		af.setLastModify(t);
		af.setMoneyInputMan("系统计价");
		af.setMoneyInputTime(t);
		af.setMoneyInputRemark("系统自动计价");

		// 保存任务单
		afService.saveAF(af);
		Logger logger = Logger.getLogger(this.getClass());
		logger.warn(u.getUsername() + " save AF:" + af.getIso() + af.getAfNo());
		AfBase afg = (AfBase) afService.getAFById(af.getAfId());
		if (afg != null) {
			Map request = (Map) ActionContext.getContext().get("request");
			request.put("AFInfo", afg);
			AFPage = afg.getAfType();
			if ("Books".equals(afg.getAfType()) || "".equals(afg.getAfType())) {
				AFPage = "AF";
			}
			return SUCCESS;
		}
		message = "获取任务单失败!";
		return ERROR;
	}

	// 转开任务单
	@SuppressWarnings("unchecked")
	public String ZK() throws Exception {
		AfBase af = (AfBase) afService.getAFByNo(AFNo);
		af.setAfId(0);
		af.setAfNo(0);
		// 修改印数
		// af.setAmount(0L);
		// 修改YZ单号
		if (Pattern.compile("^YZ").matcher(af.getPcAf().toUpperCase()).find()) {
			GregorianCalendar gc = new GregorianCalendar();
			af.setPcAf("YZ" + gc.get(Calendar.YEAR));
		}
		// 修改版次
		if (null != af.getEdition()) {
			String[] editon0 = null;
			StringBuilder editionBuilder = new StringBuilder();
			editon0 = af.getEdition().split("-");
			editionBuilder.append(editon0[0]).append("-").append(
					new Integer(editon0[1]) + 1);
			af.setEdition(editionBuilder.toString());
		}
		// 设置开单时间
		af.setAd(new Date());
		// 设置最后修改时间
		af.setLastModify(new Date());
		// 设置元件
		if (af.getAfElement() != null) {
			for (Iterator iterator = af.getAfElement().iterator(); iterator
					.hasNext();) {
				AfElement afe = (AfElement) iterator.next();
				// 设置ID为0
				afe.setAfEId(0);
				// 设置日期为当前时间
				afe.setEPlanBp(new Date());
				afe.setEPlanPm(new Date());
				afe.setEPlanPress(new Date());
				// 设置指令数
				afe.setEReam(0.0);
				afe.setEOvers(0.0);
			}
		}
		if (af.getAfDispose() != null) {
			for (Iterator iterator = af.getAfDispose().iterator(); iterator
					.hasNext();) {
				AfDispose afd = (AfDispose) iterator.next();
				afd.setAfDId(0);

			}
		}
		if (af != null) {
			// 设置送样书时间和送货时间
			// af.setPlanSendSample(new Date());
			af.setPlanDeliver(new Date());
			// 设置接洽人
			Map session = ActionContext.getContext().getSession();
			User u = (User) session.get("user");
			af.setCp(u.getEmployee().getRealname());
			af.setMoneyFact(0.0);
			af.setMoneyShould(0.0);
			Map request = (Map) ActionContext.getContext().get("request");
			request.put("AFInfo", af);
			return SUCCESS;
		}
		return ERROR;
	}

	@SuppressWarnings("unchecked")
	public String CalAF_input() throws Exception {
		AfBase af = afService.getAFById(afId);
		if (null == af.getMoneyShould() || af.getMoneyShould() == 0
				|| af.getMoneyShould() < 0) {
			List bjList = afService.AFcal(afId);
			Set bjSet = new HashSet();
			if (bjList != null) {
				for (Iterator iterator = bjList.iterator(); iterator.hasNext();) {
					AfValuation bj = (AfValuation) iterator.next();
					if (!("".equals(bj.getChejian()))) {
						bjSet.add(bj);
						bj.setAfBase(af);
					}
				}
			}
			af.setAfValuation(bjSet);
		}
		af.getAfValuation().add(new AfValuation());
		af.getAfValuation().add(new AfValuation());
		af.getAfValuation().add(new AfValuation());

		Logger logger = Logger.getLogger(this.getClass());
		logger.warn(af.getAfValuation());
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("AFInfo", af);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String CalAF_edit() throws Exception {
		AfBase af = (AfBase) afService.getAFById(afId);
		if (af != null) {
			af.getAfValuation().add(new AfValuation());
			af.getAfValuation().add(new AfValuation());
			af.getAfValuation().add(new AfValuation());
			Map request = (Map) ActionContext.getContext().get("request");
			request.put("AFInfo", af);
			return SUCCESS;
		}
		return ERROR;
	}

	@SuppressWarnings("unchecked")
	public String CalAF_MoneyInput() throws Exception {
		AfBase af = (AfBase) afService.getAFById(afId);
		if (af != null) {
			Timestamp t = new Timestamp(new GregorianCalendar(TimeZone
					.getTimeZone("GMT")).getTimeInMillis());
			if (af.getMoneyTime() == null) {
				af.setMoneyTime(t);
			}
			if (af.getFapiaoTime() == null) {
				af.setFapiaoTime(t);
			}
			Map request = (Map) ActionContext.getContext().get("request");
			request.put("AFInfo", af);
			return SUCCESS;
		}
		return ERROR;
	}

	@SuppressWarnings("unchecked")
	public String CalAF_MoneyInput_save() throws Exception {
		Map session = ActionContext.getContext().getSession();
		User u = (User) session.get("user");

		AfBase af = (AfBase) afService.getAFById(afId);
		if (moneyFact > 0) {
			af.setMoneyFact(moneyFact);
			af.setMoneyGiveMan(moneyGiveMan);
			af.setMoneyGetMan(u.getEmployee().getRealname());
			af.setMoneyTime(moneyTime);
			af.setFapiaoStatus(fapiaoStatus);
			af.setFapiaoTime(fapiaoTime);
			af.setMoneyStatus(moneyStatus);
			af.setMoneyRemark(moneyRemark);

		}
		afService.saveAF(af);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("AFInfo", af);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String CalAF_save() throws Exception {
		Map session = ActionContext.getContext().getSession();
		User u = (User) session.get("user");
		Timestamp t = new Timestamp(new GregorianCalendar(TimeZone
				.getTimeZone("GMT")).getTimeInMillis());
		double moneyShould = 0;
		AfBase af = (AfBase) afService.getAFById(afId);

		Set bjSet = new HashSet();
		if (bj != null) {
			for (Iterator iterator = bj.iterator(); iterator.hasNext();) {
				AfValuation bj = (AfValuation) iterator.next();
				if (!("Del".equals(bj.getChejian()))) {
					moneyShould = moneyShould + bj.getTotalAmount();
					bjSet.add(bj);
					bj.setAfBase(af);
				} else {
					if (bj.getAfVId() > 0) {
						afService.removeAFV(bj.getAfVId());
					}
				}
			}
		}
		af.setAfValuation(bjSet);
		// 锁定任务单,不能修改
		af.setAfStatus(0);
		// 存入计价备注
		af.setMoneyInputRemark(moneyInputRemark);
		// 存入应收款
		af.setMoneyShould(moneyShould);
		// 存入计价员

		af.setMoneyInputMan(u.getEmployee().getRealname());
		// 保存计价时间

		af.setMoneyInputTime(t);
		// 保存
		afService.saveAF(af);
		// AfBase afg = (AfBase) afService.getAFByNo(af.getIso()+af.getAfId());
		// if (afg != null) {
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("AFInfo", af);
		return SUCCESS;
		// }
		// message = "获取任务单失败!";
		// return ERROR;
	}

	@SuppressWarnings("unchecked")
	public String TentAF() throws Exception {
		List clientList = clientService.getAllClients();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("clientList", clientList);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String tjAFByClient() throws Exception {
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("client", client);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getMoneyByNoRange() throws Exception {
		List ListAF = afService.getAFByNoRange(AFType, StartAFNo, EndAFNo);
		Double moneyAF = afService.getMoneyByAFlist(ListAF);
		Double moneyGET = afService.getMoneyGETByAFlist(ListAF);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", ListAF);
		request.put("moneyAF", moneyAF);
		request.put("moneyGET", moneyGET);
		request.put("pageTitle", "按任务单号统计从" + AFType + StartAFNo + "到" + AFType
				+ EndAFNo + "的产值");
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getMoneyByYWandType() throws Exception {
		List ListAF = afService.getAFByYWandType(YWName, AFType, StartAFNo,
				EndAFNo);
		Double moneyAF = afService.getMoneyByAFlist(ListAF);
		Double moneyGET = afService.getMoneyGETByAFlist(ListAF);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", ListAF);
		request.put("moneyAF", moneyAF);
		request.put("moneyGET", moneyGET);
		request.put("pageTitle", YWName + "的业务按任务单号统计从" + AFType + StartAFNo
				+ "到" + AFType + EndAFNo + "的产值");
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getMoneyByClientandType() throws Exception {
		List ListAF = afService.getAFByClientNoRange(client, AFType, StartAFNo,
				EndAFNo);
		Double moneyAF = afService.getMoneyByAFlist(ListAF);
		Double moneyGET = afService.getMoneyGETByAFlist(ListAF);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", ListAF);
		request.put("moneyAF", moneyAF);
		request.put("moneyGET", moneyGET);
		request.put("pageTitle", client + "的业务按任务单号统计从" + AFType + StartAFNo
				+ "到" + AFType + EndAFNo + "的产值");
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getMoneyByChejian() throws Exception {
		List ListAFV = afService.getAFVByChejian(YWName, ChejianName, AFType,
				StartAFNo, EndAFNo);
		Double moneyAFV = afService.getMoneyByAFVlist(ListAFV);

		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAFV", ListAFV);
		request.put("moneyAFV", moneyAFV);
		request.put("pageTitle", YWName + ChejianName + "从" + AFType
				+ StartAFNo + "到" + AFType + EndAFNo + "的产值");
		return SUCCESS;
	}

	public String getRecentColumnName() throws Exception {
		columnNames = new ArrayList<String[]>();

		for (String c : afService.getRecentColumnName(columnName, recentSize)) {
			// columnNames.add(new String[] { c });
			columnNames.add(new String[] { c });

		}
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getAllPresswork() throws Exception {
		List pressworkList = afService.getAllAFs();
		List seriesNameList = afService.getRecentColumnName("seriesName", 1000);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("pressworkList", pressworkList);
		request.put("seriesNameList", seriesNameList);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String jsongetAllYW() throws Exception {
		ywlist = new ArrayList<String[]>();
		List ListYW = userService.getEmployeeByDeptId(3);

		for (Iterator it = ListYW.iterator(); it.hasNext();) {
			Employee e = (Employee) it.next();
			ywlist.add(new String[] { e.getRealname() });
		}
		return SUCCESS;
	}

	public String jsongetClientByYW() throws Exception {
		clientList = clientService.getClientByYW(YWName);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String jsongetPresswork() throws Exception {
		pressworklist = new ArrayList<String[]>();
		List ListPresswork = afService.getAFByClient(client, 1, 200);
		for (Iterator it = ListPresswork.iterator(); it.hasNext();) {
			AfBase af = (AfBase) it.next();
			pressworklist.add(new String[] { af.getPresswork(),
					af.getSeriesName() });
		}
		return SUCCESS;
	}

	public String taizhang_input() throws Exception {
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String taizhang() throws Exception {
		List ListAF = afService.getAFByNoRange(AFType, StartAFNo, EndAFNo);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", ListAF);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String taizhang_gkcc() throws Exception {
		List ListAF = afService.getAFByNoRangeItemContent(AFType, StartAFNo,
				EndAFNo, "edition", "1-1");
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", ListAF);
		return SUCCESS;
	}

}
