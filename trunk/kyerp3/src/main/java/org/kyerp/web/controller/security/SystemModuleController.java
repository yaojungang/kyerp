package org.kyerp.web.controller.security;

import java.io.Serializable;
import java.util.LinkedHashMap;

import net.sf.json.JSONObject;

import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.security.SystemModule;
import org.kyerp.service.security.ISystemModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统模块定义
 * 
 * @author y109 2009-12-31上午09:52:00
 */
@Controller
public class SystemModuleController {
	@Autowired
	ISystemModuleService	systemModuleService;

	@RequestMapping("/security/SystemModule/jsonList.html")
	public String list(Model model, Integer start, Integer limit) {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<SystemModule> queryResult = systemModuleService
				.getScrollData(start, limit, orderby);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("start", start);
		jsonObject.put("limit", limit);
		jsonObject.put("totalProperty", queryResult.getTotalrecord());
		jsonObject.put("rows", queryResult.getResultlist());

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
	@RequestMapping("/security/SystemModule/jsonSave.html")
	public String save(SystemModuleExtGridRow r, ModelMap model) {
		SystemModule sm = new SystemModule();
		if (null != r.getId() && r.getId() > 0) {
			sm = systemModuleService.find(r.getId());
		}
		sm.setChineseName(r.getChineseName());
		sm.setName(r.getName());
		sm.setShortName(r.getShortName());
		if (null != r.getId() && r.getId() > 0) {
			systemModuleService.update(sm);
		} else {
			systemModuleService.save(sm);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		long id = null != r.getId() && r.getId() > 0 ? r.getId()
				: systemModuleService.findLast().getId();
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
	@RequestMapping("/security/SystemModule/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		systemModuleService.delete((Serializable[]) ids);
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
