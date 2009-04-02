package com.tyopf.service;

import java.util.List;

import com.tyopf.vo.CompanyDept;
import com.tyopf.vo.Role;
import com.tyopf.vo.SystemFunctions;
import com.tyopf.vo.SystemModules;
import com.tyopf.vo.User;

public interface ISystemService {
	public User CheckLogin(User user);
	public List<CompanyDept> getDeptList(int upDeptId);
	public List<CompanyDept> getDeptTree(int upDeptId);
	public CompanyDept getDeptById(int id);	
	public void removeDept(int id);
	
	public void addDept(CompanyDept dept);
	public void editDept(CompanyDept dept);
	public List<SystemModules> getAllModules();
	public SystemFunctions getSystemFunctionById(int id);
	public SystemModules getSystemModuleById(int id);
	public SystemFunctions getLastSystemFunction();
	public List<SystemFunctions> getSystemFunctionsByModuleId(int moduleId);
	public List<SystemFunctions> getAllSystemFunctions();
	public void saveSystemFunction(SystemFunctions systemFunction);
	
	public Role getRoleById(int id);
	public Role addRole_save(Role role,List<String> functionsIds);
	public Role editRole_save(Role role,List<String> functionsIds);
	public User getUserById(int UserId);
	public User changeUsername_save(User user);
}
