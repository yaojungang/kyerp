package com.tyopf.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IAFService;
import com.tyopf.util.Pager;
import com.tyopf.vo.AfBase;
import com.tyopf.vo.AfDispose;
import com.tyopf.vo.AfElement;
import com.tyopf.vo.User;

@SuppressWarnings("serial")
public class AFAction extends ActionSupport {
	protected IAFService afService;

	private List<String[]> columnNames;
	private List<AfBase> ListAF;

	private String columnName = "seriesName";

	private String BindingFactoryName = "本厂";

	private String ItemName = "EComPm";

	private String MachineName = "";
	private String mm = "";

	private String client;

	private String AFNo;

	private String AFType;
	private String AFPage = "AF";

	private String url;

	private String YWName = "王秀云";

	private String AFNoList;

	private String YZNoList;

	private long StartAFNo = 20070001;

	private long EndAFNo = 20079999;

	private long StartAFId = 1;

	private long EndAFId = 2321;

	private Date date;

	private int recentSize = 10;

	private Integer currentPage = 1;
	private Integer pageSize = 10;

	private long afId;

	private long afEId;

	private long afDId;
	private int filmPlace;
	private String searchOption;
	private String searchValue;	
	public int getFilmPlace() {
		return filmPlace;
	}

	public void setFilmPlace(int filmPlace) {
		this.filmPlace = filmPlace;
	}

	public String getSearchOption() {
		return searchOption;
	}

	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getAFPage() {
		return AFPage;
	}

	public void setAFPage(String page) {
		AFPage = page;
	}

	public String getAFNoList() {
		return AFNoList;
	}

	public void setAFNoList(String noList) {
		AFNoList = noList;
	}

	public List<AfBase> getListAF() {
		return ListAF;
	}

	public void setListAF(List<AfBase> listAF) {
		ListAF = listAF;
	}

	public String getYZNoList() {
		return YZNoList;
	}

	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setYZNoList(String noList) {
		YZNoList = noList;
	}

	public long getEndAFId() {
		return EndAFId;
	}

	public void setEndAFId(long endAFId) {
		EndAFId = endAFId;
	}

	public String getMachineName() {
		return MachineName;
	}

	public void setMachineName(String machineName) {
		MachineName = machineName;
	}

	public long getStartAFId() {
		return StartAFId;
	}

	public void setStartAFId(long startAFId) {
		StartAFId = startAFId;
	}

	public String getAFType() {
		return AFType;
	}

