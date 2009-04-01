package com.tyopf.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.tyopf.dao.ISystemDAO;
import com.tyopf.dao.IUserDAO;
import com.tyopf.service.IUserService;
import com.tyopf.util.Encrypt;
import com.tyopf.vo.Employee;
import com.tyopf.vo.EmployeeFamily;
import com.tyopf.vo.EmployeeResume;
import com.tyopf.vo.Role;
import com.tyopf.vo.User;

public class UserService implements IUserService {
	private ISystemDAO systemDAO;
	private IUserDAO userDAO;

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public ISystemDAO getSystemDAO() {
		return systemDAO;
	}

	public void setSystemDAO(ISystemDAO systemDAO) {
		this.systemDAO = systemDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void addUser(User user) {

		userDAO.addUser(user);
	}

	public User validateUser(String username, String password) {

		return userDAO.validateUser(username, password);
	}

	public List getAllUsers() {
		return userDAO.getAllUsers();
	}

	public int getTotalUsers() {
		return userDAO.getTotalUsers();
	}

	public List getUsers(int currentPage, int pageSize) {
		return userDAO.getUsers(currentPage, pageSize);
	}

	public void editUser(User user) {
		userDAO.editUser(user);
	}

	public List getUserByHql(String hql) {
		return userDAO.getUserByHql(hql);
	}

	public void removeUser(int UserId) {
		userDAO.removeUser(UserId);
	}

	public User getUserById(int UserId) {
		return userDAO.getUserById(UserId);
	}

	public void addRole(Role userRole) {
		userDAO.addRole(userRole);
	}

	public void editRole(Role userRole) {
		userDAO.editRole(userRole);
	}

	public List<Role> getAllRoles() {
		return userDAO.getAllRoles();
	}

	public Role getRoleById(int RoleId) {
		return userDAO.getRoleById(RoleId);
	}

	public void removeRole(int userRoleId) {
		userDAO.removeRole(userRoleId);
	}

	public List<User> getUserByRole(int RoleId) {
		return userDAO.getUserByRole(RoleId);
	}

	public void ChangeMyPassword_save(int id,String userPassword) {
		User user = userDAO.getUserById(id);
		Encrypt te = new Encrypt();
		user.setPassword(te.StringEncrypt(userPassword, "MD5"));
		userDAO.editUser(user);
	}

	public List<Employee> getEmployeeByDeptId(int deptId) {
		return userDAO.getEmployeeByDeptId(deptId);
	}

	public Employee getEmployeeById(int id) {
		return userDAO.getEmployeeById(id);
	}

	public Employee ChangeMyInfo_save(Employee employee, User user, List<EmployeeResume> resumes,List<EmployeeFamily> familys) {
		List resumeList = new ArrayList();
		List familyList = new ArrayList();
		Employee employee0 = userDAO.getEmployeeById(employee.getId());
		employee0.setNativeplace(employee.getNativeplace());
		employee0.setWeeding(employee.getWeeding());
		employee0.setRpraddress(employee.getRpraddress());
		employee0.setRprtype(employee.getRprtype());
		employee0.setGraduteDate(employee.getGraduteDate());
		employee0.setSchool(employee.getSchool());
		employee0.setDegree(employee.getDegree());
		employee0.setSpeciality(employee.getSpeciality());
		employee0.setInterest(employee.getInterest());
		employee0.setWorkTel(employee.getWorkTel());
		employee0.setMobile(employee.getMobile());
		employee0.setAddress(employee.getAddress());
		employee0.setTel(employee.getTel());
		employee0.setRprtel(employee.getRprtel());
		employee0.setEmail(employee.getEmail());
		employee0.setQq(employee.getQq());
		employee0.setMsn(employee.getMsn());
		employee0.setSelfDesc(employee.getSelfDesc());
		if (user != null) {
			User user0 = userDAO.getUserById(user.getId());
			// 处理用户名和密码不变
			user.setUsername(user0.getUsername());
			user.setPassword(user0.getPassword());
			//用户角色不变			
			user.setRoles(user0.getRoles());
			//用户类型不变
			user.setUserType(user0.getUserType());
			
			user.setEmployee(employee0);
			employee0.setUser(user);
		}

		if (resumes != null) {
			for (Iterator iterator = resumes.iterator(); iterator.hasNext();) {
				EmployeeResume resume = (EmployeeResume) iterator.next();
				if (!("".equals(resume.getCompany().trim()))) {
					resume.setEmployee(employee0);
					resumeList.add(resume);
				} else {
					if (resume.getId() != 0) {
						userDAO.removeEmployeeResume(resume.getId());
					}
				}
			}

		}
		if (familys != null) {
			for (Iterator iterator = familys.iterator(); iterator.hasNext();) {
				EmployeeFamily family = (EmployeeFamily) iterator.next();
				if (!("".equals(family.getName().trim()))) {
					family.setEmployee(employee0);
					familyList.add(family);
				} else {
					if (family.getId() != 0) {
						userDAO.removeEmployeeFamily(family.getId());
					}
				}
			}

		}
		employee0.setResume(resumeList);
		employee0.setFamily(familyList);
		userDAO.saveEmployee(employee0);
		return employee0;
	}
}
