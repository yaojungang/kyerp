package com.tyopf.action;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IAFService;
import com.tyopf.service.IClientService;
import com.tyopf.util.Pager;
import com.tyopf.vo.AfBase;
import com.tyopf.vo.User;

@SuppressWarnings("serial")
public class FAAction extends ActionSupport {
	protected IClientService clientService;
	protected IAFService afService;
	private String AFNoList;
	private String YZNoList;
	private Integer currentPage = 1;
	private Timestamp moneyTime;
	private Timestamp fapiaoTime;
	private List<AfBase> afs;
	public String getAFNoList() {
		return AFNoList;
	}

	public List<AfBase> getAfs() {
		return afs;
	}

	public void setAfs(List<AfBase> afs) {
		this.afs = afs;
	}

	public Timestamp getMoneyTime() {
		return moneyTime;
	}

	public void setMoneyTime(Timestamp moneyTime) {
		this.moneyTime = moneyTime;
	}

	public Timestamp getFapiaoTime() {
		return fapiaoTime;
	}

	public void setFapiaoTime(Timestamp fapiaoTime) {
		this.fapiaoTime = fapiaoTime;
	}

	public void setAFNoList(String noList) {
		AFNoList = noList;
	}

	public String getYZNoList() {
		return YZNoList;
	}

	public void setYZNoList(String noList) {
		YZNoList = noList;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public IAFService getAfService() {
		return afService;
	}

	public void setAfService(IAFService afService) {
		this.afService = afService;
	}

	public IClientService getClientService() {
		return clientService;
	}

	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String index() throws Exception {
		return SUCCESS;
	}

	public String ClientTent_input() throws Exception {
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String MoneyIn() throws Exception {
		List clientList = clientService.getAllClients();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("clientList", clientList);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String MoneyIn_BAT_input() throws Exception {
		List clientList = clientService.getAllClients();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("clientList", clientList);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String MoneyIn_BAT_AFNoList_input2() throws Exception {
		List ListAF = afService.getAFinAFNoList(AFNoList);
		Pager AFpager = new Pager(currentPage, ListAF.size());
		AFpager.setPageSize(ListAF.size());
		Timestamp fapiaoTime = new Timestamp(new GregorianCalendar(TimeZone
				.getTimeZone("GMT")).getTimeInMillis());
		Timestamp moneyTime = fapiaoTime;
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", ListAF);
		request.put("AFPager", AFpager);
		request.put("pageTitle", "批量结帐(任务单方式)-选择任务单");
		request.put("fapiaoTime", fapiaoTime);
		request.put("moneyTime", moneyTime);

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String MoneyIn_BAT_YZNoList_input2() throws Exception {
		
		List ListAF = afService.getAFinYZNoList(YZNoList);
		Pager AFpager = new Pager(currentPage, ListAF.size());
		AFpager.setPageSize(ListAF.size());
		Timestamp fapiaoTime = new Timestamp(new GregorianCalendar(TimeZone
				.getTimeZone("GMT")).getTimeInMillis());
		Timestamp moneyTime = fapiaoTime;
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", ListAF);
		request.put("AFPager", AFpager);
		request.put("pageTitle", "批量结帐(印制单方式)-选择任务单");
		request.put("fapiaoTime", fapiaoTime);
		request.put("moneyTime", moneyTime);

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String MoneyIn_BAT() throws Exception {
		System.out.println("++++++++++批量收款开始++++++++++++++++++");
		// Set afSet = new HashSet();
		//afs0;
		List afs0 = (List)new java.util.ArrayList(); 
		List afsF = (List)new java.util.ArrayList(); 
		int i=afs.size();
		if (afs != null) {
			for (Iterator iterator = afs.iterator(); iterator.hasNext();) {
				AfBase af = (AfBase) iterator.next();
				if (af.getAfId() > 0) {
					
					AfBase af0 = afService.getAFById(af.getAfId());
					AfBase aft = (AfBase) new AfBase();
					
					aft.setAfId(af0.getAfId());
					aft.setIso(af0.getIso());
					aft.setAfNo(af0.getAfNo());
					aft.setPresswork(af0.getPresswork());
					aft.setMoneyShould(af0.getMoneyShould());
					aft.setMoneyFact(af0.getMoneyFact());
					aft.setMoneyGiveMan(af0.getMoneyGiveMan());
					aft.setFapiaoStatus(af0.getFapiaoStatus());
					aft.setFapiaoTime(af0.getFapiaoTime());
					aft.setMoneyStatus(af0.getMoneyStatus());
					aft.setMoneyTime(aft.getMoneyTime());
					aft.setMoneyRemark(af0.getMoneyRemark());
					
					afs0.add(aft);
					//System.out.println("before update af.moneyGavinMan:"+af0.getMoneyGiveMan());
					af0.setMoneyTime(moneyTime);
					af0.setMoneyGiveMan(af.getMoneyGiveMan());
					af0.setMoneyFact(af.getMoneyFact());
					af0.setFapiaoTime(fapiaoTime);
					af0.setFapiaoStatus(0);					
					if (null != af.getFapiaoStatus())
						if(1==af.getFapiaoStatus()) af0.setFapiaoStatus(1);
					af0.setMoneyStatus(0);
					if (null != af.getMoneyStatus())
						if(1==af.getMoneyStatus())af0.setMoneyStatus(1);

					Map session = ActionContext.getContext().getSession();
					User u = (User) session.get("user");
					af0.setMoneyGetMan(u.getEmployee().getRealname());
					af0.setMoneyRemark(af.getMoneyRemark());
					
					afsF.add(af0);
					//System.out.println("updated af.moneyGavinMan:"+af0.getMoneyGiveMan());

					afService.saveAF(af0);

				}
			}
		}
		System.out.println("++++++++++批量收款结束++++++++++++++++++");
		
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("pageTitle", "批量结帐-结果");
		request.put("message", "批量收款成功！"+"共有"+i+" 个任务单成功执行了收款操作！");
		return SUCCESS;
	}

}
