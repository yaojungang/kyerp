package org.kyerp.web.controller.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.kyerp.domain.base.views.ExtGridList;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.security.Role;
import org.kyerp.domain.security.User;
import org.kyerp.service.security.IRoleService;
import org.kyerp.service.security.IUserService;
import org.kyerp.utils.StringUtil;
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
public class UserController {
	@Autowired
	IUserService	userService;
	@Autowired
	IRoleService	roleService;

	@RequestMapping("/security/User/jsonList.html")
	public String list(Model model, Integer start, Integer limit) {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<User> queryResult = userService.getScrollData(start, limit,
				orderby);
		List<UserExtGridRow> rows = new ArrayList<UserExtGridRow>();
		for (User user : queryResult.getResultlist()) {
			UserExtGridRow ug = new UserExtGridRow();
			ug.setId(user.getId());
			ug.setUserName(user.getUsername());
			ug.setRemark(user.getRemark());
			// 设置用户的角色id和name
			if (null != user.getRoles() && user.getRoles().size() > 0) {
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
		ExtGridList<UserExtGridRow> userGrid = new ExtGridList<UserExtGridRow>();
		userGrid.setStart(start);
		userGrid.setLimit(limit);
		userGrid.setTotalProperty(queryResult.getTotalrecord());
		userGrid.setRows(rows);
		JSONObject jsonObject = JSONObject.fromObject(userGrid);

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
	@RequestMapping("/security/User/jsonSave.html")
	public String save(UserExtGridRow userRow, ModelMap model) {
		User user = new User();
		if (null != userRow.getId() && userRow.getId() > 0) {
			user = userService.find(userRow.getId());
		}
		user.setUserName(userRow.getUserName());
		user.setRemark(userRow.getRemark());
		if (null != userRow.getRoleIds() && userRow.getRoleIds().length() > 0) {
			HashSet<Role> roleSet = new HashSet<Role>();
			for (String roleId : userRow.getRoleIds().split(",")) {
				roleSet.add(roleService.find(new Long(roleId)));
			}
			user.setRoles(roleSet);
		}
		if (null != userRow.getId() && userRow.getId() > 0) {
			userService.update(user);
		} else {
			userService.save(user);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		long id = user.getId() > 0 ? user.getId() : userService.findLast()
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
	@RequestMapping("/security/User/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		userService.delete((Serializable[]) ids);
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
