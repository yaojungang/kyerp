package org.kyerp.web.controller.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.kyerp.domain.base.views.ExtGridList;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.security.SystemResource;
import org.kyerp.service.security.ISystemModuleService;
import org.kyerp.service.security.ISystemResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统资源管理
 * 
 * @author y109 2009-12-31上午09:52:00
 */
@Controller
public class SystemResourceController {
	@Autowired
	ISystemResourceService	systemResourceService;
	@Autowired
	ISystemModuleService	systemModuleService;

	@RequestMapping("/security/SystemResource/jsonList.html")
	public String list(Model model, Integer start, Integer limit) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<SystemResource> queryResult = systemResourceService
				.getScrollData(null == start ? 0 : start, null == limit ? 20
						: limit, orderby);

		List<SystemResourceExtGridRow> rows = new ArrayList<SystemResourceExtGridRow>();
		for (SystemResource systemResource : queryResult.getResultlist()) {
			SystemResourceExtGridRow sg = new SystemResourceExtGridRow();
			sg.setId(systemResource.getId());
			sg.setName(systemResource.getName());
			sg.setType(systemResource.getType());
			sg.setContent(systemResource.getContent());
			sg.setRemark(systemResource.getRemark());
			sg.setSystemModuleId(systemResource.getSystemModule().getId());
			sg.setSystemModuleName(systemResource.getSystemModule()
					.getChineseName());
			rows.add(sg);
		}
		ExtGridList<SystemResourceExtGridRow> sGrid = new ExtGridList<SystemResourceExtGridRow>();
		sGrid.setStart(start);
		sGrid.setLimit(limit);
		sGrid.setTotalProperty(queryResult.getTotalrecord());
		sGrid.setRows(rows);
		JSONObject jsonObject = JSONObject.fromObject(sGrid);

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
	@RequestMapping("/security/SystemResource/jsonSave.html")
	public String save(SystemResourceExtGridRow srExtRow, ModelMap model) {
		SystemResource srs = new SystemResource();
		if (null != srExtRow.getId() && srExtRow.getId() > 0) {
			srs = systemResourceService.find(srExtRow.getId());
		}
		srs.setSystemModule(systemModuleService.find(srExtRow
				.getSystemModuleId()));
		srs.setType(srExtRow.getType());
		srs.setName(srExtRow.getName());
		srs.setContent(srExtRow.getContent());
		srs.setRemark(srExtRow.getRemark());
		if (null != srExtRow.getId() && srExtRow.getId() > 0) {
			systemResourceService.update(srs);
		} else {
			systemResourceService.save(srs);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		long id = srs.getId() > 0 ? srs.getId() : systemResourceService
				.findLast().getId();
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
	@RequestMapping("/security/SystemResource/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		systemResourceService.delete((Serializable[]) ids);
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
