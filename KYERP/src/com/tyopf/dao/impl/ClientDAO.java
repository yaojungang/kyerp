package com.tyopf.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.tyopf.dao.BaseDAO;
import com.tyopf.dao.IClientDAO;
import com.tyopf.vo.ClientC;
import com.tyopf.vo.ClientLm;

public class ClientDAO extends BaseDAO implements IClientDAO {

	public void addClient(ClientC client) {
		getHibernateTemplate().saveOrUpdate(client);
	}

	public void editClient(ClientC client) {
		getHibernateTemplate().saveOrUpdate(client);

	}

	@SuppressWarnings("unchecked")
	public List<ClientC> getAllClients() {
		return getHibernateTemplate().find("from ClientC client");
	}

	public List getClientByHql(String hql) {
		return getHibernateTemplate().find(hql);
	}

	public ClientC getClientById(long clientId) {
		return (ClientC) getHibernateTemplate().get(ClientC.class, clientId);
	}

	public List getClients(int currentPage, int pageSize) {
		Session session = getSession();
		Query query = session
				.createQuery("from ClientC client order by client.CCId desc");
		int startRow = (currentPage - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		List Clients = query.list();
		session.close();
		return Clients;
	}

	public int getTotalClients() {
		Session session = getSession();
		Query query = session.createQuery("from ClientC client");
		List Clients = query.list();
		int TotalClients = Clients.size();
		return TotalClients;
	}

	public void removeClient(long clientId) {
		Session session = getSession();
		ClientC Client = (ClientC) session.get(ClientC.class, clientId);
		session.close();
		getHibernateTemplate().delete(Client);
	}

	public void removeLinkman(long linkmanId) {
		Session session = getSession();
		ClientLm linkman = (ClientLm) session.get(ClientLm.class, linkmanId);
		session.close();
		getHibernateTemplate().delete(linkman);
	}

	@SuppressWarnings("unchecked")
	public List<String> getClientbyColumn(String column) {
		String hql = "select distinct " + column
				+ " from ClientC as clientName";
		// 参数 distinct 是为了去掉重复记录
		List<String> find = getHibernateTemplate().find(hql);
		return find;
	}

	public ClientC getLinkManbyComName(String comName) {
		Session session = getSession();
		String hql = "from ClientC client where client.CCCom=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, comName);
		List clientList = query.list();
		if (clientList.size() != 0) {
			ClientC client = (ClientC) clientList.get(0);
			session.close();
			return client;
		}
		session.close();
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ClientLm> getAllLinkMan() {
		return getHibernateTemplate().find("from ClientLm linkman");
	}

	@SuppressWarnings("unchecked")
	public List<ClientLm> getLinkmanByCompany(long clientId) {
		Session session = getSession();
		String hql = "from ClientC client where client.CCId=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, clientId);
		List clientList = query.list();
		if (clientList.size() != 0) {
			ClientC client = (ClientC) clientList.get(0);
			List linkmanList = new ArrayList(client.getClientLm());

			session.close();
			return linkmanList;
		}
		session.close();
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ClientC> getClientByYW(String YWName) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(ClientC.class);
		criteria.add(Restrictions.like("ywname", "%" + YWName + "%"));
		List<ClientC> listC = criteria.list();
		session.close();
		return listC;
	}

	@SuppressWarnings("unchecked")
	public List<ClientLm> getLinkmanByClient(String clientName) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(ClientC.class);
		criteria.add(Restrictions.like("CCCom", "%" + clientName + "%"));
		List<ClientC> listC = criteria.list();
		if(listC.size() !=0){
			ClientC c = listC.get(0);
			List listLm = new ArrayList(c.getClientLm());
			session.close();
			return listLm;
		}
		session.close();
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ClientLm> getLinkmanByName(String linkmanName) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(ClientLm.class);
		criteria.add(Restrictions.like("CLmLinkman", "%" + linkmanName + "%"));
		List<ClientLm> listLm = criteria.list();
		session.close();
		return listLm;
	}

	@SuppressWarnings("unchecked")
	public ClientC getClientByName(String clientName) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(ClientC.class);
		criteria.add(Restrictions.like("CCCom", "%" + clientName + "%"));
		List<ClientC> listC = criteria.list();
		if(listC.size() !=0){
			ClientC c = listC.get(0);
			session.close();
			return c;
		}
		session.close();
		return null;
	}
}
