package com.tyopf.service;

import java.util.List;

import com.tyopf.vo.ClientC;
import com.tyopf.vo.ClientLm;

public interface IClientService {
	public List getAllClients();
	public void addClient(ClientC client);
	public void removeClient(long clientId);
	public void editClient(ClientC client);
	public int getTotalClients();
	public List getClients(int currentPage,int pageSize);
	public List getClientByHql(String hql);
	public ClientC getClientById(long clientId);
	public List<String> getClientbyColumn(String column);
	public ClientC getLinkManbyComName(String comName);
	public ClientC getClientByName(String clientName);
	public List<ClientLm> getAllLinkMan();
	public List<ClientLm> getLinkmanByCompany(long clientId);
	public List<ClientLm> getLinkmanByClient(String clientName);
	public List<ClientLm> getLinkmanByName(String linkmanName);
	public void removeLinkman(long linkmanId);
	public List<ClientC> getClientByYW(String YWName);
}
