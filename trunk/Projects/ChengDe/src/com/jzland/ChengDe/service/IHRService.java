package com.jzland.ChengDe.service;

import java.util.List;

import com.jzland.ChengDe.vo.Employee;
import com.jzland.ChengDe.vo.EmployeeFamily;
import com.jzland.ChengDe.vo.EmployeeResume;
import com.jzland.ChengDe.vo.Role;
import com.jzland.ChengDe.vo.User;

public interface IHRService {
	public List<Employee> getEmployeeListByWorkStatus(int workStatus,int currentPage,int pageSize);
	public int getCountOfEmployeeListByWorkStatus(int workStatus);
	public Employee getEmployeeById(int id);
	public Employee Employee(int id);
	public void addPhotoToEmployee_save(Employee employee);
	public Employee addEmployee_save(Employee employee,User user,int roles0Id);
	public Employee editEmployee(int id);
	public Employee editEmployee_save(Employee employee,User user,List<EmployeeResume> resumes,List<EmployeeFamily> familys,int roles0Id);
	public void saveEmployee(Employee employee);
	public Employee changeUserPassword_save(int id,String userPassword);
	public int checkUsernameForReg(String username);
	public List<Role> getRoleByDeptId(int deptId);
	public List<Employee> getAllEmployee();
	public List<Employee> getEmployeeByDeptId(int deptId);
	public int getEmpNo(int id);
}
