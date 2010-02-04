package com.tyopf.dao.qc.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import com.tyopf.dao.BaseDAO;
import com.tyopf.dao.qc.IQYYTD82408DAO;
import com.tyopf.vo.qc.QYYTD82408;

public class QYYTD82408DAO extends BaseDAO implements IQYYTD82408DAO {

	@Override
	public QYYTD82408 find(long id) {
		return (QYYTD82408) getHibernateTemplate().get(QYYTD82408.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QYYTD82408> getList(int currentPage, int pageSize) {
		Session session = getSession();
		Query query = session
				.createQuery("from QYYTD82408 o order by o.id desc");
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
				getHibernateTemplate().get(QYYTD82408.class, id));
	}

	@Override
	public void save(QYYTD82408 ins) {
		getHibernateTemplate().saveOrUpdate(ins);
	}

	@Override
	public int getCountAll() {
		Session session = getSession();
		Criteria criteria = session.createCriteria(QYYTD82408.class);
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

	@Override
	public List<QYYTD82408> getByDateRange(Date startDate, Date endDate) {
		Session session = getSession();
		Query query = session
				.createQuery("from QYYTD82408 o where o.afBase.ad >=:startDate and o.afBase.ad <= :endDate order by o.id desc");
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		List<QYYTD82408> list = query.list();
		session.close();
		return list;

	}
}
