package com.jzland.ChengDe.dao;

import java.util.List;

import com.jzland.ChengDe.vo.CompanyDept;
import com.jzland.ChengDe.vo.Role;
import com.jzland.ChengDe.vo.SystemFunctions;
import com.jzland.ChengDe.vo.SystemModules;

public interface ISystemDAO {
	public List<CompanyDept> getDeptList(int upDeptId);	
	public CompanyDept getDeptById(int id);	
	public void saveDept(CompanyDept dept);
	public void removeDept(int id);
	
	public List<SystemModules> getAllModules();
	public SystemModules getSystemModuleById(int id);
	public List<SystemFunctions> getSystemFunctionsByModuleId(int moduleId);
	public List<SystemFunctions> getAllSystemFunctions();
	public SystemFunctions getSystemFunctionById(int id);
	public SystemFunctions getLastSystemFunction();
	public void saveSystemFunction(SystemFunctions systemFunction);
	public void removeSystemFunction(int id);
	
	public Role getRoleById(int id);
	public void saveRole(Role role);
	public List<Role> getRoleByDeptId(int deptId);
}
