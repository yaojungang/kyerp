package com.jzland.ChengDe.service;

import java.util.List;

import com.jzland.ChengDe.vo.CompanyDept;
import com.jzland.ChengDe.vo.Role;
import com.jzland.ChengDe.vo.SystemFunctions;
import com.jzland.ChengDe.vo.SystemModules;
import com.jzland.ChengDe.vo.User;

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
