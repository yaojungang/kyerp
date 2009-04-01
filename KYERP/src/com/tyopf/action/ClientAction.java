package com.tyopf.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IClientService;
import com.tyopf.service.IUserService;
import com.tyopf.vo.ClientC;
import com.tyopf.vo.ClientLm;
import com.tyopf.vo.Employee;
import com.tyopf.vo.User;

@SuppressWarnings( { "deprecation", "serial" })
public class ClientAction extends ActionSupport {

	private String comName = "lg";

	private List<String> linkMan = new ArrayList<String>();

	private IClientService clientService;

	private IUserService userService;

	private ClientC client;

	private ClientLm linkman;

	private List linkmanList;

	private List linkmans;

	private long clientId;

	private long linkmanId;
	
	private String YWName;
	
	private List<ClientC> clientList;
	private List<Employee> ywList;
	
	public String getYWName() {
		return YWName;
	}

	public List<ClientC> getClientList() {
		return clientList;
	}

	public void setClientList(List<ClientC> clientList) {
		this.clientList = clientList;
	}

	public List<Employee> getYwList() {
		return ywList;
	}

	public void setYwList(List<Employee> ywList) {
		this.ywList = ywList;
	}

	public void setYWName(String name) {
		YWName = name;
	}

	public List getLinkmans() {
		return linkmans;
	}

	public void setLinkmans(List linkmans) {
		this.linkmans = linkmans;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public long getLinkmanId() {
		return linkmanId;
	}

	public void setLinkmanId(long linkmanId) {
		this.linkmanId = linkmanId;
	}

	public ClientLm getLinkman() {
		return linkman;
	}

	public void setLinkman(ClientLm linkman) {
		this.linkman = linkman;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

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
	public String execute() throws Exception {
		System.out.println(comName);
		ClientC client = clientService.getLinkManbyComName(comName);
		System.out.println(client.getClientLm());
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("Client", client);

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getAllClientAndLinkMan() {
		// List linkmanList = clientService.getAllLinkMan();
		List clientList = clientService.getAllClients();
		Map request = (Map) ActionContext.getContext().get("request");
		// request.put("linkmanList",linkmanList);
		request.put("clientList", clientList);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String addClient() {
		ClientC client = new ClientC();
		Map session = ActionContext.getContext().getSession();
		User u = (User) session.get("user");
		client.setYwname(u.getEmployee().getRealname());
		if(client.getClientLm().size()==0) client.getClientLm().add(new ClientLm());
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ClientInfo", client);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String delClient() {
		clientService.removeClient(clientId);
		return SUCCESS;
	}


	@SuppressWarnings("unchecked")
	public String getLinkmanByCompany() {
		List linkmanList = clientService.getLinkmanByCompany(clientId);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("linkmanList", linkmanList);
		request.put("clientId", clientId);

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String ClientInfo() {
		ClientC ClientInfo = clientService.getClientById(clientId);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ClientInfo", ClientInfo);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String editClient() {
		ClientC client = clientService.getClientById(clientId);
		if(client.getClientLm().size()==0) client.getClientLm().add(new ClientLm());
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ClientInfo", client);
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String addLinkman() {
		ClientC client = clientService.getClientById(clientId);
		client.getClientLm().add(new ClientLm());
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ClientInfo", client);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String saveClient() {
		Set linkmanSet = new HashSet();
		if (linkmans != null) {
			for (Iterator iterator = linkmans.iterator(); iterator.hasNext();) {
				ClientLm lm = (ClientLm) iterator.next();
				if(!("".equals(lm.getCLmLinkman()) && lm.getCLmId()>0)) {
					lm.setClientC(client);
					linkmanSet.add(lm);
				} else{
					clientService.removeLinkman(lm.getCLmId());
				}

			}
		}

		client.setClientLm(linkmanSet);
		clientService.editClient(client);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ClientInfo", client);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String editCompany_save() {
		ClientC client0 = clientService.getClientById(clientId);
		linkmanList = new ArrayList(client0.getClientLm());
		for (Iterator iterator = linkmanList.iterator(); iterator.hasNext();) {
			ClientLm linkman = (ClientLm) iterator.next();
			client.getClientLm().add(linkman);
		}
		clientService.editClient(client);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String delLinkman() {
		clientService.removeLinkman(linkmanId);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getAllClients() {
		clientList = clientService.getAllClients();
		ywList = userService.getEmployeeByDeptId(3);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("clientList", clientList);
		request.put("ywList", ywList);
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String MyClient() {
		Map session = ActionContext.getContext().getSession();
		User u = (User) session.get("user");
		List clientList = clientService.getClientByYW(u.getEmployee().getRealname());
		
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("clientList", clientList);
		request.put("pageTitle", u.getEmployee().getRealname()+"的客户");
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String getClientByYW() {
		List clientList = clientService.getClientByYW(YWName);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("clientList", clientList);
		request.put("pageTitle", YWName+"的客户");
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String tjClientHK() {
		return SUCCESS;
	}

}
