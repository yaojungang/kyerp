package com.tyopf.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.tyopf.dao.BaseDAO;
import com.tyopf.dao.IAFDAO;
import com.tyopf.vo.AfBase;
import com.tyopf.vo.AfDispose;
import com.tyopf.vo.AfElement;
import com.tyopf.vo.AfProcess;
import com.tyopf.vo.AfQualityProblem;
import com.tyopf.vo.AfValuation;
import com.tyopf.vo.Employee;
import com.tyopf.vo.User;

public class AFDAO extends BaseDAO implements IAFDAO {
	public AfBase getAFById(long afId) {
		Session session = getSession();
		// AfBase af = (AfBase) session.load(AfBase.class, afId);
		AfBase af = (AfBase) session.get(AfBase.class, afId);
		if (af.getClass() != null) {
			Map ClientSession = ActionContext.getContext().getSession();
			User u = (User) ClientSession.get("user");
			Logger logger=Logger.getLogger(this.getClass());
			logger.info(u.getUsername() + " Read AF:" + af.getIso()
					+ af.getAfNo());
			if (!Hibernate.isInitialized(af.getAfElement()))
				Hibernate.initialize(af.getAfElement());
			if (!Hibernate.isInitialized(af.getAfDispose()))
				Hibernate.initialize(af.getAfDispose());
			if (!Hibernate.isInitialized(af.getAfValuation()))
				Hibernate.initialize(af.getAfValuation());
			if (!Hibernate.isInitialized(af.getAfQualityProblem()))
				Hibernate.initialize(af.getAfQualityProblem());
			if(null != af.getViewTimes()){
				af.setViewTimes(af.getViewTimes()+1);
			}else{
				af.setViewTimes(1);
			}
			session.saveOrUpdate(af);
			session.flush();
			session.close();
			return af;
		}
		session.close();
		return null;
	}

