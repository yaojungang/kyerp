package com.tyopf.service.impl;

import java.util.List;

import com.tyopf.dao.IClientDAO;
import com.tyopf.dao.ISystemDAO;
import com.tyopf.service.IClientService;
import com.tyopf.vo.ClientC;
import com.tyopf.vo.ClientLm;

public class ClientService implements IClientService {
	protected IClientDAO clientDAO;
	private ISystemDAO systemDAO;
	public IClientDAO getClientDAO() {
		return clientDAO;
	}

	public ISystemDAO getSystemDAO() {
		return systemDAO;
	}

	public void setSystemDAO(ISystemDAO systemDAO) {
		this.systemDAO = systemDAO;
	}

	public void setClientDAO(IClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	public void addClient(ClientC client) {
		clientDAO.addClient(client);
	}

	public void editClient(ClientC client) {
		clientDAO.editClient(client);
	}

	public List getAllClients() {
		return clientDAO.getAllClients();
	}

	public List getClientByHql(String hql) {
		return clientDAO.getClientByHql(hql);
	}

	public ClientC getClientById(long clientId) {
		return clientDAO.getClientById(clientId);
	}

	public List getClients(int currentPage, int pageSize) {
		return clientDAO.getAllClients();
	}

	public int getTotalClients() {
		return clientDAO.getTotalClients();
	}

	public void removeClient(long clientId) {
		clientDAO.removeClient(clientId);
	}

	public List<String> getClientbyColumn(String column) {
		return clientDAO.getClientbyColumn(column);
	}

	public ClientC getLinkManbyComName(String comName) {
		return clientDAO.getLinkManbyComName(comName);
	}

	public List<ClientLm> getAllLinkMan() {
		return clientDAO.getAllLinkMan();
	}

	public List<ClientLm> getLinkmanByCompany(long clientId) {
		return clientDAO.getLinkmanByCompany(clientId);
	}

	public void removeLinkman(long linkmanId) {
		clientDAO.removeLinkman(linkmanId);
	}

	public List<ClientC> getClientByYW(String YWName) {
		return clientDAO.getClientByYW(YWName);
	}

	public List<ClientLm> getLinkmanByClient(String clientName) {
		return clientDAO.getLinkmanByClient(clientName);
	}

	public List<ClientLm> getLinkmanByName(String linkmanName) {
		return clientDAO.getLinkmanByName(linkmanName);
	}

	public ClientC getClientByName(String clientName) {
		return clientDAO.getClientByName(clientName);
	}



}
