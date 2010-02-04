package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.kyerp.domain.base.views.PageView;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.EnteringMaterial;
import org.kyerp.service.warehouse.IEnteringMaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
public class EnteringMaterialController {
	@Resource(name = "enteringMaterialService")
	IEnteringMaterialService	enteringMaterialService;

	@RequestMapping("/warehouse/EnteringMaterial/index.html")
	public void list(ModelMap model, Integer page) {
		page = null == page || page < 1 ? 1 : page;
		PageView<EnteringMaterial> pageView = new PageView<EnteringMaterial>(
				12, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createTime", "desc");
		QueryResult<EnteringMaterial> qureyResult = enteringMaterialService
				.getScrollData(pageView.getFirstResult(), pageView
						.getMaxresult(), orderby);
		pageView.setQueryResult(qureyResult);
		model.addAttribute("pageView", pageView);
	}

	@RequestMapping("/warehouse/EnteringMaterial/add.html")
	public String add(ModelMap model) {
		return "foward:input.html";

	}

	@RequestMapping("/warehouse/EnteringMaterial/input.html")
	public void input(ModelMap model) {

	}

	@RequestMapping("/warehouse/EnteringMaterial/save.html")
	public String save(EnteringMaterial enteringMaterial, ModelMap model) {
		enteringMaterialService.save(enteringMaterial);
		return "redirect:index.html";

	}

	@RequestMapping("/warehouse/EnteringMaterial/del.html")
	public String del(Long[] ids) {
		enteringMaterialService.delete((Serializable[]) ids);
		return "redirect:index.html";
	}
}
