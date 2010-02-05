package org.kyerp.web.controller.org;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.kyerp.domain.base.views.ExtGridList;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.org.Employee;
import org.kyerp.service.org.IDepartmentService;
import org.kyerp.service.org.IEmployeeService;
import org.kyerp.service.security.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 员工管理模块定义
 * 
 * @author y109 2009-12-31上午09:52:00
 */
@Controller
public class EmployeeController {
	@Autowired
	IEmployeeService	employeeService;
	@Autowired
	IUserService		userService;
	@Autowired
	IDepartmentService	departmentService;

	@RequestMapping("/org/Employee/jsonList.html")
	public String list(Model model) {

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<Employee> queryResult = employeeService
				.getScrollData(orderby);
		List<EmployeeGridRow> rows = new ArrayList<EmployeeGridRow>();
		for (Employee e : queryResult.getResultlist()) {
			EmployeeGridRow n = new EmployeeGridRow();
			n.setId(e.getId());
			n.setName(e.getName());
			if (null != e.getUser()) {
				n.setUserId(e.getUser().getId());
				n.setUserName(e.getUser().getUserName());
			}
			if (null != e.getDepartment()) {
				n.setDepartmentId(e.getDepartment().getId());
				n.setDepartmentName(e.getDepartment().getName());
			}
			rows.add(n);
		}
		ExtGridList<EmployeeGridRow> employeeGrid = new ExtGridList<EmployeeGridRow>();
		employeeGrid.setRows(rows);
		JSONObject jsonObject = JSONObject.fromObject(employeeGrid);

		String text = "";

		try {
			text = jsonObject.toString();
			System.out.println(text);
		} catch (Exception e) {
			text = "";
		}
		model.addAttribute("jsonText", text);
		return "share/jsonTextView";
	}

	@RequestMapping("/org/Employee/jsonSave.html")
	public String save(EmployeeGridRow r, ModelMap model) {
		Employee employee = new Employee();
		if (null != r.getId() && r.getId() > 0) {
			employee = employeeService.find(r.getId());
		}
		employee.setName(r.getName());
		if (null != r.getUserId() && r.getUserId() > 0) {
			employee.setUser(userService.find(r.getUserId()));
		}
		if (null != r.getDepartmentId() && r.getDepartmentId() > 0) {
			employee.setDepartment(departmentService.find(r.getDepartmentId()));
		}
		if (null != r.getId() && r.getId() > 0) {
			employeeService.update(employee);
		} else {
			employeeService.save(employee);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		long id = null != r.getId() && r.getId() > 0 ? r.getId()
				: employeeService.findLast().getId();
		jsonObject.put("id", id);
		String text = "";
		try {
			text = jsonObject.toString();
			System.out.println(text);
		} catch (Exception e) {
			text = "";
		}
		model.addAttribute("jsonText", text);
		return "share/jsonTextView";
	}

	@RequestMapping("/org/Employee/jsonTreeDelete.html")
	public String delete(ModelMap model, Long[] id) {
		employeeService.delete((Serializable[]) id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		String text = "";
		try {
			text = jsonObject.toString();
			System.out.println(text);
		} catch (Exception e) {
			text = "";
		}
		model.addAttribute("jsonText", text);
		return "share/jsonTextView";
	}
}
