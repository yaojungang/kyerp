package com.tyopf.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tyopf.dao.BaseDAO;
import com.tyopf.dao.IUserDAO;
import com.tyopf.util.Encrypt;
import com.tyopf.vo.Employee;
import com.tyopf.vo.EmployeeFamily;
import com.tyopf.vo.EmployeeResume;
import com.tyopf.vo.Role;
import com.tyopf.vo.User;

public class UserDAO extends BaseDAO implements IUserDAO {
	public void addUser(User user) {
		Encrypt te = new Encrypt();
		user.setPassword(te.StringEncrypt(user.getPassword(), "MD5"));
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(user);
		tx.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public User validateUser(String username, String password) {
		Encrypt te = new Encrypt();
		password = te.StringEncrypt(password, "MD5");
		Session session = getSession();
		String hql = "from User u where u.username=? and u.password=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, username);
		query.setParameter(1, password);
		List<User> users = query.list();
		if (users.size() != 0) {
			User user = (User) users.get(0);
			if (!Hibernate.isInitialized(user.getEmployee())) Hibernate.initialize(user.getEmployee());
			if (!Hibernate.isInitialized(user.getRoles())) Hibernate.initialize(user.getRoles());
			session.close();
			return user;
		}
		session.close();
		return null;
	}
	
	public List getAllUsers() {
		Session session = getSession();
		String hql = "from User u";
		Query query = session.createQuery(hql);
		List AllUsers = query.list();
		session.close();
		return AllUsers;
	}
	
	public List getUsers(int currentPage, int pageSize) {
		Session session = getSession();
		Query query = session.createQuery("from User u");
		int startRow = (currentPage - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		List Users = query.list();
		session.close();
		return Users;
	}
	
	public int getTotalUsers() {
		Session session = getSession();
		Query query = session.createQuery("from User u");
		List Users = query.list();
		int TotalUsers = Users.size();
		session.close();
		return TotalUsers;
	}
	
	public void removeUser(int UserId) {
		Session session = getSession();
		User userd = (User) session.get(User.class, UserId);
		session.delete(userd);
		session.flush();
		session.close();
	}
	
	public List getUserByHql(String hql) {
		Session session = getSession();
		Query query = session.createQuery(hql);
		List users = query.list();
		session.close();
		return users;
	}
	
	public User getUserById(int UserId) {
		Session session = getSession();
		User user = (User) session.get(User.class, UserId);
		if (user.getClass() != null) {
			if (null != user.getRoles()) {
				if (!Hibernate.isInitialized(user.getRoles())) Hibernate.initialize(user.getRoles());
			}
			session.saveOrUpdate(user);
			session.close();
			return user;
		}
		session.close();
		return null;
	}
	
	public void editUser(User user) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.merge(user);
		tx.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> getAllRoles() {
		return getHibernateTemplate().find("from Role role");
	}
	
	public void addRole(Role role) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(role);
		tx.commit();
		session.close();
	}
	
	public void editRole(Role role) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		// session.saveOrUpdate(role);
		// System.out.println("save role id:"+role.getId());
		session.merge(role);
		tx.commit();
		session.close();
	}
	
	public void removeRole(int RoleId) {
		Session session = getSession();
		Role Roled = (Role) session.get(Role.class, RoleId);
		session.delete(Roled);
		session.flush();
		session.close();
	}
	
