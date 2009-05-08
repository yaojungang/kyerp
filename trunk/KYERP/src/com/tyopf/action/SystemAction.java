package com.tyopf.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IHRService;
import com.tyopf.service.ISystemService;
import com.tyopf.service.IUserService;
import com.tyopf.util.Pager;
import com.tyopf.vo.CompanyDept;
import com.tyopf.vo.Employee;
import com.tyopf.vo.Role;
import com.tyopf.vo.SystemFunctions;
import com.tyopf.vo.User;

public class SystemAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = -2454934848806539227L;
	protected ISystemService systemService;
	protected IUserService userService;
	protected IHRService hrService;
	public int currentPage = 1;
	public int pageSize = 100;
	public Pager pager;
	private int id = 1;
	private int deptId = 1;
	private int upDeptId = 0;
	private CompanyDept dept;
	private int moudleId = 1;
	private SystemFunctions systemFunction;
	private Role role;
	private List<SystemFunctions> systemFunctions;
	private User user;
	private List<String> sfs;
	private String message;
	private Map session;
	
	
	public IHRService getHrService() {
		return hrService;
	}

	public void setHrService(IHRService hrService) {
		this.hrService = hrService;
	}
	
	public void setSession(Map session) {
		this.session = session;
	}
	
	public IUserService getUserService() {
		return userService;
	}
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	public Map getSession() {
		return session;
	}
	
	public int getId() {
		return id;
	}
	
	public String getMessage() {
		return message;
	}
	
	public int getDeptId() {
		return deptId;
	}
	
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<String> getSfs() {
		return sfs;
	}
	
	public void setSfs(List<String> sfs) {
		this.sfs = sfs;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public ISystemService getSystemService() {
		return systemService;
	}
	
	public Role getRole() {
		return role;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public SystemFunctions getSystemFunction() {
		return systemFunction;
	}
	
	public void setSystemFunction(SystemFunctions systemFunction) {
		this.systemFunction = systemFunction;
	}
	
	public List<SystemFunctions> getSystemFunctions() {
		return systemFunctions;
	}
	
	public void setSystemFunctions(List<SystemFunctions> systemFunctions) {
		this.systemFunctions = systemFunctions;
	}
	
	public int getMoudleId() {
		return moudleId;
	}
	
	public void setMoudleId(int moudleId) {
		this.moudleId = moudleId;
	}
	
	public int getUpDeptId() {
		return upDeptId;
	}
	
	public void setUpDeptId(int upDeptId) {
		this.upDeptId = upDeptId;
	}
	
	public CompanyDept getDept() {
		return dept;
	}
	
	public void setDept(CompanyDept dept) {
		this.dept = dept;
	}
	
	public void setSystemService(ISystemService systemService) {
		this.systemService = systemService;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public Pager getPager() {
		return pager;
	}
	
	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public String CheckLogin() {
		System.out.println(new Date() + " " + user.getUsername() + " Login!");
		Map request = (Map) ActionContext.getContext().get("request");
		Map session = ActionContext.getContext().getSession();
		User u = systemService.CheckLogin(user);
		if (null != u) {
			u.setLastLoginIp(org.apache.struts2.ServletActionContext.getRequest().getRemoteAddr());
			Set<Role> userRoles = u.getRoles();
			List<String> userSystemFunctionList = new ArrayList<String>();
			for (Role role : userRoles) {
				for (SystemFunctions systemFunction : role.getSystemFunctions()) {
					userSystemFunctionList.add(systemFunction.getName());
				}
			}
			Employee e = userService.getEmployeeById(u.getEmployee().getId());
			session.put("user", u);
			session.put("employee", e);
			session.put("userSystemFunctionList", userSystemFunctionList);
			message = u.getEmployee().getRealname() + "您好！ 您已经成功登陆！";
			request.put("message", message);
			HttpServletResponse response = ServletActionContext.getResponse();
			Cookie cookie;
			cookie = new Cookie("userId", Integer.toString(u.getId()));
			int cookieTime = 60 * 60 * 24 * 365;
			cookie.setMaxAge(cookieTime);
			//cooki.setPath("/"); // 根据个人的不用，在不同功能的路径下创建
			response.addCookie(cookie);
			return SUCCESS;
		}
		message = "登录失败，请核对您的用户名和密码是否输入正确!";
		request.put("message", message);
		return ERROR;
	}
	
	@SuppressWarnings("unchecked")
	public String Logout() throws Exception {
		Map session = ActionContext.getContext().getSession();
		Map request0 = (Map) ActionContext.getContext().get("request");
		User u = (User) session.get("user");
		System.out.println(new Date()+" "+u.getUsername()+" Logout!");
		session.remove("user");
		session.remove("employee");
		session.remove("userSystemFunctionList");
		HttpServletRequest request =  ServletActionContext.getRequest ();
		HttpServletResponse response = ServletActionContext.getResponse();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				//System.out.println("Delete cookie for " + cookie.getName()+":"+cookie.getValue());
				if ("userId".equals(cookie.getName())) {
					System.out.println("Delete cookie for " + cookie.getName()+":"+cookie.getValue());
					cookie.setValue("");
					int noTime = 0;
					cookie.setMaxAge(noTime);
					response.addCookie(cookie);
					return SUCCESS;
				}
			}
		}
		
		message = "您已成功注销！";
		request0.put("message", message);
		return SUCCESS;
	}
	
	public String SystemAdmin() {
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String DeptAdmin() {
		List deptTree = (List) session.get("DeptTree");
		if (null == deptTree) {
			deptTree = systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("DeptTree", deptTree);
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String updateEmpNo() throws Exception {
		// List<Employee> allEmployee = hrService.getEmployeeListByWorkStatus(0,
		// 1, 10000);
		List<Employee> allEmployee = hrService.getAllEmployee();
		for (Employee e : allEmployee) {
			e.setEmpNo(hrService.getEmpNo(e.getId()));
			hrService.saveEmployee(e);
		}
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("message", "员工编号更新成功！");
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String AddDept() {
		systemService.addDept(dept);
		List deptTree = (List) session.get("DeptTree");
		if (null == deptTree) {
			deptTree = systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("DeptTree", deptTree);
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String EditDept() {
		List deptTree = (List) session.get("DeptTree");
		if (null == deptTree) {
			deptTree = systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		systemService.editDept(dept);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("DeptTree", deptTree);
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String RoleAdmin() {
		List deptTree = (List) session.get("DeptTree");
		if (null == deptTree) {
			deptTree = systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("DeptTree", deptTree);
		// rDeptTreeut("DeptTree", deptTree);
		request.put("pageTitle", "角色管理");
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String SystemFunctionsAdmin() {
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("SystemMoudlesList", systemService.getAllModules());
		request.put("systemFunctionList", systemService.getSystemFunctionsByModuleId(moudleId));
		request.put("pageTitle", systemService.getSystemModuleById(moudleId).getChineseName() + "模块    系统功能设置");
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String AddSystemFunction_save() {
		systemService.saveSystemFunction(systemFunction);
		SystemFunctions sf = systemService.getLastSystemFunction();
		int moudleId = sf.getSystemModule().getId();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("SystemMoudlesList", systemService.getAllModules());
		request.put("systemFunctionList", systemService.getSystemFunctionsByModuleId(moudleId));
		request.put("moudleId", moudleId);
		request.put("pageTitle", systemService.getSystemModuleById(moudleId).getChineseName() + "模块    系统功能设置");
		request.put("message", "系统功能添加成功！");
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String EditSystemFunction_save() {
		systemService.saveSystemFunction(systemFunction);
		SystemFunctions sf = systemService.getSystemFunctionById(systemFunction.getId());
		int moudleId = sf.getSystemModule().getId();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("SystemMoudlesList", systemService.getAllModules());
		request.put("systemFunctionList", systemService.getSystemFunctionsByModuleId(moudleId));
		request.put("moudleId", moudleId);
		request.put("pageTitle", systemService.getSystemModuleById(moudleId).getChineseName() + "模块    系统功能设置");
		request.put("message", "系统功能修改成功！");
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String AddRole() {
		List deptTree = (List) session.get("DeptTree");
		if (null == deptTree) {
			deptTree = systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("SystemMoudlesList", systemService.getAllModules());
		request.put("DeptTree", deptTree);
		request.put("pageTitle", "添加角色");
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String AddRole_save() {
		systemService.addRole_save(role, sfs);
		upDeptId = systemService.getDeptById(role.getCompanyDept().getId()).getUpDeptId();
		List deptTree = (List) session.get("DeptTree");
		if (null == deptTree) {
			deptTree = systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("message", "角色添加成功！");
		request.put("upDeptId", upDeptId);
		request.put("DeptTree", deptTree);
		request.put("pageTitle", "角色管理");
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String EditRole() {
		List<String> sfList = new ArrayList<String>();
		Role role = systemService.getRoleById(id);
		for (Iterator iterator = role.getSystemFunctions().iterator(); iterator.hasNext();) {
			SystemFunctions sf = (SystemFunctions) iterator.next();
			String sfStr = sf.getId().toString();
			sfList.add(sfStr);
		}
		Map request = (Map) ActionContext.getContext().get("request");
		List deptTree = (List) session.get("DeptTree");
		if (null == deptTree) {
			deptTree = systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		request.put("Role", role);
		request.put("sfList", sfList);
		request.put("SystemMoudlesList", systemService.getAllModules());
		request.put("DeptTree", deptTree);
		request.put("pageTitle", "修改角色  " + systemService.getDeptById(role.getCompanyDept().getId()).getName());
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String EditRole_save() {
		systemService.editRole_save(role, sfs);
		upDeptId = systemService.getDeptById(role.getCompanyDept().getId()).getUpDeptId();
		List deptTree = (List) session.get("DeptTree");
		if (null == deptTree) {
			deptTree = systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("message", "角色修改成功！");
		request.put("DeptTree", deptTree);
		request.put("pageTitle", systemService.getDeptById(deptId).getName() + " 角色列表");
		return SUCCESS;
	}
}
