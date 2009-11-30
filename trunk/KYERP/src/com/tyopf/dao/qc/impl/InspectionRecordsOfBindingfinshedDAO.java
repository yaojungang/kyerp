package com.tyopf.dao.qc.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import com.tyopf.dao.BaseDAO;
import com.tyopf.dao.qc.IInspectionRecordsOfBindingfinshedDAO;
import com.tyopf.vo.qc.InspectionRecordsOfBindingfinshed;

public class InspectionRecordsOfBindingfinshedDAO extends BaseDAO implements
		IInspectionRecordsOfBindingfinshedDAO {

	@Override
	public InspectionRecordsOfBindingfinshed find(long id) {
		return (InspectionRecordsOfBindingfinshed) getHibernateTemplate().get(
				InspectionRecordsOfBindingfinshed.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InspectionRecordsOfBindingfinshed> getList(int currentPage,
			int pageSize) {
		Session session = getSession();
		Query query = session
				.createQuery("from InspectionRecordsOfBindingfinshed o order by o.id desc");
		int startRow = (currentPage - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		List list = query.list();
		session.close();
		return list;
	}

	@Override
	public void remove(long id) {
		getHibernateTemplate().delete(
				getHibernateTemplate().get(
						InspectionRecordsOfBindingfinshed.class, id));
	}

	@Override
	public void save(InspectionRecordsOfBindingfinshed ins) {
		getHibernateTemplate().save(ins);
	}

	@Override
	public int getCountAll() {
		Session session = getSession();
		Criteria criteria = session
				.createCriteria(InspectionRecordsOfBindingfinshed.class);
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

}
