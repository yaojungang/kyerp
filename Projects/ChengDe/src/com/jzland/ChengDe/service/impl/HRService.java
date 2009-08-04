package com.jzland.ChengDe.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import com.jzland.ChengDe.dao.ISystemDAO;
import com.jzland.ChengDe.dao.IUserDAO;
import com.jzland.ChengDe.service.IHRService;
import com.jzland.ChengDe.util.Encrypt;
import com.jzland.ChengDe.vo.Employee;
import com.jzland.ChengDe.vo.EmployeeFamily;
import com.jzland.ChengDe.vo.EmployeeResume;
import com.jzland.ChengDe.vo.Role;
import com.jzland.ChengDe.vo.User;

public class HRService implements IHRService {
	private IUserDAO userDAO;
	private ISystemDAO systemDAO;

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

	@SuppressWarnings("unchecked")
	public Employee addEmployee_save(Employee emp, User user,int roles0Id) {
		//处理用户角色
		Set roleSet = new HashSet();
		roleSet.add(systemDAO.getRoleById(roles0Id));
		user.setRoles(roleSet);
		//建立用户和Emp的关系
		user.setEmployee(emp);
		emp.setUser(user);
		//设置用户状态为试用期状态
		emp.setWorkStatus(1);
		//设置进厂时间为当前时间
		Timestamp t = new Timestamp(new GregorianCalendar(TimeZone
				.getTimeZone("GMT")).getTimeInMillis());
		emp.setParticipateDate(t);
		userDAO.saveEmployee(emp);
		userDAO.addUser(user);
		Employee employeeDb = userDAO.getLastEmployee();
		employeeDb.setEmpNo(userDAO.getEmpNo(employeeDb.getId()));
		user.setEmployee(employeeDb);
		
		userDAO.saveEmployee(employeeDb);
		return employeeDb;
	}

	@SuppressWarnings("unchecked")
	public Employee editEmployee_save(Employee emp, User user,List<EmployeeResume> resumes, List<EmployeeFamily> familys,int roles0Id) {
		List resumeList = new ArrayList();
		List familyList = new ArrayList();
		Employee employee0 = userDAO.getEmployeeById(emp.getId());
		//员工编号不变
		emp.setEmpNo(employee0.getEmpNo());
		
		if (user != null) {
			User user0 = userDAO.getUserById(user.getId());
			// 处理用户名和密码不变
			user.setUsername(user0.getUsername());
			user.setPassword(user0.getPassword());
			//处理用户角色
			Set roleSet = new HashSet();
			roleSet.add(systemDAO.getRoleById(roles0Id));
			user.setRoles(roleSet);
			
			user.setEmployee(emp);
			emp.setUser(user);
			// userDAO.editUser(user);
		}

		if (resumes != null) {
			for (Iterator iterator = resumes.iterator(); iterator.hasNext();) {
				EmployeeResume resume = (EmployeeResume) iterator.next();
				if (!("".equals(resume.getCompany().trim()))) {
					resume.setEmployee(emp);
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
					family.setEmployee(emp);
					familyList.add(family);
				} else {
					if (family.getId() != 0) {
						userDAO.removeEmployeeFamily(family.getId());
					}
				}
			}

		}
		emp.setResume(resumeList);
		emp.setFamily(familyList);
		userDAO.saveEmployee(emp);
		return emp;
	}

	public List<Employee> getEmployeeListByWorkStatus(int workStatus,
			int currentPage, int pageSize) {
		return userDAO.getEmployeeByWorkStatus(workStatus, currentPage,
				pageSize);
	}

	public Employee getEmployeeById(int id) {
		return userDAO.getEmployeeById(id);
	}

	public Employee Employee(int id) {
		return userDAO.getEmployeeById(id);
	}

	public Employee editEmployee(int id) {
		Employee employee = userDAO.getEmployeeById(id);
		employee.getResume().add(new EmployeeResume());
		employee.getResume().add(new EmployeeResume());

		employee.getFamily().add(new EmployeeFamily());
		employee.getFamily().add(new EmployeeFamily());
		return employee;
	}

	public void addPhotoToEmployee_save(Employee employee) {
		userDAO.saveEmployee(employee);
	}

	public Employee changeUserPassword_save(int id, String userPassword) {
		User user = userDAO.getUserById(id);
		Employee employee = userDAO.getEmployeeById(user.getEmployee().getId());
		Encrypt te = new Encrypt();
		user.setPassword(te.StringEncrypt(userPassword, "MD5"));
		userDAO.editUser(user);

		return userDAO.getEmployeeById(employee.getId());
	}

	public int checkUsernameForReg(String username) {
		String hql = "from User u where u.username='" + username+"'";
		int userAmount = userDAO.getUserByHql(hql).size();
		if (userAmount == 0) {
			return 1;
		}
		return 0;
	}

	public int getCountOfEmployeeListByWorkStatus(int workStatus) {
		return userDAO.getConuntOfEmployeeListByWorkStatus(workStatus);
	}

	public List<Role> getRoleByDeptId(int deptId) {
		return systemDAO.getRoleByDeptId(deptId);
	}

	public List<Employee> getEmployeeByDeptId(int deptId) {
		return userDAO.getEmployeeByDeptId(deptId);
	}

	public int getEmpNo(int id) {
		return userDAO.getEmpNo(id);
	}

	public void saveEmployee(Employee employee) {
		userDAO.saveEmployee(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return userDAO.getAllEmployee();
	}

}