	public void setAFType(String type) {
		AFType = type;
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

	public String getAFNo() {
		return AFNo;
	}

	public void setAFNo(String no) {
		AFNo = no;
	}

	public long getAfEId() {
		return afEId;
	}

	public long getAfId() {
		return afId;
	}

	public IAFService getAfService() {
		return afService;
	}

	public String getColumnName() {
		return columnName;
	}

	public int getRecentSize() {
		return recentSize;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	public long getAfDId() {
		return afDId;
	}

	public void setAfDId(long afDId) {
		this.afDId = afDId;
	}

	public String getBindingFactoryName() {
		return BindingFactoryName;
	}

	public void setBindingFactoryName(String bindingFactoryName) {
		BindingFactoryName = bindingFactoryName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public void setAfEId(long afEId) {
		this.afEId = afEId;
	}

	public void setAfId(long afId) {
		this.afId = afId;
	}

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

	public void setAfService(IAFService afService) {
		this.afService = afService;
	}

	public String getYWName() {
		return YWName;
	}

	public void setYWName(String name) {
		YWName = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSUCCESS() {
		return SUCCESS;
	}

	public String execute() throws Exception {
		afService.getAllAFs();
		return SUCCESS;
	}

	public String getRecentColumnName() throws Exception {
		columnNames = new ArrayList<String[]>();

		for (String c : afService.getRecentColumnName(columnName, recentSize)) {
			columnNames.add(new String[] { c, c });
		}
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getTodayAFs() {

		List ListAF = afService.getTodayAFs();
		Pager AFpager = new Pager(currentPage, ListAF.size());
		Map request = (Map) ActionContext.getContext().get("request");

		request.put("ListAF", ListAF);
		request.put("AFPager", AFpager);
		request.put("pageTitle", "今日任务单");

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getSKAFs() {
		List ListAF = afService.getAFByType("SK", currentPage, 50);

		Pager AFpager = new Pager(currentPage, afService
				.getTotalAFsByType("SK"));
		AFpager.setPageSize(50);
		Map request = (Map) ActionContext.getContext().get("request");

		request.put("ListAF", ListAF);
		request.put("AFPager", AFpager);
		request.put("pageTitle", "受控任务单");

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getLHAFs() {
		List ListAF = afService.getAFByType("LH", currentPage, 50);
		Pager AFpager = new Pager(currentPage, afService
				.getTotalAFsByType("LH"));
		AFpager.setPageSize(50);
		Map request = (Map) ActionContext.getContext().get("request");

		request.put("ListAF", ListAF);
		request.put("AFPager", AFpager);
		request.put("pageTitle", "零活任务单");

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getAFEs() {
		List ListAFE = afService.getAFEs(currentPage, pageSize);
		Pager AFpager = new Pager(currentPage, afService.getCountofAllAFEs());
		AFpager.setPageSize(pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAFE", ListAFE);
		request.put("AFPager", AFpager);
		return SUCCESS;
	}

	public String DL_Complete() {
		AfBase af = afService.getAFById(afId);
		Timestamp t = new Timestamp(new GregorianCalendar(TimeZone
				.getTimeZone("GMT+8")).getTimeInMillis());
		af.setComDeliver(t);
		afService.editAF(af);
		Map request = (Map) ActionContext.getContext().get("request");
		String msg = t.toString();
		request.put("message",msg);
		return SUCCESS;
	}

	public String PM_Complete() {
		AfElement afe = afService.getAFEById(afEId);
		Timestamp t = new Timestamp(new GregorianCalendar(TimeZone
				.getTimeZone("GMT+8")).getTimeInMillis());
		afe.setEComPm(t);
		afService.editAFE(afe);
		Map request = (Map) ActionContext.getContext().get("request");
		String msg = t.toString();
		request.put("message",msg);
		return SUCCESS;
	}

	public String PL_Complete() {
		AfElement afe = afService.getAFEById(afEId);
		Timestamp t = new Timestamp(new GregorianCalendar(TimeZone
				.getTimeZone("GMT+8")).getTimeInMillis());
		afe.setEComPress(t);
		afService.editAFE(afe);
		Map request = (Map) ActionContext.getContext().get("request");
		String msg = t.toString();
		request.put("message",msg);
		return SUCCESS;
	}

	public String ZP_Complete() {
		AfElement afe = afService.getAFEById(afEId);
		Timestamp t = new Timestamp(new GregorianCalendar(TimeZone
				.getTimeZone("GMT+8")).getTimeInMillis());
		afe.setEComProof(t);
		Map request = (Map) ActionContext.getContext().get("request");
		String msg = t.toString();
		request.put("message",msg);
		afService.editAFE(afe);

		return SUCCESS;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public String BL_Complete() {
		AfDispose afd = afService.getAFDById(afDId);
		Timestamp t = new Timestamp(new GregorianCalendar(TimeZone
				.getTimeZone("GMT+8")).getTimeInMillis());
		afd.setAfDDate(t);
		afService.editAFD(afd);
		Map request = (Map) ActionContext.getContext().get("request");
		String msg = t.toString();
		request.put("message",msg);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getAFByClient() throws Exception {
		List ListAF = afService.getAFByClient(client, currentPage, 5000);
		Pager AFpager = new Pager(currentPage, ListAF.size());
		AFpager.setPageSize(5000);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("pageTitle", client + "的任务单");
		request.put("ListAF", ListAF);
		request.put("AFPager", AFpager);
		return SUCCESS;

	}

	@SuppressWarnings("unchecked")
	public String getAFByDate() throws Exception {
		List ListAF = afService.getAFByDate(date);
		Pager AFpager = new Pager(currentPage, ListAF.size());
		AFpager.setPageSize(5000);
		Map request = (Map) ActionContext.getContext().get("request");
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy年MM月dd日");
		request.put("pageTitle", formatDate.format(date) + "的任务单");
		request.put("ListAF", ListAF);
		request.put("AFPager", AFpager);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getAFByBindingFactory() throws Exception {
		List ListAFD = afService.getAFByBindingFactory(BindingFactoryName,
				currentPage, pageSize);
		Pager AFpager = new Pager(currentPage, afService
				.getCountofAllAFByBindingFactory(BindingFactoryName));
		AFpager.setPageSize(pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAFD", ListAFD);
		request.put("AFPager", AFpager);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getTodayAFsForBindingFactory() throws Exception {
		List ListAFD = afService
				.getTodayAFsForBindingFactory(BindingFactoryName);
		Pager AFpager = new Pager(currentPage, ListAFD.size());
		AFpager.setPageSize(5000);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAFD", ListAFD);
		request.put("AFPager", AFpager);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getFinishedAFEByItem() throws Exception {
		List ListAFE = afService
				.getFinishedAFEByItem(ItemName, currentPage, pageSize);
		Pager AFpager = new Pager(currentPage, afService
				.getCountofFinishedAFEByItem(ItemName));
		AFpager.setPageSize(pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAFE", ListAFE);
		request.put("AFPager", AFpager);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getNotFinishedAFEByItem() throws Exception {
		List ListAFE = afService.getNotFinishedAFEByItem(ItemName, currentPage,
				pageSize);
		Pager AFpager = new Pager(currentPage, afService
				.getCountofNotFinishedAFEByItem(ItemName));
		AFpager.setPageSize(pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAFE", ListAFE);
		request.put("AFPager", AFpager);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getNotFinishedAFEByItemMachine() throws Exception {
		String machine = "";
		if ("ss".equals(MachineName))
			machine = "四色";
		if ("sm".equals(MachineName))
			machine = "双面";
		if ("05".equals(MachineName))
			machine = "05";
		if ("02".equals(MachineName))
			machine = "02";

		List ListAFE = afService.getNotFinishedAFEByItemMachine(ItemName,
				machine, currentPage,pageSize);
		Pager AFpager = new Pager(currentPage, afService.getCountofNotFinishedAFEByItemMachine(ItemName, machine));
		AFpager.setPageSize(pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAFE", ListAFE);
		request.put("AFPager", AFpager);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getFinishedAFD() throws Exception {
		List ListAFD = afService.getFinishedAFDByItem("afDDate", currentPage,
				pageSize);
		Pager AFpager = new Pager(currentPage, afService
				.getCountofFinishedAFDByItem("afDDate"));
		AFpager.setPageSize(pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAFD", ListAFD);
		request.put("AFPager", AFpager);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getNotFinishedAFD() throws Exception {
		List ListAFD = afService.getNotFinishedAFDByItem("afDDate",
				currentPage, pageSize);
		Pager AFpager = new Pager(currentPage, afService
				.getCountofNotFinishedAFDByItem("afDDate"));
		AFpager.setPageSize(pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAFD", ListAFD);
		request.put("AFPager", AFpager);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getFinishedDL() throws Exception {
		List ListAF = afService.getFinishedDL(currentPage, pageSize);
		Pager AFpager = new Pager(currentPage, afService.getCountofFinishedDL());
		AFpager.setPageSize(pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", ListAF);
		request.put("AFPager", AFpager);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getNotFinishedDL() throws Exception {
		List ListAF = afService.getNotFinishedDL(currentPage, pageSize);
		Pager AFpager = new Pager(currentPage, afService
				.getCountofNotFinishedDL());
		AFpager.setPageSize(pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", ListAF);
		request.put("AFPager", AFpager);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getTodayDL() throws Exception {
		List ListAF = afService.getTodayDL();
		Pager AFpager = new Pager(currentPage, ListAF.size());
		AFpager.setPageSize(5000);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", ListAF);
		request.put("AFPager", AFpager);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String AFInfo() throws Exception {
		AfBase af = null;

		if (afId > 0) {
			af = (AfBase) afService.getAFById(afId);
		}

		if (af != null) {
			Map request = (Map) ActionContext.getContext().get("request");
			request.put("AFInfo", af);
			AFPage = af.getAfType();
			if ("Books".equals(af.getAfType()) || "".equals(af.getAfType()))
				AFPage = "AF";
			return SUCCESS;
		}
		return ERROR;
	}

	@SuppressWarnings("unchecked")
	public String getAFByNo() throws Exception {
		AfBase af = (AfBase) afService.getAFByNo(AFNo);
		if (af != null) {
			Map request = (Map) ActionContext.getContext().get("request");
			request.put("AFInfo", af);
			return SUCCESS;
		}
		return ERROR;
	}


	@SuppressWarnings("unchecked")
	public String getAFByMachine() throws Exception {
		MachineName = "四色";
		List ListAF = afService.getAFByMachine(MachineName, (int) StartAFNo,
				(int) EndAFNo);
		Pager AFpager = new Pager(currentPage, ListAF.size());
		AFpager.setPageSize(pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", ListAF);
		request.put("AFPager", AFpager);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getAFByNoRange() throws Exception {
		List ListAF = afService.getAFByNoRange(AFType, StartAFNo, EndAFNo);
		Pager AFpager = new Pager(currentPage, ListAF.size());
		AFpager.setPageSize(5000);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", ListAF);
		request.put("AFPager", AFpager);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getSKAFEs() throws Exception {
		List ListAFE = afService.getAFEByType("SK", currentPage, pageSize);
		Pager AFpager = new Pager(currentPage, afService
				.getCountofAFEbyType("SK"));
		AFpager.setPageSize(pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAFE", ListAFE);
		request.put("AFPager", AFpager);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getLHAFEs() throws Exception {
		List ListAFE = afService.getAFEByType("LH", currentPage, pageSize);
		Pager AFpager = new Pager(currentPage, afService.getCountofAFEbyType("LH"));
		AFpager.setPageSize(pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAFE", ListAFE);
		request.put("AFPager", AFpager);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getAFEsByMachine() throws Exception {
		String machine = "";
		if ("ss".equals(mm))
			machine = "四色";
		if ("sm".equals(mm))
			machine = "双面";
		if ("05".equals(mm))
			machine = "05";
		if ("02".equals(mm))
			machine = "02";
		List ListAFE = afService.getAFEByMachine(machine, currentPage, pageSize);
		Pager AFpager = new Pager(currentPage, afService
				.getCountofAFEbyMachine(machine));
		AFpager.setPageSize(pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAFE", ListAFE);
		request.put("AFPager", AFpager);
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String getAFEsByMachine_ss() throws Exception {
		String machine="四色";
		List ListAFE = afService.getAFEByMachine(machine, currentPage, pageSize);
		Pager AFpager = new Pager(currentPage, afService
				.getCountofAFEbyMachine(machine));
		AFpager.setPageSize(pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAFE", ListAFE);
		request.put("AFPager", AFpager);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getAFByYW() throws Exception {
		List ListAF = afService.getAFByYW(YWName, currentPage, pageSize);
		Pager AFpager = new Pager(currentPage, afService
				.getCountofAFbyYW(YWName));
		AFpager.setPageSize(pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", ListAF);
		request.put("AFPager", AFpager);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getMyAF() throws Exception {
		Map session = (Map) ActionContext.getContext().get(
				ActionContext.SESSION);
		User u = (User) session.get("user");
		String YWName = u.getEmployee().getRealname();
		
		List ListAF = afService.getAFByYW(YWName, currentPage, 50);
		Pager AFpager = new Pager(currentPage, afService
				.getCountofAFbyYW(YWName));
		AFpager.setPageSize(50);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", ListAF);
		request.put("AFPager", AFpager);
		request.put("pageTitle", YWName + "的任务单");
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getAFByYWY() throws Exception {
		if ("wxy".equals(YWName))
			YWName = "王秀云";
		if ("yar".equals(YWName))
			YWName = "杨爱荣";
		if ("cgz".equals(YWName))
			YWName = "陈桂芝";
		if ("syp".equals(YWName))
			YWName = "孙玉萍";
		if ("sjw".equals(YWName))
			YWName = "孙纪文";
		if ("wbj".equals(YWName))
			YWName = "吴宝举";
		ListAF = afService.getAFByYW(YWName, currentPage, 50);
		Pager AFpager = new Pager(currentPage, afService
				.getCountofAFbyYW(YWName));
		AFpager.setPageSize(50);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", ListAF);
		request.put("AFPager", AFpager);
		request.put("pageTitle", YWName + "的任务单");
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getAFinYZNoList() throws Exception {
		List ListAF = afService.getAFinYZNoList(YZNoList);
		Pager AFpager = new Pager(currentPage, ListAF.size());
		AFpager.setPageSize(ListAF.size());
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", ListAF);
		request.put("AFPager", AFpager);
		request.put("pageTitle", "北京市增值税专用发票销货清单");
		request.put("client", client);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getAFinAFNoList() throws Exception {
		List ListAF = afService.getAFinAFNoList(AFNoList);
		Pager AFpager = new Pager(currentPage, ListAF.size());
		AFpager.setPageSize(ListAF.size());
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", ListAF);
		request.put("AFPager", AFpager);
		request.put("pageTitle", "北京市增值税专用发票销货清单");
		request.put("client", client);
		return SUCCESS;
	}

	public String getAFList_input() throws Exception {

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getAFbyAFNoList() throws Exception {
		List ListAF = afService.getAFinAFNoList(AFNoList);
		Pager AFpager = new Pager(currentPage, ListAF.size());
		AFpager.setPageSize(ListAF.size());
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", ListAF);
		request.put("AFPager", AFpager);
		request.put("pageTitle", "任务单列表");
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String getAFs() throws Exception {
		List listAF = afService.getAFs(currentPage, pageSize);
		Pager AFpager = new Pager(currentPage, afService.getCountofAllAFs());
		AFpager.setPageSize(pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", listAF);
		request.put("AFPager", AFpager);
		request.put("pageTitle", "全部任务单");
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String filmPlaceInput() throws Exception {
		afService.filmPlaceChange(afEId, filmPlace);
		Map request = (Map) ActionContext.getContext().get("request");
		Map session = ActionContext.getContext().getSession();
		User u =(User) session.get("user");
		Logger logger=Logger.getLogger(this.getClass());
		logger.warn(u.getUsername()+" update FilmPlace set afEId="+afEId+"  filmPlace:"+filmPlace);
		request.put("message", "软片存放位置修改成功！");
		return SUCCESS;
	}
	public String searchAF() {
		List afList = afService.searchAF(searchOption, searchValue);
		Pager pager = new Pager(currentPage, afList.size());
		pager.setPageSize(afList.size());
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", afList);
		request.put("AFPager", pager);
		request.put("pageTitle", "搜索："+searchOption+"="+searchValue);
		return SUCCESS;
	}

}
