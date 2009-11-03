package org.kyerp.test;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kyerp.dao.IUserDAO;
import org.kyerp.domain.Employee;
import org.kyerp.domain.Role;
import org.kyerp.domain.User;

public class EmployeeTest implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private IUserDAO userDAO;

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {}
	
	@Test
	public void save() {
		// EntityManagerFactory相当于Hibernate中的sessionFactory
		// Persistence.createEntityManagerFactory("DreamProject")与配置文件中的持久化单元名称必须相同
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpaUnit");
		// EntityManager相当于Hibernate中session
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		// 保存(持久化)方法
		User u = new User();
		u.setUserName("用户");
		u.setPassWord("password");
		Employee e = new Employee();
		e.setRealname("张三");
		e.setUser(u);
		u.setEmployee(e);
		
		Role r = new Role();
		r.setRoleName("rolename test");
		Set<User> users = new HashSet<User>();
		users.add(u);
		r.setUsers(users);
		Set<Role> roles = new HashSet<Role>();
		roles.add(r);
		u.setRoles(roles);
		
		em.persist(e);
		em.persist(r);
		em.persist(u);
		
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	@Test
	public void testSpring(){
		User u = new User();
		u.setUserName("用户");
		u.setPassWord("password");
		Employee e = new Employee();
		e.setRealname("张三");
		e.setUser(u);
		u.setEmployee(e);
		
		Role r = new Role();
		r.setRoleName("rolename test");
		Set<User> users = new HashSet<User>();
		users.add(u);
		r.setUsers(users);
		Set<Role> roles = new HashSet<Role>();
		roles.add(r);
		u.setRoles(roles);
		
		userDAO.save(u);
	}
	
}
