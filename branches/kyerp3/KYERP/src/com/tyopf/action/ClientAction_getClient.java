package com.tyopf.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.tyopf.service.IClientService;

public class ClientAction_getClient {
	protected IClientService clientService;
	private List<String[]> clientnames;
	private List clientList;

	public List getClientList() {
		return clientList;
	}

	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

	@SuppressWarnings("unchecked")
	public String execute() {
		clientnames = new ArrayList<String[]>();
 		for(String c : clientService.getClientbyColumn("CCCom")) {
			clientnames.add(new String[]{ c , c});
		}
 		List clientList = clientService.getAllClients();
 		Map request = (Map) ActionContext.getContext().get("request");
		
		request.put("clientList", clientList);
		
		return Action.SUCCESS;
	}

	public List<String[]> getClientnames() {
		return clientnames;
	}

	public void setClientnames(List<String[]> clientnames) {
		this.clientnames = clientnames;
	}
}
