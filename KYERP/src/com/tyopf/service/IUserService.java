package com.tyopf.service;

import java.util.List;

import com.tyopf.vo.Employee;
import com.tyopf.vo.EmployeeFamily;
import com.tyopf.vo.EmployeeResume;
import com.tyopf.vo.Role;
import com.tyopf.vo.User;

public interface IUserService {
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
	
	public void addRole(Role userRole);
	public void removeRole(int userRoleId);
	public void editRole(Role userRole);
	public Role getRoleById(int RoleId);
	
	public void ChangeMyPassword_save(int id,String userPassword);
	public List<Employee> getEmployeeByDeptId(int deptId);
	public Employee getEmployeeById(int id);
	public Employee ChangeMyInfo_save(Employee employee, User user, List<EmployeeResume> resumes,List<EmployeeFamily> familys);
}
