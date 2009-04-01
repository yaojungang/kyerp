package com.tyopf.action;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IHRService;
import com.tyopf.service.ISystemService;
import com.tyopf.util.Pager;
import com.tyopf.vo.CompanyDept;
import com.tyopf.vo.Employee;
import com.tyopf.vo.EmployeeFamily;
import com.tyopf.vo.EmployeeResume;
import com.tyopf.vo.Role;
import com.tyopf.vo.User;

public class HRAction extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = -2454934848806539227L;
	protected IHRService hrService;
	protected ISystemService systemService;
	public int currentPage = 1;
	public int pageSize = 100;
	public Pager pager;
	private int workStatus=0;
	private int id;
	private Employee employee;
	private User user;
	private List<EmployeeResume> resumes;
	private List<EmployeeFamily> familys;
	private String userPassword;
	private int usernameRegable=0;
	private String username;
	private List<Role> roleList;
	private List<Role> roles;
	@SuppressWarnings("unchecked")
	private Map session ;
	private Map request;
	private int deptId=1;
	public void setSession(Map session) {
		  this.session = session ;
	}
	public IHRService getHrService() {
		return hrService;
	}

	public Map getRequest() {
		return request;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public void setRequest(Map request) {
		this.request = request;
	}
	public Map getSession() {
		return session;
	}
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public int getUsernameRegable() {
		return usernameRegable;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUsernameRegable(int usernameRegable) {
		this.usernameRegable = usernameRegable;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setHrService(IHRService hrService) {
		this.hrService = hrService;
	}
	public ISystemService getSystemService() {
		return systemService;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(int workStatus) {
		this.workStatus = workStatus;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<EmployeeResume> getResumes() {
		return resumes;
	}
	public void setResumes(List<EmployeeResume> resumes) {
		this.resumes = resumes;
	}
	public List<EmployeeFamily> getFamilys() {
		return familys;
	}
	public void setFamilys(List<EmployeeFamily> familys) {
		this.familys = familys;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	@SuppressWarnings("unchecked")
	public String HRAdmin() {
		List employeeList = hrService.getEmployeeListByWorkStatus(workStatus, currentPage, 30);
		Pager pager = new Pager(currentPage, hrService.getCountOfEmployeeListByWorkStatus(workStatus));
		pager.setPageSize(30);

		List deptTree = (List) session.get("DeptTree");
		if(null == deptTree){
			deptTree =systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		String peopStatus="";
		if(workStatus ==0) peopStatus="在编人员";
		if(workStatus ==1) peopStatus="试用期人员";
		if(workStatus ==2) peopStatus="申请离职人员";
		if(workStatus ==100) peopStatus="退休人员";
		if(workStatus ==101) peopStatus="离职人员";
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("EmployeeList", employeeList);
		request.put("Pager", pager);
		request.put("DeptTree", deptTree);
		request.put("pageTitle", "人事管理 - "+peopStatus);
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String getEmployeeByDeptId() {
		List employeeList = hrService.getEmployeeByDeptId(deptId);
		Pager pager = new Pager(currentPage, employeeList.size());
		pager.setPageSize(employeeList.size());
		
		List deptTree = (List) session.get("DeptTree");
		if(null == deptTree){
			deptTree =systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("EmployeeList", employeeList);
		request.put("Pager", pager);
		request.put("DeptTree", deptTree);
		request.put("pageTitle", "人事管理 - "+systemService.getDeptById(deptId).getName()+"人员列表");
		return SUCCESS;
	}
	public String Employee() {
		Employee employee = hrService.Employee(id); 
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("Employee", employee);
		request.put("pageTitle", employee.getRealname() + " 的职工信息表");
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String addEmployee() {
		List deptTree = (List) session.get("DeptTree");
		if(null == deptTree){
			deptTree =systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("DeptTree", deptTree);
		request.put("pageTitle", "增加人员");
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String addEmployee_save() {
		hrService.addEmployee_save(employee, user,roles.get(0).getId());
		Employee employeeGet = hrService.getEmployeeById(employee.getId());
		employeeGet.getFamily().add(new EmployeeFamily());
		employeeGet.getFamily().add(new EmployeeFamily());
		employeeGet.getFamily().add(new EmployeeFamily());
		employeeGet.getFamily().add(new EmployeeFamily());
		employeeGet.getResume().add(new EmployeeResume());
		employeeGet.getResume().add(new EmployeeResume());
		employeeGet.getResume().add(new EmployeeResume());
		employeeGet.getResume().add(new EmployeeResume());
		employeeGet.getResume().add(new EmployeeResume());
		CompanyDept dept = systemService.getDeptById(employeeGet.getCompanyDept().getId());
		List roleGet = dept.getRoles();
		int role0Id =0 ;
		Set<Role> roleSet = employeeGet.getUser().getRoles();
		for(Role r : roleSet){
			role0Id =r.getId() ;
		}
		List deptTree = (List) session.get("DeptTree");
		if(null == deptTree){
			deptTree =systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("Employee", employeeGet);
		request.put("role0Id", role0Id);
		request.put("DeptTree", deptTree);
		request.put("roleList", roleGet);
		request.put("pageTitle", "修改 " + employeeGet.getRealname() + " 的资料");
		request.put("message", "添加成功，请继续输入！");
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String editEmployee() {
		Employee employee = hrService.editEmployee(id);
		CompanyDept dept = employee.getCompanyDept();
		List roleGet = dept.getRoles();
		int role0Id =0 ;
		Set<Role> roleSet = employee.getUser().getRoles();
		for(Role r : roleSet){
			role0Id =r.getId() ;
		}
		List deptTree = (List) session.get("DeptTree");
		if(null == deptTree){
			deptTree =systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("Employee", employee);
		request.put("role0Id", role0Id);
		request.put("DeptTree", deptTree);
		request.put("roleList", roleGet);
		request.put("pageTitle", employee.getRealname() + " 的资料");
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String editEmployee_save() {
		Employee employeeGet = hrService.editEmployee_save(employee, user, resumes, familys,roles.get(0).getId());
		employeeGet.getFamily().add(new EmployeeFamily());
		employeeGet.getFamily().add(new EmployeeFamily());
		employeeGet.getResume().add(new EmployeeResume());
		employeeGet.getResume().add(new EmployeeResume());
		CompanyDept dept = systemService.getDeptById(employeeGet.getCompanyDept().getId());
		List roleGet = dept.getRoles();
		int role0Id =0 ;
		Set<Role> roleSet = employeeGet.getUser().getRoles();
		for(Role r : roleSet){
			role0Id =r.getId() ;
		}
		List deptTree = (List) session.get("DeptTree");
		if(null == deptTree){
			deptTree =systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("DeptTree", deptTree);
		request.put("roleList", roleGet);
		request.put("Employee", employeeGet);
		request.put("role0Id", role0Id);
		request.put("pageTitle", "修改 " + employeeGet.getRealname() + " 的资料");
		request.put("message", "修改成功！");
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String changeUserPassword_save() {
		Employee employeeGet = hrService.changeUserPassword_save(id, userPassword);
		List deptTree = (List) session.get("DeptTree");
		if(null == deptTree){
			deptTree =systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("DeptTree", deptTree);
		request.put("Employee", employeeGet);
		return SUCCESS;
	}
	public String checkUsernameForReg() {
		usernameRegable=hrService.checkUsernameForReg(username);
		return SUCCESS;
	}
	public String getRoleListByDeptId() {
		CompanyDept dept = systemService.getDeptById(id);
		roleList = dept.getRoles();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("roleList", roleList);
		return SUCCESS;
	}

}