	public Role getRoleById(int RoleId) {
		Session session = getSession();
		Role role = (Role) session.get(Role.class, RoleId);
		if (role != null) {
			if (null != role.getUsers()) {
				if (!Hibernate.isInitialized(role.getUsers())) Hibernate.initialize(role.getUsers());
			}
			if (null != role.getSystemFunctions()) {
				if (!Hibernate.isInitialized(role.getSystemFunctions())) Hibernate.initialize(role.getSystemFunctions());
			}
			session.save(role);
			session.close();
			return role;
		}
		session.close();
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUserByRole(int RoleId) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("Role.id", RoleId));
		List listUser = criteria.list();
		session.close();
		return listUser;
	}
	
	public void addEmployee(Employee employee) {
		Session session = getSession();
		session.save(employee);
		session.close();
	}
	
	public void delEmployee(int employeeId) {
		Session session = getSession();
		Employee employee = (Employee) session.get(Employee.class, employeeId);
		session.delete(employee);
		session.flush();
		session.close();
	}
	
	public Employee getEmployeeById(int employeeId) {
		Session session = getSession();
		Employee employee = (Employee) session.get(Employee.class, employeeId);
		if (!Hibernate.isInitialized(employee.getUser())) Hibernate.initialize(employee.getUser());
		if (!Hibernate.isInitialized(employee.getUser().getRoles())) Hibernate.initialize(employee.getUser().getRoles());
		if (!Hibernate.isInitialized(employee.getResume())) Hibernate.initialize(employee.getResume());
		if (!Hibernate.isInitialized(employee.getFamily())) Hibernate.initialize(employee.getFamily());
		session.saveOrUpdate(employee);
		session.close();
		return employee;
	}
	
	public void saveEmployee(Employee employee) {
		Session session = getSession();
		// System.out.println("DEPTid:"+employee.getCompanyDept().getId());
		session.saveOrUpdate(employee);
		session.flush();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployee() {
		Session session = getSession();
		Query query = session.createQuery("from Employee employee order by employee.id desc");
		List employees = query.list();
		session.close();
		return employees;
	}
	
	public Employee getLastEmployee() {
		Session session = getSession();
		Query query = session.createQuery("from Employee employee order by employee.id desc");
		Employee employee = new Employee();
		if (query.list().size() != 0) {
			employee = (Employee) query.list().get(0);
		}
		if (!Hibernate.isInitialized(employee.getResume())) Hibernate.initialize(employee.getResume());
		if (!Hibernate.isInitialized(employee.getFamily())) Hibernate.initialize(employee.getFamily());
		session.saveOrUpdate(employee);
		session.close();
		return employee;
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> getEmployeeByDept(String deptName) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.like("dept", "%" + deptName + "%"));
		List employees = criteria.list();
		session.close();
		return employees;
	}
	
	public int getCountofAllEmployee() {
		Session session = getSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}
	
	public void removeEmployeeFamily(int familyId) {
		Session session = getSession();
		EmployeeFamily family = (EmployeeFamily) session.get(EmployeeFamily.class, familyId);
		session.delete(family);
		session.flush();
		session.close();
	}
	
	public void removeEmployeeResume(int resumeId) {
		Session session = getSession();
		EmployeeResume resume = (EmployeeResume) session.get(EmployeeResume.class, resumeId);
		session.delete(resume);
		session.flush();
		session.close();
	}
	
	public List<Employee> getEmployeeByWorkStatus(int workStatus, int currentPage, int pageSize) {
		Session session = getSession();
		Query query = session.createQuery("from Employee employee where employee.workStatus=? order by employee.id desc");
		int startRow = (currentPage - 1) * pageSize;
		query.setParameter(0, workStatus);
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		List employees = query.list();
		session.close();
		return employees;
	}
	
	public int getConuntOfEmployeeListByWorkStatus(int workStatus) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("workStatus", workStatus));
		criteria.setProjection(Projections.rowCount());
		int n = ((Integer) criteria.list().get(0)).intValue();
		session.close();
		return n;
	}
	
	public List<Employee> getEmployeeByDeptId(int deptId) {
		Session session = getSession();
		Query query = session.createQuery("from Employee e where e.companyDept.id=? order by e.id");
		query.setParameter(0, deptId);
		List employees = query.list();
		session.close();
		return employees;
	}
	
	@SuppressWarnings("unchecked")
	public int getEmpNo(int id) {
		Session session = getSession();
		Employee employee0 = (Employee) session.get(Employee.class, id);
		Date pDate = employee0.getParticipateDate();
		String pYear = pDate.toString().substring(0, 4);
		Date startTime = java.sql.Date.valueOf(pYear+"-01-01");
		Date endTime = java.sql.Date.valueOf(pYear+"-12-31");
		String hql = "from Employee as e where e.participateDate >= :startTime " + "and e.participateDate <= :endTime order by e.participateDate";
		String[] params = { "startTime", "endTime"};
		Object[] args = { startTime, endTime};
		List<Employee> list = this.getHibernateTemplate().findByNamedParam(hql, params, args);
		//System.out.println("time:"+startTime+" --- "+endTime+" Num:"+list.size());
		session.close();
		int i=0;
		for(Employee e:list){
			i=i+1;
			int seNo = 1000+i;
			int empNo = new Integer(pYear)*10000+seNo -1000;
			System.out.println(e.getRealname()+"的员工号码为："+empNo);
			if(e.getId()==id){
				return empNo;
			}
		}
		return 0;
	}
}
