package org.kyerp.web.controller.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.kyerp.domain.base.views.ExtGridList;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.security.Role;
import org.kyerp.domain.security.SystemResource;
import org.kyerp.domain.security.User;
import org.kyerp.service.org.IDepartmentService;
import org.kyerp.service.security.IRoleService;
import org.kyerp.service.security.ISystemResourceService;
import org.kyerp.service.security.IUserService;
import org.kyerp.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 岗位角色定义
 * 
 * @author y109 2009-12-31上午09:52:00
 */
@Controller
public class RoleController {
	@Autowired
	IRoleService			roleService;
	@Autowired
	IUserService			userService;
	@Autowired
	IDepartmentService		departmentService;
	@Autowired
	ISystemResourceService	systemResourceService;

	// @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/security/Role/jsonList.html")
	public String list(Model model, Integer start, Integer limit) {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<Role> queryResult = roleService.getScrollData(start, limit,
				orderby);
		List<RoleExtGridRow> rows = new ArrayList<RoleExtGridRow>();
		for (Role role : queryResult.getResultlist()) {
			RoleExtGridRow rr = new RoleExtGridRow();
			rr.setId(role.getId());
			rr.setName(role.getName());
			rr.setDepartmentId(role.getDepartment().getId());
			rr.setDepartmentName(role.getDepartment().getName());
			if (null != role.getUsers() && role.getUsers().size() > 0) {
				Long[] userIds = new Long[role.getUsers().size()];
				String[] userNames = new String[role.getUsers().size()];
				for (int i = 0; i < role.getUsers().size(); i++) {
					userIds[i] = role.getUsers().get(i).getId();
					userNames[i] = role.getUsers().get(i).getUsername();
				}
				rr.setUserIds(StringUtil.Array2String(userIds));
				rr.setUserNames(StringUtil.Array2String(userNames));
			}
			if (null != role.getSystemResources()
					&& role.getSystemResources().size() > 0) {
				Long[] systemResourceIds = new Long[role.getSystemResources()
						.size()];
				String[] systemResourceNames = new String[role
						.getSystemResources().size()];
				for (int i = 0; i < role.getSystemResources().size(); i++) {
					systemResourceIds[i] = role.getSystemResources().get(i)
							.getId();
					systemResourceNames[i] = role.getSystemResources().get(i)
							.getName();
				}
				rr.setSystemResourceIds(StringUtil
						.Array2String(systemResourceIds));
				rr.setSystemResourceNames(StringUtil
						.Array2String(systemResourceNames));
			}

			rr.setRemark(role.getRemark());
			rows.add(rr);
		}
		ExtGridList<RoleExtGridRow> roleGrid = new ExtGridList<RoleExtGridRow>();
		roleGrid.setStart(start);
		roleGrid.setLimit(limit);
		roleGrid.setTotalProperty(queryResult.getTotalrecord());
		roleGrid.setRows(rows);
		JSONObject jsonObject = JSONObject.fromObject(roleGrid);

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

	public Long[] convertUserIds(String userIds) {
		Long[] userIdLongs = new Long[userIds.split(",").length];
		for (int i = 0; i < userIds.split(",").length; i++) {
			userIdLongs[i] = Long.valueOf(userIds.charAt(i));
		}
		return userIdLongs;
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/security/Role/jsonSave.html")
	public String save(RoleExtGridRow roleR, ModelMap model) {
		Role role = new Role();
		if (null != roleR.getId() && roleR.getId() > 0) {
			role = roleService.find(roleR.getId());
		}
		role.setName(roleR.getName());
		role.setDepartment(departmentService.find(roleR.getDepartmentId()));
		role.setRemark(roleR.getRemark());
		// 设置关联用户
		if (null != roleR.getUserIds()) {
			List<User> userList = new ArrayList<User>();
			for (String userId : roleR.getUserIds().split(",")) {
				userList.add(userService.find(new Long(userId)));
			}
			role.setUsers(userList);
		}
		if (null != roleR.getSystemResourceIds()) {
			List<SystemResource> resources = new ArrayList<SystemResource>();
			for (String rId : roleR.getSystemResourceIds().split(",")) {
				resources.add(systemResourceService.find(new Long(rId)));
			}
			role.setSystemResources(resources);
		}
		if (null != role.getId() && role.getId() > 0) {
			roleService.update(role);
		} else {
			// 保存
			roleService.save(role);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		long id = role.getId() > 0 ? role.getId() : roleService.findLast()
				.getId();
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

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/security/Role/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		roleService.delete((Serializable[]) ids);
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
