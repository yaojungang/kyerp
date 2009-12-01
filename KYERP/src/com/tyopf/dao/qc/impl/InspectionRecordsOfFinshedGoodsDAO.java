package com.tyopf.dao.qc.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import com.tyopf.dao.BaseDAO;
import com.tyopf.dao.qc.IInspectionRecordsOfFinshedGoodsDAO;
import com.tyopf.vo.qc.InspectionRecordsOfFinshedGoods;

public class InspectionRecordsOfFinshedGoodsDAO extends BaseDAO implements
		IInspectionRecordsOfFinshedGoodsDAO {

	@Override
	public InspectionRecordsOfFinshedGoods find(long id) {
		return (InspectionRecordsOfFinshedGoods) getHibernateTemplate().get(
				InspectionRecordsOfFinshedGoods.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InspectionRecordsOfFinshedGoods> getList(int currentPage,
			int pageSize) {
		Session session = getSession();
		Query query = session
				.createQuery("from InspectionRecordsOfFinshedGoods o order by o.id desc");
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
						InspectionRecordsOfFinshedGoods.class, id));
	}

	@Override
	public void save(InspectionRecordsOfFinshedGoods ins) {
		getHibernateTemplate().saveOrUpdate(ins);
	}

	@Override
	public int getCountAll() {
		Session session = getSession();
		Criteria criteria = session
				.createCriteria(InspectionRecordsOfFinshedGoods.class);
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

}
