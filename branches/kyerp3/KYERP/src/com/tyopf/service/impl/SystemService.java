package com.tyopf.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import com.tyopf.dao.ISystemDAO;
import com.tyopf.dao.IUserDAO;
import com.tyopf.service.ISystemService;
import com.tyopf.vo.CompanyDept;
import com.tyopf.vo.Role;
import com.tyopf.vo.SystemFunctions;
import com.tyopf.vo.SystemModules;
import com.tyopf.vo.User;

public class SystemService implements ISystemService {
	private ISystemDAO systemDAO;
	private IUserDAO userDAO;
	List tempList = new ArrayList();
	
	
	public List getTempList() {
		return tempList;
	}

	public void setTempList(List tempList) {
		this.tempList = tempList;
	}

	public ISystemDAO getSystemDAO() {
		return systemDAO;
	}
	
	public void setSystemDAO(ISystemDAO systemDAO) {
		this.systemDAO = systemDAO;
	}
	
	public IUserDAO getUserDAO() {
		return userDAO;
	}
	
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public User CheckLogin(User user) {
		User u = userDAO.validateUser(user.getUsername(), user.getPassword());
		if (u != null) {
			Timestamp t = new Timestamp(new GregorianCalendar(TimeZone.getTimeZone("GMT")).getTimeInMillis());
			u.setLastLoginTime(t);
			if(null != u.getLoginTimes()){
				u.setLoginTimes(u.getLoginTimes()+1);
			}else{
				u.setLoginTimes(1);
			}
			userDAO.editUser(u);
		}
		return u;
	}
	
	public CompanyDept getDeptById(int id) {
		return systemDAO.getDeptById(id);
	}
	
	public List<CompanyDept> getDeptList(int upDeptId) {
		return systemDAO.getDeptList(upDeptId);
	}
	
	@SuppressWarnings("unchecked")
	public List<CompanyDept> getDeptTree(int upDeptId) {		
		
		List deptList = systemDAO.getDeptList(upDeptId);
		Iterator iterator = deptList.iterator();
		while (iterator.hasNext()) {
			String strA = "";
			CompanyDept dept = (CompanyDept) iterator.next();
			if(dept.getId() == 1){
				tempList.clear();
			}
			if (dept.getUpDeptId() == 0) {
				strA = "";
			}
			else {
				strA = "—" + strA;
			}
			dept.setName(strA +" "+ dept.getName());
			tempList.add(dept);
			getDeptTree(dept.getId());
		}
		return tempList;
	}
	
	public void removeDept(int id) {
		systemDAO.removeDept(id);
	}
	
	public void addDept(CompanyDept dept) {
		dept.setDeptOrder(new Integer(99));
		dept.setDeptStatus(new Integer(1));
		systemDAO.saveDept(dept);
	}
	
	public void editDept(CompanyDept dept) {
		CompanyDept dept0 = systemDAO.getDeptById(dept.getId());
		dept.setEmployeeAmount(dept0.getEmployeeAmount());
		dept.setRoles(dept0.getRoles());
		dept.setEmployees(dept0.getEmployees());
		systemDAO.saveDept(dept);
	}
	
	public List<SystemFunctions> getSystemFunctionsByModuleId(int moduleId) {
		return systemDAO.getSystemFunctionsByModuleId(moduleId);
	}
	
	public void saveSystemFunction(SystemFunctions systemFunction) {
		systemDAO.saveSystemFunction(systemFunction);
	}
	
	@SuppressWarnings("unchecked")
	public Role addRole_save(Role role, List<String> functionsIds) {
		Set<SystemFunctions> functionsSet = new HashSet();
		for (Iterator iterator = functionsIds.iterator(); iterator.hasNext();) {
			String rightstr = (String) iterator.next();
			int functionId = Integer.valueOf(rightstr);
			SystemFunctions f = systemDAO.getSystemFunctionById(functionId);
			f.getRoles().add(role);
			functionsSet.add(f);
		}
		role.setSystemFunctions(functionsSet);
		systemDAO.saveRole(role);
		return role;
	}
	
	@SuppressWarnings("unchecked")
	public Role editRole_save(Role role, List<String> functionsIds) {
		//老用户角色不变
		Role role0 = systemDAO.getRoleById(role.getId());
		role.setUsers(role0.getUsers());
		//加上新的权限列表
		Set<SystemFunctions> functionsSet = new HashSet();
		for (Iterator iterator = functionsIds.iterator(); iterator.hasNext();) {
			String rightstr = (String) iterator.next();
			int functionId = Integer.valueOf(rightstr);
			SystemFunctions f = systemDAO.getSystemFunctionById(functionId);
			f.getRoles().add(role);
			functionsSet.add(f);
		}
		role.setSystemFunctions(functionsSet);
		systemDAO.saveRole(role);
		return role;
	}
	
	public Role getRoleById(int id) {
		return systemDAO.getRoleById(id);
	}
	
	public List<SystemFunctions> getAllSystemFunctions() {
		return systemDAO.getAllSystemFunctions();
	}

	public List<SystemModules> getAllModules() {
		return systemDAO.getAllModules();
	}

	public SystemModules getSystemModuleById(int id) {
		return systemDAO.getSystemModuleById(id);
	}

	public SystemFunctions getLastSystemFunction() {
		return systemDAO.getLastSystemFunction();
	}

	public SystemFunctions getSystemFunctionById(int id) {
		return systemDAO.getSystemFunctionById(id);
	}

	public User getUserById(int UserId) {
		return userDAO.getUserById(UserId);
	}

	@Override
	public User changeUsername_save(User user) {
		User user0 = userDAO.getUserById(user.getId());
		user0.setUsername(user.getUsername());
		userDAO.editUser(user0);
		
		return user0;
	}

	@Override
	public String getSystemVarByName(String varName) {
		return systemDAO.getSystemVarByName(varName);
	}
}
