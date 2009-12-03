package com.tyopf.dao.qc.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import com.tyopf.dao.BaseDAO;
import com.tyopf.dao.qc.IQYYTD82410DAO;
import com.tyopf.vo.qc.QYYTD82410;

public class QYYTD82410DAO extends BaseDAO implements IQYYTD82410DAO {

	@Override
	public QYYTD82410 find(long id) {
		return (QYYTD82410) getHibernateTemplate().get(QYYTD82410.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QYYTD82410> getList(int currentPage, int pageSize) {
		Session session = getSession();
		Query query = session
				.createQuery("from QYYTD82410 o order by o.id desc");
		int startRow = (currentPage - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		List list = query.list();
		session.close();
		return list;
	}

	@Override
	public void remove(long id) {
		getHibernateTemplate().delete(
				getHibernateTemplate().get(QYYTD82410.class, id));
	}

	@Override
	public void save(QYYTD82410 ins) {
		getHibernateTemplate().saveOrUpdate(ins);
	}

	@Override
	public int getCountAll() {
		Session session = getSession();
		Criteria criteria = session.createCriteria(QYYTD82410.class);
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

}
