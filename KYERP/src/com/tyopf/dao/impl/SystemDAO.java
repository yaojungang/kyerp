package com.tyopf.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.tyopf.dao.BaseDAO;
import com.tyopf.dao.ISystemDAO;
import com.tyopf.vo.CompanyDept;
import com.tyopf.vo.Role;
import com.tyopf.vo.SystemFunctions;
import com.tyopf.vo.SystemModules;

public class SystemDAO extends BaseDAO implements ISystemDAO {

	public CompanyDept getDeptById(int id) {
		Session session = getSession();
		CompanyDept dept = (CompanyDept) session.get(CompanyDept.class, id);
		session.close();
		return dept;
	}

	@SuppressWarnings("unchecked")
	public List<CompanyDept> getDeptList(int upDeptId) {
		Session session = getSession();
		String queryDeptByUpDeptId = "from CompanyDept d where d.upDeptId=? order by d.deptOrder";
		Query query = session.createQuery(queryDeptByUpDeptId);
		query.setParameter(0, upDeptId);
		List<CompanyDept> findedDepts = query.list();
		for(CompanyDept dept : findedDepts){
			if (!Hibernate.isInitialized(dept.getRoles())) Hibernate.initialize(dept.getRoles());
		}
		session.close();
		return findedDepts;
	}

	public void removeDept(int deptId) {
		getHibernateTemplate().delete(getDeptById(deptId));
	}

	public void saveDept(CompanyDept dept) {
		getHibernateTemplate().saveOrUpdate(dept);
	}
	public void editDept(CompanyDept dept) {
		getHibernateTemplate().saveOrUpdate(dept);
	}
	public SystemFunctions getSystemFunctionById(int id) {
		return (SystemFunctions) getHibernateTemplate().get(SystemFunctions.class, id);
	}
	@SuppressWarnings("unchecked")
	public List<SystemFunctions> getSystemFunctionsByModuleId(int moduleId) {
		String queryByModuleId = "from SystemFunctions sf where sf.systemModule.id = ?";
		List<SystemFunctions> findeds = getHibernateTemplate().find(queryByModuleId,moduleId);
		return findeds;
	}
	@SuppressWarnings("unchecked")
	public List<SystemFunctions> getAllSystemFunctions() {
		String query = "from SystemFunctions";
		List<SystemFunctions> findeds = getHibernateTemplate().find(query);
		return findeds;
	}

	public void removeSystemFunction(int id) {
		getHibernateTemplate().delete(getSystemFunctionById(id));
	}
	public SystemFunctions getLastSystemFunction() {
		Session session = getSession();
		Query query = session
		.createQuery("from SystemFunctions sf order by sf.id desc");
		SystemFunctions sf = new SystemFunctions();
		if (query.list().size() != 0) {
			sf = (SystemFunctions) query.list().get(0);
		}
		session.saveOrUpdate(sf);
		session.close();
		return sf;
	}
	public void saveSystemFunction(SystemFunctions systemFunction) {
		getHibernateTemplate().saveOrUpdate(systemFunction);
	}

	public void saveRole(Role role) {
		getHibernateTemplate().saveOrUpdate(role);		
	}

	public Role getRoleById(int id) {
		return (Role) getHibernateTemplate().get(Role.class,id);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRoleByDeptId(int deptId) {
		String queryByDeptId = "from Role role where role.companyDept.id = ?";
		List<Role> findedRights = getHibernateTemplate().find(queryByDeptId,deptId);
		return findedRights;
	}

	@SuppressWarnings("unchecked")
	public List<SystemModules> getAllModules() {
		String query = "from SystemModules";
		List<SystemModules> findeds = getHibernateTemplate().find(query);
		return findeds;
	}

	public SystemModules getSystemModuleById(int id) {
		Session session = getSession();
		SystemModules systemModule = (SystemModules) session.get(SystemModules.class, id);
		session.close();
		return systemModule;
	}

}