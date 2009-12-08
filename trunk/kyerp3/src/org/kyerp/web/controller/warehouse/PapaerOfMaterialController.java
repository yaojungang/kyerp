/**
 * 
 */
package org.kyerp.web.controller.warehouse;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.kyerp.domain.base.views.PageView;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.PaperOfMaterial;
import org.kyerp.service.warehouse.IPaperOfMaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
public class PapaerOfMaterialController {
	@Resource(name = "paperOfMaterialService")
	IPaperOfMaterialService	paperOfMaterialService;

	@RequestMapping("/warehouse/PaperOfMaterial/index.html")
	public void list(ModelMap model, Integer page) {
		page = null == page || page < 1 ? 1 : page;
		PageView<PaperOfMaterial> pageView = new PageView<PaperOfMaterial>(12,
				page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createTime", "desc");
		QueryResult<PaperOfMaterial> qureyResult = paperOfMaterialService
				.getScrollData(pageView.getFirstResult(), pageView
						.getMaxresult(), orderby);
		pageView.setQueryResult(qureyResult);
		model.addAttribute("pageView", pageView);
	}

	@RequestMapping("/warehouse/PaperOfMaterial/add.html")
	public String add(ModelMap model) {
		return "redirect:input.html";

	}

	@RequestMapping("/warehouse/PaperOfMaterial/input.html")
	public void input(ModelMap model) {

	}

	@RequestMapping("/warehouse/PaperOfMaterial/save.html")
	public String save(PaperOfMaterial paper, ModelMap model) {
		paperOfMaterialService.save(paper);
		return "redirect:index.html";

	}
}
