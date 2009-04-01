package com.tyopf.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;

import com.tyopf.service.IAFService;
import com.tyopf.vo.AfProcess;

public class AfProcessAction extends ExtJSONActionSuport {
	
	private static final long serialVersionUID = 1L;
	private AfProcess afp = null;
	private List<AfProcess> afpList = new ArrayList<AfProcess>(0);
	private IAFService afService = null;
	private String delData;
	private String processType;
	private int afpId;

	public int getAfpId() {
		return afpId;
	}

	public void setAfpId(int afpId) {
		this.afpId = afpId;
	}

	public AfProcess getAfp() {
		return afp;
	}

	public void setAfp(AfProcess afp) {
		this.afp = afp;
	}

	public List<AfProcess> getAfpList() {
		return afpList;
	}

	public void setAfpList(List<AfProcess> afpList) {
		this.afpList = afpList;
	}

	public IAFService getAfService() {
		return afService;
	}

	public void setAfService(IAFService afService) {
		this.afService = afService;
	}

	public String getDelData() {
		return delData;
	}

	public void setDelData(String delData) {
		this.delData = delData;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String execute() {
		return this.SUCCESS;
	}

	@Override
	public String jsonExecute() throws Exception {
		afpList = afService.getProcessByType(processType, this.getStart(), this.getLimit());
		this.setTotalCount(afService.getCountofProcessByType(processType));
		JSONArray array = JSONArray.fromObject(this.afpList);
		//System.out.println(this.getStart() + "---" + this.getLimit());
		this.setJsonString("{success:true,totalCount : " + this.getTotalCount() + ", list:" + array.toString() + "}");
		
		return super.jsonExecute();
	}
	public String saveAFP() throws Exception {
		afService.saveAfProcess(afp);
		afpList = afService.getProcessByType("", 0, 1);
		for (Iterator iterator = afpList.iterator(); iterator.hasNext();) {
			AfProcess afp = (AfProcess) iterator.next();
		}
		this.setJsonString("{success:true,afpid:"+afp.getId()+"}");
		return this.SUCCESS;
	}
	public String removeAFP() throws Exception {
		afService.removeAfProcess(afpId);
		this.setJsonString("{success:true}");
		return this.SUCCESS;
	}
	
	
}
