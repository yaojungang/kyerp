package com.tyopf.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tyopf.dao.BaseDAO;
import com.tyopf.dao.IWareHouseDAO;
import com.tyopf.vo.Paper;

public class WareHouseDAO extends BaseDAO implements IWareHouseDAO {

	@SuppressWarnings("unchecked")
	public List<Paper> getPaperByType(String type, int currentPage, int pageSize) {
		Session session = getSession();
		Query query;
		query = session.createQuery("from Paper P where P.type like :type order by P.pid desc");
		query.setString("type", "%" + type + "%");
		int startRow = (currentPage - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		List<Paper> paperList = query.list();
		session.close();
		return paperList;
	}

	@SuppressWarnings("unchecked")
	public List<Paper> getPaperList(int currentPage, int pageSize) {
		Session session = getSession();
		Query query;
		query = session.createQuery("from Paper P order by P.pid desc");
		int startRow = (currentPage - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		List<Paper> paperList = query.list();
		session.close();
		return paperList;
	}

	public Paper getPaperById(int pid) {
		Session session = getSession();
		Paper paper = (Paper) session.get(Paper.class, pid);
		if (paper.getClass() != null) {
			session.saveOrUpdate(paper);
			session.close();
			return paper;
		}
		session.close();
		return null;
	}

	public void removePaper(int pid) {
		Session session = getSession();
		Paper paper = (Paper) session.get(Paper.class, pid);
		session.delete(paper);
		session.flush();
		session.close();
	}

	public void savePaper(Paper paper) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(paper);
		tx.commit();
		session.close();
	}

	public int getCountOfPaperByType(String type) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(Paper.class);
		criteria.add(Restrictions.eq("type", type));
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

	public int getCountOfPaperList() {
		Session session = getSession();
		Criteria criteria = session.createCriteria(Paper.class);
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

}