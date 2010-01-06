package com.tyopf.dao.qc.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import com.tyopf.dao.BaseDAO;
import com.tyopf.dao.qc.IQYYTD82402DAO;
import com.tyopf.vo.qc.QYYTD82402;

public class QYYTD82402DAO extends BaseDAO implements IQYYTD82402DAO {

	@Override
	public QYYTD82402 find(long id) {
		return (QYYTD82402) getHibernateTemplate().get(QYYTD82402.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QYYTD82402> getList(int currentPage, int pageSize) {
		Session session = getSession();
		Query query = session
				.createQuery("from QYYTD82402 o order by o.id desc");
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
				getHibernateTemplate().get(QYYTD82402.class, id));
	}

	@Override
	public void save(QYYTD82402 ins) {
		getHibernateTemplate().saveOrUpdate(ins);
	}

	@Override
	public int getCountAll() {
		Session session = getSession();
		Criteria criteria = session.createCriteria(QYYTD82402.class);
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

	@Override
	public List<QYYTD82402> getByDateRange(Date startDate, Date endDate) {
		Session session = getSession();
		Query query = session
				.createQuery("from QYYTD82402 o where o.afBase.ad >=:startDate and o.afBase.ad <= :endDate order by o.id desc");
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		List<QYYTD82402> list = query.list();
		session.close();
		return list;
	}

}
