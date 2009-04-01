package com.tyopf.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IClientService;
import com.tyopf.vo.ClientC;

@SuppressWarnings({ "deprecation", "serial" })
public class ClientAction_getLinkMan extends ActionSupport {

	private String comName = "lg";
	private List<String> linkMan = new ArrayList<String>();
	private IClientService clientService;
	private ClientC client;
	private List linkmanList;
	

	public List getLinkmanList() {
		return linkmanList;
	}
	public void setLinkmanList(List linkmanList) {
		this.linkmanList = linkmanList;
	}
	public ClientC getClient() {
		return client;
	}
	public void setClient(ClientC client) {
		this.client = client;
	}
	public IClientService getClientService() {
		return clientService;
	}
	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public List<String> getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(List<String> linkMan) {
		this.linkMan = linkMan;
	}
	@Override
	@SuppressWarnings("unchecked")
	public String execute() throws Exception
	{
		System.out.println(comName);
		ClientC client = clientService.getLinkManbyComName(comName);
		System.out.println(client.getClientLm());
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("Client",client);

		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String getAllLinkMan() {
		List linkmanList = clientService.getAllLinkMan();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("linkmanList",linkmanList);
		
		return SUCCESS;
	}
}
