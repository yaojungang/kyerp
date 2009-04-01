package com.tyopf.dao;

import java.util.List;

import com.tyopf.vo.Employee;
import com.tyopf.vo.Role;
import com.tyopf.vo.User;

public interface IUserDAO {
	
	public void addUser(User user);
	public void removeUser(int UserId);
	public void editUser(User user);
	public List getAllUsers();
	public int getTotalUsers();
	public List getUsers(int currentPage,int pageSize);
	public List getUserByHql(String hql);
	public User validateUser(String username, String password);
	public User getUserById(int UserId);
	
	public List<Role> getAllRoles();
	public List<User> getUserByRole(int RoleId);
	
	public void addRole(Role Role);
	public void removeRole(int RoleId);
	public void editRole(Role Role);
	public Role getRoleById(int RoleId);
	
	public void addEmployee(Employee employee);
	public void saveEmployee(Employee employee);
	public void delEmployee(int employeeId);
	public void removeEmployeeFamily(int familyId);
	public void removeEmployeeResume(int resumeId);
	public Employee getEmployeeById(int employeeId);
	public List<Employee> getAllEmployee(int currentPage, int pageSize);
	public List<Employee> getEmployeeByDeptId(int deptId);
	public List<Employee> getEmployeeByWorkStatus(int workStatus,int currentPage, int pageSize);
	public int getConuntOfEmployeeListByWorkStatus(int workStatus);
	public int getCountofAllEmployee();
	public List<Employee> getEmployeeByDept(String deptName);
	public Employee getLastEmployee();
	public int getEmpNo(int id);	
}
