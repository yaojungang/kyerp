package com.jzland.ChengDe.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import com.jzland.ChengDe.dao.BaseDAO;
import com.jzland.ChengDe.dao.IPlate;
import com.jzland.ChengDe.vo.Plate;
import com.jzland.ChengDe.vo.PlateUseLog;

public class PlateDAO extends BaseDAO implements IPlate {
	@SuppressWarnings("unchecked")
	@Override
	public List<Plate> getAllPlate() {
		Session session = getSession();
		Query query = session.createQuery("from Plate");
		List plateList = query.list();
		session.close();
		return plateList;
	}
	
	@Override
	public Plate getPlateById(int id) {
		Session session = getSession();
		Plate plate = (Plate) session.get(Plate.class, id);
		if (plate.getClass() != null) {
			if (null != plate.getViewTimes()) {
				plate.setViewTimes(plate.getViewTimes() + 1);
			}else{
				plate.setViewTimes(1);
			}
			session.saveOrUpdate(plate);
			session.flush();
			for (@SuppressWarnings("unused")
			PlateUseLog useLog : plate.getUseLogs()) {
				if (!Hibernate.isInitialized(plate.getUseLogs())) Hibernate.initialize(plate.getUseLogs());
			}
			session.close();
			return plate;
		}
		session.close();
		return null;
	}
	
	@Override
	public void addPlate(Plate plate) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(plate);
		tx.commit();
		session.flush();
		session.close();
	}
	
	@Override
	public List<Plate> getPlates(int currentPage, int pageSize) {
		Session session = getSession();
		Query query = session.createQuery("from Plate p order by p.id desc");
		int startRow = (currentPage - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		List Users = query.list();
		session.close();
		return Users;
	}
	
	@Override
	public int getCountofAllPlate() {
		Session session = getSession();
		Criteria criteria = session.createCriteria(Plate.class);
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}
	
	@Override
	public void delPlate(int id) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.delete(getPlateById(id));
		tx.commit();
		session.flush();
		session.close();
	}
	
	@Override
	public void editPlate(Plate plate) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(plate);
		tx.commit();
		session.flush();
		session.close();
	}
	
	@Override
	public int getCountofExpPlate() {
		Session session = getSession();
		Criteria criteria = session.createCriteria(Plate.class);
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}
	
	@Override
	public List<Plate> getExpPlates(int currentPage, int pageSize) {
		Session session = getSession();
		Date todayD = new Date();
		String hql = "from Plate as p where p.expDate <= :tday order by p.id desc";
		String[] params = { "tday" };
		Object[] args = { todayD };
		List<Plate> list = this.getHibernateTemplate().findByNamedParam(hql, params, args);
		session.close();
		return list;
	}
	
	@Override
	public List<Plate> searchPlate(String searchOption, String searchValue) {
		Session session = getSession();
		Query query = session.createQuery("from Plate p where p." + searchOption + " like :SearchValue order by p.id desc");
		query.setString("SearchValue", "%" + searchValue + "%");
		List list = query.list();
		session.close();
		return list;
	}
}
