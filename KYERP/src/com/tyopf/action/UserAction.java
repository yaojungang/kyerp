package com.tyopf.action;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IHRService;
import com.tyopf.service.ISystemService;
import com.tyopf.service.IUserService;
import com.tyopf.vo.CompanyDept;
import com.tyopf.vo.Employee;
import com.tyopf.vo.EmployeeFamily;
import com.tyopf.vo.EmployeeResume;
import com.tyopf.vo.Role;
import com.tyopf.vo.User;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport implements SessionAware {
	protected User user;
	protected IUserService userService;
	protected ISystemService systemService;
	protected IHRService hrService;
	private String message;
	private String userPassword;
	private User user0;
	private int id;
	protected Map session ;
	private Employee employee;
	private List<EmployeeResume> resumes;
	private List<EmployeeFamily> familys;
	public void setSession(Map session) {
		  this.session = session ;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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

	public Map getSession() {
		return session;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public ISystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(ISystemService systemService) {
		this.systemService = systemService;
	}

	public IHRService getHrService() {
		return hrService;
	}

	public void setHrService(IHRService hrService) {
		this.hrService = hrService;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public User getUser0() {
		return user0;
	}

	public void setUser0(User user0) {
		this.user0 = user0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String execute() throws Exception {
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String index() throws Exception {
		//Map session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		Employee employee = (Employee) session.get("employee");
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("user", user);
		request.put("Employee", employee);
		request.put("pageTitle", "个人资料");
		return SUCCESS;
	}
	
	public String ChangeMyPassword() throws Exception {
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String MyInfo() throws Exception {
		//Map session = ActionContext.getContext().getSession();
		Employee employee = (Employee) session.get("employee");
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("Employee", employee);
		request.put("pageTitle", employee.getRealname()+" 职工信息表");
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String ChangeMyInfo() throws Exception {
		Employee employee = (Employee) session.get("employee");
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("Employee", employee);
		request.put("pageTitle", "修改我的资料");
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String ChangeMyInfo_save() throws Exception {
		Employee employee0 = (Employee) session.get("employee");
		User user0 = employee0.getUser();
		employee.setId(employee0.getId());
		user.setId(user0.getId());
		
		Employee employeeGet = userService.ChangeMyInfo_save(employee, user, resumes, familys);
		Employee employeeDb = userService.getEmployeeById(employee.getId());
		employeeGet.getFamily().add(new EmployeeFamily());
		employeeGet.getFamily().add(new EmployeeFamily());
		
		employeeGet.getResume().add(new EmployeeResume());
		employeeGet.getResume().add(new EmployeeResume());
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("Employee", employeeGet);
		request.put("pageTitle", "修改 " + employeeGet.getRealname() + " 的资料");
		request.put("message", "修改成功！");
		session.put("employee", employeeDb);
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String ChangeMyPassword_save() throws Exception {
		//Map session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		user0.setUsername(user.getUsername());
		User oldUser = systemService.CheckLogin(user0);
		if (null == oldUser) {
			message = "原密码输入错误!";
			return ERROR;
		}
		else {
			userService.ChangeMyPassword_save(user.getId(), userPassword);
			session.remove("user");
			session.remove("employee");
			session.remove("userRightsList");
			message = "密码修改成功，请重新登陆系统!";
			return SUCCESS;
		}
	}
}