	public void addAF(AfBase af) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(af);
		tx.commit();
		session.flush();
		session.close();
	}

	public void editAF(AfBase af) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(af);
		tx.commit();
		session.flush();
		session.close();
	}

	public void saveAF(AfBase af) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(af);
		tx.commit();
		session.flush();
		session.close();
	}

	public void saveAFE(AfElement afe) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(afe);
		tx.commit();
		session.flush();
		session.close();
	}

	public List getAFByHql(String hql) {
		Session session = getSession();
		Query query = session.createQuery(hql);
		List af = query.list();
		session.close();
		return af;
	}


	public List getAFs(int currentPage, int pageSize) {
		Session session = getSession();
		List AFs = new ArrayList();
		Query query = session
				.createQuery("from AfBase AF order by AF.afId desc");
		int startRow = (currentPage - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		List afList = query.list();
		AfBase af = new AfBase();
		for (Iterator iterator = afList.iterator(); iterator.hasNext();) {
			af = (AfBase) iterator.next();
			if (!Hibernate.isInitialized(af.getAfElement()))
				Hibernate.initialize(af.getAfElement());
			if (!Hibernate.isInitialized(af.getAfDispose()))
				Hibernate.initialize(af.getAfDispose());
			if (!Hibernate.isInitialized(af.getAfValuation()))
				Hibernate.initialize(af.getAfValuation());
			if (!Hibernate.isInitialized(af.getAfQualityProblem()))
				Hibernate.initialize(af.getAfQualityProblem());
			AFs.add(af);
		}
		session.close();
		return AFs;
	}

	public List getAllAFs() {
		Session session = getSession();
		Query query = session
				.createQuery("from AfBase AF order by AF.afId desc");
		List AllAFs = query.list();
		session.close();
		return AllAFs;
	}

	public void removeAF(long afId) {
		Session session = getSession();
		AfBase AF = (AfBase) session.get(AfBase.class, afId);
		session.delete(AF);
		session.flush();
		session.close();
	}

	public int getNextAFNo(String AfType) {
		int NextAFNo = 0;
		Session session = getSession();
		String hql = "from AfBase AF where AF.iso=? order by AF.afId desc";
		Query query = session.createQuery(hql);
		query.setParameter(0, AfType);
		query.setFirstResult(0);
		query.setMaxResults(10);
		List afList = query.list();
		if (afList.size() != 0) {
			AfBase af = (AfBase) afList.get(0);
			NextAFNo = af.getAfNo() + 1;
		}
		session.close();

		return NextAFNo;
	}
	public int getNextAFTypeNo(String AfType) {
		int NextAFTypeNo = 0;
		Session session = getSession();
		String hql = "from AfBase AF where AF.afType=? order by AF.afId desc";
		Query query = session.createQuery(hql);
		
		query.setParameter(0, AfType);
		query.setFirstResult(0);
		query.setMaxResults(10);
		List afList = query.list();
		if (afList.size() != 0) {
			AfBase af = (AfBase) afList.get(0);
			NextAFTypeNo = af.getAftypeNo() + 1;
		}
		session.close();

		return NextAFTypeNo;
	}

	@SuppressWarnings("unchecked")
	public List<String> getRecentColumnName(String columnName, int recentSize) {
		String hql = "select distinct " + columnName
				+ " from AfBase as AF order by AF.afId desc";
		// String hql="select distinct "+columnName+" from AfBase as AF";
		// 参数 distinct 是为了去掉重复记录
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setMaxResults(recentSize);
		query.setCacheable(true);
		@SuppressWarnings("unused")
		List<String> find = query.list();
		session.close();
		List<String> find2 = getHibernateTemplate().find(hql);
		return find2;
	}

	@SuppressWarnings("unchecked")
	public List<AfBase> getTodayAFs() {
		Session session = getSession();
		List AFs = new ArrayList();
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date begin = cal.getTime();
		cal.add(Calendar.DATE, 1);
		Date end = cal.getTime();
		Query query = session
				.createQuery("from AfBase AF where AF.ad>=:begin and AF.ad<:end order by AF.afId desc");
		query.setDate("begin", begin);
		query.setDate("end", end);
		List afList = query.list();
		AfBase af = new AfBase();
		for (Iterator iterator = afList.iterator(); iterator.hasNext();) {
			af = (AfBase) iterator.next();
			if (!Hibernate.isInitialized(af.getAfElement()))
				Hibernate.initialize(af.getAfElement());
			if (!Hibernate.isInitialized(af.getAfDispose()))
				Hibernate.initialize(af.getAfDispose());
			if (!Hibernate.isInitialized(af.getAfValuation()))
				Hibernate.initialize(af.getAfValuation());
			if (!Hibernate.isInitialized(af.getAfQualityProblem()))
				Hibernate.initialize(af.getAfQualityProblem());
			AFs.add(af);
		}

		session.close();
		return AFs;
	}

	@SuppressWarnings("unchecked")
	public int getTotalTodayAFs() {
		Session session = getSession();
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date begin = cal.getTime();
		cal.add(Calendar.DATE, 1);
		Date end = cal.getTime();
		Query query = session
				.createQuery("from AfBase AF where AF.ad>=:begin and AF.ad<:end order by AF.afId desc");
		query.setDate("begin", begin);
		query.setDate("end", end);
		List list = query.list();
		int TotalAFs = list.size();
		session.close();
		return TotalAFs;
	}

	@SuppressWarnings("unchecked")
	public List<AfBase> getAFByType(String AFType, int currentPage, int pageSize) {
		Session session = getSession();
		List AFs = new ArrayList();
		Query query = session
				.createQuery("from AfBase AF where AF.iso=:AFType order by AF.afId desc");
		int startRow = (currentPage - 1) * pageSize;
		query.setString("AFType", AFType);
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		List afList = query.list();
		AfBase af = new AfBase();
		for (Iterator iterator = afList.iterator(); iterator.hasNext();) {
			af = (AfBase) iterator.next();
			if (!Hibernate.isInitialized(af.getAfElement()))
				Hibernate.initialize(af.getAfElement());
			if (!Hibernate.isInitialized(af.getAfDispose()))
				Hibernate.initialize(af.getAfDispose());
			if (!Hibernate.isInitialized(af.getAfValuation()))
				Hibernate.initialize(af.getAfValuation());
			if (!Hibernate.isInitialized(af.getAfQualityProblem()))
				Hibernate.initialize(af.getAfQualityProblem());
			AFs.add(af);
		}
		session.close();
		return AFs;
	}

	@SuppressWarnings("unchecked")
	public List<AfBase> getAFByAFNo(int AFNo) {
		Session session = getSession();
		Query query = session
				.createQuery("from AfBase AF where AF.afNo like :AFNo order by AF.afId desc");
		query.setString("AFNo", "%" + AFNo + "%");
		query.setCacheable(true);
		List AFs = query.list();
		session.close();
		return AFs;
	}
	@SuppressWarnings("unchecked")
	public List<AfBase> getAFByYZNo(int YZNo) {
		Session session = getSession();
		Query query = session
		.createQuery("from AfBase AF where AF.pcAf like :YZNo order by AF.afId desc");
		query.setString("YZNo", "%" + YZNo + "%");
		query.setCacheable(true);
		List AFs = query.list();
		session.close();
		return AFs;
	}

	@SuppressWarnings("unchecked")
	public List<AfBase> getAFByName(String Name) {
		Session session = getSession();
		Query query = session
				.createQuery("from AfBase AF where AF.presswork like :Name order by AF.afId desc");
		query.setString("Name", "%" + Name + "%");
		Map ClientSession = ActionContext.getContext().getSession();
		User u = (User) ClientSession.get("user");
		Logger logger=Logger.getLogger(this.getClass());
		logger.warn(u.getUsername() + "%" + Name + "%");
		query.setCacheable(true);
		List AFs = query.list();
		session.close();
		return AFs;
	}

	public int getTotalAFsByType(String AFType) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfBase.class);
		criteria.add(Restrictions.eq("iso", AFType));
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

	public List getAllAFEs() {
		Session session = getSession();
		Query query = session
				.createQuery("from AfElement AFE order by AFE.afEId desc");
		query.setCacheable(true);
		List AFEs = query.list();
		session.close();
		return AFEs;
	}

	public int getCountofAllAFs() {
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfBase.class);
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

	public int getCountofAllAFEs() {
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfElement.class);
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

	public List getAFEs(int currentPage, int pageSize) {
		Session session = getSession();
		Query query = session
				.createQuery("from AfElement AFE order by AFE.afEId desc");
		int startRow = (currentPage - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		List AFEs = query.list();
		session.close();
		return AFEs;
	}

	public void editAFE(AfElement afe) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(afe);
		tx.commit();
		session.close();
	}

	public AfElement getAFEById(long afEId) {
		Session session = getSession();
		AfElement afe = (AfElement) session.get(AfElement.class, afEId);
		if (afe.getClass() != null) {
			afe.getAfBase();
			session.close();
			return afe;
		}
		session.close();
		return null;
	}

	public AfDispose getAFDById(long afDId) {
		Session session = getSession();
		AfDispose afd = (AfDispose) session.get(AfDispose.class, afDId);
		if (afd.getClass() != null) {
			afd.getAfBase();
			session.close();
			return afd;
		}
		session.close();
		return null;
	}

	public void editAFD(AfDispose afd) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(afd);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<AfBase> getAllAFByClient(String client) {
		Session session = getSession();
		Query query = session
				.createQuery("from AfBase AF where AF.client like :client order by AF.afId desc");
		query.setString("client", "%" + client + "%");
		query.setCacheable(true);
		List AFs = query.list();
		session.close();
		return AFs;
	}

	@SuppressWarnings("unchecked")
	public List<AfBase> getAFByClient(String client, int currentPage,
			int pageSize) {
		Session session = getSession();
		Query query = session
				.createQuery("from AfBase AF where AF.client like :client order by AF.afId desc");
		int startRow = (currentPage - 1) * pageSize;
		query.setString("client", "%" + client + "%");
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		List AFs = query.list();
		session.close();
		return AFs;
	}

	@SuppressWarnings("unchecked")
	public List<AfBase> getAFByDate(Date date) {
		Session session = getSession();
		// date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date begin = cal.getTime();
		cal.add(Calendar.DATE, 1);
		Date end = cal.getTime();
		Query query = session
				.createQuery("from AfBase AF where AF.ad>=:begin and AF.ad<:end order by AF.afId desc");
		query.setDate("begin", begin);
		query.setDate("end", end);
		List list = query.list();
		session.close();
		return list;
	}

	public int getCountofAllAFByBindingFactory(String BindingFactoryName) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfDispose.class);
		criteria.add(Restrictions.like("afDFactory", "%" + BindingFactoryName
				+ "%"));
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

	@SuppressWarnings("unchecked")
	public List<AfDispose> getAllAFByBindingFactory(String BindingFactoryName) {
		Session session = getSession();
		Query query = session
				.createQuery("from AfDispose AFD where AFD.afDFactory like :BindingFactoryName order by AFD.afDId desc");
		query.setString("BindingFactoryName", "%" + BindingFactoryName + "%");
		query.setCacheable(true);
		List AFs = query.list();
		session.close();
		return AFs;
	}

	@SuppressWarnings("unchecked")
	public List<AfDispose> getAFByBindingFactory(String BindingFactoryName,
			int currentPage, int pageSize) {
		Session session = getSession();
		Query query = session
				.createQuery("from AfDispose AFD where AFD.afDFactory like :BindingFactoryName order by AFD.afDId desc");
		int startRow = (currentPage - 1) * pageSize;
		query.setString("BindingFactoryName", "%" + BindingFactoryName + "%");
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		List AFs = query.list();
		session.close();
		return AFs;
	}

	@SuppressWarnings("unchecked")
	public List<AfDispose> getTodayAFsForBindingFactory(
			String BindingFactoryName) {
		Session session = getSession();
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date begin = cal.getTime();
		cal.add(Calendar.DATE, 1);
		Date end = cal.getTime();
		// Query query = session.createQuery("from AfBase AF where AF.ad>=:begin
		// and AF.ad<:end order by AF.afId desc");
		Query query = session
				.createQuery("from AfDispose AFD where AFD.afDFactory like :BindingFactoryName and AFD.AfBase.ad>=:begin and AFD.AfBase.ad<:end order by AFD.afDId desc");
		query.setDate("begin", begin);
		query.setDate("end", end);
		List list = query.list();

		session.close();
		return list;
	}

	public int getCountofFinishedAFEByItem(String ItemName) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfElement.class);
		criteria.add(Restrictions.isNotNull(ItemName));
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

	public int getCountofNotFinishedAFEByItem(String ItemName) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfElement.class);
		criteria.add(Restrictions.isNull(ItemName));
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}
	public int getCountofNotFinishedAFEByItemMachine(String ItemName,String machine) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfElement.class);
		criteria.add(Restrictions.isNull(ItemName));
		criteria.add(Restrictions.eq("EMachine", machine));
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

	public int getCountofFinishedAFDByItem(String ItemName) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfDispose.class);
		criteria.add(Restrictions.isNotNull(ItemName));
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

	public int getCountofNotFinishedAFDByItem(String ItemName) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfDispose.class);
		criteria.add(Restrictions.isNull(ItemName));
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

	public int getCountofFinishedDL() {
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfBase.class);
		criteria.add(Restrictions.isNotNull("comDeliver"));
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

	public int getCountofNotFinishedDL() {
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfBase.class);
		criteria.add(Restrictions.isNull("comDeliver"));
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

	@SuppressWarnings("unchecked")
	public List<AfElement> getFinishedAFEByItem(String ItemName,
			int currentPage, int pageSize) {
		Session session = getSession();
		Query query = session.createQuery("from AfElement AFE where AFE."
				+ ItemName + " > 0 order by AFE.afEId desc");
		// query.setString("ItemName", ItemName);
		int startRow = (currentPage - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		List AFEs = query.list();
		session.close();
		return AFEs;
	}

	@SuppressWarnings("unchecked")
	public List<AfElement> getNotFinishedAFEByItem(String ItemName,
			int currentPage, int pageSize) {
		Session session = getSession();
		Query query = session.createQuery("from AfElement AFE where AFE."
				+ ItemName + " = null order by AFE.afEId desc");
		// query.setString("ItemName", ItemName);
		int startRow = (currentPage - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		List AFEs = query.list();
		session.close();
		return AFEs;
	}

	@SuppressWarnings("unchecked")
	public List<AfDispose> getFinishedAFDByItem(String ItemName,
			int currentPage, int pageSize) {
		Session session = getSession();
		Query query = session.createQuery("from AfDispose AFD where AFD."
				+ ItemName + " > 0 order by AFD.afDId desc");
		// query.setString("ItemName", ItemName);
		int startRow = (currentPage - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		List AFDs = query.list();
		session.close();
		return AFDs;
	}

	@SuppressWarnings("unchecked")
	public List<AfDispose> getNotFinishedAFDByItem(String ItemName,
			int currentPage, int pageSize) {
		Session session = getSession();
		Query query = session.createQuery("from AfDispose AFD where AFD."
				+ ItemName + " = null order by AFD.afDId desc");
		// query.setString("ItemName", ItemName);
		int startRow = (currentPage - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		List AFDs = query.list();
		session.close();
		return AFDs;
	}

	@SuppressWarnings("unchecked")
	public List<AfBase> getFinishedDL(int currentPage, int pageSize) {
		Session session = getSession();
		Query query = session
				.createQuery("from AfBase AF where AF.comDeliver > 0 order by AF.afId desc");
		// query.setString("ItemName", ItemName);
		int startRow = (currentPage - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		List AFs = query.list();
		session.close();
		return AFs;
	}

	@SuppressWarnings("unchecked")
	public List<AfBase> getNotFinishedDL(int currentPage, int pageSize) {
		Session session = getSession();
		Query query = session
				.createQuery("from AfBase AF where AF.comDeliver = null order by AF.afId desc");
		// query.setString("ItemName", ItemName);
		int startRow = (currentPage - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		List AFs = query.list();
		session.close();
		return AFs;
	}

	@SuppressWarnings("unchecked")
	public List<AfBase> getTodayDL() {
		Session session = getSession();
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date begin = cal.getTime();
		cal.add(Calendar.DATE, 1);
		Date end = cal.getTime();
		Query query = session
				.createQuery("from AfBase AF where AF.planDeliver>=:begin and AF.planDeliver<:end order by AF.afId desc");
		query.setDate("begin", begin);
		query.setDate("end", end);
		List list = query.list();

		session.close();
		return list;
	}

	public AfBase getAFByNo(String AFNo) {
		String AFType = AFNo.substring(0, 2).toUpperCase();
		int AfNo = new Integer(AFNo.substring(2, 10));
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfBase.class);
		criteria.add(Restrictions.eq("iso", AFType));
		criteria.add(Restrictions.eq("afNo", AfNo));
		List afList = criteria.list();
		AfBase af = new AfBase();
		for (Iterator iterator = afList.iterator(); iterator.hasNext();) {
			af = (AfBase) iterator.next();
		}

		if (af.getClass() != null) {
			if (!Hibernate.isInitialized(af.getAfElement()))
				Hibernate.initialize(af.getAfElement());
			if (!Hibernate.isInitialized(af.getAfDispose()))
				Hibernate.initialize(af.getAfDispose());
			if (!Hibernate.isInitialized(af.getAfValuation()))
				Hibernate.initialize(af.getAfValuation());
			if (!Hibernate.isInitialized(af.getAfQualityProblem()))
				Hibernate.initialize(af.getAfQualityProblem());
			session.saveOrUpdate(af);
			session.close();
			return af;
		}
		session.close();
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<AfBase> getAFByNoRange(String AFType, long StartAFNo,
			long EndAFNo) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfBase.class);
		criteria.add(Restrictions.eq("iso", AFType));
		criteria.add(Restrictions.ge("afNo", (int) StartAFNo));
		criteria.add(Restrictions.le("afNo", (int) EndAFNo));
		criteria.addOrder(Order.asc("afNo"));

		List afList = criteria.list();
		for (Iterator iterator = afList.iterator(); iterator.hasNext();) {
			AfBase af = (AfBase) iterator.next();
			if (!Hibernate.isInitialized(af.getAfElement()))
				Hibernate.initialize(af.getAfElement());
			if (!Hibernate.isInitialized(af.getAfDispose()))
				Hibernate.initialize(af.getAfDispose());
			if (!Hibernate.isInitialized(af.getAfValuation()))
				Hibernate.initialize(af.getAfValuation());
			if (!Hibernate.isInitialized(af.getAfQualityProblem()))
				Hibernate.initialize(af.getAfQualityProblem());
		}

		session.close();
		return afList;
	}

	@SuppressWarnings("unchecked")
	public List<AfBase> getAFByNoRangeItemContent(String AFType,
			long StartAFNo, long EndAFNo, String Item, String Content) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfBase.class);
		criteria.add(Restrictions.eq("iso", AFType));
		criteria.add(Restrictions.ge("afNo", (int) StartAFNo));
		criteria.add(Restrictions.le("afNo", (int) EndAFNo));
		criteria.add(Restrictions.eq(Item, Content));
		criteria.addOrder(Order.asc("afNo"));

		List afList = criteria.list();
		for (Iterator iterator = afList.iterator(); iterator.hasNext();) {
			AfBase af = (AfBase) iterator.next();
			if (!Hibernate.isInitialized(af.getAfElement()))
				Hibernate.initialize(af.getAfElement());
			if (!Hibernate.isInitialized(af.getAfDispose()))
				Hibernate.initialize(af.getAfDispose());
			if (!Hibernate.isInitialized(af.getAfValuation()))
				Hibernate.initialize(af.getAfValuation());
			if (!Hibernate.isInitialized(af.getAfQualityProblem()))
				Hibernate.initialize(af.getAfQualityProblem());
		}

		session.close();
		return afList;
	}

	@SuppressWarnings("unchecked")
	public double getMoneyByNoRange(String AFType, long StartAFNo, long EndAFNo) {
		double moneyS = 0;
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfBase.class);

		criteria.add(Restrictions.eq("iso", AFType));
		criteria.add(Restrictions.ge("afNo", (int) StartAFNo));
		criteria.add(Restrictions.le("afNo", (int) EndAFNo));
		List afList = criteria.list();
		for (Iterator iterator = afList.iterator(); iterator.hasNext();) {
			AfBase af = (AfBase) iterator.next();
			if (null != af.getMoneyShould())
				moneyS = moneyS + new Double(af.getMoneyShould());
		}
		session.close();
		return moneyS;
	}

	public int getCountofCoverByMachineAndAFNo(String MachineName,
			long StartAFId, long EndAFId) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfElement.class);
		criteria.add(Restrictions.eq("EMachine", MachineName));
		criteria.add(Restrictions.eq("EType", "Cover"));
		criteria.add(Restrictions.ge("afEId", StartAFId));
		criteria.add(Restrictions.le("afEId", EndAFId));
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

	@SuppressWarnings("unchecked")
	public List<AfBase> getAFByMachine(String MachineName, int StartAFNo,
			int EndAFNo) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfElement.class);
		criteria.add(Restrictions.eq("EMachine", MachineName));
		criteria.add(Restrictions.ge("AfBase.afNo", StartAFNo));
		criteria.add(Restrictions.le("AfBase.afNo", EndAFNo));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List AFList = criteria.list();
		session.close();
		return AFList;
	}

	@SuppressWarnings("unchecked")
	public List<AfElement> getNotFinishedAFEByItemMachine(String ItemName,
			String Machine, int currentPage, int pageSize) {
		Session session = getSession();
		Query query = session
				.createQuery("from AfElement AFE where AFE."
						+ ItemName
						+ " = null AND AFE.EMachine like :Machine order by AFE.afEId desc");
		query.setString("Machine", "%" + Machine + "%");
		int startRow = (currentPage - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		List AFEs = query.list();
		session.close();
		return AFEs;
	}

	@SuppressWarnings("unchecked")
	public List<AfElement> getAFEByMachine(String MachineName, int currentPage,
			int pageSize) {
		Session session = getSession();
		String queryString = "";
		queryString = "from AfElement AFE order by AFE.afEId desc";
		if (MachineName.length() > 0)
			queryString = "from AfElement AFE where AFE.EMachine like '%"
					+ MachineName + "%' order by AFE.afEId desc";
		Query query = session.createQuery(queryString);
		int startRow = (currentPage - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		List AFEs = query.list();
		session.close();
		return AFEs;
	}

	@SuppressWarnings("unchecked")
	public List<AfElement> getAFEByType(String AFType, int currentPage,
			int pageSize) {
		Session session = getSession();
		Query query = session
				.createQuery("from AfElement AFE where AFE.AfBase.iso like :AFType order by AFE.afEId desc");
		query.setString("AFType", "%" + AFType + "%");
		int startRow = (currentPage - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		List AFEs = query.list();
		session.close();
		return AFEs;
	}

	public int getCountofAFEbyMachine(String MachineName) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfElement.class);
		if (MachineName.length() > 0)
			criteria.add(Restrictions.eq("EMachine", MachineName));
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

	public int getCountofAFEbyType(String AFType) {
		Session session = getSession();
		Query query = session
				.createQuery("from AfElement AFE where AFE.AfBase.iso like :AFType order by AFE.afEId desc");
		query.setString("AFType", "%" + AFType + "%");
		query.setCacheable(true);
		List AFEs = query.list();
		session.close();
		return AFEs.size();
	}

	@SuppressWarnings("unchecked")
	public List<AfBase> getAFByYW(String YWName, int currentPage, int pageSize) {
		List AFs = new ArrayList();
		Session session = getSession();
		Query query = session
				.createQuery("from AfBase AF where AF.fmp like :YWName order by AF.afId desc");
		int startRow = (currentPage - 1) * pageSize;
		query.setString("YWName", "%" + YWName + "%");
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		List afList = query.list();
		AfBase af = new AfBase();
		for (Iterator iterator = afList.iterator(); iterator.hasNext();) {
			af = (AfBase) iterator.next();
			if (!Hibernate.isInitialized(af.getAfElement()))
				Hibernate.initialize(af.getAfElement());
			if (!Hibernate.isInitialized(af.getAfDispose()))
				Hibernate.initialize(af.getAfDispose());
			if (!Hibernate.isInitialized(af.getAfValuation()))
				Hibernate.initialize(af.getAfValuation());
			if (!Hibernate.isInitialized(af.getAfQualityProblem()))
				Hibernate.initialize(af.getAfQualityProblem());
			AFs.add(af);
		}

		session.close();
		return AFs;
	}

	public int getCountofAFbyYW(String YWName) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfBase.class);
		criteria.add(Restrictions.like("fmp", "%" + YWName + "%"));
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

	public void removeAFD(long afDId) {
		Session session = getSession();
		AfDispose afd = (AfDispose) session.get(AfDispose.class, afDId);
		if (afd.getClass() != null) {
			session.delete(afd);
		}
		session.flush();
		session.close();
	}

	public void removeAFE(long afEId) {
		Session session = getSession();
		AfElement afe = (AfElement) session.get(AfElement.class, afEId);
		if (afe.getClass() != null) {
			session.delete(afe);
		}
		session.flush();
		session.close();
	}

	public void removeAFV(long afVId) {
		Session session = getSession();
		AfValuation afv = (AfValuation) session.get(AfValuation.class, afVId);
		if (afv.getClass() != null) {
			session.delete(afv);
		}
		session.flush();
		session.close();
	}

	public List<AfBase> getAFByChejian(String ChejianName, String AFType,
			long StartAFNo, long EndAFNo) {
		List<AfBase> afListget = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfBase.class);

		criteria.add(Restrictions.eq("iso", AFType));
		criteria.add(Restrictions.ge("afNo", (int) StartAFNo));
		criteria.add(Restrictions.le("afNo", (int) EndAFNo));
		List afList = criteria.list();
		for (Iterator iterator = afList.iterator(); iterator.hasNext();) {
			AfBase af = (AfBase) iterator.next();

			if (af.getAfValuation() != null) {
				for (Iterator iteratorv = af.getAfValuation().iterator(); iteratorv
						.hasNext();) {
					AfValuation afv = (AfValuation) iteratorv.next();
					if (afv.getTotalAmount() > 0) {
						if (null != afv.getChejian()) {
							if (ChejianName.equals(afv.getChejian())) {
								// afListget.add(afv.getAfBase());
								System.out
										.println("afGet:" + af.getPresswork());
							}
						}
					}
				}
			}
		}
		session.close();
		return afListget;
	}

	@SuppressWarnings("unchecked")
	public List<AfValuation> getAFVByChejian(String YWName, String ChejianName,
			String AFType, long StartAFNo, long EndAFNo) {
		Session session = getSession();
		Query query = session
				.createQuery("from AfValuation AFV where AFV.chejian like :ChejianName and AFV.AfBase.cp like :YWName and AFV.AfBase.iso like :AFType and AFV.AfBase.afNo >= "
						+ StartAFNo + " and AFV.AfBase.afNo <= " + EndAFNo);
		query.setString("YWName", "%" + YWName + "%");
		query.setString("ChejianName", "%" + ChejianName + "%");
		query.setString("AFType", "%" + AFType + "%");
		query.setCacheable(true);
		List AFVs = query.list();
		session.close();
		return AFVs;
	}

	@SuppressWarnings("unchecked")
	public List<AfBase> getAFByYWandType(String YWName, String AFType,
			long StartAFNo, long EndAFNo) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfBase.class);

		criteria.add(Restrictions.eq("cp", YWName));
		criteria.add(Restrictions.eq("iso", AFType));
		criteria.add(Restrictions.ge("afNo", (int) StartAFNo));
		criteria.add(Restrictions.le("afNo", (int) EndAFNo));
		List afList = criteria.list();
		session.close();
		return afList;
	}

	@SuppressWarnings("unchecked")
	public List<AfBase> getAFByClientNoRange(String client, String AFType,
			long StartAFNo, long EndAFNo) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfBase.class);

		criteria.add(Restrictions.eq("client", client));
		criteria.add(Restrictions.eq("iso", AFType));
		criteria.add(Restrictions.ge("afNo", (int) StartAFNo));
		criteria.add(Restrictions.le("afNo", (int) EndAFNo));
		List<AfBase> afList = criteria.list();
		session.close();
		return afList;
	}

	public double getMoneyByAFlist(List<AfBase> aflist) {
		double moneyS = 0;
		Session session = getSession();
		for (Iterator iterator = aflist.iterator(); iterator.hasNext();) {
			AfBase af = (AfBase) iterator.next();
			if (null != af.getMoneyShould())
				moneyS = moneyS + new Double(af.getMoneyShould());
		}
		session.close();
		return moneyS;
	}

	public double getMoneyByAFVlist(List<AfValuation> afvlist) {
		double moneyV = 0;
		Session session = getSession();
		for (Iterator iterator = afvlist.iterator(); iterator.hasNext();) {
			AfValuation afv = (AfValuation) iterator.next();
			if (afv.getTotalAmount() > 0)
				moneyV = moneyV + new Double(afv.getTotalAmount());
		}
		session.close();
		return moneyV;
	}

	public double getMoneyGETByAFlist(List<AfBase> aflist) {
		double moneyG = 0;
		Session session = getSession();
		for (Iterator iterator = aflist.iterator(); iterator.hasNext();) {
			AfBase af = (AfBase) iterator.next();
			if (null != af.getMoneyFact())
				moneyG = moneyG + new Double(af.getMoneyFact());
		}
		session.close();
		return moneyG;
	}

	@SuppressWarnings("unchecked")
	public List<AfBase> getAFinAFNoList(String AFNoList) {

		String ss[] = AFNoList.split(",");

		List AFs = new ArrayList();
		for (int i = 0; i < ss.length; i++) {
			String AFType = ss[i].substring(0, 2).toUpperCase();
			int AfNo = new Integer(ss[i].substring(2, 10));
			Session session = getSession();
			Criteria criteria = session.createCriteria(AfBase.class);
			criteria.add(Restrictions.eq("iso", AFType));
			criteria.add(Restrictions.eq("afNo", AfNo));
			List afList = criteria.list();
			AfBase af = new AfBase();
			for (Iterator iterator = afList.iterator(); iterator.hasNext();) {
				af = (AfBase) iterator.next();
				if (!Hibernate.isInitialized(af.getAfElement()))
					Hibernate.initialize(af.getAfElement());
				if (!Hibernate.isInitialized(af.getAfDispose()))
					Hibernate.initialize(af.getAfDispose());
				if (!Hibernate.isInitialized(af.getAfValuation()))
					Hibernate.initialize(af.getAfValuation());
				if (!Hibernate.isInitialized(af.getAfQualityProblem()))
					Hibernate.initialize(af.getAfQualityProblem());
				AFs.add(af);
			}

			session.close();
		}

		return AFs;
	}

	public List<AfBase> getAFinYZNoList(String YZNoList) {
		Session session = getSession();
		String ss[] = YZNoList.split(",");
		String YZNoListToGet = "\'88888888888888\'";
		for (int i = 0; i < ss.length; i++) {
			YZNoListToGet = YZNoListToGet + ",\'" + ss[i] + "\'";
			
		}
		Query query = session.createQuery("from AfBase af where af.pcAf in ("
				+ YZNoListToGet + ") order by af.pcAf asc");
		List<AfBase> AFs = query.list();
		session.close();
		return AFs;
	}

	public int getCountofProcessByType(String processType) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfProcess.class);
		if (processType != null && !"".equals(processType)) {
			criteria.add(Restrictions.like("type", "%" + processType + "%"));
		}
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

	public List getProcessByType(String processType, int currentPage,
			int pageSize) {
		Session session = getSession();
		Query query;

		if (processType != null && !"".equals(processType)) {
			query = session
					.createQuery("from AfProcess AP where AP.type like :processType order by AP.id desc");
			query.setString("processType", "%" + processType + "%");
		} else {
			query = session
					.createQuery("from AfProcess AP order by AP.id desc");
		}

		int startRow = (currentPage - 1) * pageSize;
		query.setFirstResult(startRow);
		
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		List processList = query.list();
		session.close();
		return processList;
	}

	public void removeAfProcess(int afpId) {
		Session session = getSession();
		AfProcess afp = (AfProcess) session.get(AfProcess.class, afpId);
		if (afp.getClass() != null) {
			session.delete(afp);
		}
		session.flush();
		session.close();

	}

	public void saveAfProcess(AfProcess afp) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(afp);
		tx.commit();
		session.close();

	}
	public List<AfBase> searchAF(String searchOption, String searchValue) {
		Session session = getSession();
		Query query = session.createQuery("from AfBase af where af." + searchOption + " like :SearchValue order by af.afId desc");
		query.setString("SearchValue", "%" + searchValue + "%");
		List list = query.list();
		session.close();
		return list;
	}

	@Override
	public AfQualityProblem getAFQPById(int id) {
		Session session = getSession();
		AfQualityProblem afqp = (AfQualityProblem) session.get(AfQualityProblem.class, id);
		if (afqp.getClass() != null) {
			afqp.getAfBase();
			session.close();
			return afqp;
		}
		session.close();
		return null;
	}

	@Override
	public List getAllQualityProblem(int currentPage, int pageSize) {
		Session session = getSession();
		Query query = session.createQuery("from AfQualityProblem QP order by QP.id desc");
		int startRow = (currentPage - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		List listq = query.list();
		session.close();
		return listq;
	}

	@Override
	public int getCountofAllQualityProblem() {
		Session session = getSession();
		Criteria criteria = session.createCriteria(AfQualityProblem.class);
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}

	@Override
	public void removeAfQualityProblem(int id) {
		Session session = getSession();
		AfQualityProblem qp = (AfQualityProblem) session.get(AfQualityProblem.class, id);
		session.delete(qp);
		session.flush();
		session.close();
		
	}

	@Override
	public void saveAfQualityProblem(AfQualityProblem afqp) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(afqp);
		tx.commit();
		session.flush();
		session.close();		
	}
	public AfQualityProblem getLastAfQualityProblem() {
		Session session = getSession();
		Query query = session.createQuery("from AfQualityProblem q order by q.id desc");
		AfQualityProblem q = new AfQualityProblem();
		if (query.list().size() != 0) {
			q = (AfQualityProblem) query.list().get(0);
		}
		session.saveOrUpdate(q);
		session.close();
		return q;
	}
}
