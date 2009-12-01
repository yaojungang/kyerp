package com.tyopf.dao.qc.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import com.tyopf.dao.BaseDAO;
import com.tyopf.dao.qc.IInspectionRecordsOfSemifinshedDAO;
import com.tyopf.vo.qc.InspectionRecordsOfSemifinshed;

public class InspectionRecordsOfSemifinshedDAO extends BaseDAO implements
		IInspectionRecordsOfSemifinshedDAO {

	@Override
	public InspectionRecordsOfSemifinshed find(long id) {
		return (InspectionRecordsOfSemifinshed) getHibernateTemplate().get(
				InspectionRecordsOfSemifinshed.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InspectionRecordsOfSemifinshed> getList(int currentPage,
			int pageSize) {
		Session session = getSession();
		Query query = session
				.createQuery("from InspectionRecordsOfSemifinshed o order by o.id desc");
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
				getHibernateTemplate().get(
						InspectionRecordsOfSemifinshed.class, id));
	}

	@Override
	public void save(InspectionRecordsOfSemifinshed ins) {
		getHibernateTemplate().saveOrUpdate(ins);
	}

	@Override
	public int getCountAll() {
		Session session = getSession();
		Criteria criteria = session
				.createCriteria(InspectionRecordsOfSemifinshed.class);
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

}
