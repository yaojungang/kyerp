package org.kyerp.web.controller.org;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.kyerp.domain.common.view.ExtTreeNode;
import org.kyerp.domain.common.view.ExtTreeRecursion;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.org.Department;
import org.kyerp.domain.org.Employee;
import org.kyerp.service.org.IDepartmentService;
import org.kyerp.service.org.IEmployeeService;
import org.kyerp.service.security.IUserService;
import org.kyerp.utils.WebUtil;
import org.kyerp.web.controller.BaseController;
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
public class EmployeeController extends BaseController{
	@Autowired
	IEmployeeService	employeeService;
	@Autowired
	IUserService		userService;
	@Autowired
	IDepartmentService	departmentService;

	@RequestMapping("/org/Employee/jsonList.html")
	public String list(String query, Long departId, Integer start, Integer limit, Model model) {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;
		logger.info("currentEmployee:" + WebUtil.getCurrentEmployee().getName());

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		// build where jpql
		StringBuffer wherejpql = new StringBuffer("");
		List<Object> queryParams = new ArrayList<Object>();
		wherejpql.append(" 1=?").append((queryParams.size() + 1));
		queryParams.add(1);
		// set 部门 id
		if(null != departId) {
			wherejpql.append(" and o.department.id=?").append(queryParams.size() + 1);
			queryParams.add(departId);
		}
		// set query
		if(null != query && !query.equals("") && query.trim().length() > 0) {
			wherejpql.append(" and (o.name like ?").append(queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");
			// material's serialNumber
			wherejpql.append(" or o.empNo like ?").append(queryParams.size() + 1).append(")");
			queryParams.add("%" + query.trim() + "%");
		}
		logger.info("jpql:" + wherejpql.toString());
		QueryResult<Employee> queryResult = employeeService.getScrollData(start, limit, wherejpql.toString(), queryParams.toArray(), orderby);
		List<EmployeeGridRow> rows = new ArrayList<EmployeeGridRow>();
		for (Employee e : queryResult.getResultlist()) {
			EmployeeGridRow n = new EmployeeGridRow();
			n.setId(e.getId());
			n.setName(e.getName());
			if(null != e.getUser()) {
				n.setUserId(e.getUser().getId());
				n.setUserName(e.getUser().getUserName());
			}
			if(null != e.getDepartment()) {
				n.setDepartmentId(e.getDepartment().getId());
				n.setDepartmentName(e.getDepartment().getName());
			}
			rows.add(n);
		}
		model.addAttribute("start", limit);
		model.addAttribute("limit", limit);
		model.addAttribute("totalProperty", queryResult.getTotalrecord());
		model.addAttribute("rows", rows);
		return "jsonView";
	}

	@RequestMapping("/org/Employee/jsonSave.html")
	public String save(EmployeeGridRow r, ModelMap model) {
		Employee employee = new Employee();
		if(null != r.getId() && r.getId() > 0) {
			employee = employeeService.find(r.getId());
		}
		employee.setName(r.getName());
		if(null != r.getUserId() && r.getUserId() > 0) {
			employee.setUser(userService.find(r.getUserId()));
		}
		if(null != r.getDepartmentId() && r.getDepartmentId() > 0) {
			employee.setDepartment(departmentService.find(r.getDepartmentId()));
		}
		if(null != r.getId() && r.getId() > 0) {
			employeeService.update(employee);
		} else {
			employeeService.save(employee);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		long id = null != r.getId() && r.getId() > 0 ? r.getId() : employeeService.findLast().getId();
		model.addAttribute("success", true);
		model.addAttribute("id", id);
		return "jsonView";
	}

	@RequestMapping("/org/Employee/jsonTreeDelete.html")
	public String delete(ModelMap model, Long[] id) {
		employeeService.delete((Serializable[]) id);
		model.addAttribute("success", true);
		return "jsonView";
	}

	/**
	 * 返回职工树
	 * *
	 */
	@RequestMapping("/org/Employee/jsonTree.html")
	public String tree(Model model) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<Department> typeQueryResult = departmentService.getScrollData(orderby);

		List<ExtTreeNode> extTreeList = new ArrayList<ExtTreeNode>();
		if(typeQueryResult.getResultlist().size() == 0) {
			Department department = new Department();
			department.setName("本单位");
			departmentService.save(department);
			model.addAttribute("jsonText", "[{id:'1',text:'本单位',leaf:true}]");
		} else {
			for (Department department : typeQueryResult.getResultlist()) {
				ExtTreeNode node = new ExtTreeNode();
				node.setId(String.valueOf("type" + department.getId()));
				node.setText(department.getName());
				if(null != department.getParentDepartment() && department.getParentDepartment().getId() > 0) {
					node.setParentId(String.valueOf("type" + department.getParentDepartment().getId()));
				}
				if(department.getId() == 1) {
					node.setExpanded(true);
				} else {
					node.setExpanded(false);
				}
				extTreeList.add(node);
			}

			QueryResult<Employee> objQueryResult = employeeService.getScrollData(orderby);
			for (Employee employee : objQueryResult.getResultlist()) {
				ExtTreeNode node = new ExtTreeNode();
				node.setId(String.valueOf(employee.getId()));
				node.setText(employee.getName());
				node.setParentId(String.valueOf("type" + employee.getDepartment().getId()));
				node.setIcon("images/ext-extend/icons/user_suit.gif");
				extTreeList.add(node);
			}

			ExtTreeRecursion r = new ExtTreeRecursion();
			if(null != extTreeList && extTreeList.size() > 0) {
				r.recursionFn(extTreeList, extTreeList.get(0));
			}
			String strTreeString = r.modifyStr(r.getReturnStr().toString());

			model.addAttribute("jsonText", strTreeString);
		}
		return "share/jsonTextView";
	}
}
