package org.kyerp.web.controller.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.security.Role;
import org.kyerp.domain.security.User;
import org.kyerp.service.org.IEmployeeService;
import org.kyerp.service.security.IRoleService;
import org.kyerp.service.security.IUserService;
import org.kyerp.utils.StringUtil;
import org.kyerp.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统用户管理
 * 
 * @author y109 2009-12-31上午09:52:00
 */
@Controller
public class UserController extends BaseController{
	@Autowired
	IUserService		userService;
	@Autowired
	IRoleService		roleService;
	@Autowired
	IEmployeeService	employeeService;

	@RequestMapping("/security/User/jsonList.html")
	public String list(String query, Long departId, Integer start, Integer limit, Model model) {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		// build where jpql
		StringBuffer wherejpql = new StringBuffer("");
		List<Object> queryParams = new ArrayList<Object>();
		wherejpql.append(" 1=?").append((queryParams.size() + 1));
		queryParams.add(1);
		// set 部门 id
		if(null != departId) {
			wherejpql.append(" and o.employee.department.id=?").append(queryParams.size() + 1);
			queryParams.add(departId);
		}
		// set query
		if(null != query && !query.equals("") && query.trim().length() > 0) {
			wherejpql.append(" and (o.userName like ?").append(queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");
			// material's serialNumber
			wherejpql.append(" or o.employee.name like ?").append(queryParams.size() + 1).append(")");
			queryParams.add("%" + query.trim() + "%");
		}
		System.out.println("jpql:" + wherejpql);
		QueryResult<User> queryResult = userService.getScrollData(start, limit, wherejpql.toString(), queryParams.toArray(), orderby);

		List<UserExtGridRow> rows = new ArrayList<UserExtGridRow>();
		for (User user : queryResult.getResultlist()) {
			UserExtGridRow ug = new UserExtGridRow();
			ug.setId(user.getId());
			ug.setUserName(user.getUsername());
			/** 设置关联员工 */
			if(null != user.getEmployee()) {
				ug.setEmployeeId(user.getEmployee().getId());
				ug.setEmployeeName(user.getEmployee().getName());
			}
			ug.setRemark(user.getRemark());
			// 设置用户的角色id和name
			if(null != user.getRoles() && user.getRoles().size() > 0) {
				Long[] roleIds = new Long[user.getRoles().size()];
				String[] roleNames = new String[user.getRoles().size()];
				int i = 0;
				for (Role r : user.getRoles()) {
					roleIds[i] = r.getId();
					roleNames[i] = r.getName();
					i++;
				}
				ug.setRoleIds(StringUtil.Array2String(roleIds));
				ug.setRoleNames(StringUtil.Array2String(roleNames));
			}
			rows.add(ug);
		}
		model.addAttribute("start", limit);
		model.addAttribute("limit", limit);
		model.addAttribute("totalProperty", queryResult.getTotalrecord());
		model.addAttribute("rows", rows);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/security/User/jsonSave.html")
	public String save(UserExtGridRow userRow, ModelMap model) {
		User user = new User();
		if(null != userRow.getId() && userRow.getId() > 0) {
			user = userService.find(userRow.getId());
		}
		user.setUserName(userRow.getUserName());
		user.setPassword(userRow.getPassword());
		// 保存关联用户
		if(null != userRow.getEmployeeId()) {
			user.setEmployee(employeeService.find(userRow.getEmployeeId()));
		}
		user.setRemark(userRow.getRemark());
		if(null != userRow.getRoleIds() && userRow.getRoleIds().length() > 0) {
			HashSet<Role> roleSet = new HashSet<Role>();
			for (String roleId : userRow.getRoleIds().split(",")) {
				roleSet.add(roleService.find(new Long(roleId)));
			}
			user.setRoles(roleSet);
		}
		if(null != userRow.getId() && userRow.getId() > 0) {
			userService.update(user);
		} else {
			userService.save(user);
		}
		long id = user.getId() > 0 ? user.getId() : userService.findLast().getId();
		model.addAttribute("success", true);
		model.addAttribute("id", id);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/security/User/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		userService.delete((Serializable[]) ids);
		model.addAttribute("success", true);
		return "jsonView";
	}
}
