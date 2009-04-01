package com.tyopf.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tyopf.dao.BaseDAO;
import com.tyopf.dao.IBindingFactoryDAO;
import com.tyopf.vo.BindingFactory;

public class BindingFactoryDAO extends BaseDAO implements IBindingFactoryDAO {

	@SuppressWarnings("unchecked")
	public List<BindingFactory> getAllBindingFactorys() {
		return getHibernateTemplate().find("from BindingFactory bindingFactory");
	}

	@SuppressWarnings("unchecked")
	public List<String> getRecentColumnName(String columnName, int recentSize) {
		String hql="select distinct "+columnName+" from BindingFactory as bf";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setMaxResults(recentSize);
		query.setCacheable(true);
		@SuppressWarnings("unused")
		List <String>find = query.list();
		session.close();
		List <String>find2 = getHibernateTemplate().find(hql);
		return find2;
	}

}
