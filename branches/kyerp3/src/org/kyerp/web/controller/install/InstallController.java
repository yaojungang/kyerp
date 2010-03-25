package org.kyerp.web.controller.install;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.kyerp.domain.org.Department;
import org.kyerp.domain.org.Employee;
import org.kyerp.domain.security.Role;
import org.kyerp.domain.security.SystemModule;
import org.kyerp.domain.security.SystemResource;
import org.kyerp.domain.security.SystemResourceType;
import org.kyerp.domain.security.User;
import org.kyerp.domain.warehouse.MaterialCategory;
import org.kyerp.service.org.IDepartmentService;
import org.kyerp.service.org.IEmployeeService;
import org.kyerp.service.security.IRoleService;
import org.kyerp.service.security.ISystemModuleService;
import org.kyerp.service.security.ISystemResourceService;
import org.kyerp.service.security.IUserService;
import org.kyerp.service.warehouse.IMaterialCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 初始化数据库
 * 
 * @author y109 2010-1-30下午04:43:43
 */
@Controller
public class InstallController{
	@Autowired
	ISystemModuleService		systemModuleService;
	@Autowired
	IDepartmentService			departmentService;
	@Autowired
	IMaterialCategoryService	materialCategoryService;
	@Autowired
	ISystemResourceService		systemResourceService;
	@Autowired
	IRoleService				roleService;
	@Autowired
	IUserService				userService;
	@Autowired
	IEmployeeService			employeeService;

	@RequestMapping("/install/install_initSystem.html")
	public String install(String orgName, Model model) {
		StringBuffer messageBuffer = new StringBuffer();
		messageBuffer.append(initSystemModule()).append("</ br>");
		messageBuffer.append(initAdminUser("y109", "y109")).append("</ br>");
		messageBuffer.append(initORG("组织机构")).append("</ br>");
		messageBuffer.append(initWarehouse()).append("</ br>");

		model.addAttribute("jsonText", messageBuffer.toString());
		return "share/jsonTextView";
	};

	public String initSystemModule() {
		SystemModule sm1 = new SystemModule("ClientRelatiManagement", "客户关系管理", "CRM");
		SystemModule sm2 = new SystemModule("OperationsManagement", "业务销售管理", "OM");
		SystemModule sm3 = new SystemModule("ProductiveManagement", "生产制造管理", "PM");
		SystemModule sm4 = new SystemModule("WarehouseManagement", "库存物料管理", "WM");
		SystemModule sm5 = new SystemModule("FinancialManagement", "财务数据管理", "FA");
		SystemModule sm6 = new SystemModule("HumanResoursManage", "人力资源管理", "HR");
		SystemModule sm7 = new SystemModule("EquipmentManagement", "资产设备管理", "EM");
		SystemModule sm8 = new SystemModule("OfficeManagement", "办公自动化", "EM");
		SystemModule sm9 = new SystemModule("SystemManagement", "系统管理", "SYS");
		systemModuleService.save(sm1);
		systemModuleService.save(sm2);
		systemModuleService.save(sm3);
		systemModuleService.save(sm4);
		systemModuleService.save(sm5);
		systemModuleService.save(sm6);
		systemModuleService.save(sm7);
		systemModuleService.save(sm8);
		systemModuleService.save(sm9);
		return "初始化系统模块成功！";
	}

	public String initORG(String orgName) {
		Department d = new Department();
		d.setName(orgName);
		departmentService.save(d);
		return "初始化组织名称成功！";
	}

	public String initWarehouse() {
		MaterialCategory mc = new MaterialCategory();
		mc.setName("物料分类");
		materialCategoryService.save(mc);
		return "库存管理模块初始化成功！";
	}

	public String initAdminUser(String userName, String password) {
		SystemResource sr = new SystemResource();
		sr.setName("ROLE_ADMIN");
		sr.setContent("ROLE_ADMIN");
		sr.setType(SystemResourceType.ROLE);
		sr.setSystemModule(systemModuleService.find(new Long(9)));
		systemResourceService.save(sr);

		Role r = new Role();
		r.setName("管理员");
		List<SystemResource> systemResources = new ArrayList<SystemResource>();
		systemResources.add(sr);
		r.setSystemResources(systemResources);
		r.setDepartment(departmentService.find(new Long(1)));
		roleService.save(r);

		User u = new User();
		u.setUserName(userName);
		u.setPassword(password);
		userService.save(u);

		Employee e = new Employee();
		e.setName("管理员");
		employeeService.save(e);

		// 设置用户与角色之间的关联
		Set<Role> roles = new HashSet<Role>();
		roles.add(r);
		u.setRoles(roles);
		userService.save(u);

		List<User> users = new ArrayList<User>();
		users.add(u);
		r.setUsers(users);
		roleService.save(r);

		// 设置用户与职员之间的关联
		e.setUser(u);
		u.setEmployee(e);
		employeeService.save(e);
		userService.save(u);

		return "管理员初始化成功！";
	}
}
